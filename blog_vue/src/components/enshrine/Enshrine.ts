import { api } from "@/api/api";
import Favorite from "@/model/favorite/Favorite";

export default class Enshrine {
  favoriteForm: FavoriteForm;
  favoriteList: Array<Favorite>;

  constructor() {
    this.favoriteForm = new FavoriteForm();
    this.favoriteList = [new Favorite()];
  }

  public init(
    title: string,
    href: string,
    annotation: string,
    username: string
  ): void {
    this.favoriteForm.title = title;
    this.favoriteForm.href = href;
    this.favoriteForm.annotation = annotation;
    api.getEnshrineFavorite(username).then(({ data }) => {
      this.favoriteList = data;
    });
  }
}
class FavoriteForm {
  title: string;
  href: string;
  annotation: string;
  favoriteID: number;

  constructor() {
    this.title = "";
    this.href = "";
    this.annotation = "";
    this.favoriteID = 0;
  }
}
