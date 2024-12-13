/* eslint-disable no-unused-vars */
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../StylesViews/ProfesorDashboard.css';

const ProfesorDashboard = () => {
  const navigate = useNavigate();

  const [elevi] = useState([
    { id: 1, username: 'Ion Popescu', class: '10A' },
    { id: 2, username: 'Maria Ionescu', class: '10B' },
    { id: 3, username: 'Andrei Vasilescu', class: '10A' },
    { id: 4, username: 'Ana Georgescu', class: '10C' },
    { id: 5, username: 'Elena Stanescu', class: '10B' },
  ]);

  
  const [grades, setGrades] = useState({
    1: { 'Module 1': [{ grade: '8', date: '2024-12-01' }] },
    2: { 'Module 1': [{ grade: '9', date: '2024-12-02' }] },
    3: { 'Module 1': [{ grade: '7', date: '2024-12-03' }] },
    4: { 'Module 1': [{ grade: '10', date: '2024-12-04' }] },
    5: { 'Module 1': [{ grade: '6', date: '2024-12-05' }] },
  });

  const [attendance, setAttendance] = useState({
    1: { 'Module 1': [{ present: true, date: '2024-12-01' }, { present: false, date: '2024-12-02' }] },
    2: { 'Module 1': [{ present: true, date: '2024-12-02' }] },
    3: { 'Module 1': [{ present: false, date: '2024-12-03' }] },
    4: { 'Module 1': [{ present: true, date: '2024-12-04' }] },
    5: { 'Module 1': [{ present: true, date: '2024-12-05' }] },
  });

  const [studentDetails, setStudentDetails] = useState({
    1: { name: 'Ion Popescu', surname: 'Ion', birthDate: '2005-01-01', cnp: '1234567890123', parentPhone: '0712345678' },
    2: { name: 'Maria Ionescu', surname: 'Maria', birthDate: '2005-02-15', cnp: '2345678901234', parentPhone: '0723456789' },
    3: { name: 'Andrei Vasilescu', surname: 'Andrei', birthDate: '2005-03-10', cnp: '3456789012345', parentPhone: '0734567890' },
    4: { name: 'Ana Georgescu', surname: 'Ana', birthDate: '2005-04-20', cnp: '4567890123456', parentPhone: '0745678901' },
    5: { name: 'Elena Stanescu', surname: 'Elena', birthDate: '2005-05-30', cnp: '5678901234567', parentPhone: '0756789012' },
  });

  const [filterName, setFilterName] = useState('');
  const [filterClass, setFilterClass] = useState('');
  const [filterSubject, setFilterSubject] = useState('');
  const [modalType, setModalType] = useState(null);
  const [modalContent, setModalContent] = useState(null);
  const [selectedStudentId, setSelectedStudentId] = useState(null);

  const handleLogout = () => {
    navigate('/');
  };

  const openModal = (type, studentId) => {
    setModalType(type);
    setSelectedStudentId(studentId);

    if (type === 'grades') {
      setModalContent(grades[studentId]?.['Module 1'] || []);
    } else if (type === 'attendance') {
      setModalContent(attendance[studentId]?.['Module 1'] || []);
    } else if (type === 'details') {
      setModalContent(studentDetails[studentId]);
    }
  };

  const closeModal = () => {
    setModalType(null);
    setModalContent(null);
  };

  const filteredStudents = elevi.filter((elev) => {
    const matchesName = elev.username.toLowerCase().includes(filterName.toLowerCase());
    const matchesClass = filterClass === '' || elev.class === filterClass;
    const matchesSubject = true; // Placeholder logic
    return matchesName && matchesClass && matchesSubject;
  });

  return (
      <div className="professor-dashboard">
        <h2>Profesor Dashboard</h2>

        {/* Filtre */}
        <div className="filters">
          <input
              type="text"
              placeholder="Filtrează după nume"
              value={filterName}
              onChange={(e) => setFilterName(e.target.value)}
              className="filter-input"
          />
          <select
              value={filterClass}
              onChange={(e) => setFilterClass(e.target.value)}
              className="filter-select"
          >
            <option value="">Toate Clasele</option>
            <option value="10A">10A</option>
            <option value="10B">10B</option>
            <option value="10C">10C</option>
          </select>
          <select
              value={filterSubject}
              onChange={(e) => setFilterSubject(e.target.value)}
              className="filter-select"
          >
            <option value="">Toate Materiile</option>
            <option value="Math">Matematică</option>
            <option value="History">Istorie</option>
            <option value="Physics">Fizică</option>
          </select>
        </div>

        {/* Tabel cu elevii */}
        <div className="students-table">
          <h3>Lista Elevilor</h3>
          <table>
            <thead>
            <tr>
              <th>ID</th>
              <th>Nume Elev</th>
              <th>Clasă</th>
              <th>Acțiuni</th>
            </tr>
            </thead>
            <tbody>
            {filteredStudents.map((elev) => (
                <tr key={elev.id}>
                  <td>{elev.id}</td>
                  <td>{elev.username}</td>
                  <td>{elev.class}</td>
                  <td>
                    <button onClick={() => openModal('grades', elev.id)} className="button action-button">Note</button>
                    <button onClick={() => openModal('attendance', elev.id)} className="button action-button">Absențe</button>
                    <button onClick={() => openModal('details', elev.id)} className="button action-button">Detalii</button>
                  </td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>

        <button className="lgbutton" onClick={handleLogout}>
          Logout
        </button>

        {/* Modal */}
        {modalType && (
            <div className="modal active">
              <div className="modal-header">
                <h3>
                  {modalType === 'grades'
                      ? 'Note Elevului'
                      : modalType === 'attendance'
                          ? 'Absențele Elevului'
                          : 'Detalii Elevului'}
                </h3>
                <button onClick={closeModal} className="modal-close">X</button>
              </div>
              <div className="modal-content">
                {modalType === 'grades' && (
                    <ul>
                      {modalContent.map((entry, index) => (
                          <li key={index}>{entry.grade} - {entry.date}</li>
                      ))}
                    </ul>
                )}
                {modalType === 'attendance' && (
                    <ul>
                      {modalContent.map((entry, index) => (
                          <li key={index}>
                            {entry.present ? 'Prezent' : 'Absent'} - {entry.date}
                          </li>
                      ))}
                    </ul>
                )}
                {modalType === 'details' && (
                    <div>
                      <p><strong>Nume:</strong> {modalContent.name}</p>
                      <p><strong>Prenume:</strong> {modalContent.surname}</p>
                      <p><strong>Data Nașterii:</strong> {modalContent.birthDate}</p>
                      <p><strong>CNP:</strong> {modalContent.cnp}</p>
                      <p><strong>Telefon Părinte:</strong> {modalContent.parentPhone}</p>
                    </div>
                )}
              </div>
              <div className="modal-footer">
                <button onClick={closeModal}>Închide</button>
              </div>
            </div>
        )}
      </div>
  );
};

export default ProfesorDashboard;
