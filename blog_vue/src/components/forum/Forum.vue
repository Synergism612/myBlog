<template>
  <div class="box">
    <el-row class="forum">
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
              <span v-if="child.parentNickname != ''">
                @{{ child.parentNickname }}
              </span>
              <p class="body">{{ child.body }}</p>
              <span>{{ child.creationTime }}</span>
              <span>点赞{{ child.likeCount }}</span>
              <span>回复</span>
            </el-col>
          </el-col>
          <span v-if="comment.childCount > 2">更多评论</span>
        </el-col>
      </el-col>
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
    const viewData = reactive({});

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
