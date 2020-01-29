<template>
  <div>

    <div class="container">
      <b-nav pills align="center">
        <b-nav-item-dropdown id="my-nav-dropdown" text="GENRE" toggle-class="nav-link-custom" right>
          <b-dropdown-item @click="selectGenre(genre)" v-for="genre in genreList" :key="genre">{{ genre }}
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
        </b-nav-item-dropdown>
      </b-nav>

      <b-row>
          <SelectedMovieList v-for="movie in filterMovies" :key="movie.id" :movie="movie">
            {{ movie }}
          </SelectedMovieList>
      </b-row>
    </div>
    
  </div>
</template>


<script>
  import SelectedMovieList from './SelectedMovieList';

  export default {
    name: 'MovieList',
    components: {
      SelectedMovieList,
    },
    props: {
      movies: Array,
    },
    data() {
      return {
        selectedGenre: '전체보기',
        genreList: ['전체보기', '드라마', '액션', '범죄', '스릴러', '코미디', '공포(호러)', '멜로/로맨스', '미스터리', '애니메이션', '사극', 'SF'],
        // temp: '0',
      }
    },
    methods: {
      selectGenre(genre) {
        this.selectedGenre = genre;
      }
    },
    computed: {
      filterMovies() {
        if (this.selectedGenre === '전체보기') {
          return this.movies;
        } else {
          return this.movies.filter(movie => movie.genre1 === this.selectedGenre || movie.genre3 === this
            .selectedGenre);
        }
      }
    },
  }
</script>

<style>
  select {
    display: block;
    width: 50% !important;
    margin: 2rem auto !important;
  }

  .container {
    margin-top: 1em;
  }
</style>