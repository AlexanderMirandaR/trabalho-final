import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../utils/api';
import './EventosPage.css';

export default function EventosPage() {
  const [eventos, setEventos] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [editando, setEditando] = useState(null);
  const [formData, setFormData] = useState({
    titulo: '',
    descricao: '',
    dataInicio: '',
    dataFim: '',
    local: '',
    capacidade: ''
  });
  const navigate = useNavigate();

  useEffect(() => {
    carregarEventos();
  }, []);

  const carregarEventos = async () => {
    try {
      setCarregando(true);
      const response = await api.get('/rest/eventos');
      setEventos(response.data);
      setErro('');
    } catch (error) {
      setErro('Erro ao carregar eventos');
    } finally {
      setCarregando(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editando) {
        await api.put(`/rest/eventos/${editando}`, formData);
      } else {
        await api.post('/rest/eventos', formData);
      }
      setFormData({
        titulo: '',
        descricao: '',
        dataInicio: '',
        dataFim: '',
        local: '',
        capacidade: ''
      });
      setEditando(null);
      carregarEventos();
    } catch (error) {
      setErro('Erro ao salvar evento');
    }
  };

  const handleEditar = (evento) => {
    setEditando(evento.id);
    setFormData({
      titulo: evento.titulo,
      descricao: evento.descricao,
      dataInicio: evento.dataInicio,
      dataFim: evento.dataFim,
      local: evento.local,
      capacidade: evento.capacidade
    });
  };

  const handleDeletar = async (id) => {
    if (confirm('Tem certeza que deseja deletar este evento?')) {
      try {
        await api.delete(`/rest/eventos/${id}`);
        carregarEventos();
      } catch (error) {
        setErro('Erro ao deletar evento');
      }
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    navigate('/login');
  };

  return (
    <div className="eventos-container">
      <header className="header">
        <h1>Gerenciador de Eventos</h1>
        <button className="logout-btn" onClick={handleLogout}>Sair</button>
      </header>

      <main className="main-content">
        {erro && <div className="erro">{erro}</div>}

        <form className="form-evento" onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Título"
            value={formData.titulo}
            onChange={(e) => setFormData({...formData, titulo: e.target.value})}
            required
          />
          <input
            type="text"
            placeholder="Local"
            value={formData.local}
            onChange={(e) => setFormData({...formData, local: e.target.value})}
            required
          />
          <textarea
            placeholder="Descrição"
            value={formData.descricao}
            onChange={(e) => setFormData({...formData, descricao: e.target.value})}
            required
          />
          <input
            type="datetime-local"
            placeholder="Data Início"
            value={formData.dataInicio}
            onChange={(e) => setFormData({...formData, dataInicio: e.target.value})}
            required
          />
          <input
            type="datetime-local"
            placeholder="Data Fim"
            value={formData.dataFim}
            onChange={(e) => setFormData({...formData, dataFim: e.target.value})}
            required
          />
          <input
            type="number"
            placeholder="Capacidade"
            value={formData.capacidade}
            onChange={(e) => setFormData({...formData, capacidade: e.target.value})}
            required
          />
          <button type="submit">
            {editando ? 'Atualizar Evento' : 'Criar Evento'}
          </button>
        </form>

        {carregando ? (
          <div className="carregando">Carregando eventos...</div>
        ) : eventos.length === 0 ? (
          <div className="vazio">Nenhum evento cadastrado</div>
        ) : (
          <div className="eventos-grid">
            {eventos.map(evento => (
              <div key={evento.id} className="evento-card">
                <h3>{evento.titulo}</h3>
                <p><strong>Local:</strong> {evento.local}</p>
                <p><strong>Capacidade:</strong> {evento.capacidade} pessoas</p>
                <p className="descricao">{evento.descricao}</p>
                <div className="acoes">
                  <button className="btn-editar" onClick={() => handleEditar(evento)}>
                    Editar
                  </button>
                  <button className="btn-deletar" onClick={() => handleDeletar(evento.id)}>
                    Deletar
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </main>
    </div>
  );
}
