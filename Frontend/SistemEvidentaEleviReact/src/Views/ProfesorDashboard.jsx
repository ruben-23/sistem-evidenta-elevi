import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import Sidebar from "./AdminComponents/Sidebar.jsx";
import GestionareElevi from "./GestionareElevi.jsx";
import '../StylesViews/AdminDashboard.css';
import NavbarLiceu from "./Navbar.jsx";

const ProfesorDashboard = () => {
  const [vedereCurenta, setVedereCurenta] = useState('elevi');
  const navigate = useNavigate();

  const handleLogout = () => navigate('/');

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