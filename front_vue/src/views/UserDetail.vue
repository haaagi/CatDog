<template>
  <div>
    <header>
      <div class="container">
        <div class="profile">
          <div class="profile-image">
            <!-- 프로필 이미지  -->
            <div v-if="userInfo.profileimg === null || userInfo.profileimg === ''">
              <img
                style="width: 200px; height: 200px;"
                src="https://image.flaticon.com/icons/svg/1077/1077063.svg"
                :alt="userInfo.nickname"
              />
            </div>
            <div v-else>
              <img
                style="width: 200px; height: 200px;"
                :src="userInfo.profileimg"
                :alt="userInfo.nickname"
              />
            </div>
          </div>

          <!-- 프로필 수정 버튼  -->
          <div class="profile-user-settings">
            <!-- <h1 class="profile-user-name">{{ userInfo.nickname }}</h1>
            <router-link to="/editprofile">
              <button class="btn profile-edit-btn">Edit Profile</button>
            </router-link> -->

            <h1 class="profile-user-name">{{ userInfo.nickname }}</h1>
            <router-link to="/editprofile">
              <v-btn text icon large style="color: rgb(34, 136, 150); margin-left: 10px;">
                <v-icon>mdi-settings</v-icon>
              </v-btn>
            </router-link>
          </div>

          <!-- 포스팅 수 팔로잉 팔로워 버튼 -->
          <div class="profile-stats">
            <ul>
              <!-- 포스팅 카운트 -->
              <li>
                <span class="profile-stat-count"></span>
                <v-row justify="center">
                  <v-dialog scrollable max-width="300px">
                    <template v-slot:activator="{ off }">
                      <v-btn text color="primary" v-on="on">{{ userInfo.post_cnt }} post</v-btn>
                    </template>
                    <v-card> </v-card>
                  </v-dialog>
                </v-row>
              </li>
              <v-layout>
                <li>
                  <span class="profile-stat-count"></span>
                  <v-row justify="center">
                    <v-dialog v-model="dialog" scrollable max-width="300px">
                      <template v-slot:activator="{ on }">
                        <v-btn text color="primary" v-on="on"
                          >{{ userInfo.follower_cnt }}followers</v-btn
                        >
                      </template>
                      <v-card>
                        <v-card-title>Follower List</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 400px;">
                          <v-list subheader>
                            <!-- 팔로워의 상세페이지로 이동하기 @click 설정하기!!  -->
                            <v-list-item
                              v-for="follower in userInfo.followerList"
                              :key="follower.nickname"
                            >
                              <v-list-item-avatar>
                                <div v-if="follower.img === '' || follower.img === null">
                                  <v-avatar color="">
                                    <v-icon>mdi-dog</v-icon>
                                  </v-avatar>
                                </div>
                                <div v-else>
                                  <v-avatar>
                                    <img :src="follower.img" alt="" />
                                  </v-avatar>
                                </div>
                              </v-list-item-avatar>

                              <v-list-item-content>
                                <router-link
                                  :to="{
                                    name: 'followdetail',
                                    params: { nickname: follower.nickname },
                                  }"
                                  style="color: rgb(34, 136, 150);   text-decoration: none;"
                                >
                                  <v-list-item-title>{{ follower.nickname }}</v-list-item-title>
                                </router-link>
                              </v-list-item-content>
                            </v-list-item>
                          </v-list>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                          <v-btn style="color: rgb(34, 136, 150);" text @click="dialog = false"
                            >Close</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-row>
                </li>

                <li>
                  <span class="profile-stat-count"></span>
                  <v-row justify="center">
                    <v-dialog v-model="dialog_f" scrollable max-width="300px">
                      <template v-slot:activator="{ on }">
                        <v-btn text color="primary" v-on="on"
                          >{{ userInfo.following_cnt }} following</v-btn
                        >
                      </template>
                      <v-card>
                        <v-card-title>Following List</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 400px;">
                          <v-list subheader>
                            <!-- 팔로워의 상세페이지로 이동하기 @click 설정하기!!  -->
                            <v-list-item
                              v-for="following in userInfo.followingList"
                              :key="following.nickname"
                            >
                              <v-list-item-avatar>
                                <div v-if="following.img === '' || following.img === null">
                                  <v-avatar>
                                    <v-icon>mdi-dog</v-icon>
                                  </v-avatar>
                                </div>
                                <div v-else>
                                  <v-avatar>
                                    <img :src="following.img" alt="" />
                                  </v-avatar>
                                </div>
                              </v-list-item-avatar>

                              <v-list-item-content>
                                <router-link
                                  :to="{
                                    name: 'followdetail',
                                    params: { nickname: following.nickname },
                                  }"
                                  style="color: rgb(34, 136, 150);   text-decoration: none;"
                                >
                                  <v-list-item-title>{{ following.nickname }}</v-list-item-title>
                                </router-link>
                              </v-list-item-content>
                            </v-list-item>
                          </v-list>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                          <v-btn style="color: rgb(34, 136, 150);" text @click="dialog_f = false"
                            >Close</v-btn
                          >
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-row>
                </li>
              </v-layout>
            </ul>
          </div>

          <div class="profile-bio">
            <p><span class="profile-real-name"></span>{{ userInfo.pr }}</p>
          </div>
        </div>
      </div>
    </header>

    <!-- 포스팅 리스트 -->
    <main>
      <div class="container">
        <div
          class="gallery-item"
          tabindex="0"
          v-for="selectedPost in userInfo.postsList"
          :key="selectedPost.img"
          style="margin-bottom: 15px;"
        >
          <ModalPost :selectedPost="selectedPost" />
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import ModalPost from '../components/ModalPost';
// const HOST = process.env.VUE_APP_SERVER_HOST;
import API from '../plugins/api';
export default {
  name: 'Userdetail',
  components: { ModalPost },
  data() {
    return {
      // 유저 정보
      userInfo: [],

      text: 'center',
      icon: 'justify',
      toggle_none: null,
      toggle_one: 0,
      toggle_exclusive: 2,
      toggle_multiple: [0, 1, 2],

      // Follower button list
      dialogm1: '',
      dialog: false,
      dialog_f: false,
    };
  },
  beforeCreate() {
    console.log('beforeCreate :');
    const userNickname = sessionStorage.getItem('nickname');
    API
      // .get(HOST + 'auth/Mypage/' + userEmail, null, options)
      .get('auth/myPage/' + userNickname)
      .then(res => {
        this.userInfo = res.data;
        console.log(this.userInfo);
        console.log(this.userInfo.postsList);
      })
      .catch(err => console.error(err));
  },

  created() {
    console.log('created :');
  },
  beforeMount() {
    console.log('beforeMount :');
  },
  mounted() {
    console.log('mounted :');
  },
  beforeUpdate() {
    console.log('beforeUpdate :');
  },
  updated() {
    console.log('updated :');
    const userNickname = sessionStorage.getItem('nickname');
    API
      // .get(HOST + 'auth/Mypage/' + userEmail, null, options)
      .get('auth/myPage/' + userNickname)
      .then(res => {
        this.userInfo = res.data;
        console.log(this.userInfo);
        console.log(this.userInfo.postsList);
      })
      .catch(err => console.error(err));
  },
  beforeDestroy() {
    console.log('beforeDestroy :');
  },
  destroyed() {
    console.log('destroyed :');
  },
};
</script>

<style scoped>
/* 이전 버전  */
:root {
  font-size: 10px;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: 'Open Sans', Arial, sans-serif;
  min-height: 100vh;
  background-color: #fafafa;
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
}

.container {
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}

.btn {
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 0;
  cursor: pointer;
}

.btn:focus {
  outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
  position: absolute !important;
  height: 1px;
  width: 1px;
  overflow: hidden;
  clip: rect(1px, 1px, 1px, 1px);
}

.profile {
  padding: 5rem 0;
}

.profile::after {
  content: '';
  display: block;
  clear: both;
}

.profile-image {
  float: left;
  width: calc(33.333% - 1rem);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 3rem;
}

.profile-image img {
  border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
  float: left;
  width: calc(66.666% - 2rem);
}

.profile-user-settings {
  margin-top: 1.1rem;
}

.profile-user-name {
  display: inline-block;
  font-size: 3.2rem;
  font-weight: 300;
}

.profile-edit-btn {
  font-size: 1em;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 2rem;
}

.profile-settings-btn {
  font-size: 1px;
  margin-left: 1rem;
}

.profile-stats {
  margin-top: 2.3rem;
}

.profile-stats li {
  display: inline-block;
  font-size: 1.6rem;
  line-height: 1.5;
  margin-right: 4rem;
  cursor: pointer;
}

.profile-stats li:last-of-type {
  margin-right: 0;
}

.profile-bio {
  font-size: 1.6rem;
  font-weight: 400;
  line-height: 1.5;
  margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
  font-weight: 600;
}

.gallery {
  display: flex;
  flex-wrap: wrap;
  margin: -1rem -1rem;
  padding-bottom: 3rem;
}

.gallery-item {
  position: relative;
  flex: 1 0 22rem;
  margin: 1rem;
  color: #fff;
  cursor: pointer;
}

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
}

.gallery-item-info {
  display: none;
}

.gallery-item-info li {
  display: inline-block;
  font-size: 1.7rem;
  font-weight: 600;
}

.gallery-item-likes {
  margin-right: 2.2rem;
}

.gallery-item-type {
  position: absolute;
  top: 1rem;
  right: 1rem;
  font-size: 2.5rem;
  text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
}

.fa-clone,
.fa-comment {
  transform: rotateY(180deg);
}

.gallery-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.loader {
  width: 5rem;
  height: 5rem;
  border: 0.6rem solid #999;
  border-bottom-color: transparent;
  border-radius: 50%;
  margin: 0 auto;
  animation: loader 500ms linear infinite;
}

@media screen and (max-width: 40rem) {
  .profile {
    display: flex;
    flex-wrap: wrap;
    padding: 4rem 0;
  }

  .profile::after {
    display: none;
  }

  .profile-image,
  .profile-user-settings,
  .profile-bio,
  .profile-stats {
    float: none;
    width: auto;
  }

  .profile-image img {
    width: 7.7rem;
  }

  .profile-user-settings {
    flex-basis: calc(100% - 10.7rem);
    display: flex;
    flex-wrap: wrap;
    margin-top: 1rem;
  }

  .profile-user-name {
    font-size: 2.2rem;
  }

  .profile-edit-btn {
    order: 1;
    padding: 0;
    text-align: center;
    margin-top: 1rem;
  }

  .profile-edit-btn {
    margin-left: 0;
  }

  .profile-bio {
    font-size: 1.4rem;
    margin-top: 1.5rem;
  }

  .profile-edit-btn,
  .profile-bio,
  .profile-stats {
    flex-basis: 100%;
  }

  .profile-stats {
    order: 1;
    margin-top: 1.5rem;
  }

  .profile-stats ul {
    display: flex;
    text-align: center;
    padding: 1.2rem 0;
    border-top: 0.1rem solid #dadada;
    border-bottom: 0.1rem solid #dadada;
  }

  .profile-stats li {
    font-size: 1.1rem;
    flex: 1;
    margin: 0;
  }

  .profile-stat-count {
    display: block;
  }
}

@keyframes loader {
  to {
    transform: rotate(360deg);
  }
}

@supports (display: grid) {
  .profile {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-rows: repeat(3, auto);
    grid-column-gap: 3rem;
    align-items: center;
  }

  .profile-image {
    grid-row: 1 / -1;
  }

  .gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
    grid-gap: 2rem;
  }

  .profile-image,
  .profile-user-settings,
  .profile-stats,
  .profile-bio,
  .gallery-item,
  .gallery {
    width: auto;
    margin: 0;
  }

  @media (max-width: 40rem) {
    .profile {
      grid-template-columns: auto 1fr;
      grid-row-gap: 1.5rem;
    }

    .profile-image {
      grid-row: 1 / 2;
    }

    .profile-user-settings {
      display: grid;
      grid-template-columns: auto 1fr;
      grid-gap: 1rem;
    }

    .profile-edit-btn,
    .profile-stats,
    .profile-bio {
      grid-column: 1 / -1;
    }

    .profile-user-settings,
    .profile-edit-btn,
    .profile-settings-btn,
    .profile-bio,
    .profile-stats {
      margin: 0;
    }
  }
}
</style>
