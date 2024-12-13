// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ModalNoteAbsente.css';


const ModalAbsente = ({ student, onClose, onSave }) => {
    const [selectedSemester, setSelectedSemester] = useState('');
    const [absences, setAbsences] = useState([
        { data: '', motivare: '', gestionare: '', isNew: false, isEdited: false, isEditable: true },
    ]);

    const handleSemesterChange = (e) => setSelectedSemester(e.target.value);

    const handleAbsenceChange = (index, field, value) => {
        const updatedAbsences = [...absences];
        updatedAbsences[index][field] = value;
        updatedAbsences[index].isEdited = true;
        setAbsences(updatedAbsences);
    };

    const handleAddAbsence = () => {
        setAbsences([{ data: '', motivare: '', gestionare: '', isNew: true, isEdited: false, isEditable: false }, ...absences]);
    };

    const handleEditAbsence = (index) => {
        const updatedAbsences = [...absences];
        updatedAbsences[index].isEditable = true;
        setAbsences(updatedAbsences);
    };

    const handleSaveAbsence = (index) => {
        const updatedAbsences = [...absences];
        updatedAbsences[index].isEditable = false;
        updatedAbsences[index].isNew = false;
        updatedAbsences[index].isEdited = false;
        setAbsences(updatedAbsences);
    };

    const handleRemoveAbsence = (index) => {
        if (index > 0) {
            setAbsences(absences.filter((_, i) => i !== index));
        }
    };

    // Calculate total of new absences (excluding the default row)
    const calculateTotal = () => {
        return absences.filter(absence => absence.isNew).length; // Count only new rows
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <div className="modal-header">
                    <h3>
                        {student.name} {student.prenume} - Absente Nemotivate: {student.absenteNemotivate}
                    </h3>
                    <div className="total">Total Absente: {calculateTotal()}</div>
                </div>

                <div className="modal-body">
                    <div className="semester-dropdown">
                        <label htmlFor="semester">Select Semester:</label>
                        <select
                            id="semester"
                            value={selectedSemester}
                            onChange={handleSemesterChange}
                        >
                            <option value="">Select a semester</option>
                            <option value="Modul 1">Modul 1</option>
                            <option value="Modul 2">Modul 2</option>
                            <option value="Modul 3">Modul 3</option>
                            <option value="Modul 4">Modul 4</option>
                            <option value="Modul 5">Modul 5</option>
                        </select>
                    </div>

                    <table>
                        <thead>
                        <tr>
                            <th>Data</th>
                            <th>Motivare</th>
                            <th>Gestionare</th>
                        </tr>
                        </thead>
                        <tbody className="scrollable-table-body">
                        {absences.map((absence, index) => (
                            <tr key={index}>
                                <td>
                                    <input
                                        type="date"
                                        value={absence.data}
                                        onChange={(e) =>
                                            handleAbsenceChange(index, 'data', e.target.value)
                                        }
                                        disabled={!absence.isEditable && index !== 0}
                                    />
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        value={absence.motivare}
                                        onChange={(e) =>
                                            handleAbsenceChange(index, 'motivare', e.target.value)
                                        }
                                        disabled={!absence.isEditable && index !== 0}
                                    />
                                </td>
                                <td>
                                    {index === 0 ? (
                                        <button onClick={handleAddAbsence}>Adauga Absenta</button>
                                    ) : (
                                        <>
                                            {absence.isEditable ? (
                                                <button onClick={() => handleSaveAbsence(index)}>
                                                    Salveaza
                                                </button>
                                            ) : (
                                                <button onClick={() => handleEditAbsence(index)}>
                                                    Editeaza
                                                </button>
                                            )}
                                        </>
                                    )}
                                    <button onClick={() => handleRemoveAbsence(index)} disabled={index === 0}>
                                        Sterge
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <div className="modal-footer">
                        <button onClick={onSave}>Salveaza</button>
                        <button onClick={onClose}>Inchide</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

// PropTypes validation
ModalAbsente.propTypes = {
    student: PropTypes.shape({
        name: PropTypes.string.isRequired,
        prenume: PropTypes.string.isRequired,
        absenteNemotivate: PropTypes.string.isRequired,
    }).isRequired,
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};

export default ModalAbsente;
