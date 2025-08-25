<template>
  <div class="flex flex-col min-h-screen">
    <Header :showLogout="true" />
    <main class="flex-grow max-w-xl mx-auto p-6">
      <h2 class="text-xl font-bold mb-4">Agendar Transferência</h2>
      <TransferForm @transfer-scheduled="loadTransfers" />

      <h3 class="text-lg font-semibold mt-6">Transferências Agendadas</h3>
      <ul class="mt-2">
        <li v-for="t in transfers" :key="t.id" class="border-b py-2">
          <span class="font-medium">R$ {{ t.amount }}</span> → {{ t.destinationAccount }}
          <span class="text-sm text-gray-500">({{ t.transferDate }})</span>
        </li>
      </ul>
    </main>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useAuthStore } from "../store/auth";
import { api } from "../api/index";
import Header from "../components/AppHeader.vue";
import Footer from "../components/AppFooter.vue";
import TransferForm from "../components/TransferForm.vue";

const transfers = ref([]);
const auth = useAuthStore();

const setAuthToken = (token) => {
  if (token) {
    api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    delete api.defaults.headers.common['Authorization'];
  }
};

const getTransfers = async () => {
  const res = await api.get("/transfers");
  return res.data;
};

const loadTransfers = async () => {
  try {
    setAuthToken(auth.token);
    transfers.value = await getTransfers();
  } catch (err) {
    console.error("Não foi possível carregar transferências:", err);
  }
};

onMounted(loadTransfers);
</script>
