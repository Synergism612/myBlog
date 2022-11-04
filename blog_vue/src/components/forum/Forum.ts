import { api } from "@/api/api";
import CommentChild from "@/model/comment/CommentChild";
import CommentParent from "@/model/comment/CommentParent";
import UserInformation from "@/model/user/UserInformation";
import { store } from "@/store";
import StringUtil from "@/utils/StringUtil";

export default class Forum {
  commentList: Array<CommentParent>;

  commentInput: string;
  userInfo: UserInformation;
  isLogin: boolean;

  rootID: number;
  rootUsername: string;
  parentID: number;
  parentUsername: string;

  constructor() {
    this.commentList = [new CommentParent()];

    this.commentInput = "";
    this.userInfo = store.getters.getUser;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.userInfo.username);

    this.rootID = -1;
    this.rootUsername = "";
    this.parentID = -1;
    this.parentUsername = "";
  }

  public checkInput(): boolean {
    return (
      StringUtil.checkStringIfEmpty(this.commentInput) ||
      StringUtil.checkStringIfUnsafe(this.commentInput)
    );
  }

  public init(articleID: number): void {
    const commentList = (): void => {
      api.getContentCommentList(articleID).then(({ data }) => {
        this.commentList = data || [new CommentParent()];
        this.commentList.forEach((commentParent: CommentParent) => {
          if (commentParent.childCount === 0) {
            commentParent.commentChildList = [new CommentChild()];
          }
        });
      });
    };
    commentList();
  }
}
