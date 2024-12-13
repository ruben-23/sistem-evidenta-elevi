// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';

const Dropdowns = ({ selectedClass, setSelectedClass, selectedSubject, setSelectedSubject }) => (
    <div className="upper-dropdowns">
        <select value={selectedClass} onChange={(e) => setSelectedClass(e.target.value)}>
            <option value="">Select Class</option>
            <option value="9A">9A</option>
            <option value="9B">9B</option>
            <option value="10A">10A</option>
            <option value="10B">10B</option>
            <option value="11A">11A</option>
            <option value="11B">11B</option>
            <option value="12A">12A</option>
            <option value="12B">12B</option>
        </select>
Dropdowns.propTypes = {
    selectedClass: PropTypes.string.isRequired,
    setSelectedClass: PropTypes.func.isRequired,
};

export default Dropdowns;
