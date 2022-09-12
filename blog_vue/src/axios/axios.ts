import { apiEnum } from "@/enum/apiEnum";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import Result from "@/entity/Result";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import StringUtil from "@/utils/StringUtil";
import Message from "@/utils/MessageUtil";

//请求根路径
const baseURL = "http://localhost:8088";

// const publicKeyURL = "/api/public/key";
class Axios {
  //axios配置
  config = {
    //配置请求根路径
    baseURL: baseURL,
    //设置超时时间
    timeout: apiEnum.TIMEOUT,
    //跨域设置
    withCredentials: true,
    //自定义头部
    headers: {
      ANOTHER_WORLD_KEY: "",
      AUTH_ID: "",
      "Content-Type": "application/json; charset=utf-8",
      Accept: "application/json",
    },
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    transformRequest: [
      (data: string) => {
        // 对 data 进行加密
        return AESUtil.encrypt(JSON.stringify(data), store.state.KEY);
      },
    ],
  };

  // 创建axios对象
  service: AxiosInstance;
  //构造函数
  constructor() {
    this.service = axios.create(this.config);
    //请求拦截
    this.service.interceptors.request.use((request: AxiosRequestConfig) => {
      if (request && request.headers) {
        // 多一步判断
        request.headers["ANOTHER_WORLD_KEY"] = store.state.ANOTHER_WORLD_KEY;
        request.headers["AUTH_ID"] = store.state.AUTH_ID;
      }
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      //前端对应的安全策略
      const auth_id = response.headers["auth_id"];
      if (!StringUtil.checkStringIfEmpty(auth_id))
        store.commit("SET_AUTH_ID", auth_id);
      if (StringUtil.checkStringIfEmpty(store.state.ANOTHER_WORLD_KEY)) {
        return Result.getResult(response);
      } else {
        //对响应的数据解密
        const json = JSON.parse(
          AESUtil.decrypt(response.data, store.state.KEY)
        ).ANOTHER_WORLD_RESPONSE;
        console.log(json);
        const result = new Result(json.code, json.msg, json.time, json.data);
        //全局异常处理
        if (result.code != 200) {
          Message.errorMessage(result.msg);
        }
        return result;
      }
    });
  }
}

export default new Axios().service;
