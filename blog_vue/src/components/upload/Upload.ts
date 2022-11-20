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
  type: string;
  /**总文件大小 */
  size: number;

  constructor(
    multiple?: boolean,
    limit?: number,
    type?: string,
    size?: number
  ) {
    this.multiple = multiple || false;
    this.limit = limit || 1;
    this.type = type || "image/jpeg";
    this.size = size || 5;
  }
}
