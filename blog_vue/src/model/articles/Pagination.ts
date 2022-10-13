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
}
