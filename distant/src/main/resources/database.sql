-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema distant_exam
-- -----------------------------------------------------
-- Система для дистанционного сдачи экзаменов.
--
-- Описание предметной области.
-- Предназначена для студентов дистанционного обучения и преподавателей. Преподователи могут создавать и редактировать разделы с предметами и добавлять в них вопросы с несколькими вариантами ответов. Правильный ответ лишь один.
-- Студент может зарегестрироваться в системе. Выбрать из списка существующего предмета нужный и сдать экзамен, который состоит из пяти вопросов. После сдачи экзамена студент видит свою отметку и предмет становится недоступным для повторной сдачи.
--
--
DROP SCHEMA IF EXISTS `distant_exam` ;

-- -----------------------------------------------------
-- Schema distant_exam
--
-- Система для дистанционного сдачи экзаменов.
--
-- Описание предметной области.
-- Предназначена для студентов дистанционного обучения и преподавателей. Преподователи могут создавать и редактировать разделы с предметами и добавлять в них вопросы с несколькими вариантами ответов. Правильный ответ лишь один.
-- Студент может зарегестрироваться в системе. Выбрать из списка существующего предмета нужный и сдать экзамен, который состоит из пяти вопросов. После сдачи экзамена студент видит свою отметку и предмет становится недоступным для повторной сдачи.
--
--
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `distant_exam` DEFAULT CHARACTER SET utf8 ;
USE `distant_exam` ;

-- -----------------------------------------------------
-- Table `distant_exam`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam`.`users` ;

CREATE TABLE IF NOT EXISTS `distant_exam`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL COMMENT 'Логин пользователя',
  `password` CHAR(64) NULL COMMENT 'Пароль пользователя',
  `name` VARCHAR(255) NULL COMMENT 'Имя пользователя',
  `surname` VARCHAR(255) NULL COMMENT 'Фамилия пользователя',
  `role` VARCHAR(45) NULL COMMENT 'Роль пользователя ',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = 'Хранит имя и фамилию пользователей';


-- -----------------------------------------------------
-- Table `distant_exam`.`subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam`.`subjects` ;

CREATE TABLE IF NOT EXISTS `distant_exam`.`subjects` (
  `id` INT NOT NULL,
  `subject` VARCHAR(255) NULL COMMENT 'Название предмета \nПрименен индекс, чтобы ускорить поиск по предмету, если предметов много',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `subject_UNIQUE` (`subject` ASC))
  ENGINE = InnoDB
  COMMENT = 'Предметы которые создали преподователи и по которым студенты могут сдать экзамен';


-- -----------------------------------------------------
-- Table `distant_exam`.`languages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam`.`languages` ;

CREATE TABLE IF NOT EXISTS `distant_exam`.`languages` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID вопроса PK',
  `language` VARCHAR(45) NULL COMMENT 'Язык вопроса',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = 'Хранит возможные языки вопросов для сдачи экзамена';


-- -----------------------------------------------------
-- Table `distant_exam`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam`.`questions` ;

CREATE TABLE IF NOT EXISTS `distant_exam`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(255) NULL COMMENT 'Вопрос по предмету',
  `answer1` VARCHAR(255) NULL COMMENT 'Первый ответ',
  `answer2` VARCHAR(255) NULL COMMENT 'Второй ответ',
  `answer3` VARCHAR(255) NULL COMMENT 'Третий ответ',
  `answer` INT NOT NULL COMMENT 'Номер правильного ответа',
  `subjects_id` INT NOT NULL COMMENT 'FK предмета',
  `languages_id` INT NOT NULL COMMENT 'FK для language',
  PRIMARY KEY (`id`, `subjects_id`, `languages_id`),
  INDEX `fk_questions_subjects1_idx` (`subjects_id` ASC),
  INDEX `fk_questions_language1_idx` (`languages_id` ASC),
  CONSTRAINT `fk_questions_subjects1`
  FOREIGN KEY (`subjects_id`)
  REFERENCES `distant_exam`.`subjects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questions_language1`
  FOREIGN KEY (`languages_id`)
  REFERENCES `distant_exam`.`languages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Хранит вопросы с ответами для всех возможных предметов';


-- -----------------------------------------------------
-- Table `distant_exam`.`marks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam`.`marks` ;

CREATE TABLE IF NOT EXISTS `distant_exam`.`marks` (
  `mark` INT NULL COMMENT 'Отметка',
  `subjects_id` INT NOT NULL COMMENT 'FK на предмет по которому поставлена отметка',
  `users_id` INT NOT NULL COMMENT 'FK к таблице с пользователями',
  INDEX `fk_marks_subjects1_idx` (`subjects_id` ASC),
  PRIMARY KEY (`subjects_id`, `users_id`),
  INDEX `fk_marks_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_marks_subjects1`
  FOREIGN KEY (`subjects_id`)
  REFERENCES `distant_exam`.`subjects` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marks_users1`
  FOREIGN KEY (`users_id`)
  REFERENCES `distant_exam`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Отметки которые выставляются за сдачу экзамена';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `distant_exam`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam`;
INSERT INTO `distant_exam`.`users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (1, 'admin', 'admin', 'Admin', 'Adminovich', 'admin');
INSERT INTO `distant_exam`.`users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (2, 'teacher', '123qwert', 'Petr', 'Stepanovsky', 'teacher');
INSERT INTO `distant_exam`.`users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (3, 'teacher2', '123qwert', 'Alexandr', 'Luk', 'teacher');
INSERT INTO `distant_exam`.`users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (4, 'student', '123qwert', 'Vasya', 'Zuk', 'student');
INSERT INTO `distant_exam`.`users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (5, 'student2', '123qwert', 'Katya', 'Lis', 'student');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam`.`subjects`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam`;
INSERT INTO `distant_exam`.`subjects` (`id`, `subject`) VALUES (1, 'math');
INSERT INTO `distant_exam`.`subjects` (`id`, `subject`) VALUES (2, 'history');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam`.`languages`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam`;
INSERT INTO `distant_exam`.`languages` (`id`, `language`) VALUES (1, 'en');
INSERT INTO `distant_exam`.`languages` (`id`, `language`) VALUES (2, 'ru');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam`.`questions`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam`;
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (1, 'How much is 2+2', '4', '5', '7', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (2, 'How much is 6+2', '8', '89', '5', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (3, 'How much is 5+2', '7', '67', '4', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (4, 'How much is 1+2', '3', '7', '4', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (5, 'How much is 3+2', '5', '55', '3', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (6, 'How much is 4+2', '6', '3', '2', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (7, 'How much is 9+2', '11', '2', '3', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (8, 'How much is 7+2', '9', '4', '4', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (9, 'How much is 0+2', '2', '3', '5', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (10, 'How much is 6+6', '12', '23', '4', 1, 1, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (11, 'The Battle of Jutland was a naval battle that occured during which war?', 'World War II', 'Meditarian War', 'World War I', 3, 2, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (12, 'In 1513, who became the first European explorer to set eyes on the Pacific Ocean?', 'Columb', 'Vasco N??ez de Balboa', 'Napoleon', 2, 2, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (13, 'According to Greek mythology, who was the god of wine?', 'Dionysos', 'Aurora', 'Hitler', 1, 2, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (14, 'What three countries were part of the Axis powers in World War II?', 'German, Italy', 'German, Italy, and Russia', 'German, Italy, and Japan', 3, 2, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (15, 'Who were there first two astronauts that landed on the moon in 1969?', 'Gagarin and Buzz Aldrin', 'Neil Armstrong and Buzz Aldrin', 'Shekspear and Shwart Nigger', 2, 2, 1);
INSERT INTO `distant_exam`.`questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (16, 'Which U.S. president made the first presidential radio broadcast?', 'Calvin Coolidge', 'Bush', 'Nil Gamilton', 1, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam`.`marks`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam`;
INSERT INTO `distant_exam`.`marks` (`mark`, `subjects_id`, `users_id`) VALUES (5, 1, 4);

COMMIT;

