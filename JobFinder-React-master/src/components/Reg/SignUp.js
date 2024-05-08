import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const SignUp = () => {
  const navigate = useNavigate();
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      await axios.post('http://localhost:8080/api/v1/auth/register', {
        firstName,
        lastName,
        email,
        password
      });
      // Optionally, you can reset the form fields here
      setFirstName('');
      setLastName('');
      setEmail('');
      setPassword('');
      navigate('/');
      alert('User added successfully!');
    } catch (error) {
      console.error('Error adding user:', error);
      alert('Error adding user. Please try again later.');
    }
  };

  return (
    <>
      {/* Your existing HTML and CSS */}
      <link rel="stylesheet" href="assets/reg/css/style.css" />
      <div className="main">
        <section className="signup">
          <div className="container">
            <div className="signup-content">
              <div className="signup-form">
                <h2 className="form-title">Sign up</h2>
                <form method="POST" className="register-form" onSubmit={handleSubmit} id="register-form">
                  {/* Form fields */}
                  <div className="form-group">
                    <label htmlFor="firstName"></label>
                    <input
                      type="text"
                      name="firstName"
                      id="firstName"
                      value={firstName}
                      onChange={(e) => setFirstName(e.target.value)}
                      placeholder="Your First Name"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="lastName"></label>
                    <input
                      type="text"
                      name="lastName"
                      id="lastName"
                      value={lastName}
                      onChange={(e) => setLastName(e.target.value)}
                      placeholder="Your Last Name"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="email"></label>
                    <input
                      type="email"
                      name="email"
                      id="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      placeholder="Your Email"
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="password"></label>
                    <input
                      type="password"
                      name="password"
                      id="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      placeholder="Password"
                    />
                  </div>
                  {/* Submit button */}
                  <div className="form-group form-button">
                    <input
                      type="submit"
                      name="signup"
                      id="signup"
                      className="form-submit"
                      value="Register"
                    />
                  </div>
                </form>
              </div>
              {/* Your existing image */}
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default SignUp;
