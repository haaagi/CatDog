/* eslint-disable vue/no-unused-vars */
<template>
  <!-- 수경이가 다시... -->
  <v-row justify="center">
    <v-btn color="blue darken-1" text @click.stop="dialog = true">
      Edit
    </v-btn>
    <v-dialog v-model="dialog" persistent max-width="900px">
      <v-card>
        <!-- 작성자 나오는 부분  -->
        <v-card-title>
          <v-col cols="12" sm="4" md="4">
            <v-list-item>
              <!-- 글쓴이 프로필 사진 -->
              <v-list-item-avatar>
                <div v-if="selectedPost.profileimg === null">
                  <v-icon>mdi-dog</v-icon>
                </div>
                <div v-else>
                  <v-img :src="selectedPost.profileimg" />
                </div>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="headline">{{ selectedPost.nickname }}</v-list-item-title>
                <v-list-item-subtitle>{{ selectedPost.modifiedDate }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-col>
          <v-col cols="12" sm="4" md="8">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="submitEdit">
                Save
              </v-btn>
              <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            </v-card-actions>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon large @click="deletePost">
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>
            </v-card-actions>
          </v-col>
        </v-card-title>
        <v-card-text>
          <!-- 내용 나오는 부분  -->
          <v-container>
            <v-row>
              <!-- 사진 나오는 부분  -->
              <v-col cols="12" sm="8" md="8">
                <v-img
                  aspect-ratio="1"
                  :src="selectedPost.img"
                  :alt="selectedPost.contents"
                ></v-img>
              </v-col>
              <!-- 수정 내용 부분  -->
              <v-col cols="12" sm="4" md="4">
                <v-textarea
                  v-model="propContent"
                  outlined
                  name="input-7-4"
                  label="수정"
                  value="propContent"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
  <!-- 수경이 다시 끝  -->
  <!-- 오빠 코드  -->
  <!-- <v-row justify="center">
    <v-btn color="blue darken-1" text @click.stop="dialog = true">
      Edit
    </v-btn>
    <v-dialog v-model="dialog">
      <v-card justify-center>
        <v-row>
          <v-col>
            <v-img aspect-ratio="1" :src="selectedPost.img"></v-img>
          </v-col>
          <v-col>
            <v-list-item>
              <v-list-item-avatar color="grey"></v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="headline">{{ selectedPost.nickname }}</v-list-item-title>
                <v-list-item-subtitle>{{ selectedPost.modifiedDate }}</v-list-item-subtitle>
              </v-list-item-content>
              <v-flex xs2>
                <v-list-item>
                  <v-btn class="ma-2" color="blue darken-2" dark @click="submitEdit">
                    수정
                  </v-btn>
                </v-list-item>
              </v-flex>
            </v-list-item>
            <v-card-text>
              <v-text-field :placeholder="selectedPost.contents" v-model="propContent">
              </v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon @click="deletePost">
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>
            </v-card-actions>
            <hr />
          </v-col>
        </v-row>
      </v-card>
    </v-dialog>
  </v-row> -->
</template>
<script>
import { mdiAccount, mdiPencil, mdiDelete, mdiArrowRightBoldBox } from '@mdi/js';
const axios = require('axios');
const HOST = process.env.VUE_APP_SERVER_HOST;
export default {
  name: 'ModalPost',
  props: {
    selectedPost: Object,
    realContent: String,
  },
  data() {
    return {
      propContent: this.realContent,
      editContents: { content: '' },
      icons: {
        mdiAccount,
        mdiPencil,
        mdiDelete,
        mdiArrowRightBoldBox,
      },
      dialog: false,
      items: [],
    };
  },
  //   created() {
  //     axios.get(HOST + 'auth/posts/comment/' + this.selectedPost.pid).then(res => {
  //       console.log(res);
  //       console.log(this.selectedPost);
  //       this.items = res.data;
  //     });
  //   },
  methods: {
    submitEdit() {
      this.editContents.content = this.propContent;
      axios
        .put(HOST + 'auth/posts/update/' + this.selectedPost.pid, this.editContents)
        .then(res => {
          console.log(this.editContents.content);
          console.log(res);
          this.$emit('post');
        });
      this.dialog = false;
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
<style></style>
