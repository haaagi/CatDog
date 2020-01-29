<template>
  <div class="home">
    <RecommendMovie :movies="movies"/>
    <MovieList :movies="movies"/>
    
  </div>
</template>

<script>
const HOST = process.env.VUE_APP_SERVER_HOST;

// import router from '../router';
const axios = require('axios'); 
// import MovieHome from '../components/MovieHome';
import MovieList from'../components/movies/MovieList';
import RecommendMovie from '../components/movies/RecommendMovie';
export default {
  name: 'home', 
  components: {
    MovieList,
    RecommendMovie,

  },
  data () {
    return {
      movies: [],
    }
  },
  methods: {
    },

  created () {
    const hash = sessionStorage.getItem('jwt');
    const options = {
        headers: {
            Authorization:'JWT ' + hash
        }
    }
    axios.get(HOST + 'api/movies/', options)
      .then(res=> this.movies = res.data)
      .catch(err => console.error(err))
    
  } 
}


</script>

<style>
</style>