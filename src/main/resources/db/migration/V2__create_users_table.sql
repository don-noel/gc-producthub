CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(20) NOT NULL DEFAULT 'VIEWER',
    enabled     BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Utilisateurs de test (mot de passe = "password123" hashé en bcrypt)
INSERT INTO users (email, password, role) VALUES
('admin@gchub.com',   '$2a$10$s2LY9PVU.DFHyp3Dh9JJt.Ct7r5GyHYLiwu73ZyOu8fbmDIBgd2JG', 'ADMIN'),
('manager@gchub.com', '$2a$10$s2LY9PVU.DFHyp3Dh9JJt.Ct7r5GyHYLiwu73ZyOu8fbmDIBgd2JG', 'MANAGER'),
('viewer@gchub.com',  '$2a$10$s2LY9PVU.DFHyp3Dh9JJt.Ct7r5GyHYLiwu73ZyOu8fbmDIBgd2JG', 'VIEWER');