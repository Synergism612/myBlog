import axios from "@/axios/axios";
// import { Result } from "@/result/Result";

export class api {
  public static getPublicKey(): string {
    //导出方法
    axios({
      url: "/api/public/key",
      method: "get",
    })
      .then(({ data }) => {
        return data;
      })
      .catch((err) => {
        console.log("err:\n" + err);
        return false;
      });
    return "";
  }
}
