<template>
  <form @submit.prevent="submit" class="space-y-3">
    <input v-model="transfer.sourceAccount" placeholder="Conta origem" class="border p-2 w-full rounded" />
    <input v-model="transfer.destinationAccount" placeholder="Conta destino" class="border p-2 w-full rounded" />
    <input v-model="transfer.amount" type="number" placeholder="Valor" class="border p-2 w-full rounded" />
    <input v-model="transfer.transferDate" type="date" class="border p-2 w-full rounded" />
    <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded w-full">Agendar</button>

    <p v-if="fee" class="text-green-600 mt-2">
      Transferência agendada com taxa: R$ {{ fee.toFixed(2) }}
    </p>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { scheduleTransfer } from "../api/index";

const emit = defineEmits(["transfer-scheduled"]);

const transfer = ref({
  sourceAccount: "",
  destinationAccount: "",
  amount: "",
  transferDate: ""
});

const fee = ref(null);

const submit = async () => {
  try {
    const res = await scheduleTransfer(transfer.value);

    fee.value = res.fee;
    emit("transfer-scheduled");

    transfer.value = {
      sourceAccount: "",
      destinationAccount: "",
      amount: "",
      transferDate: ""
    };
  } catch (err) {
    alert("Erro ao agendar transferência: " + err.message);
  }
};
</script>
