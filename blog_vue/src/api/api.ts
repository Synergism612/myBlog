import axios from "@/axios/axios";
import Result from "@/entity/Result";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import RSAUtil from "@/utils/RSAUtil";
import StringUtil from "@/utils/StringUtil";
import { AxiosResponse } from "axios";
import { keysOf } from "element-plus/es/utils";

export class api {
  /**
   * 获取公钥
   * @returns 返回Promise
   */
  public static getPublicKey(): Promise<void> {
    //开启请求
    return axios({
      url: "/api/public/key",
      method: "get",
      data: {
        id: "152",
      },
    })
      .then((response) => {
        const data = response.data.data;
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
        // }
      })
      .catch((err) => {
        console.log("err:\n" + err);
      });
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
   * 登录
   * @param username 用户名
   * @param password 密码
   * @returns Promise
   */
  public static login(
    username: string,
    password: string
  ): Promise<AxiosResponse<Result>> {
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
   * 获取邮箱验证码
   * @param mail 邮箱
   * @returns Promise
   */
  public static getSecurityCode(
    mail: string,
    key: string
  ): Promise<AxiosResponse<Result>> {
    return axios({
      url: "/api/mail/register/code",
      method: "get",
      params: {
        mail: mail,
        key: key,
      },
    });
  }

  public static register(
    username: string,
    password: string,
    code: string,
    key: string
  ): Promise<AxiosResponse<Result>> {
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
}
