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
  };

  // 创建axios对象
  service: AxiosInstance;
  //构造函数
  constructor() {
    this.service = axios.create(this.config);
    //请求拦截
    this.service.interceptors.request.use((request: AxiosRequestConfig) => {
      console.log("请求拦截" + request.url);
      if (request && request.headers) {
        // 多一步判断
        request.headers["ANOTHER_WORLD_KEY"] = store.state.ANOTHER_WORLD_KEY;
        request.headers["AUTH_ID"] = store.state.AUTH_ID;
      }
      // 对 data 进行加密
      request.data = AESUtil.encrypt(
        JSON.stringify(request.data),
        store.state.KEY
      );

      // 对 params 进行加密
      request.params = {
        params: AESUtil.encrypt(
          JSON.stringify(request.params),
          store.state.KEY
        ),
      };
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      console.log("响应拦截" + JSON.stringify(response.data));
      let result;
      //前端对应的安全策略
      const auth_id = response.headers["auth_id"];
      if (!StringUtil.checkStringIfEmpty(auth_id))
        store.commit("SET_AUTH_ID", auth_id);
      if (StringUtil.checkStringIfEmpty(store.state.ANOTHER_WORLD_KEY)) {
        result = Result.getResult(response);
      } else {
        try {
          //对响应的数据解密
          const json = JSON.parse(
            AESUtil.decrypt(response.data, store.state.KEY)
          ).ANOTHER_WORLD_RESPONSE;
          console.log(json);
          result = new Result(json.code, json.msg, json.time, json.data);
          //全局异常处理
        } catch {
          result = Result.getResult(response);
        }
      }
      if (result.code != 200) {
        Message.errorMessage(result.msg);
      }
      response.data = result;
      return response;
    });
  }
}

export default new Axios().service;
