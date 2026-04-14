import { useState } from "react";
import axios from "axios";
import './Login.css';
import { useNavigate } from "react-router-dom";

export default function Login() {

  const [data, setData] = useState({
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value
    });
  };

  const handleLogin = async () => {
    try {
      const res = await axios.post("http://localhost:8080/auth/login", data);

      if (res.data) {
        // save user in localStorage
        localStorage.setItem("user", JSON.stringify(res.data));

        // role-based redirect
        if (res.data.role === "ADMIN") {
          window.location.href = "/add";
        } else {
          window.location.href = "/products";
        }

      } else {
        alert("❌ Invalid credentials");
      }

    } catch (err) {
      console.error(err);
      alert("❌ Login failed");
    }
  };

  const navigate = useNavigate();

  return (
    
    <div className="login-page">

      <div className="top-bar">
        <button 
          className="home-btn"
          onClick={() => navigate("/")}
        >
          Home
        </button>
      </div>

       <div className="login-wrapper">
    <div className="login-container">
      <h2>Login 🔐</h2>

      <input
        name="username"
        placeholder="Username"
        onChange={handleChange}
      />

      <input
        type="password"
        name="password"
        placeholder="Password"
        onChange={handleChange}
      />

      <button onClick={handleLogin}>Login</button>

      <p className="auth-link">
        Don't have an account?{" "}
        <span onClick={() => navigate("/register")}>
          Register
        </span>
      </p>
    </div>
  </div>
    </div>
 
);
}