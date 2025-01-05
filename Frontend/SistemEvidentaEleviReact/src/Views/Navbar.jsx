import React from 'react';
import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import '../StylesViews/Navbar.css';
import {useNavigate} from "react-router-dom";
import {useUser} from "../UserContext.jsx";

/**
 * Componenta `NavbarLiceu` reprezinta bara de navigare a aplicatiei.
 *
 * Aceasta componenta afiseaza numele liceului si ofera optiuni de navigare printr-un meniu dropdown
 * care include linkuri pentru vizualizarea profilului, setari si posibilitatea de deconectare.
 *
 * @component
 * @returns {JSX.Element} Bara de navigare personalizata.
 */
function NavbarLiceu() {

    const { setUser  } = useUser ();
    const navigate = useNavigate();

    /**
     * Functia `handleLogout` gestioneaza deconectarea utilizatorului.
     * Sterge utilizatorul din context si navigheaza catre pagina principala.
     */
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