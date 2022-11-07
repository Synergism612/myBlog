<template>
  <div>
    <el-dialog
      v-model="collectionShow"
      :show-close="false"
      custom-class="collectionBox"
      destroy-on-close
      @close="close"
    >
      <template #header>
        <div class="my-header">
          <div class="close">
            <span class="title">添加到收藏夹</span>
            <span @click="close" class="click rotate">
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
            :model="collectionForm"
            style="max-width: 460px"
          >
            <el-form-item label="标题">
              <el-input v-model="collectionForm.title" />
            </el-form-item>
            <el-form-item label="地址">
              <el-input v-model="collectionForm.href" />
            </el-form-item>
            <el-form-item label="摘要">
              <el-input
                v-model="collectionForm.synopsis"
                :rows="2"
                type="textarea"
              />
            </el-form-item>
            <el-form-item label="收藏夹" prop="region">
              <el-select
                v-model="collectionForm.favoriteID"
                placeholder="请选择"
                filterable
              >
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
          <span @click="save" class="click">添加</span>
          <span @click="close" class="click">取消</span>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import { api } from "src/api/api";
import Message from "src/utils/MessageUtil";
import { defineComponent, reactive, toRefs, watchEffect } from "vue";
import Enshrine from "./Enshrine";

export default defineComponent({
  emits: {
    close: null,
    succeed: null,
  },
  props: {
    title: {
      type: String,
      required: false,
    },
    href: {
      type: String,
      required: false,
    },
    synopsis: {
      type: String,
      required: false,
    },
    favoriteID: {
      type: Number,
      required: false,
    },
    username: {
      type: String,
      required: true,
    },
    show: {
      type: Boolean,
      required: true,
    },
  },
  setup(props, { emit }) {
    /**数据仓 */
    const viewData = reactive(new Enshrine());

    watchEffect((): void => {
      viewData.collectionForm.favoriteID = props.favoriteID || 0;
      viewData.collectionShow = props.show;

      viewData.init(
        props.title || "",
        props.href || "",
        props.synopsis || "",
        props.favoriteID || 0,
        props.username
      );
    });

    const close = (): void => {
      emit("close", false);
    };

    const save = (): void => {
      if (viewData.collectionForm.favoriteID === 0) {
        viewData.collectionForm.favoriteID = -1;
      }
      api.saveEnshrineCollection(viewData.collectionForm).then(() => {
        emit("close", false);
        emit("succeed", true);
        Message.successMessage("成功添加");
      });
    };
    return {
      ...toRefs(viewData),
      close,
      save,
    };
  },
  components: {},
});
</script>