import axios from 'axios';
import { useAuthStore } from '../store/auth';

const API_URL = 'http://localhost:8080';

export const api = axios.create({
  baseURL: API_URL,
});

export const login = async (username, password) => {
  const res = await api.post('/auth/login', { username, password });
  return res.data; // { token: "..." }
};

export const register = async (username, password) => {
  const res = await api.post('/auth/register', { username, password });
  return res.data;
};

export const scheduleTransfer = async (transfer) => {
  const authStore = useAuthStore();

  if (!authStore.token) {
    throw new Error('Usuário não logado!');
  }

  const res = await api.post('/transfers', transfer, {
    headers: { Authorization: `Bearer ${authStore.token}` },
  });

  return res.data;
};
