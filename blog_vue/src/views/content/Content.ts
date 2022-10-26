import Article from "@/model/article/Article";
import Classify from "@/model/classify/Classify";
import CommentParent from "@/model/comment/CommentParent";
import Tag from "@/model/tag/Tag";
import Author from "@/model/user/Author";

export default class Content {
  article: Article;
  author: Author;
  classify: Classify;
  tagList: Array<Tag>;
  commentParentList: Array<CommentParent>;

  refresh: number;
  editorName: string;
  catalogShow: boolean;
  pageFullScreen: boolean;

  constructor() {
    this.article = new Article();
    this.author = new Author();
    this.classify = new Classify();
    this.tagList = [new Tag()];
    this.commentParentList = [new CommentParent()];

    this.refresh = 0;
    this.catalogShow = false;
    this.editorName = "MyEditor";
    this.pageFullScreen = false;
  }
}
