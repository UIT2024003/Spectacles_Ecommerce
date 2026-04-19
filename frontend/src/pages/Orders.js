import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Orders.css";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) return;

    axios
      .get(`http://localhost:8080/orders/${user.username}`)
      .then((res) => setOrders(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="orders-page">

      {/* Back Button */}
      <div className="back-container">
        <button className="back-btn" onClick={() => navigate("/")}>
          ← Back to Home
        </button>
      </div>

      {/* Title */}
      <h2 className="orders-title">📦 Your Orders</h2>

      {/* Orders */}
      {orders.length === 0 ? (
        <p className="empty-orders">No orders yet ❌</p>
      ) : (
        <div className="orders-container">
          {orders.map((order) => (
            <div key={order.id} className="order-card">

              <div className="order-header">
                <span>Order #{order.id}</span>
              </div>

              <div className="order-body">
                <p><strong>User:</strong> {order.username}</p>
                <p><strong>Total:</strong> ₹{order.total}</p>
              </div>

              <div className="order-footer">
                <span className="status">Placed ✅</span>
              </div>

            </div>
          ))}
        </div>
      )}
    </div>
  );
}