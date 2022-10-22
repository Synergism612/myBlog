import Classify from "./Classify";

export default class ClassifyInformation extends Classify {
  /**分类下文章数*/
  articleCount!: number;
  /**所有者昵称*/
  nickname!: string;
  /**所有者账号*/
  username!: string;
}
