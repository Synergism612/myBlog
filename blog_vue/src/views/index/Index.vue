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
            <el-col :sm="24" :md="20" class="notice frame">
              <p>公告栏</p>
              <p>公告内容</p>
            </el-col>
          </el-row>
        </el-header>

        <el-main>
          <el-row justify="space-around">
            <el-col :xs="24" :sm="24" :md="20" :span="20">
              <el-row gutter="20" justify="space-between">
                <el-col :xs="24" :sm="16" :md="16" :span="16">
                  <div class="frame">
                    <p>文章列表</p>
                  </div>
                </el-col>
                <el-col :xs="0" :sm="8" :md="8" :span="8">
                  <div class="frame">
                    <p>右侧</p>
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
    const viewData = reactive({});
    return { ...toRefs(viewData) };
  },
  components: { Menu, Screen },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>