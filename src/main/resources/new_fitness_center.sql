-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema new_fitness_center
-- -----------------------------------------------------
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
-- 
DROP SCHEMA IF EXISTS `new_fitness_center` ;

-- -----------------------------------------------------
-- Schema new_fitness_center
--
-- Фитнесс-центр
-- Описание предметной области
-- Вы работаете в фитнесс-центре. Вашей задачей является отслеживание ее финансовой деятельности. Деятельность компании организована следующим образом: к вам обращаются различные лица с целью записаться на занятия по фитнессу. Вашими клиентами являются лица, о которых вы собираете определенную информацию (фамилия, имя, отчество, возраст, e-mail, номер телефона).  Они оставляют заявку, в которой  указаны критерии: тип тренировки (для соревнований, нормальный, щадящий режим), тренер (которого клиент выбрал исходя из критериев), предпочитаемое время для занятий. Каждый тренер помимо имени, фамилии, возраста имеет такие критерии как уровень образования/награды и стоимость занятия. При заключении договора вы фиксируете сумма денег, которую должен заплатить клиент, иходя изколичества тренировок, типа тренировок и тренера.
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `new_fitness_center` ;
USE `new_fitness_center` ;

-- -----------------------------------------------------
-- Table `new_fitness_center`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`role` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`role` (
  `idrole` INT NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `new_fitness_center`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`user` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`user` (
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
-- Table `new_fitness_center`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`client` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`client` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `discount` SMALLINT NULL COMMENT 'скидка, если есть',
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`idclient`))
  ENGINE = InnoDB
  COMMENT = 'клиент';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`trainer` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`trainer` (
  `idtrainer` INT NOT NULL AUTO_INCREMENT,
  `education_or_level` VARCHAR(45) NOT NULL,
  `cost_per_lesson` DECIMAL NOT NULL DEFAULT 0,
  `user_iduser` INT NOT NULL,
  PRIMARY KEY (`idtrainer`))
  ENGINE = InnoDB
  COMMENT = 'тренер';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`order_client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`order_client` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`order_client` (
  `idorder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_training` VARCHAR(45) NOT NULL COMMENT 'тип тренировки',
  `cost_of_lessons` DECIMAL NULL COMMENT 'итоговая сумма,сколько клиент должен заплатить фитнесс-центру.высчитывается автоматически',
  `client_idclient` INT NOT NULL,
  `trainer_idtrainer` INT NOT NULL,
  PRIMARY KEY (`idorder`))
  ENGINE = InnoDB
  COMMENT = 'таблица заказа клиента';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`exercises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`exercises` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`exercises` (
  `idexercises` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `muscle_group` VARCHAR(45) NOT NULL COMMENT 'название упражнения',
  `names_of_exercises` TEXT NULL COMMENT 'описание упражнения',
  `equipment` VARCHAR(45) NULL COMMENT 'снаряды,которые могут понадобиться для данного упражнения, а могут не понадобиться',
  PRIMARY KEY (`idexercises`))
  ENGINE = InnoDB
  COMMENT = 'таблица упражнений.здесь указано какие упражнения делать,их описание и снаряды,которые понадобятся';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`food`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`food` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`food` (
  `idfood` INT NOT NULL AUTO_INCREMENT,
  `name_of_dish` VARCHAR(45) NOT NULL COMMENT 'название блюда',
  `data_receipt` DATE NOT NULL COMMENT 'дата приема',
  `time_of_receipt` TIME NOT NULL COMMENT 'время приема',
  PRIMARY KEY (`idfood`))
  ENGINE = InnoDB
  COMMENT = 'пища,в этой таблице указано какое блюдо когда принимать';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`appointments` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`appointments` (
  `idappointments` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `exercises_idexercises` INT NOT NULL,
  `food_idfood` INT NOT NULL,
  `client_idclient` INT NOT NULL,
  PRIMARY KEY (`idappointments`))
  ENGINE = InnoDB
  COMMENT = 'таблица назначений';


-- -----------------------------------------------------
-- Table `new_fitness_center`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `new_fitness_center`.`reviews` ;

CREATE TABLE IF NOT EXISTS `new_fitness_center`.`reviews` (
  `idreviews` INT NOT NULL COMMENT 'id отзыва',
  `client_idclient` INT NOT NULL COMMENT 'id пользователя, который оставил комментарий',
  `text_review` VARCHAR(45) NULL COMMENT 'текст отзыва\n',
  `mark` TINYINT(4) NULL COMMENT 'оценка фитнесс центру',
  PRIMARY KEY (`idreviews`, `client_idclient`))
  ENGINE = InnoDB
  COMMENT = 'таблица отзывов клиентов';


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`role` (`idrole`, `role_name`) VALUES (1, 'admin');
INSERT INTO `new_fitness_center`.`role` (`idrole`, `role_name`) VALUES (2, 'trainer');
INSERT INTO `new_fitness_center`.`role` (`idrole`, `role_name`) VALUES (3, 'client');

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (1, 'admin', 'admin', 27, 'M', 'adm@mail.ru', '7c6a180b36896a0a8c02787eeafb0e4c', 1);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (2, 'Егор', 'Комиссаров', 23, 'M', 'komisarov@mail.ru', '6cb75f652a9b52798eb6cf2201057c73', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (3, 'Святослав ', 'Фомин', 35, 'M', 'fomin23@yandex.ru', '819b0643d6b89dc9b579fdfc9094f28e', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (4, 'Данила ', 'Куликов', 42, 'M', 'kulikov42@gmail.com', '34cc93ece0ba9e3f6f235d4af979b16c', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (5, 'Владислав', 'Исаев', 18, 'M', 'isaev.gmail.com', 'db0edd04aaac4506f7edab03ac855d56', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (6, 'Дмитрий ', 'Ларионов', 20, 'M', 'larionov@mail.ru', '218dd27aebeccecae69ad8408d9a36bf', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (7, 'Борис', 'Гурьев', 37, 'M', 'gyrev37@yandex.ru', '00cdb7bb942cf6b290ceb97d6aca64a3', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (8, 'Евгений', 'Соболев', 33, 'M', 'sobolev@gamil.com', 'b25ef06be3b6948c0bc431da46c2c738', 3);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (9, 'Федор', 'Макаров', 27, 'M', 'makarov@gmail.com', '5d69dd95ac183c9643780ed7027d128a', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (10, 'Алексей ', 'Артемьев', 24, 'M', 'artemiev@yandex.ru', '87e897e3b54a405da144968b2ca19b45', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (11, 'Эдуард', 'Туров', 34, 'M', 'turov@mail.ru', '1e5c2776cf544e213c3d279c40719643', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (12, 'Михаил', 'Александров', 45, 'М', 'aleksadrov@mail.ru', 'c24a542f884e144451f9063b79e7994e', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (13, 'Николай', 'Силин', 56, 'М', 'silsin@gmail.ru', 'ee684912c7e588d03ccb40f17ed080c9', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (14, 'Сергей', 'Тетерин', 43, 'М', 'teterin@yandex.ru', '8ee736784ce419bd16554ed5677ff35b', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (15, 'Тимур', 'Кондратьев', 45, 'М', 'kondratev@mail.ru', '9141fea0574f83e190ab7479d516630d', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (16, 'Лев', 'Савин', 56, 'М', 'savin@mail.ru', '2b40aaa979727c43411c305540bbed50', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (17, 'Максим', 'Евсеев', 34, 'М', 'evseev34@gamil.ru', 'a63f9709abc75bf8bd8f6e1ba9992573', 2);
INSERT INTO `new_fitness_center`.`user` (`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`) VALUES (18, 'Станислав', 'Федотов', 32, 'М', 'fedotov@yandex.ru', '80b8bdceb474b5127b6aca386bb8ce14', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`client`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (1, 0, 2);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (2, 0, 3);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (3, 30, 4);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (4, 5, 5);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (5, 5, 6);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (6, 0, 7);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (7, 0, 8);
INSERT INTO `new_fitness_center`.`client` (`idclient`, `discount`, `user_iduser`) VALUES (8, 0, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`trainer`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (1, 'кмс', 10, 9);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (2, 'кмс', 10, 10);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (3, 'кмс', 10, 11);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (4, 'мс', 12, 12);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (5, 'кмс', 10, 13);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (6, 'мс', 12, 14);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (7, 'мсмк', 20, 15);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (8, 'кмс', 10, 16);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (9, 'мс', 12, 17);
INSERT INTO `new_fitness_center`.`trainer` (`idtrainer`, `education_or_level`, `cost_per_lesson`, `user_iduser`) VALUES (10, 'мсмк(чемпион мира)', 25, 18);

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`order_client`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (1, 'щадящий', 160, 1, 4);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (2, 'нормальный', 240, 2, 3);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (3, 'для спортсменов', 500, 3, 1);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (4, 'щадящий', 300, 4, 9);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (5, 'нормальный ', 430, 5, 2);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (6, 'нормальный', 400, 6, 5);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (7, 'нормальный', 480, 7, 6);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (8, 'для спортсменов', 500, 8, 10);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (9, 'щадящий', 100, 9, 8);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (10, 'щадящий', 160, 10, 9);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (11, 'для спортсменов', 500, 11, 10);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (12, 'щадящий', 160, 12, 6);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (13, 'нормальный', 300, 13, 3);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (14, 'нормальный', 300, 14, 7);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (15, 'для спортсменов', 500, 15, 9);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (16, 'щадящий', 160, 16, 5);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (17, 'щадящий', 160, 17, 3);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (18, 'нормальный', 300, 18, 2);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (19, 'щадящий', 160, 19, 3);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (20, 'нормальный', 300, 20, 4);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (21, 'нормальный', 300, 21, 5);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (22, 'щадящий', 160, 22, 7);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (23, 'щадящий', 160, 23, 8);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (24, 'для спортсменов', 500, 24, 9);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (25, 'для спортсменов', 500, 25, 6);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (26, 'нормальный', 300, 26, 5);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (27, 'щадящий', 160, 27, 4);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (28, 'для спортсменов', 500, 28, 3);
INSERT INTO `new_fitness_center`.`order_client` (`idorder`, `type_of_training`, `cost_of_lessons`, `client_idclient`, `trainer_idtrainer`) VALUES (29, 'щадящий', 160, 29, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`exercises`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (1, 'мышцы шеи', 'подъем головы лежа на спине; подъем головы леда на животе; тяга с лямкой; вращение в \"борцовском\" мосту', 'лямки, борцовский ковер');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (2, 'трапеции', 'шраги со штангой; шарги с гантелями; тяга штанги к подбородку', 'машина Смита, гантели, гриф');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (3, 'бицепс', 'сгибание рук с гантелями; молотковый подъем гантелей; подъем штанги; сгибание рук сидя на наклонной скамье', 'штанга, гантели, скамья Скотта');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (4, 'трицепс', 'разгибание рук из-за головы; французский жим лежа; жим лежа узким хватом; разгибание рук на верхнем блоке в положении стоя', 'штанга, гантели, скамья');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (5, 'грудные мышцы', 'жим штанги лежа; отжимания на брусьях; разведение рук с гантелями; пуловеры с гантелью', 'штанга, гантели, брусья');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (6, 'широчайшие мышцы спины', 'тяга верхнего блока перед собой; тяга гантели одной рукой; тяга гантели к поясу; отжимания стоя на руках; подтягивания широким хватом', 'гантели, турник, тренажер');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (7, 'пресс', 'подъем корпуса лежа на полу; \"раскладушка\" с подъемом разноименных рук и ног; \"велосипед\"; подъем ног на турнике; обратные скручивания; планка', 'турник');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (8, 'квадрицепсы', 'выпрыгивания с гирей; выпады назад с гантелями; приседания Зерхера; приседания со штангой на груди; жим ногами в тренажере', 'гантели, штанга, тренажер');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (9, 'ягодичные мышцы', 'жим одной ногой в тренажере; гиперэкстензии спины; подъем таза; отведение ноги назад', 'тренажер');
INSERT INTO `new_fitness_center`.`exercises` (`idexercises`, `muscle_group`, `names_of_exercises`, `equipment`) VALUES (10, 'икры', 'подъем на носки в тренажере стоя; подхем на носки со штангой; подъем на носки в тренажере для жима ногами; подъем на пятки в машине Смита', 'машина Смита');

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`food`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (1, 'томатный сок', '2017-09-20', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (2, 'апельсиновый сок', '2017-09-21', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (3, 'салат \"Цезарь\"', '2017-09-21', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (4, 'салат \"Греческий\"', '2017-09-22', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (5, 'Протеин 100 мл', '2017-09-22', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (6, 'Креатин 100 мл', '2017-09-23', '16:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (7, 'Шоколад 100 гр', '2017-09-20', '15:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (8, 'салат \"Швейцарский\"', '2017-09-23', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (9, 'грейфрутовый сок', '2017-09-24', '18:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (10, 'Желатин', '2017-09-24', '15:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (11, 'Гречка 100 гр', '2017-09-25', '18:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (12, 'Курица отварная 50 гр', '2017-09-25', '13:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (13, 'Тунец 100 гр', '2017-09-26', '10:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (14, 'Лосось 100 гр', '2017-09-26', '14:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (15, 'Грецкий орех 40 гр', '2017-09-27', '16:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (16, 'Мюсли', '2017-09-27', '16:00:00');
INSERT INTO `new_fitness_center`.`food` (`idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (17, 'Творого', '2017-09-28', '18:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `new_fitness_center`.`appointments`
-- -----------------------------------------------------
START TRANSACTION;
USE `new_fitness_center`;
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (1, 1, 2, 9);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (2, 2, 1, 4);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (3, 3, 3, 3);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (4, 4, 10, 5);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (5, 5, 8, 2);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (6, 6, 7, 1);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (7, 7, 6, 6);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (8, 8, 5, 10);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (9, 9, 4, 8);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (10, 10, 3, 7);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (11, 4, 4, 11);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (12, 5, 2, 12);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (13, 6, 4, 13);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (14, 8, 5, 14);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (15, 10, 2, 15);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (16, 9, 5, 16);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (17, 8, 5, 17);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (18, 7, 2, 18);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (19, 6, 2, 19);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (20, 1, 1, 20);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (21, 2, 1, 21);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (22, 3, 1, 22);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (23, 4, 4, 23);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (24, 5, 5, 24);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (25, 2, 6, 25);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (26, 1, 7, 26);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (27, 7, 3, 27);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (28, 8, 10, 28);
INSERT INTO `new_fitness_center`.`appointments` (`idappointments`, `exercises_idexercises`, `food_idfood`, `client_idclient`) VALUES (29, 9, 9, 29);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
