import { api } from "src/api/api";
import ArticleForm from "src/api/entity/ArticleForm";
import Classify from "src/model/classify/Classify";
import Tag from "src/model/tag/Tag";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";
import { ToolbarNames } from "md-editor-v3";
import ArticleInformation from "src/model/article/ArticleInformation";

export default class Write {
  username: string;

  isLogin: boolean;

  articleForm: ArticleForm;

  toolbars: Array<ToolbarNames>;

  articleInformation: ArticleInformation;

  /**分类列表 */
  classifyInformationList: Array<Classify>;
  /**标签列表*/
  tagInformationList: Array<Tag>;

  constructor() {
    this.username = store.getters.getUser.username;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.username);
    this.articleForm = new ArticleForm();
    this.toolbars = toolbars as Array<ToolbarNames>;
    this.classifyInformationList = [new Classify()];
    this.tagInformationList = [new Tag()];
    this.articleInformation = new ArticleInformation();
  }

  public init(): void {
    const classify = () => {
      api.getWriteClassify().then(({ data }) => {
        this.classifyInformationList = data;
      });
    };
    const tag = () => {
      api.getWriteTag().then(({ data }) => {
        this.tagInformationList = data;
      });
    };
    classify();
    tag();
  }

  public articleFormInit(articleID: number): void {
    api.getWriteArticle(articleID).then(({ data }): void => {
      this.articleInformation = data;
      this.articleForm.id = this.articleInformation.id;
      this.articleForm.icon = this.articleInformation.icon;
      this.articleForm.title = this.articleInformation.title;
      this.articleForm.body = this.articleInformation.body;
      this.articleForm.synopsis = this.articleInformation.synopsis;
      this.articleForm.ifPrivate = this.articleInformation.ifPrivate;
      this.articleForm.classifyID = this.articleInformation.classify.id;
      this.articleForm.tagIDList = this.articleInformation.tagList.map(
        (tag: Tag) => {
          return tag.id;
        }
      );
    });
  }
}

export const toolbars = [
  "bold",
  "underline",
  "italic",
  "strikeThrough",
  "-",
  "title",
  "sub",
  "sup",
  "quote",
  "unorderedList",
  "orderedList",
  "task",
  "-",
  "codeRow",
  "code",
  "link",
  "image",
  "table",
  "mermaid",
  "katex",
  0,
  1,
  2,
  "-",
  "revoke",
  "next",
  "=",
  "prettier",
  "pageFullscreen",
  "fullscreen",
  "preview",
  "htmlPreview",
  "catalog",
];
