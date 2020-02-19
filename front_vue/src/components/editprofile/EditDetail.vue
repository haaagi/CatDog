<template>
  <div>
    <v-card-text style="height: 600px;">
      <v-col cols="12" md="10">
        <div v-if="userInfo.profileimg === null">
          <v-avatar size="80"> <v-icon>mdi-dog</v-icon> </v-avatar>
        </div>
        <div v-else>
          <v-avatar size="80">
            <img :src="userInfo.profileimg" :alt="userInfo.nickname" />
          </v-avatar>
        </div>

        <p class="font-weight-black title" style="margin-bottom: 1px; margin-top: 5px;">
          {{ userInfo.nickname }}
        </p>
        <ImgEditButton />
      </v-col>

      <v-col cols="12" md="10">
        <v-text-field
          v-model="editInfo.nickname"
          :rules="nameRules"
          :counter="20"
          label="Nick Name"
          required
          :placeholder="userInfo.nickname"
        >
        </v-text-field>
      </v-col>

      <v-col cols="12" md="10">
        <v-text-field v-model="editInfo.pr" label="자기소개" required :placeholder="userInfo.pr">
        </v-text-field>
      </v-col>

      <!-- <v-flex lg5>
          <v-col>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <v-combobox
                    v-model="likePet"
                    :items="dogs"
                    label="내가 좋아하는 강아지"
                    multiple
                    chips
                  ></v-combobox>
                </v-col>
              </v-row>
            </v-container>
          </v-col>

          <v-col>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <v-combobox
                    v-model="myPet"
                    :items="dogs"
                    label="내가 키우는 강아지"
                    multiple
                    chips
                  ></v-combobox>
                </v-col>
              </v-row>
            </v-container>
          </v-col>
        </v-flex> -->

      <v-col cols="12" md="10">
        <v-row style="place-content: center;">
          <router-link to="/userdetail">
            <v-btn class="mr-4" @click="onSave">save</v-btn>
          </router-link>
          <router-link to="/userdetail"> <v-btn>cancle</v-btn></router-link>
        </v-row>
      </v-col>
    </v-card-text>
  </div>
</template>

<script>
import ImgEditButton from './ImgEditButton';

const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');

export default {
  name: 'EditDetail',
  components: {
    ImgEditButton,
  },
  data: () => ({
    userInfo: [],
    editInfo: {
      img: '',
      nickname: '',
      pr: '',
    },

    // nick name 유효성 검사
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length >= 1 || 'Name must be less than 10 characters',
    ],

    img: '',

    // phoneNumber: '',
    // // phone number 유효성 검사
    // phoneRules: [
    //   v => !!v || '핸드폰 번호를 입력해주세요',
    //   v => /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '적합한 핸드폰 번호 형식이 아닙니다.',
    // ],

    // email 유효성 검사
    // email: '',
    // emailRules: [v => !!v || 'E-mail is required', v => /.+@.+/.test(v) || 'E-mail must be valid'],

    likePet: ['푸들'],
    myPet: ['포메라니안'],
    dogs: ['시츄', '말티즈', '포메라니안', '푸들'],
  }),
  methods: {
    onSave() {
      const userNickname = sessionStorage.getItem('nickname');
      this.editInfo.img = sessionStorage.getItem('profileFile');
      // console.log(this.editInfo);
      if (this.editInfo.img === null) {
        this.editInfo.img = this.userInfo.profileimg;
      }
      if (this.editInfo.nickname === '') {
        alert('닉네임을 한글자 이상 설정해주세요.');
      } else {
        axios
          .put(HOST + 'auth/user/update/' + userNickname, {
            img: this.editInfo.img,
            pr: this.editInfo.pr,
            nickname: this.editInfo.nickname,
          })
          .then(res => {
            console.log(this.editInfo.img);
            console.log(res);
            sessionStorage.removeItem('nickname');
            sessionStorage.setItem('nickname', res.data.nickname);
            sessionStorage.removeItem('profileFile');
          });
      }
    },
  },
  beforeCreate() {
    const userNickname = sessionStorage.getItem('nickname');
    axios
      .get(HOST + 'auth/myPage/' + userNickname)
      .then(res => {
        this.userInfo = res.data;
        console.log(res);
        console.log(this.userInfo);
      })
      .catch(err => console.error(err));
  },
};
</script>

<style scoped></style>
