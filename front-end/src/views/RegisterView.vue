<template>
  <div class="flex flex-col min-h-screen">
    <Header />
    <main class="flex-grow flex items-center justify-center">
      <form @submit.prevent="handleRegister" class="space-y-3 bg-white p-6 rounded shadow-md w-80">
        <input v-model="username" placeholder="Usuário" class="border p-2 w-full rounded" />
        <input v-model="password" type="password" placeholder="Senha" class="border p-2 w-full rounded" />
        <button type="submit" class="bg-green-600 text-white px-4 py-2 rounded w-full">Cadastrar</button>
        <router-link to="/login" class="text-sm text-blue-500 block text-center">Já tem conta? Entrar</router-link>
      </form>
    </main>
    <Footer />
  </div>
</template>



<script setup>
import { ref } from 'vue';
import { register } from '../api';
import { useRouter } from 'vue-router';
import Footer  from '../components/AppFooter';
import Header  from '../components/AppHeader';

const username = ref('');
const password = ref('');
const router = useRouter();

const handleRegister = async () => {
  try {
    await register(username.value, password.value);
    alert('Usuário registrado com sucesso!');
    router.push('/login');
  } catch (err) {
    console.error(err);
    alert('Erro ao registrar usuário.');
  }
};
</script>

