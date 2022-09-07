import CryptoJS from "crypto-js";

/**
 * AES工具类
 */
export default class AESUtil {
  /**
   * 解析密钥
   * @param key 密钥
   * @returns 解析后值
   */
  private static getHexKey(key: string) {
    return CryptoJS.enc.Utf8.parse(key);
  }
  /**
   * 加密
   * @param str 内容
   * @param key 密钥
   * @returns 密文
   */
  public static encrypt(str: string, key: string): string {
    const encrypted = CryptoJS.AES.encrypt(str, this.getHexKey(key), {
      iv: this.getHexKey(key), // 这个参数我之前忘传了，使用CBC的时候一直会报错，所以千万不要忘记哦，如果创建成功，但是控制台一直报错什么undefined的呀，什么[0]啥的，就检查一下 是不是自己使用有问题
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    });
    return encrypted.toString();
  }

  /**
   * 解密
   * @param str 密文
   * @param key 密钥(Base64编码)
   * @returns 内容
   */
  public static decrypt(str: string, key: string): string {
    const decrypted = CryptoJS.AES.decrypt(str, this.getHexKey(key), {
      iv: this.getHexKey(key),
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    });
    return decrypted.toString(CryptoJS.enc.Utf8);
  }

  public static getKey(): string {
    const num = 16;
    const amm = [
      "!",
      "@",
      "#",
      "$",
      "%",
      "&",
      "*",
      "(",
      ")",
      "_",
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
    ];
    let tmp = Math.floor(Math.random() * num);
    let s = "";
    s = amm[tmp] as string;
    for (let i = 0; i < Math.floor(num / 2) - 1; i++) {
      tmp = Math.floor(Math.random() * 26);
      s = s + String.fromCharCode(65 + tmp);
    }
    for (let i = 0; i < num - Math.floor(num / 2) - 1; i++) {
      tmp = Math.floor(Math.random() * 26);
      s = s + String.fromCharCode(97 + tmp);
    }
    s += amm[Math.floor(Math.random() * 19)] as string;
    return s;
  }
}
