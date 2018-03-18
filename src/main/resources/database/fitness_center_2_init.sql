-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fitness_center_2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fitness_center_2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fitness_center_2` DEFAULT CHARACTER SET utf8 ;
USE `fitness_center_2` ;

-- -----------------------------------------------------
-- Table `fitness_center_2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness_center_2`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role` ENUM('client', 'trainer', 'admin') NULL DEFAULT 'client',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_center_2`.`clients_has_trainers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness_center_2`.`clients_has_trainers` (
  `clients_has_trainers_id` INT NOT NULL AUTO_INCREMENT,
  `training_client_id` INT NOT NULL,
  `training_client_personal_trainer_id` INT NOT NULL,
  PRIMARY KEY (`clients_has_trainers_id`),
  INDEX `training_client_id_idx` (`training_client_id` ASC),
  INDEX `training_client_personal_trainer_id_idx` (`training_client_personal_trainer_id` ASC),
  CONSTRAINT `training_client_id`
    FOREIGN KEY (`training_client_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `training_client_personal_trainer_id`
    FOREIGN KEY (`training_client_personal_trainer_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_center_2`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness_center_2`.`review` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `from_client_id` INT NOT NULL,
  `about_trainer_id` INT NOT NULL,
  `text` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE INDEX `review_id_UNIQUE` (`review_id` ASC),
  INDEX `from_client_id_idx` (`from_client_id` ASC),
  INDEX `about_trainer_id_idx` (`about_trainer_id` ASC),
  CONSTRAINT `from_client_id`
    FOREIGN KEY (`from_client_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `about_trainer_id`
    FOREIGN KEY (`about_trainer_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_center_2`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness_center_2`.`appointment` (
  `appointment_id` INT NOT NULL AUTO_INCREMENT,
  `excercise` ENUM('ex1', 'ex2') NOT NULL DEFAULT 'ex1',
  `food` ENUM('food1', 'food2') NOT NULL DEFAULT 'food1',
  `start_date` DATE NULL DEFAULT CURDATE(),
  `end_date` DATE NULL DEFAULT ADDDATE(CURDATE(), 14);,
  PRIMARY KEY (`appointment_id`),
  UNIQUE INDEX `appointment_id_UNIQUE` (`appointment_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_center_2`.`clients_has_appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fitness_center_2`.`clients_has_appointments` (
  `clients_has_appointments_id` INT NOT NULL AUTO_INCREMENT,
  `current_appointment_id` INT NOT NULL,
  `to_client_id` INT NOT NULL,
  `from_trainer_id` INT NOT NULL,
  PRIMARY KEY (`clients_has_appointments_id`),
  INDEX `to_client_id_idx` (`to_client_id` ASC),
  INDEX `from_trainer_id_idx` (`from_trainer_id` ASC),
  INDEX `current_appointment_id_idx` (`current_appointment_id` ASC),
  CONSTRAINT `to_client_id`
    FOREIGN KEY (`to_client_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `from_trainer_id`
    FOREIGN KEY (`from_trainer_id`)
    REFERENCES `fitness_center_2`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `current_appointment_id`
    FOREIGN KEY (`current_appointment_id`)
    REFERENCES `fitness_center_2`.`appointment` (`appointment_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
