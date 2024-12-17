/* eslint-disable no-unused-vars */
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../StylesViews/ProfesorDashboard.css';

const ProfesorDashboard = () => {
  const navigate = useNavigate();

  const [elevi] = useState([
    { id: 1, username: 'Ion Popescu', class: '10A' },
    { id: 2, username: 'Maria Ionescu', class: '10B' },
  ]);

  const [grades, setGrades] = useState({
    1: [{ grade: '8', date: '2024-06-01' }],
    2: [{ grade: '9', date: '2024-06-02' }],
  });

  const [absences, setAbsences] = useState({
    1: [{ date: '2024-06-01', status: 'Nemotivată' }],
    2: [{ date: '2024-06-03', status: 'Motivată' }],
    3: [{ date: '2024-07-03', status: 'Absent' }],
  });

  const [studentDetails] = useState({
    1: { name: 'Ion', surname: 'Popescu', birthDate: '2005-01-01', parentPhone: '0712345678' },
    2: { name: 'Maria', surname: 'Ionescu', birthDate: '2005-02-15', parentPhone: '0722223333' },
  });

  const [modalType, setModalType] = useState(null);
  const [selectedStudentId, setSelectedStudentId] = useState(null);
  const [newGrade, setNewGrade] = useState('');
  const [newGradeDate, setNewGradeDate] = useState('');
  const [newAbsence, setNewAbsence] = useState('');
  const [absenceStatus, setAbsenceStatus] = useState('Nemotivată');

  const openModal = (type, studentId) => {
    setModalType(type);
    setSelectedStudentId(studentId);
  };

  const closeModal = () => {
    setModalType(null);
  };

  const addGrade = () => {
    if (!newGrade || !newGradeDate) return;
    setGrades((prevGrades) => ({
      ...prevGrades,
      [selectedStudentId]: [
        ...(prevGrades[selectedStudentId] || []),
        { grade: newGrade, date: newGradeDate },
      ],
    }));
    setNewGrade('');
    setNewGradeDate('');
  };

  const addAbsence = () => {
    if (!newAbsence) return;
    setAbsences((prevAbsences) => ({
      ...prevAbsences,
      [selectedStudentId]: [
        ...(prevAbsences[selectedStudentId] || []),
        { date: newAbsence, status: absenceStatus },
      ],
    }));
    setNewAbsence('');
  };

  const modifyAbsence = (index) => {
    const updatedAbsences = [...absences[selectedStudentId]];
    updatedAbsences[index].status =
        updatedAbsences[index].status === 'Motivată' ? 'Nemotivată' : updatedAbsences[index].status === 'Nemotivată' ? 'Prezent' : updatedAbsences[index].status === 'Prezent' ? 'Absent' : 'Motivată';
    setAbsences((prevAbsences) => ({
      ...prevAbsences,
      [selectedStudentId]: updatedAbsences,
    }));
  };

  return (
      <div className="professor-dashboard">
        <h2>Profesor Dashboard</h2>

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
            {elevi.map((elev) => (
                <tr key={elev.id}>
                  <td>{elev.id}</td>
                  <td>{elev.username}</td>
                  <td>{elev.class}</td>
                  <td>
                    <button onClick={() => openModal('grades', elev.id)} className="action-button">
                      Note
                    </button>
                    <button onClick={() => openModal('absences', elev.id)} className="action-button">
                      Absențe
                    </button>
                    <button onClick={() => openModal('details', elev.id)} className="action-button">
                      Detalii
                    </button>
                  </td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>

        {modalType && (
            <div className="modal active">
              <div className="modal-content">
                {modalType === 'grades' && (
                    <>
                      <h3>Note</h3>
                      <ul>
                        {grades[selectedStudentId]?.map((grade, index) => (
                            <li key={index}>
                              {grade.date} - Nota: {grade.grade}
                            </li>
                        ))}
                      </ul>
                      <input
                          type="text"
                          value={newGrade}
                          onChange={(e) => setNewGrade(e.target.value)}
                          placeholder="Introdu nota"
                      />
                      <input
                          type="date"
                          value={newGradeDate}
                          onChange={(e) => setNewGradeDate(e.target.value)}
                      />
                      <button onClick={addGrade}>Adaugă Notă</button>
                    </>
                )}

                {modalType === 'absences' && (
                    <>
                      <h3>Absențe</h3>
                      <ul>
                        {absences[selectedStudentId]?.map((absence, index) => (
                            <li key={index}>
                              {absence.date} - {absence.status}
                              <button onClick={() => modifyAbsence(index)}>Modifică</button>
                            </li>
                        ))}
                      </ul>
                      <input
                          type="date"
                          value={newAbsence}
                          onChange={(e) => setNewAbsence(e.target.value)}
                      />
                      <select
                          value={absenceStatus}
                          onChange={(e) => setAbsenceStatus(e.target.value)}
                      >
                        <option value="Nemotivată">Nemotivată</option>
                        <option value="Motivată">Motivată</option>
                        <option value="Absent">Absent</option>
                        <option value="Prezent">Prezent</option>
                      </select>
                      <button onClick={addAbsence}>Adaugă Absență</button>
                    </>
                )}

                {modalType === 'details' && (
                    <>
                      <h3>Detalii Elev</h3>
                      <p>
                        <strong>Nume:</strong> {studentDetails[selectedStudentId]?.name}
                      </p>
                      <p>
                        <strong>Prenume:</strong> {studentDetails[selectedStudentId]?.surname}
                      </p>
                      <p>
                        <strong>Data Nașterii:</strong>{' '}
                        {studentDetails[selectedStudentId]?.birthDate}
                      </p>
                      <p>
                        <strong>Telefon Părinte:</strong>{' '}
                        {studentDetails[selectedStudentId]?.parentPhone}
                      </p>
                    </>
                )}
                <button onClick={closeModal}>Închide</button>
              </div>
            </div>
        )}

        <button className="lgbutton" onClick={() => navigate('/')}>
          Logout
        </button>
      </div>
  );
};

export default ProfesorDashboard;
