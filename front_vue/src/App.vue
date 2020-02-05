<template>
  <v-app id="keep">
    <div v-if="!isStart">
      <router-link to="/login">
        <div class="home">
          <div class="box">
            <div class="title">
              <span class="block"></span>
              <div @click="callStart()">
                <h1>CAT & DOG<span></span></h1>
              </div>
            </div>

            <div class="role">
              <div class="block"></div>
              <p>MEET MY FRIEND</p>
            </div>
          </div>
        </div>
      </router-link>
    </div>
    <div v-if="isStart">
      <div v-if="isLoggedIn">
        <v-app-bar app clipped-left hide-on-scroll color="amber">
          <v-app-bar-nav-icon @click="drawer = !drawer" />
          <v-toolbar-title style="width: 300px; padding-left: 0px;">
            <v-img src="./assets/logo2.png" alt="logo" max-height="50" contain />
          </v-toolbar-title>
          <v-spacer /><v-spacer />
          <v-text-field
            style="width: 300px;"
            placeholder="Search..."
            single-line
            color="black"
            prepend-inner-icon="search"
            hide-details
          ></v-text-field>
          <v-spacer />
          <v-btn icon> <v-icon>apps</v-icon> </v-btn>
          <v-btn icon> <v-icon>chat_bubble</v-icon> </v-btn>
          <v-btn icon> <v-icon>mdi-bell</v-icon> </v-btn>
          <v-btn icon> <v-icon>mdi-login</v-icon> </v-btn>
          <v-btn icon> <v-icon>mdi-logout</v-icon> </v-btn>
        </v-app-bar>
      </div>
      <div v-if="isLoggedIn">
        <v-navigation-drawer v-model="drawer" app clipped color="grey lighten-4">
          <v-list dense class="grey lighten-4">
            <template v-for="(item, i) in items">
              <v-row v-if="item.heading" :key="i" align="center">
                <v-col cols="6">
                  <v-subheader v-if="item.heading">
                    {{ item.heading }}
                  </v-subheader>
                </v-col>
                <v-col cols="6" class="text-right">
                  <v-btn small text>edit</v-btn>
                </v-col>
              </v-row>
              <v-divider v-else-if="item.divider" :key="i" dark class="my-4" />
              <v-list-item v-else :key="i" link>
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title class="grey--text">
                    {{ item.text }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </v-navigation-drawer>
      </div>

      <v-content style="height:2000px"> <router-view /> </v-content>
      <v-content>
        <v-footer absolute color="amber" class="white--text">
          <v-card flat tile class="amber lighten-1 white--text text-center">
            <v-card-text class="white--text pt-0">
              Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus
              commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed
              at orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et
              faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices,
              cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut
              orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur
              ridiculus mus.
            </v-card-text>

            <v-divider />

            <v-card-text class="white--text">
              <strong>Vuetify</strong>
            </v-card-text>
          </v-card>
        </v-footer>
      </v-content>
    </div>
  </v-app>
</template>

<script>
// import Board from './views/Board';
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

  components: {},

  data: () => ({
    temp2: false,
    drawer: null,
    items: [
      { icon: 'lightbulb_outline', text: 'Notes' },
      { icon: 'touch_app', text: 'Reminders' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'add', text: 'Create new label' },
      { divider: true },
      { icon: 'archive', text: 'Archive' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'keyboard', text: 'Keyboard shortcuts' },
    ],
  }),
  // comments: { board },
};
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css?family=Lato:300,400|Poppins:300,400,800&display=swap');
* {
  margin: 0;
  padding: 0;
}

body,
html {
  overflow: hidden;
}

router-link .home {
  text-decoration: none;
}

.home {
  width: 100%;
  height: 100vh;
  background: #232323;
  display: flex;
  justify-content: center;
  align-items: center;
}
.home .box {
  width: 250px;
  height: 250px;
  position: relative;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.home .box .title {
  width: 100%;
  position: relative;
  display: flex;
  align-items: center;
  height: 50px;
}
.home .box .title .block {
  width: 0%;
  height: inherit;
  background: #ffb510;
  position: absolute;
  animation: mainBlock 2s cubic-bezier(0.74, 0.06, 0.4, 0.92) forwards;
  display: flex;
}
.home .box .title h1 {
  font-family: 'Poppins';
  color: #fff;
  font-size: 32px;
  -webkit-animation: mainFadeIn 2s forwards;
  -o-animation: mainFadeIn 2s forwards;
  animation: mainFadeIn 2s forwards;
  animation-delay: 1.6s;
  opacity: 0;
  display: flex;
  align-items: baseline;
  position: relative;
}
.home .box .title h1 span {
  width: 0px;
  height: 0px;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  background: #ffb510;
  -webkit-animation: load 0.6s cubic-bezier(0.74, 0.06, 0.4, 0.92) forwards;
  animation: popIn 0.8s cubic-bezier(0.74, 0.06, 0.4, 0.92) forwards;
  animation-delay: 2s;
  margin-left: 5px;
  margin-top: -10px;
  position: absolute;
  bottom: 13px;
  right: -12px;
}
.home .box .role {
  width: 100%;
  position: relative;
  display: flex;
  align-items: center;
  height: 30px;
  margin-top: -10px;
}
.home .box .role .block {
  width: 0%;
  height: inherit;
  background: #e91e63;
  position: absolute;
  animation: secBlock 2s cubic-bezier(0.74, 0.06, 0.4, 0.92) forwards;
  animation-delay: 2s;
  display: flex;
}
.home .box .role p {
  animation: secFadeIn 2s forwards;
  animation-delay: 3.2s;
  opacity: 0;
  font-weight: 400;
  font-family: 'Lato';
  color: #ffffff;
  font-size: 12px;
  text-transform: uppercase;
  letter-spacing: 5px;
}

@keyframes mainBlock {
  0% {
    width: 0%;
    left: 0;
  }
  50% {
    width: 100%;
    left: 0;
  }
  100% {
    width: 0;
    left: 100%;
  }
}
@keyframes secBlock {
  0% {
    width: 0%;
    left: 0;
  }
  50% {
    width: 100%;
    left: 0;
  }
  100% {
    width: 0;
    left: 100%;
  }
}
@keyframes mainFadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes popIn {
  0% {
    width: 0px;
    height: 0px;
    background: #e9d856;
    border: 0px solid #ddd;
    opacity: 0;
  }
  50% {
    width: 10px;
    height: 10px;
    background: #e9d856;
    opacity: 1;
    bottom: 45px;
  }
  65% {
    width: 7px;
    height: 7px;
    bottom: 0px;
    width: 15px;
  }
  80% {
    width: 10px;
    height: 10px;
    bottom: 20px;
  }
  100% {
    width: 7px;
    height: 7px;
    background: #e9d856;
    border: 0px solid #222;
    bottom: 13px;
  }
}
@keyframes secFadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 0.5;
  }
}

@keyframes top {
  0% {
    opacity: 0;
    bottom: -80px;
  }
  100% {
    opacity: 1;
    bottom: 0px;
  }
}
@keyframes icon {
  0% {
    opacity: 0;
    transform: scale(0);
  }
  50% {
    opacity: 1;
    transform: scale(1.3) rotate(-2deg);
  }
  100% {
    opacity: 1;
    bottom: 0px;
  }
}
#keep .v-navigation-drawer__border {
  display: none;
}
</style>
