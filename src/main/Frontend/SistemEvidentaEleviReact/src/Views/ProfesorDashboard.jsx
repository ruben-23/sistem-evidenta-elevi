import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../StylesViews/ProfesorDashboard.css';

const ProfesorDashboard = () => {
  const navigate = useNavigate();

  const [elevi] = useState([
    { id: 1, username: 'Ion Popescu' },
    { id: 2, username: 'Maria Ionescu' },
    { id: 3, username: 'Andrei Vasilescu' },
    { id: 4, username: 'Ana Georgescu' },
    { id: 5, username: 'Elena Stanescu' },
  ]);

  const [selectedStudentId, setSelectedStudentId] = useState('');
  const [selectedModule, setSelectedModule] = useState('Module 1');
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
    1: {
      name: 'Ion Popescu',
      surname: 'Ion',
      birthDate: '2005-01-01',
      cnp: '1234567890123',
      fatherPhone: '0756123456',
      motherPhone: '0756123457',
      studentPhone: '0756123458',
    },
    2: {
      name: 'Maria Ionescu',
      surname: 'Maria',
      birthDate: '2005-02-15',
      cnp: '2345678901234',
      fatherPhone: '0756123459',
      motherPhone: '0756123460',
      studentPhone: '0756123461',
    },
    3: {
      name: 'Andrei Vasilescu',
      surname: 'Andrei',
      birthDate: '2005-03-10',
      cnp: '3456789012345',
      fatherPhone: '0756123462',
      motherPhone: '0756123463',
      studentPhone: '0756123464',
    },
    4: {
      name: 'Ana Georgescu',
      surname: 'Ana',
      birthDate: '2005-04-20',
      cnp: '4567890123456',
      fatherPhone: '0756123465',
      motherPhone: '0756123466',
      studentPhone: '0756123467',
    },
    5: {
      name: 'Elena Stanescu',
      surname: 'Elena',
      birthDate: '2005-05-30',
      cnp: '5678901234567',
      fatherPhone: '0756123468',
      motherPhone: '0756123469',
      studentPhone: '0756123470',
    },
  });

  const [modalType, setModalType] = useState(null);  // 'grades', 'attendance', 'details'
  const [modalContent, setModalContent] = useState(null);  // Conținutul modalului

  const handleLogout = () => {
    navigate('/');
  };

  // Modal Handling
  const openModal = (type, studentId) => {
    setModalType(type);
    setSelectedStudentId(studentId);

    // Setează conținutul pentru fiecare tip de modal
    if (type === 'grades') {
      setModalContent(grades[studentId]?.[selectedModule] || []); // Verifică dacă există grade
    } else if (type === 'attendance') {
      setModalContent(attendance[studentId]?.[selectedModule] || []); // Verifică dacă există attendance
    } else if (type === 'details') {
      setModalContent(studentDetails[studentId]); // Detalii elev
    }
  };

  const closeModal = () => {
    setModalType(null);
    setModalContent(null);
  };

  return (
      <div className="professor-dashboard">
        <h2>Profesor Dashboard</h2>

        {/* Container flex pentru tabel și butoane */}
        <div className="content-container">
          {/* Tabel cu elevii */}
          <div className="students-table">
            <h3>Lista Elevilor</h3>
            <table>
              <thead>
              <tr>
                <th>ID</th>
                <th>Nume Elev</th>
                <th>Acțiuni</th>
              </tr>
              </thead>
              <tbody>
              {elevi.map((elev) => (
                  <tr key={elev.id}>
                    <td>{elev.id}</td>
                    <td>{elev.username}</td>
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
        </div>

        {/* Butonul de Logout */}
        <button className="lgbutton" onClick={handleLogout}>
          Logout
        </button>

        {/* Modal pentru Note, Absențe, Detalii */}
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
                          <li key={index}>
                            {entry.grade} - {entry.date}
                          </li>
                      ))}
                    </ul>
                )}
                {modalType === 'attendance' && (
                    <ul>
                      {modalContent.map((att, index) => (
                          <li key={index}>
                            {att.present ? 'Prezent' : 'Absent'} - {att.date}
                          </li>
                      ))}
                    </ul>
                )}
                {modalType === 'details' && modalContent && (
                    <div>
                      <p><strong>Nume:</strong> {modalContent.name}</p>
                      <p><strong>Prenume:</strong> {modalContent.surname}</p>
                      <p><strong>Data Nașterii:</strong> {modalContent.birthDate}</p>
                      <p><strong>CNP:</strong> {modalContent.cnp}</p>
                      <p><strong>Telefon Tată:</strong> {modalContent.fatherPhone}</p>
                      <p><strong>Telefon Mamă:</strong> {modalContent.motherPhone}</p>
                      <p><strong>Telefon Elev:</strong> {modalContent.studentPhone}</p>
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
