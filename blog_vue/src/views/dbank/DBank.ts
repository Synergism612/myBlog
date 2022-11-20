import { Config } from "src/components/upload/Upload";
import { api } from "src/api/api";
import File from "src/model/folder/File";
import FolderInformation from "src/model/folder/FolderInformation";
import { store } from "src/store";
import FolderForm from "src/api/entity/FolderForm";

export default class DBank {
  isLogin: boolean;

  path: string;

  parentID: number;

  parentPath: string;

  isRepository: boolean;

  folderInformation: FolderInformation;

  DataList: Array<File>;

  uploadShow: boolean;

  config: Config;

  folderForm: FolderForm;

  saveFolderShow: boolean;

  constructor() {
    this.isLogin = store.getters.getIsLogin;
    this.path = "";
    this.parentID = -1;
    this.parentPath = "";
    this.isRepository = true;
    this.folderInformation = new FolderInformation();
    this.DataList = this.getDataArray();
    this.uploadShow = false;
    this.config = new Config();
    this.folderForm = new FolderForm();
    this.saveFolderShow = false;
  }

  public init(): void {
    api.getDBankRepository().then(({ data }): void => {
      this.isRepository = true;
      this.folderInformation = data as FolderInformation;
      this.DataList = this.getDataArray();
      this.path = data.path;
    });
    console.log();
  }

  public getDataArray(): Array<File> {
    if (this.folderInformation.fileList === null) {
      return this.folderInformation.folderList.map((folder) => {
        return folder as File;
      });
    }
    return this.folderInformation.fileList.concat(
      this.folderInformation.folderList.map((folder) => {
        return folder as File;
      })
    );
  }
  public updateDataArray(path: string): void {
    if (this.parentID !== this.folderInformation.id && this.isRepository) {
      this.parentID = this.folderInformation.id;
      this.parentPath = this.folderInformation.path;
    }
    api.getDBankFolder(path).then(({ data }): void => {
      this.isRepository = false;
      this.folderInformation = data;
      this.DataList = this.getDataArray();
      this.path = this.folderInformation.path;
    });
  }

  public folderFormInit(): void {
    this.folderForm = new FolderForm();
  }
}
