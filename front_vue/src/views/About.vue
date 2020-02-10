<template>
  <div>
    <h2>about</h2>
  </div>
</template>

<script>
export default {
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
