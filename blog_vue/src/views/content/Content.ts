import { api } from "src/api/api";
import Article from "src/model/article/Article";
import ArticleTagNominate from "src/model/article/ArticleTagNominate";
import Classify from "src/model/classify/Classify";
import Tag from "src/model/tag/Tag";
import Author from "src/model/user/Author";
import { store } from "src/store";

export default class Content {
  article: Article;
  author: Author;
  classify: Classify;
  tagList: Array<Tag>;

  refresh: number;
  editorName: string;
  catalogShow: boolean;
  pageFullScreen: boolean;

  classifyNominate: Array<Article>;
  tagNominate: Array<ArticleTagNominate>;

  toolBoxShow: boolean;

  collectionShow: boolean;

  isLogin: boolean;
  username: string;

  constructor() {
    this.article = new Article();
    this.author = new Author();
    this.classify = new Classify();
    this.tagList = [new Tag()];

    this.refresh = 0;
    this.catalogShow = false;
    this.editorName = "MyEditor";
    this.pageFullScreen = false;

    this.classifyNominate = [new Article()];
    this.tagNominate = [new ArticleTagNominate()];

    this.toolBoxShow = true;
    this.collectionShow = false;

    this.username = store.getters.getUsername;
    this.isLogin = store.getters.getIsLogin;
  }

  public init(articleID: number): void {
    const article = (): void => {
      api.getContentArticle(articleID).then(({ data }): void => {
        this.article = data;
      });
    };
    const author = (): void => {
      api.getContentAuthor(articleID).then(({ data }): void => {
        this.author = data;
      });
    };
    const classify = (): void => {
      api.getContentClassify(articleID).then(({ data }): void => {
        this.classify = data;
      });
    };
    const tagList = (): void => {
      api.getContentTagList(articleID).then(({ data }): void => {
        this.tagList = data;
      });
    };

    const classifyNominate = (): void => {
      api.getContentClassifyNominate(articleID).then(({ data }): void => {
        this.classifyNominate = data || [new Article()];
      });
    };

    const tagNominate = (): void => {
      api.getContentTagNominate(articleID).then(({ data }): void => {
        this.tagNominate = data || [new ArticleTagNominate()];
      });
    };

    article();
    author();
    classify();
    tagList();
    classifyNominate();
    tagNominate();
  }
}
