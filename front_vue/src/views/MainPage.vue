<template>
  <div>
    <Banner />
    <v-container class="grid-layout">
      <v-img
        class="grid-item"
        :class="[word.tag > 0.75 ? 'span-2' : '']"
        v-for="(word, index) in words"
        :key="word.id"
        :src="word.resource"
        @click="onclick(index)"
      >
      </v-img>
      <v-dialog v-model="modal">
        <ModalPost v-bind:selectedPost="post" />
      </v-dialog>
    </v-container>

    <Hint v-if="total <= Index" />
  </div>
</template>
<script>
import API from '../plugins/api';
import Banner from '../components/Banner.vue';
import ModalPost from '../components/ModalPost.vue';
import Hint from '../components/hint.vue';
export default {
  components: { Banner, ModalPost, Hint },
  props: {
    selectedPost: Object,
  },
  data: () => ({
    bottom: false,
    words: [],
    Index: 0,
    total: 0,
    postList: [],
    test: 'test',
    flag: false,
    modal: false,
    post: [],
  }),
  watch: {
    bottom(bottom) {
      if (bottom) {
        this.addWord();
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', () => {
      this.bottom = this.bottomVisible();
    });
    this.addWord();
  },
  created() {
    API.get('api/posts/list/' + sessionStorage.getItem('nickname')).then(res => {
      this.postList = res.data;
      this.flag = true;
      this.total = this.postList.length;
      if (this.total > this.Index)
        this.words.push({
          resource: this.postList[this.Index++].img,
          tag: Math.random(),
        });
      this.words.push({
        resource: this.postList[this.Index++].img,
        tag: Math.random(),
      });
      this.words.push({
        resource: this.postList[this.Index++].img,
        tag: Math.random(),
      });
    });
  },
  methods: {
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
        if (this.total > this.Index)
          this.words.push({
            resource: this.postList[this.Index++].img,
            tag: Math.random(),
          });

        if (this.bottomVisible()) {
          this.addWord();
        }
      }, 10);
    },
    onclick(index) {
      this.modal = true;
      this.post = this.postList[index];
    },
  },
};
</script>
<style scoped>
.grid-layout {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
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
.feed-grid {
  padding-right: 150px;
  padding-left: 150px;
}
.container:before,
.container:after {
  content: ' ';
  grid-column-end: span 3;
  grid-row-end: span 1;
  height: 10px;
}
</style>
