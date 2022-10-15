import UserInfo from "../user/UserInfo";
import Family from "./CommentsFamily";

export default class Comments {
  id!: number;

  body!: string;

  likeCount!: string;

  parentId!: number;

  parent!: Family;

  children!: Array<Family>;

  userInformation!: UserInfo;

  creationTime!: Date;

  modifyTime!: Date;
}
