export default class Upload {
  /**显示 */
  show: boolean;

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
  /**文件类型 */
  type: Array<string> | string;
  /**总文件大小 */
  size: number;

  constructor(
    multiple?: boolean,
    limit?: number,
    type?: Array<string> | string,
    size?: number
  ) {
    this.multiple = multiple || false;
    this.limit = limit || 1;
    this.type = type || ["image/jpeg", "image/png"];
    this.size = size || 5;
  }
}
