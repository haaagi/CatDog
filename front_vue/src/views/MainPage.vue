<template>
  <div>
    <Banner />

    <v-container class="grid-layout">
      <v-img
        class="grid-item"
        :class="[word.tag > 0.8 ? (word.tag > 0.9 ? 'span-3' : 'span-2') : '']"
        v-for="(word, i) in words"
        :key="i"
        :src="word.resource"
      />
    </v-container>
  </div>
</template>

<script>
import Banner from '../components/Banner.vue';

export default {
  data: () => ({
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
    this.addWord();
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
        this.words.push({
          resource: 'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
          tag: Math.random(),
        });
        if (this.bottomVisible()) {
          this.addWord();
        }
      }, 10);
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
