import Classify from "../classify/Classify";
import Tag from "../tag/Tag";
import TagInformation from "../tag/TagInformation";
import Article from "./Article";

export default class ArticleInformation extends Article {
  /**所属者昵称 */
  nickname: string;
  /**所属者账号 */
  username: string;
  /**评论数 */
  commentCount: number;
  /**分类 */
  classify: Classify;
  /**标签列表 */
  tagList: Array<Tag>;

  constructor() {
    super();
    this.nickname = "";
    this.username = "";
    this.commentCount = -1;
    this.classify = new Classify();
    this.tagList = [new TagInformation()];
  }
}
