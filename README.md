# 🍽️ RestLogix - Sistema de Gestão de Estoque para Restaurantes  

-- English Version Bellow

🔹 **Versão**: 1.3  
🔹 **Responsável**: João Pedro Bohm  

## 📌 Apresentação  
O **RestLogix** é um sistema desenvolvido em **Java** com integração ao **MySQL** para gerenciamento de estoque em restaurantes, oferecendo:  
✔ Controle de insumos e validade de produtos.  
✔ Geração de relatórios de movimentação.  
✔ Planejamento de compras baseado em fichas técnicas.  
✔ Alertas automáticos para vencimentos e baixo estoque.  

---

## 🎯 Funcionalidades  

### **👨‍💼 Módulos por Perfil**  
| **Gerente**               | **Estoquista**            | **Cozinha**                |  
|---------------------------|---------------------------|----------------------------|  
| CRUD de produtos/usuários | Registrar entradas/saídas | Cadastrar fichas técnicas  |  
| Configurar fichas técnicas| Consultar alertas         | Solicitar insumos          |  
| Visualizar logs           | Acessar relatórios        |                            |  

### **📋 Requisitos Funcionais**  
- **RF01**: Cadastro de itens (CRUD).  
- **RF02**: Registro de movimentações (entradas/saídas).  
- **RF03**: Alertas de validade (ex.: 7 dias antes).  
- **RF04**: Relatórios mensais em tabela.  
- **RF05**: Fichas técnicas (insumos x quantidades).  
- **RF06**: Cálculo de demanda semanal.  
- **RF07**: Histórico de movimentações.  
- **RF08**: Autenticação por perfil.  

---

## 🚧 Em Desenvolvimento 

Este projeto ainda está em desenvolvimento ativo. Algumas funcionalidades foram prototipadas mas ainda não estão completamente implementadas.  

**Status atual**:  
✔ Funcionalidades básicas parcialmente implementadas  
⚠ Algumas features em fase experimental  
🚧 Componentes principais sendo refinados 

---


## 🛠️ Tecnologias  
| **Backend**       | **Frontend** | **Banco de Dados** | **Outros**       |  
|-------------------|-------------|--------------------|------------------|  
| Java (JDK 17+)    | JavaFX/Swing| MySQL 8.0+         | Git/GitHub       |  
| POO               |             | Triggers (alertas) | JDBC             |  




----------------------------------------------------------------------------------------------------------------------------------------------------------------------




## English Version

# 🍽️ RestLogix - Restaurant Inventory Management System  

🔹 **Version**: 1.3  
🔹 **Maintainer**: João Pedro Witt Bohm  

## 📌 Overview  
**RestLogix** is a **Java-based** system integrated with **MySQL** designed to streamline inventory management for restaurants. Key features include:  
✔ Real-time stock tracking and expiry alerts.  
✔ Automated purchase planning based on recipe cards.  
✔ Role-based access control (Manager, Stockkeeper, Kitchen).  
✔ Monthly movement reports and low-stock notifications.  

---

## 🎯 Key Features  

### **👨‍💼 Role-Based Modules**  
| **Manager**               | **Stockkeeper**         | **Kitchen**                |  
|---------------------------|-------------------------|----------------------------|  
| CRUD (products/users)     | Register in/out items   | Manage recipe cards        |  
| Configure recipe cards    | Check expiry alerts     | Request ingredients       |  
| View system logs          | Generate reports        |                            |  

### **📋 Functional Requirements**  
- **RF01**: Product CRUD operations.  
- **RF02**: Stock movement records (in/out).  
- **RF03**: Expiry alerts (e.g., 7 days in advance).  
- **RF04**: Monthly PDF/table reports.  
- **RF05**: Recipe cards (ingredients × quantities).  
- **RF06**: Weekly demand forecasting.  
- **RF07**: Movement history per product.  
- **RF08**: User authentication (JWT/OAuth2).  

---

## 🚧 Under Development | ⚠️ Not Production Ready

This project is still under active development. Some features have been prototyped but are not yet fully implemented.  

**Current status**:  
✔ Core functionality partially implemented  
⚠ Some features in experimental phase  
🚧 Major components still being refined  

---

## 🛠️ Tech Stack  
| **Backend**       | **Frontend** | **Database**       | **Dev Tools**     |  
|-------------------|-------------|--------------------|------------------|  
| Java (JDK 17+)    | JavaFX/Swing| MySQL 8.0+         | Git/GitHub       |  
| OOP               |             | Triggers (alerts)  | JDBC             |  

---

