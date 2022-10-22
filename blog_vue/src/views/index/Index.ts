import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";
import UserInfo from "@/model/user/UserInfo";
import { store } from "@/store";
import StringUtil from "@/utils/StringUtil";

export default class Index {
  /**是否已经登录*/
  ifLogin!: boolean;

  /**日历日期*/
  calender!: Date;
  /**用户信息*/
  userInfo!: UserInfo;
  /**标签云列表*/
  TagInformationList!: Array<TagInformation>;
  /**分类云列表 */
  classifyInformationList!: Array<ClassifyInformation>;

  constructor() {
    this.userInfo = store.getters.getUser;
    this.ifLogin = StringUtil.checkStringIfEmpty(this.userInfo.username);
    this.TagInformationList = [new TagInformation()];
    this.classifyInformationList = [new ClassifyInformation()];
  }
}
