<template>
  <div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <aside
      id="fh5co-aside"
      role="complementary"
      class="border js-fullheight"
      style="z-index:1; background-color: whitesmoke; widht "
    >
      <h1 id="fh5co-logo" style="margin-bottom: 1em" v-if="isLoggedIn">
        <a href="/main"
          ><center><img src="../src/assets/logo.png" style="width:180px" /></center
        ></a>
      </h1>
      <h1 v-else>
        <a href="/"
          ><center><img src="../src/assets/logo.png" style="width:180px" /></center
        ></a>
      </h1>
      <!-- 검색창 -->
      <center v-if="isLoggedIn">
        <v-btn rounded style="color: #FFC107; margin-bottom: 30px;" @click="logout"
          ><v-icon>mdi-account </v-icon>Logout</v-btn
        >
        <form style="margin-bottom: 30px">
          <input type="text" name="keyword" style=" border-style: solid; border-color: #228896;" />
          <button type="submit" formaction="/search">
            <v-icon style="color: #228896">mdi-magnify</v-icon>
          </button>
        </form>
      </center>

      <nav id="fh5co-main-menu" role="navigation">
        <ul>
          <div v-if="isLoggedIn">
            <li><a href="/main">Home</a></li>
            <li><a href="/userdetail">my page</a></li>
            <li><a href="/info">introduce dog</a></li>

            <li><a href="board">Board</a></li>
            <li><a href="/chat">Chat</a></li>
            <li><a href="aboutus">About Us</a></li>
          </div>

          <div v-else>
            <router-link to="/login">
              <v-btn rounded style="color: #FFC107;"><v-icon>mdi-account </v-icon>Login</v-btn>
            </router-link>
            <li style="margin-top: 30px;"><a href="aboutus">About Us</a></li>
          </div>

          <div class="fh5co-footer">
            <p><span> Copyright 2020. Cat&Dog Co.</span> <span> All Rights Reserved</span></p>

            <ul>
              <li>
                <a href="https://github.com/ponyong"><i class="icon-phone"></i></a>
              </li>
              <li>
                <a href="https://github.com/daehi4u"><i class="icon-facebook2"></i></a>
              </li>
              <li>
                <a href="https://github.com/haaagi"><i class="icon-twitter2"></i></a>
              </li>
              <li>
                <a href="https://github.com/Han-kiyeon"><i class="icon-instagram"></i></a>
              </li>
              <li>
                <a href="https://github.com/sooya14"><i class="icon-linkedin2"></i></a>
              </li>
            </ul>
          </div>
        </ul>
      </nav>
    </aside>
    <div v-if="check">
      <div id="fh5co-main"><router-view /></div>
    </div>

    <div v-if="isLoggedIn">
      <Posting />
    </div>
  </div>
</template>

<script>
// import Board from './views/Board';

import Posting from './views/Posting';
import { mapState, mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
      chkPosting: 0,
      check: 0,
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
    this.chkPosting = 1;
    this.check = this.$store.dispatch('chkLogin');
    // this.$store.dispatch('chkStart');
    // this.temp2 = this.$store.dispatch('callStart');
  },
  mounted() {
    this.chkPosting = this.$store.start;
  },
  components: { Posting },

  // comments: { board },
};
</script>
<style scoped></style>
