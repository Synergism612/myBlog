import UserInfo from "./UserInfo";

export default class Author extends UserInfo {
  sexName:string;
  articleCount: number;
  notableCount: number;
  fansCount: number;

  constructor() {
    super();
    this.sexName = "";
    this.articleCount = -1;
    this.notableCount = -1;
    this.fansCount = -1;
  }
}
