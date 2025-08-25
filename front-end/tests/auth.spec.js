import { mount } from "@vue/test-utils";
import LoginView from "../src/views/LoginView.vue";
import { createTestingPinia } from "@pinia/testing";

describe("LoginView", () => {
  it("tenta logar com usuário e senha", async () => {
    const wrapper = mount(LoginView, {
      global: {
        plugins: [createTestingPinia({ stubActions: false })]
      }
    });

    await wrapper.find('input[placeholder="Usuário"]').setValue("kaio");
    await wrapper.find('input[placeholder="Senha"]').setValue("1234");

    await wrapper.find("form").trigger("submit.prevent");

    const authStore = wrapper.vm.$pinia._s.get("auth");
    expect(authStore.login).toHaveBeenCalledWith("kaio", "1234");
  });

  it("exibe alerta se login falhar", async () => {
    const wrapper = mount(LoginView, {
      global: {
        plugins: [createTestingPinia({ stubActions: true })]
      }
    });

    window.alert = jest.fn();
    await wrapper.find("form").trigger("submit.prevent");

    expect(window.alert).toHaveBeenCalledWith("Usuário ou senha inválidos");
  });
});
