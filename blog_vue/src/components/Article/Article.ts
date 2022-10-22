import ArticleInformation from "@/model/article/ArticleInformation";
import ArticleSort from "@/model/article/ArticleSort";
import { store } from "@/store";
import StringUtil from "@/utils/StringUtil";

export default class Article {
  /**文章排序列表*/
  articleSort = ArticleSort;

  /**是否已经登录 */
  isLogin!: boolean;
  /**登录的用户名 */
  username!: string;

  isMy!: boolean;

  /**分页页数*/
  currentPage!: number;
  /**分页页容量*/
  pageSize!: number;
  /**排序字段*/
  articleOrderBy!: string;

  /**文章信息列表*/
  articleInformationList!: Array<ArticleInformation>;
  /**文章总数*/
  total!: number;

  constructor() {
    this.username = store.getters.getUser.username;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.username);;
    this.isMy = false;
    this.currentPage = 1;
    this.pageSize = 10;
    this.articleOrderBy = this.articleSort[0];
    this.articleInformationList = [new ArticleInformation()];
    this.total = 0;
  }
}
