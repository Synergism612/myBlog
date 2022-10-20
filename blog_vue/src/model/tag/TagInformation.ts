import Tag from "./Tag";

export default class TagInformation extends Tag {
  /**标签下文章数 */
  article_count!: number;
  /**所属者昵称 */
  nickname!: string;
  /**所属者账号 */
  username!: string;

  constructor() {
    super();
    this.article_count = -1;
    this.nickname = "";
    this.username = "";
  }
}
