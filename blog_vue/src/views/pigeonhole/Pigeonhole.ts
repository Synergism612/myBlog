import { store } from "src/store";
import { api } from "src/api/api";
import Archive from "src/model/article/Archive";

export default class Pigeonhole {
  isMy: boolean;

  username: string;

  archiveList: Array<Archive>;

  monthList: Array<string>;
  constructor() {
    this.isMy = true;
    this.username = store.getters.getUsername;
    this.archiveList = [new Archive()];
    this.monthList = ["空"];
  }

  public init(): void {
    if (this.isMy) {
      api.getPifeonholeArchive(this.username).then(({ data }): void => {
        this.archiveList = data;
        this.monthList = this.archiveList.map((archive: Archive): string => {
          return archive.month;
        });
      });
    } else {
      api.getPifeonholeArchive("").then(({ data }): void => {
        this.archiveList = data;
        this.monthList = this.archiveList.map((archive: Archive): string => {
          return archive.month;
        });
      });
    }
  }
}
