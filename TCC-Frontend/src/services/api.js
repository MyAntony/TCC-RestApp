import axios from 'axios';

const api = axios.create({
  // Coloque aqui a URL base do seu backend Java
  baseURL: /*import.meta.env.VITE_API_URL || */'https://restapp-backend.onrender.com', /* Para funcionar no deploy ou localmente automaticamente */
});

// Interceptor para adicionar o token a cada requisição (se ele existir)
api.interceptors.request.use(async config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;