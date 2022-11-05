export default class Tag {
  /**主键 */
  id: number;
  /**标签名 */
  name: string;
  /**标签介绍 */
  annotation: string;
  /**创建时间 */
  creationTime: string;
  /**修改时间 */
  modifyTime: string;

  constructor() {
    this.id = -1;
    this.name = "";
    this.annotation = "";
    this.creationTime = "";
    this.modifyTime = "";
  }
}
