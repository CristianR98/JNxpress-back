-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2019 a las 04:05:39
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jnxpress`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `product-id` smallint(255) NOT NULL,
  `user-id` smallint(255) NOT NULL,
  `product-name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product-description` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product-price` float(9,2) NOT NULL,
  `product-stock` tinyint(3) NOT NULL,
  `product-image` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category-id` tinyint(4) NOT NULL,
  `condition-id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`product-id`, `user-id`, `product-name`, `product-description`, `product-price`, `product-stock`, `product-image`, `product-date`, `category-id`, `condition-id`) VALUES
(1, 1, 'Topper Street Nova - Rojas', 'Otra descripcion', 588.00, 24, 'url', '2019-10-06 00:19:46', 5, 1),
(2, 2, 'Topper Street Nova - Rojas', 'Otra descripcion', 588.00, 24, 'url', '2019-10-06 00:51:02', 5, 1),
(3, 1, 'Moto one', 'Un celular, no tengo ganas de buscar la descripción pero si de escribir esto xd.', 7000.00, 5, 'Despues arreglo esto xd', '2019-10-22 00:07:16', 1, 1),
(4, 1, 'Topper Street Nova', 'Zapatillas peolas xd', 1200.00, 23, 'noseeeep', '2019-10-22 00:14:24', 1, 2),
(5, 1, 'Topper Street Nova', 'Zapatillas peolas xd', 1000000.00, 43, 'noseeeep', '2019-10-22 00:17:58', 1, 2),
(6, 1, 'Topper Street Nova - Rojas', 'Nueva descripcion xd', 588.00, 24, 'url', '2019-10-22 00:30:10', 5, 1),
(7, 1, 'Topper Street Nova', 'Zapatillas peolas xd', 1245.50, 43, 'noseeeep', '2019-10-22 00:30:26', 1, 2),
(8, 1, 'Topper Street Nova', 'Topper Street NovaTopper Street NovaTopper Street Nova', 1245.50, 43, 'noseeeep', '2019-10-22 00:33:19', 1, 2),
(9, 1, 'Topper Street Nova - Rojas', 'Otra descripcion', 588.00, 24, 'url', '2019-10-22 00:33:26', 5, 1),
(10, 1, 'Topper Street Nova', 'Topper Street NovaTopper Street NovaTopper Street Nova\n                              Topper Street NovaTopper Street NovaTopper Street Nova\n                              Topper Street NovaTopper Street NovaTopper Street Nova\n                              Topper Street NovaTopper Street NovaTopper Street Nova', 1245.50, 43, 'noseeeep', '2019-10-22 18:51:42', 1, 2),
(11, 1, 'Topper Street Nova', 'Topper Street NovaTopper Street NovaTopper Street Nova\n                                  Topper Street NovaTopper Street NovaTopper Street Nova\n                                  Topper Street NovaTopper Street NovaTopper Street Nova\n                                  Topper Street NovaTopper Street NovaTopper Street Nova', 1245.50, 43, 'noseeeep', '2019-10-22 19:19:44', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products-categories`
--

CREATE TABLE `products-categories` (
  `category-id` tinyint(4) NOT NULL,
  `category-name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products-categories`
--

INSERT INTO `products-categories` (`category-id`, `category-name`) VALUES
(1, 'Animales y Mascotas'),
(2, 'Antigüedades y Colecciones'),
(3, 'Autos, Motos y Otros Vehículos'),
(4, 'Belleza y Cuidado Personal'),
(5, 'Celulares y Teléfonos'),
(6, 'Computación'),
(7, 'Consolas y Videojuegos'),
(8, 'Electrodomésticos'),
(9, 'Electrónica, Audio y Video'),
(10, 'Herramientas y Construcción'),
(11, 'Hogar, Muebles y Jardín'),
(12, 'Instrumentos Musicales'),
(13, 'Joyas y Relojes'),
(14, 'Juegos y Juguetes'),
(15, 'Libros, Revistas y Comics'),
(16, 'Música, Películas y Series'),
(17, 'Ropa y Accesorios'),
(18, 'Otras categorías');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products-condition`
--

CREATE TABLE `products-condition` (
  `condition-id` smallint(6) NOT NULL,
  `condition-name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products-condition`
--

INSERT INTO `products-condition` (`condition-id`, `condition-name`) VALUES
(1, 'Nuevo'),
(2, 'Usado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products-reviews`
--

CREATE TABLE `products-reviews` (
  `product-review-id` smallint(6) NOT NULL,
  `user-id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product-id` smallint(6) NOT NULL,
  `product-review-content` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `product-review-appreciation` tinyint(1) NOT NULL,
  `product-review-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products-reviews`
--

INSERT INTO `products-reviews` (`product-review-id`, `user-id`, `product-id`, `product-review-content`, `product-review-appreciation`, `product-review-date`) VALUES
(1, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:41:48'),
(2, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:41:48'),
(3, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:41:48'),
(4, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:42:32'),
(5, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:42:33'),
(6, '6', 3, 'Muy buen producto', 4, '2019-10-24 18:43:31'),
(7, '6', 3, 'Muy buen producto', 4, '2019-10-24 19:58:42'),
(8, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:01:40'),
(9, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:37'),
(10, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:38'),
(11, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:38'),
(12, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:38'),
(13, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:38'),
(14, '6', 3, 'Muy buen producto', 4, '2019-10-24 20:02:39');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user-id` smallint(6) NOT NULL,
  `user-username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user-email` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user-password` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user-balance` float(10,2) NOT NULL,
  `user-description` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user-sales` smallint(6) NOT NULL DEFAULT '0',
  `user-purchases` smallint(6) DEFAULT '0',
  `user-appreciation` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user-id`, `user-username`, `user-email`, `user-password`, `user-balance`, `user-description`, `user-sales`, `user-purchases`, `user-appreciation`) VALUES
(1, 'JNxpress', 'jnxpress@gmail.com', 'quemirasxd', 12343245.00, 'Fundador de JNxpress!', 0, 0, 5),
(2, 'Nahuel', 'c.nahu.roman@gmail.com', 'qwerty123', 1234413.50, 'Nueva descripcion', 0, 0, 4),
(3, 'Cristian', 'jnxpress@jnx.com', 'qwerty1fkahofijafjia', 10000.00, 'Nueva descripcion', 0, 0, 0),
(4, 'Cristina', 'nahu.r@jnx.com', 'qwertasd', 10000.00, 'Nueva descripcion', 0, 0, 0),
(5, 'Pepito', 'maru.chan@jnx.com', 'qwertasd', 47283.00, 'Nueva descripcion', 0, 0, 0),
(6, 'Pepito', 'crisdenawa@jnx.com', 'qwertasd', 64362.00, 'Nueva descripcion', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users-reviews`
--

CREATE TABLE `users-reviews` (
  `user-review-id` smallint(6) NOT NULL,
  `user-id` smallint(6) NOT NULL,
  `target-id` smallint(6) NOT NULL,
  `user-review-content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user-review-appreciation` tinyint(5) NOT NULL,
  `user-review-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `users-reviews`
--

INSERT INTO `users-reviews` (`user-review-id`, `user-id`, `target-id`, `user-review-content`, `user-review-appreciation`, `user-review-date`) VALUES
(1, 1, 2, 'Excelente vendedor!', 5, '2019-10-24 18:42:25'),
(2, 1, 2, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(3, 1, 2, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(4, 1, 2, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(5, 1, 4, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(6, 1, 5, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(7, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(8, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(9, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(10, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:25'),
(11, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:30'),
(12, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:42:33'),
(13, 6, 3, 'Muy buen vendedor', 4, '2019-10-24 18:43:30');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product-id`),
  ADD KEY `users-id` (`user-id`),
  ADD KEY `users-id_2` (`user-id`),
  ADD KEY `id-category` (`category-id`),
  ADD KEY `condition-id` (`condition-id`);

--
-- Indices de la tabla `products-categories`
--
ALTER TABLE `products-categories`
  ADD PRIMARY KEY (`category-id`);

--
-- Indices de la tabla `products-condition`
--
ALTER TABLE `products-condition`
  ADD PRIMARY KEY (`condition-id`);

--
-- Indices de la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  ADD PRIMARY KEY (`product-review-id`),
  ADD KEY `id-product` (`product-id`),
  ADD KEY `product-id` (`product-id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user-id`);

--
-- Indices de la tabla `users-reviews`
--
ALTER TABLE `users-reviews`
  ADD PRIMARY KEY (`user-review-id`),
  ADD KEY `id-user-review` (`user-id`),
  ADD KEY `id-user-receptor` (`target-id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `product-id` smallint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `products-categories`
--
ALTER TABLE `products-categories`
  MODIFY `category-id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `products-condition`
--
ALTER TABLE `products-condition`
  MODIFY `condition-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  MODIFY `product-review-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `users-reviews`
--
ALTER TABLE `users-reviews`
  MODIFY `user-review-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category-id`) REFERENCES `products-categories` (`category-id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`condition-id`) REFERENCES `products-condition` (`condition-id`);

--
-- Filtros para la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  ADD CONSTRAINT `products-reviews_ibfk_1` FOREIGN KEY (`product-id`) REFERENCES `products` (`product-id`);

--
-- Filtros para la tabla `users-reviews`
--
ALTER TABLE `users-reviews`
  ADD CONSTRAINT `users-reviews_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `users-reviews_ibfk_2` FOREIGN KEY (`target-id`) REFERENCES `users` (`user-id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
