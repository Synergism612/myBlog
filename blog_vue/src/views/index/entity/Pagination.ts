import Article from "./Article";

export default class Pagination {
  //文章信息列表
  articleList!: Array<Article>;
  //总数
  total!: number;

  constructor(articleList?: Array<Article>, total?: number) {
    this.articleList = articleList || [];
    this.total = total || 0;
  }

  /**
   * getPagination
   */
  // eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/explicit-module-boundary-types
  public static getPagination(data: any): Pagination {
    return new Pagination(data.articleList, data.total);
  }
}
