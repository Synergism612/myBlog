export default class CollectionForm {
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
