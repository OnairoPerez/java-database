CREATE USER 'SQL_Admin'@'localhost' IDENTIFIED BY 'zjKgjWK/xRyQcJyu+bU4';
GRANT DELETE, INSERT, SELECT, UPDATE ON Tienda_Online.* TO 'SQL_Admin'@'localhost';

FLUSH PRIVILEGES;

-- Eliminar usuario
-- DROP USER 'SQL_Admin'@'localhost';

-- Mostrar permisos
-- SHOW GRANTS FOR 'SQL_Admin'@'localhost';
