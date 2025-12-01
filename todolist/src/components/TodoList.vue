<template>
  <div class="container">
    <div
        class="todo-card"
        v-for="(todo, index) in todos"
        :key="index"
        :class="{ erledigt: todo.erledigt }"
    >
      <h3>{{ todo.titel }}</h3>
      <p>{{ todo.beschreibung }}</p>
      <span class="status">{{ todo.erledigt ? '✅ Erledigt' : '⏳ Offen' }}</span>
    </div>
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
.container {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.todo-card {
  background: #fff;
  padding: 15px 20px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.1);
}

.todo-card.erledigt {
  opacity: 0.6;
  text-decoration: line-through;
}

h3 {
  margin: 0 0 8px 0;
}

p {
  margin: 0 0 10px 0;
}

.status {
  font-weight: bold;
  color: #0077ff;
}
</style>
