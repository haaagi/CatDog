<template>
  <div>
    <center>
      <form style="padding-bottom:100px; padding-top:100px; background-color:#228896;">
        <input
          type="text"
          name="keyword"
          style=" border-style:solid; border-color:white; font-size:25px; width:70%; height:50px; font-size: 22px;
    font-weight: 600;    text-align: center;"
          :value="keyword"
        />
        <button type="submit" formaction="/search">
          <v-icon x-large style="color:white;">mdi-magnify</v-icon>
        </button>
      </form>

      <v-btn-toggle v-model="tog" tile color="deep-purple accent-3" group style="width: 90%;">
        <v-btn style="width: 50%;">
          <v-icon>USER</v-icon>
        </v-btn>

        <v-btn style="width: 50%;">
          <v-icon>TAG</v-icon>
        </v-btn>
      </v-btn-toggle>
      <div v-if="tog === 0" style="margin-top: 40px;">
        <h2>검색된 유저 수: {{ data.search_nickname.length }}</h2>
        <v-container>
          <div class="column" v-for="(item, i) in data.search_nickname" :key="i">
            <router-link
              :to="{
                name: 'followdetail',
                params: {
                  nickname: item.nickname,
                },
              }"
            >
              <div class="card">
                <tr>
                  <td v-if="item.img === null" rowspan="2" style="width:120px">
                    <center>
                      <img src="https://image.flaticon.com/icons/svg/1077/1077063.svg" alt="" />
                    </center>
                  </td>
                  <td v-else rowspan="2" style="width:120px">
                    <center><img :src="item.img" alt="" /></center>
                  </td>
                  <td style="font-size:50px; align-content:center;">
                    {{ item.nickname }}
                  </td>
                </tr>
                <tr>
                  <td>{{ item.name }}</td>
                </tr>
              </div>
            </router-link>
          </div>
        </v-container>
      </div>
      <div v-else style="margin-top: 40px;">
        <h2>검색된 태그 수: {{ data.search_content.length }}</h2>
        <v-container>
          <div class="column" v-for="(item, i) in data.search_content" :key="i">
            <router-link
              :to="{
                name: 'tagname',
                params: {
                  tagname: item.content,
                },
              }"
            >
              <div class="card">
                <tr>
                  <td rowspan="2" style="width:120px">
                    <center>
                      <h1 style="font-size: 60px;">
                        #
                      </h1>
                    </center>
                  </td>
                  <td style="font-size:50px; align-content:center;">
                    {{ item.content }}
                  </td>
                </tr>
                <tr>
                  <td>게시물 {{ item.content_cnt }}</td>
                </tr>
              </div>
            </router-link>
          </div>
        </v-container>
      </div>
    </center>
  </div>
</template>

<script>
const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');

export default {
  data() {
    return {
      keyword: 'no name',
      data: '',
      tog: 0,
    };
  },
  mounted() {
    this.keyword = this.$route.query.keyword;
    axios
      .get(HOST + 'auth/user/search/' + this.$route.query.keyword)
      .then(res => {
        // let list = res.data;
        // this.userInfo = list;
        this.data = res.data;
        console.log(this.data);
        // const follower = sessionStorage.getItem('nickname');
        // console.log(follower, this.$route.params.nickname);
      })
      .catch(err => console.error(err));
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: auto auto;
  grid-template-rows: auto auto;
  grid-gap: 10px;
  padding: 10px;
}

img {
  border-radius: 50%;
  border-style: none;
  height: 100px;
  width: 100px;
}
h1 {
  font-size: 90px;
  margin: 0;
}
/* /////////////////////////////////////////// */

* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 50%;
  padding: 0 10px 10px 10px;
}

/* Remove extra left and right margins, due to padding */
.row {
  margin: 0 -5px;
}

/* Clear floats after the columns */
.row:after {
  content: '';
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  background-color: white;
  text-align: start;
}
</style>
