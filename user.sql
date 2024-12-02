USE pharmanet;
-- Crear permisos
INSERT INTO permissions (id, name) VALUES
(1, 'CREATE'),
(2, 'READ'),
(3, 'UPDATE'),
(4, 'DELETE');

-- Crear roles
INSERT INTO roles (id, role_name) VALUES
(1, 'ADMIN'),
(2, 'CAJERO');

-- Asignar permisos a roles en la tabla role_permissions
-- Permisos para ADMIN (todos los permisos)
INSERT INTO role_permissions (role_id, permission_id) VALUES
(1, 1), -- ADMIN tiene permiso CREATE
(1, 2), -- ADMIN tiene permiso READ
(1, 3), -- ADMIN tiene permiso UPDATE
(1, 4); -- ADMIN tiene permiso DELETE

-- Permisos para CAJERO (solo CREATE y READ)
INSERT INTO role_permissions (role_id, permission_id) VALUES
(2, 2); -- CAJERO tiene permiso READ

INSERT INTO users (id, account_no_expired, account_no_locked, credential_no_expired, is_enabled, password, username)
 VALUES (1, 1, 1,1,1, "$2a$10$kXILNOW.GrFoCCnmuqku1eYOYdnuOZ5rsZjWpBmU9Vm47kJyW7vFy", "admin");

 INSERT INTO user_roles (user_id, role_id) VALUES (1, 1)