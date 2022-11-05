import { api } from "@/api/api";
import CollectionForm from "@/api/entity/CollectionForm";
import Favorite from "@/model/favorite/Favorite";

export default class Enshrine {
  collectionForm: CollectionForm;
  favoriteList: Array<Favorite>;

  collectionShow: boolean;
  constructor() {
    this.collectionForm = new CollectionForm();
    this.favoriteList = [new Favorite()];

    this.collectionShow = true;
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
