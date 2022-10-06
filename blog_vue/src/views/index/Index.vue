<template>
  <div class="box">
    <div class="background">
      <Menu></Menu>
      <Screen></Screen>
    </div>
    <div class="container">
      <el-container>
        <el-header>
          <el-row justify="center">
            <el-col :sm="24" :md="16" class="notice frame">
              <p>公告栏</p>
              <p>公告内容</p>
            </el-col>
          </el-row>
        </el-header>

        <el-main>
          <el-row justify="space-around">
            <el-col :xs="24" :sm="24" :md="16" :span="16">
              <el-row gutter="20" justify="space-around">
                <el-col :xs="24" :sm="16" :md="16" :span="16">
                  <div class="header frame">
                    <el-row>
                      <el-col :span="24"> <p>头部</p></el-col>
                    </el-row>
                  </div>

                  <div class="articles">
                    <el-row class="article frame">
                      <el-row>
                        <el-col :span="24" class="title"> 标题</el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="preview"> 预览</el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="footer"> 底部</el-col>
                      </el-row>
                    </el-row>

                    <el-row class="article frame">
                      <el-row>
                        <el-col :span="24" class="title"> 标题</el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="preview"> 预览</el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24" class="footer"> 底部</el-col>
                      </el-row>
                    </el-row>
                  </div>

                  <div class="pagination">
                    <el-pagination
                      :page-sizes="[10, 50, 100, 200]"
                      layout="total,sizes, prev, pager, next, jumper"
                      :total="400"
                    />
                  </div>
                </el-col>
                <el-col :xs="0" :sm="8" :md="8" :span="8">
                  <div class="user frame">
                    <el-row>
                      <el-col :span="24">
                        <p>用户头像</p>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="24">
                        <p>用户信息</p>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="24">
                        <p>用户链接</p>
                      </el-col>
                    </el-row>
                  </div>

                  <div class="calender frame">
                    <el-calendar id="calender-body" v-model="calender" />
                  </div>

                  <div class="comments frame">
                    <el-row class="comment">
                      <el-col :span="4">头像</el-col>
                      <el-col :span="20">评论内容</el-col>
                    </el-row>

                    <el-row class="comment">
                      <el-col :span="4">头像</el-col>
                      <el-col :span="20">评论内容</el-col>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Screen from "@/components/screen/Screen.vue";
export default defineComponent({
  setup() {
    const viewData = reactive({
      calender: new Date(),
    });
    return { ...toRefs(viewData) };
  },
  components: { Menu, Screen },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>