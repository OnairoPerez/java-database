-- Creación de la base de datos Tienda Online
CREATE DATABASE IF NOT EXISTS `Tienda_Online`;
USE `Tienda_Online`;

-- Creación de la tabla Persona
CREATE TABLE IF NOT EXISTS `Persona` (
`nombre` VARCHAR(50) NOT NULL,
`apellido` VARCHAR(50) NOT NULL,
`cedula` VARCHAR(15) NOT NULL UNIQUE,
`telefono` VARCHAR(15) NULL,
`direccion` VARCHAR(150) NULL,
`ciudad` VARCHAR(25) NULL,
PRIMARY KEY (`cedula`)
);
-- Creación de la tabla Cuenta
CREATE TABLE IF NOT EXISTS `Cuenta` (
`id_cuenta` VARCHAR(10) NOT NULL UNIQUE,
`correo` VARCHAR(45) NOT NULL,
`hash` VARCHAR(110) NOT NULL UNIQUE,
`salt` VARCHAR(20) NOT NULL UNIQUE,
`persona_cedula` VARCHAR(15) NOT NULL UNIQUE,
PRIMARY KEY (`id_cuenta`),
UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
INDEX `fk_Cuenta_Persona_idx` (`persona_cedula` ASC) VISIBLE,
FOREIGN KEY (`persona_cedula`)
REFERENCES `Persona` (`cedula`)
ON DELETE CASCADE
ON UPDATE CASCADE
);
-- Creación de la tabla Fidelidad
CREATE TABLE IF NOT EXISTS `Fidelidad` (
`persona_cedula` VARCHAR(15) NOT NULL,
`puntos` INT NOT NULL DEFAULT 0,
INDEX `fk_Fidelidad_Persona_idx` (`persona_cedula` ASC) VISIBLE,
CONSTRAINT `fk_Fidelidad_Persona`
FOREIGN KEY (`persona_cedula`)
REFERENCES `Persona` (`cedula`)
ON DELETE CASCADE
ON UPDATE CASCADE
);
-- Creación de la tabla Categorias
CREATE TABLE IF NOT EXISTS `Categorias` (
`id_categoria` TINYINT NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id_categoria`)
);
-- Creación de la tabla Marca
CREATE TABLE IF NOT EXISTS `Marca` (
`id_Marca` TINYINT NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id_Marca`)
);
-- Creación de la tabla Productos
CREATE TABLE IF NOT EXISTS `Productos` (
`codigo` VARCHAR(20) NOT NULL,
`nombre` VARCHAR(45) NOT NULL,
`valor_compra` FLOAT NOT NULL,
`valor_venta` FLOAT NOT NULL,
`existencias` INT NOT NULL,
`Categorias_id_categoria` TINYINT NOT NULL,
`Marca_id_Marca` TINYINT NOT NULL,
PRIMARY KEY (`codigo`),
INDEX `fk_Productos_Categorias_idx` (`Categorias_id_categoria` ASC) VISIBLE,
INDEX `fk_Productos_Marca_idx` (`Marca_id_Marca` ASC) VISIBLE,
CONSTRAINT `fk_Productos_Categorias`
FOREIGN KEY (`Categorias_id_categoria`)
REFERENCES `Categorias` (`id_categoria`)
ON DELETE NO ACTION
ON UPDATE CASCADE,
CONSTRAINT `fk_Productos_Marca`
FOREIGN KEY (`Marca_id_Marca`)
REFERENCES `Marca` (`id_Marca`)
ON DELETE NO ACTION
ON UPDATE CASCADE
);
-- Creación de la tabla FormaDePago
CREATE TABLE IF NOT EXISTS `FormaDePago` (
`id_FormaDePago` TINYINT NOT NULL AUTO_INCREMENT,
`metodo` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id_FormaDePago`)
);
-- Creación de la tabla Factura
CREATE TABLE IF NOT EXISTS `Factura` (
`id_factura` VARCHAR(37) NOT NULL UNIQUE,
`fecha` DATE NOT NULL,
`hora` TIME NOT NULL,
`subtotal` FLOAT NOT NULL,
`cedula` VARCHAR(15) NOT NULL,
`cambio` FLOAT NOT NULL,
`pago` FLOAT NOT NULL,
`metodo` TINYINT NOT NULL,

PRIMARY KEY (`id_factura`),
INDEX `fk_Factura_Persona_idx` (`cedula` ASC) VISIBLE,
INDEX `fk_Factura_Metodo_idx` (`metodo` ASC) VISIBLE,
CONSTRAINT `fk_Factura_Persona`
FOREIGN KEY (`cedula`)
REFERENCES `Persona` (`cedula`)
ON DELETE RESTRICT
ON UPDATE CASCADE,
CONSTRAINT `fk_Factura_Metodo`
FOREIGN KEY (`metodo`)
REFERENCES `FormaDePago` (`id_FormaDePago`)
ON DELETE RESTRICT
ON UPDATE CASCADE
);
-- Creación de la tabla ProductoXFactura
CREATE TABLE IF NOT EXISTS `ProductoXFactura` (
`valor_unitario` INT NOT NULL,
`cantidad` INT NOT NULL,
`monto_total` FLOAT NOT NULL,
`factura_id_factura` VARCHAR(37) NOT NULL,
`Productos_codigo` VARCHAR(20) NOT NULL,
INDEX `fk_ProductoXFactura_Factura_idx` (`factura_id_factura` ASC) VISIBLE,
INDEX `fk_ProductoXFactura_Productos_idx` (`Productos_codigo` ASC) VISIBLE,
CONSTRAINT `fk_ProductoXFactura_Factura`
FOREIGN KEY (`factura_id_factura`)
REFERENCES `Factura` (`id_factura`)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT `fk_ProductoXFactura_Productos`
FOREIGN KEY (`Productos_codigo`)
REFERENCES `Productos` (`codigo`)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);
