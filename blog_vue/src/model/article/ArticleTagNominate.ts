import Article from "./Article";

export default class ArticleTagNominate extends Article {
  tagCount: number;

  constructor() {
    super();
    this.tagCount = -1;
  }
}
