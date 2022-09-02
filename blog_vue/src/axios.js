import axios from 'axios'
// import ElementPlus from 'element-plus'
// import store from "./store";
// import router from "./router";

/**
 * 请求根路径
 */
axios.defaults.baseURL = 'http://localhost:8080'

/**
 * 请求拦截
 */
axios.interceptors.request.use((request) => {
  request.headers.common = {
    'ANOTHER_WORLD_KEY': ''
  }
  return request
});
/**
 * 响应拦截
 */
axios.interceptors.response.use((response) => {
  const res = response.data;
  console.log("后置拦截")
  // 当结果的code是否为200的情况
  if (res.code === 200) {
    return response
  } else {
    // 弹窗异常信息
    this.$notify({
      title: "错误",
      type: 'error',
      message: response.data.msg,
      offset: 100
    })
    // 直接拒绝往下面返回结果信息
    return Promise.reject(response.data.msg)
  }
});