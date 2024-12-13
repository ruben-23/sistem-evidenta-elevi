// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ConfirmationModal.css'

const ConfirmationModal = ({ message, onConfirm, onCancel }) => {
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h3>{message}</h3>
                <div className="modal-actions">
                    <button className="confirm-btn" onClick={onConfirm}>Confirm</button>
                    <button className="cancel-btn" onClick={onCancel}>Cancel</button>
                </div>
            </div>
        </div>
    );
};

ConfirmationModal.propTypes = {
    message: PropTypes.string.isRequired,
    onConfirm: PropTypes.func.isRequired,
    onCancel: PropTypes.func.isRequired,
};

export default ConfirmationModal;
