import { useState } from "react";
import axios from "axios"; // Import Axios
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/v1/auth/Authenticate', {
        email: email,
        password: password
      });

      const userData = response.data;
      const token = userData.token;

      // Save token to local storage
      localStorage.setItem('token', token);

      // Now you can handle the user data as per your requirements
      console.log(userData);

      // Redirect to another page after successful login
      navigate('/Jobs'); // Change '/dashboard' to your desired route
    } catch (error) {
      console.error('Login error:', error.message);
      // Handle the error appropriately, e.g., display an error message to the user
    }
  };

  return ( 
    <>
      <meta charSet="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta httpEquiv="X-UA-Compatible" content="ie=edge" />
      <link rel="stylesheet" href="assets/reg/css/style.css" />
      <title>Login Form</title>
      <div className="main">
        <section className="sign-in">
          <div className="container">
            <div className="signin-content">
              <div className="signin-image">
                <figure>
                  <img src="assets/reg/images/signin-image.jpg" alt="sing up image" />
                </figure>
                <Link to="/SignUp" className="signup-image-link">
                  Create an account
                </Link>
              </div>
              <div className="signin-form">
                <h2 className="form-title">Login</h2>
                <form method="POST" className="register-form" onSubmit={handleSubmit} id="login-form">
                  <div className="form-group">
                    <label htmlFor="your_name">
                      <i className="zmdi zmdi-account material-icons-name" />
                    </label>
                    <input
                      type="text"
                      name="your_name"
                      onChange={(e) => setEmail(e.target.value)}
                      value={email} 
                      id="your_name"
                      placeholder="Your Email"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="your_pass">
                      <i className="zmdi zmdi-lock" />
                    </label>
                    <input
                      type="password"
                      name="your_pass"
                      onChange={(e) => setPassword(e.target.value)}
                      value={password} 
                      id="your_pass"
                      placeholder="Password"
                    />
                  </div>
                  <div className="form-group form-button">
                    <input
                      type="submit"
                      name="signin"
                      id="signin"
                      className="form-submit"
                      value="Log in"
                    />
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>
      </div>
    </>  
  );
}

export default Login;
