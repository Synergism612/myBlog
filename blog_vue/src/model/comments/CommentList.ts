import Comments from "./Comments";

export default class CommentList {
  comments!: Array<Comments>;

  constructor(comments?: Array<Comments>) {
    this.comments = comments || [];
  }

  public static getCommentList(data: Array<Comments>): CommentList {
    return new CommentList(data);
  }
}
