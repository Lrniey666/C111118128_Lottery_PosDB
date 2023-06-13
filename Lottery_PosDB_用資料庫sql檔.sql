DROP DATABASE IF EXISTS `c111118128_db_pos`;
CREATE DATABASE IF NOT EXISTS `c111118128_db_pos`;
USE `c111118128_db_pos`;


DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` varchar(20) NOT NULL,
  `category` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` int(11) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `product` (`product_id`, `category`, `name`, `price`, `photo`, `description`) VALUES
	('p-2k-101', '2k', '2000萬開運發財金', 2000, '2k-1.jpg', '兩千'),
	('p-2k-102', '2k', '2000萬超級紅包', 2000, '2k-2.jpg', '產品描述'),
	('p-1k-103', '1k', '1000萬行大運', 1000, '1k-1.jpg', '產品描述'),
	('p-1k-104', '1k', '1200萬大吉利', 1000, '1k-2.jpg','產品描述'),
	('p-5-105', '1k', '1000萬年終獎金', 1000, '1k-3.jpg', '產品描述'),
	('p-5-106', '500', '金兔獎', 500, '5-1.jpg', '產品描述'),
	('p-5-107', '500', '五福臨門', 500, '5-2.jpg', '產品描述'),
	('p-5-108', '500', '財神發紅包', 500, '5-3.jpg', '產品描述'),
	('p-5-109', '500', '黃金大連線', 500, '5-4.jpg', '產品描述'),
	('p-5-110', '500', '五路財神', 500, '5-5.jpg', '產品描述'),
	('p-2-111', '200', '鴻運當頭', 200, '2-1.jpg', '產品描述'),
	('p-2-112', '200', '大三元', 200, '2-2.jpg', '產品描述'),
	('p-2-113', '200', '金兔報喜', 200, '2-3.jpg', '產品描述'),
	('p-2-114', '200', '黃金滿屋', 200, '2-4.jpg', '產品描述'),
	('p-2-115', '200', '刮刮金樂透', 200, '2-5.jpg', '產品描述'),
	('p-2-116', '200', '麻將', 200, '2-6.jpg', '產品描述'),
  ('p-2-117', '200', '好運連發', 200, '2-7.jpg', '產品描述'),
  ('p-1-118', '100', '聚寶盆', 100, '1-1.jpg', '產品描述'),
  ('p-1-119', '100', '好運旺來', 100, '1-2.jpg', '產品描述'),
  ('p-1-120', '100', '兔年行大運', 100, '1-3.jpg', '產品描述'),
  ('p-1-121', '100', '金鼓開運', 100, '1-4.jpg', '產品描述'),
  ('p-1-122', '100', '財神報到', 100, '1-5.jpg', '產品描述'),
  ('p-1-123', '100', '招財兔', 100, '1-6.jpg', '產品描述');


DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE IF NOT EXISTS `sale_order` (
  `order_num` varchar(20) NOT NULL,
  `order_date` datetime NOT NULL DEFAULT current_timestamp(),
  `total_price` double(22,0) NOT NULL DEFAULT 0,
  `customer_name` varchar(150) DEFAULT NULL,
  `customer_address` varchar(250) DEFAULT NULL,
  `customer_phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `sale_order` (`order_num`, `order_date`, `total_price`, `customer_name`, `customer_address`, `customer_phone`) VALUES
('ord-101', '2021-05-04 22:54:47', 70, '王範例', '高雄市楠梓區大學路一號', '093256789'),
('ord-102', '2021-05-04 22:55:19', 380, '王範例', '高雄市楠梓區大學路一號', '093256789');

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(20) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `product_price` int(11) DEFAULT NULL,
  `product_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_detail_product` (`product_id`),
  KEY `FK_order_detail_order` (`order_num`),
  CONSTRAINT `FK_order_detail_sale_order` FOREIGN KEY (`order_num`) REFERENCES `sale_order` (`order_num`),
  CONSTRAINT `FK_order_detail_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


INSERT INTO `order_detail` (`id`, `order_num`, `product_id`, `quantity`, `product_price`, `product_name`) VALUES
(67, 'ord-101', 'p-2k-101', 1, 2000, '2000萬開運發財金'),
(68, 'ord-102', 'p-2k-102', 1, 2000, '2000萬超級紅包'),
(69, 'ord-102', 'p-1k-103', 3, 1000, '1000萬行大運');

 
order_detailproductsale_order