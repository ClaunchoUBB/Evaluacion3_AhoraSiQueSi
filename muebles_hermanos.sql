CREATE DATABASE IF NOT EXISTS muebles_hermanos;
USE muebles_hermanos;

CREATE TABLE IF NOT EXISTS mueble (
    ID_Mueble INT PRIMARY KEY AUTO_INCREMENT,
    nombre_mueble VARCHAR(100),
    tipo VARCHAR(50),
    precio_base INT ,
    stock INT  DEFAULT 0,
    activo BOOLEAN DEFAULT 1,
    tamano ENUM('Pequeño', 'Mediano', 'Grande'),
    material ENUM('Madera', 'Metal', 'Plastico', 'Vidrio') 
) ;

CREATE TABLE IF NOT EXISTS cotizacion (
    ID_Cotizacion INT PRIMARY KEY AUTO_INCREMENT,
    fecha_cotizacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    total int DEFAULT 0
) ;

CREATE TABLE IF NOT EXISTS variantes (
    ID_Variante INT PRIMARY KEY AUTO_INCREMENT,
    ID_Mueble INT NOT NULL,
    descripcion VARCHAR(255),
    precio_adicional INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_variantes_mueble 
        FOREIGN KEY (ID_Mueble) 
        REFERENCES mueble(ID_Mueble) 
        ON DELETE CASCADE
) ;

CREATE TABLE IF NOT EXISTS cot_mueble (
    ID_Cot_Mueble INT PRIMARY KEY AUTO_INCREMENT,
    ID_Cotizacion INT NOT NULL,
    ID_Mueble INT NOT NULL,
    ID_Variante INT NOT NULL,
    cantidad INT DEFAULT 1,
    precio_unitario INT, 
    subtotal int AS (precio_unitario * cantidad) STORED,
    CONSTRAINT fk_cotmueble_cotizacion 
        FOREIGN KEY (ID_Cotizacion) 
        REFERENCES cotizacion(ID_Cotizacion) ,
    CONSTRAINT fk_cotmueble_mueble 
        FOREIGN KEY (ID_Mueble) 
        REFERENCES mueble(ID_Mueble) ,
    CONSTRAINT fk_variantes_cotizacion 
        FOREIGN KEY (ID_Variante) 
        REFERENCES variantes(ID_Variante) 
) ;


CREATE TABLE IF NOT EXISTS venta (
    ID_Venta INT PRIMARY KEY AUTO_INCREMENT,
    ID_cotizacion INT NOT NULL,
    fecha_venta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_venta int NOT NULL,
    CONSTRAINT fk_venta_cotizacion 
        FOREIGN KEY (ID_cotizacion) 
        REFERENCES cotizacion(ID_Cotizacion) 
        ON DELETE CASCADE
) ; 






