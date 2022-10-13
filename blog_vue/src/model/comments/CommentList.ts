import Comment from "./comment";

export default class CommentList {
  comments!: Array<Comment>;

  constructor(comments?: Array<Comment>) {
    this.comments = comments || [];
  }

  public static getCommentList(data: Array<Comment>): CommentList {
    return new CommentList(data);
  }
}
