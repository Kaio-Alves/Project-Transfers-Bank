import { defineStore } from 'pinia';
import * as api from '../api/index';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: null,
  }),
  actions: {
    async login(username, password) {
      try {
        const res = await api.login(username, password);

        if (!res.token) {
          throw new Error('Usuário ou senha inválidos');
        }

        this.token = res.token;
      } catch (err) {
        throw new Error('Usuário ou senha inválidos');
      }
    },

    logout() {
      this.token = null;
    }
  }
});
