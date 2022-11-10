import Article from "./Article";

export default class Archive {
  year: string;
  month: string;
  date: string;
  articleList: Array<Article>;

  constructor(){
    this.year = "";
    this.month = "";
    this.date = "";
    this.articleList = [new Article()];
  }
}
