import { api } from "@/api/api";
import Author from "@/model/user/Author";
import { store } from "@/store";

export default class Homepage {
  author: Author;
  //本地账号
  username: string;

  changeShow: boolean;
  //头像
  icon: string;
  //昵称
  nickname: string;
  //生日
  birthday: Date;
  //性别代码
  sex: number;
  //个人简介
  intro: string;

  constructor() {
    this.author = new Author();
    this.username = store.getters.getUser.username;
    this.changeShow = false;
    this.icon = "";
    this.nickname = "";
    this.birthday = new Date();
    this.sex = 0;
    this.intro = "";
  }

  public init(): void {
    api.getHomepageAuthor(this.username).then(({ data }) => {
      this.author = data;
      this.icon = this.author.icon;
      this.nickname = this.author.nickname;
      this.birthday = this.author.birthday;
      this.sex = this.author.sex;
      this.intro = this.author.intro;
    });
  }
}
