import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import EventosPage from './pages/EventosPage';

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" />;
}

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/eventos"
          element={
            <ProtectedRoute>
              <EventosPage />
            </ProtectedRoute>
          }
        />
        <Route path="/" element={<Navigate to="/eventos" />} />
      </Routes>
    </Router>
  );
}

export default App;
