import Classify from "../classify/Classify";
import Tag from "../tag/Tag";
import Article from "./Article";

export default class ArticleInformation extends Article {
  /**所属者昵称 */
  nickname: string;

  ifPrivate: number;

  classifyList: Array<Classify>;

  tagList: Array<Tag>;

  constructor(){
    super();
    this.nickname = "";
    this.ifPrivate = -1;
    this.classifyList = [];
    this.tagList = [];
  }
}
