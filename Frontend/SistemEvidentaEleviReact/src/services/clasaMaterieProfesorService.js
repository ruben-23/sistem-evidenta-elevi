
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/liceu/clase/materii/profesori';

export const fetchMateriiDinClasa = async (idClasa) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idClasa}/materii`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea materiilor pentru clasa:', error);
        throw error;
    }
};

export const fetchProfesoriDinClasa = async (idClasa) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idClasa}/profesori`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea profesorilor din clasa:', error);
        throw error;
    }
};

export const fetchClaseProfesor = async (idProfesor) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idProfesor}/clase`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea claselor in care preda profesorul:', error);
        throw error;
    }
};

export const fetchMateriiPredateDeProfesorInClasa = async (idClasa, idProfesor) => {
    try {
        const raspuns = await axios.get(`${BASE_URL}/${idClasa}/materii/profesor/${idProfesor}`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la returnarea materiilor preadate de profesor in clasa:', error);
        throw error;
    }
};

export const adaugaMaterieSiProfesorLaClasa = async (idClasa, idProfesor, idMaterie) => {
    try {
        const raspuns = await axios.post(`${BASE_URL}/${idClasa}/${idProfesor}/${idMaterie}`);
        return raspuns.data;
    } catch (error) {
        console.error('Eroare la adaugarea materiei si profesorului la clasa:', error);
        throw error;
    }
};

export const stergereMaterieSiProfesorDinClasa = async (idClasa, idProfesor, idMaterie) => {
    try {
        await axios.delete(`${BASE_URL}/${idClasa}/${idProfesor}/${idMaterie}`);
    } catch (error) {
        console.error('Eroare la stergerea materiei si profesorului din clasa:', error);
        throw error;
    }
};

