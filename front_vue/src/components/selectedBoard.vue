<template>
  <div style="margin-top: 80px;">
    <v-card class="mx-auto my-12" max-width="1200" style="margin-left: 20px;">
      <v-card-subtitle style="float:right">작성자: {{ onePost.nickname }}</v-card-subtitle>
      <v-card-title class="headline mb-1">{{ onePost.title }}</v-card-title>

      <v-card-text>
        <div>
          {{ onePost.contents }}
        </div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-title>Comments</v-card-title>

      <v-card-text> 댓글 리스트 나오는 부분 </v-card-text>

      <!-- 댓글 입력창  -->
      <v-row>
        <v-text-field
          label="Comment"
          placeholder="댓글을 남겨주세요"
          outlined
          style="max-width: 1000px; margin-left: 50px;"
        ></v-text-field>
        <v-btn icon style="margin-top: 15px;">
          <v-icon>mdi-send</v-icon>
        </v-btn>
      </v-row>

      <!-- 뒤로가기  -->
      <v-card-actions style="float:right">
        <router-link to="/board">
          <v-btn color="deep-purple lighten-2" text @click="reserve">
            Back
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
// const axios = require('axios');
// const HOST = process.env.VUE_APP_SERVER_HOST;
export default {
  props: {
    post: Object,
  },
  data() {
    return {
      onePost: [],
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
  },
  methods: {},
};
</script>
<style></style>
