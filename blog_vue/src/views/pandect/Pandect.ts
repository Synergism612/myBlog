import Message from "src/utils/MessageUtil";
import StringUtil from "src/utils/StringUtil";
import Classify from "src/model/classify/Classify";
import { api } from "src/api/api";
import Tag from "src/model/tag/Tag";
import { store } from "src/store";

export default class Pandect {
  keyword: string;

  classifyList: Array<Classify>;
  tagList: Array<Tag>;

  classifyIDList: Array<number>;
  tagIDList: Array<number>;

  constructor() {
    this.keyword = "";

    this.classifyList = [new Classify()];
    this.tagList = [new Tag()];

    this.classifyIDList = [-1];
    this.tagIDList = [-1];
  }

  public init(): void {
    const classify = (): void => {
      api.getPandectClissify().then(({ data }): void => {
        this.classifyList = data;
      });
    };
    const tag = (): void => {
      api.getPandectTag().then(({ data }): void => {
        this.tagList = data;
      });
    };

    classify();
    tag();
  }

  public isMyInit(): void {
    const username = store.getters.getUser.username;

    const classify = (): void => {
      api.getPandectClissify(username).then(({ data }): void => {
        this.classifyList = data;
      });
    };
    const tag = (): void => {
      api.getPandectTag(username).then(({ data }): void => {
        this.tagList = data;
      });
    };

    classify();
    tag();
  }

  public checkKeyword = (): boolean => {
    if (StringUtil.checkStringIfEmpty(this.keyword)) {
      return true;
    } else {
      if (StringUtil.checkStringIfUnsafe(this.keyword)) {
        Message.warningMessage("请键入合法字符");
        return false;
      } else {
        if (this.keyword.length <= 30) {
          return true;
        } else {
          Message.warningMessage("查询字段不能超过30字");
          return false;
        }
      }
    }
  };
}
