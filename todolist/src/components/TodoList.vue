<template>
  <div>
    <h2>Meine Todos</h2>
    <ul>
      <li v-for="(todo, index) in todos" :key="index">
        {{ todo }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TodoList",
  props: {
    todos: Array
  }
};
</script>

<style scoped>
div {
  background: #f7f7f7;
  padding: 10px;
  border-radius: 8px;
  width: 300px;
}
ul {
  list-style: none;
  padding: 0;
}
</style>
