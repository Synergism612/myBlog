export default class Favorite {
  id: number;
  name: string;
  annotation: string;
  ifPrivate: number;

  constructor() {
    this.id = -1;
    this.name = "";
    this.annotation = "";
    this.ifPrivate = -1;
  }
}
