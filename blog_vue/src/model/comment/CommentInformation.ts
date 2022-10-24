import Comment from "./Comment";

export default class CommentInformation extends Comment {
  username: string;
  icon: string;
  nickname: string;

  constructor() {
    super();
    this.username = "";
    this.icon = "";
    this.nickname = "";
  }
}
