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
                        <div>更改头像</div>
                      </el-col>
                    </el-col>
                    <el-col :span="12">
                      <el-col :span="18" class="information nickname">
                        <span>昵称：</span>
                        <el-input
                          v-model="nickname"
                          placeholder="请输入昵称"
                          :disabled="changeShow"
                          clearable
                        />
                        <div class="information username">
                          账号：
                          {{ username }}
                        </div>
                        <div class="information birthday">
                          生日：
                          <el-date-picker
                            v-model="birthday"
                            type="date"
                            placeholder="请选择"
                            :disabled="changeShow"
                          />
                        </div>
                        <div class="information sex">
                          性别：
                          <el-radio-group v-model="sex" :disabled="changeShow">
                            <el-radio :label="0">不愿透露</el-radio>
                            <el-radio :label="1">男</el-radio>
                            <el-radio :label="2">女</el-radio>
                          </el-radio-group>
                        </div>
                        <div class="information intro">
                          个人简介：
                          <el-input
                            v-model="intro"
                            :rows="2"
                            type="textarea"
                            placeholder="请输入简介"
                            :disabled="changeShow"
                          />
                        </div>
                        <div class="information upToNow">
                          园龄：{{ author.upToNow }}
                        </div>
                      </el-col>
                      <el-col :span="24" class="information button">
                        <span @click="changeShow = false" class="click">
                          修改信息
                        </span>
                        <span v-if="!changeShow" class="click">提交修改</span>
                        <span v-if="!changeShow" @click="close" class="click">
                          取消修改
                        </span>
                      </el-col>
                    </el-col>
                  </el-row>
                  <el-row class="bottom">
                    <el-col :span="24">
                      <el-tabs v-model="tabsName" class="demo-tabs">
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
                              :name="favoriteInformation.id"
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
                                <span>
                                  {{ favoriteInformation.name }}
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
                                        class="click"
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
                          Config
                        </el-tab-pane>
                        <el-tab-pane label="我的粉丝" name="third">
                          Config
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
        :username="username"
      ></Enshrine>
    </div>
    <div>
      <el-dialog
        v-model="saveFavoriteShow"
        :show-close="false"
        custom-class="favoriteBox"
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
              label-width="100px"
              :model="favoriteForm"
              style="max-width: 460px"
            >
              <el-form-item label="名称">
                <el-input clearable v-model="favoriteForm.name" />
              </el-form-item>
              <el-form-item label="注释">
                <el-input clearable v-model="favoriteForm.annotation" />
              </el-form-item>
              <el-form-item label="">
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
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Homepage from "./Homepage";
import Enshrine from "@/components/enshrine/Enshrine.vue";
import Message from "@/utils/MessageUtil";
import { api } from "@/api/api";
import FavoriteInformation from "@/model/favorite/FavoriteInformation";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Homepage());

    const close = (): void => {
      viewData.changeShow = true;
      viewData.userInit();
    };

    const goCollection = (href: string): void => {
      window.open(href);
    };

    const saveCollection = (show: boolean, favoriteID: number): void => {
      viewData.saveCollectionShow = show;
      viewData.saveCollectionfavoriteID = favoriteID;
    };

    const saveCollectionSucceed = () => {
      viewData.updateMyFavorite();
    };

    const delectCollection = (
      favoriteID: number,
      collectionIDList: Array<number>
    ) => {
      api.delectCollection(favoriteID, collectionIDList).then(() => {
        saveCollectionSucceed();
        Message.successMessage("删除成功");
      });
    };

    const saveFavorite = () => {
      viewData.favoriteForm.username = viewData.username;
      if (viewData.isFavoriteFormEdit) {
        api.updateFavorite(viewData.favoriteForm).then(() => {
          Message.successMessage("更新成功");

          viewData.saveFavoriteShow = false;
          viewData.init();
        });
      } else {
        api.saveFavorite(viewData.favoriteForm).then(() => {
          Message.successMessage("添加成功");

          viewData.saveFavoriteShow = false;
          viewData.init();
        });
      }
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

    const deleteFavorite = (favoriteID: number) => {
      console.log(favoriteID);
      
    };

    onMounted(() => {
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
    };
  },
  components: { Menu, Enshrine },
});
</script>
<style lang="less">
@import url("./Homepage.less");
</style>
