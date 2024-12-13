// eslint-disable-next-line no-unused-vars
import React from 'react';
import PropTypes from 'prop-types';
import '../../StylesViews/StyleComponents/Table.css';

const Table = ({ type, data, onEdit, onDelete, onDetails, onNote, onAbsente }) => (
    <table>
        <thead>
        <tr>
            {/* Display ID column for 'students' */}
            {type === 'students' && <th>ID</th>}
            {/* Render headers dynamically for other columns */}
            {type === 'students' ? (
                <th>Name</th>
            ) : (
                Object.keys(data[0] || {}).map((key) => <th key={key}>{key}</th>) // Dynamic headers for non-student types
            )}
            <th>Actions</th> {/* Actions column for Edit/Delete/Details buttons */}
        </tr>
        </thead>
        <tbody>
        {data.map((item) => (
            <tr key={item.id}>
                {/* Render student ID in the first column for 'students' type */}
                {type === 'students' && <td>{item.id}</td>}
                {/* Render only the student name when type is 'students', otherwise display all values */}
                {type === 'students' ? (
                    <td>{item.name}</td> // Display only the student name in the student view
                ) : (
                    Object.values(item).map((val, i) => (
                        <td key={i}>{val}</td> // For other types, display all values
                    ))
                )}
                <td>
                    {/* Only show the Edit button for non-student types */}
                    {type !== 'students' && (
                        <button onClick={() => onEdit(type, item)}>Edit</button>
                    )}
                    <button onClick={() => onDelete(type, item.id)}>Delete</button>
                    {type === 'students' && (
                        <>
                            <button onClick={() => onDetails(item)}>Details</button>
                            <button onClick={() => onNote(item)}>Note</button>
                            <button onClick={() => onAbsente(item)}>Absente</button>
                        </>
                    )}
                </td>
            </tr>
        ))}
        </tbody>
    </table>
);

//v1 Arata toata informatie student
// const Table = ({ type, data, onEdit, onDelete, onDetails, onNote, onAbsente }) => (
//     <table>
//         <tbody>
//         {data.map((item) => (
//             <tr key={item.id}>
//                 {Object.values(item).map((val, i) => (
//                     <td key={i}>{val}</td>
//                 ))}
//                 <td>
//                     <button onClick={() => onEdit(type, item)}>Edit</button>
//                     <button onClick={() => onDelete(type, item.id)}>Delete</button>
//                     {type === 'students' && (
//                         <>
//                             <button onClick={() => onDetails(item)}>Details</button>
//                             <button onClick={() => onNote(item)}>Note</button>
//                             <button onClick={() => onAbsente(item)}>Absente</button>
//                         </>
//                     )}
//                 </td>
//             </tr>
//         ))}
//         </tbody>
//     </table>
// );

Table.propTypes = {
    type: PropTypes.string.isRequired,
    data: PropTypes.arrayOf(PropTypes.object).isRequired,
    onEdit: PropTypes.func.isRequired,
    onDelete: PropTypes.func.isRequired,
    onDetails: PropTypes.func,
    onNote: PropTypes.func,
    onAbsente: PropTypes.func,
};

export default Table;
