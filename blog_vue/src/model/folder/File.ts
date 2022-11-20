import Folder from "./Folder";

export default class File extends Folder {
  suffix: string;
  type: string;
  href: string;

  constructor() {
    super();
    this.suffix = "";
    this.type = "";
    this.href = "";
  }
}
