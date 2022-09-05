import { apiEnum } from "@/enum/apiEnum";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import Result from "@/entity/Result";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import CryptoJS from "crypto-js";

//请求根路径
const baseURL = "http://localhost:8088";

const publicKeyURL = "/api/public/key";
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
    transformRequest: [
      (data: string) => {
        // 对 data 进行加密
        const encJson = AESUtil.encrypt(JSON.stringify(data), store.state.KEY);
        // return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(encJson));
        // return AESUtil.encrypt(data, store.state.KEY);
        console.log("对data进行加密" + encJson);
        return encJson;
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
      console.log("请求拦截:" + request.url);
      if (request && request.headers) {
        if (request.url == publicKeyURL) return request;
        // 多一步判断
        request.headers["ANOTHER_WORLD_KEY"] = store.state.ANOTHER_WORLD_KEY;
      }
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      return Result.getResult(response);
    });
  }
}

export default new Axios().service;
