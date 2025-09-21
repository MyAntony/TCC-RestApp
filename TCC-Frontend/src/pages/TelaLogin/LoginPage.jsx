// import React, { useState } from "react";
// import axios from "axios";

// export default function LoginPage() {
//   const [username, setUsername] = useState("");
//   const [password, setPassword] = useState("");
//   const [error, setError] = useState("");

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await axios.post("http://localhost:8080/auth/login", {
//         username,
//         password,
//       });

//       // O backend devolve o token
//       const token = response.data;

//       // Guardar no localStorage
//       localStorage.setItem("token", token);

//       alert("Login realizado com sucesso!");
//       setError("");
//     } catch (err) {
//       setError("Usuário ou senha inválidos");
//     }
//   };

//   return (
//     <div className="login-container">
//       <h2>Login</h2>
//       <form onSubmit={handleSubmit}>
//         <input
//           type="text"
//           placeholder="Usuário"
//           value={username}
//           onChange={(e) => setUsername(e.target.value)}
//           required
//         />
//         <input
//           type="password"
//           placeholder="Senha"
//           value={password}
//           onChange={(e) => setPassword(e.target.value)}
//           required
//         />
//         <button type="submit">Entrar</button>
//       </form>
//       {error && <p style={{ color: "red" }}>{error}</p>}
//     </div>
//   );
// }
