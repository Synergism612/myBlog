import { AxiosResponse } from "axios";

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export default class Result<T = any> {
  code!: number;
  msg!: string;
  time!: string;
  data!: T;

  constructor(code: number, msg: string, time: string, data: T) {
    this.code = code;
    this.msg = msg;
    this.time = time;
    this.data = data;
  }

  static getResult(response: AxiosResponse): Result {
    return new Result(
      response.data.code,
      response.data.msg,
      response.data.time,
      response.data.data
    );
  }
}
