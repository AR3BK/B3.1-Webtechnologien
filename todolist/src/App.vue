<template>
  <div class="app">
    <!-- SIDEBAR -->
    <aside class="sidebar">
      <div class="logo">✓ Todo</div>

      <div class="nav-title">Navigation</div>
      <ul class="nav">
        <li
            :class="{ active: activeTab === 'open' }"
            @click="activeTab = 'open'"
        >
          Aufgaben
        </li>
        <li
            :class="{ active: activeTab === 'done' }"
            @click="activeTab = 'done'"
        >
          Erledigt
        </li>
      </ul>

      <div class="stats">
        <div><span class="dot open"></span> Offen {{ offene.length }}</div>
        <div><span class="dot done"></span> Erledigt {{ erledigte.length }}</div>
      </div>

      <footer>B3.1 Webtechnologien Todo App</footer>
    </aside>

    <!-- MAIN -->
    <main class="main">
      <div class="content-card">
        <h1>{{ activeTab === 'open' ? 'Aufgaben' : 'Erledigte Aufgaben' }}</h1>
        <p class="subtitle">
          {{ activeTab === 'open'
            ? 'Noch offene Aufgaben.'
            : 'Bereits erledigte Aufgaben.' }}
        </p>

        <button
            v-if="activeTab === 'open'"
            class="add-btn"
            @click="openModal"
        >
          Neue Aufgabe hinzufügen
        </button>

        <!-- FADE ANIMATION -->
        <transition name="fade" mode="out-in">
          <TodoList
              :key="activeTab"
              :todos="activeTab === 'open' ? offene : erledigte"
              @toggle="toggleTodo"
              @delete="askDelete"
              @edit="startEdit"
          />
        </transition>
      </div>
    </main>

    <!-- MODAL -->
    <transition name="modal-fade">
      <div
          v-if="showModal"
          class="modal-backdrop"
          @keydown.esc="closeModal"
          tabindex="0"
      >
        <div class="modal">
          <h2>{{ editing ? 'Aufgabe bearbeiten' : 'Todo hinzufügen' }}</h2>

          <input
              v-model="form.titel"
              placeholder="Titel"
              autofocus
              @keydown.enter="saveTodo"
          />

          <textarea
              v-model="form.beschreibung"
              placeholder="Beschreibung"
          ></textarea>

          <div class="modal-actions">
            <button
                class="primary"
                :disabled="!form.titel.trim()"
                @click="saveTodo"
            >
              Speichern
            </button>
            <button class="secondary" @click="closeModal">
              Abbrechen
            </button>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import TodoList from "./components/TodoList.vue";

export default {
  components: { TodoList },

  data() {
    return {
      todos: [],
      activeTab: "open",
      showModal: false,
      editing: false,
      editId: null,
      form: {
        titel: "",
        beschreibung: ""
      }
    };
  },

  computed: {
    offene() {
      return this.todos.filter(t => !t.erledigt);
    },
    erledigte() {
      return this.todos.filter(t => t.erledigt);
    }
  },

  methods: {
    async loadTodos() {
      const res = await fetch("https://b3-1-webtechnologien.onrender.com/aufgaben");
      this.todos = await res.json();
    },

    openModal() {
      this.editing = false;
      this.form = { titel: "", beschreibung: "" };
      this.showModal = true;
      this.$nextTick(() => {
        document.querySelector(".modal-backdrop")?.focus();
      });
    },

    closeModal() {
      this.showModal = false;
    },

    startEdit(todo) {
      this.editing = true;
      this.editId = todo.id;
      this.form = {
        titel: todo.titel,
        beschreibung: todo.beschreibung
      };
      this.showModal = true;
    },

    async saveTodo() {
      if (!this.form.titel.trim()) return;

      if (this.editing) {
        await fetch(
            `https://b3-1-webtechnologien.onrender.com/aufgaben/${this.editId}`,
            {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(this.form)
            }
        );
      } else {
        await fetch(
            "https://b3-1-webtechnologien.onrender.com/aufgaben",
            {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                ...this.form,
                erledigt: false
              })
            }
        );
      }

      this.closeModal();
      this.loadTodos();
    },

    async toggleTodo(id) {
      await fetch(
          `https://b3-1-webtechnologien.onrender.com/aufgaben/${id}/toggle`,
          { method: "PUT" }
      );
      this.loadTodos();
    },

    askDelete(id) {
      if (!confirm("Aufgabe wirklich löschen?")) return;
      this.deleteTodo(id);
    },

    async deleteTodo(id) {
      await fetch(
          `https://b3-1-webtechnologien.onrender.com/aufgaben/${id}`,
          { method: "DELETE" }
      );
      this.loadTodos();
    }
  },

  mounted() {
    this.loadTodos();
  }
};
</script>

<style>
/* FADE LIST */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

/* MODAL */
.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.25s ease;
}
.modal-fade-enter-from,
.modal-fade-leave-to {
  opacity: 0;
}

button.primary:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
