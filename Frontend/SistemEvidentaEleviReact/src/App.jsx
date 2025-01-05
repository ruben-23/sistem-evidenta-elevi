import { Routes, Route } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './Views/Login.jsx';
import AdminDashboard from './Views/AdminDashboard';
import ProfesorDashboard from './Views/ProfesorDashboard';
import ElevDashboard from './Views/ElevDashboard';
import GestionareConturi from "./Views/GestionareConturi.jsx";
import GestionareProfesori from "./Views/GestionareProfesor.jsx";
import GestionareElevi from "./Views/GestionareElevi.jsx";

/**
 * Componenta principala a aplicatiei.
 *
 * Aceasta componenta gestioneaza rutele aplicatiei, permite navigarea intre
 * paginile ale aplicatiei: pagina de login si dashboard-urile pentru administratori,
 * profesori si elevi.
 *
 * @component
 * @returns {JSX.Element} Elementul principal al aplicatiei care contine rutele.
 */
const App = () => {

    return (
        <div className="app">
            <Routes>
                <Route path="/" element={<Login />} />
                <Route
                    path="/admin"
                    element={<AdminDashboard />}
                />
                <Route
                    path="/profesor"
                    element={<ProfesorDashboard />}
                />
                <Route path="/elev" element={<ElevDashboard />} />
                <Route path="/admin/students" element={<GestionareElevi />} />
                <Route path="/admin/teachers" element={<GestionareProfesori />} />
                <Route path="/admin/accounts" element={<GestionareConturi />} />
            </Routes>
        </div>
    );
};

export default App;
