import Article from "@/model/article/Article";
import Classify from "@/model/classify/Classify";
import CommentParent from "@/model/comment/CommentParent";
import Tag from "@/model/tag/Tag";
import Author from "@/model/user/Author";

export default class Content {
  id: number;
  title: string;
  article: Article;
  author: Author;
  classify: Classify;
  tagList: Array<Tag>;
  commentParentList: Array<CommentParent>;

  constructor() {
    this.id = -1;
    this.title = "";
    this.article = new Article();
    this.author = new Author();
    this.classify = new Classify();
    this.tagList = [new Tag()];
    this.commentParentList = [new CommentParent()];
  }
}
