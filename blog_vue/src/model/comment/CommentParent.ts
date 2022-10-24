import CommentChild from "./CommentChild";
import CommentInformation from "./CommentInformation";

export default class CommentParent extends CommentInformation {
  commentChildList: Array<CommentChild>;

  constructor() {
    super();
    this.commentChildList = [new CommentChild()];
  }
}
