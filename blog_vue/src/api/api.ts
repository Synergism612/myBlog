import FavoriteForm from "src/api/entity/FavoriteForm";
import { ClassifyForm, TagForm } from "src/api/entity/ElementForm";
import axios, { JSONHeaders } from "src/axios/axios";
import { store } from "src/store";
import AESUtil from "src/utils/AESUtil";
import RSAUtil from "src/utils/RSAUtil";
import StringUtil from "src/utils/StringUtil";
import { AxiosResponse } from "axios";
import UserInformationForm from "./entity/UserInformationForm";
import CollectionForm from "./entity/CollectionForm";
import CommentForm from "./entity/CommentForm";
import RegisterForm from "./entity/RegisterForm";
import ArticleForm from "./entity/ArticleForm";
import FolderForm from "./entity/FolderForm";
export class api {
  /**
   * 获取公钥接口
   * @returns Result[String]
   */
  static async getPublicKey(): Promise<void> {
    //开启请求
    try {
      const { data } = await axios({
        url: "/api/public/key",
        method: "get",
        headers: JSONHeaders,
      });
      //更改公钥值
      store.commit("SET_PUBLIC_KEY", data);
      //生成钥匙或者直接获取本地钥匙
      let key;
      if (StringUtil.checkStringIfEmpty(store.state.KEY)) {
        key = AESUtil.getKey();
      } else {
        key = store.state.KEY;
      }
      store.commit("SET_KEY", key);
      store.commit(
        "SET_ANOTHER_WORLD_KEY",
        //公钥加密密钥
        RSAUtil.encryptedData(key, data)
      );
      console.log("ANOTHER_WORLD_KEY--" + store.state.ANOTHER_WORLD_KEY);
      console.log("PUBLIC_KEY--" + store.state.PUBLIC_KEY);
      console.log("KEY--" + store.state.KEY);
      console.log("EVIL_EYE--" + store.state.EVIL_EYE);
    } catch (err) {
      store.commit("DELECT_ALL_KEY");
      console.log("err:\n" + err);
    }
  }

  /**
   * 登录接口
   * @param username 用户名
   * @param password 密码
   * @returns Result[String]
   */
  static login(username: string, password: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/login",
      method: "post",
      headers: JSONHeaders,
      data: {
        username: username,
        password: password,
      },
    });
  }

  /**
   * 获取邮箱验证码接口
   * @param mail 邮箱
   * @returns Result[String]
   */
  static getSecurityCode(mail: string, key: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/mail/register/code",
      method: "get",
      headers: JSONHeaders,
      params: {
        mail: mail,
        key: key,
      },
    });
  }

  /**
   * 注册接口
   * @param username 账号
   * @param password 密码
   * @param code 验证码
   * @param key 验证码密钥
   * @returns Result[String]
   */
  static register(registerForm: RegisterForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/register",
      method: "post",
      headers: JSONHeaders,
      data: registerForm,
    });
  }

  /**
   * 登出接口
   * @param loginID 登录信息ID
   * @param nickname 昵称
   * @param username 账号
   * @returns Result[String]
   */
  static logout(
    loginID: string,
    nickname: string,
    username: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/logout",
      method: "post",
      headers: JSONHeaders,
      data: {
        loginID: loginID,
        nickname: nickname,
        username: username,
      },
    });
  }

  /**
   * 首页文章分页接口
   * @param currentPage 第几页
   * @param pageSize 一页几条
   * @param articleSort 排序
   * @param username 用户名 可为空
   * @returns Result[分页信息]
   */
  static getIndexArticle(
    currentPage: number,
    pageSize: number,
    articleSort: string,
    username: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/article",
      method: "get",
      headers: JSONHeaders,
      params: {
        currentPage: currentPage,
        pageSize: pageSize,
        articleSort: articleSort,
        username: username || "",
      },
    });
  }

  /**
   * 首页用户信息接口
   * @returns Result[用户信息]
   */
  static getIndexAuthor(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/author",
      method: "get",
      params: {
        username: store.getters.getUsername,
      },
    });
  }

  /**
   * 首页标签接口
   * @returns Result[标签列表]
   */
  static getIndexTag(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/tag",
      method: "get",
    });
  }

  /**
   * 首页分类接口
   * @returns Result[分类列表]
   */
  static getIndexClassify(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/classify",
      method: "get",
    });
  }

  /**
   * 首页删除文章接口
   * @param username 账号
   * @param articleIDList 文章id列表
   */
  static removeIndexArticle(
    articleIDList: Array<number>
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/article",
      method: "delete",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        articleIDList: articleIDList.toString(),
      },
    });
  }

  /**
   * 内容页评文章接口
   * @param articleID 文章id
   * @returns Result[文章内容]
   */
  static getContentArticle(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/article",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页文章是否点赞接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  static getContentArticleLike(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/article/like",
      method: "get",
      headers: JSONHeaders,
      params: { username: store.getters.getUsername, articleID: articleID },
    });
  }

  /**
   * 内容页文章点赞接口
   * @param articleID 文章id
   * @param state 状态
   * @returns Result[评论列表]
   */
  static updateContentArticleLike(
    articleID: number,
    state: boolean
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/article/like",
      method: "patch",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        articleID: articleID,
        state: state.toString(),
      },
    });
  }

  /**
   * 内容页作者接口
   * @param articleID 文章id
   * @returns Result[作者信息]
   */
  static getContentAuthor(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/author",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页分类接口
   * @param articleID 文章id
   * @returns Result[分类]
   */
  static getContentClassify(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/classify",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页标签接口
   * @param articleID 文章id
   * @returns Result[标签列表]
   */
  static getContentTagList(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/tag",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页评论区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  static getContentCommentList(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/comment",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页文章点过赞的文章
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  static getContentCommentLike(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/comment/like",
      method: "get",
      headers: JSONHeaders,
      params: { username: store.getters.getUsername },
    });
  }

  /**
   * 内容页文章点赞接口
   * @param commentID 文章id
   * @param state 状态
   * @returns Result[评论列表]
   */
  static updateContentCommentLike(
    commentID: number,
    state: boolean
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/comment/like",
      method: "patch",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        commentID: commentID,
        state: state.toString(),
      },
    });
  }

  /**
   * 内容页分类推荐区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  static getContentClassifyNominate(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/nominate/classify",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页标签推荐区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  static getContentTagNominate(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/nominate/tag",
      method: "get",
      headers: JSONHeaders,
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页新的评论接口
   * @param commentForm 评论信息表单
   * @returns Result[String]
   */
  static saveContentComment(commentForm: CommentForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/comment",
      method: "post",
      headers: JSONHeaders,
      data: commentForm,
    });
  }

  /**
   * 总览页获取分类接口
   * @param username 账号 可为空
   * @returns Result[分类列表]
   */
  static getPandectClissify(username?: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pandect/classify",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: username || "",
      },
    });
  }

  /**
   * 总览页获取标签接口
   * @param username 账号 可为空
   * @returns Result[标签列表]
   */
  static getPandectTag(username?: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pandect/tag",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: username || "",
      },
    });
  }

  /**
   * 总览页面获取文章接口
   * @param currentPage 页数
   * @param pageSize 页容
   * @param articleSort 排序字段
   * @param username 账号 可为空
   * @param keyword 查询关键字
   * @param classifyIDList 分类id列表
   * @param tagIDList 标签id列表
   * @returns 分页信息
   */
  static getPandectArticle(
    currentPage: number,
    pageSize: number,
    articleSort: string,
    username: string,
    keyword: string,
    classifyIDList: Array<number>,
    tagIDList: Array<number>
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pandect/article",
      method: "get",
      headers: JSONHeaders,
      params: {
        currentPage: currentPage,
        pageSize: pageSize,
        articleSort: articleSort,
        username: username || "",
        keyword: keyword || "",
        classifyIDList: classifyIDList.toString(),
        tagIDList: tagIDList.toString(),
      },
    });
  }

  /**
   * 收藏获取收藏夹接口
   * @returns 收藏夹列表
   */
  static getEnshrineFavorite(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/enshrine/favorite",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
      },
    });
  }

  /**
   * 添加到收藏夹接口
   * @param title 标题
   * @param href 链接
   * @param synopsis 注释
   * @param favoriteID 收藏夹id
   * @returns Result[String]
   */
  static saveEnshrineCollection(
    collectionForm: CollectionForm
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/enshrine/collection",
      method: "post",
      headers: JSONHeaders,
      data: collectionForm,
    });
  }

  /**
   * 个人信息页作者接口
   * @returns Result[作者信息]
   */
  static getHomepageAuthor(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/author",
      method: "get",
      headers: JSONHeaders,
      params: { username: store.getters.getUsername },
    });
  }

  /**
   * 个人信息页我的收藏接口
   * @returns 我的收藏列表
   */
  static getHomepageFavoriteInformationList(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/favorite",
      method: "get",
      headers: JSONHeaders,
      params: { username: store.getters.getUsername },
    });
  }

  /**
   * 个人信息页删除收藏接口
   * @param favoriteID 收藏夹id
   * @param collectionIDList 收藏id列表
   * @returns 成功
   */
  static removeHomepageCollection(
    favoriteID: number,
    collectionIDList: Array<number>
  ): Promise<AxiosResponse> {
    console.log(collectionIDList);
    return axios({
      url: "/api/blog/homepage/collection",
      method: "delete",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        favoriteID: favoriteID,
        collectionIDList: collectionIDList.toString(),
      },
    });
  }

  /**
   * 个人信息页保存收藏夹接口
   * @param favoriteForm 收藏夹信息表单
   * @returns 成功
   */
  static saveHomepageFavorite(
    favoriteForm: FavoriteForm
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/favorite",
      method: "post",
      headers: JSONHeaders,
      data: favoriteForm,
    });
  }

  /**
   * 个人信息页更新收藏夹接口
   * @param favoriteForm 收藏夹信息表单
   * @returns 成功
   */
  static updateHomepageFavorite(
    favoriteForm: FavoriteForm
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/favorite",
      method: "put",
      headers: JSONHeaders,
      data: favoriteForm,
    });
  }

  /**
   * 个人信息页删除收藏夹接口
   * @param favoriteID 收藏夹id
   * @returns 成功
   */
  static removeHomepageFavorite(favoriteID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/favorite",
      method: "delete",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        favoriteID: favoriteID,
      },
    });
  }

  /**
   * 个人信息页更新用户信息接口
   * @param userInformationForm 用户信息表单
   * @returns 成功
   */
  static updateHomepageUserInformation(
    userInformationForm: UserInformationForm
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/user",
      method: "put",
      headers: JSONHeaders,
      data: userInformationForm,
    });
  }

  /**
   * 个人页面保存头像接口
   * @param formData 文件信息
   * @returns 成功
   */
  static saveHomepageUserInformationIcon(
    formData: FormData
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/user/icon",
      method: "post",
      params: { username: store.getters.getUsername },
      data: formData,
    });
  }

  /**
   * 创作页面用户分类获取接口
   * @returns 分类列表
   */
  static getWriteClassify(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/classify",
      method: "get",
    });
  }
  /**
   * 创作页面用户标签获取接口
   * @returns 标签列表
   */
  static getWriteTag(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/tag",
      method: "get",
    });
  }

  /**
   * 创作页面文章获取接口
   * @param articleID 文章id
   * @returns 文章信息
   */
  static getWriteArticle(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/article",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        articleID: articleID,
      },
    });
  }

  /**
   * 创作页面保存文章接口
   * @param articleForm 文章信息表单
   * @returns 成功
   */
  static saveWriteArticle(articleForm: ArticleForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/article",
      method: "post",
      headers: JSONHeaders,
      data: articleForm,
    });
  }

  /**
   * 创作页面更新文章接口
   * @param articleForm 文章信息表单
   * @returns 成功
   */
  static updateWriteArticle(articleForm: ArticleForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/article",
      method: "put",
      headers: JSONHeaders,
      data: articleForm,
    });
  }

  /**
   * 创作页面保存分类接口
   * @param classifyForm 分类信息表单
   * @returns 成功
   */
  static saveWriteClassify(classifyForm: ClassifyForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/classify",
      method: "post",
      headers: JSONHeaders,
      data: classifyForm,
    });
  }

  /**
   * 创作页面保存标签接口
   * @param tagForm 标签信息表单
   * @returns 成功
   */
  static saveWriteTag(tagForm: TagForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/tag",
      method: "post",
      headers: JSONHeaders,
      data: tagForm,
    });
  }

  /**
   * 创作页面保存封面接口
   * @param formData 文件信息
   * @returns 成功
   */
  static saveWriteArticleIcon(formData: FormData): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/article/icon",
      method: "post",
      params: { username: store.getters.getUsername },
      data: formData,
    });
  }

  /**
   * 创作页面保存封面接口
   * @param formData 文件信息
   * @returns 成功
   */
  static saveWriteArticleImg(formData: FormData): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/write/article/img",
      method: "post",
      params: { username: store.getters.getUsername },
      data: formData,
    });
  }

  /**
   * 归档页面获取档案信息接口
   * @param username 账号 可为空
   * @returns 档案信息
   */
  static getPifeonholeArchive(username: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pigeonhole/archive",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: username || "",
      },
    });
  }

  /**
   * 资源页面获取仓库信息接口
   * @returns 档案信息
   */
  static getDBankRepository(): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/repository",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
      },
    });
  }

  /**
   * 资源页面获取文件夹信息接口
   * @returns 档案信息
   */
  static getDBankFolder(path: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/folder",
      method: "get",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        path: path,
      },
    });
  }

  /**
   * 资源页面保存文件接口
   * @param formData 文件信息
   * @returns 成功
   */
  static saveDBankFile(
    formData: FormData,
    path: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/file",
      method: "post",
      params: { username: store.getters.getUsername, path: path },
      data: formData,
    });
  }

  /**
   * 资源页面获取文件夹信息接口
   * @returns 档案信息
   */
  static removeDBankFolder(folderID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/folder",
      method: "delete",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        folderID: folderID,
      },
    });
  }

  /**
   * 资源页面获取文件夹信息接口
   * @returns 档案信息
   */
  static removeDBankFile(fileID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/file",
      method: "delete",
      headers: JSONHeaders,
      params: {
        username: store.getters.getUsername,
        fileID: fileID,
      },
    });
  }

  /**
   * 资源页面获取文件夹信息接口
   * @returns 档案信息
   */
  static saveDBankFolder(folderForm: FolderForm): Promise<AxiosResponse> {
    return axios({
      url: "/api/DBank/folder",
      method: "post",
      headers: JSONHeaders,
      data: folderForm,
    });
  }
}
