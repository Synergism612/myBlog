<template>
  <div class="box">
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
        <span>提交</span>
      </el-col>
    </el-row>
    <el-row class="forum" v-if="commentList[0].id != -1">
      <el-col
        :span="24"
        v-for="comment in props.commentList"
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
          <span>{{ comment.nickname }}</span>
          <p class="body">{{ comment.body }}</p>
          <span>{{ comment.creationTime }}</span>
          <span>点赞{{ comment.likeCount }}</span>
          <span>回复</span>
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
                <span>{{ child.nickname }}</span>
                <span v-if="child.parentNickname != ''" class="parent">
                  @{{ child.parentNickname }}
                </span>
                <p class="body">{{ child.body }}</p>
                <span>{{ child.creationTime }}</span>
                <span>点赞{{ child.likeCount }}</span>
                <span>回复</span>
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
import CommentParent from "@/model/comment/CommentParent";
import { defineComponent, PropType, reactive, toRefs } from "vue";

export default defineComponent({
  props: {
    commentList: {
      type: Array as PropType<Array<CommentParent>>,
      required: true,
    },
  },
  setup(props) {
    /**数据仓初始化 */
    const viewData = reactive({
      commentInput: "",
    });

    return {
      ...toRefs(viewData),
      props,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url(./Forum.less);
</style>
