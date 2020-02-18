<template>
  <div>
    <v-card>
      <!-- 작성자 나오는 부분  -->
      <v-card-title style="margin: 0;padding: 0;">
        <v-col cols="12" sm="4" md="4" style="margin: 0;padding: 0;">
          <router-link
            :to="{
              name: 'followdetail',
              params: {
                nickname: selectedPost.nickname,
              },
            }"
          >
            <v-list-item>
              <!-- 글쓴이 프로필 사진 -->
              <div v-if="selectedPost.profileimg === null">
                <v-icon>mdi-dog</v-icon>
              </div>
              <div v-else>
                <v-img
                  :src="selectedPost.profileimg"
                  style="height:50px; width:50px; border-radius: 50%;"
                />
              </div>
              <v-list-item-content style="padding:0;">
                <v-list-item-title class="headline" style="font-size: 1em !important; margin:0">{{
                  selectedPost.nickname
                }}</v-list-item-title>
                <v-list-item-subtitle style="font-size: 0.5em !important;">{{
                  selectedPost.modifiedDate
                }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </router-link>
        </v-col>
        <v-col cols="12" sm="4" md="8" style="margin: 0;padding: 0;">
          <v-card-actions>
            <v-spacer></v-spacer>
            <div v-if="userNickname === selectedPost.nickname">
              <v-btn color="blue darken-1" text>
                <EditPost :selectedPost="selectedPost" :hashTag="hashTag" @post="postUpdate"
              /></v-btn>
            </div>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          </v-card-actions>
        </v-col>
      </v-card-title>
      <v-card-text>
        <!-- 내용 나오는 부분  -->
        <v-row>
          <!-- 사진 나오는 부분  -->
          <v-col cols="12" sm="8" md="8">
            <v-img
              :src="selectedPost.img"
              :alt="selectedPost.contents"
              style="width= auto; height= auto;"
            ></v-img>
          </v-col>
          <!-- 내용 해쉬태그 댓글  -->
          <v-col cols="12" sm="4" md="4">
            <v-card-text class="title">
              {{ this.usercontents }}
            </v-card-text>
            <v-item v-for="(hash, i) in selectedPost.hashtags" :key="i">
              <router-link
                :to="{
                  name: 'tagname',
                  params: {
                    tagname: hash,
                  },
                }"
              >
                <v-chip active-class="purple--text"> #{{ hash }} </v-chip>
              </router-link>
            </v-item>
            <!-- 좋아요 버튼  -->
            <v-row>
              <div v-if="likeCheck">
                <v-btn text icon color="pink" @click="onClickLike">
                  <v-icon>mdi-heart</v-icon>
                </v-btn>
              </div>
              <div v-else>
                <v-btn text icon color="pink" @click="onClickLike">
                  <v-icon>mdi-heart-outline</v-icon>
                </v-btn>
              </div>
              <div class="body-1" style="padding-top: 8px;">
                <!-- 좋아요 {{ selectedPost.likes }} 개 -->
                좋아요 {{ this.likeCnt }} 개
              </div>
            </v-row>
            <!-- 댓글 리스트  -->
            <div v-if="items.length >= 1">
              <v-divider></v-divider>
              <v-list three-line v-for="(item, index) in items" :key="index">
                <router-link
                  :to="{
                    name: 'followdetail',
                    params: {
                      nickname: item.user.nickname,
                    },
                  }"
                >
                  <v-row>
                    <div v-if="!item.user || item.user.img === null">
                      <v-icon>mdi-dog</v-icon>
                    </div>
                    <div v-else>
                      <v-img
                        :src="item.user.img"
                        style="height:30px; width:30px; border-radius: 50%;"
                      />
                    </div>

                    <v-list-item-content>
                      <v-list-item-subtitle class="overline">{{
                        !item.user || item.user.nickname
                      }}</v-list-item-subtitle>
                      <v-list-item-title class="subtitle-1">{{ item.content }}</v-list-item-title>
                    </v-list-item-content>
                  </v-row>
                </router-link>
              </v-list>
            </div>
            <v-divider></v-divider>
            <!-- 댓글 입력창  -->
            <v-row>
              <v-text-field
                v-model="review.contents"
                label="Comment"
                placeholder="댓글을 남겨주세요"
                outlined
              ></v-text-field>
              <v-btn icon @click="reviewSubmit" style="margin-top: 10px;">
                <v-icon l>{{ icons.mdiSend }}</v-icon>
              </v-btn>
            </v-row>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </div>
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
      flag: 0,
      hashTag: '',
      icons: {
        mdiAccount,
        mdiPencil,
        mdiDelete,
        mdiArrowRightBoldBox,
        mdiSend,
      },
      review: {
        pid: this.selectedPost.pid,
        nickname: sessionStorage.getItem('nickname'),
        contents: '',
      },
      dialog: false,
      //댓글
      items: [],

      // 좋아요 유무 체크
      likeCheck: null,
      likeList: [],
      likeCnt: this.selectedPost.likes,
      userNickname: '',
      usercontents: this.selectedPost.contents,
    };
  },
  watch: {
    selectedPost() {
      axios.get(HOST + 'auth/posts/comment/' + this.selectedPost.pid).then(res => {
        // console.log(this.selectedPost);
        console.log('get', res.data);
        this.items = res.data;
        this.review.pid = this.selectedPost.pid;
        this.usercontents = this.selectedPost.contents;
      });
      this.hashTag = this.selectedPost.contents;
      // 좋아요 체크하기 위한
      this.userNickname = sessionStorage.getItem('nickname');
      axios
        .get(HOST + 'api/posts/like/likeslist/' + this.selectedPost.pid)
        .then(res => {
          this.likeList = res.data;
          if (this.likeList.includes(this.userNickname)) {
            this.likeCheck = true;
          } else {
            this.likeCheck = false;
          }
        })
        .catch(err => console.error(err));
    },
  },
  // watch: {
  //   realContent: function() {
  //     this.selectedPost.contents = this.realContent;
  //   },
  // },
  methods: {
    // 댓글 리스트
    reviewSubmit() {
      axios.post(HOST + 'auth/posts/comment', this.review).then(res => {
        console.log(res.data);
        this.items = [];
        this.items = res.data;
        this.review.contents = '';
      });
    },
    deletePost() {
      axios.delete(HOST + 'auth/posts/delete/' + this.selectedPost.pid).then(res => {
        console.log(res);
        this.dialog = false;
      });
    },
    // 좋아요
    onClickLike() {
      axios
        .post(HOST + 'auth/posts/likesup/', {
          pid: this.selectedPost.pid,
          nickname: this.userNickname,
        })
        .then(res => {
          console.log(res.data);
          this.likeCheck = res.data;
          axios.get(HOST + 'api/posts/postdetail/' + this.selectedPost.pid).then(res => {
            console.log(res);
            this.likeCnt = res.data.likes;
          });
        })
        .catch(err => console.error(err));
    },
    // 수정완료후 호출
    postUpdate() {
      axios.get(HOST + 'api/posts/postdetail/' + this.selectedPost.pid).then(res => {
        //alert(res.data.contents);
        this.usercontents = res.data.contents;
      });
    },
    getCon() {
      alert('go');
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
