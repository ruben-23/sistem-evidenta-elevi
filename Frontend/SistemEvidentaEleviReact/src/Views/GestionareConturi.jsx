import React, { useState } from 'react';
import Table from './AdminComponents/Table';
import '../StylesViews/GestionareConturi.css';

const GestionareConturi = () => {
    const [conturi, setConturi] = useState([
        { id: 1, username: 'admin', email: 'admin@example.com', role: 'ADMIN' },
        { id: 2, username: 'professor1', email: 'professor1@example.com', role: 'PROFESOR' },
    ]);

    const [searchTerm, setSearchTerm] = useState('');

    const handleAdaugareCont = (newAccount) => {
        setConturi([...conturi, { id: new Date().getTime(), ...newAccount }]);
    };

    const handleStergereCont = (id) => {
        setConturi(conturi.filter((cont) => cont.id !== id));
    };

    const conturiFiltrate = conturi.filter(
        (cont) =>
            cont.username.toLowerCase().includes(searchTerm.toLowerCase()) ||
            cont.email.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="main-content">
            <h2>Gestioneaza Conturi</h2>
            <div className="actions-container">
                <button className="btn btn-warning" >Adaugă Cont</button>

                <input
                    type="text"
                    placeholder="Cauta dupa username sau email"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="search-bar"
                />
            </div>

            <Table
                tip="conturi"
                date={conturiFiltrate}
                onStergere={handleStergereCont}
            />
        </div>
    );
};

export default GestionareConturi;