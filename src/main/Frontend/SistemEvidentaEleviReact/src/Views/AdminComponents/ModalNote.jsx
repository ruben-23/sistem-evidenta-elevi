// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ModalNoteAbsente.css';


const ModalNote = ({ student, onClose, onSave }) => {
    const [selectedSemester, setSelectedSemester] = useState('');
    const [grades, setGrades] = useState([
        { nota: '', data: '', gestionare: '', isNew: false, isEdited: false, isEditable: true },
    ]);

    const handleSemesterChange = (e) => setSelectedSemester(e.target.value);

    const handleGradeChange = (index, field, value) => {
        const updatedGrades = [...grades];
        updatedGrades[index][field] = value;
        updatedGrades[index].isEdited = true;
        setGrades(updatedGrades);
    };

    const handleAddGrade = () => {
        setGrades([{ nota: '', data: '', gestionare: '', isNew: true, isEdited: false, isEditable: false }, ...grades]);
    };

    const handleEditGrade = (index) => {
        const updatedGrades = [...grades];
        updatedGrades[index].isEditable = true;
        setGrades(updatedGrades);
    };

    const handleSaveGrade = (index) => {
        const updatedGrades = [...grades];
        updatedGrades[index].isEditable = false;
        updatedGrades[index].isNew = false;
        updatedGrades[index].isEdited = false;
        setGrades(updatedGrades);
    };

    const handleRemoveGrade = (index) => {
        if (index > 0) {
            setGrades(grades.filter((_, i) => i !== index));
        }
    };

    // Calculate total of new grades (excluding the default row)
    const calculateTotal = () => {
        return grades.filter(grade => grade.isNew).length; // Count only new rows
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <div className="modal-header">
                    <h3>
                        {student.name} {student.prenume} - Media: {student.media}
                    </h3>
                    <div className="total">Total Notes: {calculateTotal()}</div>
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
                            <th>Nota</th>
                            <th>Data</th>
                            <th>Gestionare</th>
                        </tr>
                        </thead>
                        <tbody className="scrollable-table-body">
                        {grades.map((grade, index) => (
                            <tr key={index}>
                                <td>
                                    <input
                                        type="text"
                                        value={grade.nota}
                                        onChange={(e) =>
                                            handleGradeChange(index, 'nota', e.target.value)
                                        }
                                        disabled={!grade.isEditable && index !== 0}
                                    />
                                </td>
                                <td>
                                    <input
                                        type="date"
                                        value={grade.data}
                                        onChange={(e) =>
                                            handleGradeChange(index, 'data', e.target.value)
                                        }
                                        disabled={!grade.isEditable && index !== 0}
                                    />
                                </td>
                                <td>
                                    {index === 0 ? (
                                        <button onClick={handleAddGrade}>Adauga Nota</button>
                                    ) : (
                                        <>
                                            {grade.isEditable ? (
                                                <button onClick={() => handleSaveGrade(index)}>
                                                    Salveaza
                                                </button>
                                            ) : (
                                                <button onClick={() => handleEditGrade(index)}>
                                                    Editeaza
                                                </button>
                                            )}
                                        </>
                                    )}
                                    <button onClick={() => handleRemoveGrade(index)} disabled={index === 0}>
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
ModalNote.propTypes = {
    student: PropTypes.shape({
        name: PropTypes.string.isRequired,
        prenume: PropTypes.string.isRequired,
        media: PropTypes.string.isRequired,
    }).isRequired,
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};

export default ModalNote;
