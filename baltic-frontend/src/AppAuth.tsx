import React from 'react';
import './App.css';
import Login from './Login'
import App from './App'

function AppAuth() {
  const user = userAuth();
  return (
      user ? <Login/> : <App/>
  );
}

function userAuth() {
  return localStorage.getItem("token") == null;
}

export default AppAuth;
