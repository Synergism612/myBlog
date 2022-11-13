import { api } from "src/api/api";
import CommentForm from "src/api/entity/CommentForm";
import CommentChild from "src/model/comment/CommentChild";
import CommentParent from "src/model/comment/CommentParent";
import UserInformation from "src/model/user/UserInformation";
import { store } from "src/store";
import Message from "src/utils/MessageUtil";
import StringUtil from "src/utils/StringUtil";

export default class Forum {
  articleID: number;

  commentList: Array<CommentParent>;

  commentInput: string;
  userInfo: UserInformation;
  isLogin: boolean;

  rootID: number;
  rootUsername: string;
  parentID: number;
  parentUsername: string;

  commentForm: CommentForm;

  constructor(articleID: number) {
    this.articleID = articleID;

    this.commentList = [new CommentParent()];

    this.commentInput = "";
    this.userInfo = store.getters.getUser;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.userInfo.username);

    this.rootID = -1;
    this.rootUsername = "";
    this.parentID = -1;
    this.parentUsername = "";

    this.commentForm = new CommentForm();
  }
  public getCommentForm(): CommentForm {
    this.commentForm.username = this.userInfo.username;
    this.commentForm.comment = this.commentInput;
    this.commentForm.articleID = this.articleID;
    this.commentForm.rootID = this.rootID;
    this.commentForm.parentID = this.parentID;
    return this.commentForm;
  }
  public init(): void {
    const commentList = (): void => {
      api.getContentCommentList(this.articleID).then(({ data }): void => {
        this.commentList = data || [new CommentParent()];
        this.commentList.forEach((commentParent: CommentParent): void => {
          if (commentParent.childCount === 0) {
            commentParent.commentChildList = [new CommentChild()];
          }
        });
      });
    };
    commentList();
  }
}
