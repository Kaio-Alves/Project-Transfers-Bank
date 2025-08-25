<template>
  <div class="flex flex-col min-h-screen">
    <AppHeader />
    <main class="flex-grow flex items-center justify-center">
      <form @submit.prevent="login" class="space-y-3 bg-white p-6 rounded shadow-md w-80">
        <input v-model="username" placeholder="Usuário" class="border p-2 w-full rounded" />
        <input v-model="password" type="password" placeholder="Senha" class="border p-2 w-full rounded" />
        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded w-full">Entrar</button>
        <router-link to="/register" class="text-sm text-blue-500 block text-center">Não tem conta? Cadastre-se</router-link>
      </form>
    </main>
    <AppFooter />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "../store/auth";
import AppHeader from "../components/AppHeader.vue";
import AppFooter from "../components/AppFooter.vue";

const username = ref("");
const password = ref("");
const router = useRouter();
const auth = useAuthStore();

const login = async () => {
  try {
    await auth.login(username.value, password.value);
    router.push("/dashboard");
  } catch (err) {
    alert(err.message);
  }
};
</script>