import ArticleInformation from "@/model/article/ArticleInformation";
import ArticleSort from "@/model/article/ArticleSort";
import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";
import UserInfo from "@/model/user/UserInfo";
import { store } from "@/store";

export default class Index {
  /**文章排序列表*/
  articleSort = ArticleSort;
  /**是否已经登录*/
  ifLogin!: boolean;
  /**分页页数*/
  currentPage!: number;
  /**分页页容量*/
  pageSize!: number;

  /**排序字段*/
  articleOrderBy!: string;
  /**日历日期*/
  calender!: Date;
  /**文章信息列表
   * */
  articleInformationList!: Array<ArticleInformation>;
  /**文章总数
   * */
  total!: number;
  /**用户信息*/
  userInfo!: UserInfo;
  /**标签云列表*/
  TagInformationList!: Array<TagInformation>;
  /**分类云列表 */
  classifyInformationList!: Array<ClassifyInformation>;

  constructor() {
    this.ifLogin = false;
    this.currentPage = 1;
    this.pageSize = 10;
    this.articleOrderBy = this.articleSort[0];
    this.calender = new Date();
    this.articleInformationList = [new ArticleInformation()];
    this.total = 0;
    this.userInfo = store.getters.getUser;
    this.TagInformationList = [new TagInformation()];
    this.classifyInformationList = [new ClassifyInformation()];
  }
}
