import ArticleInformation from "./ArticleInformation";

export default class Pagination {
  //文章信息列表
  articleInformationList!: Array<ArticleInformation>;
  //总数
  total!: number;

  constructor(
    articleInformationList?: Array<ArticleInformation>,
    total?: number
  ) {
    this.articleInformationList = articleInformationList || [];
    this.total = total || 0;
  }

  /**
   * getPagination
   */
  // eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/explicit-module-boundary-types
  public static getPagination(data: any): Pagination {
    return new Pagination(data.articleInformationList, data.total);
  }
}
