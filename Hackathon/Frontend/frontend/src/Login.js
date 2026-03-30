import  React, { useState } from "react";

function Login(){
    const [Email,setEmail] = useState("")
    const [Password,setPassword] = useState("")

    function handleLogin(){
        if(Email ==="admin@gmail.com" && Password === "123"){
            alert("Login Success");
        } else{
            alert("Invalid Credentials");
        }
    }
    return(
        <div className="login-container">
  <div className="login-box">
    <h1>Login Page</h1>

    <p>Email</p>
    <input 
      type="text" 
      placeholder="Enter an Email Address" 
      value={Email} 
      onChange={(e)=>setEmail(e.target.value)}
    />

    <p>Password</p>
    <input 
      type="password" 
      placeholder="Enter Password" 
      value={Password}
      onChange={(e)=>setPassword(e.target.value)}
    />

    <button onClick={handleLogin}>Login</button>
  </div>
</div>
    )
}
export default Login;