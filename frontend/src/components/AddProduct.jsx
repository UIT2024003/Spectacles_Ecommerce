import { useState, useEffect } from "react";
import axios from "axios";
import "./AddProduct.css";
import { useNavigate } from "react-router-dom";

export default function AddProduct() {
  const navigate = useNavigate();
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
    stock: "",
  });

  const [file, setFile] = useState(null);

  const handleChange = (e) => {
    setProduct({
      ...product,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async () => {
    try {
      const formData = new FormData();

      formData.append("file", file);
      formData.append("name", product.name);
      formData.append("brand", product.brand);
      formData.append("price", product.price);
      formData.append("category", product.category);
      formData.append("description", product.description);
      formData.append("stock", product.stock);

      await axios.post("http://localhost:8080/products/upload", formData);

      alert("✅ Product Added");
    } catch (err) {
      console.error(err);
      alert("❌ Error adding product");
    }
  };

  return (
    <div className="add-product-page">
      <button 
        className="back-btn"
        onClick={() => navigate("/")}
      >
        ← Back to Home
      </button>
      <div className="add-product-card">
        <h2>Add Spectacles </h2>

        <input name="name" placeholder="Name" onChange={handleChange} />
        <input name="brand" placeholder="Brand" onChange={handleChange} />
        <input name="price" placeholder="Price" onChange={handleChange} />
        <input name="category" placeholder="Category" onChange={handleChange} />
        <input type="file" onChange={(e) => setFile(e.target.files[0])} />
        <input
          name="description"
          placeholder="Description"
          onChange={handleChange}
        />
        <input name="stock" placeholder="Quantity" onChange={handleChange} />

        <button onClick={handleSubmit}>Add Product</button>
      </div>
    </div>
  );
}
