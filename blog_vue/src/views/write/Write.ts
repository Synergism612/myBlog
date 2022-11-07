import { api } from "src/api/api";
import ArticleForm from "src/api/entity/ArticleForm";
import ClassifyInformation from "src/model/classify/ClassifyInformation";
import TagInformation from "src/model/tag/TagInformation";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";
import { ToolbarNames } from "md-editor-v3";

export default class Write {
  username: string;

  isLogin: boolean;

  articleForm: ArticleForm;

  toolbars: Array<ToolbarNames>;

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
