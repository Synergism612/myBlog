import JSEncrypt from 'jsencrypt'
import CryptoJS from 'crypto-js'

/**
 * RSA非对称加密工具类
 */

export default {
  /**
   * 
   * @param {String} word 要加密的内容 
   * @param {String} publicKey 公钥
   * @returns 
   */
  encryptedData(word, publicKey) {

    //转编码
    var key = CryptoJS.enc.Base64.parse(publicKey)
    var srcs = CryptoJS.enc.Base64.parse(word)
    // 新建JSEncrypt对象
    let encryptor = new JSEncrypt();
    // 设置公钥
    encryptor.setPublicKey(key);
    // 加密数据
    return encryptor.encrypt(srcs);
  }

  //前端不需要拿到私钥进行解密，故不写加密方法
}