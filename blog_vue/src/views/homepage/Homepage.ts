import { api } from "src/api/api";
import FavoriteForm from "src/api/entity/FavoriteForm";
import UserInformationForm from "src/api/entity/UserInformationForm";
import Collection from "src/model/favorite/Collection";
import FavoriteInformation from "src/model/favorite/FavoriteInformation";
import Author from "src/model/user/Author";
import { store } from "src/store";

export default class Homepage {
  author: Author;
  //本地账号
  username: string;

  changeShow: boolean;
  //头像
  icon: string;

  userInformationForm: UserInformationForm;

  tabsName: string;

  collapseName: string;

  favoriteInformationList: Array<FavoriteInformation>;
  myFavoriteShow: boolean;

  saveCollectionShow: boolean;
  saveCollectionfavoriteID: number;

  saveFavoriteShow: boolean;
  favoriteForm: FavoriteForm;
  isFavoriteFormEdit: boolean;

  constructor() {
    this.author = new Author();
    this.username = store.getters.getUsername;
    this.changeShow = true;
    this.icon = "";

    this.userInformationForm = new UserInformationForm();

    this.tabsName = "first";
    this.collapseName = "";

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
    this.updateAuthor();
    this.updateMyFavorite();
    this.collapseName = this.favoriteInformationList[0].name;
  }

  public updateAuthor(): void {
    api.getHomepageAuthor().then(({ data }): void => {
      this.author = data;
      this.userInit();
    });
  }

  public updateMyFavorite(): void {
    api.getHomepageFavoriteInformationList().then(({ data }): void => {
      this.favoriteInformationList = data || [new FavoriteInformation()];
      this.favoriteInformationList.forEach(
        (favoriteInformation: FavoriteInformation): void => {
          if (favoriteInformation.collectionList.length === 0) {
            favoriteInformation.collectionList = [new Collection()];
          }
        }
      );
    });
  }

  public userInit(): void {
    this.icon = this.author.icon;
    this.userInformationForm.username = this.username;
    this.userInformationForm.nickname = this.author.nickname;
    this.userInformationForm.birthday = this.author.birthday;
    this.userInformationForm.sex = this.author.sex;
    this.userInformationForm.intro = this.author.intro;
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
