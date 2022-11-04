export default class Collection {
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
