/* eslint-disable vue/no-unused-vars */

<template>
  <v-row justify="center">
    <v-btn
      icon
      @click.stop="dialog = true"
      absolute
      color="orange"
      class="white--text"
      fab
      large
      right
      top
    >
      <v-icon color="orange">mdi-dog</v-icon>
    </v-btn>

    <v-dialog v-model="dialog">
      <v-card justify-center>
        <v-row>
          <v-col class="a">
            <v-img aspect-ratio="1" :src="selectedPost.img"></v-img>
          </v-col>

          <v-col class="b">
            <v-list-item>
              <v-list-item-avatar color="grey"></v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="headline">{{ selectedPost.nickname }}</v-list-item-title>
                <v-list-item-subtitle>{{ selectedPost.modifiedDate }}</v-list-item-subtitle>
              </v-list-item-content>
              <v-flex xs2>
                <v-list-item>
                  <v-btn class="ma-2" color="blue darken-2" dark @click="dialog = false">
                    <v-icon dark center>mdi-arrow-left</v-icon>
                  </v-btn>
                </v-list-item>
              </v-flex>
            </v-list-item>

            <v-card-text>
              {{ realContent }}
            </v-card-text>
            <v-item v-for="(hash, i) in selectedPost.hashtags" :key="i">
              <v-chip active-class="purple--text">
                {{ hash }}
              </v-chip>
            </v-item>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon>
                <EditPost :selectedPost="selectedPost" :realContent="realContent" />
              </v-btn>
              <v-btn icon @click="deletePost">
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>
            </v-card-actions>
            <hr />
            <v-container>
              <v-col cols="12" sm="9">
                <v-text-field
                  v-model="review.contents"
                  label="댓글을 남겨주세요"
                  solo
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="2">
                <v-btn icon @click="reviewSubmit">
                  <v-icon l>{{ icons.mdiSend }}</v-icon>
                </v-btn>
              </v-col>
            </v-container>
            <v-list three-line v-for="(item, index) in items" :key="index">
              <v-list-item-content>
                <v-list-item-subtitle v-html="item.user.nickname"></v-list-item-subtitle>

                <v-list-item-title v-html="item.content"></v-list-item-title>
              </v-list-item-content>
            </v-list>
          </v-col>
        </v-row>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import EditPost from './EditPost';
import { mdiAccount, mdiPencil, mdiDelete, mdiArrowRightBoldBox, mdiSend } from '@mdi/js';
const axios = require('axios');
const HOST = process.env.VUE_APP_SERVER_HOST;
export default {
  name: 'ModalPost',
  components: { EditPost },
  props: {
    selectedPost: Object,
  },
  data() {
    return {
      realContent: '',
      icons: {
        mdiAccount,
        mdiPencil,
        mdiDelete,
        mdiArrowRightBoldBox,
        mdiSend,
      },
      review: {
        pid: this.selectedPost.pid,
        nickname: this.selectedPost.nickname,
        contents: '',
      },
      dialog: false,
      items: [],
    };
  },
  // mounted() {
  //   axios.get(HOST + 'api/posts/postdetail/' + this.selectedPost.pid).then(res => {
  //     console.log(res);
  //     this.realContent = res.data.contents;
  //   });
  // },
  beforecreated() {
    axios.get(HOST + 'auth/posts/comment/' + this.selectedPost.pid).then(res => {
      // console.log(this.selectedPost);
      this.items = res.data;
    });
    this.realContent = this.selectedPost.contents;
  },
  methods: {
    updateContent(text) {
      this.realContent = text;
    },
    reviewSubmit() {
      axios.post(HOST + 'auth/posts/comment', this.review).then(res => {
        console.log(res.data);
        this.items = res.data;
        console.log(this.items);
        this.review.contents = null;
      });
    },
    deletePost() {
      axios.delete(HOST + 'auth/posts/delete/' + this.selectedPost.pid).then(res => {
        console.log(res);
        this.dialog = false;
      });
    },
  },
};
</script>
<style scoped>
.a {
  overflow-y: unset;
}
.b {
  overflow-y: scroll;
}
</style>
