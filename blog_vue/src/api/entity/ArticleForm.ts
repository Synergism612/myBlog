export default class ArticleForm {
  /**账号 */
  username: string;
  /**主键 */
  id: number;
  /**头像 */
  icon: string;
  /**标题 */
  title: string;
  /**内容 */
  body: string;
  /**简介 */
  synopsis: string;
  /**是否私有 */
  ifPrivate: number;
  /**分类id */
  classifyID: number | null;
  /**标签id列表 */
  tagIDList: Array<number>;

  constructor() {
    this.username = "";
    this.id = -1;
    this.title = "";
    this.icon = "";
    this.body = "";
    this.synopsis = "";
    this.ifPrivate = 0;
    this.classifyID = null;
    this.tagIDList = [];
  }
}
