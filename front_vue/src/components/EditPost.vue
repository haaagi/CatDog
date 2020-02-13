/* eslint-disable vue/no-unused-vars */

<template>
  <v-row justify="center">
    <v-btn icon @click.stop="dialog = true">
      <v-icon color="orange">{{ icons.mdiPencil }}</v-icon>
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
              <v-text-field v-model="propContent"> </v-text-field>
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
  </v-row>
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
      this.$emit('updateContent', this.propContent);
      this.editContents.content = this.propContent;
      axios
        .put(HOST + 'auth/posts/update/' + this.selectedPost.pid, this.editContents)
        .then(res => {
          console.log(this.editContents.content);
          console.log(res);
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
