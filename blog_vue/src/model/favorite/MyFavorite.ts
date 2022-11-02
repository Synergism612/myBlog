import Favorite from "./Favorite";

export default class MyFavorite extends Favorite {
  collectionList: Array<Collection>;

  constructor() {
    super();
    this.collectionList = [new Collection()];
  }
}

class Collection {
  id: number;
  title: string;
  href: string;
  synopsis: string;

  constructor() {
    this.id = -1;
    this.title = "";
    this.href = "";
    this.synopsis = "";
  }
}
