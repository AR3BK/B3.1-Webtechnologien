<template>
  <div>
    <h2>Meine Todos</h2>

    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <span
            class="todo-text"
            :class="{ 'todo-done': todo.erledigt }"
        >
          {{ todo.titel }} – {{ todo.beschreibung }}
        </span>

        <div class="todo-actions">
          <button @click="$emit('toggle', todo.id)">
            {{ todo.erledigt ? "Undo" : "Erledigt" }}
          </button>

          <button @click="$emit('delete', todo.id)">
            Löschen
          </button>
        </div>
      </li>
    </ul>
  </div>
</template>


<script>
export default {
  props: {
    todos: Array
  }
};
</script>