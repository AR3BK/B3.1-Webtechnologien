<template>
  <main>
    <h1>Todo App</h1>
    <TodoList :todos="todoItems" />
  </main>
</template>

<script>
import TodoList from './components/TodoList.vue';

export default {
  components: { TodoList },
  data() {
    return {
      todoItems: [
        "Einkaufen",
        "Lernen",
        "Hausaufgaben",
        "In die kluft"
      ]
    };
  }
};
</script>

<style>
main {
  padding: 20px;
  font-family: sans-serif;
}
</style>
