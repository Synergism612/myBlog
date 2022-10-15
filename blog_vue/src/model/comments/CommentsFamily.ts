import UserInfo from "../user/UserInfo";

export default class CommentsFamily {
  id!: number;

  body!: string;

  likeCount!: string;

  userInformation!: UserInfo;

  creationTime!: Date;

  modifyTime!: Date;
}
