<template>
  <div id="app">
    <h1>My Todo App!</h1>
    <TodoList />
  </div>
</template>

<script>
import TodoList from '../components/todo/TodoList.vue';

export default {
  components: {
    TodoList,
  },
};
</script>
<style scoped></style>
