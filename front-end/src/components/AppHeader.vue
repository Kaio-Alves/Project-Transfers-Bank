<template>
  <header class="p-4 bg-blue-600 text-white flex justify-between items-center">
    <h1 class="text-lg font-bold">Bank Transfers</h1>
    <button v-if="showLogout" @click="logout" class="bg-red-500 px-3 py-1 rounded">
      Sair
    </button>
  </header>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useAuthStore } from "../store/auth";

/* eslint-disable no-unused-vars */
const props = defineProps({
  showLogout: { type: Boolean, default: false }
});

const router = useRouter();
const auth = useAuthStore();

const logout = () => {
  auth.logout();
  router.push("/login");
};
</script>
