import ArticleInformation from "src/model/article/ArticleInformation";
import ArticleSort from "src/model/article/ArticleSort";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";

export default class pandectArticle {
  /**文章排序列表*/
  articleSort = ArticleSort;

  /**是否已经登录 */
  isLogin: boolean;
  /**登录的用户名 */
  username: string;

  /**选择我的文章 */
  isMy: boolean;

  refresh: boolean;

  /**分页页数*/
  currentPage: number;
  /**分页页容量*/
  pageSize: number;
  /**排序字段*/
  articleOrderBy: string;

  /**文章信息列表*/
  articleInformationList: Array<ArticleInformation>;
  /**文章总数*/
  total: number;

  keyword: string;
  classifyIDList: Array<number>;
  tagIDList: Array<number>;

  constructor() {
    this.username = store.getters.getUsername;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.username);
    this.isMy = false;
    this.refresh = true;
    this.currentPage = 1;
    this.pageSize = 10;
    this.articleOrderBy = this.articleSort[0];
    this.articleInformationList = [new ArticleInformation()];
    this.total = 0;

    this.keyword = "";
    this.classifyIDList = [-1];
    this.tagIDList = [-1];
  }
}
