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
            {
                id: 2,
                name: 'Jane Smith',
                prenume: 'Jane',
                cnp: '9876543210987',
                adresa: 'Street 2',
                dataNasterii: '2006-02-02',
                parinteNumarTelefon: '887654321',
                elevNumarTelefon: '223456789',
                sex: 'female',
                class: '10B',
                subject: 'Literature',
                media: '9.2',
                totalAbsente: 1,
            },
        ],
        teachers: [
            { id: 1, name: 'Mr. White', email: 'mr.white@example.com', subject: 'Math' },
            { id: 2, name: 'Mrs. Green', email: 'mrs.green@example.com', subject: 'History' },
        ],
    });

    const [selectedClass, setSelectedClass] = useState('');
    const [selectedSubject, setSelectedSubject] = useState('');
    const [editingItem, setEditingItem] = useState(null);
    const [currentStudent, setCurrentStudent] = useState(null);
    const [detailsMode, setDetailsMode] = useState(false);
    const [searchQuery, setSearchQuery] = useState('');
    const [isModalNoteOpen, setIsModalNoteOpen] = useState(false);
    const [isModalAbsenteOpen, setIsModalAbsenteOpen] = useState(false);
    const [isModalAddInfoOpen, setIsModalAddInfoOpen] = useState(false);
    const [isConfirmDeleteOpen, setIsConfirmDeleteOpen] = useState(false); // State for confirmation modal
    const [itemToDelete, setItemToDelete] = useState(null); // Store the item to be deleted

    const navigate = useNavigate();

    const handleLogout = () => navigate('/');

    const handleEdit = (type, item) => setEditingItem({ type, item });

    const handleSave = () => {
        setData({
            ...data,
            [editingItem.type]: data[editingItem.type].map((item) =>
                item.id === editingItem.item.id ? editingItem.item : item
            ),
        });
        setEditingItem(null);
    };

    const handleDelete = (type, id) => {
        setItemToDelete({ type, id }); // Store the item to delete
        setIsConfirmDeleteOpen(true); // Show the confirmation modal
    };

    const handleDetails = (student) => {
        setCurrentStudent(student);
        setDetailsMode(true);
    };

    const handleNote = (student) => {
        setCurrentStudent(student);
        setIsModalNoteOpen(true);
    };

    const handleAbsente = (student) => {
        setCurrentStudent(student);
        setIsModalAbsenteOpen(true);
    };

    const confirmDelete = () => {
        const { type, id } = itemToDelete; // Get the item type and id to delete
        let updatedData;

        // Handle deletion based on the item type
        if (type === 'students') {
            updatedData = data.students.filter((item) => item.id !== id); // Filter out the student with the given id
        } else if (type === 'teachers') {
            updatedData = data.teachers.filter((item) => item.id !== id); // Filter out the teacher with the given id
        } else if (type === 'accounts') {
            updatedData = data.accounts.filter((item) => item.id !== id); // Filter out the account with the given id
        }

        // Update the state with the remaining data after deletion
        setData({
            ...data,
            [type]: updatedData,
        });

        // Close the confirmation modal
        setIsConfirmDeleteOpen(false);
        setItemToDelete(null); // Clear the item to delete
    };

    const cancelDelete = () => {
        setIsConfirmDeleteOpen(false); // Close the confirmation modal without deleting
        setItemToDelete(null); // Clear the item to delete
    };

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
