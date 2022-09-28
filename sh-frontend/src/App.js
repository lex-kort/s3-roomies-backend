import React from 'react';
import Navbar from './components/Navbar.js';
import './App.css';
import { Routes, Route } from 'react-router-dom';

// Pages
import Home from './pages/Home.js';
import Listings from './pages/Listings.js';
import Account from './pages/Account.js';
import Login from './pages/Login.js';

function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/listings" element={<Listings />} />
        <Route path="/my-account" element={<Account />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </div>
  );
}

export default App;
