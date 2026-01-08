import { describe, it, expect } from "vitest";
import { mount } from "@vue/test-utils";
import TodoList from "../components/TodoList.vue";

describe("TodoList", () => {
    it("rendert eine Liste von Todos", () => {
        const todos = [
            { id: 1, titel: "Test Aufgabe", beschreibung: "", erledigt: false }
        ];

        const wrapper = mount(TodoList, {
            props: { todos }
        });

        expect(wrapper.text()).toContain("Test Aufgabe");
    });

    it("emittiert toggle Event beim Klick", async () => {
        const todos = [
            { id: 1, titel: "Test", beschreibung: "", erledigt: false }
        ];

        const wrapper = mount(TodoList, {
            props: { todos }
        });

        await wrapper.find("input[type='checkbox']").trigger("change");

        expect(wrapper.emitted()).toHaveProperty("toggle");
    });
});
