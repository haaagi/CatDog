<template>
  <div class="moviedetail">
      <router-view />
 <h3>{{ getMovieid.title }} </h3>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
    name: 'Moviedetail',
    computed: {
      ...mapGetters(['getMovieid'])
    },
    methods: {
      ...mapActions(['getmoviedetail'])
    }

}
</script>

<style>

</style>