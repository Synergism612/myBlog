<template>
  <div>
    <el-dialog
      v-model="collectionShow"
      :show-close="false"
      class="collectionBox"
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
            :model="collectionForm"
            ref="fromRef"
            :rules="rules"
            :hide-required-asterisk="true"
          >
            <el-form-item label="标题" prop="title">
              <el-input v-model="collectionForm.title" />
            </el-form-item>
            <el-form-item label="链接" prop="href">
              <el-input v-model="collectionForm.href" />
            </el-form-item>
            <el-form-item label="摘要" prop="synopsis">
              <el-input
                v-model="collectionForm.synopsis"
                :rows="2"
                type="textarea"
                autosize
              />
            </el-form-item>
            <el-form-item label="收藏夹" prop="favorite">
              <el-select
                v-model="collectionForm.favoriteID"
                placeholder="请选择"
                filterable
              >
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
import { defineComponent, reactive, ref, toRefs, watchEffect } from "vue";
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
        props.favoriteID || 0
      );
    });

    const rules = {
      title: [
        {
          required: true,
          message: "标题不能为空",
          trigger: "blur",
        },
        { max: 10, message: "长度不能超过10", trigger: "blur" },
      ],
      href: [
        {
          required: true,
          message: "链接不能为空",
          trigger: "blur",
        },
        { max: 200, message: "长度不能超过200", trigger: "blur" },
      ],
      synopsis: [
        {
          required: true,
          message: "摘要不能为空",
          trigger: "blur",
        },
        { max: 100, message: "长度不能超过100", trigger: "blur" },
      ],
    };

    const close = (): void => {
      emit("close", false);
    };

    const fromRef = ref();

    const save = (): void => {
      if (viewData.collectionForm.favoriteID === 0) {
        viewData.collectionForm.favoriteID = -1;
      }
      fromRef.value
        .validate()
        .then((): void => {
          api.saveEnshrineCollection(viewData.collectionForm).then((): void => {
            emit("close", false);
            emit("succeed", true);
            Message.successMessage("成功添加");
          });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };
    return {
      ...toRefs(viewData),
      close,
      save,
      rules,
      fromRef,
    };
  },
  components: {},
});
</script>
