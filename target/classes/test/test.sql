-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
-- 
DROP SCHEMA IF EXISTS `test` ;

-- -----------------------------------------------------
-- Schema test
--
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`role` ;

CREATE TABLE IF NOT EXISTS `test`.`role` (
  `idrole` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`user` ;

CREATE TABLE IF NOT EXISTS `test`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `years_old` INT NULL,
  `sex` VARCHAR(1) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`))
  ENGINE = InnoDB
  COMMENT = 'пользователь';


-- -----------------------------------------------------
-- Table `test`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`client` ;

CREATE TABLE IF NOT EXISTS `test`.`client` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `discount` SMALLINT NULL COMMENT 'скидка, если есть',
  `user_iduser` INT NOT NULL,
  `user_role_idrole` INT NOT NULL,
  PRIMARY KEY (`idclient`, `user_iduser`, `user_role_idrole`))
  ENGINE = InnoDB
  COMMENT = 'клиент';


-- -----------------------------------------------------
-- Table `test`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`trainer` ;

CREATE TABLE IF NOT EXISTS `test`.`trainer` (
  `idtrainer` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `education_or_level` VARCHAR(45) NOT NULL,
  `cost_per_lesson` DECIMAL NOT NULL DEFAULT 0,
  `user_iduser` INT NOT NULL,
  `user_role_idrole` INT NOT NULL,
  PRIMARY KEY (`idtrainer`, `user_iduser`, `user_role_idrole`))
  ENGINE = InnoDB
  COMMENT = 'тренер';


-- -----------------------------------------------------
-- Table `test`.`order_client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`order_client` ;

CREATE TABLE IF NOT EXISTS `test`.`order_client` (
  `idorder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_training` VARCHAR(45) NOT NULL COMMENT 'тип тренировки',
  `cost_of_lessons` DECIMAL NULL COMMENT 'итоговая сумма,сколько клиент должен заплатить фитнесс-центру.высчитывается автоматически',
  `client_idclient` INT NOT NULL,
  `trainer_idtrainer` INT NOT NULL,
  PRIMARY KEY (`idorder`, `client_idclient`, `trainer_idtrainer`))
  ENGINE = InnoDB
  COMMENT = 'таблица заказа клиента';


-- -----------------------------------------------------
-- Table `test`.`exercises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`exercises` ;

CREATE TABLE IF NOT EXISTS `test`.`exercises` (
  `idexercises` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `muscle_group` VARCHAR(45) NOT NULL COMMENT 'название упражнения',
  `names_of_exercises` TEXT NULL COMMENT 'описание упражнения',
  `equipment` VARCHAR(45) NULL COMMENT 'снаряды,которые могут понадобиться для данного упражнения, а могут не понадобиться',
  PRIMARY KEY (`idexercises`))
  ENGINE = InnoDB
  COMMENT = 'таблица упражнений.здесь указано какие упражнения делать,их описание и снаряды,которые понадобятся';


-- -----------------------------------------------------
-- Table `test`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`food` ;

CREATE TABLE IF NOT EXISTS `test`.`food` (
  `idfood` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name_of_dish` VARCHAR(45) NOT NULL COMMENT 'название блюда',
  `data_receipt` DATE NOT NULL COMMENT 'дата приема',
  `time_of_receipt` TIME NOT NULL COMMENT 'время приема',
  PRIMARY KEY (`idfood`))
  ENGINE = InnoDB
  COMMENT = 'пища,в этой таблице указано какое блюдо когда принимать';


-- -----------------------------------------------------
-- Table `test`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`appointments` ;

CREATE TABLE IF NOT EXISTS `test`.`appointments` (
  `idappointments` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `exercises_idexercises` INT NOT NULL,
  `food_idfood` INT NOT NULL,
  `client_idclient` INT NOT NULL,
  PRIMARY KEY (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`))
  ENGINE = InnoDB
  COMMENT = 'таблица назначений';


-- -----------------------------------------------------
-- Data for table `test`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`role` (`idrole`, `role_name`) VALUES (1, 'admin');
INSERT INTO `test`.`role` (`idrole`, `role_name`) VALUES (2, 'trainer');
INSERT INTO `test`.`role` (`idrole`, `role_name`) VALUES (3, 'client');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (1, 'Vasy', 'Philimonov', 21, 'M', 'phily@yandex.ru', '1f82cdf9195b31244721c6026587fb78', 1);
INSERT INTO `test`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (2, 'Pety', 'Saplov', 23, 'M', 'goodmail@gmail.com', '58bad6b697dff48f4927941962f23e90', 3);
INSERT INTO `test`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (3, 'Kosty ', 'Pyshyk', 35, 'M', 'pyshhyk@gmail.com', '819b0643d6b89dc9b579fdfc9094f28e', 3);
INSERT INTO `test`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (4, 'Данила ', 'Куликов', 42, 'M', 'kulikov42@gmail.com', '34cc93ece0ba9e3f6f235d4af979b16c', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`client`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (1, 0, 2, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (2, 0, 3, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (3, 30, 4, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (4, 5, 5, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (5, 5, 6, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (6, 0, 7, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (7, 0, 8, 3);
INSERT INTO `test`.`client` (`idclient`, `discount`, `user_iduser`, `user_role_idrole`) VALUES (8, 0, 9, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`trainer`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`, `user_role_idrole`) VALUES (1, 'кмс', 10, 9, 2);
INSERT INTO `test`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`, `user_role_idrole`) VALUES (2, 'кмс', 10, 10, 2);
INSERT INTO `test`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`, `user_role_idrole`) VALUES (3, 'кмс', 10, 11, 2);
INSERT INTO `test`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`, `user_role_idrole`) VALUES (4, 'мс', 12, 12, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`order_client`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (1, 'щадящий', 160, 1, 4);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (2, 'нормальный', 240, 2, 3);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (3, 'для спортсменов', 500, 3, 1);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (4, 'щадящий', 300, 4, 9);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (5, 'нормальный ', 430, 5, 2);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (6, 'нормальный', 400, 6, 5);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (7, 'нормальный', 480, 7, 6);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (8, 'для спортсменов', 500, 8, 10);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (9, 'щадящий', 100, 9, 8);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (10, 'щадящий', 160, 10, 9);
INSERT INTO `test`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (11, 'для спортсменов', 500, 11, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`exercises`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (1, 'мышцы шеи', 'подъем головы лежа на спине; подъем головы леда на животе; тяга с лямкой; вращение в \"борцовском\" мосту', 'лямки, борцовский ковер');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (2, 'трапеции', 'шраги со штангой; шарги с гантелями; тяга штанги к подбородку', 'машина Смита, гантели, гриф');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (3, 'бицепс', 'сгибание рук с гантелями; молотковый подъем гантелей; подъем штанги; сгибание рук сидя на наклонной скамье', 'штанга, гантели, скамья Скотта');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (4, 'трицепс', 'разгибание рук из-за головы; французский жим лежа; жим лежа узким хватом; разгибание рук на верхнем блоке в положении стоя', 'штанга, гантели, скамья');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (5, 'грудные мышцы', 'жим штанги лежа; отжимания на брусьях; разведение рук с гантелями; пуловеры с гантелью', 'штанга, гантели, брусья');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (6, 'широчайшие мышцы спины', 'тяга верхнего блока перед собой; тяга гантели одной рукой; тяга гантели к поясу; отжимания стоя на руках; подтягивания широким хватом', 'гантели, турник, тренажер');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (7, 'пресс', 'подъем корпуса лежа на полу; \"раскладушка\" с подъемом разноименных рук и ног; \"велосипед\"; подъем ног на турнике; обратные скручивания; планка', 'турник');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (8, 'квадрицепсы', 'выпрыгивания с гирей; выпады назад с гантелями; приседания Зерхера; приседания со штангой на груди; жим ногами в тренажере', 'гантели, штанга, тренажер');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (9, 'ягодичные мышцы', 'жим одной ногой в тренажере; гиперэкстензии спины; подъем таза; отведение ноги назад', 'тренажер');
INSERT INTO `test`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (10, 'икры', 'подъем на носки в тренажере стоя; подхем на носки со штангой; подъем на носки в тренажере для жима ногами; подъем на пятки в машине Смита', 'машина Смита');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`food`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (1, 'томатный сок', '2017-09-20', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (2, 'апельсиновый сок', '2017-09-21', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (3, 'салат \"Цезарь\"', '2017-09-21', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (4, 'салат \"Греческий\"', '2017-09-22', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (5, 'Протеин 100 мл', '2017-09-22', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (6, 'Креатин 100 мл', '2017-09-23', '16:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (7, 'Шоколад 100 гр', '2017-09-20', '15:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (8, 'салат \"Швейцарский\"', '2017-09-23', '14:00:00');
INSERT INTO `test`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (9, 'грейфрутовый сок', '2017-09-24', '18:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test`.`appointments`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (1, 1, 2, 9);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (2, 2, 1, 4);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (3, 3, 3, 3);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (4, 4, 10, 5);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (5, 5, 8, 2);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (6, 6, 7, 1);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (7, 7, 6, 6);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (8, 8, 5, 10);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (9, 9, 4, 8);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (10, 10, 3, 7);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (11, 4, 4, 11);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (12, 5, 2, 12);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (13, 6, 4, 13);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (14, 8, 5, 14);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (15, 10, 2, 15);
INSERT INTO `test`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (16, 9, 5, 16);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
