/**
 * 字符串工具类
 */
export default class StringUtil {
  /**
   * @param str 字符串
   * @return 为空则真，反之则假
   */
  public static checkStringIfEmpty(str: string | null | undefined): boolean {
    return (
      str == null ||
      str.length == 0 ||
      str == "null" ||
      str == undefined ||
      str == "" ||
      str == "none" ||
      str == "undefined"
    );
  }

  /**
   * @param str 字符串
   * @return 为空则真，反之则假
   */
  public static checkStringsIfEmpty(strs: string[]): boolean {
    let result = false;
    strs.forEach((str) => {
      result = this.checkStringIfEmpty(str);
    });
    return result;
  }

  /**
   * 检查传入的字符串是否存在不安全字符
   *
   * @param str 字符串或者其它
   */
  public static checkStringIfUnsafe(str: string): boolean {
    let count = 0;
    for (let i = 0; i < str.length; i++) {
      switch (str[i]) {
        case " ":
        case "'":
        case '"':
        case "\\":
        case "/":
        case "&":
        case "|":
        case "^":
        case "#":
        case "$":
          return true;
        case "@":
        case "-":
          count++;
      }
    }
    if (count > 1) return true;

    return false;
  }
}
