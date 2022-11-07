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
    let title = "";

    switch (type) {
      case "success":
        title = "成功哩";
        break;
      case "warning":
        title = "Warning! Warning!";
        break;
      case "info":
        title = "嗯哼, 通知";
        break;
      case "error":
        title = "咚咚咚! 心肺停止";
        break;
      default:
        title = "这里这里";
        break;
    }

    ElNotification({
      title: title,
      dangerouslyUseHTMLString: true,
      message: "<string>" + message + "</string>",
      type: type,
      offset: 100,
      showClose: false,
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
