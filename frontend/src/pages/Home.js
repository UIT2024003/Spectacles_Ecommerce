import './Home.css';
import { useNavigate } from "react-router-dom";

export default function Home() {
  const navigate = useNavigate();

  return (
    <div className="home">

      {/* Hero Section */}
      <div className="hero">
        <h1>VisionX Spectacles 👓</h1>
        <p>
          See Better. Look Better.
          Discover stylish frames crafted for your everyday life.
        </p>

        <button className="shop-btn" onClick={() => navigate('/products')}>
          Shop Now
        </button>
      </div>

      {/* Features Section */}
      <div className="features">

        <div className="feature-card">
          <h3>✨ Premium Design</h3>
          <p>Trendy and modern frames that match your personality.</p>
        </div>

        <div className="feature-card">
          <h3>💸 Best Prices</h3>
          <p>High-quality spectacles at prices you’ll love.</p>
        </div>

        <div className="feature-card">
          <h3>🛡️ Durable Build</h3>
          <p>Strong and long-lasting frames for everyday use.</p>
        </div>

        <div className="feature-card">
          <h3>🚚 Fast Delivery</h3>
          <p>Quick and reliable delivery to your doorstep.</p>
        </div>

      </div>

      {/* Footer */}
      <div className="footer">
        <p>© 2026 VisionX Spectacles | Made with 💙</p>
      </div>

    </div>
  );
}