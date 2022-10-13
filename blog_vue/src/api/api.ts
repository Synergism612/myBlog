import axios from "@/axios/axios";
import Result from "@/model/user/Result";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import RSAUtil from "@/utils/RSAUtil";
import StringUtil from "@/utils/StringUtil";
import { AxiosResponse } from "axios";

export class api {
  /**
   * 获取公钥接口
   * @returns 返回Promise
   */
  public static async getPublicKey(): Promise<void> {
    //开启请求
    try {
      const { data } = await axios({
        url: "/api/public/key",
        method: "get",
      });
      //更改公钥值
      store.commit("SET_PUBLIC_KEY", data);
      //生成钥匙或者直接获取本地钥匙
      let key;
      if (StringUtil.checkStringIfEmpty(store.state.KEY)) {
        key = AESUtil.getKey();
      } else {
        key = store.state.KEY;
      }
      store.commit("SET_KEY", key);
      store.commit(
        "SET_ANOTHER_WORLD_KEY",
        //公钥加密密钥
        RSAUtil.encryptedData(key, data)
      );
      console.log("ANOTHER_WORLD_KEY--" + store.state.ANOTHER_WORLD_KEY);
      console.log("PUBLIC_KEY--" + store.state.PUBLIC_KEY);
      console.log("KEY--" + store.state.KEY);
      console.log("EVIL_EYE--" + store.state.EVIL_EYE);
    } catch (err) {
      store.commit("DELECT_ALL_KEY");
      console.log("err:\n" + err);
    }
  }

  /**
   * 测试函数
   * @returns 空
   */
  public static getTest(): string {
    axios({
      url: "/api/public/test",
      method: "post",
      data: {
        username: "152",
        password: "123456",
      },
    })
      .then(({ data }) => {
        console.log(data);
      })
      .catch((err) => {
        console.log("err:\n" + err);
        return false;
      });
    return "";
  }

  /**
   * 登录接口
   * @param username 用户名
   * @param password 密码
   * @returns Promise
   */
  public static login(
    username: string,
    password: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/login",
      method: "post",
      data: {
        username: username,
        password: password,
      },
    });
  }

  /**
   * 获取邮箱验证码接口
   * @param mail 邮箱
   * @returns Promise
   */
  public static getSecurityCode(
    mail: string,
    key: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/mail/register/code",
      method: "get",
      params: {
        mail: mail,
        key: key,
      },
    });
  }

  /**
   * 注册接口
   * @param username 账号
   * @param password 密码
   * @param code 验证码
   * @param key 验证码密钥
   * @returns Promise
   */
  public static register(
    username: string,
    password: string,
    code: string,
    key: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/register",
      method: "post",
      data: {
        username: username,
        password: password,
        code: code,
        key: key,
      },
    });
  }

  /**
   * 登出接口
   * @param loginID 登录信息ID
   * @param name 昵称
   * @param username 账号
   * @returns Promise
   */
  public static logout(
    loginID: string,
    name: string,
    username: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/logout",
      method: "post",
      data: {
        loginID: loginID,
        name: name,
        username: username,
      },
    });
  }

  /**
   * 首页文章分页接口
   * @param currentPage 第几页
   * @param pageSize 一页几条
   * @returns Promise
   */
  public static getIndexArticle(
    currentPage: number,
    pageSize: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/article",
      method: "get",
      params: {
        currentPage: currentPage,
        pageSize: pageSize,
      },
    });
  }

  /**
   * 首页用户信息接口
   * @returns 用户信息
   */
  public static getIndexUserInfo(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/userInfo",
      method: "get",
    });
  }

  /**
   * 首页评论信息接口
   * @returns Array[评论信息]
   */
  public static getIndexComments(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/comments",
      method: "get",
    });
  }
}
