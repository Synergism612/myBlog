export default class Article {
  /**主键 */
  id: number;
  /**标题 */
  title: string;
  /**头像 */
  icon: string;
  /**内容 */
  body: string;
  /**简介 */
  synopsis: string;
  /**阅读量 */
  views: number;
  /**点赞量 */
  likeCount: number;
  /**创建时间 */
  creationTime: Date | null;
  /**修改时间 */
  modifyTime: Date | null;

  constructor() {
    this.id = -1;
    this.title = "";
    this.icon = "";
    this.body = "";
    this.synopsis = "";
    this.views = 0;
    this.likeCount = 0;
    this.creationTime = null;
    this.modifyTime = null;
  }
}
