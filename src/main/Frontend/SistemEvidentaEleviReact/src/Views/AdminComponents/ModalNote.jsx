
import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/ModalNoteAbsente.css';
import { fetchNoteElev } from "../../services/eleviService.js";
import {actualizareNota, adaugaNota, stergereNota} from "../../services/notaService.js";
import {useUser} from "../../UserContext.jsx";

const ModalNote = ({ elev, onClose, onSave, materii }) => {
    const {user} = useUser();
    const [modulSelectat, setModulSelectat] = useState('');
    const [materieSelectata, setMaterieSelectata] = useState('');
    const [note, setNote] = useState([
        {
            idNota: null,
            valoare: '',
            data: '',
            gestionare: '',
            isNew: true,
            isEdited: false,
            isEditable: true,
            idElev: elev.idElev,
            idMaterie: null,
            modul: '',
        }
    ]);
    const [noteFiltrate, setNoteFiltrate] = useState([]);
    const [media, setMedia] = useState(0);


    useEffect(() => {
        const fetchAllNote = async () => {
            if (elev?.idElev) {
                try {
                    const noteReturnate = await fetchNoteElev(elev.idElev);

                    const noteMapate = noteReturnate.map((nota) => ({
                        idNota: nota.idNota,
                        valoare: nota.valoare.toString(),
                        data: nota.data,
                        gestionare: '',
                        isNew: false,
                        isEdited: false,
                        isEditable: false,
                        modul: nota.modul,
                        idMaterie: nota.idMaterie,
                    }));

                    setNote([
                        {
                            idNota: null,
                            valoare: '',
                            data: '',
                            gestionare: '',
                            isNew: true,
                            isEdited: false,
                            isEditable: true,
                            idElev: elev.idElev,
                            idMaterie: null,
                            modul: '',
                        },
                        ...noteMapate,
                    ]);
                } catch (error) {
                    console.error("Eroare la returnarea tuturor notelor:", error);
                }
            }
        };

        fetchAllNote();
    }, [elev]);

    // actualizare NoteFiltrate ori de cate ori note, modulSelectat sau materieSelectata se schimba
    useEffect(() => {
        const filtrareNote = () => {

            // primul rand din tabel ramane gol pentru a putea adauga note
            const randGol = note[0];

            // filtrare dupa modul si materie
            const filtrate = note.slice(1).filter((nota) => {
                const verifModul = modulSelectat ? nota.modul === modulSelectat : true;
                const verifMaterie = materieSelectata ? nota.idMaterie === materieSelectata.idMaterie : true;
                return verifModul && verifMaterie;
            });

            setNoteFiltrate([randGol, ...filtrate]);
        };

        filtrareNote();
    }, [note, modulSelectat, materieSelectata]);

    useEffect(() => {
        const media = calculeazaMedia(noteFiltrate);
        setMedia(media);
    }, [noteFiltrate]);


    const handleSchimbareModul = (e) => setModulSelectat(e.target.value);

    const handleSchimbareMaterie = (e) => {
        const idSelectat = parseInt(e.target.value);
        const materiaSelectata = materii.find((materie) => materie.idMaterie === idSelectat);
        setMaterieSelectata(materiaSelectata || '');
    };

    const handleModificareNota = (idNota, camp, valoare) => {
        setNote((prevNote) =>
            prevNote.map((nota) =>
                nota.idNota === idNota || (idNota === null && nota.idNota === null)
                    ? { ...nota, [camp]: valoare, isEdited: true }
                    : nota
            )
        );
    };

    const handleAdaugareNota = async () => {
        if (!modulSelectat || !materieSelectata || !note[0].data) {
            alert("Alegeti modulul, materia si data pentru a adauga nota");
            return;
        }

        // verificare ca nota sa fie un numar intre 1 si 10
        const inputNota = note[0].valoare;
        const numar = parseInt(inputNota);

        if (isNaN(numar) || numar < 1 || numar > 10) {
            alert("Nota trebuie sa fie un numar intre 1 si 10");
            return;
        }

        const notaNoua = {
            idElev: elev.idElev,
            idMaterie: materieSelectata.idMaterie,
            valoare: note[0].valoare,
            data: note[0].data,
            modul: modulSelectat,
        };

        try {
            const notaAdaugata = await adaugaNota(notaNoua);

            // resetare valori pentru nota si data
            note[0].valoare = '';
            note[0].data = '';

            setNote((prevNote) => [
                prevNote[0], // primul rand ramane gol pentru a putea adauga note
                {
                    idNota: notaAdaugata.idNota,
                    nota: notaAdaugata.valoare,
                    data: notaAdaugata.data,
                    gestionare: '',
                    isNew: false,
                    isEdited: false,
                    isEditable: false,
                    modul: notaAdaugata.modul,
                    idMaterie: notaAdaugata.idMaterie,
                },
                ...prevNote.slice(1),
            ]);
        } catch (error) {
            console.error("Eroare la adaugarea notei:", error);
            alert("A aparut o eroare la adaugarea notei");
        }

    };

    const handleSalvareNota = async (idNota) => {

        const notaGasita = note.find((nota) => nota.idNota === idNota);

        const notaActualizata = {
            idNota: idNota,
            idElev: elev.idElev,
            idMaterie: notaGasita.idMaterie,
            valoare: notaGasita.valoare,
            data: notaGasita.data,
            modul: notaGasita.modul,
        };

        try {
            await actualizareNota(elev.idElev, notaActualizata);
            setNote((prevNote) =>
                prevNote.map((nota) =>
                    nota.idNota === idNota
                        ? { ...nota, isEditable: false, isNew: false, isEdited: false }
                        : nota
                )
            );
        } catch (error) {
            console.error("Eroare la actualizarea notei:", error);
            alert("A aparut o eroare la actualizarea notei");
        }
    };

    const handleEditareNota = (idNota) => {
        setNote((prevNote) =>
            prevNote.map((nota) =>
                nota.idNota === idNota ? { ...nota, isEditable: true } : nota
            )
        );
    };

    const handleStergereNota = async (idNota) => {
        try {
            await stergereNota(idNota);
            setNote((prevNote) => prevNote.filter((nota) => nota.idNota !== idNota));
        } catch (error) {
            console.error("Eroare la stergerea notei:", error);
            alert("A aparut o eroare la stergerea notei");
        }
    };

    const calculeazaMedia = (note) => {
        const noteValide = note
            .filter(nota => nota.valoare && !isNaN(nota.valoare))
            .map(nota => parseFloat(nota.valoare));

        if (noteValide.length === 0) return 0

        const suma = noteValide.reduce((acc, curr) => acc + curr, 0);
        return (suma / noteValide.length).toFixed(2);
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <div className="modal-header">
                    <h3>Notele pentru {elev.nume} {elev.prenume}</h3>
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
                            <th>Nota</th>
                            <th>Data</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody className="scrollable-table-body">
                        {noteFiltrate.map((nota) => (
                            <tr key={nota.idNota}>
                                <td>
                                    <input
                                        type="text"
                                        style={{ fontWeight: "600" }}
                                        className="form-control bg-gradient-secondary"
                                        value={nota.valoare}
                                        onChange={(e) =>
                                            handleModificareNota(nota.idNota, 'valoare', e.target.value)
                                        }
                                        disabled={!nota.isEditable}
                                    />
                                </td>
                                <td>
                                    <input
                                        type="date"
                                        style={{ fontWeight: "600" }}
                                        className="form-control bg-gradient-secondary"
                                        value={nota.data}
                                        onChange={(e) =>
                                            handleModificareNota(nota.idNota, 'data', e.target.value)
                                        }
                                        disabled={!nota.isEditable}
                                    />
                                </td>
                                <td>
                                    {nota.idNota === null ? (
                                        <button className="btn btn-warning" onClick={handleAdaugareNota}>
                                            Adaugă Nota
                                        </button>
                                    ) : (
                                        user.rol === "ROLE_SECRETARA" && (
                                            <>
                                                {nota.isEditable ? (
                                                    <button className="btn btn-success" onClick={() => handleSalvareNota(nota.idNota)}>
                                                        Salvează
                                                    </button>
                                                ) : (
                                                    <button className="btn btn-secondary" onClick={() => handleEditareNota(nota.idNota)}>
                                                        Editează
                                                    </button>
                                                )}

                                                <button
                                                    onClick={() => handleStergereNota(nota.idNota)}
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

                    <h4>Media: {media}</h4>

                    <div className="modal-footer">
                        {/*<button onClick={onSave}>Salveaza</button>*/}
                        {/*<button onClick={onClose}>Inchide</button>*/}
                    </div>
                </div>
            </div>
        </div>
    );
};

ModalNote.propTypes = {
    elev: PropTypes.shape({
        id: PropTypes.number.isRequired,
        nume: PropTypes.string.isRequired,
        prenume: PropTypes.string.isRequired,
        media: PropTypes.string.isRequired,
    }).isRequired,
    onClose: PropTypes.func.isRequired,
    onSave: PropTypes.func.isRequired,
    materii: PropTypes.arrayOf(PropTypes.shape({
        idMaterie: PropTypes.number.isRequired,
        nume: PropTypes.string.isRequired,
    })).isRequired,
};

export default ModalNote;