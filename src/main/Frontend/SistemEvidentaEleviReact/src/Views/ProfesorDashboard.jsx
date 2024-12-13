// eslint-disable-next-line no-unused-vars
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
  const [grades, setGrades] = useState({});
  const [attendance, setAttendance] = useState({});
  const [error, setError] = useState('');

  const handleAddGrade = (grade, date, module) => {
    if (!selectedStudentId) return;

    if (grade < 1 || grade > 10) {
      setError('Grade must be between 1 and 10.');
      return;
    }

    setGrades((prevGrades) => ({
      ...prevGrades,
      [selectedStudentId]: {
        ...(prevGrades[selectedStudentId] || {}),
        [module]: [
          ...(prevGrades[selectedStudentId]?.[module] || []),
          { grade, date },
        ],
      },
    }));

    setError('');
  };

  const handleMarkPresent = (date, module) => {
    if (!selectedStudentId) return;

    setAttendance((prevAttendance) => ({
      ...prevAttendance,
      [selectedStudentId]: {
        ...(prevAttendance[selectedStudentId] || {}),
        [module]: [
          ...(prevAttendance[selectedStudentId]?.[module] || []),
          { date, present: true },
        ],
      },
    }));
  };

  const handleMarkAbsent = (date, module) => {
    if (!selectedStudentId) return;

    setAttendance((prevAttendance) => ({
      ...prevAttendance,
      [selectedStudentId]: {
        ...(prevAttendance[selectedStudentId] || {}),
        [module]: [
          ...(prevAttendance[selectedStudentId]?.[module] || []),
          { date, present: false },
        ],
      },
    }));
  };

  const handleLogout = () => {
    navigate('/');
  };

  return (
    <div className="professor-dashboard">
      <h2>Profesor Dashboard</h2>

      <div className="manage-student">
        <h3>Selectează Elev</h3>
        <select
          value={selectedStudentId}
          onChange={(e) => setSelectedStudentId(e.target.value)}
        >
          <option value="" disabled>
            Alege un elev
          </option>
          {elevi.map((elev) => (
            <option key={elev.id} value={elev.id}>
              {elev.username}
            </option>
          ))}
        </select>
      </div>

      {selectedStudentId && (
        <>
          <div className="manage-module">
            <h3>Selectează Modul</h3>
            <select
              value={selectedModule}
              onChange={(e) => setSelectedModule(e.target.value)}
            >
              <option value="Module 1">Module 1</option>
              <option value="Module 2">Module 2</option>
              <option value="Module 3">Module 3</option>
              <option value="Module 4">Module 4</option>
              <option value="Module 5">Module 5</option>
            </select>
          </div>

          <div className="manage-grades">
            <h3>Note</h3>
            {error && <p className="error">{error}</p>}
            <form
              onSubmit={(e) => {
                e.preventDefault();
                const grade = e.target.elements.grade.value;
                const date = e.target.elements.date.value;
                if (grade && date) {
                  handleAddGrade(grade, date, selectedModule);
                  e.target.reset();
                }
              }}
            >
              <input
                type="number"
                name="grade"
                placeholder="Adaugă notă"
                required
                min="1"
                max="10"
              />
              <input type="date" name="date" required />
              <button type="submit" className="button">
                Adaugă Notă
              </button>
            </form>
            <ul>
              {(grades[selectedStudentId]?.[selectedModule] || []).map((entry, index) => (
                <li key={index}>
                  {entry.grade} - {entry.date}
                </li>
              ))}
            </ul>
          </div>

          <div className="manage-attendance">
            <h3>Prezență</h3>
            <ul>
              {(attendance[selectedStudentId]?.[selectedModule] || []).map((att, index) => (
                <li key={index}>
                  {att.present ? 'Prezent' : 'Absent'} - {att.date}
                </li>
              ))}
            </ul>
            <form>
              <input type="date" name="date" required />
              <button
                type="button"
                className="button"
                onClick={(e) => {
                  const date = e.target.form.elements.date.value;
                  if (date) {
                    handleMarkPresent(date, selectedModule);
                  }
                }}
              >
                Marchează Prezență
              </button>
              <button
                type="button"
                className="button"
                onClick={(e) => {
                  const date = e.target.form.elements.date.value;
                  if (date) {
                    handleMarkAbsent(date, selectedModule);
                  }
                }}
              >
                Marchează Absență
              </button>
            </form>
          </div>
        </>
      )}

      <button className="lgbutton" onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

export default ProfesorDashboard;
