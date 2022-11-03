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
                          <span class="click rotate">
                            <font-awesome-icon :icon="['fas', 'plus']" />
                            新建收藏夹
                          </span>
                          <el-collapse
                            v-model="collapseName"
                            accordion
                            class="myFavorite"
                          >
                            <el-collapse-item
                              v-for="myFavorite in myFavoriteList"
                              :key="myFavorite.id"
                              :name="myFavorite.id"
                            >
                              <template #title>
                                <span v-if="myFavorite.ifPrivate === 1">
                                  <font-awesome-icon :icon="['fas', 'lock']" />
                                  （私密）
                                </span>
                                <span v-if="myFavorite.ifPrivate != 1">
                                  <font-awesome-icon
                                    :icon="['fas', 'unlock']"
                                  />
                                  （公开）
                                </span>
                                <span>
                                  {{ myFavorite.name }}
                                </span>
                              </template>
                              <div class="option">
                                <span
                                  class="click"
                                  v-if="myFavorite.ifPrivate != 1"
                                >
                                  <font-awesome-icon :icon="['fas', 'lock']" />
                                  设为私密
                                </span>
                                <span
                                  class="click"
                                  v-if="myFavorite.ifPrivate === 1"
                                >
                                  <font-awesome-icon
                                    :icon="['fas', 'unlock']"
                                  />
                                  设为公开
                                </span>
                                <span
                                  @click="addFavorite(true, myFavorite.id)"
                                  class="click rotate"
                                >
                                  <font-awesome-icon :icon="['fas', 'plus']" />
                                  添加新的收藏
                                </span>
                                <span class="click">
                                  <font-awesome-icon :icon="['fas', 'trash']" />
                                  删除该收藏夹
                                </span>
                              </div>
                              <el-row :gutter="20">
                                <div
                                  v-if="myFavorite.collectionList[0].id === -1"
                                >
                                  收藏夹为空
                                </div>
                                <el-col
                                  :span="8"
                                  v-for="collection in myFavorite.collectionList"
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
                                    链接：
                                    <span
                                      @click="goCollection(collection.href)"
                                      class="click"
                                    >
                                      {{ collection.href }}
                                    </span>
                                  </div>
                                  <div>
                                    <span
                                      @click="
                                        delectCollection(myFavorite.id, [
                                          collection.id,
                                        ])
                                      "
                                      class="click"
                                    >
                                      删除
                                    </span>
                                  </div>
                                </el-col>
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
        @close="addFavorite"
        @succeed="addFavoriteSucceed"
        v-model:show="addFavoriteShow"
        v-model:favoriteID="addFavoriteID"
        :username="username"
      ></Enshrine>
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

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Homepage());

    const close = (): void => {
      viewData.changeShow = true;
      viewData.fromInit();
    };

    const goCollection = (href: string): void => {
      window.open(href);
    };

    const addFavorite = (show: boolean, addFavoriteID: number): void => {
      viewData.addFavoriteShow = show;
      viewData.addFavoriteID = addFavoriteID;
    };

    const addFavoriteSucceed = () => {
      viewData.updateMyFavorite();
    };

    const delectCollection = (
      favoriteID: number,
      collectionIDList: Array<number>
    ) => {
      console.log(collectionIDList);

      api.delectCollection(favoriteID, collectionIDList).then(() => {
        addFavoriteSucceed();
        Message.successMessage("删除成功");
      });
    };

    onMounted(() => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      close,
      goCollection,
      addFavorite,
      addFavoriteSucceed,
      delectCollection,
    };
  },
  components: { Menu, Enshrine },
});
</script>
<style lang="less">
@import url("./Homepage.less");
</style>
