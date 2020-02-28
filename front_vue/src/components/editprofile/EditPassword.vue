<template>
  <div>
    <v-card-text style="height: 600px;">
      <v-col cols="12" md="10">
        <v-text-field v-model="pastPw" label="현재 비밀번호" required type="password">
        </v-text-field>
      </v-col>

      <v-col cols="12" md="10">
        <v-text-field
          v-model="newPw"
          :rules="newPwRules"
          :counter="20"
          label="새 비밀번호"
          required
          type="password"
        >
        </v-text-field>
      </v-col>

      <v-col cols="12" md="10">
        <v-text-field
          v-model="checkPw"
          :rules="checkPwRules"
          label="새 비밀번호 확인"
          required
          type="password"
        >
        </v-text-field>
      </v-col>

      <v-col cols="12" md="10">
        <v-row style="place-content: center;">
          <v-btn class="mr-4" @click="submit">save</v-btn>
          <router-link to="/userdetail"> <v-btn>cancle</v-btn></router-link>
        </v-row>
      </v-col>
    </v-card-text>
  </div>
</template>

<script>
import API from '../../plugins/api';
export default {
  name: 'EditPassword',
  data: () => ({
    // 이전 비밀번호
    pastPw: '',

    // 새 비밀번호 pw 유효성 검사
    newPw: '',
    newPwRules: [
      v => !!v || '비밀번호를 다시 설정해주세요',
      v => /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(v) || '비밀번호를 다시 설정해주세요',
      v => v.length <= 20 || '20자 이하로 하거라',
    ],

    // 새 비밀번호 확인
    checkPw: '',
    checkPwRules: [
      // 병학오빠한테 코드가 있겠지?...
      // v => newPw === checkPw || 'ㄴㄴ 다시 입력 ㄱㄱ ',
    ],
  }),
  methods: {
    submit() {
      const userNickname = sessionStorage.getItem('nickname');
      if (this.checkPw != this.newPw) {
        alert('비밀번호가 일치하지 않습니다. 다시 확인해 주세요');
      } else {
        API.put('auth/user/update/' + userNickname, {
          password: this.newPw,
        }).then(res => {
          console.log(res);
        });
      }
    },
  },
};
</script>

<style scoped></style>
