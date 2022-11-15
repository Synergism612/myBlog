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
                <el-form
                  label-position="top"
                  :model="articleForm"
                  :rules="rules"
                  ref="formRef"
                  :hide-required-asterisk="true"
                >
                  <el-row :gutter="20">
                    <el-col :span="6">
                      <el-form-item label="封面" prop="icon">
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
                      <el-form-item label="标题" prop="title">
                        <el-input
                          clearable
                          v-model="articleForm.title"
                          placeholder="请输入标题"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item
                        label="分类"
                        prop="classifyID"
                        :disabled="isUpdate"
                      >
                        <el-select
                          v-model="articleForm.classifyID"
                          placeholder="选择一个分类"
                          filterable
                          ref="classifySelect"
                        >
                          <el-option
                            v-for="element in classifyInformationList"
                            :key="element.id"
                            :label="element.name"
                            :value="element.id"
                          />
                        </el-select>
                        <div class="button" @click="saveClassifyShow = true">
                          <span class="click">
                            <font-awesome-icon :icon="['fas', 'plus']" />
                            新增分类
                          </span>
                        </div>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item
                        label="标签"
                        prop="tagIDList"
                        :disabled="isUpdate"
                      >
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
                        <div class="button" @click="saveTagShow = true">
                          <span class="click">
                            <font-awesome-icon :icon="['fas', 'plus']" />
                            新增标签
                          </span>
                        </div>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-form-item label="隐私设置" prop="ifPrivate">
                        <el-radio-group v-model="articleForm.ifPrivate">
                          <el-radio :label="0">公开</el-radio>
                          <el-radio :label="1">私密</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>

                    <el-col :span="20">
                      <el-form-item label="摘要" prop="synopsis">
                        <el-input
                          v-model="articleForm.synopsis"
                          :rows="2"
                          type="textarea"
                          autosize
                          placeholder="为空时将会自动截取正文前20个字"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <div class="save">
                        <span v-if="!isUpdate" @click="save" class="click">
                          提交
                        </span>
                        <span v-if="isUpdate" @click="update" class="click">
                          保存
                        </span>
                      </div>
                    </el-col>
                  </el-row>
                  <el-form-item label="正文" prop="body">
                    <div class="mdEditor">
                      <MdEditor
                        v-model="articleForm.body"
                        :showCodeRowNumber="true"
                        :toolbars="toolbars"
                      />
                    </div>
                  </el-form-item>
                </el-form>
              </div>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
    <el-dialog
      v-model="saveClassifyShow"
      :show-close="false"
      class="favoriteBox"
      destroy-on-close
    >
      <template #header>
        <div class="my-header">
          <div class="close">
            <span class="title">新建收藏夹</span>
            <span @click="saveClassifyShow = false" class="click rotate">
              <font-awesome-icon :icon="['fas', 'times']" />
            </span>
          </div>
        </div>
      </template>
      <el-row justify="space-around" align="middle">
        <el-col :span="24">
          <el-form
            label-position="top"
            :model="classifyForm"
            :rules="elementFormRules"
            :hide-required-asterisk="true"
            ref="classifyFormRef"
          >
            <el-form-item label="名称" prop="name">
              <el-input clearable v-model="classifyForm.name" />
            </el-form-item>
            <el-form-item label="备注" prop="annotation">
              <el-input clearable v-model="classifyForm.annotation" />
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="24" class="button">
          <span @click="saveClassify" class="click">提交</span>
          <span @click="saveClassifyShow = false" class="click"> 取消 </span>
        </el-col>
      </el-row>
    </el-dialog>

    <el-dialog
      v-model="saveTagShow"
      :show-close="false"
      class="favoriteBox"
      destroy-on-close
    >
      <template #header>
        <div class="my-header">
          <div class="close">
            <span class="title">新建收藏夹</span>
            <span @click="saveTagShow = false" class="click rotate">
              <font-awesome-icon :icon="['fas', 'times']" />
            </span>
          </div>
        </div>
      </template>
      <el-row justify="space-around" align="middle">
        <el-col :span="24">
          <el-form
            label-position="top"
            :model="tagForm"
            :rules="elementFormRules"
            :hide-required-asterisk="true"
            ref="classifyFormRef"
          >
            <el-form-item label="名称" prop="name">
              <el-input clearable v-model="tagForm.name" />
            </el-form-item>
            <el-form-item label="备注" prop="annotation">
              <el-input clearable v-model="tagForm.annotation" />
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="24" class="button">
          <span @click="saveTag" class="click">提交</span>
          <span @click="saveTagShow = false" class="click"> 取消 </span>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Write from "./Write";
import Menu from "src/components/menu/Menu.vue";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { api } from "src/api/api";
import Message from "src/utils/MessageUtil";
import { UploadProps } from "element-plus";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Write());

    const router = useRouter();

    const route = useRoute();

    const id = Number(route.params.id); //字符串读取为数字

    const formRef = ref();

    const checkTagIDList = (
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      rule: any,
      value: string,
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      callback: any
    ): void => {
      viewData.articleForm.tagIDList.forEach((tagID) => {
        if (tagID < 1) {
          return callback(new Error("存在错误标签"));
        }
      });
      return callback();
    };

    const rules = {
      icon: [
        {
          required: true,
          message: "封面不能为空",
          trigger: "blur",
        },
        {
          pattern:
            /^https?:\/\/(.+\/)+.+(\.(gif|png|jpg|jpeg|webp|svg|psd|bmp|tif))$/i,
          message: "必须为图片链接",
          trigger: "blur",
        },
      ],
      title: [
        {
          required: true,
          message: "标题不能为空",
          trigger: "blur",
        },
        { max: 10, message: "长度不能超过10", trigger: "blur" },
      ],
      classifyID: [
        {
          type: "number",
          required: true,
          message: "必须选择一个分类",
          trigger: "blur",
        },
      ],
      tagIDList: [
        {
          required: true,
          message: "至少选择一个标签",
          trigger: "blur",
        },
        { validator: checkTagIDList, trigger: "blur" },
      ],
      ifPrivate: [
        {
          required: true,
          message: "隐私设置不能为空",
          trigger: "blur",
        },
      ],
      synopsis: [{ max: 100, message: "长度不能超过100", trigger: "blur" }],
      body: [
        {
          required: true,
          message: "正文不能为空",
          trigger: "blur",
        },
      ],
    };

    const save = (): void => {
      formRef.value
        .validate()
        .then((): void => {
          api.saveWriteArticle(viewData.articleForm).then(({ data }): void => {
            data;
            Message.successMessage("提交成功");
            router.push({
              name: "Index",
            });
          });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const update = (): void => {
      formRef.value
        .validate()
        .then((): void => {
          api
            .updateWriteArticle(viewData.articleForm)
            .then(({ data }): void => {
              data;
              Message.successMessage("保存成功");
              router.push({
                name: "Index",
              });
            });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const handleAvatarSuccess: UploadProps["onSuccess"] = (
      response,
      uploadFile
    ): void => {
      viewData.articleForm.icon = URL.createObjectURL(uploadFile.raw!);
    };

    const beforeAvatarUpload: UploadProps["beforeUpload"] = (
      rawFile
    ): boolean => {
      if (rawFile.type !== "image/jpeg/png") {
        Message.warningMessage("只能上传图片类型");
        return false;
      } else if (rawFile.size / 1024 / 1024 > 5) {
        Message.warningMessage("请勿上传超过5MB的图片");
        return false;
      }
      return true;
    };
    const classifyFormRef = ref();
    const tagFormRef = ref();

    const elementFormRules = {
      name: [
        {
          required: true,
          message: "名称不能为空",
          trigger: "blur",
        },
        { max: 10, message: "长度不能超过10", trigger: "blur" },
      ],
      annotation: [
        {
          required: true,
          message: "备注不能为空",
          trigger: "blur",
        },
        { max: 20, message: "长度不能超过20", trigger: "blur" },
      ],
    };

    const saveClassify = (): void => {
      classifyFormRef.value
        .validate()
        .then((): void => {
          api.saveWriteClassify(viewData.classifyForm).then((): void => {
            viewData.init();
            Message.successMessage("新建成功");
            viewData.saveClassifyShow = false;
            viewData.articleForm.classifyID = null;
          });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const saveTag = (): void => {
      tagFormRef.value
        .validate()
        .then((): void => {
          api.saveWriteTag(viewData.tagForm).then((): void => {
            viewData.init();
            Message.successMessage("新建成功");
            viewData.saveTagShow = false;
            viewData.articleForm.tagIDList = [];
          });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    onMounted((): void => {
      if (id > 0) {
        viewData.isUpdate = true;
        viewData.articleFormInit(id);
      }
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      save,
      update,
      handleAvatarSuccess,
      beforeAvatarUpload,
      formRef,
      rules,
      classifyFormRef,
      tagFormRef,
      elementFormRules,
      saveClassify,
      saveTag,
    };
  },
  components: { Menu, MdEditor },
});
</script>
<style lang="less">
@import url("./Write.less");
</style>
