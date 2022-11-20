import Folder from "./Folder";
import File from "./File";

export default class FolderInformation extends Folder {
  username: string;
  folderList: Array<Folder>;
  fileList: Array<File>;

  constructor() {
    super();
    this.username = "";
    this.folderList = [];
    this.fileList = [];
  }
}
