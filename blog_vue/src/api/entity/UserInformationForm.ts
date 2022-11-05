export default class UserInformationForm {
  /**账号 */
  username: string;
  /**昵称 */
  nickname: string;
  /**生日*/
  birthday: string;
  /**性别代码*/
  sex: number;
  /**个人简介 */
  intro: string;

  constructor() {
    this.username = "";
    this.nickname = "";
    this.birthday = "";
    this.sex = 0;
    this.intro = "";
  }
}
