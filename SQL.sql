create database TiendaBolso;
use TiendaBolso;


CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- =========================
-- TABLA PRODUCTO
-- =========================
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,

    id_categoria INT NOT NULL,

    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categoria(id_categoria)
);
ALTER TABLE producto
ADD COLUMN id_usuario INT;

ALTER TABLE producto
ADD CONSTRAINT fk_producto_usuario
FOREIGN KEY (id_usuario)
REFERENCES usuario(id_usuario);

-- =========================
-- TABLA COMPRA
-- =========================
CREATE TABLE compra (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,

    id_usuario INT NOT NULL,

    CONSTRAINT fk_compra_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
        ON DELETE CASCADE
);

-- =========================
-- TABLA DETALLE_COMPRA
-- =========================
CREATE TABLE detalle_compra (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,

    id_compra INT NOT NULL,
    id_producto INT NOT NULL,

    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_detalle_compra
        FOREIGN KEY (id_compra)
        REFERENCES compra(id_compra)
        ON DELETE CASCADE,

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
);















-- Tabla Rol
CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL
);

-- Tabla Ruta
CREATE TABLE ruta (
    id_ruta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    url VARCHAR(255) UNIQUE NOT NULL
);

-- Relación Usuario-Rol (Muchos a Muchos)
CREATE TABLE rol_usuario (
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,

    PRIMARY KEY (id_usuario, id_rol),

    CONSTRAINT fk_rol_usuario_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),

    CONSTRAINT fk_rol_usuario_rol
        FOREIGN KEY (id_rol)
        REFERENCES rol(id_rol)
);

-- Relación Rol-Ruta (Muchos a Muchos)
CREATE TABLE rol_ruta (
    id_rol INT NOT NULL,
    id_ruta INT NOT NULL,

    PRIMARY KEY (id_rol, id_ruta),

    CONSTRAINT fk_rol_ruta_rol
        FOREIGN KEY (id_rol)
        REFERENCES rol(id_rol),

    CONSTRAINT fk_rol_ruta_ruta
        FOREIGN KEY (id_ruta)
        REFERENCES ruta(id_ruta)
);
INSERT INTO categoria (nombre, descripcion) VALUES
('Tote', 'Bolsos amplios para uso diario.'),
('Mochilas', 'Mochilas urbanas, deportivas y ejecutivas.'),
('Bandoleras', 'Bolsos con correa para llevar cruzados.'),
('Clutch', 'Bolsos pequeños para eventos y ocasiones especiales.'),
('Riñoneras', 'Bolsos deportivos para cintura.'),
('Carteras', 'Carteras y bolsos elegantes de mano.');

INSERT INTO producto
(nombre, descripcion, precio, imagen, stock, id_categoria, id_usuario)
VALUES

(
'Bolso Tote Elegante',
'Bolso tote de cuero genuino con acabado premium. Amplio compartimento ideal para el día a día.',
89.99,
'https://images.unsplash.com/photo-1590874103328-eac38a683ce7?w=400',
25,
1,
4
),

(
'Mochila Urbana',
'Mochila moderna con compartimento para laptop. Perfecta para la ciudad.',
69.99,
'https://images.unsplash.com/photo-1622560480605-d83c853bc5c3?w=400',
18,
2,
4
),

(
'Cartera Bandolera',
'Bandolera de diseño minimalista con correa ajustable. Ideal para salir.',
54.99,
'https://images.unsplash.com/photo-1566150905458-1bf1fc113f0d?w=400',
30,
3,
4
),

(
'Bolso Clutch Noche',
'Clutch elegante con detalles brillantes para ocasiones especiales.',
45.99,
'https://images.unsplash.com/photo-1565814329452-e1efa11c5b89?w=400',
15,
4,
4
),

(
'Bolso Shopper',
'Bolso grande tipo shopper en tela resistente. Ideal para compras y viajes.',
39.99,
'https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=400',
20,
1,
4
),

(
'Riñonera Deportiva',
'Riñonera ajustable perfecta para actividades al aire libre.',
29.99,
'https://images.unsplash.com/photo-1594226801341-41427b4e5c22?w=400',
40,
5,
4
),

(
'Cartera de Mano',
'Cartera de mano con estructura rígida y cierre magnético. Estilo sofisticado.',
64.99,
'https://images.unsplash.com/photo-1563903530908-afdd155d057a?w=400',
16,
6,
4
),

(
'Bolso Cruzado',
'Bolso cruzado de lona con múltiples bolsillos. Práctico y casual.',
34.99,
'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400',
28,
3,
4
),

(
'Mochila Ejecutiva',
'Mochila de cuero para ejecutivos con organizador interno y puerto USB.',
89.99,
'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=400',
12,
2,
4
),

(
'Bolso Acolchado',
'Bolso acolchado trendy con cadena dorada. El must-have de la temporada.',
74.99,
'https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400',
22,
6,
4
),

(
'Clutch Sobre',
'Sobre clutch minimalista para bodas y eventos formales.',
38.99,
'https://images.unsplash.com/photo-1566150905458-1bf1fc113f0d?w=400',
17,
4,
4
),

(
'Bolso Viajero XL',
'Bolso de viaje extra grande con ruedas y múltiples compartimentos.',
119.99,
'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=400',
8,
1,
4
);
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE producto;

SET FOREIGN_KEY_CHECKS = 1;


use db_ferreteria;
select * from producto;
select * from categoria;
use tiendabolso;
select * from usuario;
select * from compra;
select * from detalle_compra;

alter table articulos modify id bigint not null auto_increment 
