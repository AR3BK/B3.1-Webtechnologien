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
      todoItems: []
    };
  },
  mounted() {
    fetch("https://b3-1-webtechnologien.onrender.com/aufgaben")
        .then(res => res.json())
        .then(data => {
          this.todoItems = data;
        })
        .catch(err => console.error("API Fehler:", err));
  }
};
</script>

<style>
main {
  padding: 20px;
  font-family: sans-serif;
  background: #f0f4f8;
  min-height: 100vh;
}

h1 {  
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}
</style>
