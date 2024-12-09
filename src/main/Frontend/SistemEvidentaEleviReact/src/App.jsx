import { Routes, Route, useNavigate } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import './App.css';
import Login from './Views/Login.jsx';
import AdminDashboard from './Views/AdminDashboard';
import ProfesorDashboard from './Views/ProfesorDashboard';
import ElevDashboard from './Views/ElevDashboard';

const App = () => {
    // eslint-disable-next-line no-unused-vars
    const [loggedInUser, setLoggedInUser] = useState(null);
    const navigate = useNavigate();

    const handleLoginSuccess = (user) => {
        setLoggedInUser(user);
        if (user.role === 'ADMIN') {
            navigate('/admin');
        } else if (user.role === 'PROFESOR') {
            navigate('/profesor');
        } else if (user.role === 'ELEV') {
            navigate('/elev');
        }
    };

    return (
        <div className="app">
            {/*{!loggedInUser ? (*/}
            {/*    <h3 className="welcome-message">Welcome, User!</h3>*/}
            {/*) : (*/}
            {/*    <h3 className="welcome-message">Welcome, {loggedInUser.role}!</h3>*/}
            {/*)}*/}

            <Routes>
                <Route path="/" element={<Login onLoginSuccess={handleLoginSuccess} />} />
                <Route
                    path="/admin"
                    element={<AdminDashboard />}
                />
                <Route
                    path="/profesor"
                    element={<ProfesorDashboard />}
                />
                <Route path="/elev" element={<ElevDashboard />} />
            </Routes>
        </div>
    );
};

export default App;
