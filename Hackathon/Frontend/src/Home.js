import React,{useEffect,useState} from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";

function Home(){
const[search,setSearch]=useState("");
const[foods,setFoods]=useState([]);
const[filter,setFilter]=useState("all");

useEffect(()=>{
fetch("http://localhost:8082/products")
.then(res=>res.json())
.then(data=>setFoods(data));
},[]);
const handleCart=(e)=>{
    e.preventDefault();
    alert("item cicked")
}
const filteredFoods=foods.filter((item)=>item.name.toLowerCase().includes(search.toLowerCase())).filter((item)=>{
if(filter==="all") return true;
return item.category.toLowerCase()===filter;
});

return(
<div>

<nav className="navbar navbar-dark navbar-expand-lg bg-dark px-3">
<div className="container-fluid">
<span className="navbar-brand fw-bold">FoodMunch</span>
<div className="d-flex gap-3 align-items-center">
<Link to="/Cart" className="text-light text-decoration-none">My Cart</Link>

<Link to="/confirmPage" className="text-light text-decoration-none">Orders</Link>
<Link to="/register"><button className="btn btn-danger">Logout</button></Link>
</div>
</div>
</nav>

<div className="container mt-4">
<input type="text" className="form-control" placeholder="Search food..." value={search} onChange={(e)=>setSearch(e.target.value)}/>
</div>



<div className="container mt-4">
<div className="row">
{filteredFoods.map((item)=>(
<div className="col-md-3 mb-4" key={item.id}>
<div className="card shadow">
<div className="card-body text-center">
<h5 className="card p-5">{item.name}</h5>
<p>{item.brand}</p>
<p>₹{item.price}</p>
<button className="btn btn-primary w-100" onClick={handleCart}>Add to Cart</button>
</div>
</div>
</div>
))}
</div>
</div>

</div>
);
}

export default Home;