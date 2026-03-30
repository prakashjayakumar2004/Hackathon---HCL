import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function Cart() {
  const [cartItems, setCartItems] = useState([]);

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    fetch(http://localhost:8080/cart/${userId})
      .then((res) => res.json())
      .then((data) => setCartItems(data))
      .catch((err) => console.error(err));
  }, [userId]);

  return (
    <div className="container mt-4">

      <h2 className="text-center mb-4">My Cart</h2>

      <div className="row">
        {cartItems.length === 0 ? (
          <h5 className="text-center">Cart is empty</h5>
        ) : (
          cartItems.map((item) => (
            <div className="col-md-3 mb-4" key={item.id}>
              <div className="card shadow text-center p-3">

                <h5>{item.productName}</h5>
                <p>₹{item.price}</p>
                <p>Qty: {item.quantity}</p>

              </div>
            </div>
          ))
        )}
      </div>

    </div>
  );
}
export default Cart;