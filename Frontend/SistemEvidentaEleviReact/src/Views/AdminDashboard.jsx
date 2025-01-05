import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from './AdminComponents/Sidebar';
import GestionareElevi from './GestionareElevi.jsx';
import GestionareProfesori from './GestionareProfesor.jsx';
import GestionareConturi from './GestionareConturi.jsx';
import NavbarLiceu from "./Navbar.jsx";
import '../StylesViews/AdminDashboard.css';

/**
 * Componenta principala pentru dashboard-ul administratorului.
 *
 * Aceasta componenta administreaza navigarea intre diferitele sectiuni ale interfetei
 * pentru administrator, incluzand gestionarea elevilor, profesorilor si conturilor.
 * De asemenea, ofera functionalitatea de deconectare.
 *
 * @component
 * @returns {JSX.Element} Interfata pentru dashboard-ul administratorului.
 */
const AdminDashboard = () => {

    // starea care determina sectiunea curenta afisata in dashboard.
    const [vedereCurenta, setVedereCurenta] = useState('elevi');

    // hook pentru navigare.
    const navigate = useNavigate();

    /**
     * Navigheaza utilizatorul catre pagina de login (deconectare).
     */
    const handleLogout = () => navigate('/');

    /**
     * Randeaza sectiunea principala pe baza optiunii curente selectate in sidebar.
     *
     * @returns {JSX.Element} Componenta asociata cu optiunea curenta.
     */
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