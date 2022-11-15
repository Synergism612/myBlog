import { store } from "src/store";
export default class CollectionForm {
  username: string;
  title: string;
  href: string;
  synopsis: string;
  favoriteID: number;

  constructor() {
    this.username = store.getters.getUsername;
    this.title = "";
    this.href = "";
    this.synopsis = "";
    this.favoriteID = 0;
  }
}
