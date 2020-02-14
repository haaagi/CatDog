<template>
  <div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <aside id="fh5co-aside" role="complementary" class="border js-fullheight" style="z-index:1">
      <h1 id="fh5co-logo" style="margin-bottom: 1em">
        <a href="/"
          ><center><img src="../src/assets/logo.png" style="width:180px" /></center
        ></a>
      </h1>
      <center>
        <form style="margin-bottom: 30px">
          <input type="text" name="keyword" style=" border-style: solid; border-color: #228896;" />
          <button type="submit" formaction="/search">
            <v-icon style="color: #228896">mdi-magnify</v-icon>
          </button>
        </form>
      </center>

      <nav id="fh5co-main-menu" role="navigation">
        <ul>
          <li><a href="/main">Home</a></li>
          <li><a href="/userdetail">Blog</a></li>
          <li><a href="/info">강아지 소개</a></li>
          <li><a href="/userdetail">Userdetail</a></li>
          <li><a @click="logout" href="/login">logout</a></li>
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
    <div v-if="check">
      <div id="fh5co-main"><router-view /></div>
    </div>
    <Posting />
  </div>
</template>

<script>
// import Board from './views/Board';

import Posting from './views/Posting';
import { mapState, mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
      check: null,
    };
  },
  computed: {
    ...mapState(['start']),
    ...mapGetters(['isLoggedIn', 'isStart']),
  },
  name: 'App',
  methods: {
    ...mapActions(['callStart', 'chkLogin', 'chkStart', 'logout']),
  },
  created() {
    this.check = this.$store.dispatch('chkLogin');
    this.$store.dispatch('chkStart');
    // this.temp2 = this.$store.dispatch('callStart');
  },
  components: { Posting },

  // comments: { board },
};
</script>
<style scoped></style>
