import React, { useState } from 'react';
import Table from './AdminComponents/Table';

const GestionareProfesori = () => {
    const [profesori, setProfesori] = useState([
        { id: 1, name: 'Ion Popescu', email: 'ionpopescu@example.com' },
        { id: 2, name: 'Sebastian Sava', email: 'sebastiansava@example.com' },
    ]);

    const handleAdaugareProfesor = (profesorNou) => {
        setProfesori([...profesori, { id: new Date().getTime(), ...profesorNou }]);
    };

    const handleStergereProfesor = (id) => {
        setProfesori(profesori.filter((profesor) => profesor.id !== id));
    };

    return (
        <div>
            <h2>Gestioneaza Profesori</h2>

            <button className="btn btn-warning">
                Adaugă Profesor
            </button>

            <Table
                tip="profesori"
                date={profesori}
                onStergere={handleStergereProfesor}
            />
        </div>
    );
};

export default GestionareProfesori;