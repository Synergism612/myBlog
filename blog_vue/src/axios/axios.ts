import { apiEnum } from "@/enum/apiEnum";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { ElNotification } from "element-plus";
import Result from "@/entity/Result";
import StoreUtil from "@/utils/StoreUtil";
// import store from "./store";
// import router from "./router";

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
    //添加自定义头部
    headers: {
      ANOTHER_WORLD_KEY: StoreUtil.fetch("ANOTHER_WORLD_KEY"),
    },
  };

  // 创建axios对象
  service: AxiosInstance;
  //构造函数
  constructor() {
    this.service = axios.create(this.config);
    //请求拦截
    this.service.interceptors.request.use((request: AxiosRequestConfig) => {
      console.log("请求拦截");
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      console.log("响应拦截");
      return Result.getResult(response);
    });
  }
}

export default new Axios().service;
