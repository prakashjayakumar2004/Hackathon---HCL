import React from "react";
import { Link } from "react-router-dom";

function Order(){

    const total = localStorage.getItem("total");

    return(
        <div>

        <nav className="navbar navbar-expand-lg bg-dark navbar-dark px-4" >
            <Link to="/home" className="navbar-brand fw-bold text-white">
                FoodMunch
            </Link>

            <div className="ms-auto">
                
                <Link to="/login">
                    <button className="btn btn-danger">Logout</button>
                </Link>
            </div>
        </nav>

        <div className="d-flex justify-content-center align-items-center vh-100 ">

            <div className="card p-5 shadow-lg bg-dark" >

                <h2 className="text-white fw-bold text-center">
                    Order Placed Successfully!! 
                </h2>

                <h5 className="text-white text-center mt-2">
                    Thank you for ordering with Swiggy.
                </h5>

                <hr className="text-white"/>

                <h4 className="text-white text-center">
                    Payment : Cash On Delivery
                </h4>

                <h5 className="text-white text-center mt-2">
                    Total Amount : ₹{total}
                </h5>

                <p className="btn btn-warning fw-bold mt-3">
                    Your order will arrive in 30-40 minutes.
                </p>

                <div className="text-center mt-3">
                    <Link to="/cart">
                        <button className="btn btn-light ms-2">View Cart</button>
                    </Link>

                    <Link to="/home">
                        <button className="btn btn-light ms-2">Continue Order</button>
                    </Link>
                </div>

            </div>

        </div>
        </div>
    );
}

export default Order;