<template>
    <!-- <<div class="container">
        <div>
    <button @click="recommend()">추천영화 보가</button>
    <div class="row">
        <div v-for="movie in n_movies" :key="movie.id">
            <img :src="movie.poster_url" >
        </div>
    </div>
  </div> // 여기까진 안쓴다. -->
    <!-- <div>
        
    </div>  -->
    <div>
        <!-- <button  type="button" class="btn btn-primary" @click="recommend()">추천영화</button>
            <div v-for="movie in n_movies" :key="movie.id">
                <div class="carousel-item active">
                    <img :src="movie.poster_url" class="d-block w-6" alt="">
                </div>
            </div> -->
        <!-- <button  type="button" class="btn btn-primary" @click="recommend()">추천영화</button>
        <div v-for="movie in n_movies" :key="movie.id">
            <div class="title">
                <h1><span style="color: #ff9f43">하기</span><span style="color: #0abde3">수야</span> <span
                        style="color: #ee5253">가</span><span style="color: #00d2d3">추천하는</span><span
                                style="color: #5f27cd">영화</span></h1>
            </div>
            <div class="card1">
                <img :src="movie.poster_url" alt="">
                <h3>{{movie.title}}</h3>
            </div>
        </div> -->
        <div class="title">
            <h1><span style="color: #ff9f43">H</span><span style="color: #0abde3">AA</span> <span
                    style="color: #ee5253">GI</span><span style="color: #00d2d3">SOO</span><span
                    style="color: #5f27cd">YA</span></h1>
        </div>
        <button type="button" class="btn btn-primary" @click="recommend()">추천영화</button>
        <div class="row">
            <div v-for="movie in n_movies" :key="movie.id">

                <div class="card1">
                    <img :src="movie.poster_url" alt="">
                    
                </div>
                
            <!-- <div class="box" v-for="movie in n_movies" :key="movie.id">
                    <div class="card">
                        <div class="imgBx">
                            <img :src="movie.poster_url"
                                :alt="movie.title">
                        </div>
                        <div class="details">
                            <h2>{{movie.title}}</h2>
                        </div>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
</template>
<script>
    import {
        mapGetters
    } from 'vuex';
    export default {
        name: 'RecommendMovie',
        data() {
            return {
                new_movies: [],
                temp: [],
                n_movies: [],
            }
        },
        props: {
            movies: Array,
        },
        computed: {
            ...mapGetters(['getuserinfo'])
        },
        methods: {
            recommend() {
                for (var key in this.getuserinfo) {
                    if (key === 'stars') {
                        this.temp = this.getuserinfo[key]
                    }
                    for (var i in this.temp) {
                        for (var k in this.movies) {
                            for (var j in this.movies[k]['like_users']) {
                                if (this.temp[i] === this.movies[k]['like_users'][j]) {
                                    this.new_movies.push(this.movies[k])
                                }
                            }
                        }
                    }
                }
                this.n_movies = new Set(this.new_movies)
            }
        }
        // filterMovies() {
        //     if (this.selectedGenre === '전체보기') {
        //       return this.movies;
        //     } else {
        //       return this.movies.filter(movie => movie.genre1 === this.selectedGenre || movie.genre3 === this
        //         .selectedGenre);
        //     }
        //   }
    }
</script>

<style scoped>
    .title {
        width: 100%;
        text-align: center;
    }

    .title h1 {
        font-size: 50px;
        font-family: 'Exo', sans-serif;
    }


    /* .card {
        position: relative;
        width: 300px;
        height: 350px;
        background: #fff;
        margin: 0 auto;
        border-radius: 4px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, .2);
    }

    .card:before,
    .card:after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border-radius: 4px;
        background: #fff;
        transition: 0.5s;
        z-index: -1;
    }

    .card:hover:before {
        transform: rotate(20deg);
        box-shadow: 0 2px 20px rgba(0, 0, 0, .2);
    }

    .card:hover:after {
        transform: rotate(10deg);
        box-shadow: 0 2px 20px rgba(0, 0, 0, .2);
    }

    .card .imgBx {
        position: absolute;
        top: 10px;
        left: 10px;
        bottom: 10px;
        right: 10px;
        background: #222;
        transition: 0.5s;
        z-index: 1;
    }

    .card:hover .imgBx {
        bottom: 80px;
    }

    .card .imgBx img {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .card .details {
        position: absolute;
        left: 10px;
        right: 10px;
        bottom: 10px;
        height: 60px;
        text-align: center;
    }

    .card .details h2 {
        margin: 0;
        padding: 0;
        font-weight: 600;
        font-size: 20px;
        color: #777;
        text-transform: uppercase;
    }

    .card .details h2 span {
        font-weight: 500;
        font-size: 16px;
        color: #f38695;
        display: block;
        margin-top: 5px;
    } */
</style>