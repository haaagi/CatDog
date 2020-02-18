<template>
  <div style="margin-top: 80px;">
    <v-card class="mx-auto my-12" max-width="1200" style="margin-left: 20px;">
      <v-card-title class="headline mb-1">
        <v-text-field
          label="글 제목"
          v-model="pastPost.title"
          :value="pastPost.title"
          type="text"
          style="min-height: 96px"
        >
        </v-text-field>
      </v-card-title>

      <v-card-text>
        <v-textarea
          label="글 내용"
          v-model="pastPost.contents"
          counter
          height="400"
          filled
          auto-grow
          :value="pastPost.contents"
        ></v-textarea>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-btn @click="onSubmit">제출하기</v-btn>

      <!-- 뒤로가기  -->
      <v-card-actions style="float:right">
        <router-link to="/selectedpost">
          <v-btn color="deep-purple lighten-2" text>
            Back
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
const axios = require('axios');
const HOST = process.env.VUE_APP_SERVER_HOST;

export default {
  props: {
    onePost: Object,
  },
  data() {
    return {
      pastPost: [],
      updatePost: {
        title: '',
        contents: '',
      },
    };
  },
  created() {
    this.pastPost = this.onePost;
  },
  methods: {
    onSubmit() {
      const bid = this.pastPost.bid;

      axios
        .put(HOST + 'auth/board/update/' + bid, {
          title: this.pastPost.title,
          contents: this.pastPost.contents,
        })
        .then(res => {
          console.log(res);
        });
    },
  },
};
</script>
<style></style>
