<template>
  <div>
    <h2>Meine Todos</h2>

    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <span :class="{ done: todo.erledigt }">
          {{ todo.titel }} – {{ todo.beschreibung }}
        </span>

        <button @click="$emit('toggle', todo.id)">
          {{ todo.erledigt ? "Undo" : "Erledigt" }}
        </button>

        <button @click="$emit('delete', todo.id)">
          Löschen
        </button>
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

<style scoped>
.done {
  text-decoration: line-through;
  color: gray;
}

li {
  margin-bottom: 10px;
}
</style>
