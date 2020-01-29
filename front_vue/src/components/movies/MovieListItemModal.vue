<template>
  <div class="modal fade" tabindex="-1" role="dialog" :id="`movie-${movie.id}`">

    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h3>{{ movie.title }}({{movie.title_eng}})</h3>
          <div class="ui labeled button" tabindex="0" @click="likeMovie">
            <div class="ui red button">
              <i class="heart icon"></i> Like
            </div>
          </div>
        </div>
        <div class="modal-body">
          <img class="movie--poster my-3 imgess" :src="movie.poster_url" :alt="movie.name">
          <div class="container icon-des">
            <p><i class="calendar alternate icon"></i>개봉일 {{movie.open_date}}</p>
            <p><i class="user alternate icon"></i> 관객수 {{movie.audience}}</p>
            <p><i class="eye alternate icon"></i> 관람등급 {{movie.watch_grade}}</p>
            <p><i class="star alternate icon"></i> 평점 {{movie.user_rating}}</p>
          </div>
          <hr>
          <p>{{ movie.description }}</p>
          <p><a :href="movie.naver_link">...더보기</a></p>

          <!-- 무비 디테일!!!! -->
          <button><router-link to="/moviedetail" >클릭 </router-link></button>

          <hr>
          <div class="container">
            <form class="ui form row" @submit.prevent="createReview">

              <div class="ten wide field ">
                <label for="content">Review</label>
                <input v-model="reviewInput.content" type="text" name="content" placeholder="review"
                  :id="reviewInput.content">
              </div>
              <div class="three wide field ">
                <label for="score">Score</label>
                <input v-model="reviewInput.score" type="number" name="score" placeholder="score"
                  :id="reviewInput.score">

              </div>
              <button class="ui button" type="submit">Submit</button>
            </form>
            <div class="ui list">

              <div class="item" v-for="review in reviewList" :key="review.id">
                <i class="facebook messenger icon"></i>
                {{ review.content}} ({{review.score}})
              </div>
            </div>

          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
  const HOST = process.env.VUE_APP_SERVER_HOST;
  const axios = require('axios');

 import { mapGetters } from 'vuex';


  export default {
    name: 'MovieListItemModal',
    props: {
      movie: Object,
    },
    data() {
      return {
        reviewInput: {
          content: '',
          score: 0,
        },
        reviewList: [],
      }
    },

    methods: {
      // clickLike(movie) {
      //   this.clickedMovie = movie;
      // },
      likeMovie() {
        const hash = sessionStorage.getItem('jwt');
        const options = {
          headers: {
            Authorization: 'JWT ' + hash
          }
        }
        axios.get(HOST + 'api/movielike/' + this.movie.id + '/', options)
          .then(res => console.log(res))
          .catch(err => console.error(err))

      },

      createReview() {
        const hash = sessionStorage.getItem('jwt');
        const options = {
          headers: {
            Authorization: 'JWT ' + hash
          }
        }
        axios.post(HOST + 'api/reviews/new/' + this.movie.id + '/', this.reviewInput, options)
          .then(res => {
            if (res.status == 200) {
              this.reviewList = res.data.review_set;
            }
          })
          .catch(err => console.error(err))
      },
      // created() {
      //   const hash = sessionStorage.getItem('jwt');
      //     const options = {
      //       headers: {
      //         Authorization: 'JWT ' + hash
      //       }
      //     }
      //   axios.get(HOST + `api/${this.movie.id}/reviews/`, options)
      //     .then(res => {
      //         console.log(res.data)
      //         this.reviewList = res.data.review_set
      //       })
      // }
    }, 
    computed: {
      ...mapGetters(['getMovieid'])
    }
    //

  }
</script>

<style>
  #modal-header {
    text-align: center;

  }

  #imgess {
    float: left;
    margin-right: 20px;
    margin-top: 20px;
    clear: both;
  }
</style>