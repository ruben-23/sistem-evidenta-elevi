
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/liceu/clase';

export const fetchAllClase = async () => {
    try {
        const raspuns = await axios.get(BASE_URL);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea claselor:', error);
        throw error;
    }
};

export const fetchClasaById = async (id) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${id}`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea clasei:', error);
        throw error;
    }
};

export const adaugaClasa = async (clasaData) => {
    try {
        const raspuns = await axios.post(BASE_URL, clasaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea clasei:', error);
        throw error;
    }
};

export const actualizareClasa = async (id, clasaData) => {
    try {
        const raspuns = await axios.put(`${BASE_URL}/${id}`, clasaData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la actualizarea clasei:', error);
        throw error;
    }
};

export const stergeClasa = async (id) => {
    try {
        await axios.delete(`${BASE_URL}/${id}`);
    } catch (error) {
        console.error('Eroare la stergerea clasei:', error);
        throw error;
    }
};

export const getEleviByClasa = async (id) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${id}/elevi`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea elevilor din clasa:', error);
        throw error;
    }
};