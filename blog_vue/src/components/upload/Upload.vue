<template>
  <div>
    <el-dialog class="uploadBox" v-model="show" title="上传">
      <el-upload
        class="upload-demo"
        drag
        :action="config.action"
        :headers="config.headers"
        :multiple="config.multiple"
        :limit="config.limit"
        :on-exceed="exceed"
        :before-upload="beforeAvatarUpload"
        :auto-upload="false"
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">拖入文件或 <em>点击上传</em></div>
        <template #tip>
          <div class="el-upload__tip">jpg/png 格式的文件</div>
        </template>
      </el-upload>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import Upload, { Config } from "./Upload";
import { defineComponent, reactive, toRefs, watchEffect } from "vue";
import Message from "src/utils/MessageUtil";
import { UploadProps } from "element-plus";

export default defineComponent({
  props: {
    uploadShow: {
      type: Boolean,
      required: true,
    },
    uploadConfig: {
      type: Config,
      required: false,
    },
  },
  setup(props) {
    const viewData = reactive(new Upload());

    watchEffect((): void => {
      viewData.show = props.uploadShow;
      if (props.uploadConfig) {
        viewData.config = props.uploadConfig;
      }
    });

    const exceed = () => {
      Message.warningMessage("超过文件数上限");
    };

    const beforeAvatarUpload: UploadProps["beforeUpload"] = (rawFile) => {
      console.log(rawFile.type);

      if (rawFile.type !== viewData.config.type) {
        Message.warningMessage("文件类型错误");
        return false;
      } else if (rawFile.size / 1024 / 1024 > viewData.config.size) {
        Message.warningMessage("文件大小超过" + viewData.config.size + "MB!");
        return false;
      }
      return true;
    };

    return { ...toRefs(viewData), exceed, beforeAvatarUpload };
  },
});
</script>
