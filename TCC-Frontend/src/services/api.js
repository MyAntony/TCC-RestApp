import axios from 'axios';

const api = axios.create({
  // Coloque aqui a URL base do seu backend Java
  baseURL: 'http://localhost:8080', 
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