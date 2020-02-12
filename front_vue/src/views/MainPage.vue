<template>
  <div>
    <Banner />

    <v-container grid-list-4>
      <v-layout row>
        <v-card v-for="(selectedPost, i) in postList" :key="i" class="d-inline-block mx-auto">
          <v-container>
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
                    <v-btn icon class="ma-2" text color="blue lighten-2">
                      <ModalPost :selectedPost="selectedPost" />
                    </v-btn>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </v-layout>
    </v-container>
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
.grid-layout {
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
}
</style>
