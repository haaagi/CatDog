<template>
  <v-app style="background-color:whitesmoke">
    <v-container>
      <v-card width="500" class="mx-auto">
        <v-system-bar color="amber darken-1" dark>
          <v-spacer></v-spacer>

          <v-btn to="/main" icon>
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-system-bar>

        <v-app-bar dark color="amber">
          <v-toolbar-title>CAT&DOG 채팅방</v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-container id="scroll-target" style="max-height: 500px" class="overflow-y-auto">
          <v-row dense v-scroll:#scroll-target="onScroll">
            <v-textarea
              style="font-family:fantasy"
              height="500"
              v-model="textarea"
              disabled
              auto-grow
            >
            </v-textarea>
          </v-row>
        </v-container>
        <v-container>
          <v-row cols="6">
            <v-text-field @keyup.enter="sendMessage" type="text" v-model="message"> </v-text-field>
            <v-btn @click="sendMessage">보내기</v-btn>
          </v-row>
        </v-container>
      </v-card>
      <!-- <md-field class="page-container">
    <md-app>
      <md-app-toolbar class="md-primary">
        <md-field class="md-toolbar-row">
          <span class="md-title">My Chat App</span>
        </md-field>
      </md-app-toolbar>
      <md-app-content>
        <md-field>
          <label>Message</label>
          <md-textarea v-model="textarea" disabled v-auto-scroll-bottom></md-textarea>
        </md-field>
        <md-field>
          <label>Your Message</label>
          <md-input v-model="message"></md-input>
          <md-button class="md-primary md-raised" @click="sendMessage">Submit</md-button>
        </md-field>
      </md-app-content>
    </md-app>
  </md-field> -->
    </v-container>
  </v-app>
</template>
<script>
export default {
  name: 'Chat',
  created() {
    this.$socket.on('chat', data => {
      console.log(data);
      this.textarea += data.sender + ':    ' + data.message + '\n';
    });
  },
  data() {
    return {
      offsetTop: 0,

      textarea: '',
      message: '',
      sender: '',
    };
  },
  methods: {
    onScroll(e) {
      this.offsetTop = e.target.scrollTop;
    },
    sendMessage() {
      const userNickname = sessionStorage.getItem('nickname');
      this.$socket.emit('chat', {
        sender: userNickname,
        message: this.message,
      });
      this.textarea += userNickname + ':' + this.message + '\n';
      this.message = '';
    },
  },
};
</script>
<style scoped>
/* .md-app {
  height: 800px;
  border: 1px solid rgba(#000, 0.12);
}
.md-textarea {
  height: 300px;
} */

/* 새로 붙이는거 */
</style>
