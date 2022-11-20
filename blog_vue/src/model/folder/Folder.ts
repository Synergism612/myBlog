export default class Folder {
  id: number;
  path: string;
  name: string;
  creationTime: string;

  constructor() {
    this.id = -1;
    this.path = "";
    this.name = "";
    this.creationTime = "";
  }
}
