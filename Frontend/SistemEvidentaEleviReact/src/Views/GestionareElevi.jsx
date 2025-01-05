
import React, { useEffect, useState } from 'react';
import Table from './AdminComponents/Table';
import ModalDetaliiElev from './AdminComponents/ModalDetaliiElev.jsx';
import Dropdowns from "./AdminComponents/Dropdowns.jsx";
import ModalNote from "./AdminComponents/ModalNote.jsx";
import ModalAbsente from "./AdminComponents/ModalAbsente.jsx";
import ModalAdaugareElev from "./AdminComponents/ModalAdaugareElev.jsx";
import {adaugaElev, fetchAllElevi, stergeElev} from "../services/eleviService.js";
import {fetchAllClase, getEleviByClasa} from "../services/clasaService.js";
import {
    fetchClaseProfesor,
    fetchMateriiDinClasa,
    fetchMateriiPredateDeProfesorInClasa
} from "../services/clasaMaterieProfesorService.js";
import {useUser} from "../UserContext.jsx";

/**
 * Componenta principala pentru gestionarea elevilor.
 *
 * Aceasta componenta permite vizualizarea, adaugarea, modificarea, stergerea si gestionarea
 * informatiilor despre elevi, inclusiv note si absente. Continutul este adaptat in functie
 * de rolul utilizatorului (secretara sau profesor).
 *
 * @component
 * @returns {JSX.Element} Interfata pentru gestionarea elevilor.
 */
const GestionareElevi = () => {

    /**
     * Contextul utilizatorului curent, care contine rolul si alte informatii.
     * @type {Object}
     */
    const { user  } = useUser ();

    const [searchTerm, setSearchTerm] = useState('');
    const [clasaSelectata, setClasaSelectata] = useState(null);
    const [materieSelectata, setMaterieSelectata] = useState(null);
    const [elevSelectat, setElevSelectat] = useState(null);
    const [isModalNoteOpen, setIsModalNoteOpen] = useState(false);
    const [isModalAbsenteOpen, setIsModalAbsenteOpen] = useState(false);
    const [isStudentDetaliOpen, setIsStudentDetaliOpen] = useState(false);
    const [isModalAddInfoOpen, setIsModalAddInfoOpen] = useState(false);
    const [clase, setClase] = useState([]);
    const [materii, setMaterii] = useState([]);
    const [elevi, setElevi] = useState([]);

    /**
     * Obtine lista claselor in functie de rolul utilizatorului.
     * Daca utilizatorul este secretara, returneaza toate clasele;
     * daca este profesor, returneaza clasele in care preda.
     *
     * @async
     */
    // incarcare clase
    useEffect(() => {
        const fetchClase = async () => {
            try {
                if (user.rol === "ROLE_SECRETARA") {
                    const claseReturnate = await fetchAllClase();
                    setClase(claseReturnate);
                } else if (user.rol === "ROLE_PROFESOR") {
                    const claseReturnate = await fetchClaseProfesor(user.profesor.idProfesor);
                    setClase(claseReturnate);
                }
            } catch (error) {
                console.error("Error fetching classes:", error);
            }
        };

        fetchClase();
    }, [user]);

    /**
     * Obtine lista elevilor in functie de rolul utilizatorului si clasa selectata.
     * Daca utilizatorul este secretara, returneaza toti elevii;
     * daca este profesor, returneaza elevii din clasa selectata.
     *
     * @async
     */
    // incarcare elevi
    useEffect(() => {
        const fetchElevi = async () => {
            try {
                if (user.rol === "ROLE_SECRETARA") {
                    // incarca toti elevii daca userul e secretara
                    const eleviReturnati = await fetchAllElevi();
                    setElevi(eleviReturnati);
                } else if (user.rol === "ROLE_PROFESOR" && clasaSelectata) {
                    // daca userul e profesor se vor incarca doar elevi din clasa selectata
                    const eleviReturnati = await getEleviByClasa(clasaSelectata.idClasa);
                    setElevi(eleviReturnati);
                } else {
                    setElevi([]);
                }
            } catch (error) {
                console.error("Eroare la returnarea elevilor:", error);
            }
        };

        fetchElevi();
    }, [user, clasaSelectata]);

    /**
     * Obtine lista materiilor pentru clasa selectata.
     * Daca utilizatorul este diriginte pentru clasa selectata, afiseaza toate materiile;
     * altfel, afiseaza doar materiile predate de profesor.
     *
     * @async
     */
    // incarcare materii
    useEffect(() => {
        const fetchMaterii = async () => {
            if (clasaSelectata && clasaSelectata.idClasa) {
                try {
                    // daca prfesorul e diriginte pentru clasa selectata, se vor afisa toate materiile
                    if (user.rol === "ROLE_SECRETARA" || (user.rol === "ROLE_PROFESOR" &&
                        user.profesor.idProfesor === clasaSelectata.idProfesor)) {
                        const materiiReturnate = await fetchMateriiDinClasa(clasaSelectata.idClasa);
                        setMaterii(materiiReturnate);
                    } else if (user.rol === "ROLE_PROFESOR") {
                        // restul profesorilor vor vedea doar materiile pe care le predau
                        const materiiReturnate = await fetchMateriiPredateDeProfesorInClasa(clasaSelectata.idClasa, user.profesor.idProfesor);
                        setMaterii(materiiReturnate);
                    }
                } catch (error) {
                    console.error("Eroare la returnarea materiilor:", error);
                }
            } else {
                setMaterii([]);
            }
        };

        fetchMaterii();
    }, [clasaSelectata, user]);

    /**
     * Adauga un elev nou in clasa selectata.
     *
     * @param {Object} elevNou - Obiect ce contine informatiile despre elevul nou.
     * @async
     */
    // adaugare elev
    const handleAdaugareElev = async (elevNou) => {
        if (clasaSelectata) {
            const elevData = { ...elevNou, idClasa: clasaSelectata.idClasa };
            try {
                const elevAdaugat = await adaugaElev(elevData);
                setElevi([...elevi, elevAdaugat]);
                setIsModalAddInfoOpen(false);
            } catch (error) {
                console.error("Eroare la adaugarea elevului:", error);
            }
        } else {
            alert("Selectati o clasa pentru elev inainte de a-l adauga.");
        }
    };

    /**
     * Actualizeaza informatiile despre un elev existent.
     *
     * @param {Object} elevActualizat - Obiect ce contine informatiile actualizate ale elevului.
     */
    // actualizare elev modificat
    const handleEditareElev = (elevActualizat) => {
        setElevi((prevElevi) => {
            return prevElevi.map((elev) =>
                elev.idElev === elevActualizat.idElev ? { ...elev, ...elevActualizat } : elev
            );
        });
    };

    /**
     * Sterge un elev din lista curenta.
     *
     * @param {number} idElev - ID-ul elevului de sters.
     * @async
     */
    // stergere elev
    const handleStergereElev = async (idElev) => {
        try{
            await stergeElev(idElev);
            setElevi(elevi.filter((student) => student.idElev !== idElev));
        } catch (error) {
            console.error("Eroare la stergerea elevului:", error);
            alert("Elevul nu a putut fi sters");
        }
    };

    //filtrare elevi dupa nume si clasa
    const eleviFiltrati = elevi.filter((elev) => {
        // verifica daca literele introduse in input se gasesc in nume sau prenume
        const verifInput = elev.nume.toLowerCase().includes(searchTerm.toLowerCase()) ||
            elev.prenume.toLowerCase().includes(searchTerm.toLowerCase());

        const verifClasa = clasaSelectata ? elev.idClasa === clasaSelectata.idClasa : true;

        return verifInput && verifClasa ;
    });

    const handleNote = (elev) => {
        setElevSelectat(elev);
        setIsModalNoteOpen(true);
    };

    const handleAbsente = (elev) => {
        setElevSelectat(elev);
        setIsModalAbsenteOpen(true);
    };

    const handleDetalii = (elev) => {
        setElevSelectat(elev);
        setIsStudentDetaliOpen (true);
    };

    /**
     * Randeaza un modal specific.
     *
     * @param {React.Component} ModalComponent - Componenta de modal care trebuie randata.
     * @param {boolean} modalState - Starea curenta a modalului.
     * @param {Function} setModalState - Functie pentru actualizarea starii modalului.
     * @param {Function} onSave - Callback pentru salvarea datelor.
     * @param {Object} [additionalProps={}] - Proprietati suplimentare pentru modal.
     * @returns {JSX.Element|null} Elementul modal sau null daca modalul este inchis.
     */
    const renderModal = (ModalComponent, modalState, setModalState, onSave, additionalProps={}) => {
        return modalState && (
            <ModalComponent
                elev={elevSelectat}
                onClose={() => setModalState(false)}
                onSave={onSave}
                {...additionalProps}
            />
        );
    };

    return (
        <div className="main-content">
            <h2>Gestionează Elevi</h2>

            <div className="search-and-dropdowns">
                <Dropdowns
                    clasaSelectata={clasaSelectata}
                    setClasaSelectata={setClasaSelectata}
                    materieSelectata={materieSelectata}
                    setMaterieSelectata={setMaterieSelectata}
                    clase={clase}
                    materii={materii}
                />
                <input
                    type="text"
                    placeholder="Caută dupa nume"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="search-bar"
                />
            </div>

            {user.rol === "ROLE_SECRETARA" && (
                <button className="btn btn-warning" onClick={() => setIsModalAddInfoOpen(true)}>
                    Adaugă Elev
                </button>
            )}

            <Table
                tip="elevi"
                date={eleviFiltrati}
                clase={clase}
                onDetalii={handleDetalii}
                onAbsente={handleAbsente}
                onNote={handleNote}
                onStergere={handleStergereElev}
            />

            {renderModal(ModalNote, isModalNoteOpen, setIsModalNoteOpen, () => {
                console.log('Nota salvata');
            }, { materii })}

            {renderModal(ModalAbsente, isModalAbsenteOpen, setIsModalAbsenteOpen, () => {
                console.log('Absenta salvata');
            }, {materii})}

            {renderModal(ModalDetaliiElev, isStudentDetaliOpen, setIsStudentDetaliOpen, handleEditareElev)}

            {renderModal(ModalAdaugareElev, isModalAddInfoOpen, setIsModalAddInfoOpen, handleAdaugareElev)}

        </div>
    );
};

export default GestionareElevi;


