<template>
  <main>
    <h1>Todo App</h1>

    <!-- Neue Aufgabe -->
    <div class="add">
      <input v-model="newTitel" placeholder="Titel" />
      <input v-model="newBeschreibung" placeholder="Beschreibung" />
      <button @click="addTodo">Hinzufügen</button>
    </div>

    <!-- Liste -->
    <TodoList
        :todos="todos"
        @toggle="toggleTodo"
        @delete="deleteTodo"
    />
  </main>
</template>

<script>
import TodoList from "./components/TodoList.vue";

export default {
  components: { TodoList },
  data() {
    return {
      todos: [],
      newTitel: "",
      newBeschreibung: ""
    };
  },
  methods: {
    async loadTodos() {
      const res = await fetch("https://b3-1-webtechnologien.onrender.com/aufgaben");
      this.todos = await res.json();
    },

    async addTodo() {
      if (!this.newTitel) return;

      await fetch("https://b3-1-webtechnologien.onrender.com/aufgaben", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          titel: this.newTitel,
          beschreibung: this.newBeschreibung,
          erledigt: false
        })
      });

      this.newTitel = "";
      this.newBeschreibung = "";
      this.loadTodos();
    },

    async toggleTodo(id) {
      await fetch(`https://b3-1-webtechnologien.onrender.com/aufgaben/${id}/toggle`, {
        method: "PUT"
      });
      this.loadTodos();
    },

    async deleteTodo(id) {
      await fetch(`https://b3-1-webtechnologien.onrender.com/aufgaben/${id}`, {
        method: "DELETE"
      });
      this.loadTodos();
    }
  },
  mounted() {
    this.loadTodos();
  }
};
</script>

<style>
main {
  max-width: 600px;
  margin: auto;
  font-family: sans-serif;
}

.add {
  margin-bottom: 20px;
}

input {
  margin-right: 5px;
}

button {
  margin-left: 5px;
}
</style>
