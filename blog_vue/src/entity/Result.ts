import Base64Util from "@/utils/Base64Util";
import { AxiosResponse } from "axios";

export default class Result {
  code!: number;
  msg!: string;
  time!: string;
  data!: string;

  constructor(code: number, msg: string, time: string, data: string) {
    this.code = code;
    this.msg = msg;
    this.time = time;
    this.data = data;
  }

  static getResult(response: AxiosResponse): Result {
    console.log("响应拦截: " + response);

    return new Result(
      response.data.code,
      response.data.msg,
      response.data.time,
      response.data.data
    );
  }
}
