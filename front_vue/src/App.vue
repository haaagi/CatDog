<template>
  <div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <aside id="fh5co-aside" role="complementary" class="border js-fullheight">
      <h1 id="fh5co-logo">
        <a href="/"
          ><center><img src="../src/assets/logo.png" style="width:180px" /></center
        ></a>
      </h1>
      <nav id="fh5co-main-menu" role="navigation">
        <ul>
          <li><a href="/main">Home</a></li>
          <li><a href="/userdetail">Blog</a></li>
          <li><a href="/info">강아지 소개</a></li>
          <li><a href="about.html">About</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
          <li><a href="contact.html">Contact</a></li>
        </ul>
      </nav>
    </aside>

    <div id="fh5co-main"><router-view /></div>

    <Posting />
  </div>
</template>

<script>
// import Board from './views/Board';
import Posting from './views/Posting';
import { mapState, mapGetters, mapActions } from 'vuex';
export default {
  computed: {
    ...mapState(['start']),
    ...mapGetters(['isLoggedIn', 'isStart']),
  },
  name: 'App',
  methods: {
    ...mapActions(['callStart', 'chkLogin', 'chkStart']),
  },
  created() {
    this.$store.dispatch('chkLogin');
    this.$store.dispatch('chkStart');
    // this.temp2 = this.$store.dispatch('callStart');
  },

  components: { Posting },

  data: () => ({}),
  // comments: { board },
};
</script>
<style scoped></style>
