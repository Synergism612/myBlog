import Author from "src/model/user/Author";
import { api } from "src/api/api";
import ClassifyInformation from "src/model/classify/ClassifyInformation";
import TagInformation from "src/model/tag/TagInformation";
import { store } from "src/store";

export default class Index {
  /**是否已经登录*/
  isLogin: boolean;

  /**日历日期*/
  calender: Date;
  /**用户信息*/
  author: Author;
  /**标签云列表*/
  tagInformationList: Array<TagInformation>;
  /**分类云列表 */
  classifyInformationList: Array<ClassifyInformation>;

  constructor() {
    this.author = new Author();
    this.isLogin = store.getters.getIsLogin;
    this.calender = new Date();
    this.tagInformationList = [new TagInformation()];
    this.classifyInformationList = [new ClassifyInformation()];
  }

  public init(): void {
    const author = (): void => {
      api.getIndexAuthor().then(({ data }): void => {
        this.author = data;
        this.isLogin = false;
      });
    };

    const tags = (): void => {
      api.getIndexTag().then(({ data }): void => {
        this.tagInformationList = data;
      });
    };

    const classify = (): void => {
      api.getIndexClassify().then(({ data }): void => {
        this.classifyInformationList = data;
      });
    };
    if (this.isLogin) {
      author();
    }
    tags();
    classify();
  }
}
