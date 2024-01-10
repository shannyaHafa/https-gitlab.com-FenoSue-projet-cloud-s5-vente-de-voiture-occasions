import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
import HomeComponent from './components/HomeComponent';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/connexion" element={<HomeComponent />} />
        <Route path="/" element={<LoginComponent />} />
        {/* Ajoutez d'autres routes au besoin */}
      </Routes>
    </Router>
  );
};

export default App;
