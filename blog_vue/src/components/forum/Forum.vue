<template>
  <div class="forumBox">
    <el-row class="input">
      <el-col :span="22">
        <el-form
          label-position="left"
          ref="formRef"
          :model="commentForm"
          :rules="rules"
          :hide-required-asterisk="true"
        >
          <el-form-item label="发表评论" prop="comment">
            <el-input
              v-model="commentForm.comment"
              :rows="2"
              type="textarea"
              autosize
              placeholder="请输入..."
            />
          </el-form-item>
        </el-form>
      </el-col>
      <el-col class="buttom" :span="1" :offset="1">
        <span class="click" @click="saveComment()">提交</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="target" v-if="rootID != -1 || parentID != -1">
        <span v-if="rootID != -1">
          评论@
          <span>
            {{ rootUsername }}
          </span>
        </span>
        <span v-if="parentID != -1">
          回复@
          <span>
            {{ parentUsername }}
          </span>
        </span>
        <span class="cancel click" @click="cancel">取消</span>
      </el-col>
    </el-row>
    <el-row class="forum" v-if="commentList[0].id != -1">
      <el-col
        :span="24"
        v-for="comment in commentList"
        :key="comment.id"
        class="comment"
      >
        <el-col :span="4">
          <div
            class="icon"
            :style="{
              backgroundImage: 'url(' + comment.icon + ')',
            }"
          ></div>
        </el-col>
        <el-col :span="20">
          <span class="click">{{ comment.nickname }}</span>
          <p class="body">{{ comment.body }}</p>
          <span>{{ comment.creationTime }}</span>
          <span class="click function" @click="likeComment(comment.id)">
            <span v-if="likeCommentIDList.indexOf(comment.id) !== -1">
              <font-awesome-icon :icon="['fas', 'thumbs-up']" />
              取消点赞
            </span>
            <span v-if="likeCommentIDList.indexOf(comment.id) === -1">
              <font-awesome-icon :icon="['far', 'thumbs-up']" />
              点赞
            </span>
          </span>
          <span>评论{{ comment.childCount }}</span>
          <span
            class="click"
            @click="toForum(comment.nickname, comment.id, '', -1)"
            >回复</span
          >
          <el-col class="children" v-if="comment.commentChildList[0].id != -1">
            <el-col
              :span="24"
              v-for="child in comment.commentChildList"
              :key="child.id"
              class="child"
            >
              <el-col :span="2">
                <div
                  class="icon"
                  :style="{
                    backgroundImage: 'url(' + child.icon + ')',
                  }"
                ></div>
              </el-col>
              <el-col :span="22">
                <span class="click">{{ child.nickname }}</span>
                <span v-if="child.parentNickname != ''" class="parent">
                  @{{ child.parentNickname }}
                </span>
                <p class="body">{{ child.body }}</p>
                <span>{{ child.creationTime }}</span>
                <span class="click function" @click="likeComment(child.id)">
                  <span v-if="likeCommentIDList.indexOf(child.id) !== -1">
                    <font-awesome-icon :icon="['fas', 'thumbs-up']" />
                    取消点赞
                  </span>
                  <span v-if="likeCommentIDList.indexOf(child.id) === -1">
                    <font-awesome-icon :icon="['far', 'thumbs-up']" />
                    点赞
                  </span>
                </span>
                <span
                  class="click"
                  @click="
                    toForum(
                      comment.nickname,
                      comment.id,
                      child.nickname,
                      child.id
                    )
                  "
                >
                  回复
                </span>
              </el-col>
            </el-col>
          </el-col>
          <el-divider
            v-if="comment.id != commentList[commentList.length - 1].id"
          />
        </el-col>
      </el-col>
    </el-row>
    <el-row v-if="commentList[0].id === -1">
      <el-col style="text-align: center" :span="24">还没有评论哦</el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { api } from "src/api/api";
import Message from "src/utils/MessageUtil";
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Forum from "./Forum";

export default defineComponent({
  emits: { toForum: null },
  props: {
    articleID: {
      type: Number,
      required: true,
    },
  },
  setup(props, { emit }) {
    /**数据仓初始化 */
    const viewData = reactive(new Forum(props.articleID));

    const rules = {
      comment: [
        {
          required: true,
          message: "评论不能为空",
          trigger: "blur",
        },
        { max: 500, message: "长度不能超过500", trigger: "blur" },
      ],
    };

    const formRef = ref();

    /**新的评论函数 */
    const saveComment = (): void => {
      if (viewData.isLogin) {
        formRef.value
          .validate()
          .then((): void => {
            api.saveContentComment(viewData.getCommentForm()).then((): void => {
              Message.successMessage("评论成功");
              viewData.init();
            });
          })
          .catch((): void => {
            Message.warningMessage("校验未通过");
          });
      } else {
        Message.warningMessage("您未登录");
      }
    };

    /**
     * 回复点击跳转函数
     * @param rootUsername 根评论用户昵称
     * @param rootID 根评论id
     * @param parentUsername 父评论用户昵称
     * @param parentID 夫评论id
     */
    const toForum = (
      rootUsername: string,
      rootID: number,
      parentUsername: string,
      parentID: number
    ): void => {
      if (viewData.isLogin) {
        viewData.rootUsername = rootUsername;
        viewData.rootID = rootID;
        viewData.parentUsername = parentUsername;
        viewData.parentID = parentID;
        emit("toForum");
        Message.successMessage("已为您瞄准目标");
      } else {
        Message.warningMessage("您未登录");
      }
    };

    /**取消评论函数 */
    const cancel = (): void => {
      viewData.rootUsername = "";
      viewData.rootID = -1;
      viewData.parentUsername = "";
      viewData.parentID = -1;
    };

    const likeComment = (commentID: number): void => {
      const index = viewData.likeCommentIDList.indexOf(commentID);
      if (index !== -1) {
        viewData.likeCommentIDList.splice(index, 1);
      } else {
        viewData.likeCommentIDList.push(commentID);
      }
      viewData.updateCommentLike(commentID, index !== -1);
    };

    onMounted((): void => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      props,
      saveComment,
      toForum,
      cancel,
      rules,
      formRef,
      likeComment,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url(./Forum.less);
</style>
