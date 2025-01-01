import React from 'react';
import '../../StylesViews/StyleComponents/Sidebar.css';
import {useUser} from "../../UserContext.jsx";

const Sidebar = ({ vedereCurenta, setVedereCurenta, onLogout }) => {
    const { user } = useUser ();

    return (
        <div className="dashboard-sidebar">

            <div className="dashboard-buttons">
                <button
                    onClick={() => setVedereCurenta('elevi')}
                    className={vedereCurenta === 'elevi' ? 'active' : ''}
                >
                    Gestionare Elevi
                </button>

                {user && user.rol === 'ROLE_SECRETARA' && (
                    <>
                        <button
                            onClick={() => setVedereCurenta('profesori')}
                            className={vedereCurenta === 'profesori' ? 'active' : ''}

                        >
                            Gestionare Profesori
                        </button>
                        <button
                            onClick={() => setVedereCurenta('conturi')}
                            className={vedereCurenta === 'conturi' ? 'active' : ''}
                        >
                            Gestionare Conturi
                        </button>
                    </>
                )}
                <button >Rapoarte</button>
            </div>
        </div>
    );
};

export default Sidebar;