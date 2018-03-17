-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema haltura_fitness_test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `haltura_fitness_test` ;

-- -----------------------------------------------------
-- Schema haltura_fitness_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `haltura_fitness_test` DEFAULT CHARACTER SET utf8 ;
USE `haltura_fitness_test` ;

-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`appointments` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`appointments` (
  `idappointments` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `exercises_idexercises` INT(11) NOT NULL,
  `food_idfood` INT(11) NOT NULL,
  `client_idclient` INT(11) NOT NULL,
  PRIMARY KEY (`idappointments`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8
COMMENT = 'таблица назначений';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`client` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`client` (
  `idclient` INT(11) NOT NULL AUTO_INCREMENT,
  `discount` SMALLINT(6) NULL DEFAULT NULL COMMENT 'скидка, если есть',
  `user_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`idclient`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COMMENT = 'клиент';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`exercises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`exercises` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`exercises` (
  `idexercises` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `muscle_group` VARCHAR(45) NOT NULL COMMENT 'название упражнения',
  `names_of_exercises` TEXT NULL DEFAULT NULL COMMENT 'описание упражнения',
  `equipment` VARCHAR(45) NULL DEFAULT NULL COMMENT 'снаряды,которые могут понадобиться для данного упражнения, а могут не понадобиться',
  PRIMARY KEY (`idexercises`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = 'таблица упражнений.здесь указано какие упражнения делать,их описание и снаряды,которые понадобятся';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`food` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`food` (
  `idfood` INT(11) NOT NULL AUTO_INCREMENT,
  `name_of_dish` VARCHAR(45) NOT NULL COMMENT 'название блюда',
  `data_receipt` DATE NOT NULL COMMENT 'дата приема',
  `time_of_receipt` TIME NOT NULL COMMENT 'время приема',
  PRIMARY KEY (`idfood`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8
COMMENT = 'пища,в этой таблице указано какое блюдо когда принимать';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`order_client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`order_client` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`order_client` (
  `idorder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_training` VARCHAR(45) NOT NULL COMMENT 'тип тренировки',
  `cost_of_lessons` DECIMAL(10,0) NULL DEFAULT NULL COMMENT 'итоговая сумма,сколько клиент должен заплатить фитнесс-центру.высчитывается автоматически',
  `client_idclient` INT(11) NOT NULL,
  `trainer_idtrainer` INT(11) NOT NULL,
  PRIMARY KEY (`idorder`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8
COMMENT = 'таблица заказа клиента';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`reviews` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`reviews` (
  `idreviews` INT(11) NOT NULL COMMENT 'id отзыва',
  `client_idclient` INT(11) NOT NULL COMMENT 'id пользователя, который оставил комментарий',
  `text_review` VARCHAR(45) NULL DEFAULT NULL COMMENT 'текст отзыва\n',
  `mark` TINYINT(4) NULL DEFAULT NULL COMMENT 'оценка фитнесс центру',
  PRIMARY KEY (`idreviews`, `client_idclient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'таблица отзывов клиентов';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`role` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`role` (
  `idrole` INT(11) NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`trainer` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`trainer` (
  `idtrainer` INT(11) NOT NULL AUTO_INCREMENT,
  `education_or_level` VARCHAR(45) NOT NULL DEFAULT 'кмс',
  `cost_per_lesson` DECIMAL(10,0) NOT NULL DEFAULT '0',
  `user_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`idtrainer`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = 'тренер';


-- -----------------------------------------------------
-- Table `haltura_fitness_test`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haltura_fitness_test`.`user` ;

CREATE TABLE IF NOT EXISTS `haltura_fitness_test`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `years_old` INT(11) NULL DEFAULT NULL,
  `sex` VARCHAR(1) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_idrole` INT(11) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COMMENT = 'пользователь';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
