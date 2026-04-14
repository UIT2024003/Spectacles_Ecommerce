import { useState } from "react";
import axios from "axios";
import "./Login.css";
import { useNavigate } from "react-router-dom";

export default function Register() {

  const [data, setData] = useState({
    username: "",
    password: "",
    role: "USER"
  });

  const handleChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value
    });
  };

  const handleRegister = async () => {
    try {
      await axios.post("http://localhost:8080/auth/register", data);
      alert("✅ Registration Successful");
      window.location.href = "/login";
    } catch (err) {
      console.error(err);

      if (err.response && err.response.data) {
        alert(err.response.data);
      } else {
        alert("❌ Registration Failed");
      }
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

        <h2>Register 📝</h2>

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

        <select name="role" onChange={handleChange}>
          <option value="USER">User</option>
          <option value="ADMIN">Admin</option>
        </select>

        <button onClick={handleRegister}>Register</button>

        <p className="auth-link">
          Already have an account?{" "}
          <span onClick={() => navigate("/login")}>
            Login
          </span>
        </p>

      </div>
    </div>
  </div>
);
}