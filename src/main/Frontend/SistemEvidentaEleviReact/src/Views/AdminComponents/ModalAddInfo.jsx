// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ModalAddInfo.css';

const ModalAddInfo = ({ onClose, onSave }) => {
    const [formData, setFormData] = useState({
        name: '',
        prenume: '',
        cnp: '',
        adresa: '',
        dataNasterii: '',
        parinteNumarTelefon: '',
        elevNumarTelefon: '',
        sex: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSave = () => {
        onSave(formData); // Pass form data to the parent
    };

    return (
        <div className="modal-overlay">
            <div className="modal-container">
                <div className="modal-header">
                    <h2>Adaugă Elev</h2>
                    <button className="modal-close-btn" onClick={onClose}>X</button>
                </div>
                <div className="modal-body">
                    <input name="name" type="text" placeholder="Nume" onChange={handleChange} value={formData.name} />
                    <input name="prenume" type="text" placeholder="Prenume" onChange={handleChange} value={formData.prenume} />
                    <input name="cnp" type="text" placeholder="CNP" onChange={handleChange} value={formData.cnp} />
                    <input name="adresa" type="text" placeholder="Adresa" onChange={handleChange} value={formData.adresa} />
                    <input name="dataNasterii" type="date" onChange={handleChange} value={formData.dataNasterii} />
                    <input name="parinteNumarTelefon" type="text" placeholder="Telefon Parinte" onChange={handleChange} value={formData.parinteNumarTelefon} />
                    <input name="elevNumarTelefon" type="text" placeholder="Telefon Elev" onChange={handleChange} value={formData.elevNumarTelefon} />
                    <select name="sex" onChange={handleChange} value={formData.sex}>
                        <option value="">Selectează Sex</option>
                        <option value="male">Masculin</option>
                        <option value="female">Feminin</option>
                    </select>
                </div>
                <div className="modal-actions">
                    <button className="cancel-btn" onClick={onClose}>Anulează</button>
                    <button className="save-btn" onClick={handleSave}>Adaugă</button>
                </div>
            </div>
        </div>
    );
};

ModalAddInfo.propTypes = {
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};

export default ModalAddInfo;
