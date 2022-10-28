import CommentInformation from "./CommentInformation";

export default class CommentChild extends CommentInformation {
  parentUserName: string;
  parentNickname: string;

  constructor() {
    super();
    this.parentUserName = "";
    this.parentNickname = "";
  }
}
