
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const LoginComponent = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    
    try {
      const response = await fetch('http://localhost:8080/Connection', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          "login":username,
          "pwd":password,
        }),
      });

      if (!response.ok) {
        throw new Error('Erreur lors de la connexion');
      }
      else {
        const data = await response.json();

        if(data.status===200) {
          localStorage.setItem('token', data.data[0]);
          console.log("status ok");
          
          navigate('/connexion');
        }
        else if(data.status===400) {
          console.log("erreur : "+data.data);
        }
      }

    } catch (error) {
      console.error('Erreur lors de la connexion:', error.message);
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Connexion</h3>
              <form onSubmit={handleLogin}>
                <div className="mb-3">
                  <label htmlFor="username" className="form-label">Nom d'utilisateur :</label>
                  <input
                    type="text"
                    className="form-control"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">Mot de passe :</label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="d-grid">
                  <button type="submit" className="btn btn-primary">Se connecter</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;