import React, { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext(null);

export const UserProvider = ({ children }) => {
    const [user, setUser ] = useState(() => {
        // obtinere user din localStorage
        const userSavat  = localStorage.getItem('user');
        return userSavat  ? JSON.parse(userSavat ) : null;
    });

    useEffect(() => {
        // stocare user in localStorage
        if (user) {
            localStorage.setItem('user', JSON.stringify(user));
        } else {
            localStorage.removeItem('user');
        }
    }, [user]);


    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
);
};

// hook pentru a folosi UserContext
export const useUser  = () => {
    return useContext(UserContext);
};