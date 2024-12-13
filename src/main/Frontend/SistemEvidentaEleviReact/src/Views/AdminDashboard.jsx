// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from "./AdminComponents/Sidebar.jsx";
import Dropdowns from "./AdminComponents/Dropdowns.jsx";
import AdaugaForm from './AdminComponents/AdaugaForm.jsx';
import Table from './AdminComponents/Table';
import StudentDetali from './AdminComponents/StudentDetali';
import ModalNote from "./AdminComponents/ModalNote.jsx";
import ModalAbsente from "./AdminComponents/ModalAbsente.jsx";
import ModalAddInfo from "./AdminComponents/ModalAddInfo.jsx";
import ConfirmationModal from "./AdminComponents/ConfirmationModal.jsx";
import '../StylesViews/AdminDashboard.css';
import '../StylesViews/StyleComponents/Dropdown Search.css';
import '../StylesViews/StyleComponents/Table.css';
import { v4 as uuidv4 } from 'uuid';

const AdminDashboard = () => {
    const [currentView, setCurrentView] = useState('accounts');
    const [data, setData] = useState({
        accounts: [
            { id: 1, username: 'admin', email: 'admin@example.com', role: 'ADMIN' },
            { id: 2, username: 'professor1', email: 'professor1@example.com', role: 'PROFESOR' },
        ],
        students: [
            {
                id: 1,
                name: 'John Doe',
                prenume: 'John',
                cnp: '1234567890123',
                adresa: 'Street 1',
                dataNasterii: '2005-01-01',
                parinteNumarTelefon: '987654321',
                elevNumarTelefon: '123456789',
                sex: 'male',
                class: '9A',
                subject: 'Mathematics',
                media: '8.5',
                totalAbsente: 3,
            },
    });

    const [editingItem, setEditingItem] = useState(null); // Track which item is being edited
    const [showAddStudent, setShowAddStudent] = useState(false); // Manage the visibility of the add student form
    const navigate = useNavigate(); // Hook to navigate programmatically

    // Handle logout (redirect to login page)
    const handleLogout = () => {
        navigate('/'); // Navigate back to the login page
    };

    // Handle form submissions
    const handleAccountSubmit = (e) => {
        e.preventDefault();
        const newId = accounts.length + 1;
        setAccounts([
            ...accounts,
            { id: newId, ...newAccount },
        ]);
        setNewAccount({ username: '', email: '', role: '' }); // Reset form
    };

    const handleStudentSubmit = (e) => {
        e.preventDefault();
        const newId = students.length + 1;
        setStudents([
            ...students,
            { id: newId, ...newStudent },
        ]);
        setNewStudent({
            name: '',
            prenume: '',
            cnp: '',
            adresa: '',
            dataNasterii: '',
            numarTelefon: '',
            parinteNumarTelefon: '',
            elevNumarTelefon: '',
            sex: '',
        }); // Reset form
        setShowAddStudent(false); // Close the form after submission
    };

    const handleTeacherSubmit = (e) => {
        e.preventDefault();
        const newId = teachers.length + 1;
        setTeachers([
            ...teachers,
            { id: newId, ...newTeacher },
        ]);
        setNewTeacher({ name: '', email: '', subject: '' }); // Reset form
    };

    // Handle Edit
    const handleEdit = (type, item) => {
        setEditingItem({ type, item }); // Set the item to be edited
    };

    // Handle Save (After Editing)
    const handleSave = (type) => {
        if (type === 'accounts') {
            setAccounts(accounts.map((account) =>
                account.id === editingItem.item.id ? editingItem.item : account
            ));
        } else if (type === 'students') {
            setStudents(students.map((student) =>
                student.id === editingItem.item.id ? editingItem.item : student
            ));
        } else if (type === 'teachers') {
            setTeachers(teachers.map((teacher) =>
                teacher.id === editingItem.item.id ? editingItem.item : teacher
            ));
        }
        setEditingItem(null); // Clear editing state after saving
    };

    // Handle Delete
    const handleDelete = (type, id) => {
        if (type === 'accounts') {
            setAccounts(accounts.filter((account) => account.id !== id));
        } else if (type === 'students') {
            setStudents(students.filter((student) => student.id !== id));
        } else if (type === 'teachers') {
            setTeachers(teachers.filter((teacher) => teacher.id !== id));
        }
    };

    // Render the appropriate section based on the currentView state
    const renderView = () => {
        switch (currentView) {
            case 'accounts':
                return (
                    <div className="manage-accounts">
                        <h2>Manage Accounts</h2>
                        <form onSubmit={handleAccountSubmit}>
                            <input
                                type="text"
                                placeholder="Username"
                                value={newAccount.username}
                                onChange={(e) => setNewAccount({ ...newAccount, username: e.target.value })}
                                required
                            />
                            <input
                                type="email"
                                placeholder="Email"
                                value={newAccount.email}
                                onChange={(e) => setNewAccount({ ...newAccount, email: e.target.value })}
                                required
                            />
                            <select
                                value={newAccount.role}
                                onChange={(e) => setNewAccount({ ...newAccount, role: e.target.value })}
                                required
                            >
                                <option value="">Select Role</option>
                                <option value="ADMIN">Admin</option>
                                <option value="PROFESOR">Profesor</option>
                                <option value="ELEV">Elev</option>
                            </select>
                            <button type="submit">Add Account</button>
                        </form>

                        <table>
                            <tbody>
                            {accounts.map((account) => (
                                <tr key={account.id}>
                                    <td>
                                        {editingItem && editingItem.type === 'accounts' && editingItem.item.id === account.id ? (
                                            <input
                                                type="text"
                                                value={editingItem.item.username}
                                                onChange={(e) => setEditingItem({ ...editingItem, item: { ...editingItem.item, username: e.target.value } })}
                                            />
                                        ) : (
                                            account.username
                                        )}
                                    </td>
                                    <td>
                                        {editingItem && editingItem.type === 'accounts' && editingItem.item.id === account.id ? (
                                            <input
                                                type="email"
                                                value={editingItem.item.email}
                                                onChange={(e) => setEditingItem({ ...editingItem, item: { ...editingItem.item, email: e.target.value } })}
                                            />
                                        ) : (
                                            account.email
                                        )}
                                    </td>
                                    <td>
                                        {editingItem && editingItem.type === 'accounts' && editingItem.item.id === account.id ? (
                                            <select
                                                value={editingItem.item.role}
                                                onChange={(e) => setEditingItem({ ...editingItem, item: { ...editingItem.item, role: e.target.value } })}
                                            >
                                                <option value="ADMIN">Admin</option>
                                                <option value="PROFESOR">Profesor</option>
                                                <option value="ELEV">Elev</option>
                                            </select>
                                        ) : (
                                            account.role
                                        )}
                                    </td>
                                    <td>
                                        {editingItem && editingItem.type === 'accounts' && editingItem.item.id === account.id ? (
                                            <button onClick={() => handleSave('accounts')}>Salveaza</button>
                                        ) : (
                                            <button onClick={() => handleEdit('accounts', account)}>Edit</button>
                                        )}
                                        <button onClick={() => handleDelete('accounts', account.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                );
            case 'students':
                return (
                    <div className="manage-students">
                        <h2>Gestioneaza Elevi</h2>
                        <button onClick={() => setShowAddStudent(true)}>Adauga Elev</button>

                        {showAddStudent && (
                            <div className="add-student-form">
                                <form onSubmit={handleStudentSubmit}>
                                    <input
                                        type="text"
                                        placeholder="Nume"
                                        value={newStudent.name}
                                        onChange={(e) => setNewStudent({ ...newStudent, name: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="Prenume"
                                        value={newStudent.prenume}
                                        onChange={(e) => setNewStudent({ ...newStudent, prenume: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="CNP"
                                        value={newStudent.cnp}
                                        onChange={(e) => setNewStudent({ ...newStudent, cnp: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="Adresa"
                                        value={newStudent.adresa}
                                        onChange={(e) => setNewStudent({ ...newStudent, adresa: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="date"
                                        placeholder="Data Nasterii"
                                        value={newStudent.dataNasterii}
                                        onChange={(e) => setNewStudent({ ...newStudent, dataNasterii: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="Numar Telefon"
                                        value={newStudent.numarTelefon}
                                        onChange={(e) => setNewStudent({ ...newStudent, numarTelefon: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="Parinte Numar Telefon"
                                        value={newStudent.parinteNumarTelefon}
                                        onChange={(e) => setNewStudent({ ...newStudent, parinteNumarTelefon: e.target.value })}
                                        required
                                    />
                                    <input
                                        type="text"
                                        placeholder="Elev Numar Telefon"
                                        value={newStudent.elevNumarTelefon}
                                        onChange={(e) => setNewStudent({ ...newStudent, elevNumarTelefon: e.target.value })}
                                        required
                                    />
                                    <select
                                        value={newStudent.sex}
                                        onChange={(e) => setNewStudent({ ...newStudent, sex: e.target.value })}
                                        required
                                    >
                                        <option value="male">Masculin</option>
                                        <option value="female">Feminin</option>
                                    </select>

                                    <button type="submit">Adauga Elev</button>
                                </form>
                            </div>
                        )}
                    </div>
                );
            case 'teachers':
                return (
                    <div className="manage-teachers">
                        <h2>Manage Teachers</h2>
                        <form onSubmit={handleTeacherSubmit}>
                            <input
                                type="text"
                                placeholder="Name"
                                value={newTeacher.name}
                                onChange={(e) => setNewTeacher({ ...newTeacher, name: e.target.value })}
                                required
                            />
                            <input
                                type="email"
                                placeholder="Email"
                                value={newTeacher.email}
                                onChange={(e) => setNewTeacher({ ...newTeacher, email: e.target.value })}
                                required
                            />
                            <input
                                type="text"
                                placeholder="Subject"
                                value={newTeacher.subject}
                                onChange={(e) => setNewTeacher({ ...newTeacher, subject: e.target.value })}
                                required
                            />
                            <button type="submit">Add Teacher</button>
                        </form>

                        <table>
                            <tbody>
                            {teachers.map((teacher) => (
                                <tr key={teacher.id}>
                                    <td>{teacher.name}</td>
                                    <td>{teacher.email}</td>
                                    <td>{teacher.subject}</td>
                                    <td>
                                        <button onClick={() => handleDelete('teachers', teacher.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                );
            default:
                return <div>Select a view</div>;
        }
    };

    return (
        <div className="admin-dashboard">
            {/* Sidebar buttons */}
            <div className="dashboard-buttons">
                <button className="dashboard-button" onClick={() => setCurrentView('accounts')}>Gestioneaza Conturi
                </button>
                <button className="dashboard-button" onClick={() => setCurrentView('students')}>Gestioneaza Elevi
                </button>
                <button className="dashboard-button" onClick={() => setCurrentView('teachers')}>Gestioneaza Profesori
                </button>
                <button className="logout-button" onClick={handleLogout}>Logout</button>
            </div>
            <div className="main-content">
                {renderView()}
            </div>
        </div>
    );
};

export default AdminDashboard;
