export default class CommentForm {
  /**账号 */
  username: string;
  /**评论内容 */
  comment: string;
  /**文章id */
  articleID: number;
  /**根评论id */
  rootID: number;
  /**父评论id */
  parentID: number;

  constructor() {
    this.username = "";
    this.comment = "";
    this.articleID = -1;
    this.rootID = -1;
    this.parentID = -1;
  }
}
