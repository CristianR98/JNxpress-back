-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2019 a las 17:37:16
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
-- Estructura de tabla para la tabla `interests`
--

CREATE TABLE `interests` (
  `category-id` tinyint(6) NOT NULL,
  `user-id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Volcado de datos para la tabla `interests`
--

INSERT INTO `interests` (`category-id`, `user-id`) VALUES
(3, 7),
(6, 7),
(7, 7),
(9, 7),
(15, 7),
(1, 8),
(2, 8),
(5, 8),
(12, 8),
(15, 8),
(1, 9),
(5, 9),
(8, 9),
(11, 9),
(12, 9),
(3, 10),
(5, 10),
(14, 10);

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
  `product-image-min` varchar(104) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category-id` tinyint(4) NOT NULL,
  `condition-id` smallint(6) NOT NULL,
  `product-appreciation` tinyint(5) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`product-id`, `user-id`, `product-name`, `product-description`, `product-price`, `product-stock`, `product-image`, `product-image-min`, `product-date`, `category-id`, `condition-id`, `product-appreciation`) VALUES
(18, 1, 'Koala', 'un koala espero que ande', 1500.00, 4, 'users/JNxpress/Koala/Koala.jpg', 'users/JNxpress/Koala/Koala.jpg-min.jpg', '2019-11-01 05:00:27', 1, 1, 0),
(19, 7, 'Pinguinos', 'y que queres son pinguinos', 500.00, 4, 'users/CristianRr/Pinguinos/Penguins.jpg', 'users/CristianRr/Pinguinos/Penguins.jpg-min.jpg', '2019-11-01 13:00:32', 1, 1, 0),
(20, 7, 'Pinguinos', 'y que queres son pinguinos papa', 500.00, 6, 'users/CristianRr/Pinguinos/Penguins.jpg', 'users/CristianRr/Pinguinos/Penguins.jpg-min.jpg', '2019-11-01 13:02:01', 1, 2, 4),
(21, 7, 'Pinguinos', 'y que queres son pinguinos papa', 500.00, 0, 'users/CristianRr/Pinguinos/Lighthouse.jpg', 'users/CristianRr/Pinguinos/Lighthouse.jpg-min.jpg', '2019-11-01 13:02:32', 1, 1, 4),
(22, 7, 'Pinguinos', 'y que queres son pinguinos papa', 4000.00, 4, 'users/CristianRr/Pinguinos/Desert.jpg', 'users/CristianRr/Pinguinos/Desert.jpg-min.jpg', '2019-11-01 13:04:32', 2, 1, 0),
(23, 7, 'Pinguinos', 'y que queres son pinguinos papa', 4000.00, 4, 'users/CristianRr/Pinguinos/Desert.jpg', 'users/CristianRr/Pinguinos/Jellyfish.jpg-min.jpg', '2019-11-01 13:05:20', 2, 1, 0),
(24, 7, 'Nosep', 'asdafasfafasfafafsa', 5.00, 50, 'users/CristianRr/Nosep/Jellyfish.jpg', 'users/CristianRr/Nosep/Jellyfish.jpg-min.jpg', '2019-11-01 13:05:20', 3, 2, 0),
(25, 7, 'Nosep', 'asdafasfafasfafafsa', 5.00, 50, 'users/CristianRr/Nosep/Tulips.jpg', 'users/CristianRr/Nosep/Tulips.jpg-min.jpg', '2019-11-01 13:06:07', 3, 2, 0),
(26, 7, 'Nosep', 'asdafasfafasfafafsa', 5.00, 50, 'users/CristianRr/Nosep/Jellyfish.jpg', 'users/CristianRr/Nosep/Tulips.jpg-min.jpg', '2019-11-01 13:06:07', 3, 2, 0),
(27, 7, 'Pinguinos', 'y que queres son pinguinos papa', 4000.00, 4, 'users/CristianRr/Pinguinos/Desert.jpg', 'users/CristianRr/Pinguinos/Tulips.jpg-min.jpg', '2019-11-01 13:06:07', 2, 1, 0),
(28, 7, 'que onda cd', 'Anda re mal esto', 565.00, 28, 'users/CristianRr/que onda cd/Chrysanthemum.jpg', 'users/CristianRr/que onda cd/Chrysanthemum.jpg-min.jpg', '2019-11-01 13:09:32', 8, 1, 0),
(29, 8, 'Celular', 'un celular que mas queres', 7000.00, 1, 'users/Nahuel/Celular/Chrysanthemum.jpg', 'users/Nahuel/Celular/Chrysanthemum.jpg-min.jpg', '2019-11-01 13:38:46', 5, 2, 0),
(30, 8, 'Celular', 'un celular que mas queres', 7000.00, 0, 'users/Nahuel/Celular/Tulips.jpg', 'users/Nahuel/Celular/Tulips.jpg-min.jpg', '2019-11-01 13:39:33', 5, 2, 0),
(31, 8, 'Nose que mas poner', 'holaaaa', 588.00, 19, 'users/Nahuel/Nose que mas poner/Koala.jpg', 'users/Nahuel/Nose que mas poner/Koala.jpg-min.jpg', '2019-11-01 13:44:44', 10, 2, 4),
(32, 8, 'Nose que mas poner', 'holaaaa', 588.00, 24, 'users/Nahuel/Nose que mas poner/Koala.jpg', 'users/Nahuel/Nose que mas poner/Koala.jpg-min.jpg', '2019-11-01 13:46:02', 10, 2, 0),
(33, 8, 'nose porque se duplica', 'a ver si lo hace', 10000.00, 11, 'users/Nahuel/nose porque se duplica/Penguins.jpg', 'users/Nahuel/nose porque se duplica/Penguins.jpg-min.jpg', '2019-11-01 13:47:21', 8, 1, 0),
(34, 1, 'Un celular', 'es un celu tiene sus cosas no sabes xd', 7500.00, 2, 'users/JNxpress/Un celular/Lighthouse.jpg', 'users/JNxpress/Un celular/Lighthouse.jpg-min.jpg', '2019-11-01 15:15:43', 3, 2, 0);

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
(11, 'Instrumentos Musicales'),
(12, 'Juegos y Juguetes'),
(13, 'Libros, Revistas y Comics'),
(14, 'Música, Películas y Series'),
(15, 'Ropa y Accesorios'),
(16, 'Otras categorías');

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
-- Estructura de tabla para la tabla `products-favorites`
--

CREATE TABLE `products-favorites` (
  `user-id` smallint(6) NOT NULL,
  `product-id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products-favorites`
--

INSERT INTO `products-favorites` (`user-id`, `product-id`) VALUES
(7, 27),
(7, 28),
(8, 33),
(8, 31),
(8, 23),
(1, 21),
(1, 31),
(1, 29),
(1, 33),
(1, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products-reviews`
--

CREATE TABLE `products-reviews` (
  `product-review-id` smallint(6) NOT NULL,
  `user-id` smallint(6) NOT NULL,
  `product-id` smallint(6) NOT NULL,
  `product-review-content` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `product-review-appreciation` tinyint(1) NOT NULL,
  `product-review-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `products-reviews`
--

INSERT INTO `products-reviews` (`product-review-id`, `user-id`, `product-id`, `product-review-content`, `product-review-appreciation`, `product-review-date`) VALUES
(1, 8, 21, 'Muy bueno', 4, '2019-11-01 14:26:39'),
(2, 8, 20, 'muy bueno!', 4, '2019-11-01 15:02:32'),
(3, 1, 31, 'Muy buen producto', 4, '2019-11-01 15:13:43');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchases`
--

CREATE TABLE `purchases` (
  `purchase-id` smallint(6) NOT NULL,
  `user-id` smallint(6) NOT NULL,
  `product-id` smallint(6) NOT NULL,
  `purchase-lot` smallint(255) NOT NULL,
  `purchase-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `purchases`
--

INSERT INTO `purchases` (`purchase-id`, `user-id`, `product-id`, `purchase-lot`, `purchase-date`) VALUES
(1, 8, 21, 1, '2019-11-01 14:24:06'),
(2, 8, 21, 3, '2019-11-01 14:26:59'),
(3, 1, 31, 5, '2019-11-01 15:13:22'),
(4, 1, 30, 2, '2019-11-01 15:31:19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

CREATE TABLE `sales` (
  `sale-id` mediumint(6) NOT NULL,
  `user-id` smallint(6) NOT NULL,
  `product-id` smallint(6) NOT NULL,
  `sale-lot` smallint(255) NOT NULL,
  `sale-date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `sales`
--

INSERT INTO `sales` (`sale-id`, `user-id`, `product-id`, `sale-lot`, `sale-date`) VALUES
(1, 7, 21, 1, '2019-11-01 14:24:06'),
(2, 7, 21, 3, '2019-11-01 14:26:59'),
(3, 8, 31, 5, '2019-11-01 15:13:22'),
(4, 8, 30, 2, '2019-11-01 15:31:19');

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
(1, 'JNxpress', 'jnxpress@gmail.com', '12345678', 12392323.00, 'Fundador de JNxpress!', 0, 1, 2),
(2, 'Cristian', 'cristian@gmail.com', 'qwerty123', 28282.00, 'Soy nuevo xd', 0, 0, 0),
(3, 'CristianR', 'cris@gmail.com', 'qwerty123', 4132.00, 'hola como estan?', 0, 0, 0),
(4, 'Cristian389', 'Cress@jnse.com', 'qwertasd', -39876.00, 'Cliente con ganas de comprar xd', 0, 0, 3),
(5, 'CristianR98', 'c.nahu.roman@gmail.com', 'qwerty123', 1231.00, 'wqrqrrwrqrqrqwr qnoseeeppp', 0, 0, 0),
(6, 'romancris', 'cristi@gmail.com', 'asdfghjk', 31241.00, 'hola que hace', 0, 0, 3),
(7, 'CristianRr', 'cristianr@gmail.com', 'qwerty123', 5500.00, 'hola soy nuevo xd', 2, 0, 5),
(8, 'Nahuel', 'nahu@gmail.com', 'qwerty123', 28912.00, 'Vendedor de 40 aÃ±os xd', 2, 1, 2),
(9, 'CristianR9851', 'pepee@gmail.com', 'qwerty123', 1234.00, 'ahfnewuihiwnfvuo', 0, 0, 0),
(10, 'CrisRoman', 'pepe@gmail.com', 'qwerty123', 1000.00, 'afasfafasfasf', 0, 0, 0);

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
(1, 1, 3, 'Muy buen vendedor', 4, '2019-10-31 00:29:03'),
(2, 1, 3, 'Muy buen vendedor', 4, '2019-10-31 00:29:05'),
(3, 1, 3, 'Muy buen vendedor', 4, '2019-10-31 00:29:05'),
(4, 1, 3, 'Muy buen vendedor', 4, '2019-10-31 00:29:05'),
(5, 1, 3, 'Muy buen vendedor', 4, '2019-10-31 00:32:09'),
(6, 6, 2, 'afasfafafasfafasfaf', 0, '2019-11-01 00:20:27'),
(7, 6, 2, 'asdasfasfaf', 0, '2019-11-01 00:21:24'),
(8, 6, 1, 'fafasffasfasf', 0, '2019-11-01 00:28:38'),
(9, 6, 1, 'asdadasdasd', 0, '2019-11-01 00:28:53'),
(10, 6, 1, 'asdasdasdasd', 0, '2019-11-01 00:28:57'),
(11, 6, 1, 'asdasdafasfsaf', 3, '2019-11-01 00:41:00'),
(12, 6, 1, 'asfasfasfasf', 2, '2019-11-01 00:41:07'),
(13, 6, 1, 'asdasdasdasf', 1, '2019-11-01 00:41:12'),
(14, 6, 1, 'asdasdfasfasf', 3, '2019-11-01 00:43:41'),
(15, 6, 1, 'asdasfasfasf', 2, '2019-11-01 00:45:19'),
(16, 6, 1, 'asdadasfsafafasf', 1, '2019-11-01 00:46:05'),
(17, 6, 1, 'asfafasfasfasf', 3, '2019-11-01 00:46:17'),
(18, 6, 1, 'asfasfafafgasgag', 4, '2019-11-01 00:46:35'),
(19, 6, 1, 'asfasfafaf', 3, '2019-11-01 00:47:15'),
(20, 6, 1, 'asdasdasdasf', 3, '2019-11-01 00:48:07'),
(21, 6, 1, 'asfafasfasfafsf', 2, '2019-11-01 00:48:12'),
(22, 6, 1, 'ffadsgasgfgdgdfggdfg', 3, '2019-11-01 01:02:04'),
(23, 1, 4, 'dasdasdafasf', 3, '2019-11-01 01:14:42'),
(24, 1, 4, 'asdasfasfafasfaf', 2, '2019-11-01 01:17:20'),
(25, 1, 4, 'asdasfasfafasfaf', 3, '2019-11-01 01:17:21'),
(26, 1, 4, 'muy bueno!', 5, '2019-11-01 01:18:16'),
(27, 1, 6, 'maso meno', 3, '2019-11-01 01:45:03'),
(28, 7, 1, 'Maso meno', 3, '2019-11-01 13:13:19'),
(29, 8, 7, 'Muy buen vendedor!', 5, '2019-11-01 14:27:33'),
(30, 1, 8, 'Muy buen vendedor!\n', 4, '2019-11-01 15:14:07'),
(31, 1, 8, 'malisimo xd', 1, '2019-11-01 15:31:43');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `interests`
--
ALTER TABLE `interests`
  ADD KEY `user-id` (`user-id`),
  ADD KEY `category-id` (`category-id`);

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
-- Indices de la tabla `products-favorites`
--
ALTER TABLE `products-favorites`
  ADD KEY `user-id` (`user-id`),
  ADD KEY `product-id` (`product-id`);

--
-- Indices de la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  ADD PRIMARY KEY (`product-review-id`),
  ADD KEY `id-product` (`product-id`),
  ADD KEY `product-id` (`product-id`),
  ADD KEY `user-id` (`user-id`);

--
-- Indices de la tabla `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`purchase-id`),
  ADD KEY `user-id` (`user-id`),
  ADD KEY `product-id` (`product-id`);

--
-- Indices de la tabla `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale-id`),
  ADD KEY `user-id` (`user-id`),
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
  MODIFY `product-id` smallint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `products-categories`
--
ALTER TABLE `products-categories`
  MODIFY `category-id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `products-condition`
--
ALTER TABLE `products-condition`
  MODIFY `condition-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  MODIFY `product-review-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `purchases`
--
ALTER TABLE `purchases`
  MODIFY `purchase-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `sales`
--
ALTER TABLE `sales`
  MODIFY `sale-id` mediumint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `users-reviews`
--
ALTER TABLE `users-reviews`
  MODIFY `user-review-id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `interests`
--
ALTER TABLE `interests`
  ADD CONSTRAINT `interests_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `interests_ibfk_2` FOREIGN KEY (`category-id`) REFERENCES `products-categories` (`category-id`);

--
-- Filtros para la tabla `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category-id`) REFERENCES `products-categories` (`category-id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`condition-id`) REFERENCES `products-condition` (`condition-id`);

--
-- Filtros para la tabla `products-favorites`
--
ALTER TABLE `products-favorites`
  ADD CONSTRAINT `products-favorites_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `products-favorites_ibfk_2` FOREIGN KEY (`product-id`) REFERENCES `products` (`product-id`);

--
-- Filtros para la tabla `products-reviews`
--
ALTER TABLE `products-reviews`
  ADD CONSTRAINT `products-reviews_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `products-reviews_ibfk_2` FOREIGN KEY (`product-id`) REFERENCES `products` (`product-id`);

--
-- Filtros para la tabla `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`product-id`) REFERENCES `products` (`product-id`);

--
-- Filtros para la tabla `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`product-id`) REFERENCES `products` (`product-id`);

--
-- Filtros para la tabla `users-reviews`
--
ALTER TABLE `users-reviews`
  ADD CONSTRAINT `users-reviews_ibfk_1` FOREIGN KEY (`target-id`) REFERENCES `users` (`user-id`),
  ADD CONSTRAINT `users-reviews_ibfk_2` FOREIGN KEY (`user-id`) REFERENCES `users` (`user-id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
