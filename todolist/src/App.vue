<template>
  <div class="app">
    <!-- SIDEBAR -->
    <aside class="sidebar">
      <div class="logo">✓ Todo</div>

      <div>
        <div class="nav-title">Navigation</div>
        <ul>
          <li
              :class="{ active: activeTab === 'open' }"
              @click="activeTab = 'open'"
          >
            Offen
          </li>
          <li
              :class="{ active: activeTab === 'done' }"
              @click="activeTab = 'done'"
          >
            Erledigt
          </li>
        </ul>
      </div>

      <div class="stats">
        <div><span class="dot open"></span> Offen {{ offene.length }}</div>
        <div><span class="dot done"></span> Erledigt {{ erledigte.length }}</div>
      </div>

      <footer>B3.1 Webtechnologien Todo App</footer>
    </aside>

    <!-- MAIN -->
    <main class="main">
      <div class="content-card">
        <h1>
          {{ activeTab === 'open' ? 'Offene Aufgaben' : 'Erledigte Aufgaben' }}
        </h1>

        <p class="subtitle">
          {{
            activeTab === 'open'
                ? 'Noch nicht erledigte Aufgaben.'
                : 'Bereits abgeschlossene Aufgaben.'
          }}
        </p>

        <!-- Button nur bei offenen Aufgaben -->
        <button
            v-if="activeTab === 'open'"
            class="add-btn"
            @click="openModal"
        >
          Neue Aufgabe hinzufügen
        </button>

        <!-- LISTEN -->
        <TodoList
            v-if="activeTab === 'open'"
            :todos="offene"
            @toggle="toggleTodo"
            @delete="deleteTodo"
            @edit="startEdit"
        />

        <TodoList
            v-if="activeTab === 'done'"
            :todos="erledigte"
            @toggle="toggleTodo"
            @delete="deleteTodo"
            @edit="startEdit"
        />

        <p
            v-if="activeTab === 'open' && offene.length === 0"
            class="empty"
        >
          Keine offenen Aufgaben vorhanden.
        </p>

        <p
            v-if="activeTab === 'done' && erledigte.length === 0"
            class="empty"
        >
          Noch keine erledigten Aufgaben.
        </p>
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
          <h2>{{ editing ? 'Aufgabe bearbeiten' : 'Aufgabe hinzufügen' }}</h2>

          <input
              v-model="form.titel"
              placeholder="Titel"
              @keydown.enter.prevent="saveTodo"
              autofocus
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
      const res = await fetch(
          "https://b3-1-webtechnologien.onrender.com/aufgaben"
      );
      this.todos = await res.json();
    },

    openModal() {
      this.editing = false;
      this.form = { titel: "", beschreibung: "" };
      this.showModal = true;
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
