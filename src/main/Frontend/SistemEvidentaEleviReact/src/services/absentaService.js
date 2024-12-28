
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/liceu/absente';

export const fetchAllAbsente = async () => {
    try {
        const raspuns = await axios.get(BASE_URL);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea absentelor:', error);
        throw error;
    }
};

export const fetchAbsentaById = async (id) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${id}`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea absentei cu id ${id}:`, error);
        throw error;
    }
};

export const adaugaAbsenta = async (absentaData) => {
    try {
        const raspuns = await axios.post(BASE_URL, absentaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea absentei:', error);
        throw error;
    }
};

export const actualizareAbsenta = async (idAbsenta, absentaData) => {
    try {
        const raspuns = await axios.put(`${BASE_URL}/${idAbsenta}`, absentaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la actualizarea absentei:', error);
        throw error;
    }
};

export const stergereAbsenta = async (idAbsenta) => {
    try {
        await axios.delete(`${BASE_URL}/${idAbsenta}`);
    } catch (error) {
        console.error(`Eroare la stergerea absentei cu id ${idAbsenta}:`, error);
        throw error;
    }
};