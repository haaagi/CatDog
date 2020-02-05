<template>
  <div class="grid-layout">
    <v-img class="grid-item span-2" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item span-2" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item span-3" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item span-3" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item span-2" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item span-2" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
    <v-img class="grid-item" src="https://source.unsplash.com/category/nature" />
  </div>
</template>

<script>
export default {
  name: 'home',
};
</script>

<style>
.grid-layout {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-gap: 15px;
  grid-auto-rows: minmax(200px, auto);
  grid-auto-flow: dense;
  padding: 15px;
}

.grid-item {
  padding: 1rem;
  border-radius: 5px;
}

.span-2 {
  grid-column-end: span 2;
  grid-row-end: span 2;
}

.span-3 {
  grid-column-end: span 3;
  grid-row-end: span 4;
}
</style>
