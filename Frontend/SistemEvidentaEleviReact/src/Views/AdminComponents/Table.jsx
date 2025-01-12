// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/Table.css';
import {useUser} from '../../UserContext.jsx';

/**
 * Componenta `Table` afiseaza un tabel pentru diferite tipuri de date.
 * Este utilizata pentru gestionarea informatiilor elevilor sau altor entitati.
 *
 * @component
 * @param {Object} props - Proprietatile componentei.
 * @param {string} props.tip - Tipul datelor care vor fi afisate in tabel (ex. "elevi").
 * @param {Array} props.date - Datele care trebuie afisate in tabel.
 * @param {Array} props.clase - Lista claselor disponibile (utilizata pentru asocierea elevilor cu clasele).
 * @param {function} props.onEditare - Functie apelata la editarea unui element.
 * @param {function} props.onStergere - Functie apelata la stergerea unui element.
 * @param {function} [props.onDetalii] - Functie apelata pentru afisarea detaliilor unui elev.
 * @param {function} [props.onNote] - Functie apelata pentru afisarea notelor unui elev.
 * @param {function} [props.onAbsente] - Functie apelata pentru afisarea absentelor unui elev.
 *
 * @returns {JSX.Element} Un tabel cu datele specificate.
 */
const Table = ({ tip, date, clase, onEditare, onStergere, onDetalii, onNote, onAbsente }) => {

    const {user} = useUser();

    /**
     * Returneaza numele clasei in functie de ID.
     *
     * @param {number} id - ID-ul clasei.
     * @returns {string} Numele clasei sau "Fara clasa" daca clasa nu este gasita.
     */
    // gasire nume clasa dupa id
    const getNumeClasDupaId = (id) => {
        const clasa = clase.find((cls) => cls.idClasa === id);
        return clasa ? clasa.nume : 'Fara clasa';
    };

    return (
        <table>
            <thead>
            <tr>
                {tip === 'elevi' && <th>ID</th>}
                {tip === 'elevi' ? (
                    <>
                        <th>Nume</th>
                        <th>Clasa</th>
                    </>
                ) : (
                    Object.keys(date[0] || {}).map((key) => <th key={key}>{key}</th>)
                )}
                <th></th>
            </tr>
            </thead>
            <tbody>
            {date.map((element) => (
                <tr key={element.idElev}>
                    {tip === 'elevi' && <td>{element.idElev}</td>}
                    {tip === 'elevi' ? (
                        <>
                            <td>{element.nume} {element.prenume}</td>
                            <td>{getNumeClasDupaId(element.idClasa)}</td>
                        </>
                    ) : (
                        Object.values(element).map((val, i) => (
                            <td key={i}>{val}</td>
                        ))
                    )}
                    <td>

                        {tip !== 'elevi' && (
                            <button className="btn btn-secondary" onClick={() => onEditare(tip, element)}>Editează</button>
                        )}
                        {tip === 'elevi' && (
                            <>
                                <button className="btn btn-outline-dark" onClick={() => onDetalii(element)}>Detalii</button>
                                <button className="btn btn-outline-dark" onClick={() => onNote(element)}>Note</button>
                                <button className="btn btn-outline-dark" onClick={() => onAbsente(element)}>Absențe</button>
                                <button className="btn btn-outline-dark">Burse</button>
                            </>
                        )}

                        {/*afisare buton de stergere doar pentru secretara*/}
                        {user.rol === "ROLE_SECRETARA" &&
                            <button className="btn btn-danger" onClick={() => onStergere(element.idElev)}>Șterge</button>
                        }
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    )
};

Table.propTypes = {
    tip: PropTypes.string.isRequired,
    date: PropTypes.arrayOf(PropTypes.object).isRequired,
    onEditare: PropTypes.func.isRequired,
    onStergere: PropTypes.func.isRequired,
    onDetalii: PropTypes.func,
    onNote: PropTypes.func,
    onAbsente: PropTypes.func,
};

export default Table;