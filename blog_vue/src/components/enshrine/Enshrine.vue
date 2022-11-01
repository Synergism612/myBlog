<template>
  <div class="favoriteBox">
    <div class="favorite frame">
      <el-row justify="space-around" align="middle">
        <div class="close">
          <span class="title">添加到收藏夹</span>
          <span @click="close" class="click">
            <font-awesome-icon :icon="['fas', 'times']" />
          </span>
        </div>
        <el-col :span="24">
          <el-form
            label-position="top"
            label-width="100px"
            :model="favoriteForm"
            style="max-width: 460px"
          >
            <el-form-item label="标题">
              <el-input v-model="favoriteForm.title" />
            </el-form-item>
            <el-form-item label="地址">
              <el-input v-model="favoriteForm.href" />
            </el-form-item>
            <el-form-item label="注释">
              <el-input v-model="favoriteForm.annotation" />
            </el-form-item>
            <el-form-item label="收藏夹" prop="region">
              <el-select v-model="favoriteForm.favoriteID" placeholder="请选择">
                <el-option label="请选择" :value="0" />
                <el-option
                  v-for="favorite in favoriteList"
                  :key="favorite.id"
                  :label="favorite.name"
                  :value="favorite.id"
                />
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="24" class="button">
          <span @click="add" class="click">添加</span>
          <span @click="close" class="click">取消</span>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script lang="ts">
import { api } from "@/api/api";
import Message from "@/utils/MessageUtil";
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Favorite from "./Enshrine";

export default defineComponent({
  emits: {
    close: null,
  },
  props: {
    title: {
      type: String,
      required: true,
    },
    href: {
      type: String,
      required: true,
    },
    annotation: {
      type: String,
      required: true,
    },
    username: {
      type: String,
      required: true,
    },
  },
  setup(props, { emit }) {
    /**数据仓 */
    const viewData = reactive(new Favorite());

    const close = (): void => {
      emit("close", true);
    };

    const add = (): void => {
      api
        .setEnshrineCollection(
          viewData.favoriteForm.title,
          viewData.favoriteForm.href,
          viewData.favoriteForm.annotation,
          viewData.favoriteForm.favoriteID === 0
            ? -1
            : viewData.favoriteForm.favoriteID
        )
        .then(() => {
          emit("close", true);
          Message.successMessage("成功添加");
        });
    };

    onMounted(() => {
      viewData.init(props.title, props.href, props.annotation, props.username);
    });
    return {
      ...toRefs(viewData),
      close,
      add,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url("./Enshrine.less");
</style>
