<template>
  <div>
    <Banner />
    <!-- hover 도즈언2  -->

    <div class="container" v-for="(selectedPost, i) in postList" :key="i">
      <img :src="selectedPost.img" :alt="selectedPost.nickname" class="image" style="width:100%" />
      <div class="middle">
        <div class="text">
          {{ selectedPost.contents }} <ModalPost :selectedPost="selectedPost" />
        </div>
      </div>
    </div>
    <!-- hover 도즈언 끝  -->

    <!-- 도즈언1  -->
    <!-- <v-container class="justify-center" style="margin-left: 20px;">
      <v-col grid-list-4>
        <v-layout row align-center="">
          <v-card
            class="mx-auto"
            color="grey lighten-4"
            max-width="350"
            v-for="(selectedPost, i) in postList"
            :key="i"
            style="margin: 5px;"
          >
            <v-img
              :aspect-ratio="16 / 9"
              height="350"
              width="350"
              :src="selectedPost.img"
              :alt="selectedPost.nickname"
            >
            </v-img>
            <v-card-text class="pt-6" style="position: relative;">
              <v-btn absolute color="orange" class="white--text" fab large right top>
                <ModalPost :selectedPost="selectedPost" />
              </v-btn>

              <h3 class="display-1 font-weight-light orange--text mb-2">
                {{ selectedPost.contents }}
              </h3>
              <div class="font-weight-light title mb-2">
                {{ selectedPost.nickname }}
              </div>
            </v-card-text>
          </v-card>
        </v-layout>
      </v-col>
    </v-container> -->

    <!-- 도즈언 파트 끝  -->

    <!-- 오빠 코드  -->
    <!-- <v-col grid-list-4>
      <v-layout row>
        <v-card v-for="(selectedPost, i) in postList" :key="i" class="d-inline-block mx-auto">
          <v-col>
            <v-row justify="space-between">
              <v-col cols="auto">
                <v-img
                  height="300"
                  width="300"
                  :src="selectedPost.img"
                  :alt="selectedPost.nickname"
                ></v-img>
              </v-col>

              <v-col cols="auto" class="text-center pl-0">
                <v-row class="flex-column ma-0 fill-height" justify="center">
                  <v-col class="px-0">
                    <ModalPost :selectedPost="selectedPost" />
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-col>
        </v-card>
      </v-layout>
    </v-col> -->
  </div>
</template>

<script>
const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');
import Banner from '../components/Banner.vue';
import ModalPost from '../components/ModalPost';

export default {
  components: { Banner, ModalPost },
  props: {
    selectedPost: Object,
  },
  data: () => ({
    postList: [],
    tempList: null,
    bottom: false,
    words: [],
    testIndex: 0,

    // 도즈언 데이터 부분
  }),

  watch: {
    bottom(bottom) {
      if (bottom) {
        this.addWord();
      }
    },
  },
  created() {
    window.addEventListener('scroll', () => {
      this.bottom = this.bottomVisible();
    });
    // axios.get(HOST + 'api/posts/list').then(res => {
    //   console.log(res);
    //   this.postList = res.data;
    //   sessionStorage.setItem('post', this.postList);
    //   this.tempList = this.postList.data;
    // });
    // this.addWord();
  },
  mounted() {
    axios.get(HOST + 'api/posts/list').then(res => {
      // console.log(res);
      // console.log(res.data);
      this.postList = res.data;
      sessionStorage.setItem('post', this.postList);
      //this.addWord();
    });
  },
  methods: {
    makeWord({ image }) {
      console.log(image);
      this.words.push({
        resource: image,
        tag: Math.random(),
      });
    },
    bottomVisible() {
      const docEl = document.documentElement;
      const visibleHeight = docEl.clientHeight;
      const pageHeight = docEl.scrollHeight;
      const scrolled = window.scrollY;
      const reachedBottom = visibleHeight + scrolled >= pageHeight - 30;
      return reachedBottom || pageHeight < visibleHeight;
    },
    addWord() {
      this.words.push({
        resource: this.postList,
        tag: Math.random(),
      });
      //   setTimeout(() => {
      //     console.log(this.postList);

      //     if (this.bottomVisible()) {
      //       this.addWord();
      //     }
      //   }, 19);
    },
  },
};
</script>

<style scoped>
/* 오빠가 한 부분 */
/* .grid-layout {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 15px;
  grid-auto-flow: dense;
  padding: 15px;
}
.grid-item {
  padding: 1rem;
  border-radius: 5px;
}
.span-2 {
  grid-column-end: span 2;
  grid-row-end: span 2;
}
.span-3 {
  grid-column-end: span 3;
  grid-row-end: span 3;
} */

/* 도전1 하다가 앙대면 지우기  */
/* .v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
} */

/* 도전 2 */
.container {
  position: relative;
  width: 50%;
}

.image {
  opacity: 1;
  display: block;
  width: 100%;
  height: auto;
  transition: 0.5s ease;
  backface-visibility: hidden;
}

.middle {
  transition: 0.5s ease;
  opacity: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  text-align: center;
}

.container:hover .image {
  opacity: 0.3;
}

.container:hover .middle {
  opacity: 1;
}

.text {
  color: black;
  font-size: 16px;
  padding: 16px 32px;
}
</style>
