import { api } from "@/api/api";
import FavoriteForm from "@/api/entity/FavoriteForm";
import Collection from "@/model/favorite/Collection";
import FavoriteInformation from "@/model/favorite/FavoriteInformation";
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

  favoriteInformationList: Array<FavoriteInformation>;
  myFavoriteShow: boolean;

  saveCollectionShow: boolean;
  saveCollectionfavoriteID: number;

  saveFavoriteShow: boolean;
  favoriteForm: FavoriteForm;
  isFavoriteFormEdit: boolean;

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
    this.collapseName = 0;

    this.favoriteInformationList = [new FavoriteInformation()];
    this.myFavoriteShow = true;

    this.saveCollectionShow = false;
    this.saveCollectionfavoriteID = 0;

    this.saveFavoriteShow = false;
    this.favoriteForm = new FavoriteForm();
    this.isFavoriteFormEdit = false;
  }

  public init(): void {
    if (this.username === "") return;
    api.getHomepageAuthor(this.username).then(({ data }) => {
      this.author = data;
      this.userInit();
    });
    api.getHomepageFavoriteInformationList(this.username).then(({ data }) => {
      this.favoriteInformationList = data || [new FavoriteInformation()];
      this.favoriteInformationList.forEach(
        (favoriteInformation: FavoriteInformation) => {
          if (favoriteInformation.collectionList.length === 0) {
            favoriteInformation.collectionList = [new Collection()];
          }
        }
      );
    });
  }

  public updateMyFavorite(): void {
    api.getHomepageFavoriteInformationList(this.username).then(({ data }) => {
      this.favoriteInformationList = data;
    });
  }

  public userInit(): void {
    this.icon = this.author.icon;
    this.nickname = this.author.nickname;
    this.birthday = this.author.birthday;
    this.sex = this.author.sex;
    this.intro = this.author.intro;
  }

  public favoriteFormInit(favoriteInformation?: FavoriteInformation): void {
    if (favoriteInformation) {
      this.isFavoriteFormEdit = true;
      this.favoriteForm.id = favoriteInformation.id;
      this.favoriteForm.name = favoriteInformation.name;
      this.favoriteForm.annotation = favoriteInformation.annotation;
      this.favoriteForm.ifPrivate = favoriteInformation.ifPrivate;
    } else {
      this.isFavoriteFormEdit = false;
      this.favoriteForm = new FavoriteForm();
    }
  }
}
