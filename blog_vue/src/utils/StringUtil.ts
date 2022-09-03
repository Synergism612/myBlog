/**
 * 字符串工具类
 */
export default class StringUtil {
  /**
   * @param str 字符串
   * @return 为空则真，反之则假
   */
  public static checkStringIfEmpty(str: string): boolean {
    return (
      str == null ||
      str.length == 0 ||
      str == "null" ||
      str == undefined ||
      str == ""
    );
  }
}
