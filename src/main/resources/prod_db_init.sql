-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fitness_center
-- -----------------------------------------------------
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
--
DROP SCHEMA IF EXISTS `fitness_center` ;

-- -----------------------------------------------------
-- Schema fitness_center
--
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
--
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fitness_center` DEFAULT CHARACTER SET utf8 ;
USE `fitness_center` ;

-- -----------------------------------------------------
-- Table `fitness_center`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`role` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`role` (
  `idrole` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_center`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`user` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `years_old` INT NULL,
  `sex` VARCHAR(1) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`),
  CONSTRAINT `fk_user_role1`
  FOREIGN KEY (`role_idrole`)
  REFERENCES `fitness_center`.`role` (`idrole`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'пользователь';


-- -----------------------------------------------------
-- Table `fitness_center`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`client` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`client` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `discount` SMALLINT NULL COMMENT 'скидка, если есть',
  `client_user_iduser` INT NOT NULL,
  PRIMARY KEY (`idclient`),
  CONSTRAINT `fk_client_user1`
  FOREIGN KEY (`client_user_iduser`)
  REFERENCES `fitness_center`.`user` (`iduser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'клиент';


-- -----------------------------------------------------
-- Table `fitness_center`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`trainer` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`trainer` (
  `idtrainer` INT NOT NULL AUTO_INCREMENT,
  `education_or_level` VARCHAR(45) NOT NULL DEFAULT 'кмс',
  `cost_per_lesson` DECIMAL NOT NULL DEFAULT 0,
  `trainer_user_iduser` INT NOT NULL,
  PRIMARY KEY (`idtrainer`),
  CONSTRAINT `fk_trainer_user1`
  FOREIGN KEY (`trainer_user_iduser`)
  REFERENCES `fitness_center`.`user` (`iduser`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'тренер';


-- -----------------------------------------------------
-- Table `fitness_center`.`order_client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`order_client` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`order_client` (
  `idorder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_training` VARCHAR(45) NOT NULL COMMENT 'тип тренировки',
  `cost_of_lessons` DECIMAL NULL COMMENT 'итоговая сумма,сколько клиент должен заплатить фитнесс-центру.высчитывается автоматически',
  `order_client_idclient` INT NOT NULL,
  `order_trainer_idtrainer` INT NOT NULL,
  `number_of_lessons` INT(11) NULL DEFAULT 1,
  PRIMARY KEY (`idorder`),
  CONSTRAINT `fk_order_client`
  FOREIGN KEY (`order_client_idclient`)
  REFERENCES `fitness_center`.`client` (`idclient`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_trainer1`
  FOREIGN KEY (`order_trainer_idtrainer`)
  REFERENCES `fitness_center`.`trainer` (`idtrainer`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'таблица заказа клиента';


-- -----------------------------------------------------
-- Table `fitness_center`.`exercises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`exercises` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`exercises` (
  `idexercises` INT NOT NULL AUTO_INCREMENT,
  `muscle_group` VARCHAR(45) NOT NULL COMMENT 'название упражнения',
  `names_of_exercises` TEXT NULL COMMENT 'описание упражнения',
  `equipment` VARCHAR(45) NULL COMMENT 'снаряды,которые могут понадобиться для данного упражнения, а могут не понадобиться',
  PRIMARY KEY (`idexercises`))
  ENGINE = InnoDB
  COMMENT = 'таблица упражнений.здесь указано какие упражнения делать,их описание и снаряды,которые понадобятся';


-- -----------------------------------------------------
-- Table `fitness_center`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`food` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`food` (
  `idfood` INT NOT NULL AUTO_INCREMENT,
  `name_of_dish` VARCHAR(45) NOT NULL COMMENT 'название блюда',
  `data_receipt` DATE NOT NULL COMMENT 'дата приема',
  `time_of_receipt` TIME NOT NULL COMMENT 'время приема',
  PRIMARY KEY (`idfood`))
  ENGINE = InnoDB
  COMMENT = 'пища,в этой таблице указано какое блюдо когда принимать';


-- -----------------------------------------------------
-- Table `fitness_center`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`appointments` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`appointments` (
  `idappointments` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `exercises_idexercises` INT NOT NULL,
  `food_idfood` INT NOT NULL,
  `appointments_client_idclient` INT NOT NULL,
  PRIMARY KEY (`idappointments`),
  CONSTRAINT `fk_appointments_exercises1`
  FOREIGN KEY (`exercises_idexercises`)
  REFERENCES `fitness_center`.`exercises` (`idexercises`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_appointments_food1`
  FOREIGN KEY (`food_idfood`)
  REFERENCES `fitness_center`.`food` (`idfood`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_appointments_client1`
  FOREIGN KEY (`appointments_client_idclient`)
  REFERENCES `fitness_center`.`client` (`idclient`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'таблица назначений';


-- -----------------------------------------------------
-- Table `fitness_center`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fitness_center`.`reviews` ;

CREATE TABLE IF NOT EXISTS `fitness_center`.`reviews` (
  `idreviews` INT NOT NULL COMMENT 'id отзыва',
  `reviews_client_idclient` INT NOT NULL COMMENT 'id пользователя, который оставил комментарий',
  `text_review` VARCHAR(45) NULL COMMENT 'текст отзыва\n',
  `mark` TINYINT(4) NULL COMMENT 'оценка фитнесс центру',
  PRIMARY KEY (`idreviews`, `reviews_client_idclient`),
  CONSTRAINT `fk_reviews_client1`
  FOREIGN KEY (`reviews_client_idclient`)
  REFERENCES `fitness_center`.`client` (`idclient`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'таблица отзывов клиентов';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
