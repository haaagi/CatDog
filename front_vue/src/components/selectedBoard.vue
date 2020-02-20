<template>
  <div style="margin-top: 80px;">
    <v-card class="mx-auto my-12" max-width="1200" style="margin-left: 20px;">
      <v-card-subtitle style="float:right">작성자: {{ onePost.nickname }}</v-card-subtitle>
      <v-card-title class="headline mb-1">{{ onePost.title }}</v-card-title>

      <v-card-text>
        <router-link
          :to="{
            name: 'updateboard',
            params: { onePost: onePost },
          }"
        >
          <v-btn icon style="float:right">
            <v-icon>{{ icons.mdiPencil }}</v-icon>
          </v-btn>
        </router-link>
        <router-link to="board">
          <v-btn icon style="float:right" @click="onDelete">
            <v-icon>{{ icons.mdiDelete }}</v-icon>
          </v-btn>
        </router-link>
        <td scope="col" colspan="20" v-html="onePost.contents">
          <!-- {{ onePost.contents }} -->
        </td>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-title>Comments</v-card-title>
      <v-list three-line v-for="(item, index) in reviewList" :key="index">
        <v-row style="margin-left:20px;">
          <v-list-item-avatar style="margin-right: 20px;">
            <div v-if="item.user.img === null || item.user.img === ''">
              <v-icon>mdi-dog</v-icon>
            </div>
            <div v-else>
              <v-img :src="item.user.img" />
            </div>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-subtitle class="overline">{{
              !item.user || item.user.nickname
            }}</v-list-item-subtitle>
            <v-list-item-title class="subtitle-1">{{ item.contents }}</v-list-item-title>
          </v-list-item-content>
        </v-row>
      </v-list>

      <!-- 댓글 입력창  -->
      <v-row>
        <v-text-field
          v-model="review.contents"
          label="Comment"
          placeholder="댓글을 남겨주세요"
          outlined
          style="max-width: 1000px; margin-left: 50px; margin-top: 20px;"
        ></v-text-field>
        <v-btn @click="reviewSubmit" icon style="margin-top: 30px;">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </v-row>

      <!-- 뒤로가기  -->
      <v-card-actions style="float:right; margin-right: 60px;">
        <router-link to="/board">
          <v-btn color="deep-purple lighten-2" text>
            Back
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
import { mdiDelete, mdiPencil } from '@mdi/js';
import API from '../plugins/api';
export default {
  props: {
    post: Object,
  },
  data() {
    return {
      review: {
        nickname: '',
        bid: 0,
        contents: '',
      },
      icons: { mdiDelete, mdiPencil },
      onePost: [],
      reviewList: [],
      selectedBoard: {
        bid: 0,
        title: '',
        nickname: '',
        contents: '',
      },
    };
  },
  created() {
    this.onePost = this.post;
    this.onePost.contents = this.onePost.contents.replace(/(\n|\r\n)/g, '<br>');

    API.get('api/board/boarddetail/' + this.post.bid).then(res => {
      this.reviewList = res.data;
      console.log(this.reviewList);
    });
  },
  methods: {
    onDelete() {
      const bid = this.post.bid;
      API.delete('auth/board/delete/' + bid);
    },
    reviewSubmit() {
      this.review.nickname = sessionStorage.getItem('nickname');
      this.review.bid = this.post.bid;
      API.post('auth/board/comment/', this.review).then(res => {
        console.log(res);
        this.reviewList = res.data;
        this.review.contents = '';
      });
    },
  },
};
</script>
<style></style>
