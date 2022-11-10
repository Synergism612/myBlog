import Article from "./Article";

export default class Archive {
  month: string;
  date: string;
  articleList: Array<Article>;

  constructor(){
    this.month = "";
    this.date = "";
    this.articleList = [new Article()];
  }
}
