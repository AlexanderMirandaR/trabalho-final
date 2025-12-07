# Gerenciador de Eventos - Trabalho Prático de Frameworks Web II

Aplicação full stack para gerenciamento de eventos desenvolvida com React e Spring Boot.

## 👨‍💻 Integrantes

Alexander Miranda Ribeiro

## 📋 Requisitos Atendidos

### Backend (Spring Boot)
- ✅ API RESTful funcional
- ✅ Spring Data JPA com MySQL/PostgreSQL no Aiven
- ✅ Duas entidades relacionadas (Evento e Participante)
- ✅ CRUD completo para Evento
- ✅ Autenticação com JWT
- ✅ Swagger/OpenAPI
- ✅ CORS configurado

### Frontend (React)
- ✅ Consumo de API com Axios
- ✅ React Router para navegação
- ✅ Componentes reutilizáveis com Hooks
- ✅ Telas de Login/Registro
- ✅ CRUD de Eventos
- ✅ Indicadores de loading
- ✅ Tratamento de erros

### Deploy
- ✅ Backend no Render
- ✅ Frontend no Vercel
- ✅ Banco de dados MySQL/PostgreSQL no Aiven

## 🚀 Como Executar Localmente

### Backend

1. Navegue até a pasta backend:
```bash
cd backend
```

2. Execute o projeto:
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

### Frontend

1. Navegue até a pasta frontend:
```bash
cd frontend
```

2. Instale as dependências:
```bash
npm install
```

3. Execute o projeto:
```bash
npm run dev
```

A aplicação estará disponível em `http://localhost:5173`

## 🔗 Endpoints da API

### Autenticação
- `POST /rest/auth/register` - Registrar novo usuário
- `POST /rest/auth/login` - Fazer login

### Eventos
- `GET /rest/eventos` - Listar todos os eventos
- `GET /rest/eventos/{id}` - Obter evento por ID
- `POST /rest/eventos` - Criar novo evento
- `PUT /rest/eventos/{id}` - Atualizar evento
- `DELETE /rest/eventos/{id}` - Deletar evento

### Participantes
- `GET /rest/participantes/evento/{eventoId}` - Listar participantes de um evento
- `POST /rest/participantes` - Criar novo participante
- `PUT /rest/participantes/{id}` - Atualizar participante
- `DELETE /rest/participantes/{id}` - Deletar participante

## 📚 Documentação

A documentação da API está disponível em `https://api-trabalho-tqyl.onrender.com/swagger-ui/index.html#/` quando o backend está em execução.

## 🔐 Autenticação

A aplicação usa JWT para autenticação. Após fazer login ou registrar, um token é armazenado no localStorage e enviado automaticamente em todas as requisições protegidas.

## 📝 Variáveis de Ambiente

### Frontend (.env)
```
VITE_API_URL=http://localhost:8080
```

### Backend (application.properties)
Para produção, configure as seguintes variáveis:
- `SPRING_DATASOURCE_URL` - URL de conexão do banco de dados
- `SPRING_DATASOURCE_USERNAME` - Usuário do banco
- `SPRING_DATASOURCE_PASSWORD` - Senha do banco
- `JWT_SECRET` - Chave secreta para JWT
- `JWT_EXPIRATION` - Tempo de expiração do token em ms

## 🔗 Links do Projeto

- Repositório Git: `https://github.com/AlexanderMirandaR/trabalho-final`

- Frontend (Vercel): `https://frontend-trabalho-ten.vercel.app/login`

- Backend (Render + Swagger): `https://api-trabalho-tqyl.onrender.com/swagger-ui/index.html#/`

- Vídeo de Demonstração: `https://unilavrasedu-my.sharepoint.com/:v:/g/personal/alexandermiranda_unilavras_edu_br/IQDRNXDg6zWBSq1Hu6LS4QfPATW7-V7Q8F8dAL1nGvBiFxo?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=DsZ9hE`
