// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../StylesViews/Login.css';
import {useUser} from "../UserContext.jsx";

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const navigate = useNavigate();

  const { setUser  } = useUser ();

  const loginUser  = async (username, password) => {
    try {
      const response = await fetch('http://localhost:8080/liceu/autentificare/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, parola: password }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error("eroare:", errorData);
        throw new Error(errorData.message || 'eroare necunoscuta');
      }

      return await response.json();
    } catch (error) {
      console.error('Eroare login:', error);
      throw error;
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const user = await loginUser(username, password);

      setUser(user);

      if (user.rol === 'ROLE_SECRETARA') {
        navigate('/admin');
      } else if (user.rol === 'ROLE_PROFESOR') {
        navigate('/profesor');
      } else if (user.rol === 'ROLE_ELEV') {
        navigate('/elev');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  return (
      <div className="login-container">
        <div className="form-wrapper">
          <h2 className="header">Autentificare</h2>
          {error && <p className="error">{"Nume utilizator sau parola incorecte"}</p>}
          <form onSubmit={handleLogin}>
            <div className="input-group">
              <label className="label">Nume Utilizator:</label>
              <input
                  className="input"
                  type="text"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
              />
            </div>
            <div className="input-group">
              <label className="label">Parola:</label>
              <div className="password-wrapper">
                <input
                    className="input"
                    type={passwordVisible ? 'text' : 'password'}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button
                    type="button"
                    className="eye-button"
                    onClick={() => setPasswordVisible(!passwordVisible)}
                >
                  {passwordVisible ? '🙈' : '👁️'}
                </button>
              </div>
            </div>
            <button className="btn btn-warning button" >Autentificare</button>
          </form>
        </div>
      </div>
  );
};

export default Login;
