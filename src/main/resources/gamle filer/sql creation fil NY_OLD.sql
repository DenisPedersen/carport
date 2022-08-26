-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
ENGINE = InnoDB;

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
    REFERENCES `carport`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `carport`.`customer`
(`name`,
`address`,
`city`,
`zip`,
`mobile`,
`account_id`)
VALUES
('test testington', 'balladegade 42', 'Bagsværd', 2880, 12345678, 2),
('allan albertson', 'Bygade 20', 'Søborg', 2860, 87654321, 3);


-- -----------------------------------------------------
-- Table `carport`.`material_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`material_type` ;

CREATE TABLE IF NOT EXISTS `carport`.`material_type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB;

INSERT INTO `carport`.`material_type`
(`name`)
VALUES
('Træ & Tagplader'),
('Beslag & Skruer');


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
  `width` VARCHAR(45) NULL,
  `height` VARCHAR(45) NULL,
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
`height`)
VALUES
('25x200 mm. trykimp. Brædt', 50, 'Stk', 720, 1, 25, 200),
('45x95 mm. Reglar ub.', 25, 'Stk', 720, 1, 45, 95);

INSERT INTO `carport`.`material`
(`name`,
`price`,
`unit`,
`type_id`)
VALUES
('plastmo bundskruer 200 stk.', 10, 'Pakke', 2),
('universal 190 mm højre', 5, 'Stk', 2);

-- -----------------------------------------------------
-- Table `carport`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order` ;

CREATE TABLE IF NOT EXISTS `carport`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orderline_has_customer_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderline_has_customer_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `carport`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `carport`.`order`
(`customer_id`,
`date`)
VALUES
(1, '2022-02-20 00:00:00'),
(2, '2022-04-11 08:50:27');

-- -----------------------------------------------------
-- Table `carport`.`description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`description` ;

CREATE TABLE IF NOT EXISTS `carport`.`description` (
  `description_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`description_id`))
ENGINE = InnoDB;

INSERT INTO `carport`.`description`
(`description`)
VALUES
('understernbrædder til for & bag ende'),
('løsholter til skur gavle'),
('Skruer til tagplader'),
('understernbrædder til for & bag ende'),
('løsholter til skur gavle'),
('Skruer til tagplader');


-- -----------------------------------------------------
-- Table `carport`.`orderline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`orderline` ;

CREATE TABLE IF NOT EXISTS `carport`.`orderline` (
  `orderline_id` INT NOT NULL AUTO_INCREMENT,
  `material_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `description_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`orderline_id`),
  INDEX `fk_orderline_material1_idx` (`material_id` ASC) VISIBLE,
  INDEX `fk_orderline_order1_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_orderline_description1_idx` (`description_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderline_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`material_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderline_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `carport`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderline_description1`
    FOREIGN KEY (`description_id`)
    REFERENCES `carport`.`description` (`description_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `carport`.`orderline`
(`material_id`,
`quantity`,
`description_id`,
`order_id`)
VALUES
(1, 20, 1, 1),
(2, 5, 2, 1),
(3, 2, 3, 1),
(1, 10, 4, 2),
(2, 2, 5, 2),
(3, 1, 6, 2);

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
        `m`.`length` AS `length`,
         `m`.`width` AS `width`,
          `m`.`height` AS `height`,
        `mt`.`name` AS `mt_name`
    FROM
        (`material` `m`
        JOIN `material_type` `mt` ON ((`m`.`type_id` = `mt`.`type_id`)));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


DROP SCHEMA IF EXISTS `carport_test` ;
CREATE DATABASE  IF NOT EXISTS `carport_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport_test`;
CREATE TABLE carport_test.account LIKE carport.account;
CREATE TABLE carport_test.customer LIKE carport.customer;
CREATE TABLE carport_test.material_type LIKE carport.material_type;
CREATE TABLE carport_test.material LIKE carport.material;
CREATE TABLE carport_test.order LIKE carport.order;
CREATE TABLE carport_test.description LIKE carport.description;
CREATE TABLE carport_test.orderline LIKE carport.orderline;

CREATE VIEW `material_view` AS
SELECT 
        `m`.`material_id` AS `material_id`,
        `m`.`type_id` AS `type_id`,
        `m`.`name` AS `material_name`,
        `m`.`price` AS `price`,
        `m`.`unit` AS `unit`,
        `m`.`length` AS `length`,
         `m`.`width` AS `width`,
          `m`.`height` AS `height`,
        `mt`.`name` AS `mt_name`
    FROM
        (`material` `m`
        JOIN `material_type` `mt` ON ((`m`.`type_id` = `mt`.`type_id`)));
