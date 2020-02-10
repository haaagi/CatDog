<template>
  <div>
    <h2>검색 결과 : {{ name }}</h2>

    <v-container class="grid-container">
      <UserCard class="item1" />
      <UserCard />
      <UserCard />
      <UserCard />
      <UserCard />
    </v-container>
  </div>
</template>

<script>
import UserCard from '../components/UserCard.vue';
export default {
  components: { UserCard },
  data() {
    return { name: 'no name' };
  },
  mounted() {
    this.name = this.$route.params.name;
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
</style>
