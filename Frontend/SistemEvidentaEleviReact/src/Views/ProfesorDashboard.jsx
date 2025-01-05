import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import Sidebar from "./AdminComponents/Sidebar.jsx";
import GestionareElevi from "./GestionareElevi.jsx";
import '../StylesViews/AdminDashboard.css';
import NavbarLiceu from "./Navbar.jsx";

/**
 * Componenta principala pentru dashboard-ul al profesorului.
 *
 * Aceasta componenta ofera o interfata pentru gestionarea elevilor si alte functionalitati
 * accesibile profesorului. Include o bara laterala lateral (sidebar) pentru navigarea intre diferite sectiuni
 * si o bara de navigare.
 *
 * @component
 * @returns {JSX.Element} dashboard-ul al profesorului.
 */
const ProfesorDashboard = () => {
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
      case 'rapoarte':
        return <div>rapoarte</div>;
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

}

export default ProfesorDashboard;