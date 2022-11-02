import { api } from "@/api/api";
import Favorite from "@/model/favorite/Favorite";

export default class Enshrine {
  favoriteForm: FavoriteForm;
  favoriteList: Array<Favorite>;

  favoriteShow: boolean;
  constructor() {
    this.favoriteForm = new FavoriteForm();
    this.favoriteList = [new Favorite()];

    this.favoriteShow = true;
  }

  public init(
    title: string,
    href: string,
    synopsis: string,
    favoriteID: number,
    username: string
  ): void {
    this.favoriteForm.title = title;
    this.favoriteForm.href = href;
    this.favoriteForm.synopsis = synopsis;
    this.favoriteForm.favoriteID = favoriteID;
    api.getEnshrineFavorite(username).then(({ data }) => {
      this.favoriteList = data;
    });
  }
}
class FavoriteForm {
  title: string;
  href: string;
  synopsis: string;
  favoriteID: number;

  constructor() {
    this.title = "";
    this.href = "";
    this.synopsis = "";
    this.favoriteID = 0;
  }
}
