import { api } from "@/api/api";
import Favorite from "@/model/favorite/Favorite";

export default class Enshrine {
  collectionForm: CollectionForm;
  favoriteList: Array<Favorite>;

  favoriteShow: boolean;
  constructor() {
    this.collectionForm = new CollectionForm();
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
    this.collectionForm.title = title;
    this.collectionForm.href = href;
    this.collectionForm.synopsis = synopsis;
    this.collectionForm.favoriteID = favoriteID;
    api.getEnshrineFavorite(username).then(({ data }) => {
      this.favoriteList = data;
    });
  }
}
class CollectionForm {
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
