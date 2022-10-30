import { api } from "@/api/api";
import Classify from "@/model/classify/Classify";
import Tag from "@/model/tag/Tag";

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
}
