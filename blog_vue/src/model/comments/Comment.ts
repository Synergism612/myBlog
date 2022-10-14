import UserInfo from "../user/UserInfo";

export default class Comment {
  id!: number;

  body!: string;

  likeCount!: string;

  fatherID!: number;

  father!: Comment;

  userInformation!: UserInfo;

  creationTime!: Date;

  modifyTime!: Date;
}
