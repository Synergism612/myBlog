export default class FavoriteForm {
  /**主键 */
  id: number;
  /**收藏夹名称 */
  name: string;
  /**收藏夹注释 */
  annotation: string;
  /**公开/私密 */
  ifPrivate: number;
  /**账号 */
  username: string;
  constructor() {
    this.id = -1;
    this.name = "";
    this.annotation = "";
    this.ifPrivate = 0;
    this.username = "";
  }
}
