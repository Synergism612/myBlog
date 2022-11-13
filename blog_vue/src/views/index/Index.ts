import { api } from "src/api/api";
import ClassifyInformation from "src/model/classify/ClassifyInformation";
import TagInformation from "src/model/tag/TagInformation";
import UserInformation from "src/model/user/UserInformation";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";

export default class Index {
  /**是否已经登录*/
  ifLogin: boolean;

  /**日历日期*/
  calender: Date;
  /**用户信息*/
  userInfo: UserInformation;
  /**标签云列表*/
  tagInformationList: Array<TagInformation>;
  /**分类云列表 */
  classifyInformationList: Array<ClassifyInformation>;

  constructor() {
    this.userInfo = store.getters.getUser;
    this.ifLogin = StringUtil.checkStringIfEmpty(this.userInfo.username);
    this.calender = new Date();
    this.tagInformationList = [new TagInformation()];
    this.classifyInformationList = [new ClassifyInformation()];
  }

  public init(): void {
    const userInfo = (): void => {
      if (StringUtil.checkStringIfEmpty(this.userInfo.username)) {
        api.getIndexUserInformation().then(({ data }): void => {
          this.userInfo = data;
          this.ifLogin = false;
        });
      }
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
    userInfo();
    tags();
    classify();
  }
}
