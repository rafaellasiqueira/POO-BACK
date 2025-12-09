# Sistema de Gerenciamento de Doações - Backend

Este repositório contém o back-end do Sistema de Gerenciamento de Doações, uma aplicação que gerencia doadores, instituições, pedidos e doações, permitindo o registro, consulta, atualização e exclusão de informações de forma segura e eficiente.

## Funcionalidades

- **Gerenciamento de Doadores**
  - Cadastro, login, atualização e exclusão.
  - Validação de e-mail e senha para autenticação segura.

- **Gerenciamento de Instituições**
  - Cadastro, login, atualização e consulta por ID.
  - Integração com pedidos relacionados à instituição.

- **Gerenciamento de Pedidos**
  - Criação de pedidos vinculados a instituições.
  - Listagem de pedidos gerais ou por instituição.
  - Atualização e exclusão de pedidos.

- **Gerenciamento de Doações**
  - Registro de doações vinculadas a doadores e pedidos.
  - Consulta de doações por doador ou instituição.
  - Atualização do status da doação (Pendente, Em andamento, Entregue, Cancelada).
  - Exclusão de doações.

## Arquitetura

- **Spring Boot** para criação da API RESTful.
- **JPA/Hibernate** para mapeamento das entidades com o banco de dados MySQL.
- **Repositories** para persistência e consultas, incluindo queries customizadas para otimização e carregamento de relacionamentos.
- **Services** que contêm toda a lógica de negócio.
- **Controllers** que recebem requisições HTTP do front-end e chamam os serviços correspondentes.
- **SecurityConfig** configurando CORS e desabilitando CSRF para integração com o front-end.

## Endpoints Principais

### Doador
- `POST /doador/cadastrar` → Cadastro de doador.
- `POST /doador/login` → Login do doador.
- `GET /doador/{id}` → Buscar doador por ID.
- `PUT /doador` → Atualizar dados do doador.
- `DELETE /doador/{id}` → Excluir doador.

### Instituição
- `POST /instituicao/cadastrar` → Cadastro de instituição.
- `POST /instituicao/login` → Login da instituição.
- `GET /instituicao/{id}` → Buscar instituição por ID.
- `PUT /instituicao` → Atualizar dados da instituição.

### Pedido
- `POST /pedido` → Criar pedido.
- `GET /pedido` → Listar todos os pedidos.
- `GET /pedido/instituicao/{id}` → Listar pedidos de uma instituição.
- `PUT /pedido/{id}` → Atualizar pedido.
- `DELETE /pedido/{id}` → Excluir pedido.

### Doação
- `POST /doacao/add` → Registrar doação.
- `GET /doacao/list/{id}` → Listar doações por doador.
- `GET /doacao/listInstituicao/{id}` → Listar doações por instituição.
- `PUT /doacao/updateStatus/{id}` → Atualizar status da doação.
- `DELETE /doacao/delete/{id}` → Excluir doação.

## Análise e Neutralização de Vulnerabilidades

- **Validação de entrada de dados:** Garante que IDs de doador, pedido ou instituição existam antes de salvar ou atualizar registros.
- **Controle de status:** Define status padrão (PENDENTE) caso não seja informado.
- **Tratamento de Optional:** Evita `NullPointerException` e falhas de autenticação.
- **Evita inconsistência de relacionamentos:** Entidades relacionadas são sempre recarregadas antes de persistir.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Como Rodar

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/seu-repositorio-backend.git
