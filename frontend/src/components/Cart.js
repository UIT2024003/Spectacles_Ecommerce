import { useEffect, useState } from "react";
import './Cart.css';

export default function Cart() {

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

  return (
    <div className="cart-page">
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
        </>
      )}
    </div>
  );
}