/**
 * store的工具类
 */
export default class StoreUtil {
  /**
   * 从本地序列化中寻找对应键值
   * @param key 键名
   * @returns 对应键值
   */
  public static fetch(key: string): string | false {
    if (localStorage.getItem(key) == null) {
      return false;
    }
    console.log(localStorage.getItem(key));
    return JSON.parse(localStorage.getItem(key) || " ");
  }
  /**
   * 将键值对存储到本地序列化中
   * @param key 键名
   * @param value 键值
   * @returns 不反回或失败
   */
  public static save(key: string | null, value: string): void | boolean {
    if (key == null) {
      return false;
    }
    localStorage.setItem(key, JSON.stringify(value));
  }
}
