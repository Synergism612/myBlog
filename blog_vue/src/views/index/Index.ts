import { api } from "@/api/api";
import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";
import UserInfo from "@/model/user/UserInfo";
import { store } from "@/store";
import StringUtil from "@/utils/StringUtil";

export default class Index {
  /**是否已经登录*/
  ifLogin: boolean;

  /**日历日期*/
  calender: Date;
  /**用户信息*/
  userInfo: UserInfo;
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
        api.getIndexUserInfo().then(({ data }) => {
          this.userInfo = data;
          this.ifLogin = false;
        });
      }
    };

    const tags = (): void => {
      api.getIndexTag(this.userInfo.username).then(({ data }): void => {
        this.tagInformationList = data;
      });
    };

    const classify = (): void => {
      api.getIndexClassify(this.userInfo.username).then(({ data }): void => {
        this.classifyInformationList = data;
      });
    };
    userInfo();
    tags();
    classify();
  }
}
