import Classify from "../classify/Classify";
import Tag from "../tag/Tag";
import TagInformation from "../tag/TagInformation";
import Article from "./Article";

export default class ArticleInformation extends Article {
  /**所属者昵称 */
  nickname: string;

  ifPrivate: number;

  classify: Classify;

  tagList: Array<Tag>;

  constructor() {
    super();
    this.nickname = "";
    this.ifPrivate = -1;
    this.classify = new Classify();
    this.tagList = [new TagInformation()];
  }
}
