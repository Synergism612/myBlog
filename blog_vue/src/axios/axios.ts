import { apiEnum } from "@/enum/apiEnum";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import Result from "@/entity/Result";
import store from "@/store";
import AESUtil from "@/utils/AESUtil";

//请求根路径
const baseURL = "http://localhost:8088";

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
      ANOTHER_WORLD_KEY: " ",
    },
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    // transformRequest: [
    //   (data: string) => {
    //     // 对 data 进行加密
    //     return AESUtil.encrypt(JSON.stringify(data), store.state.KEY);
    //   },
    // ],
    // `transformResponse` 在传递给 then/catch 前，允许修改响应数据
    // transformResponse: [
    //   (data: string) => {
    //     // 对 data 进行解密
    //     return AESUtil.decrypt(JSON.stringify(data), store.state.KEY);
    //   },
    // ],
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
      }
      console.log("请求拦截:" + request.url);
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      console.log("响应拦截:" + response.data.data);
      return Result.getResult(response);
    });
  }
}

export default new Axios().service;
