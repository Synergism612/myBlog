import { store } from "src/store";
import { api } from "src/api/api";
import Archive from "src/model/article/Archive";

export default class Pigeonhole {
  username: string;

  archiveList: Array<Archive>;

  monthList: Array<string>;
  constructor() {
    this.username = store.getters.getUser.username;
    this.archiveList = [new Archive()];
    this.monthList = ["空"];
  }

  public init(): void {
    api.getPifeonholeArchive(this.username).then(({ data }): void => {
      this.archiveList = data;
      this.monthList = this.archiveList.map((archive: Archive) => {
        return archive.month;
      });
    });
  }
}
