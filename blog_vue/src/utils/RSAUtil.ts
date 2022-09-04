import JSEncrypt from "jsencrypt";
// import CryptoJS from "crypto-js";

/**
 * RSA非对称加密工具类
 */
export default class AESUtil {
  /**
   * 加密
   * @param {String} word 要加密的内容
   * @param {String} publicKey 公钥
   * @returns 加密后的密文
   */
  public static encryptedData(word: string, publicKey: string): string {
    //判空
    if (publicKey == null) {
      return "";
    }

    console.log("密文" + word + "\n" + "公钥" + publicKey);
    //转编码
    // const key = CryptoJS.enc.Base64.parse(publicKey);
    // const srcs = CryptoJS.enc.Base64.parse(word);
    // 新建JSEncrypt对象
    const encryptor = new JSEncrypt();
    // 设置公钥
    encryptor.setPublicKey(publicKey);
    // 加密数据
    return encryptor.encrypt(word) || "";
  }

  //前端不需要拿到私钥进行解密，故不写加密方法
}
