<template>
  <div id="roomId">
    <div>
      <h2>{{ room.name }}</h2>
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">내용</label>
      </div>
      <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage" />
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-for="message in messages" v-bind:key="message.message">
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul>
    <div></div>
  </div>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
<script>
const axios = require('axios');
const SockJS = require('sockjs-client');
const Stomp = require('stompjs');
const HOST = process.env.VUE_APP_SERVER_HOST;
var sock = new SockJS('/ws-stomp');
var ws = Stomp.over(sock);
var reconnect = 0;
export default {
  name: 'roomId',
  data: () => ({
    roomId: '',
    room: {},
    sender: '',
    message: '',
    messages: [],
  }),
  created() {
    this.roomId = localStorage.getItem('wschat.roomId');
    this.sender = localStorage.getItem('wschat.sender');
    this.findRoom();
  },

  mounted() {
    this.connect();
  },
  methods: {
    findRoom: function() {
      axios.get(Host + '/chat/room/' + this.roomId).then(response => {
        this.room = response.data;
      });
    },
    sendMessage: function() {
      ws.send(
        Host + '/pub/chat/message',
        {},
        JSON.stringify({
          type: 'TALK',
          roomId: this.roomId,
          sender: this.sender,
          message: this.message,
        }),
      );
      this.recvMessage({ roomId: this.roomId, sender: this.sender, message: this.message });
      this.message = '';
    },
    recvMessage: function(recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == 'ENTER' ? '[알림]' : recv.sender,
        message: recv.message,
      });
    },

    connect: function() {
      ws.connect(
        {},
        function(frame) {
          ws.subscribe(Host + '/sub/chat/room/' + this.$data.roomId, function(message) {
            var recv = JSON.parse(message.body);
            this.recvMessage(recv);
          });
          ws.send(
            Host + '/pub/chat/message',
            {},
            JSON.stringify({ type: 'ENTER', roomId: this.$data.roomId, sender: this.$data.sender }),
          );
        },
        function(error) {
          if (reconnect++ <= 5) {
            setTimeout(function() {
              console.log('connection reconnect');
              sock = new SockJS('/ws-stomp');
              ws = Stomp.over(sock);
              connect();
            }, 10 * 1000);
          }
        },
      );
    },
  },
};
</script>
