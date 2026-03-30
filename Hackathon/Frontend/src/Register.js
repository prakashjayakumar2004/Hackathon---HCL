import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";

function Register() {

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmpassword, setConfirmPassword] = useState("");

  const navigate = useNavigate();

  const handleHome = async (e) => {
    e.preventDefault();

 
    if (password !== confirmpassword) {
      alert("Passwords do not match ❌");
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/users/register", {
        method: "POST",   
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: name,
          email: email,
          password: password
        })
      });
     
      const data = await res.json();


      localStorage.setItem("userId", data.id);

      alert("Registered Successfully ");

      navigate("/home");

    } catch (error) {
      console.error(error);
      alert("Error: " + error.message);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100" style={{ backgroundColor: "#00c6ff" }}>

      <form className="card p-4 shadow-lg" style={{ width: "400px" }} onSubmit={handleHome}>

        <h2 className="text-center mb-3">Register</h2>

        <input
          type="text"
          placeholder="Enter Name"
          className="form-control mb-3"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <input
          type="email"
          placeholder="Enter Email"
          className="form-control mb-3"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Enter Password"
          className="form-control mb-3"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <input
          type="password"
          placeholder="Confirm Password"
          className="form-control mb-3"
          value={confirmpassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />

        <button className="btn btn-primary w-100">
          Register
        </button>

      </form>

    </div>
  );
}

export default Register;