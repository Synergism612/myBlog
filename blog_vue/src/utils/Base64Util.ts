export default class Base64Util {
  public static encode(str: string): string {
    // eslint-disable-next-line @typescript-eslint/no-var-requires
    const Base64 = require("js-base64").Base64;
    return Base64.encode(str);
  }

  public static decode(str: string): string {
    // eslint-disable-next-line @typescript-eslint/no-var-requires
    const Base64 = require("js-base64").Base64;
    return Base64.decode(str);
  }
}
