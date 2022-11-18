import Classify from "./Classify";

export default class ClassifyInformation extends Classify {
  /**分类下文章数*/
  articleCount: number;

  constructor() {
    super();
    this.articleCount = -1;
  }
}
