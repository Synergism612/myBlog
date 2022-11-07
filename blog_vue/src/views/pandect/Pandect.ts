import { api } from "src/api/api";
import ClassifyInformation from "src/model/classify/ClassifyInformation";
import TagInformation from "src/model/tag/TagInformation";
import { store } from "src/store";

export default class Pandect {
  keyword: string;

  classifyList: Array<ClassifyInformation>;
  tagList: Array<TagInformation>;

  classifyIDList: Array<number>;
  tagIDList: Array<number>;

  constructor() {
    this.keyword = "";

    this.classifyList = [new ClassifyInformation()];
    this.tagList = [new TagInformation()];

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
}
