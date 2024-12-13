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

    const handleSaveStudentDetails = (updatedStudent) => {
        const newStudent = { id: uuidv4(), ...updatedStudent };
        setData({
            ...data,
            students: [newStudent, ...data.students],
        });
    };

    const filterData = (dataList, searchQuery, additionalFilters = {}) => {
        return dataList.filter((item) => {
            const searchMatch = Object.values(item)
                .join(' ')
                .toLowerCase()
                .includes(searchQuery.toLowerCase());
            const additionalMatch = Object.keys(additionalFilters).every(
                (key) => !additionalFilters[key] || item[key] === additionalFilters[key]
            );
            return searchMatch && additionalMatch;
        });
    };


    const filteredStudents = filterData(data.students, searchQuery, {
        class: selectedClass,
        subject: selectedSubject,
    });
    const filteredTeachers = filterData(data.teachers, searchQuery);
    const filteredAccounts = filterData(data.accounts, searchQuery);

    const renderModal = (ModalComponent, modalState, setModalState, onSave) => {
        return modalState && (
            <ModalComponent
                student={currentStudent}
                onClose={() => setModalState(false)}
                onSave={onSave}
            />
        );
    };

    const renderContent = () => {
        if (editingItem) {
            return (
                <div>
                    <h3>Edit {editingItem.type}</h3>
                    <AdaugaForm
                        type={editingItem.type}
                        forms={{ [editingItem.type]: editingItem.item }}
                        setForms={(newForms) =>
                            setEditingItem({
                                ...editingItem,
                                item: newForms[editingItem.type],
                            })
                        }
                    />
                    <button onClick={handleSave}>Save</button>
                </div>
            );
        }
        if (detailsMode) {
            return (
                <StudentDetali
                    student={currentStudent}
                    onSave={handleSaveStudentDetails}
                    onClose={() => setDetailsMode(false)} // Close modal
                />
            );
        }
        return (
            <div>
                <h2>Manage {currentView}</h2>
                <div className="upper-dropdowns">
                    <Dropdowns
                        selectedClass={selectedClass}
                        setSelectedClass={setSelectedClass}
                        selectedSubject={selectedSubject}
                        setSelectedSubject={setSelectedSubject}
                    />
                    <div className="search-bar">
                        <input
                            type="text"
                            placeholder="Search..."
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                    </div>
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
