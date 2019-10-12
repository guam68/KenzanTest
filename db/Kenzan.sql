-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema employeedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `employeedb` ;

-- -----------------------------------------------------
-- Schema employeedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employeedb` DEFAULT CHARACTER SET utf8 ;
USE `employeedb` ;

-- -----------------------------------------------------
-- Table `employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee` ;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_initial` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NULL,
  `date_of_employment` DATE NULL,
  `status` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` TEXT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  `enabled` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS employee;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'employee' IDENTIFIED BY 'employee';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'employee';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `employeedb`;
INSERT INTO `employee` (`id`, `first_name`, `middle_initial`, `last_name`, `date_of_birth`, `date_of_employment`, `status`) VALUES (1, 'James', 'E', 'Taylor', '1984-10-12', '2015-01-30', 1);
INSERT INTO `employee` (`id`, `first_name`, `middle_initial`, `last_name`, `date_of_birth`, `date_of_employment`, `status`) VALUES (2, 'Sarah', NULL, 'Conner', '1967-04-24', '2018-04-13', 1);
INSERT INTO `employee` (`id`, `first_name`, `middle_initial`, `last_name`, `date_of_birth`, `date_of_employment`, `status`) VALUES (3, 'Paul', 'R', 'West', '1990-11-02', '2019-06-12', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `employeedb`;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `enabled`) VALUES (1, 'kenzan', '$2a$10$KqSKT7QByBooYVE6WDicNucr5gqcME6EcdquH0M5t//OS18E1OlZ6', 'ROLE_USER', 1);

COMMIT;

