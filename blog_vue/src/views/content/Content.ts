import { api } from "@/api/api";
import Article from "@/model/article/Article";
import ArticleTagNominate from "@/model/article/ArticleTagNominate";
import Classify from "@/model/classify/Classify";
import Tag from "@/model/tag/Tag";
import Author from "@/model/user/Author";
import { store } from "@/store";
import StringUtil from "@/utils/StringUtil";

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

    this.username = store.getters.getUser.username;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.username);
  }

  public init(articleID: number): void {
    const article = (): void => {
      api.getContentArticle(articleID).then(({ data }) => {
        this.article = data;
      });
    };
    const author = (): void => {
      api.getContentAuthor(articleID).then(({ data }) => {
        this.author = data;
      });
    };
    const classify = (): void => {
      api.getContentClassify(articleID).then(({ data }) => {
        this.classify = data;
      });
    };
    const tagList = (): void => {
      api.getContentTagList(articleID).then(({ data }) => {
        this.tagList = data;
      });
    };

    const classifyNominate = (): void => {
      api.getContentClassifyNominate(articleID).then(({ data }) => {
        this.classifyNominate = data || [new Article()];
      });
    };

    const tagNominate = (): void => {
      api.getContentTagNominate(articleID).then(({ data }) => {
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
