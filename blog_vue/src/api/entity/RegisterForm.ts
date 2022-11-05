export default class RegisterForm {
  /**账号 */
  username: string;
  /**密码 */
  password: string;
  /**二次输入密码 */
  passwordAgen: string;
  /**验证码 */
  code: string;
  /**验证码密钥 */
  key: string;

  constructor() {
    this.username = "";
    this.password = "";
    this.passwordAgen = "";
    this.code = "";
    this.key = "";
  }
}
