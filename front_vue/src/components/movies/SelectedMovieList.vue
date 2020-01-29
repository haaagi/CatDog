<template>
    <div class="col-10 col-sm-6 col-lg-4 col-xl-3">
 <div class="container catalog">
  <div class="row">
    <div class="col s4">
      <div class="card content">
        <div class="card-image">
          <img class="content-image" :src="movie.poster_url">
          <div class="content-overlay">
          </div>
        </div>
        <div class="card-content content-details fadeIn">
          <p class="content-text" align="center">
              <button class="btn btn-primary" data-toggle="modal" :data-target="`#movie-${movie.id}`">상세보기</button>
          </p>
        </div>
        <MovieListItemModal :movie="movie" />

        <div class="card-action">
          <!-- <h4 class="content-title">{{ movie.title }}</h4> -->
        </div>
      </div>
    </div>
  </div>
</div>

    </div>
</template>

<script>
// import router from '../../router';
import MovieListItemModal from './MovieListItemModal';
// import {mapGetters} from 'vuex'; 

export default {
    name: 'SelectedMovieList',
    props:{
        movie: Object,
    }, 
    components: {
      MovieListItemModal,
    },
    // computed: {
    //   ...mapGetters(['getmovieOne'])
    // }
    

}


</script>

<style>
*, :before, :after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.content {
  position: relative;
  width: 100%;
  max-width: 300px;
  margin: auto;
  overflow: hidden;
}
.content .content-overlay {
  background: rgba(104, 97, 97, 0.75);
  position: absolute;
  height: 100%;
  width: 100%;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  opacity: 0;
  transition: all 0.4s ease-in-out 0s;
}
.content:hover .content-overlay {
  opacity: 1;
}
.content:hover .content-details {
  top: 30%;
  left: 50%;
  opacity: 1;
}

.content-image {
  width: 100%;
}

.content-details {
  position: absolute;
  text-align: justify;
  font-weight: bolder;
  padding-left: 1em;
  padding-right: 1em;
  width: 100%;
  top: 30%;
  left: 50%;
  opacity: 0;
  transform: translate(-50%, -50%);
  transition: all 0.3s ease-in-out 0s;
}
.content-details h3 {
  color: #000;
  letter-spacing: 0.15em;
  text-transform: uppercase;
}

.fadeIn {
  top: 80%;
}

.container.catalog {
  text-align: center;
}

.content-title {
    font-size: 1em;
}

.card {
  margin: 0.3em;
}
</style>