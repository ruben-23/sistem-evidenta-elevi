
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/liceu/elevi';

export const fetchAllElevi = async () => {
    try {
        const raspuns = await axios.get(BASE_URL);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea elevilor:', error);
        throw error;
    }
};

export const fetchElevById = async (id) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${id}`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea elevului cu id ${id}:`, error);
        throw error;
    }
};

export const fetchNoteElev = async (idElev) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idElev}/note`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea notelor pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
};

export const fetchNoteElevMaterie = async (idElev, idMaterie) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idElev}/${idMaterie}/note`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea notelor la materie pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
};

export const fetchAbsenteElev = async (idElev) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idElev}/absente`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea absentelor pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
}

export const fetchAbsenteElevMaterie = async (idElev, idMaterie) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idElev}/${idMaterie}/absente`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea absente pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
};

export const fetchBurseElev = async (idElev) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idElev}/burse`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la returnarea burse pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
};

export const adaugaElev = async (elevData) => {
    try {
        const raspuns = await axios.post(BASE_URL, elevData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea elevului:', error);
        throw error;
    }
};

export const actualizareElev = async (idElev, elevData) => {
    try {
        const raspuns = await axios.put(`${BASE_URL}/${idElev}`, elevData, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la actualizarea elevului:', error);
        throw error;
    }
};

export const adaugaBursaLaElev = async (idElev, idBursa) => {
    try {
        const raspuns = await axios.put(`${BASE_URL}/${idElev}/burse/${idBursa}`, {}, {
            headers: { 'Content-Type': 'application/json' },
        });
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea bursei pentru elev:', error);
        throw error;
    }
};

export const stergeElev = async (id) => {
    try {
        const raspuns = await axios.delete(`${BASE_URL}/${id}`);
        return raspuns.data;

    } catch (error) {
        console.error(`Eroare la stergerea elevului cu id ${id}:`, error);
        throw error;
    }
};

export const stergeBursaElev = async (idElev, idBursa) => {
    try {
        const raspuns = await axios.delete(`${BASE_URL}/${idElev}/burse/${idBursa}`);
        return raspuns.data;
    } catch (error) {
        console.error(`Eroare la stergerea bursei pentru elevul cu id ${idElev}:`, error);
        throw error;
    }
};