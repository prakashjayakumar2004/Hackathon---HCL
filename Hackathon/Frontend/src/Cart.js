import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";

function Cart() {

  const [cartItems, setCartItems] = useState([]);
  const userId = localStorage.getItem("userId") || 1;


  useEffect(() => {
    fetch(`http://localhost:8080/cart/${userId}`)
      .then((res) => res.json())
      .then((data) => setCartItems(data))
      .catch((err) => console.error(err));
  }, [userId]);

  const total = cartItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  localStorage.setItem("total", total);

  const clearCart = async () => {
    await fetch(`http://localhost:8080/cart/clear/${userId}`, {
      method: "DELETE",
    });

    setCartItems([]);
    alert("Cart Cleared 🗑️");
  };

  return (
    <div>

      <nav className="navbar navbar-dark bg-dark px-3">
        <span className="navbar-brand fw-bold">FoodMunch</span>

        <div>
          <Link to="/home" className="text-light me-3">Home</Link>
          <Link to="/orders" className="text-light me-3">Orders</Link>
        </div>
      </nav>

     
      <div className="container mt-4">

        <h2 className="text-center mb-4">My Cart</h2>

        {cartItems.length === 0 ? (
          <h5 className="text-center">Cart is empty</h5>
        ) : (
          <div className="row">
            {cartItems.map((item) => (
              <div className="col-md-3 mb-4" key={item.id}>
                <div className="card shadow p-3 text-center">

                  <h5>{item.productName}</h5>
                  <p>₹{item.price}</p>
                  <p>Qty: {item.quantity}</p>

                  <h6>
                    Total: ₹{item.price * item.quantity}
                  </h6>

                </div>
              </div>
            ))}
          </div>
        )}

        
        {cartItems.length > 0 && (
          <div className="text-center mt-4">

            <h4>Total: ₹{total}</h4>

            <Link to="/orders">
              <button className="btn btn-success m-2">
                Place Order
              </button>
            </Link>

            <button className="btn btn-danger m-2" onClick={clearCart}>
              Clear Cart
            </button>

          </div>
        )}

      </div>
    </div>
  );
}

export default Cart;