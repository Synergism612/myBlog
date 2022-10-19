import Classify from "../classify/Classify";
import Tag from "../tag/Tag";
import UserInfo from "../user/UserInfo";

export default class ArticleInformation {
  id!: number;

  title!: string;

  icon!: string;

  body!: string;

  synopsis!: string;

  views!: string;

  likeCount!: string;

  userName!: string;

  ifPrivate!: number;

  classifyList!: Array<Classify>;

  tagList!: Array<Tag>;

  creationTime!: Date;

  modifyTime!: Date;
}
