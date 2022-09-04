import axios from "@/axios/axios";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import RSAUtil from "@/utils/RSAUtil";
import StringUtil from "@/utils/StringUtil";

export class api {
  public static getPublicKey(): Promise<void> {
    //开启请求
    return axios({
      url: "/api/public/key",
      method: "get",
      data: {
        id: "152",
      },
    })
      .then(({ data }) => {
        //判断store中是否存在钥匙,若不存在,初始化钥匙
        if (
          StringUtil.checkStringsIfEmpty([
            store.state.ANOTHER_WORLD_KEY,
            store.state.PUBLIC_KEY,
            store.state.KEY,
          ])
        ) {
          //更改公钥值
          store.commit("SET_PUBLIC_KEY", data);
          console.log("公钥钥  " + data);
          //生成钥匙
          const key = AESUtil.getKey();
          console.log("对称钥  " + key);
          store.commit("SET_KEY", key);
          console.log("加密对称钥  " + RSAUtil.encryptedData(key, data));
          store.commit(
            "SET_ANOTHER_WORLD_KEY",
            //公钥加密密钥
            AESUtil.encrypt(key, store.state.PUBLIC_KEY)
          );
        }
      })
      .catch((err) => {
        console.log("err:\n" + err);
      });
  }

  public static getTest(): string {
    //开启请求
    axios({
      url: "/api/public/test",
      method: "post",
      data: {
        id: "152",
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
}
