<template>
  <v-container>
    <v-layout style="margin-top:50px;">
      <v-flex>
        <h1>Q&A게시판</h1>
      </v-flex>
      <v-flex style="">
        <router-link to="writeBoard"
          ><v-btn style="float: right; margin-top: 10px;">글 작성하기</v-btn></router-link
        >
      </v-flex>
    </v-layout>
    <v-simple-table height="800px">
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">종류</th>
            <th class="text-left">글 제목</th>
            <th class="text-right">작성자</th>
            <th class="text-right">작성일</th>
          </tr>
        </thead>

        <tbody v-for="post in boardList" :key="post.title">
          <tr
            v-if="!post.type"
            style="background-color:lemonchiffon; font-size: x-large; font-style:italic"
          >
            <td>
              공지사항
            </td>

            <router-link
              :to="{
                name: 'selectedboard',
                params: { post: post },
              }"
              style="color: black; text-decoration: none;"
            >
              <td style="padding-top: 12px">{{ post.title }}</td>
            </router-link>
            <td class="text-right">{{ post.nickname }}</td>
            <td class="text-right">{{ post.modifiedDate }}</td>
          </tr>
          <tr v-else>
            <td>
              Q&A
            </td>

            <router-link
              :to="{
                name: 'selectedboard',
                params: { post: post },
              }"
              style="color: black; text-decoration: none;"
            >
              <td style="padding-top: 12px">{{ post.title }}</td>
            </router-link>
            <td class="text-right">{{ post.nickname }}</td>
            <td class="text-right">{{ post.modifiedDate }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import API from '../plugins/api';
export default {
  props: {
    post: Object,
  },
  data() {
    return {
      boardList: [],
    };
  },
  beforeCreate() {
    API.get('api/board/list').then(res => {
      console.log(res);
      this.boardList = res.data;
    });
  },

  created() {
    console.log('created :');
  },
  beforeMount() {
    console.log('beforeMount :');
  },
  mounted() {
    console.log('mounted :');
    API.get('api/board/list').then(res => {
      console.log(res);
      this.boardList = res.data;
    });
  },
  beforeUpdate() {
    console.log('beforeUpdate :');
  },
  updated() {
    console.log('updated :');
  },
  beforeDestroy() {
    console.log('beforeDestroy :');
  },
  destroyed() {
    console.log('destroyed :');
  },

  // beforeUpdate() {
  //   API.get(HOST + 'api/board/list').then(res => {
  //     console.log(res);
  //     this.boardList = res.data;
  //   });
  // },
};
</script>
<style scoped></style>
