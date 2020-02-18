<template>
  <div>
    <div class="card">
      <tr>
        <td rowspan="2" style="width:120px">
          <center>
            <h1 style="font-size: 60px;">
              #
            </h1>
          </center>
        </td>
        <td style="font-size:50px; align-content:center;">
          {{ this.$route.params.tagname }}
        </td>
      </tr>
      <tr>
        <td>게시물 수: {{ length }}</td>
      </tr>
    </div>

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
  </div>
</template>
<script>
const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');
import ModalPost from '../components/ModalPost.vue';
export default {
  components: { ModalPost },
  props: {
    selectedPost: Object,
  },
  data: () => ({
    bottom: false,
    words: [],
    Index: 0,
    postList: [],
    test: 'test',
    flag: false,
    modal: false,
    post: [],
    length: 0,
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
    axios.get(HOST + 'hashtag/' + this.$route.params.tagname).then(res => {
      this.postList = res.data;
      this.length = this.postList.length;
      this.flag = true;
      this.words.push({
        resource: this.postList[this.Index++].img,
        //resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
        tag: Math.random(),
      });
      this.words.push({
        resource: this.postList[this.Index++].img,
        //resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
        tag: Math.random(),
      });
      this.words.push({
        resource: this.postList[this.Index++].img,
        //resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
        tag: Math.random(),
      });
      this.words.push({
        resource: this.postList[this.Index++].img,
        //resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
        tag: Math.random(),
      });
      this.words.push({
        resource: this.postList[this.Index++].img,
        //resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
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
        var len = this.postList.length - this.Index < 5 ? this.postList.length - this.Index : 5;
        for (var i = 0; i < len; i++) {
          this.words.push({
            resource: this.postList[this.Index++].img,
            tag: Math.random(),
          });
        }
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
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  background-color: white;
  text-align: start;
  margin: 90 90 0 0;
  padding: 100px;
}
</style>
