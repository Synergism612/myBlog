<template>
  <div class="forumBox">
    <el-row class="input">
      <el-col class="left" :span="1">
        <span>发表评论</span>
      </el-col>
      <el-col class="middle" :span="22">
        <el-input
          v-model="commentInput"
          :rows="2"
          type="textarea"
          placeholder="请输入..."
        />
      </el-col>
      <el-col class="right" :span="1">
        <span class="click" @click="addComment()">提交</span>
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
          <span class="click">点赞{{ comment.likeCount }}</span>
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
                <span class="click">点赞{{ child.likeCount }}</span>
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
import { api } from "@/api/api";
import Message from "@/utils/MessageUtil";
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Forum from "./Forum";

export default defineComponent({
  emits: ["toForum"],
  props: {
    articleID: {
      type: Number,
      required: true,
    },
  },
  setup(props, { emit }) {
    /**数据仓初始化 */
    const viewData = reactive(new Forum());

    /**新的评论函数 */
    const addComment = (): void => {
      if (viewData.isLogin) {
        if (viewData.checkInput()) {
          Message.warningMessage("评论校验未通过");
          return;
        }
        api
          .addComment(
            viewData.userInfo.username,
            viewData.commentInput,
            props.articleID,
            viewData.rootID,
            viewData.parentID
          )
          .then((): void => {
            viewData.commentInput = "";
            Message.successMessage("评论成功");
            viewData.init(props.articleID);
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
    ) => {
      if (viewData.isLogin) {
        viewData.rootUsername = rootUsername;
        viewData.rootID = rootID;
        viewData.parentUsername = parentUsername;
        viewData.parentID = parentID;
        emit("toForum");
        Message.infoMessage("请输入评论");
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

    onMounted(() => {
      viewData.init(props.articleID);
    });

    return {
      ...toRefs(viewData),
      props,
      addComment,
      toForum,
      cancel,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url(./Forum.less);
</style>
