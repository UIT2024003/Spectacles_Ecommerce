import { useEffect, useState } from "react";
import './Cart.css';

export default function Cart() {

  const [cart, setCart] = useState([]);

  useEffect(() => {
    const data = JSON.parse(localStorage.getItem("cart")) || [];
    console.log("🛒 CART DATA:", data); // DEBUG
    setCart(data);
  }, []);

  const removeItem = (id) => {
    const updated = cart.filter(item => item.id !== id);
    setCart(updated);
    localStorage.setItem("cart", JSON.stringify(updated));
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
    localStorage.setItem("cart", JSON.stringify(updated));
  };

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  return (
    <div style={{ padding: "20px" }}>
      <h2>🛒 Your Cart</h2>

      {cart.length === 0 ? (
        <p>Cart is empty ❌</p>
      ) : (
        <>
          {cart.map(item => (
            <div key={item.id} style={{
              border: "1px solid gray",
              margin: "10px",
              padding: "10px"
            }}>
              <h3>{item.name}</h3>
              <p>₹{item.price}</p>

              <button onClick={() => changeQty(item.id, "dec")}>-</button>
              <span style={{ margin: "0 10px" }}>{item.quantity}</span>
              <button onClick={() => changeQty(item.id, "inc")}>+</button>

              <br/><br/>

              <button onClick={() => removeItem(item.id)}>
                Remove ❌
              </button>
            </div>
          ))}

          <h3>Total: ₹{total}</h3>
        </>
      )}
    </div>
  );
}