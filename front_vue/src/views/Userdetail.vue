<template>
  <div>
    <header>

      <div class="container">

        <div class="profile">

          <div class="profile-image">

            <img src="https://image.flaticon.com/icons/svg/1077/1077063.svg" alt="">

          </div>

          <div class="profile-user-settings">

            <h1 class="profile-user-name">User ID</h1>

            <button class="btn profile-edit-btn">Edit Profile</button>


            <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog"
                aria-hidden="true"></i></button>

          </div>

          <!-- 팔로잉 팔로워 버튼 -->
          <div class="profile-stats">

            <ul>
              <li><span class="profile-stat-count"></span><v-btn text large>123 posts</v-btn></li>

              <li><span class="profile-stat-count"></span>
                <v-row justify="center">
                  <v-dialog v-model="dialog" scrollable max-width="300px">
                    <template v-slot:activator="{ on }">
                      <v-btn text color="black" dark v-on="on">300 followers</v-btn>
                    </template>
                    <v-card>
                      <v-card-title>Follower List</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text style="height: 400px;">
                        <v-list subheader>

                          <!-- 팔로워의 상세페이지로 이동하기 @click 설정하기!!  -->
                          <v-list-item v-for="item in items" :key="item.title">
                            <v-list-item-avatar>
                              <v-img :src="item.avatar"></v-img>
                            </v-list-item-avatar>

                            <v-list-item-content>
                              <v-list-item-title v-text="item.title"></v-list-item-title>
                            </v-list-item-content>
                          </v-list-item>
                        </v-list>


                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </li>

              <li><span class="profile-stat-count"></span>
                <v-row justify="center">
                  <v-dialog v-model="dialog_f" scrollable max-width="300px">
                    <template v-slot:activator="{ on }">
                      <v-btn text color="black" dark v-on="on">300 following</v-btn>
                    </template>
                    <v-card>
                      <v-card-title>Following List</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text style="height: 400px;">
                        <v-list subheader>

                          <!-- 팔로워의 상세페이지로 이동하기 @click 설정하기!!  -->
                          <v-list-item v-for="item in items" :key="item.title">
                            <v-list-item-avatar>
                              <v-img :src="item.avatar"></v-img>
                            </v-list-item-avatar>

                            <v-list-item-content>
                              <v-list-item-title v-text="item.title"></v-list-item-title>
                            </v-list-item-content>
                          </v-list-item>
                        </v-list>


                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-btn color="blue darken-1" text @click="dialog_f = false">Close</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </li>


            </ul>

          </div>

          <div class="profile-bio">

            <p><span class="profile-real-name">username</span>궁시렁 궁시렁</p>

          </div>

        </div>
        <!-- End of profile section -->

      </div>
      <!-- End of container -->

    </header>

    <!-- buttons -->
    <div class="menu-btn">
      <v-container fluid>
        <v-row>
          <v-col cols="12" sm="6" class="py-2">
            <v-btn-toggle v-model="icon" borderless>
              <v-btn value="history">
                <v-icon>mdi-clock</v-icon>
                <span>HISTORY</span>
              </v-btn>

              <v-btn value="post">
                <v-icon>mdi-image</v-icon>
                <span>POSTING</span>
              </v-btn>

            </v-btn-toggle>
          </v-col>
        </v-row>
      </v-container>
    </div>



    <main>

      <div class="container">

        <div class="gallery">

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 56</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 2</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1497445462247-4330a224fdb1?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 89</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 5</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1426604966848-d7adac402bff?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Gallery</span><i class="fas fa-clone" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 42</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 1</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1502630859934-b3b41d18206c?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Video</span><i class="fas fa-video" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 38</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 0</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1498471731312-b6d2b8280c61?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Gallery</span><i class="fas fa-clone" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 47</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 1</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1515023115689-589c33041d3c?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 94</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 3</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1504214208698-ea1916a2195a?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Gallery</span><i class="fas fa-clone" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 52</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 4</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1515814472071-4d632dbc5d4a?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 66</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 2</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1511407397940-d57f68e81203?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Gallery</span><i class="fas fa-clone" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 45</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 0</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1518481612222-68bbe828ecd1?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 34</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 1</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1505058707965-09a4469a87e4?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 41</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 0</li>
              </ul>

            </div>

          </div>

          <div class="gallery-item" tabindex="0">

            <img src="https://images.unsplash.com/photo-1423012373122-fff0a5d28cc9?w=500&h=500&fit=crop"
              class="gallery-image" alt="">

            <div class="gallery-item-type">

              <span class="visually-hidden">Video</span><i class="fas fa-video" aria-hidden="true"></i>

            </div>

            <div class="gallery-item-info">

              <ul>
                <li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart"
                    aria-hidden="true"></i> 30</li>
                <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i
                    class="fas fa-comment" aria-hidden="true"></i> 2</li>
              </ul>

            </div>

          </div>

        </div>
        <!-- End of gallery -->

        <div class="loader"></div>

      </div>
      <!-- End of container -->

    </main>
  </div>
</template>

<script>
  export default {
    name: 'Userdetail',
    data() {
      return {
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

        // 임시 팔로워 리스트 
        items: [{
            active: true,
            title: 'Jason Oner',
            avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg'
          },
          {
            active: true,
            title: 'Ranee Carlson',
            avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg'
          },
          {
            title: 'Cindy Baker',
            avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg'
          },
          {
            title: 'Ali Connors',
            avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg'
          },
        ],
        items2: [{
          title: 'Travis Howard',
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'
        }, ],
      }
    },
  }
</script>

<style scoped>
  .menu-btn {}

  /*

All grid code is placed in a 'supports' rule (feature query) at the bottom of the CSS (Line 310). 
        
The 'supports' rule will only run if your browser supports CSS grid.

Flexbox and floats are used as a fallback so that browsers which don't support grid will still recieve a similar layout.

*/

  /* Base Styles */

  :root {
    font-size: 10px;
  }

  *,
  *::before,
  *::after {
    box-sizing: border-box;
  }

  body {
    font-family: "Open Sans", Arial, sans-serif;
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

  /* Profile Section */

  .profile {
    padding: 5rem 0;
  }

  .profile::after {
    content: "";
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
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
  }

  .profile-settings-btn {
    font-size: 2rem;
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

  /* Gallery Section */

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

  /* Loader */

  .loader {
    width: 5rem;
    height: 5rem;
    border: 0.6rem solid #999;
    border-bottom-color: transparent;
    border-radius: 50%;
    margin: 0 auto;
    animation: loader 500ms linear infinite;
  }

  /* Media Query */

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

  /* Spinner Animation */

  @keyframes loader {
    to {
      transform: rotate(360deg);
    }
  }

  /*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling. 

*/

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