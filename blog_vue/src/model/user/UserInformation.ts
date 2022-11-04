export default class UserInformation {
  //头像
  icon: string;
  //昵称
  nickname: string;
  //账号
  username: string;
  //生日
  birthday: Date;
  //性别代码
  sex: number;
  //性别
  sexName: string;
  //个人简介
  intro: string;
  //园龄
  upToNow: string;

  constructor(
    icon?: string,
    nickname?: string,
    username?: string,
    birthday?: Date,
    sex?: number,
    intro?: string,
    upToNow?: string
  ) {
    this.icon = icon || "";
    this.nickname = nickname || "";
    this.username = username || "";
    this.birthday = birthday || new Date();
    this.sex = sex || 0;
    this.sexName = "";
    this.intro = intro || "";
    this.upToNow = upToNow || "";
  }
}
