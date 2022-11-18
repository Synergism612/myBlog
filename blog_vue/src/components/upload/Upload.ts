import { baseURL, getHeaders } from "src/axios/axios";
export default class Upload {
  /**显示 */
  show: boolean;
  /**上传配置 */
  config: Config;
  constructor() {
    this.show = true;

    this.config = new Config();
  }
}

export class Config {
  /**是否能多选 */
  multiple: boolean;
  /**文件数量 */
  limit: number;
  /**请求路径 */
  action: string;
  /**头部 */
  headers: Headers;
  /**文件类型 */
  type: string;
  /**总文件大小 */
  size: number;

  constructor() {
    this.multiple = false;
    this.limit = 1;
    this.action = baseURL + "/api/blog/write/article/icon";
    this.headers = getHeaders();
    this.type = "image/jpeg";
    this.size = 5;
  }
}
