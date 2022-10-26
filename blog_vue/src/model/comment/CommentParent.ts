import CommentChild from "./CommentChild";
import CommentInformation from "./CommentInformation";

export default class CommentParent extends CommentInformation {
  commentChildList: Array<CommentChild>;
  childCount: number;

  constructor() {
    super();
    this.commentChildList = [new CommentChild()];
    this.childCount = -1;
    console.log(this.id);
  }
}
