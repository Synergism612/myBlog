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
                <el-row>
                  <el-col :span="18" class="info frame">
                    <el-col :span="24">
                      <el-row>
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
                              v-model="author.nickname"
                              placeholder="请输入昵称"
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
                              />
                            </div>
                            <div class="information sex">
                              性别：
                              <el-radio-group v-model="sex" class="ml-4">
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
                              />
                            </div>
                            <div class="information upToNow">
                              园龄：{{ author.upToNow }}
                            </div>
                          </el-col>
                          <el-col :span="24" class="information button">
                            <span class="click">提交修改</span>
                          </el-col>
                          <el-col :span="24" class="information button">
                            <span>我的文章</span>
                            <span>我的关注</span>
                            <span>我的粉丝</span>
                          </el-col>
                        </el-col>
                      </el-row>
                    </el-col>
                  </el-col>
                </el-row>
              </el-col>
            </transition>
          </el-row>
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Homepage from "./Homepage";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Homepage());

    onMounted(() => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
    };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url("./Homepage.less");
</style>
