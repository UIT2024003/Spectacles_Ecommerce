import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ProductList.css";

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  const addToCart = (product) => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
      alert("Please login first ❌");
      return;
    }

    const cartKey = `cart_${user.username}`;

    let cart = JSON.parse(localStorage.getItem(cartKey)) || [];

    const existingIndex = cart.findIndex((item) => item.id === product.id);

    if (existingIndex !== -1) {
      cart[existingIndex] = {
        ...cart[existingIndex],
        quantity: cart[existingIndex].quantity + 1,
      };
    } else {
      cart.push({ ...product, quantity: 1 });
    }

    localStorage.setItem(cartKey, JSON.stringify(cart));
    alert("🛒 Added to cart");
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/products")
      .then((res) => {
        console.log("DATA:", res.data);
        setProducts(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <button 
        //className="home-btn"
        className="button"
        onClick={() => navigate("/")}
      >
        ← Back to Home
      </button>
      <h2>Products 👓</h2>

      <button onClick={() => navigate("/cart")}>Go to Cart 🛒</button>

      <br />
      <br />

      {products.length === 0 ? (
        <p>No products found ❌</p>
      ) : (
        <div className="product-container">
          {products.map((p) => (
            <div key={p.id} className="product-card">
              {/* 👇 Wrap content */}
              <div>
                <img
                  src={`http://localhost:8080/${p.imageUrl}`}
                  alt=""
                  loading="lazy"
                  className="product-img"
                  onLoad={(e) => (e.target.style.opacity = 1)}
                  onError={(e) =>
                    (e.target.src = "https://via.placeholder.com/150")
                  }
                />
                <h3>{p.name}</h3>
                <p>{p.brand}</p>
                <p>₹{p.price}</p>
              </div>

              {/* 👇 Button stays at bottom */}
              <button onClick={() => addToCart(p)}>Add to Cart</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
