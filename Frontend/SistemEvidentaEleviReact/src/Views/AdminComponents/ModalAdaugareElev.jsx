// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Modal, Button, Form } from 'react-bootstrap';
import '../../StylesViews/StyleComponents/DetaliiElev.css';

/**
 * Componenta `ModalAdaugareElev` permite adaugarea unui nou elev prin completarea unui formular.
 *
 * @component
 * @param {Object} props - Proprietatile componentei.
 * @param {Function} props.onClose - Functie apelata pentru inchiderea modalului.
 * @param {Function} props.onSave - Functie apelata pentru salvarea datelor introduse in formular.
 *
 * @returns {JSX.Element} Modal pentru adaugarea unui elev.
 */
const ModalAdaugareElev = ({ onClose, onSave }) => {
    const [formData, setFormData] = useState({
        nume: '',
        prenume: '',
        cnp: '',
        adresa: '',
        dataNasterii: '',
        numarTelefon: '',
        sex: '',
        idClasa : '',
    });

    /**
     * Actualizeaza valorile campurilor din formular.
     *
     * @param {Object} e - Evenimentul de schimbare.
     */
    const handleModificare = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    /**
     * Apelata la trimiterea formularului pentru a salva datele.
     */
    const handleAdaugare = () => {
        onSave(formData);
        onClose();
    };

    return (
        <Modal show={true} onHide={onClose}>
            <Modal.Header closeButton className="custom-modal-header">
                <Modal.Title>Adaugă Elev</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form className="student-details-grid">
                    {/* Rand 1: nume and prenume */}
                    <Form.Group className="grid-item">
                        <Form.Label>Nume</Form.Label>
                        <Form.Control
                            name="nume"
                            type="text"
                            value={formData.nume}
                            onChange={handleModificare}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Prenume</Form.Label>
                        <Form.Control
                            name="prenume"
                            type="text"
                            value={formData.prenume}
                            onChange={handleModificare}
                        />
                    </Form.Group>

                    {/* Rand 2: CNP si data nasterii */}
                    <Form.Group className="grid-item">
                        <Form.Label>CNP</Form.Label>
                        <Form.Control
                            name="cnp"
                            type="text"
                            value={formData.cnp}
                            onChange={handleModificare}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Data Nasterii</Form.Label>
                        <Form.Control
                            name="dataNasterii"
                            type="date"
                            value={formData.dataNasterii}
                            onChange={handleModificare}
                        />
                    </Form.Group>

                    {/* Rand 3: adresa */}
                    <Form.Group className="grid-item grid-span-2">
                        <Form.Label>Adresa</Form.Label>
                        <Form.Control
                            name="adresa"
                            type="text"
                            value={formData.adresa}
                            onChange={handleModificare}
                        />
                    </Form.Group>

                    {/* Rand 4: numar telefon si sex */}
                    <Form.Group className="grid-item">
                        <Form.Label>Numar Telefon</Form.Label>
                        <Form.Control
                            name="numarTelefon"
                            type="text"
                            value={formData.numarTelefon}
                            onChange={handleModificare}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Sex</Form.Label>
                        <Form.Control
                            as="select"
                            name="sex"
                            value={formData.sex}
                            onChange={handleModificare}
                        >
                            <option value="">Selectează Sex</option>
                            <option value="masculin">Masculin</option>
                            <option value="feminin">Feminin</option>
                        </Form.Control>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>

                <Button className="btn btn-success" onClick={handleAdaugare}>
                    Adaugă
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

ModalAdaugareElev.propTypes = {
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
};

export default ModalAdaugareElev;