import UserInfo from "./UserInfo";

export default class Author extends UserInfo {
  articleCount: number;
  notableCount: number;
  fansCount: number;

  constructor() {
    super();
    this.articleCount = -1;
    this.notableCount = -1;
    this.fansCount = -1;
  }
}
