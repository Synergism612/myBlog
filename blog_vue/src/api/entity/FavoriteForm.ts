export default class FavoriteForm {
  name: string;
  annotation: string;
  ifPrivate: number;

  constructor() {
    this.name = "";
    this.annotation = "";
    this.ifPrivate = 0;
  }
}