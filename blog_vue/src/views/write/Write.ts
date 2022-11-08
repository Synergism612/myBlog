import { api } from "src/api/api";
import ArticleForm from "src/api/entity/ArticleForm";
import ClassifyInformation from "src/model/classify/ClassifyInformation";
import TagInformation from "src/model/tag/TagInformation";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";
import { ToolbarNames } from "md-editor-v3";
import ArticleInformation from "src/model/article/ArticleInformation";
import Tag from "src/model/tag/Tag";

export default class Write {
  username: string;

  isLogin: boolean;

  articleForm: ArticleForm;

  toolbars: Array<ToolbarNames>;

  articleInformation: ArticleInformation;

  /**分类列表 */
  classifyInformationList: Array<ClassifyInformation>;
  /**标签列表*/
  tagInformationList: Array<TagInformation>;

  constructor() {
    this.username = store.getters.getUser.username;
    this.isLogin = !StringUtil.checkStringIfEmpty(this.username);
    this.articleForm = new ArticleForm();
    this.toolbars = toolbars as Array<ToolbarNames>;
    this.classifyInformationList = [new ClassifyInformation()];
    this.tagInformationList = [new TagInformation()];
    this.articleInformation = new ArticleInformation();
  }

  public init(): void {
    const classify = () => {
      api.getWriteClassify(this.username).then(({ data }) => {
        this.classifyInformationList = data;
      });
    };
    const tag = () => {
      api.getWriteTag(this.username).then(({ data }) => {
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
