-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport` ;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8 ;
USE `carport` ;

-- -----------------------------------------------------
-- Table `carport`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`account` ;

CREATE TABLE IF NOT EXISTS `carport`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` INT NULL DEFAULT '2',
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`account`
(`email`,
`password`,
`role`)
VALUES
('admin@fog.dk', 1234, 1),
('test@test.dk', 1234, 2),
('a@a.dk', 1234, 2);


-- -----------------------------------------------------
-- Table `carport`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`customer` ;

CREATE TABLE IF NOT EXISTS `carport`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `zip` INT NOT NULL,
  `mobile` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  INDEX `fk_customer_user1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`account_id`)
    REFERENCES `carport`.`account` (`account_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`customer`
(`name`,
`address`,
`city`,
`zip`,
`mobile`,
`account_id`)
VALUES
('test testington', 'balladegade 42', 'Bagsv??rd', 2880, 12345678, 2),
('allan albertson', 'Bygade 20', 'S??borg', 2860, 87654321, 3);

-- -----------------------------------------------------
-- Table `carport`.`carport_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`carport_request` ;

CREATE TABLE IF NOT EXISTS `carport`.`carport_request` (
  `carport_request_id` INT NOT NULL AUTO_INCREMENT,
  `width` INT NOT NULL,
  `length` INT NOT NULL,
  `roof` VARCHAR(45) NOT NULL,
  `roof_incline` INT NULL DEFAULT NULL,
  `is_approved` TINYINT NOT NULL DEFAULT '0',
  `shed_length` INT NULL DEFAULT NULL,
  `shed_width` INT NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`carport_request_id`),
  INDEX `fk_carport_request_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_carport_request_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carport`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`carport_request`
(`width`,
`length`,
`roof`,
`is_approved`,
`shed_length`,
`shed_width`,
`customer_id`)
VALUES
(600, 780, 'Trapez', TRUE, 210, 600, 2);

-- -----------------------------------------------------
-- Table `carport`.`description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`description` ;

CREATE TABLE IF NOT EXISTS `carport`.`description` (
  `description_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`description_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`description`
(`description`)
VALUES
('understernbr??dder til for & bag ende'),
('understernbr??dder til siderne'),
('oversternbr??dder til forenden'),
('oversternbr??dder til siderne'),
('til z p?? bagside af d??r'),
('l??sholter til skur gavle'),
('l??sholter til skur sider'),
('Remme i sider, sadles ned i stolper'),
('Remme i sider, sadles ned i stolper ( skur del,
deles)'),
('Sp??r, monteres p?? rem'),
('Stolper nedgraves 90 cm. i jord'),
('til bekl??dning af skur 1 p?? 2'),
('vandbr??dt p?? stern i sider'),
('vandbr??dt p?? stern i forende'),
('tagplader monteres p?? sp??r'),
('Skruer til tagplader'),
('Til vindkryds p?? sp??r'),
('Til montering af sp??r p?? rem'),
('Til montering af stern&vandbr??dt'),
('Til montering af universalbeslag + hulb??nd'),
('Til montering af rem p?? stolper'),
('til montering af yderste bekl??dning'),
('til montering af inderste bekl??dning'),
('Til l??s p?? d??r i skur'),
('Til skurd??r'),
('Til montering af l??sholter i skur');


-- -----------------------------------------------------
-- Table `carport`.`material_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`material_type` ;

CREATE TABLE IF NOT EXISTS `carport`.`material_type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`material_type`
(`name`)
VALUES
('Tr??'),
('Tagplader'),
('Beslag & Hulb??nd & Diverse'),
('Skruer & Skiver & Bolte');
-- -----------------------------------------------------
-- Table `carport`.`material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`material` ;

CREATE TABLE IF NOT EXISTS `carport`.`material` (
  `material_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `length` INT NULL,
  `type_id` INT NOT NULL,
  `width` FLOAT NULL,
  `height` INT NULL,
  `quantity` INT NULL DEFAULT '1', 
  PRIMARY KEY (`material_id`),
  INDEX `fk_material_material_type1_idx` (`type_id` ASC) VISIBLE,
  CONSTRAINT `fk_material_material_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `carport`.`material_type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`length`,
`type_id`,
`width`,
`height`,
`quantity`)
VALUES
("trykimp. Br??t", 50, "Stk", 360, 1, 25, 200, 1),
("trykimp. Br??t", 70, "Stk", 540, 1, 25, 200, 1),
("trykimp. Br??t", 40, "Stk", 360, 1, 25, 125, 1),
("trykimp. Br??t", 50, "Stk", 540, 1, 25, 125, 1),
("L??gte ubh.", 20, "Stk", 420, 1, 38, 73, 1),
("Reglar ubh.", 45, "Stk", 270, 1, 45, 95, 1),
("Reglar ubh.", 40, "Stk", 240, 1, 45, 95, 1),
("Sp??rtr?? ubh.", 100, "Stk", 600, 1, 45, 195, 1),
("Sp??rtr?? ubh.", 80, "Stk", 480, 1, 45, 195, 1),
("Trykimp. Stolpe", 200, "Stk", 300, 1, 97, 97, 1),
("trykimp. Br??t", 45, "Stk", 210, 1, 19, 100, 1),
("trykimp. Br??t", 55, "Stk", 360, 1, 19, 100, 1),
("trykimp. Br??t", 70, "Stk", 540, 1, 19, 100, 1),
("Plastmo Ecolite bl??tonet", 45, "Stk", 360, 2, 109, 16, 1),
("Plastmo Ecolite bl??tonet", 30, "Stk", 600, 2, 109, 16, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`quantity`)
VALUES
('Plastmo bundskruer', 10, 'Pakke', 4, 200);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`width`,
`height`,
`quantity`)
VALUES
('Hulb??nd', 10, 'Rulle', 3, 1, 20, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`quantity`)
VALUES
('universal 190 mm h??jre', 5, 'Stk', 3, 1),
('universal 190 mm venstre', 5, 'Stk', 3, 1),
('Vinkelbeslag 35', 5, 'Stk', 3, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`quantity`,
`width`,
`height`)
VALUES
('Skruer', 10, 'Pakke', 4, 200, 4.5, 60),
('Beslagskruer', 10, 'Pakke', 4, 250, 4, 50),
('Br??ddebolt', 7, 'Stk', 4, 1, 10, 120);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`length`,
`type_id`,
`width`,
`height`,
`quantity`)
VALUES
("Firkantskiver", 4, "Stk", 40, 4, 11, 40, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`quantity`,
`width`,
`height`)
VALUES
('Skruer', 10, 'Pakke', 4, 400, 4.5, 70),
('Skruer', 10, 'Pakke', 4, 300, 4.5, 50);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`length`,
`type_id`,
`width`,
`height`,
`quantity`)
VALUES
("Firkantskiver", 4, "Stk", 40, 4, 11, 40, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`width`,
`height`,
`quantity`)
VALUES
('Staldd??rsgreb', 50, 1, 3, 50, 75, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`length`,
`quantity`)
VALUES
('T h??ngsel', 30, 1, 3, 390, 1);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`,
`quantity`)
VALUES
('Vinkelbeslag 35', 5, 'Stk', 3, 1);

-- -----------------------------------------------------
-- Table `carport`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order` ;

CREATE TABLE IF NOT EXISTS `carport`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `carport_type` INT NOT NULL,
  `price` INT NULL DEFAULT NULL,
  `carport_request_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orderline_has_customer_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_order_carport_request1_idx` (`carport_request_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderline_has_customer_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carport`.`customer` (`customer_id`),
  CONSTRAINT `fk_order_carport_request1`
    FOREIGN KEY (`carport_request_id`)
    REFERENCES `carport`.`carport_request` (`carport_request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`order`
(`customer_id`,
`date`,
`carport_type`,
`carport_request_id`)
VALUES
(2, '2022-02-20 00:00:00',2 , 1);

-- -----------------------------------------------------
-- Table `carport`.`bill_of_materials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`bill_of_materials` ;

CREATE TABLE IF NOT EXISTS `carport`.`bill_of_materials` (
  `bom_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`bom_id`),
  INDEX `fk_bill_of_materials_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_bill_of_materials_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `carport`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`bill_of_materials`
(`order_id`)
VALUES
(1);


-- -----------------------------------------------------
-- Table `carport`.`bomline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`bomline` (
  `bomline_id` INT NOT NULL AUTO_INCREMENT,
  `bom_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `description_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  PRIMARY KEY (`bomline_id`),
  INDEX `fk_bomline_description1_idx` (`description_id` ASC) VISIBLE,
  INDEX `fk_bomline_bill_of_materials1_idx` (`bom_id` ASC) VISIBLE,
  INDEX `fk_bomline_material1_idx` (`material_id` ASC) VISIBLE,
  CONSTRAINT `fk_bomline_bill_of_materials1`
    FOREIGN KEY (`bom_id`)
    REFERENCES `carport`.`bill_of_materials` (`bom_id`),
  CONSTRAINT `fk_bomline_description1`
    FOREIGN KEY (`description_id`)
    REFERENCES `carport`.`description` (`description_id`),
  CONSTRAINT `fk_bomline_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`material_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `carport`.`bomline`
(`bom_id`,
`quantity`,
`description_id`,
`material_id`)
VALUES
(1, 4, 1, 1),
(1, 4, 2, 2),
(1, 2, 3, 3),
(1, 4, 4, 4),
(1, 1, 5, 5),
(1, 12, 6, 6),
(1, 4, 7, 7),
(1, 2, 8, 8),
(1, 1, 9, 9),
(1, 15, 10, 10),
(1, 11, 11, 11),
(1, 200, 12, 12),
(1, 4, 13, 13),
(1, 2, 14, 14),
(1, 6, 15, 15),
(1, 6, 15, 16),
(1, 3, 16, 17),
(1, 2, 17, 18),
(1, 15, 18, 19),
(1, 15, 18, 20),
(1, 1, 19, 21),
(1, 3, 20, 22),
(1, 18, 21, 23),
(1, 12, 21, 24),
(1, 2, 22, 25),
(1, 2, 23, 26),
(1, 1, 24, 27),
(1, 1, 25, 28),
(1, 32, 26, 29);

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `material_view` AS
    SELECT 
        `m`.`material_id` AS `material_id`,
        `m`.`type_id` AS `type_id`,
        `m`.`name` AS `material_name`,
        `m`.`price` AS `price`,
        `m`.`unit` AS `unit`,
        `m`.`quantity` AS `quantity`,
        `m`.`length` AS `length`,
         `m`.`width` AS `width`,
          `m`.`height` AS `height`,
        `mt`.`name` AS `mt_name`
    FROM
        (`material` `m`
        JOIN `material_type` `mt` ON ((`m`.`type_id` = `mt`.`type_id`)));
        
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bomlines_with_materialname_and_description` AS
    SELECT 
        `b`.`bom_id` AS `bom_id`,
        `b`.`bomline_id` AS `bomline_id`,
        `m`.`name` AS `name`,
        `m`.`length` AS `length`,
        `b`.`quantity` AS `quantity`,
        `m`.`unit` AS `unit`,
        `d`.`description` AS `description`,
        `m`.`material_id` AS `material_id`,
        `m`.`price` AS `price`,
        `m`.`type_id` AS `type_id`,
        `m`.`width` AS `width`,
        `m`.`height` AS `height`,
        `m`.`quantity` AS `m_quantity`
    FROM
        ((`bomline` `b`
        JOIN `material` `m` ON ((`b`.`material_id` = `m`.`material_id`)))
        JOIN `description` `d` ON ((`b`.`description_id` = `d`.`description_id`)))
    ORDER BY `b`.`bom_id`;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `customer_and_account_overview` AS
    SELECT 
        `account`.`account_id` AS `account_id`,
        `account`.`email` AS `email`,
        `account`.`password` AS `password`,
        `account`.`role` AS `role`,
        `customer`.`customer_id` AS `customer_id`,
        `customer`.`name` AS `name`,
        `customer`.`address` AS `address`,
        `customer`.`city` AS `city`,
        `customer`.`zip` AS `zip`,
        `customer`.`mobile` AS `mobile`
    FROM
        (`account`
        JOIN `customer` ON ((`account`.`account_id` = `customer`.`account_id`)));
        
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `carport_request_with_customer_info` AS
    SELECT 
        `carport_request`.`customer_id` AS `customer_id`,
        `carport_request`.`carport_request_id` AS `carport_request_id`,
        `carport_request`.`width` AS `width`,
        `carport_request`.`length` AS `length`,
        `carport_request`.`roof` AS `roof`,
        `carport_request`.`roof_incline` AS `roof_incline`,
        `carport_request`.`is_approved` AS `is_approved`,
        `carport_request`.`shed_length` AS `shed_length`,
        `carport_request`.`shed_width` AS `shed_width`,
        `c`.`name` AS `name`,
        `c`.`address` AS `address`,
        `c`.`city` AS `city`,
        `c`.`zip` AS `zip`,
        `c`.`mobile` AS `mobile`,
        `c`.`account_id` AS `account_id`
    FROM
        (`carport_request`
        JOIN `customer` `c` ON ((`carport_request`.`customer_id` = `c`.`customer_id`)));
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `order_to_description_view` AS
    SELECT 
        `bomline`.`description_id` AS `description_id`,
        `bomline`.`material_id` AS `material_id`,
        `bill_of_materials`.`bom_id` AS `bom_id`,
        `order`.`order_id` AS `order_id`,
        `order`.`customer_id` AS `customer_id`,
        `order`.`date` AS `date`,
        `order`.`carport_type` AS `carport_type`,
        `order`.`price` AS `order_price`,
        `order`.`carport_request_id` AS `carport_request_id`,
        `bomline`.`bomline_id` AS `bomline_id`,
        `bomline`.`quantity` AS `bomline_quantity`,
        `material`.`name` AS `name`,
        `material`.`price` AS `material_price`,
        `material`.`unit` AS `unit`,
        `material`.`length` AS `length`,
        `material`.`type_id` AS `type_id`,
        `material`.`width` AS `width`,
        `material`.`height` AS `height`,
        `material`.`quantity` AS `material_quantity`,
        `description`.`description` AS `description`
    FROM
        ((((`order`
        JOIN `bill_of_materials` ON ((`order`.`order_id` = `bill_of_materials`.`order_id`)))
        JOIN `bomline` ON ((`bill_of_materials`.`bom_id` = `bomline`.`bom_id`)))
        JOIN `material` ON ((`bomline`.`material_id` = `material`.`material_id`)))
        JOIN `description` ON ((`bomline`.`description_id` = `description`.`description_id`)));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

DROP SCHEMA IF EXISTS `carport_test` ;
CREATE DATABASE  IF NOT EXISTS `carport_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport_test`;
CREATE TABLE carport_test.account LIKE carport.account;
CREATE TABLE carport_test.customer LIKE carport.customer;
CREATE TABLE carport_test.carport_request LIKE carport.carport_request;
CREATE TABLE carport_test.description LIKE carport.description;
CREATE TABLE carport_test.material_type LIKE carport.material_type;
CREATE TABLE carport_test.material LIKE carport.material;
CREATE TABLE carport_test.order LIKE carport.order;
CREATE TABLE carport_test.bill_of_materials LIKE carport.bill_of_materials;
CREATE TABLE carport_test.bomline LIKE carport.bomline;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `material_view` AS
    SELECT 
        `m`.`material_id` AS `material_id`,
        `m`.`type_id` AS `type_id`,
        `m`.`name` AS `material_name`,
        `m`.`price` AS `price`,
        `m`.`unit` AS `unit`,
        `m`.`quantity` AS `quantity`,
        `m`.`length` AS `length`,
         `m`.`width` AS `width`,
          `m`.`height` AS `height`,
        `mt`.`name` AS `mt_name`
    FROM
        (`material` `m`
        JOIN `material_type` `mt` ON ((`m`.`type_id` = `mt`.`type_id`)));
        
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bomlines_with_materialname_and_description` AS
    SELECT 
        `b`.`bom_id` AS `bom_id`,
        `b`.`bomline_id` AS `bomline_id`,
        `m`.`name` AS `name`,
        `m`.`length` AS `length`,
        `b`.`quantity` AS `quantity`,
        `m`.`unit` AS `unit`,
        `d`.`description` AS `description`,
        `m`.`material_id` AS `material_id`,
        `m`.`price` AS `price`,
        `m`.`type_id` AS `type_id`,
        `m`.`width` AS `width`,
        `m`.`height` AS `height`,
        `m`.`quantity` AS `m_quantity`
    FROM
        ((`bomline` `b`
        JOIN `material` `m` ON ((`b`.`material_id` = `m`.`material_id`)))
        JOIN `description` `d` ON ((`b`.`description_id` = `d`.`description_id`)))
    ORDER BY `b`.`bom_id`;
    
    CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `customer_and_account_overview` AS
    SELECT 
        `account`.`account_id` AS `account_id`,
        `account`.`email` AS `email`,
        `account`.`password` AS `password`,
        `account`.`role` AS `role`,
        `customer`.`customer_id` AS `customer_id`,
        `customer`.`name` AS `name`,
        `customer`.`address` AS `address`,
        `customer`.`city` AS `city`,
        `customer`.`zip` AS `zip`,
        `customer`.`mobile` AS `mobile`
    FROM
        (`account`
        JOIN `customer` ON ((`account`.`account_id` = `customer`.`account_id`)));
        
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `carport_request_with_customer_info` AS
    SELECT 
        `carport_request`.`customer_id` AS `customer_id`,
        `carport_request`.`carport_request_id` AS `carport_request_id`,
        `carport_request`.`width` AS `width`,
        `carport_request`.`length` AS `length`,
        `carport_request`.`roof` AS `roof`,
        `carport_request`.`roof_incline` AS `roof_incline`,
        `carport_request`.`is_approved` AS `is_approved`,
        `carport_request`.`shed_length` AS `shed_length`,
        `carport_request`.`shed_width` AS `shed_width`,
        `c`.`name` AS `name`,
        `c`.`address` AS `address`,
        `c`.`city` AS `city`,
        `c`.`zip` AS `zip`,
        `c`.`mobile` AS `mobile`,
        `c`.`account_id` AS `account_id`
    FROM
        (`carport_request`
        JOIN `customer` `c` ON ((`carport_request`.`customer_id` = `c`.`customer_id`)));
        CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `order_to_description_view` AS
    SELECT 
        `bomline`.`description_id` AS `description_id`,
        `bomline`.`material_id` AS `material_id`,
        `bill_of_materials`.`bom_id` AS `bom_id`,
        `order`.`order_id` AS `order_id`,
        `order`.`customer_id` AS `customer_id`,
        `order`.`date` AS `date`,
        `order`.`carport_type` AS `carport_type`,
        `order`.`price` AS `order_price`,
        `order`.`carport_request_id` AS `carport_request_id`,
        `bomline`.`bomline_id` AS `bomline_id`,
        `bomline`.`quantity` AS `bomline_quantity`,
        `material`.`name` AS `name`,
        `material`.`price` AS `material_price`,
        `material`.`unit` AS `unit`,
        `material`.`length` AS `length`,
        `material`.`type_id` AS `type_id`,
        `material`.`width` AS `width`,
        `material`.`height` AS `height`,
        `material`.`quantity` AS `material_quantity`,
        `description`.`description` AS `description`
    FROM
        ((((`order`
        JOIN `bill_of_materials` ON ((`order`.`order_id` = `bill_of_materials`.`order_id`)))
        JOIN `bomline` ON ((`bill_of_materials`.`bom_id` = `bomline`.`bom_id`)))
        JOIN `material` ON ((`bomline`.`material_id` = `material`.`material_id`)))
        JOIN `description` ON ((`bomline`.`description_id` = `description`.`description_id`)));