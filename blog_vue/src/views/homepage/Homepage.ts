import { api } from "@/api/api";
import MyFavorite from "@/model/favorite/MyFavorite";
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

  tabsName: string;

  collapseName: number;

  myFavoriteList: Array<MyFavorite>;

  addFavoriteShow: boolean;
  addFavoriteID: number;

  constructor() {
    this.author = new Author();
    this.username = store.getters.getUser.username;
    this.changeShow = true;
    this.icon = "";
    this.nickname = "";
    this.birthday = new Date();
    this.sex = 0;
    this.intro = "";

    this.tabsName = "first";
    this.collapseName = 1;

    this.myFavoriteList = [new MyFavorite()];

    this.addFavoriteShow = false;
    this.addFavoriteID = 0;
  }

  public init(): void {
    if (this.username === "") return;
    api.getHomepageAuthor(this.username).then(({ data }) => {
      this.author = data;
      this.fromInit();
    });
    api.getMyFavorite(this.username).then(({ data }) => {
      this.myFavoriteList = data;
    });
  }

  public fromInit(): void {
    this.icon = this.author.icon;
    this.nickname = this.author.nickname;
    this.birthday = this.author.birthday;
    this.sex = this.author.sex;
    this.intro = this.author.intro;
  }
}
