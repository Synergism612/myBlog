import Tag from "./Tag";

export default class TagInformation extends Tag {
  /**标签下文章数 */
  articleCount: number;

  constructor() {
    super();
    this.articleCount = -1;
  }
}
