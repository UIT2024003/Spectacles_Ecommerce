import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './ProductList.css';

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  const addToCart = (product) => {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    const existingIndex = cart.findIndex(item => item.id === product.id);

    if (existingIndex !== -1) {
      cart[existingIndex] = {
        ...cart[existingIndex],
        quantity: cart[existingIndex].quantity + 1
      };
    } else {
      cart.push({ ...product, quantity: 1 });
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    alert("🛒 Added to cart");
  };

  useEffect(() => {
    axios.get("http://localhost:8080/products")
      .then(res => {
        console.log("DATA:", res.data);
        setProducts(res.data);
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Products 👓</h2>

      <button onClick={() => navigate("/cart")}>
        Go to Cart 🛒
      </button>

      <br/><br/>

      {products.length === 0 ? (
        <p>No products found ❌</p>
      ) : (
        <div style={{ display: "flex", gap: "20px", flexWrap: "wrap" }}>
          {products.map((p) => (
            <div key={p.id} style={{
              border: "1px solid gray",
              padding: "10px",
              width: "200px"
            }}>
              <img 
                src={p.imageUrl} 
                alt="" 
                width="100%"
                onError={(e) => e.target.src = "https://via.placeholder.com/150"}
              />
              <h3>{p.name}</h3>
              <p>{p.brand}</p>
              <p>₹{p.price}</p>
              <button onClick={() => addToCart(p)}>Add to Cart</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}