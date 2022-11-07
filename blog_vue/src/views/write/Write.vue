<template>
  <div class="writeBox">
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
                    <a href="/">创作</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <!-- 主要内容 -->
          <el-row justify="space-around">
            <!-- 主体框架 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="20" class="frame">
              <div class="from">
                <el-form label-position="top" :model="articleForm">
                  <el-form-item label="内容" v-show="false">
                    <el-input clearable v-model="articleForm.body" />
                  </el-form-item>

                  <el-row :gutter="20">
                    <el-col :span="6">
                      <el-form-item label="封面">
                        <el-upload
                          class="avatar-uploader"
                          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                          :show-file-list="false"
                          :on-success="handleAvatarSuccess"
                          :before-upload="beforeAvatarUpload"
                        >
                          <img
                            v-if="articleForm.icon"
                            :src="articleForm.icon"
                            class="avatar"
                          />
                          <font-awesome-icon :icon="['fas', 'plus']" />
                        </el-upload>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="标题">
                        <el-input
                          clearable
                          v-model="articleForm.title"
                          placeholder="请输入标题"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="分类">
                        <el-select
                          v-model="articleForm.classifyID"
                          placeholder="选择一个分类"
                          filterable
                        >
                          <el-option
                            v-for="element in classifyInformationList"
                            :key="element.id"
                            :label="element.name"
                            :value="element.id"
                          />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="标签">
                        <el-select
                          v-model="articleForm.tagIDList"
                          placeholder="选择至少一个标签"
                          multiple
                          filterable
                        >
                          <el-option
                            v-for="element in tagInformationList"
                            :key="element.id"
                            :label="element.name"
                            :value="element.id"
                          />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="隐私设置">
                        <el-radio-group v-model="articleForm.ifPrivate">
                          <el-radio :label="0">公开</el-radio>
                          <el-radio :label="1">私密</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>

                    <el-col :span="20">
                      <el-form-item label="摘要">
                        <el-input
                          v-model="articleForm.synopsis"
                          :rows="2"
                          type="textarea"
                          placeholder="为空时将会自动截取正文前20个字"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <div class="save">
                        <el-popconfirm
                          confirm-button-text="是"
                          cancel-button-text="否"
                          icon-color="#3fc4c2"
                          title="确定要保存吗？"
                          @confirm="save"
                        >
                          <template #reference>
                            <span class="click">保存</span>
                          </template>
                        </el-popconfirm>
                      </div>
                    </el-col>
                  </el-row>
                </el-form>
              </div>
              <div class="mdEditor">
                <MdEditor
                  v-model="articleForm.body"
                  :showCodeRowNumber="true"
                  :toolbars="toolbars"
                />
              </div>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Write from "./Write";
import Menu from "@/components/menu/Menu.vue";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { api } from "@/api/api";
import Message from "@/utils/MessageUtil";
import { UploadProps } from "element-plus";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Write());

    const router = useRouter();

    const save = (): void => {
      viewData.articleForm.username = viewData.username;
      api.saveWriteArticle(viewData.articleForm).then(({ data }): void => {
        data;
        Message.successMessage("提交成功");
        router.push({
          name: "Index",
        });
      });
    };

    const handleAvatarSuccess: UploadProps["onSuccess"] = (
      response,
      uploadFile
    ) => {
      viewData.articleForm.icon = URL.createObjectURL(uploadFile.raw!);
    };

    const beforeAvatarUpload: UploadProps["beforeUpload"] = (rawFile) => {
      if (rawFile.type !== "image/jpeg/png") {
        Message.errorMessage("只能上传图片类型");
        return false;
      } else if (rawFile.size / 1024 / 1024 > 5) {
        Message.errorMessage("请勿上传超过5MB的图片");
        return false;
      }
      return true;
    };

    onMounted(() => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      save,
      handleAvatarSuccess,
      beforeAvatarUpload,
    };
  },
  components: { Menu, MdEditor },
});
</script>
<style lang="less">
@import url("./Write.less");
</style>
