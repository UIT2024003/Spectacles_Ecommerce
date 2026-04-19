import { useEffect, useState } from "react";
import './Cart.css';
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Cart() {

  const navigate = useNavigate();
  const [cart, setCart] = useState([]);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
      setCart([]);
      return;
    }

    const cartKey = `cart_${user.username}`;
    const data = JSON.parse(localStorage.getItem(cartKey)) || [];

    setCart(data);
  }, []);

  const removeItem = (id) => {
    const updated = cart.filter(item => item.id !== id);
    setCart(updated);
    const user = JSON.parse(localStorage.getItem("user"));
    const cartKey = `cart_${user.username}`;
    localStorage.setItem(cartKey, JSON.stringify(updated));
  };

  const changeQty = (id, type) => {
    const updated = cart.map(item => {
      if (item.id === id) {
        if (type === "inc") item.quantity += 1;
        if (type === "dec" && item.quantity > 1) item.quantity -= 1;
      }
      return item;
    });

    setCart(updated);
    const user = JSON.parse(localStorage.getItem("user"));
    const cartKey = `cart_${user.username}`;
    localStorage.setItem(cartKey, JSON.stringify(updated));
  };

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const placeOrder = async () => {
    const user = JSON.parse(localStorage.getItem("user"));

    await axios.post("http://localhost:8080/orders", {
      username: user.username,
      total: total
    });

    alert("Order placed successfully ✅");

    // optional: clear cart
    localStorage.removeItem(`cart_${user.username}`);
    setCart([]);
  };

  return (
    <div className="cart-page">
      <button 
        className="back-container"
        onClick={() => navigate("/products")}
      >
        ← Back to Products
      </button>
      <h2 className="cart-title">🛒 Your Cart</h2>

      {cart.length === 0 ? (
        <p className="empty-cart">Cart is empty ❌</p>
      ) : (
        <>
          {cart.map(item => (
            <div key={item.id} className="cart-item">
              
              <h3>{item.name}</h3>
              <p>₹{item.price}</p>

              <div className="qty-controls">
                <button onClick={() => changeQty(item.id, "dec")}>-</button>
                <span>{item.quantity}</span>
                <button onClick={() => changeQty(item.id, "inc")}>+</button>
              </div>

              <button 
                className="remove-btn"
                onClick={() => removeItem(item.id)}
              >
                Remove ❌
              </button>

            </div>
          ))}

          <h3 className="cart-total">Total: ₹{total}</h3>
          <button 
            className="checkout-btn"
            onClick={placeOrder}
          >
            Checkout 🧾
          </button>
        </>
      )}
    </div>
  );
}