import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from "react-router-dom";
function Register(){
    const[name,setName]=useState("")
    const[email,setEmail]=useState("")
    const[password,setPassword]=useState("")
    const[confirmpassword,setConfirmPassword]=useState("")
    const navigate=useNavigate();
    const handleHome=async(e)=>{
        e.preventDefault(); 
        const res = await fetch("http://localhost:8080/users/add",
        {
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                name:name,
                password:password,
                email:email
            })
        })
        const data = await res.json();
        

    }
    return(
        
        <div className="d-flex align-items-center justify-content-center p-5 vh-100 " style={{backgroundColor:"#00c6ff"}}  >
            <form className="card rounded shadow-lg p-5 "style={{width:"400px"}} onSubmit={handleHome}>
            <h1 className="text-center">Register Page </h1>
            <div className="mt-3">
                <label className="fw-bold">User Name</label>
                <input type="text" placeholder="Enter User Name" className="form-control" value={name} onChange={(e)=>setName(e.target.value)} />
            </div>
            <div className="mt-3">
                <label className="fw-bold">User Email</label>
                <input type="text" placeholder="Enter User Email" className="form-control" value={email} onChange={(e)=>setEmail(e.target.value)} />
            </div>
            <div className="mt-3">
                <label className="fw-bold">User password</label>
                <input type="password" placeholder="Enter User Password" className="form-control" value={password} onChange={(e)=>setPassword(e.target.value)} />
            </div>
            <div className="mt-3">
                <label className="fw-bold">Confirm password</label>
                <input type="password" placeholder="Confirm Password" className="form-control" value={confirmpassword} onChange={(e)=>setConfirmPassword(e.target.value)}/>
            </div>
            <div className="mt-4 text-center">
                <button className="bg-primary rounded p-2 text-center text-light w-100 " style={{borderWidth:"02"}}> Click to Register</button>
            </div>
            </form>
        </div>

    )
}
export default Register;