// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/Dropdown Search.css'

const Dropdowns = ({ clasaSelectata, setClasaSelectata, materieSelectata, setMaterieSelectata, clase, materii }) => {

    return (
        <div className="upper-dropdowns">
            <select
                value={clasaSelectata?.idClasa || ''}
                className="custom-select"
                onChange={(e) => {
                    const clsSelectata = clase.find((cls) => cls.idClasa === parseInt(e.target.value));
                    setClasaSelectata(clsSelectata || null);
                }}
            >
                <option value="">Selectați clasa</option>
                {clase.map((cls) => (
                    <option key={cls.idClasa} value={cls.idClasa}>{cls.nume}</option>
                ))}
            </select>

            {/*<select*/}
            {/*    value={materieSelectata?.idMaterie || ''}*/}
            {/*    onChange={(e) => {*/}
            {/*        const matSelectata = materii.find(materie => materie.idMaterie === parseInt(e.target.value));*/}
            {/*        setMaterieSelectata(matSelectata || null)}*/}
            {/*    }*/}
            {/*        >*/}
            {/*    <option value="">Selectați materia</option>*/}
            {/*    {materii.map((materie) => (*/}
            {/*        <option key={materie.idMaterie} value={materie.idMaterie}>{materie.nume}</option>*/}
            {/*    ))}*/}
            {/*</select>*/}
        </div>
    )
};

Dropdowns.propTypes = {
    clasaSelectata: PropTypes.oneOfType([
        PropTypes.shape({
            idClasa: PropTypes.number,
            nume: PropTypes.string,
        }),
        PropTypes.string,
    ]),
    setClasaSelectata: PropTypes.func.isRequired,
    materieSelectata: PropTypes.oneOfType([
        PropTypes.shape({
            idMaterie: PropTypes.number,
            nume: PropTypes.string,
        }),
        PropTypes.string,
    ]),
    setMaterieSelectata: PropTypes.func.isRequired,
    clase: PropTypes.arrayOf(
        PropTypes.shape({
            idClasa: PropTypes.number.isRequired,
            nume: PropTypes.string.isRequired,
        })
    ).isRequired,
    materii: PropTypes.arrayOf(
        PropTypes.shape({
            idMaterie: PropTypes.number.isRequired,
            nume: PropTypes.string.isRequired,
        })
    ).isRequired,
};

export default Dropdowns;