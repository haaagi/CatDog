<template>
  <div>
    <Banner />

    <v-container class="grid-layout">
      <v-img
        class="grid-item"
        :class="span - 3"
        v-for="(post, i) in postList"
        :key="i"
        :src="post.img"
      />
    </v-container>
  </div>
</template>

<script>
const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');
import Banner from '../components/Banner.vue';

export default {
  data: () => ({
    postList: [],
    tempList: null,
    bottom: false,
    words: [],
    testIndex: 0,
  }),
  components: { Banner },
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
    //   console.log(res.data);
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
      this.tempList = this.postList.data;
    });
    this.addWord();
  },
  methods: {
    getPostList() {},
    bottomVisible() {
      const docEl = document.documentElement;
      const visibleHeight = docEl.clientHeight;
      const pageHeight = docEl.scrollHeight;
      const scrolled = window.scrollY;
      const reachedBottom = visibleHeight + scrolled >= pageHeight - 30;
      return reachedBottom || pageHeight < visibleHeight;
    },
    addWord() {
      setTimeout(() => {
        console.log(this.postList);
        this.words.push({
          resource: this.postList,
          tag: Math.random(),
        });
        if (this.bottomVisible()) {
          this.addWord();
        }
      }, 19);
    },
  },
};
</script>

<style scoped>
.grid-layout {
  display: grid;
  grid-template-columns: auto;
  grid-gap: 15px;
  grid-auto-flow: row dense;
  padding: 15px;
  grid-column-end: auto;
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
.feed-grid {
  padding-right: 150px;
  padding-left: 150px;
}
</style>
