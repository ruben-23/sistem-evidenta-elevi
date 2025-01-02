import React from 'react';
import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import '../StylesViews/Navbar.css';
import {useNavigate} from "react-router-dom";
import {useUser} from "../UserContext.jsx";

function NavbarLiceu() {

    const { setUser  } = useUser ();
    const navigate = useNavigate();

    const handleLogout = () => {
        // stergere user din localStorage
        setUser (null);
        navigate('/');
    };

    return (
        <Navbar className="custom-navbar" expand="lg">
            <Container>
                <Navbar.Brand href="#home">Colegiul Tehnic "George Baritiu"</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse className="justify-content-end">
                    <Nav className="ms-auto">
                        <NavDropdown title="Profil" id="basic-nav-dropdown" align="end">
                            <NavDropdown.Item href="#action/3.1">Vezi Profil</NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.2">Setari</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavbarLiceu;