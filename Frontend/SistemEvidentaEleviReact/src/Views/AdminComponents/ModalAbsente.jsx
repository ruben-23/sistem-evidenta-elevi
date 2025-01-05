import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ModalNoteAbsente.css';
import {fetchAbsenteElev} from "../../services/eleviService.js";
import {actualizareAbsenta, adaugaAbsenta, stergereAbsenta} from "../../services/absentaService.js";
import {useUser} from "../../UserContext.jsx";

/**
 * Componenta `AbsenteModal` gestioneaza afisarea, adaugarea, editarea si stergerea absentelor unui elev.
 *
 * Aceasta componenta permite utilizatorului sa vizualizeze absentele unui elev, sa adauge absente noi,
 * sa editeze absente existente si sa stearga absente. De asemenea, permite filtrarea absentelor pe baza
 * modulului si materiei selectate.
 *
 * @component
 * @param {Object} props - Proprietatile componentei.
 * @param {Object} props.elev - Datele despre elevul pentru care sunt gestionate absentele.
 * @param {number} props.elev.idElev - ID-ul elevului.
 * @param {string} props.elev.nume - Numele elevului.
 * @param {string} props.elev.prenume - Prenumele elevului.
 * @param {function} props.onClose - Functie care inchide modalul.
 * @param {function} props.onSave - Functie apelata la salvarea modificarilor.
 * @param {Array} props.materii - Lista materiilor disponibile.
 * @param {number} props.materii[].idMaterie - ID-ul materiei.
 * @param {string} props.materii[].nume - Numele materiei.
 *
 * @returns {JSX.Element} Componenta de tip modal pentru gestionarea absentelor elevilor.
 */
const AbsenteModal = ({ elev, onClose, onSave, materii }) => {
    const {user} = useUser();
    const [modulSelectat, setModulSelectat] = useState('');
    const [materieSelectata, setMaterieSelectata] = useState('');
    const [absente, setAbsente] = useState([
        {
            idAbsenta: null,
            data: '',
            gestionare: '',
            isNew: true,
            isEdited: false,
            isEditable: true,
            idElev: elev.idElev,
            idMaterie: null,
            modul: '',
        },
    ]);
    const [absenteFiltrate, setAbsenteFiltrate] = useState([]);

    // incarca toate absentele elevului
    useEffect(() => {
        const fetchAllAbsente = async () => {
            if (elev?.idElev) {
                try {
                    const absenteReturnate = await fetchAbsenteElev(elev.idElev);
                    const absenteMapate = absenteReturnate.map((absenta) => ({
                        idAbsenta: absenta.idAbsenta,
                        data: absenta.data,
                        gestionare: '',
                        isNew: false,
                        isEdited: false,
                        isEditable: false,
                        modul: absenta.modul,
                        idMaterie: absenta.idMaterie,
                    }));

                    setAbsente([
                        {
                            idAbsenta: null,
                            data: '',
                            gestionare: '',
                            isNew: true,
                            isEdited: false,
                            isEditable: true,
                            idElev: elev.idElev,
                            idMaterie: null,
                            modul: '',
                        },
                        ...absenteMapate
                    ]);
                } catch (error) {
                    console.error("Eroare la returnarea tuturor absentelor:", error);
                }
            }
        };

        fetchAllAbsente();
    }, [elev]);


    // actualizare absenteFiltrate ori de cate ori note, modulSelectat sau materieSelectata se schimba
    useEffect(() => {
        const filtrareAbsente = () => {

            // primul rand din tabel ramane gol pentru a putea adauga absente
            const randGol = absente[0];

            const filtrate = absente.slice(1).filter((absenta) => {
                const verifModul = modulSelectat ? absenta.modul === modulSelectat : true;
                const verifMaterie = materieSelectata ? absenta.idMaterie === materieSelectata.idMaterie : true;
                return verifModul && verifMaterie;
            });

            setAbsenteFiltrate([randGol, ...filtrate]);
        };

        filtrareAbsente();
    }, [absente, modulSelectat, materieSelectata]);

    /**
     * Schimba modulul selectat.
     * @param {Event} e - Evenimentul de schimbare a valorii selectate.
     */
    const handleSchimbareModul = (e) => setModulSelectat(e.target.value);

    /**
     * Schimba materia selectata.
     * @param {Event} e - Evenimentul de schimbare a valorii selectate.
     */
    const handleSchimbareMaterie = (e) => {
        const idSelectat = parseInt(e.target.value);
        const materiaSelectata = materii.find((materie) => materie.idMaterie === idSelectat);
        setMaterieSelectata(materiaSelectata || '');
    };

    /**
     * Modifica o absenta existenta.
     * @param {number|null} idAbsenta - ID-ul absentei de modificat.
     * @param {string} camp - Numele campului de modificat.
     * @param {string} valoare - Noua valoare pentru campul specificat.
     */
    const handleModificareAbsenta = (idAbsenta, camp, valoare) => {
        setAbsente((prevAbsente) =>
            prevAbsente.map((absenta) =>
                absenta.idAbsenta === idAbsenta || (idAbsenta === null && absenta.idAbsenta === null)
                    ? { ...absenta, [camp]: valoare, isEdited: true }
                    : absenta
            )
        );
    };


    /**
     * Adauga o absenta noua.
     */
    const handleAdaugareAbsenta = async () => {
        if (!modulSelectat || !materieSelectata || !absente[0].data) {
            alert("Alegeti modulul, materia si data pentru a adauga absenta");
            return;
        }

        const absentaNoua = {
            idElev: elev.idElev,
            idMaterie: materieSelectata.idMaterie,
            data: absente[0].data,
            modul: modulSelectat,
        };

        // resetare data
        absente[0].data = '';

        try {
            const absentaAdaugata = await adaugaAbsenta(absentaNoua);

            setAbsente((prevAbsente) => [
                prevAbsente[0],
                {
                    idAbsenta: absentaAdaugata.idAbsenta,
                    data: absentaAdaugata.data,
                    gestionare: '',
                    isNew: false,
                    isEdited: false,
                    isEditable: false,
                    modul: absentaAdaugata.modul,
                    idMaterie: absentaAdaugata.idMaterie,
                },
                ...prevAbsente.slice(1),
            ]);
        } catch (error) {
            console.error("Eroare la adaugarea absentei:", error);
            alert("A aparut o eroare la adaugarea absentei");
        }
    };

    /**
     * Salveaza modificarile unei absente existente.
     * @param {number} idAbsenta - ID-ul absentei de salvat.
     */
    const handleSalvareAbsenta = async (idAbsenta) => {
        const absentaGasita = absente.find((absenta) => absenta.idAbsenta === idAbsenta);

        const absentaActualizata = {
            idAbsenta: idAbsenta,
            idElev: elev.idElev,
            idMaterie: absentaGasita.idMaterie,
            data: absentaGasita.data,
            modul: absentaGasita.modul,
        };

        try {
            await actualizareAbsenta(elev.idElev, absentaActualizata);
            setAbsente((prevAbsente) =>
                prevAbsente.map((absenta) =>
                    absenta.idAbsenta === idAbsenta
                        ? { ...absenta, isEditable: false, isNew: false, isEdited: false }
                        : absenta
                )
            );
        } catch (error) {
            console.error("Eroare la actualizarea absentei:", error);
            alert("A aparut o eroare la actualizarea absentei");
        }
    };

    /**
     * Activeaza modul de editare pentru o absenta.
     * @param {number} idAbsenta - ID-ul absentei de editat.
     */
    const handleEditareAbsenta = (idAbsenta) => {
        setAbsente((prevAbsente) =>
            prevAbsente.map((absenta) =>
                absenta.idAbsenta === idAbsenta ? { ...absenta, isEditable: true } : absenta
            )
        );
    };

    /**
     * Sterge o absenta existenta.
     * @param {number} idAbsenta - ID-ul absentei de sters.
     */
    const handleStergereAbsenta = async (idAbsenta) => {
        try {
            await stergereAbsenta(idAbsenta);
            setAbsente((prevAbsente) => prevAbsente.filter((absenta) =>
                absenta.idAbsenta !== idAbsenta));
        } catch (error) {
            console.error("Eroare la stergerea absentei:", error);
            alert("A aparut o eroare la stergerea absentei");
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <div className="modal-header">
                    <h3>Absențe pentru {elev.nume} {elev.prenume}</h3>
                    <button className="close-button" onClick={onClose}>&times;</button>
                </div>

                <div className="modal-body">
                    <div className="modul-dropdown">
                        <label htmlFor="modul">Modul:</label>
                        <select id="modul" value={modulSelectat} onChange={handleSchimbareModul}>
                            <option value="">Selectați modulul</option>
                            <option value="Modul 1">Modul 1</option>
                            <option value="Modul 2">Modul 2</option>
                            <option value="Modul 3">Modul 3</option>
                            <option value="Modul 4">Modul 4</option>
                            <option value="Modul 5">Modul 5</option>
                        </select>

                        <label htmlFor="materii" className="materii-dropdown">Materie:</label>
                        <select id="materii" value={materieSelectata?.idMaterie || ''} onChange={handleSchimbareMaterie}>
                            <option value="">Selectați materia</option>
                            {materii.map((materie) => (
                                <option key={materie.idMaterie} value={materie.idMaterie}>
                                    {materie.nume}
                                </option>
                            ))}
                        </select>
                    </div>

                    <table>
                        <thead>
                        <tr>
                            <th>Data</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody className="scrollable-table-body">
                        {absenteFiltrate.map((absenta) => (
                            <tr key={absenta.idAbsenta}>
                                <td>
                                    <input
                                        type="date"
                                        style={{ fontWeight: "600" }}
                                        className="form-control bg-gradient-secondary"
                                        value={absenta.data}
                                        onChange={(e) =>
                                            handleModificareAbsenta(absenta.idAbsenta, 'data', e.target.value)
                                        }
                                        disabled={!absenta.isEditable}
                                    />
                                </td>
                                <td>
                                    {absenta.idAbsenta === null ? (
                                        <button className="btn btn-warning" onClick={handleAdaugareAbsenta}>Adaugă Absența</button>
                                    ) : (
                                        user.rol === "ROLE_SECRETARA" && (
                                            <>
                                                {absenta.isEditable ? (
                                                    <button className="btn btn-success" onClick={() => handleSalvareAbsenta(absenta.idAbsenta)}>
                                                        Salvează
                                                    </button>
                                                ) : (
                                                    <button className="btn btn-secondary" onClick={() => handleEditareAbsenta(absenta.idAbsenta)}>
                                                        Editează
                                                    </button>
                                                )}

                                                <button
                                                    onClick={() => handleStergereAbsenta(absenta.idAbsenta)}
                                                    className="btn btn-danger"
                                                >
                                                    Șterge
                                                </button>
                                            </>
                                        )
                                    )}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <h4>Absente: {absenteFiltrate.length-1}</h4>

                    <div className="modal-footer">
                        {/*<button onClick={onSave}>Salveaza</button>*/}
                        {/*<button onClick={onClose}>Inchide</button>*/}
                    </div>
                </div>
            </div>
        </div>
    );
};

AbsenteModal.propTypes = {
    elev: PropTypes.shape({
        idElev: PropTypes.number.isRequired,
        nume: PropTypes.string.isRequired,
        prenume: PropTypes.string.isRequired,
    }).isRequired,
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
    materii: PropTypes.arrayOf(PropTypes.shape({
        idMaterie: PropTypes.number.isRequired,
        nume: PropTypes.string.isRequired,
    })).isRequired,
};

export default AbsenteModal;