import './Home.css';
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import design from '../images/design.png';
import price from '../images/price.png';
import durable from '../images/durable.png';
import delivery from '../images/delivery.png';

export default function Home() {
  const navigate = useNavigate();
  const [showDropdown, setShowDropdown] = useState(false);

  return (
    <div className="home">

      {/* Navbar */}
      <div className="navbar">
       <h2 className="logo">
  Vision<span>X</span> 
</h2>

        <div 
          className="login-container"
          onMouseEnter={() => setShowDropdown(true)}
          onMouseLeave={() => setShowDropdown(false)}
        >
          <button className="login-btn">Login ⌄</button>

          {showDropdown && (
            <div className="dropdown">
              <p onClick={() => navigate('/login-user')}>Login as User</p>
              <p onClick={() => navigate('/login')}>Login as Admin</p>
            </div>
          )}
        </div>
      </div>

      {/* Hero Section */}
      <div className="hero">
        <h1 className='main-text'>VisionX Spectacles </h1>
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
    <img src={design} alt="design" />
    <h3>Premium Design</h3>
    <p>Trendy and modern frames that match your personality.</p>
  </div>

  <div className="feature-card">
    <img src={price} alt="price" />
    <h3>Best Prices</h3>
    <p>High-quality spectacles at prices you’ll love.</p>
  </div>

  <div className="feature-card">
    <img src={durable} alt="durable" />
    <h3>Durable Build</h3>
    <p>Strong and long-lasting frames for everyday use.</p>
  </div>

  <div className="feature-card">
    <img src={delivery} alt="delivery" />
    <h3>Fast Delivery</h3>
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