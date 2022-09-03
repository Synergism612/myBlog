import { apiEnum } from "@/enum/apiEnum";
import { Result } from "@/result/Result";
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { ElNotification } from "element-plus";
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
  };

  // 创建axios对象
  service: AxiosInstance ;
  //构造函数
  constructor() {
    this.service = axios.create(this.config);
    //请求拦截
    this.service.interceptors.request.use((request: AxiosRequestConfig) => {
      console.log("拦截");
      request.headers = {
        ANOTHER_WORLD_KEY: "",
      };
      return request;
    });

    //响应拦截
    this.service.interceptors.response.use((response: AxiosResponse) => {
      const res = response.data;
      console.log("后置拦截");
      // 当结果的code是否为200的情况
      if (res.code === 200) {
        return response;
      } else {
        // 弹窗异常信息
        ElNotification({
          title: "错误",
          type: "error",
          message: response.data.msg,
          offset: 100,
        });
        // 直接拒绝往下面返回结果信息
        return Promise.reject(response.data.msg);
      }
    });
  }
}

export default new Axios().service;
