<template>
  <div>
    <div class="grid-layout" v-for="(word, i) in words" :key="i">
      <v-img class="grid-item" :src="word"> </v-img>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
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
        this.words.push(
          'https://source.unsplash.com/random/' + Math.floor(600 + Math.random() * 100),
        );
        if (this.bottomVisible()) {
          this.addWord();
        }
      }, 100);
    },
  },
};
</script>

<style scoped>
.grid-layout {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-gap: 15px;
  grid-auto-rows: minmax(200px, auto);
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
  grid-row-end: span 4;
}
</style>
