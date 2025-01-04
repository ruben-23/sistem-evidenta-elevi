import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from './AdminComponents/Sidebar';
import GestionareElevi from './GestionareElevi.jsx';
import GestionareProfesori from './GestionareProfesor.jsx';
import GestionareConturi from './GestionareConturi.jsx';
import NavbarLiceu from "./Navbar.jsx";
import '../StylesViews/AdminDashboard.css';

const AdminDashboard = () => {
    const [vedereCurenta, setVedereCurenta] = useState('elevi');
    const navigate = useNavigate();

    const handleLogout = () => navigate('/');

    const renderMainContent = () => {
        switch (vedereCurenta) {
            case 'elevi':
                return <GestionareElevi />;
            case 'profesori':
                return <GestionareProfesori />;
            case 'conturi':
                return <GestionareConturi />;
            default:
                return <div>Selecteaza o optiune din sidebar</div>;
        }
    };

    return (
        <div className="admin-dashboard">
            <NavbarLiceu />

            <div className="admin-content">
                <Sidebar
                    vedereCurenta={vedereCurenta}
                    setVedereCurenta={setVedereCurenta}
                    onLogout={handleLogout}
                />

                <div className="main-content">
                    {renderMainContent()}
                </div>

            </div>
        </div>
    );
};

export default AdminDashboard;