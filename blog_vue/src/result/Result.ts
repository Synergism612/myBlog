export class Result {
  code!: number;
  msg!: string;
  time!: string;
  data = {};

  constructor(code: number, msg: string, time: string, data: { a?: string }) {
    this.code = code;
    this.msg = msg;
    this.time = time;
    this.data = data;
  }
}
