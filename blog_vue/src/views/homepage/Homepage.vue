<template>
  <div class="homepageBox">
    <Menu></Menu>
    <div class="container">
      <el-container>
        <el-header>
          <!-- 头部面包屑 -->
          <el-row justify="center">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="header frame">
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item :to="{ path: '/' }">
                    {{ "首页" }}
                  </el-breadcrumb-item>
                  <el-breadcrumb-item>
                    <a href="/">我的主页</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <!-- 主要内容 -->
          <el-row justify="space-around">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <!-- 主体框架 -->
              <el-col :xs="24" :sm="24" :md="24" :lg="20">
                <el-col :span="24" class="info frame">
                  <el-row class="top">
                    <el-col :span="12">
                      <el-col :span="24" class="icon">
                        <el-avatar :size="200" :src="icon" />
                        <span class="click" @click="uploadShow = true">
                          更改头像
                        </span>
                      </el-col>
                    </el-col>
                    <el-col :span="12">
                      <el-form
                        class="information"
                        label-position="top"
                        :model="userInformationForm"
                        :rules="userInformationFormRules"
                        :hide-required-asterisk="true"
                        ref="userInformationFormRef"
                      >
                        <el-form-item label="昵称" prop="nickname">
                          <el-input
                            v-model="userInformationForm.nickname"
                            placeholder="请输入昵称"
                            :disabled="changeShow"
                            clearable
                          />
                        </el-form-item>
                        <el-form-item label="账号" prop="username">
                          <el-input
                            v-model="userInformationForm.username"
                            :disabled="true"
                          />
                        </el-form-item>

                        <el-form-item label="生日" prop="birthday">
                          <el-date-picker
                            v-model="userInformationForm.birthday"
                            type="date"
                            placeholder="请选择"
                            :disabled="changeShow"
                            format="YYYY/MM/DD"
                            value-format="YYYY-MM-DD"
                          />
                        </el-form-item>
                        <el-form-item label="性别" prop="sex">
                          <el-radio-group
                            v-model="userInformationForm.sex"
                            :disabled="changeShow"
                          >
                            <el-radio :label="0">不愿透露</el-radio>
                            <el-radio :label="1">男</el-radio>
                            <el-radio :label="2">女</el-radio>
                          </el-radio-group>
                        </el-form-item>
                        <el-form-item label="个人简介" prop="intro">
                          <el-input
                            v-model="userInformationForm.intro"
                            :rows="2"
                            type="textarea"
                            autosize
                            placeholder="请输入简介"
                            :disabled="changeShow"
                          />
                        </el-form-item>
                      </el-form>
                      <div class="information upToNow">
                        园龄：{{ author.upToNow }}
                      </div>
                      <el-col :span="24" class="button">
                        <span
                          v-if="changeShow"
                          @click="changeShow = false"
                          class="click"
                        >
                          修改信息
                        </span>
                        <span
                          v-if="!changeShow"
                          @click="updateUserInformation"
                          class="click"
                          >提交修改</span
                        >
                        <span v-if="!changeShow" @click="close" class="click">
                          取消修改
                        </span>
                      </el-col>
                    </el-col>
                  </el-row>
                  <el-row class="bottom">
                    <el-col :span="24">
                      <el-tabs v-model="tabsName" class="tabs">
                        <el-tab-pane label="我的收藏" name="first">
                          <span
                            @click="saveFavoriteShow = true"
                            class="click rotate"
                          >
                            <font-awesome-icon :icon="['fas', 'plus']" />
                            新建收藏夹
                          </span>
                          <el-collapse
                            v-model="collapseName"
                            accordion
                            class="myFavorite"
                          >
                            <el-collapse-item
                              v-for="favoriteInformation in favoriteInformationList"
                              :key="favoriteInformation.id"
                              :name="favoriteInformation.name"
                            >
                              <template #title>
                                <span
                                  v-if="favoriteInformation.ifPrivate === 1"
                                >
                                  <font-awesome-icon :icon="['fas', 'lock']" />
                                  （私密）
                                </span>
                                <span v-if="favoriteInformation.ifPrivate != 1">
                                  <font-awesome-icon
                                    :icon="['fas', 'unlock']"
                                  />
                                  （公开）
                                </span>
                                <span class="name">
                                  {{ favoriteInformation.name }}
                                </span>
                                <span>
                                  {{ favoriteInformation.annotation }}
                                </span>
                              </template>
                              <div class="option">
                                <span
                                  @click="favoriteFormEdit(favoriteInformation)"
                                  class="click"
                                >
                                  <font-awesome-icon :icon="['fas', 'edit']" />
                                  编辑
                                </span>
                                <span
                                  @click="
                                    saveCollection(true, favoriteInformation.id)
                                  "
                                  class="click rotate"
                                >
                                  <font-awesome-icon :icon="['fas', 'plus']" />
                                  添加新的收藏
                                </span>
                                <span
                                  @click="
                                    deleteFavorite(favoriteInformation.id)
                                  "
                                  class="click"
                                >
                                  <font-awesome-icon :icon="['fas', 'trash']" />
                                  删除该收藏夹
                                </span>
                              </div>
                              <el-row>
                                <el-col
                                  :span="24"
                                  v-if="
                                    favoriteInformation.collectionList[0].id ===
                                    -1
                                  "
                                >
                                  收藏夹为空
                                </el-col>
                                <el-row
                                  :gutter="20"
                                  v-if="
                                    favoriteInformation.collectionList[0].id !=
                                    -1
                                  "
                                >
                                  <el-col
                                    :span="8"
                                    v-for="collection in favoriteInformation.collectionList"
                                    :key="collection.id"
                                    class="collection"
                                  >
                                    <div>
                                      <span
                                        @click="goCollection(collection.href)"
                                        class="click title"
                                      >
                                        {{ collection.title }}
                                      </span>
                                    </div>
                                    <div>
                                      摘要：
                                      <span>
                                        {{ collection.synopsis }}
                                      </span>
                                    </div>
                                    <div>
                                      <span
                                        @click="goCollection(collection.href)"
                                        class="click"
                                      >
                                        <font-awesome-icon
                                          :icon="['fas', 'external-link-alt']"
                                        />
                                        {{ collection.href }}
                                      </span>
                                    </div>
                                    <div>
                                      <span
                                        @click="
                                          delectCollection(
                                            favoriteInformation.id,
                                            [collection.id]
                                          )
                                        "
                                        class="click"
                                      >
                                        <font-awesome-icon
                                          :icon="['fas', 'minus']"
                                        />

                                        删除
                                      </span>
                                    </div>
                                  </el-col>
                                </el-row>
                              </el-row>
                            </el-collapse-item>
                          </el-collapse>
                        </el-tab-pane>
                        <el-tab-pane label="我的关注" name="second">
                          正在开发
                        </el-tab-pane>
                        <el-tab-pane label="我的粉丝" name="third">
                          正在开发
                        </el-tab-pane>
                      </el-tabs>
                    </el-col>
                  </el-row>
                </el-col>
              </el-col>
            </transition>
          </el-row>
        </el-main>
      </el-container>
    </div>
    <div>
      <Enshrine
        @close="saveCollection"
        @succeed="saveCollectionSucceed"
        v-model:show="saveCollectionShow"
        v-model:favoriteID="saveCollectionfavoriteID"
      ></Enshrine>
    </div>
    <div>
      <el-dialog
        v-model="saveFavoriteShow"
        :show-close="false"
        class="favoriteBox"
        destroy-on-close
        @close="favoriteFormClose"
      >
        <template #header>
          <div class="my-header">
            <div class="close">
              <span class="title">新建收藏夹</span>
              <span @click="saveFavoriteShow = false" class="click rotate">
                <font-awesome-icon :icon="['fas', 'times']" />
              </span>
            </div>
          </div>
        </template>
        <el-row justify="space-around" align="middle">
          <el-col :span="24">
            <el-form
              label-position="top"
              :model="favoriteForm"
              :rules="favoriteFormRules"
              :hide-required-asterisk="true"
              ref="favoriteFormRef"
            >
              <el-form-item label="名称" prop="name">
                <el-input clearable v-model="favoriteForm.name" />
              </el-form-item>
              <el-form-item label="备注" prop="annotation">
                <el-input clearable v-model="favoriteForm.annotation" />
              </el-form-item>
              <el-form-item label="隐私设置" prop="ifPrivate">
                <el-radio-group v-model="favoriteForm.ifPrivate">
                  <el-radio :label="0">公开</el-radio>
                  <el-radio :label="1">私密</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="24" class="button">
            <span @click="saveFavorite" class="click">提交</span>
            <span @click="saveFavoriteShow = false" class="click">取消</span>
          </el-col>
        </el-row>
      </el-dialog>
    </div>
    <Upload
      v-model:upload-show="uploadShow"
      @upload="upload"
      @close="uploadShow = false"
    />
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Menu from "src/components/menu/Menu.vue";
import Homepage from "./Homepage";
import Upload from "src/components/upload/Upload.vue";
import Enshrine from "src/components/enshrine/Enshrine.vue";
import Message from "src/utils/MessageUtil";
import { api } from "src/api/api";
import FavoriteInformation from "src/model/favorite/FavoriteInformation";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Homepage());

    const userInformationFormRules = {
      nickname: [
        {
          required: true,
          message: "昵称不能为空",
          trigger: "blur",
        },
        { max: 8, message: "长度不能超过8", trigger: "blur" },
      ],
      birthday: [
        {
          required: true,
          message: "生日不能为空",
          trigger: "blur",
        },
      ],
      sex: [
        {
          pattern: /^(0|[1-9\d?|2])/,
          message: "性别输入错误",
          trigger: "blur",
        },
      ],
      intro: [
        {
          required: true,
          message: "个人简介不能为空",
          trigger: "blur",
        },
        { max: 100, message: "长度不能超过100", trigger: "blur" },
      ],
    };

    const favoriteFormRules = {
      name: [
        {
          required: true,
          message: "名称不能为空",
          trigger: "blur",
        },
        { max: 10, message: "长度不能超过10", trigger: "blur" },
      ],
      annotation: [
        {
          required: true,
          message: "备注不能为空",
          trigger: "blur",
        },
        { max: 20, message: "长度不能超过20", trigger: "blur" },
      ],
    };

    const userInformationFormRef = ref();

    const updateUserInformation = (): void => {
      userInformationFormRef.value
        .validate()
        .then((): void => {
          api
            .updateHomepageUserInformation(viewData.userInformationForm)
            .then((): void => {
              viewData.changeShow = true;
              viewData.init();
              Message.successMessage("修改成功");
            });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const close = (): void => {
      viewData.changeShow = true;
      viewData.userInit();
      userInformationFormRef.value.validate();
    };

    const goCollection = (href: string): void => {
      window.open(href);
    };

    const saveCollection = (show: boolean, favoriteID: number): void => {
      viewData.saveCollectionShow = show;
      viewData.saveCollectionfavoriteID = favoriteID;
    };

    const saveCollectionSucceed = (): void => {
      viewData.updateMyFavorite();
    };

    const delectCollection = (
      favoriteID: number,
      collectionIDList: Array<number>
    ): void => {
      api
        .removeHomepageCollection(favoriteID, collectionIDList)
        .then((): void => {
          saveCollectionSucceed();
          Message.successMessage("删除成功");
        });
    };

    const favoriteFormRef = ref();

    const saveFavorite = (): void => {
      favoriteFormRef.value
        .validate()
        .then((): void => {
          if (viewData.isFavoriteFormEdit) {
            api.updateHomepageFavorite(viewData.favoriteForm).then((): void => {
              Message.successMessage("更新成功");
              viewData.saveFavoriteShow = false;
              viewData.init();
            });
          } else {
            api.saveHomepageFavorite(viewData.favoriteForm).then((): void => {
              Message.successMessage("添加成功");
              viewData.saveFavoriteShow = false;
              viewData.init();
            });
          }
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const favoriteFormClose = (): void => {
      viewData.favoriteFormInit();
    };

    const favoriteFormEdit = (
      favoriteInformation: FavoriteInformation
    ): void => {
      viewData.saveFavoriteShow = true;
      viewData.favoriteFormInit(favoriteInformation);
    };

    const deleteFavorite = (favoriteID: number): void => {
      api.removeHomepageFavorite(favoriteID).then((): void => {
        Message.successMessage("删除成功");
        viewData.init();
      });
    };
    const upload = (formData: FormData): void => {
      if (formData) {
        api.saveHomepageUserInformationIcon(formData).then(({ data }): void => {
          viewData.uploadShow = false;
          viewData.icon = data;
        });
      }
    };

    onMounted((): void => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      close,
      goCollection,
      saveCollection,
      saveCollectionSucceed,
      delectCollection,
      saveFavorite,
      favoriteFormClose,
      favoriteFormEdit,
      deleteFavorite,
      updateUserInformation,
      favoriteFormRules,
      userInformationFormRules,
      userInformationFormRef,
      favoriteFormRef,
      upload,
    };
  },
  components: { Menu, Enshrine, Upload },
});
</script>
<style lang="less">
@import url("./Homepage.less");
</style>
