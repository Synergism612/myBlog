import CryptoJS from "crypto-js";

/**
 * AES对称加密工具类
 */

export default {
  /**
   *
   * @param {String} word 需要加密的内容
   * @param {String} key 钥匙
   * @returns 密文
   */
  encrypt(word, key) {
    //转编码
    key = CryptoJS.enc.Base64.parse(key);
    word = CryptoJS.enc.Base64.parse(word);
    //加密
    return CryptoJS.AES.encrypt(word, key, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    }).toString();
  },
  /**
   * 解密方法
   * @param {String} word 密文
   * @param {String} key 钥匙
   * @returns 解密后的内容
   */
  decrypt(word, key) {
    //编码
    key = CryptoJS.enc.Base64.parse(key);
    //转解密
    word = CryptoJS.AES.decrypt(word, key, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    //转编码
    return CryptoJS.enc.Base64.stringify(word).toString();
  },
};
