// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/DetaliiElev.css'
import {actualizareElev} from "../../services/eleviService.js";
import {useUser} from "../../UserContext.jsx";

/**
 * Componenta `ModalDetaliiElev` afiseaza un modal pentru gestionarea detaliilor unui elev.
 * Permite vizualizarea si editarea informatiilor elevului, in functie de rolul utilizatorului.
 *
 * @component
 * @param {Object} props - Proprietatile componentei.
 * @param {Object} props.elev - Obiect ce contine informatiile elevului.
 * @param {string} props.elev.nume - Numele elevului.
 * @param {string} props.elev.prenume - Prenumele elevului.
 * @param {string} props.elev.cnp - Codul Numeric Personal al elevului.
 * @param {string} props.elev.dataNasterii - Data nasterii elevului (in format ISO).
 * @param {string} props.elev.adresa - Adresa elevului.
 * @param {string} props.elev.numarTelefon - Numarul de telefon al elevului.
 * @param {string} props.elev.sex - Sexul elevului (ex. "masculin", "feminin").
 * @param {function} props.onSave - Functie apelata dupa salvarea modificarilor.
 * @param {function} props.onClose - Functie apelata pentru inchiderea modalului.
 *
 * @returns {JSX.Element} Modal pentru afisarea si gestionarea detaliilor unui elev.
 */
const ModalDetaliiElev = ({ elev, onSave, onClose }) => {
    const {user} = useUser();
    const [isEditable, setIsEditable] = useState(false);
    const [elevEditat, setElevEditat] = useState(elev);

    /**
     * Gestionarea modificarilor pentru inputuri.
     *
     * @param {Object} e - Evenimentul de schimbare.
     */
    const handleModificare = (e) => {
        const { name, value } = e.target;
        setElevEditat({ ...elevEditat, [name]: value });
    };

    /**
     * Activeaza modul de editare al formularului.
     */
    const handleEditare = () => {
        setIsEditable(true);
    };

    /**
     * Salveaza modificarile facute informatiilor elevului si actualizeaza baza de date.
     *
     * @async
     */
    const handleSalvare = async () => {
        try {
            // actualizare elev in db
            await actualizareElev(elev.idElev, elevEditat);
            onSave(elevEditat);
            setIsEditable(false);
        } catch (error) {
            console.error("Eroare la actualizarea elevului:", error);
            alert("Elevul nu a putut fi editat");
        }
    };

    return (

        <Modal show={true} onHide={onClose} >
            <Modal.Header closeButton className="custom-modal-header">
                <Modal.Title>Detalii {elev.nume} {elev.prenume}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form className="student-details-grid">
                    {/* Rand 1: nume and prenume */}
                    <Form.Group className="grid-item">
                        <Form.Label>Nume</Form.Label>
                        <Form.Control
                            name="nume"
                            type="text"
                            value={elevEditat.nume || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Prenume</Form.Label>
                        <Form.Control
                            name="prenume"
                            type="text"
                            value={elevEditat.prenume || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>

                    {/* Rand 2: CNP si data nasterii */}
                    <Form.Group className="grid-item">
                        <Form.Label>CNP</Form.Label>
                        <Form.Control
                            name="cnp"
                            type="text"
                            value={elevEditat.cnp || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Data Nasterii</Form.Label>
                        <Form.Control
                            name="dataNasterii"
                            type="date"
                            value={elevEditat.dataNasterii || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>

                    {/* Rand 3: adresa */}
                    <Form.Group className="grid-item grid-span-2">
                        <Form.Label>Adresa</Form.Label>
                        <Form.Control
                            name="adresa"
                            type="text"
                            value={elevEditat.adresa || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>

                    {/* Rand 4: numar telefon si sex */}
                    <Form.Group className="grid-item">
                        <Form.Label>Numar Telefon</Form.Label>
                        <Form.Control
                            name="numarTelefon"
                            type="text"
                            value={elevEditat.numarTelefon || ''}
                            onChange={handleModificare}
                            readOnly={!isEditable}
                        />
                    </Form.Group>
                    <Form.Group className="grid-item">
                        <Form.Label>Sex</Form.Label>
                        <Form.Control
                            as="select"
                            name="sex"
                            value={elevEditat.sex || ''}
                            onChange={handleModificare}
                            disabled={!isEditable}
                        >
                            <option value="masculin">Masculin</option>
                            <option value="feminin">Feminin</option>
                        </Form.Control>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>

                {user.rol === "ROLE_SECRETARA" && (
                    <Button
                        variant={isEditable ? "success" : "warning"}
                        onClick={isEditable ? handleSalvare : handleEditare}
                    >
                        {isEditable ? "Save Changes" : "Edit"}
                    </Button>
                )}

            </Modal.Footer>
        </Modal>

    );
};

ModalDetaliiElev.propTypes = {
    elev: PropTypes.object.isRequired,
    onSave: PropTypes.func.isRequired,
    onClose: PropTypes.func.isRequired,
};

export default ModalDetaliiElev;