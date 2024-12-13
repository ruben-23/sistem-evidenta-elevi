// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';
const Sidebar = ({ currentView, setCurrentView, onLogout }) => (
    <div className="dashboard-sidebar">
        <div className="dashboard-buttons">
            {['accounts', 'students', 'teachers'].map((view) => (
                <button
                    key={view}
                    onClick={() => setCurrentView(view)}
                    className={currentView === view ? 'active' : ''}
                >
                    Gestioneaza {view}
                </button>
            ))}
            <button onClick={onLogout}>Logout</button>
        </div>
    </div>
);

Sidebar.propTypes = {
    currentView: PropTypes.string.isRequired,
    setCurrentView: PropTypes.func.isRequired,
    onLogout: PropTypes.func.isRequired,
};

export default Sidebar;
