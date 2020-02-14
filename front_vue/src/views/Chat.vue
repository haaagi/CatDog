<template>
  <div>
    <div class="container" id="app" v-cloak>
      <div class="row">
        <div class="col-md-12">
          <h3>채팅방 리스트</h3>
        </div>
      </div>
      <div class="input-group">
        <div class="input-group-prepend">
          <label class="input-group-text">방제목</label>
        </div>
        <input type="text" class="form-control" v-model="room_name" v-on:keyup.enter="createRoom" />
        <div class="input-group-append">
          <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
        </div>
      </div>
      <ul class="list-group">
        <li
          class="list-group-item list-group-item-action"
          v-for="item in chatrooms"
          v-bind:key="item.roomId"
          v-on:click="enterRoom(item.roomId)"
        >
          {{ item.name }}
        </li>
      </ul>
    </div>
  </div>
</template>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>
const axios = require('axios');
const HOST = process.env.VUE_APP_SERVER_HOST;
export default {
  data: function() {
    return {
      room_name: '',
      chatrooms: [],
    };
  },
  created() {
    this.findAllRoom();
  },
  methods: {
    findAllRoom: function() {
      axios.get(HOST + '/chat/rooms').then(response => {
        this.chatrooms = response.data;
      });
    },
    createRoom: function() {
      if ('' === this.room_name) {
        alert('방 제목을 입력해 주십시요.');

        return;
      } else {
        var params = new URLSearchParams();
        params.append('name', this.room_name);
        axios.post(HOST + '/chat/room', params).then(response => {
          alert(response.data.name + '방 개설에 성공하였습니다.');
          this.room_name = '';
          this.findAllRoom();
        });
      }
    },
    enterRoom: function(roomId) {
      var sender = prompt('대화명을 입력해 주세요.');
      if (sender != '') {
        localStorage.setItem('wschat.sender', sender);
        localStorage.setItem('wschat.roomId', roomId);
        location.href = '/chat/detail/' + roomId;
      }
    },
  },
};
</script>

<style scoped></style>
