// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';

const StudentDetali = ({ student, onSave, onClose }) => {
    const [isEditable, setIsEditable] = useState(false); // To track if the fields are editable
    const [editedStudent, setEditedStudent] = useState(student); // Store the edited student data

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditedStudent({ ...editedStudent, [name]: value });
    };

    const handleEdit = () => {
        setIsEditable(true); // Enable editing
    };

    const handleSave = () => {
        onSave(editedStudent); // Save changes
        setIsEditable(false); // Disable editing
    };

    return (
        <div className="student-details">
            <form>
                {Object.keys(student).map((key) => {
                    // Skip rendering the 'id' field
                    if (key === 'id') return null;

                    return (
                        <div key={key}>
                            <label>{key}</label>
                            <input
                                name={key}
                                type={key === 'dataNasterii' ? 'date' : 'text'}
                                value={editedStudent[key]}
                                onChange={handleChange}
                                readOnly={!isEditable} // Make input read-only when not in edit mode
                            />
                        </div>
                    );
                })}
            </form>
            <div className="modal-actions">
                <button className="cancel-btn" onClick={onClose}>Close</button>
                {isEditable ? (
                    <button className="save-btn" onClick={handleSave}>Save</button> // Save button when in edit mode
                ) : (
                    <button className="edit-btn" onClick={handleEdit}>Edit</button> // Edit button when in view mode
                )}
            </div>
        </div>
    );
};

StudentDetali.propTypes = {
    student: PropTypes.object.isRequired,
    onSave: PropTypes.func.isRequired,
    onClose: PropTypes.func.isRequired, // Ensure onClose is passed from the parent
};

export default StudentDetali;
