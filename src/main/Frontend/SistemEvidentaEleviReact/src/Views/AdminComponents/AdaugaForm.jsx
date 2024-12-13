// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import ModalAddInfo from './ModalAddInfo';
import '../../StylesViews/StyleComponents/AdaugaForm.css';


const AdaugaForm = ({ type, forms, setForms }) => {
    const [showModal, setShowModal] = useState(false);

    const handleChange = (key, value) => {
        setForms({
            ...forms,
            [type]: { ...forms[type], [key]: value },
        });
    };

    // Function to handle the data save from the modal
    const handleSaveData = (data) => {
        setForms({
            ...forms,
            [type]: data,
        });
    };

    return (
        <>
            <form onSubmit={(e) => e.preventDefault()}>
                {Object.keys(forms[type]).map((key) => (
                    <input
                        key={key}
                        type={key === 'dataNasterii' ? 'date' : 'text'}
                        placeholder={key}
                        value={forms[type][key]}
                        onChange={(e) => handleChange(key, e.target.value)}
                    />
                ))}
                <button type="button" onClick={() => setShowModal(true)}>Adauga</button>
            </form>

            {showModal && (
                <ModalAddInfo
                    onClose={() => setShowModal(false)}
                    onSave={handleSaveData}
                />
            )}
        </>
    );
};

AdaugaForm.propTypes = {
    type: PropTypes.string.isRequired,
    forms: PropTypes.objectOf(PropTypes.object).isRequired,
    setForms: PropTypes.func.isRequired,
};

export default AdaugaForm;
