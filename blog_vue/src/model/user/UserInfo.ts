export default class UserInfo {
  //头像
  icon!: string;
  //昵称
  name!: string;
  //账号
  username!: string;
  //生日
  birthday!: Date;
  //性别代码
  sex!: number;
  //个人简介
  intro!: string;

  constructor(
    icon?: string,
    name?: string,
    username?: string,
    birthday?: Date,
    sex?: number,
    intro?: string
  ) {
    this.icon = icon || "";
    this.name = name || "";
    this.username = username || "";
    this.birthday = birthday || new Date();
    this.sex = sex || 0;
    this.intro = intro || "";
  }

  // eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types, @typescript-eslint/no-explicit-any
  static getUserInfo(data: UserInfo): UserInfo {
    return data;
  }
}
