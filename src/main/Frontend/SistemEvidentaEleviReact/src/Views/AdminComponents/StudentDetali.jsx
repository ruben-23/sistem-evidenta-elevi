// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';

const StudentDetali = ({ student, onSave, onClose }) => {
    const [isEditable, setIsEditable] = useState(false); // To track if the fields are editable
    const [editedStudent, setEditedStudent] = useState(student); // Store the edited student data
