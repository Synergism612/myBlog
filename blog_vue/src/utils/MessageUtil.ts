import { ElNotification } from "element-plus";
import { EpPropMergeType } from "element-plus/es/utils";

export default class Message {
  private static message(
    type: EpPropMergeType<
      StringConstructor,
      "" | "success" | "warning" | "info" | "error",
      unknown
    >,
    message: string
  ): void {
    ElNotification({
      title: "出错啦!",
      dangerouslyUseHTMLString: true,
      message: "<string>" + message + "</string>",
      type: type,
      offset: 100,
    });
  }

  public static successMessage(message: string): void {
    this.message("success", message);
  }
  public static warningMessage(message: string): void {
    this.message("warning", message);
  }
  public static infoMessage(message: string): void {
    this.message("info", message);
  }
  public static errorMessage(message: string): void {
    this.message("error", message);
  }
}
