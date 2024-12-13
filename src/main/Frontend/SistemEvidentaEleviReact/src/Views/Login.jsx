// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types'; 
import '../StylesViews/Login.css';

const Login = ({ onLoginSuccess }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);  
  const navigate = useNavigate();

  const mockLogin = (username, password) => {
    const mockUsers = {
      Admin: { role: 'ADMIN' },
      Profesor: { role: 'PROFESOR' },
      Elev: { role: 'ELEV' },
    };

    if (mockUsers[username] && password === 'password') {
      return mockUsers[username];
    } else {
      throw new Error('Nume Utilizator sau parola invalid');
    }
  };

  const handleLogin = (e) => {
    e.preventDefault();
    try {
      const user = mockLogin(username, password);

      onLoginSuccess(user);

      if (user.role === 'ADMIN') {
        navigate('/admin');
      } else if (user.role === 'PROFESOR') {
        navigate('/profesor');
      } else if (user.role === 'ELEV') {
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
        {error && <p className="error">{error}</p>}
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
          <button className="button" type="submit">Autentificare</button>
        </form>
      </div>
    </div>
  );
};

Login.propTypes = {
  onLoginSuccess: PropTypes.func.isRequired,
};

export default Login;
