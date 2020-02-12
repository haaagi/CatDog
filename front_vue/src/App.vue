<template>
  <div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <aside id="fh5co-aside" role="complementary" class="border js-fullheight" style="z-index:1">
      <h1 id="fh5co-logo">
        <a href="/"
          ><center><img src="../src/assets/logo.png" style="width:180px" /></center
        ></a>
      </h1>
      <form>
        <fieldset>
          <input type="search" /><button type="submit"><i class="fa fa-search"></i></button>
        </fieldset>
      </form>
      <nav id="fh5co-main-menu" role="navigation">
        <ul>
          <li><a href="/main">Home</a></li>
          <li><a href="/userdetail">Blog</a></li>
          <li><a href="/info">강아지 소개</a></li>
          <li><a href="/userdetail">Userdeail</a></li>
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
    ...mapActions(['callStart', 'chkLogin', 'chkStart']),
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
<style scoped>
fieldset {
  position: relative;
  display: inline-block;
  padding: 0 0 0 40px;
  background: #fff;
  border: none;
  border-radius: 5px;
}

input,
button {
  position: relative;
  width: 200px;
  height: 50px;
  padding: 0;
  display: inline-block;
  float: left;
}

input {
  color: #666;
  z-index: 2;
  border: 0 none;
}
input:focus {
  outline: 0 none;
}
input:focus + button {
  -webkit-transform: translate(0, 0);
  -ms-transform: translate(0, 0);
  transform: translate(0, 0);
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
}
input:focus + button .fa {
  -webkit-transform: translate(0px, 0);
  -ms-transform: translate(0px, 0);
  transform: translate(0px, 0);
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  color: #fff;
}

button {
  z-index: 1;
  width: 50px;
  border: 0 none;
  background: #ceb980;
  cursor: pointer;
  border-radius: 0 5px 5px 0;
  -webkit-transform: translate(-50px, 0);
  -ms-transform: translate(-50px, 0);
  transform: translate(-50px, 0);
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
}

.fa-search {
  font-size: 1.4rem;
  color: #29abe2;
  z-index: 3;
  top: 25%;
  -webkit-transform: translate(-190px, 0);
  -ms-transform: translate(-190px, 0);
  transform: translate(-190px, 0);
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition: all 0.1s ease-in-out;
  transition: all 0.1s ease-in-out;
}
</style>
