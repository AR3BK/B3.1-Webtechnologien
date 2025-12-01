<template>
  <div class="container">
    <h1>Meine Todos</h1>
    <div class="todo-list">
      <div
          class="todo-card"
          v-for="(todo, index) in todos"
          :key="index"
          :class="{ erledigt: todo.erledigt }"
      >
        <h3>{{ todo.name }}</h3>
        <p>{{ todo.beschreibung }}</p>
        <span class="status">{{ todo.erledigt ? '✅ Erledigt' : '⏳ Offen' }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoList",
  data() {
    return {
      todos: []
    };
  },
  mounted() {
    fetch("http://localhost:8080/aufgaben")
        .then(res => res.json())
        .then(data => {
          this.todos = data;
        })
        .catch(err => console.error(err));
  }
};
</script>

<style scoped>
body {
  font-family: 'Arial', sans-serif;
  background: #f0f4f8;
  margin: 0;
}

.container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.todo-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.todo-card {
  background: #fff;
  padding: 15px 20px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.1);
  transition: transform 0.2s;
}

.todo-card:hover {
  transform: translateY(-3px);
}

.todo-card.erledigt {
  opacity: 0.6;
  text-decoration: line-through;
}

h3 {
  margin: 0 0 8px 0;
  color: #222;
}

p {
  margin: 0 0 10px 0;
  color: #555;
}

.status {
  font-weight: bold;
  color: #0077ff;
}
</style>
