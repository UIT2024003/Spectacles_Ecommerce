import { useState, useEffect } from "react";
import axios from "axios";
import './AddProduct.css';

export default function AddProduct() {

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user || user.role !== "ADMIN") {
      window.location.href = "/login";
    }
  }, []);

  const [product, setProduct] = useState({
    name: "",
    brand: "",
    price: "",
    category: "",
    imageUrl: "",
    description: "",
    stock: ""
  });

  const handleChange = (e) => {
    setProduct({
      ...product,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:8080/products", product);
      alert("✅ Product Added");
    } catch (err) {
      console.error(err);
      alert("❌ Error adding product");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Add Spectacles 👓</h2>

      <input name="name" placeholder="Name" onChange={handleChange} /><br/>
      <input name="brand" placeholder="Brand" onChange={handleChange} /><br/>
      <input name="price" placeholder="Price" onChange={handleChange} /><br/>
      <input name="category" placeholder="Category" onChange={handleChange} /><br/>
      <input name="imageUrl" placeholder="Image URL" onChange={handleChange} /><br/>
      <input name="description" placeholder="Description" onChange={handleChange} /><br/>
      <input name="stock" placeholder="Stock" onChange={handleChange} /><br/>

      <button onClick={handleSubmit}>Add Product</button>
    </div>
  );
}