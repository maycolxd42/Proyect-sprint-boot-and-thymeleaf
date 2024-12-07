CREATE DATABASE Pizzeriadb3

Use Pizzeriadb3

-- Tabla Cliente
CREATE TABLE Cliente (
    idCliente INT IDENTITY(1,1) PRIMARY KEY,
    nombreCompleto NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    contrasena NVARCHAR(100) NOT NULL,
    telefono NVARCHAR(15) NOT NULL,
    fechaNacimiento DATE NOT NULL
);

-- Tabla CategoriaProducto (uno a muchos con Producto)
CREATE TABLE CategoriaProducto (
    idCategoriaProducto INT PRIMARY KEY,
    nombreCategoria VARCHAR(100)
);

-- Tabla Producto (muchos a uno con CategoriaProducto, muchos a muchos con Pedido a través de DetallePedido)
CREATE TABLE Producto (
    idProducto INT PRIMARY KEY,
    nombre NVARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    imagen VARCHAR(100),
    idCategoriaProducto INT,
    FOREIGN KEY (idCategoriaProducto) REFERENCES CategoriaProducto(idCategoriaProducto) -- Relación con CategoriaProducto
);

-- Tabla Inventario (uno a uno con Producto)
CREATE TABLE Inventario (
    idInventario INT PRIMARY KEY,
    idProducto INT,
    cantidadDisponible INT,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) -- Relación con Producto
);

-- Tabla Empleado
CREATE TABLE Empleado (
    idEmpleado INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    telefono NVARCHAR(15),
    email NVARCHAR(100) NOT NULL UNIQUE,
    contrasena NVARCHAR(255) NOT NULL
);

-- Tabla Soporte (sin relaciones directas, independiente)
CREATE TABLE Soporte (
    idSoporte INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(255),
    email VARCHAR(255),
    mensaje TEXT,
    fecha DATETIME DEFAULT GETDATE()
);

-- Tabla Pedido (muchos a uno con Cliente, uno a muchos con DetallePedido y Transaccion)
CREATE TABLE Pedido (
    idPedido INT PRIMARY KEY IDENTITY(1,1),
    idCliente INT,
    fechaPedido DATETIME DEFAULT GETDATE(),
    estado NVARCHAR(50) DEFAULT 'Pendiente',
    total DECIMAL(10, 2),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) -- Relación con Cliente
);

-- Tabla DetallePedido (muchos a uno con Pedido, muchos a uno con Producto)
CREATE TABLE DetallePedido (
    idDetallePedido INT PRIMARY KEY IDENTITY(1,1),
    idPedido INT,
    idProducto INT,
    cantidad INT,
    precioUnitario DECIMAL(10, 2),
    subtotal DECIMAL(10, 2),
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido), -- Relación con Pedido
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) -- Relación con Producto
);

-- Tabla TipoDocumento (uno a muchos con Transaccion)
CREATE TABLE TipoDocumento (
    idTipoDocumento INT PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL UNIQUE
);

-- Tabla Transaccion (muchos a uno con Pedido, muchos a uno con TipoDocumento)
CREATE TABLE Transaccion (
    idTransaccion INT PRIMARY KEY IDENTITY(1,1),
    idPedido INT,
    idTipoDocumento INT,
    estado NVARCHAR(50),
    fechaTransaccion DATETIME,
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido), -- Relación con Pedido
    FOREIGN KEY (idTipoDocumento) REFERENCES TipoDocumento(idTipoDocumento) -- Relación con TipoDocumento
);


INSERT INTO TipoDocumento (idTipoDocumento, nombre) VALUES 
(1, 'Factura'),
(2, 'Boleta');


INSERT INTO CategoriaProducto (idCategoriaProducto, nombreCategoria) VALUES 
(1, 'Pizzas'),
(2, 'Bebidas');

INSERT INTO Producto (idProducto, nombre, descripcion, precio, imagen, idCategoriaProducto) VALUES 
(1, 'Margarita', 'Pizza clásica con salsa de tomate y queso mozzarella.', 12.00, 'margarita.jpg', 1),
(2, 'Pepperoni', 'Pizza con pepperoni y queso mozzarella.', 15.00, 'pepperoni.jpg', 1),
(3, 'Hawaiana', 'Pizza con piña, jamón y queso mozzarella.', 14.00, 'hawaiana.jpg', 1),
(4, 'Cuatro Quesos', 'Pizza con mezcla de cuatro tipos de queso.', 16.00, 'cuatroquesos.jpg', 1),
(5, 'Vegetariana', 'Pizza con pimientos, cebolla, champiñones y queso mozzarella.', 13.00, 'vegetariana.jpg', 1),
(6, 'Mexicana', 'Pizza con carne molida, jalapeños, pimientos y queso cheddar.', 17.00, 'mexicana.jpg', 1),
(7, 'Barbacoa', 'Pizza con pollo, salsa barbacoa y cebolla caramelizada.', 18.00, 'barbacoa.jpg', 1),
(8, 'Napolitana', 'Pizza clásica con anchoas, alcaparras y aceitunas.', 15.00, 'napolitana.jpg', 1),
(9, 'Coca Cola', 'Bebida gaseosa Coca Cola', 12.00, 'coca_cola.jpg', 2),
(10, 'Fanta', 'Bebida gaseosa Fanta', 9.50, 'fanta.jpg', 2),
(11, 'Sprite', 'Bebida gaseosa Sprite', 7.50, 'sprite.jpg', 2),
(12, 'Inca Cola', 'Bebida gaseosa Inka Cola', 12.00, 'inca_cola.jpg', 2);

INSERT INTO Inventario (idInventario, idProducto, cantidadDisponible) VALUES
(1, 1, 40),
(2, 2, 40),
(3, 3, 40),
(4, 4, 40),
(5, 5, 40),
(6, 6, 40),
(7, 7, 40),
(8, 8, 40),
(9, 9, 40),
(10, 10, 40),
(11, 11, 40),
(12, 12, 40);

INSERT INTO Empleado (nombre, telefono, email, contrasena) VALUES 
('Pedro Ramirez', '5551234567', 'pedro.r@pizzeria.delicias.com', 'pedro123'),
('Maycol Mayhua', '5557654321', 'maycol.m@pizzeria.delicias.com', 'maycolxd'),
('Renzo Goyzueta', '5559876543', 'renzo.g@pizzeria.delicias.com', 'renzo423');

Select*from Pedido
Select*from Transaccion
Select*from DetallePedido

drop table DetallePedido
drop table Pedido
drop table TipoDocumento
drop table Transaccion

