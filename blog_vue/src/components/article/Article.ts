import ArticleInformation from "src/model/article/ArticleInformation";

export default class Article {
  /**分页页数*/
  currentPage: number;
  /**分页页容量*/
  pageSize: number;

  /**文章信息列表*/
  articleInformationList: Array<ArticleInformation>;
  /**文章总数*/
  total: number;

  constructor() {
    this.currentPage = 1;
    this.pageSize = 10;
    this.articleInformationList = [new ArticleInformation()];
    this.total = 0;
  }
}
