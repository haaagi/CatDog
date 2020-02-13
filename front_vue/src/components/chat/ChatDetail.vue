<template>
  <div id="chat">
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
      <li class="list-group-item" v-for="message in messages" :key="message">
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul>
    <div></div>
  </div>
</template>

<script>
import axios from 'axios';
import sock from 'sockjs-client';
import Stomp from 'vue-stomp';
var ws = Stomp.over(sock);
var reconnect = 0;
export default {
  name: 'chat',
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
      axios.get('/chat/room/' + this.roomId).then(response => {
        this.room = response.data;
      });
    },
    sendMessage: function() {
      ws.send(
        '/pub/chat/message',
        {},
        JSON.stringify({
          type: 'TALK',
          roomId: this.roomId,
          sender: this.sender,
          message: this.message,
        }),
      );
      this.message = '';
    },
    recvMessage: function(recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == 'ENTER' ? '[알림]' : recv.sender,
        message: recv.message,
      });
    },
    connect() {
      // pub/sub event
      ws.connect(
        {},
        () => {
          ws.subscribe('/sub/chat/room/' + this.$data.roomId, function(message) {
            var recv = JSON.parse(message.body);
            this.recvMessage(recv);
          });
          ws.send(
            '/pub/chat/message',
            {},
            JSON.stringify({ type: 'ENTER', roomId: this.$data.roomId, sender: this.$data.sender }),
          );
        },
        () => {
          if (reconnect++ <= 5) {
            setTimeout(function() {
              console.log('connection reconnect');
              // eslint-disable-next-line no-undef
              sock = new SockJS('/ws-stomp');
              ws = Stomp.over(sock);
              this.connect();
            }, 10 * 1000);
          }
        },
      );
    },
  },
};
</script>
