import { api } from "@/api/api";
import Classify from "@/model/classify/Classify";
import Tag from "@/model/tag/Tag";

export default class Pandect {
  searchInput: string;

  classifyList: Array<Classify>;
  tagList: Array<Tag>;

  constructor() {
    this.searchInput = "";

    this.classifyList = [new Classify()];
    this.tagList = [new Tag()];
  }

  public init() {
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
