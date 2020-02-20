<template>
  <v-container style="margin-top:100px">
    <v-text-field
      v-model="board.title"
      required
      color="deep-purple"
      counter="30"
      label="글 제목"
      style="min-height: 96px"
      type="text"
    ></v-text-field>
    <v-textarea
      v-model="board.contents"
      counter
      clearable
      height="400"
      clear-icon="cancel"
      filled
      label="본문"
      auto-grow
    ></v-textarea>
    <router-link to="/board"><v-btn @click="onSubmit">제출하기</v-btn></router-link>
  </v-container>
</template>
<script>
import API from '../plugins/api';
export default {
  data() {
    return {
      board: {
        title: '',
        contents: '',
        type: 0,
        nickname: '',
      },
    };
  },
  methods: {
    onSubmit() {
      this.board.nickname = sessionStorage.getItem('nickname');
      API.post('auth/board/upload', this.board).then(res => {
        console.log(res);
        this.board.title = '';
        this.board.contents = '';
        this.board.type = 0;
        this.board.nickname = '';
      });
    },
  },
};
</script>
<style></style>
