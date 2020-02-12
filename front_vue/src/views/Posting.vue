<template>
  <v-app style="z-index=1">
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn class="mb-12" right bottom absolute fixed fab dark color="amber" v-on="on"
            >+</v-btn
          >
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">피드 작성하기</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-layout justify-center align-center>
                <img height="auto" width="400px" class="previewImage" :src="postingFile.img" />
              </v-layout>
              <v-layout justify-center align-center>
                <v-flex>
                  <v-file-input
                    type="file"
                    id="file"
                    ref="fileInput"
                    show-size
                    accept="image/*"
                    @change="onSave($event)"
                    label="사진을 올려주세요"
                    style="margin-top:40px"
                  ></v-file-input>
                </v-flex>
              </v-layout>
              <v-row>
                <v-col cols="12">
                  <v-textarea
                    name="input-7-1"
                    filled
                    label="Label"
                    auto-grow
                    value="내용을 작성해주세요"
                    v-model="postingFile.content"
                  ></v-textarea>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="hashtag"
                    type="text"
                    v-model="postingFile.hashtags"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="reset">Close</v-btn>
            <v-btn color="blue darken-1" text @click="onSubmit">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
</template>

<script>
const axios = require('axios');
const HOST = process.env.VUE_APP_SERVER_HOST;
export default {
  name: 'Posting',
  data() {
    return {
      postList: [],
      dialog: false,
      postingFile: {
        nickname: '',
        img: '',
        content: '',
        hashtags: '',
      },
    };
  },
  methods: {
    onSubmit() {
      this.postingFile.nickname = sessionStorage.getItem('nickname');
      axios.post(HOST + 'auth/posts/posting', this.postingFile).then(res => {
        console.log(res);
        this.dialog = false;
        this.postingFile.content = null;
        this.postingFile.img = null;
        this.postingFile.hashtags = null;
        this.$refs.fileInput.value = '';
      });
    },
    reset() {
      this.dialog = false;
      this.postingFile.content = null;
      this.postingFile.img = null;
      this.postingFile.hashtags = null;
      this.$refs.fileInput.value = '';
    },
    onSave(event) {
      console.log(event);
      let fd = new FormData();
      fd.append('image', event);
      axios
        .post('https://api.imgur.com/3/image', fd, {
          headers: {
            // 'Access-Control-Allow-Origin': '*',
            Authorization: 'Client-ID 1001abddfee2596',
          },
        })
        .then(res => {
          console.log(res);
          console.log(res.data.data.link);
          this.postingFile.img = res.data.data.link;
        });
    },
  },
};
</script>
