<template>
  <div>
    <v-card>
      <!-- 작성자 나오는 부분  -->
      <v-card-title>
        <v-col cols="12" sm="4" md="4">
          <router-link
            :to="{
              name: 'followdetail',
              params: { nickname: selectedPost.nickname },
            }"
            style="color: rgb(34, 136, 150);   text-decoration: none;"
          >
            <v-list-item>
              <!-- 글쓴이 프로필 사진 -->
              <div style="text-align: center;">
                <div v-if="!selectedPost.profileimg">
                  <img
                    src="../assets/img/paw.png"
                    style="height:40px; width:40px; margin-right: 10px;"
                  />
                </div>
                <div v-else>
                  <img
                    :src="selectedPost.profileimg"
                    style="height:40px; width:40px; margin-right: 10px;"
                  />
                </div>
              </div>
              <v-list-item-content style="padding:0;">
                <v-list-item-title class="headline" style="font-size: 1em !important; margin:0">{{
                  selectedPost.nickname
                }}</v-list-item-title>
                <v-list-item-subtitle>{{ selectedPost.modifiedDate }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </router-link>
        </v-col>
        <v-col cols="12" sm="4" md="8" style="margin: 0;padding: 0;">
          <v-card-actions>
            <v-spacer></v-spacer>
            <div v-if="userNickname === selectedPost.nickname">
              <v-btn color="blue darken-1" text>
                <EditPost :selectedPost="selectedPost" :realContent="realContent" @post="postUpdate"
              /></v-btn>
            </div>
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
              <td scope="col" colspan="20" v-html="usercontents"></td>
              <!-- {{ this.usercontents }} -->
            </v-card-text>

            <v-item v-for="(hash, i) in selectedPost.hashtags" :key="i">
              <router-link
                :to="{
                  name: 'tagname',
                  params: {
                    tagname: hash,
                  },
                }"
                style="color: rgb(34, 136, 150);   text-decoration: none;"
              >
                <v-chip active-class="purple--text">
                  {{ hash }}
                </v-chip>
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
            <div v-if="items.length >= 1" style="height: 150px; overflow-x: auto;">
              <v-divider></v-divider>
              <v-list three-line v-for="(item, index) in items" :key="index">
                <router-link
                  :to="{
                    name: 'followdetail',
                    params: { nickname: selectedPost.nickname },
                  }"
                  style="color: rgb(34, 136, 150);   text-decoration: none;"
                >
                  <v-list-item>
                    <!-- 댓글 작성자 프로필 사진 -->
                    <div style="text-align: center;">
                      <div v-if="!item.user.img">
                        <img
                          src="../assets/img/paw.png"
                          style="height:20px; width:20px; margin-right: 10px;"
                        />
                      </div>
                      <div v-else>
                        <img
                          :src="item.user.img"
                          style="height:20px; width:20px; margin-right: 10px;"
                        />
                      </div>
                    </div>
                    <v-list-item-content>
                      <v-list-item-title>
                        {{ item.user.nickname }}
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        {{ item.content }}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
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
        nickname: sessionStorage.getItem('nickname'),
        contents: '',
      },
      dialog: false,
      items: [],
      // 좋아요 유무 체크
      likeCheck: null,
      likeList: [],
      likeCnt: this.selectedPost.likes,
      userNickname: '',
      usercontents: this.selectedPost.contents,
    };
  },
  beforeCreate() {
    console.log('beforeCreate :');
  },

  created() {
    console.log('create :');

    this.pid = this.selectedPost;
    axios.get(HOST + 'auth/posts/comment/' + this.selectedPost.pid).then(res => {
      // console.log(this.selectedPost);
      this.items = res.data;
    });
    this.realContent = this.selectedPost.contents;
    // 좋아요 체크하기 위한
    this.userNickname = sessionStorage.getItem('nickname');
    axios
      .get(HOST + 'api/posts/like/likeslist/' + this.selectedPost.pid)
      .then(res => {
        console.log(res);
        this.likeList = res.data;
        console.log(this.likeList);
        if (this.likeList.includes(this.userNickname)) {
          this.likeCheck = true;
        } else {
          this.likeCheck = false;
        }
      })
      .catch(err => console.error(err));
  },
  beforeMount() {
    console.log('beforeMount :');
  },
  mounted() {
    console.log('mounted :');
  },
  beforeUpdate() {
    console.log('beforeUpdate :');
  },
  updated() {
    console.log('updated :');
  },
  beforeDestroy() {
    console.log('beforeDestroy :');
  },
  destroyed() {
    console.log('destroyed :');
  },

  watch: {
    selectedPost: function() {
      this.pid = this.selectedPost;
      axios.get(HOST + 'auth/posts/comment/' + this.selectedPost.pid).then(res => {
        // console.log(this.selectedPost);
        this.items = res.data;
      });
      this.realContent = this.selectedPost.contents;
      // 좋아요 체크하기 위한
      this.userNickname = sessionStorage.getItem('nickname');
      axios
        .get(HOST + 'api/posts/like/likeslist/' + this.selectedPost.pid)
        .then(res => {
          console.log(res);
          this.likeList = res.data;
          console.log(this.likeList);
          if (this.likeList.includes(this.userNickname)) {
            this.likeCheck = true;
          } else {
            this.likeCheck = false;
          }
        })
        .catch(err => console.error(err));
    },
  },

  methods: {
    updateContent(text) {
      this.realContent = text;
    },
    // 댓글 리스트
    reviewSubmit() {
      axios.post(HOST + 'auth/posts/comment', this.review).then(res => {
        console.log(res.data);
        this.items = res.data;
        console.lvog(this.items);
        this.review.contents = null;
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
          console.log(res);
          this.likeCheck = res.data;
          console.log(this.likeCheck);
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
        console.log(res);
        //alert(res.data.contents);
        this.usercontents = res.data.contents;
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
