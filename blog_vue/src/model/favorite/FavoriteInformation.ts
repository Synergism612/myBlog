import Collection from "./Collection";
import Favorite from "./Favorite";

export default class FavoriteInformation extends Favorite {
  collectionList: Array<Collection>;
  nickname: string;
  username: string;

  constructor() {
    super();
    this.collectionList = [new Collection()];
    this.nickname = "";
    this.username = "";
  }
}