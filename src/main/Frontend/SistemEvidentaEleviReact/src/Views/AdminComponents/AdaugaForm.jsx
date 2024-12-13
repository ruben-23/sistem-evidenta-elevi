// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import ModalAddInfo from './ModalAddInfo';
import '../../StylesViews/StyleComponents/AdaugaForm.css';


const AdaugaForm = ({ type, forms, setForms }) => {
    const [showModal, setShowModal] = useState(false);

    const handleChange = (key, value) => {
        setForms({
            ...forms,
            [type]: { ...forms[type], [key]: value },
        });
    };
