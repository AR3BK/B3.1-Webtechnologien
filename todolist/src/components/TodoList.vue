<template>
  <ul class="todo-list">
    <li v-for="todo in todos" :key="todo.id">
      <input
          type="checkbox"
          :checked="todo.erledigt"
          @change="$emit('toggle', todo.id)"
      />

      <span :class="{ done: todo.erledigt }">
        {{ todo.titel }}
      </span>

      <div class="actions">
        <button @click="$emit('edit', todo)">Bearbeiten</button>
        <button class="danger" @click="$emit('delete', todo.id)">
          Löschen
        </button>
      </div>
    </li>

    <li v-if="todos.length === 0" class="empty">
      Keine Aufgaben vorhanden.
    </li>
  </ul>
</template>

<script>
export default {
  props: {
    todos: Array
  }
};
</script>
