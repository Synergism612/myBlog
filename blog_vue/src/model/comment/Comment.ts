export default class Comment {
  id: number;
  body: string;
  likeCount: number;
  rootId: number;
  parentId: number;
  creationTime: Date;
  modifyTime: Date;

  constructor() {
    this.id = -1;
    this.body = "";
    this.likeCount = 0;
    this.rootId = -1;
    this.parentId = -1;
    this.creationTime = new Date();
    this.modifyTime = new Date();
  }
}
