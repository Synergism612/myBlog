import CommentInformation from "./CommentInformation";

export default class CommentChild extends CommentInformation {
  parentNickname: string;

  constructor() {
    super();
    this, (this.parentNickname = "");
  }
}
