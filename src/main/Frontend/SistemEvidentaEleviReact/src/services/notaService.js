
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/liceu/note';

export const fetchAllNote = async () => {
    try {
        const raspuns = await axios.get(BASE_URL);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea notelor:', error);
        throw error;
    }
};

export const fetchNotaById = async (id) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${id}`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea notei cu id ${id}:`, error);
        throw error;
    }
};

export const adaugaNota = async (notaData) => {
    try {
        const raspuns = await axios.post(BASE_URL, notaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea notei:', error);
        throw error;
    }
};

export const actualizareNota = async (idNota, notaData) => {
    try {
        const raspuns = await axios.put(`${BASE_URL}/${idNota}`, notaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la actualizarea notei:', error);
        throw error;
    }
};

export const stergereNota = async (idNota) => {
    try {
        await axios.delete(`${BASE_URL}/${idNota}`);
    } catch (error) {
        console.error(`Eroare la stergerea notei cu id ${idNota}:`, error);
        throw error;
    }
};