import axios from "@/axios/axios";
import { store } from "@/store";
import AESUtil from "@/utils/AESUtil";
import RSAUtil from "@/utils/RSAUtil";
import StringUtil from "@/utils/StringUtil";
import { AxiosResponse } from "axios";
export class api {
  /**
   * 获取公钥接口
   * @returns Result[String]
   */
  public static async getPublicKey(): Promise<void> {
    //开启请求
    try {
      const { data } = await axios({
        url: "/api/public/key",
        method: "get",
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
   * 测试函数
   * @returns 空
   */
  public static getTest(): string {
    axios({
      url: "/api/public/test",
      method: "post",
      data: {
        username: "152",
        password: "123456",
      },
    })
      .then(({ data }) => {
        console.log(data);
      })
      .catch((err) => {
        console.log("err:\n" + err);
        return false;
      });
    return "";
  }

  /**
   * 登录接口
   * @param username 用户名
   * @param password 密码
   * @returns Result[String]
   */
  public static login(
    username: string,
    password: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/login",
      method: "post",
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
  public static getSecurityCode(
    mail: string,
    key: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/mail/register/code",
      method: "get",
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
  public static register(
    username: string,
    password: string,
    code: string,
    key: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/register",
      method: "post",
      data: {
        username: username,
        password: password,
        code: code,
        key: key,
      },
    });
  }

  /**
   * 登出接口
   * @param loginID 登录信息ID
   * @param nickname 昵称
   * @param username 账号
   * @returns Result[String]
   */
  public static logout(
    loginID: string,
    nickname: string,
    username: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/user/logout",
      method: "post",
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
   * @param username 用户名
   * @returns Result[分页信息]
   */
  public static getIndexArticle(
    currentPage: number,
    pageSize: number,
    articleSort: string,
    username: string
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/article/index",
      method: "get",
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
  public static getIndexUserInfo(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/userInfo",
      method: "get",
    });
  }

  /**
   * 首页标签接口
   * @param username 用户名
   * @returns Result[标签列表]
   */
  public static getIndexTag(username: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/tag",
      method: "get",
      params: { username: username || "" },
    });
  }

  /**
   * 首页分类接口
   * @param username 用户名
   * @returns Result[分类列表]
   */
  public static getIndexClassify(username: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/index/classify",
      method: "get",
      params: { username: username || "" },
    });
  }

  /**
   * 内容页评文章接口
   * @param articleID 文章id
   * @returns Result[文章内容]
   */
  public static getContentArticle(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/article",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页作者接口
   * @param articleID 文章id
   * @returns Result[作者信息]
   */
  public static getContentAuthor(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/author",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页分类接口
   * @param articleID 文章id
   * @returns Result[分类]
   */
  public static getContentClassify(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/classify",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页标签接口
   * @param articleID 文章id
   * @returns Result[标签列表]
   */
  public static getContentTagList(articleID: number): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/tagList",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页评论区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  public static getContentCommentList(
    articleID: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/commentList",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页分类推荐区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  public static getContentClassifyNominate(
    articleID: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/nominate/classify",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页标签推荐区接口
   * @param articleID 文章id
   * @returns Result[评论列表]
   */
  public static getContentTagNominate(
    articleID: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/nominate/tag",
      method: "get",
      params: { articleID: articleID },
    });
  }

  /**
   * 内容页新的评论接口
   * @param username 用户名
   * @param comment 评论内容
   * @param articleID 文章id
   * @param rootID 根评论id
   * @param parentID 父评论id
   * @returns Result[String]
   */
  public static setComment(
    username: string,
    comment: string,
    articleID: number,
    rootID: number,
    parentID: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/content/comment",
      method: "post",
      data: {
        username: username,
        comment: comment,
        articleID: articleID,
        rootID: rootID,
        parentID: parentID,
      },
    });
  }

  /**
   * 总览页获取分类接口
   * @returns Result[分类列表]
   */
  public static getPandectClissify(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pandect/classify",
      method: "get",
    });
  }

  /**
   * 总览页获取标签接口
   * @returns Result[标签列表]
   */
  public static getPandectTag(): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/pandect/tag",
      method: "get",
    });
  }

  public static getPandectArticle(
    currentPage: number,
    pageSize: number,
    articleSort: string,
    username: string,
    keyword: string,
    classifyIDList: Array<number>,
    tagIDList: Array<number>
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/article/search",
      method: "get",
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
   * @param username 账号
   * @returns 收藏夹列表
   */
  public static getEnshrineFavorite(username: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/favorite/favorite",
      method: "get",
      params: {
        username: username,
      },
    });
  }

  /**
   * 添加到收藏夹接口
   * @param title 标题
   * @param href 链接
   * @param annotation 注释
   * @param favoriteID 收藏夹id
   * @returns Result[String]
   */
  public static setEnshrineCollection(
    title: string,
    href: string,
    annotation: string,
    favoriteID: number
  ): Promise<AxiosResponse> {
    return axios({
      url: "/api/favorite/collection",
      method: "post",
      data: {
        title: title,
        href: href,
        annotation: annotation,
        favoriteID: favoriteID,
      },
    });
  }

  /**
   * 个人信息页作者接口
   * @param username 账号
   * @returns Result[作者信息]
   */
  public static getHomepageAuthor(username: string): Promise<AxiosResponse> {
    return axios({
      url: "/api/blog/homepage/author",
      method: "get",
      params: { username: username },
    });
  }
}
