import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/HomeCss.css';

const HomeComponent = () => {
  const navigate = useNavigate();

  const deconnexion = async (e) => {
    e.preventDefault();
   
    try {
      const token = localStorage.getItem('token');
      const response = await fetch('http://localhost:8080/Deconnection', {
          method: 'GET',
          headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json',
          },
      });

      if (!response.ok) {
          throw new Error(`Réponse du serveur non OK: ${response.status}`);
      }
      else {
          const data = await response.json();

          if(data.status===200) {
              localStorage.removeItem("token");
              console.log("status ok");
          
              navigate('/');
          }
          else if(data.status===400) {
              console.log("erreur : "+data.data);
          }
      }
    } 
    catch (error) {
        console.log("catch");
        console.error('Erreur lors de la requête HTTP :', error);
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container">
          <a className="navbar-brand" href="/">Mon Site</a>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item active">
                <a className="nav-link" href="/">Accueil</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/search">À propos</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/contact">Contact</a>
              </li>
            </ul>
          </div>
          <div className="bg-light border-right" id="sidebar">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item active">
                <button className="nav-link" onClick={deconnexion}>Deconnexion</button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="vertical-menu">
        <a href="#" className="active">Accueil</a>
        <a href="#">Tableau de bord</a>
        <a href="#">Profil</a>
        <a href="#">Paramètres</a>
        {/* Ajoutez d'autres liens au besoin */}
      </div>
    </div>
  );
};

export default HomeComponent;