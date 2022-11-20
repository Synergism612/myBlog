import { store } from "src/store";

export default class RegisterForm {
  /**账号 */
  username: string;
  parentPath: string;
  name: string;

  constructor() {
    this.username = store.getters.getUsername;
    this.parentPath = "";
    this.name = "";
  }
}
