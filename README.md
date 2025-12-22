# Eventify Authentication

Application de gestion d'événements avec système d'authentification et d'autorisation basé sur les rôles.

## 📋 Description

Eventify Authentication est une API REST développée avec Spring Boot qui permet la gestion complète d'événements avec un système d'inscription et d'authentification utilisateur. L'application implémente une autorisation basée sur les rôles (RBAC) avec trois niveaux d'accès : Admin, Organizer et User.

## ✨ Fonctionnalités

- **Gestion des utilisateurs**
  - Inscription et authentification
  - Gestion des rôles (ADMIN, ORGANIZER, USER)
  - Profils utilisateurs

- **Gestion des événements**
  - Création, modification et suppression d'événements
  - Consultation des événements publics
  - Gestion des inscriptions aux événements

- **Système d'inscription**
  - Inscription aux événements
  - Gestion du statut d'inscription (PENDING, APPROVED, REJECTED, CANCELLED)
  - Validation des inscriptions par les organisateurs

- **Sécurité**
  - Authentification HTTP Basic
  - Autorisation basée sur les rôles
  - Protection des endpoints sensibles

## 🛠️ Technologies utilisées

- **Backend**: Spring Boot 3.x
- **Base de données**: PostgreSQL / MySQL (configurable)
- **Migration de BD**: Liquibase
- **Build**: Maven
- **Sécurité**: Spring Security
- **Containerisation**: Docker

## 📦 Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- PostgreSQL ou MySQL
- Docker (optionnel)

## 🚀 Installation et configuration

### 1. Cloner le repository

```bash
git clone <repository-url>
cd Eventify_Authentication
```

### 2. Configuration de la base de données

Modifier le fichier `src/main/resources/application.properties` avec vos paramètres de connexion :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eventify
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Compilation et lancement

#### Avec Maven

```bash
# Compilation
./mvnw clean install

# Lancement de l'application
./mvnw spring-boot:run
```

#### Avec Docker

```bash
# Construction de l'image
docker build -t eventify-authentication .

# Lancement du container
docker run -p 8080:8080 eventify-authentication
```

## 📚 Structure du projet

```
src/
├── main/
│   ├── java/com/eventify/authentication/
│   │   ├── config/           # Configuration (Security, Data Initializer)
│   │   ├── controller/       # Contrôleurs REST
│   │   ├── entity/           # Entités JPA
│   │   ├── exception/        # Gestion des exceptions
│   │   ├── repository/       # Repositories JPA
│   │   └── services/         # Logique métier
│   └── resources/
│       ├── application.properties
│       └── db/changelog/     # Scripts Liquibase
└── test/                     # Tests unitaires
```

## 🔑 Rôles et permissions

| Rôle | Permissions |
|------|-------------|
| **ADMIN** | Accès complet à toutes les fonctionnalités |
| **ORGANIZER** | Création et gestion d'événements, validation des inscriptions |
| **USER** | Consultation des événements, inscription aux événements |

## 🌐 API Endpoints

### Endpoints publics
- `POST /api/public/register` - Inscription d'un nouvel utilisateur
- `GET /api/public/events` - Liste des événements publics

### Endpoints utilisateurs
- `GET /api/users/profile` - Profil utilisateur
- `POST /api/users/events/{eventId}/register` - S'inscrire à un événement

### Endpoints organisateurs
- `POST /api/organizers/events` - Créer un événement
- `PUT /api/organizers/events/{id}` - Modifier un événement
- `GET /api/organizers/registrations` - Voir les inscriptions
- `PUT /api/organizers/registrations/{id}/approve` - Approuver une inscription

### Endpoints administrateurs
- `GET /api/admin/users` - Liste de tous les utilisateurs
- `DELETE /api/admin/users/{id}` - Supprimer un utilisateur
- `DELETE /api/admin/events/{id}` - Supprimer un événement

📄 **Collection Postman** : Utilisez le fichier `Postman.json` pour tester l'API.

## 🧪 Tests

```bash
./mvnw test
```

## 📝 Migration de base de données

Les migrations sont gérées automatiquement par Liquibase au démarrage de l'application. Les changesets sont définis dans :
- `src/main/resources/db/changelog/changeSets/`

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une issue ou une pull request.

## 📄 Licence

Ce projet est sous licence [MIT](https://github.com/achraf99sik/Eventify_Authentication/blob/main/LICENCE).

## 👥 Auteurs

- [Achraf sikal](https://github.com/achraf99sik)
- [Abdeljabar moudiri](https://github.com/Abdelmoudiri)

## 📞 Contact

Pour toute question, contactez : [Email](mailto:achrafsikal@gmail.com)
