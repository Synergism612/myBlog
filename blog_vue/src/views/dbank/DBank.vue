<template>
  <div class="dbankBox">
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
                    <a href="/">资源分享</a>
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
            <el-col :xs="24" :sm="24" :md="24" :lg="20">
              <transition
                appear
                appear-active-class="animate__animated animate__backInUp"
              >
                <div v-if="isLogin" class="body frame">
                  <el-row>
                    <el-col :span="24">
                      <div class="button">
                        <span class="click" @click="back">
                          <font-awesome-icon :icon="['fas', 'arrow-left']" />
                          返回上一级
                        </span>
                        <span class="click" @click="uploadShow = true">
                          <font-awesome-icon :icon="['fas', 'upload']" />
                          上传文件
                        </span>
                        <span class="click" @click="saveFolderShow = true">
                          <font-awesome-icon :icon="['fas', 'folder-plus']" />
                          新建文件夹
                        </span>
                        <span class="click" @click="refresh">
                          <font-awesome-icon :icon="['fas', 'sync']" />
                          刷新
                        </span>
                      </div>
                    </el-col>
                  </el-row>
                  <el-divider />
                  <el-row>
                    <el-col :span="24">
                      <div class="path">
                        当前路径：
                        <span class="click" @click="getRepository"
                          >仓库 |
                        </span>
                        {{ path }}
                      </div>
                    </el-col>
                  </el-row>
                  <el-divider />
                  <el-row>
                    <el-col :span="24">
                      <div class="file">
                        <el-table :data="DataList" style="width: 100%" stripe>
                          <el-table-column
                            fixed
                            prop="name"
                            label="名称"
                            min-width="150"
                          />
                          <el-table-column
                            prop="suffix"
                            label="后缀"
                            min-width="120"
                          />
                          <el-table-column
                            prop="size"
                            label="大小"
                            min-width="100"
                          />
                          <el-table-column
                            prop="href"
                            label="链接"
                            min-width="500"
                          />
                          <el-table-column
                            prop="creationTime"
                            label="创建时间"
                            min-width="150"
                          />
                          <el-table-column
                            fixed="right"
                            label="选项"
                            min-width="120"
                          >
                            <template v-slot="scope">
                              <el-button
                                link
                                type="primary"
                                size="small"
                                @click="open(scope.row.href, scope.row.path)"
                              >
                                打开
                              </el-button>
                              <el-button
                                link
                                type="primary"
                                size="small"
                                @click="remove(scope.row.id, scope.row.href)"
                              >
                                删除
                              </el-button>
                            </template>
                          </el-table-column>
                        </el-table>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </transition>
              <div v-if="!isLogin" style="text-align: center">您未登录</div>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
    <el-dialog
      v-model="saveFolderShow"
      :show-close="false"
      destroy-on-close
      @close="saveFolderClose"
    >
      <template #header>
        <div class="my-header">
          <div class="close">
            <span class="title">新建文件夹</span>
            <span @click="saveFolderShow = false" class="click rotate">
              <font-awesome-icon :icon="['fas', 'times']" />
            </span>
          </div>
        </div>
      </template>
      <el-row justify="space-around" align="middle">
        <el-col :span="24">
          <el-form
            label-position="top"
            :model="folderForm"
            :rules="folderFormRules"
            :hide-required-asterisk="true"
            ref="folderFormRef"
          >
            <el-form-item label="名称" prop="name">
              <el-input clearable v-model="folderForm.name" />
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="24" class="button">
          <span @click="saveFolder" class="click">提交</span>
          <span @click="saveFolderShow = false" class="click"> 取消 </span>
        </el-col>
      </el-row>
    </el-dialog>
    <Upload
      v-model:upload-show="uploadShow"
      :upload-config="config"
      @upload="upload"
      @close="uploadShow = false"
    />
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Menu from "src/components/menu/Menu.vue";
import Upload from "src/components/upload/Upload.vue";
import { api } from "src/api/api";
import DBank from "./DBank";
import Message from "src/utils/MessageUtil";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new DBank());

    const getRepository = (): void => {
      if (viewData.isLogin) {
        viewData.init();
      }
    };

    const open = (href: string, path: string): void => {
      if (href) {
        window.open(href, "_blank");
      } else {
        viewData.updateDataArray(path);
      }
    };

    const remove = (id: number, href: string): void => {
      if (href) {
        api.removeDBankFile(id).then((): void => {
          refresh();
          Message.successMessage("删除成功");
        });
      } else {
        api.removeDBankFolder(id).then((): void => {
          refresh();
          Message.successMessage("删除成功");
        });
      }
    };

    const back = (): void => {
      if (viewData.path.indexOf("/") != -1) {
        if (viewData.path.split("/").length <= 2) {
          getRepository();
        } else {
          open("", viewData.path.substring(0, viewData.path.lastIndexOf("/")));
        }
      } else {
        if (viewData.path.split("\\").length <= 2) {
          getRepository();
        } else {
          open("", viewData.path.substring(0, viewData.path.lastIndexOf("\\")));
        }
      }
    };

    const upload = (formData: FormData): void => {
      if (formData) {
        api.saveDBankFile(formData, viewData.path).then((): void => {
          refresh();
          Message.successMessage("上传成功");
          viewData.uploadShow = false;
        });
      }
    };

    const refresh = (): void => {
      if (viewData.path.indexOf("/") != -1) {
        if (viewData.path.split("/").length <= 2) {
          getRepository();
        } else {
          open("", viewData.folderInformation.path);
        }
      } else {
        if (viewData.path.split("\\").length <= 2) {
          getRepository();
        } else {
          open("", viewData.folderInformation.path);
        }
      }
    };

    const folderFormRules = {
      name: [
        {
          required: true,
          message: "名称不能为空",
          trigger: "blur",
        },
        { max: 10, message: "长度不能超过10", trigger: "blur" },
        {
          pattern: /^[^? \\ * | “ < > : /]{1,256}$/,
          message: "文件夹名不合法",
          trigger: "blur",
        },
      ],
    };

    const folderFormRef = ref();

    const saveFolder = (): void => {
      if (viewData.isLogin) {
        folderFormRef.value
          .validate()
          .then((): void => {
            if (!viewData.isRepository) {
              viewData.folderForm.parentPath = viewData.path;
            }
            api.saveDBankFolder(viewData.folderForm).then((): void => {
              viewData.saveFolderShow = false;
              refresh();
              Message.successMessage("创建成功");
            });
          })
          .catch((): void => {
            Message.warningMessage("校验未通过");
          });
      } else {
        Message.warningMessage("您未登录");
      }
    };

    const saveFolderClose = (): void => {
      viewData.folderFormInit();
    };

    onMounted((): void => {
      if (viewData.isLogin) {
        viewData.init();
      }
    });

    return {
      ...toRefs(viewData),
      getRepository,
      open,
      remove,
      back,
      upload,
      refresh,
      folderFormRef,
      folderFormRules,
      saveFolder,
      saveFolderClose,
    };
  },
  components: { Menu, Upload },
});
</script>
<style lang="less">
@import url("./DBank.less");
</style>
