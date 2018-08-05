-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema distant_exam_test
-- -----------------------------------------------------
-- Система для дистанционного сдачи экзаменов.
--
-- Описание предметной области.
-- Предназначена для студентов дистанционного обучения и преподавателей. Преподователи могут создавать и редактировать разделы с предметами и добавлять в них вопросы с несколькими вариантами ответов. Правильный ответ лишь один.
-- Студент может зарегестрироваться в системе. Выбрать из списка существующего предмета нужный и сдать экзамен, который состоит из пяти вопросов. После сдачи экзамена студент видит свою отметку и предмет становится недоступным для повторной сдачи.
--
--
DROP SCHEMA IF EXISTS `distant_exam_test` ;

-- -----------------------------------------------------
-- Schema distant_exam_test
--
-- Система для дистанционного сдачи экзаменов.
--
-- Описание предметной области.
-- Предназначена для студентов дистанционного обучения и преподавателей. Преподователи могут создавать и редактировать разделы с предметами и добавлять в них вопросы с несколькими вариантами ответов. Правильный ответ лишь один.
-- Студент может зарегестрироваться в системе. Выбрать из списка существующего предмета нужный и сдать экзамен, который состоит из пяти вопросов. После сдачи экзамена студент видит свою отметку и предмет становится недоступным для повторной сдачи.
--
--
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `distant_exam_test` DEFAULT CHARACTER SET utf8 ;
USE `distant_exam_test` ;

-- -----------------------------------------------------
-- Table `distant_exam_test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam_test`.`users` ;

CREATE TABLE IF NOT EXISTS `distant_exam_test`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL COMMENT 'Логин пользователя',
  `password` CHAR(64) NULL COMMENT 'Пароль пользователя',
  `name` VARCHAR(255) NULL COMMENT 'Имя пользователя',
  `surname` VARCHAR(255) NULL COMMENT 'Фамилия пользователя',
  `role` VARCHAR(45) NULL COMMENT 'Роль пользователя ',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = 'Хранит имя и фамилию пользователей';


-- -----------------------------------------------------
-- Table `distant_exam_test`.`subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam_test`.`subjects` ;

CREATE TABLE IF NOT EXISTS `distant_exam_test`.`subjects` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(255) NULL COMMENT 'Название предмета \nПрименен индекс, чтобы ускорить поиск по предмету, если предметов много',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `subject_UNIQUE` (`subject` ASC))
  ENGINE = InnoDB
  COMMENT = 'Предметы которые создали преподователи и по которым студенты могут сдать экзамен';


-- -----------------------------------------------------
-- Table `distant_exam_test`.`languages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam_test`.`languages` ;

CREATE TABLE IF NOT EXISTS `distant_exam_test`.`languages` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID вопроса PK',
  `language` VARCHAR(45) NULL COMMENT 'Язык вопроса',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = 'Хранит возможные языки вопросов для сдачи экзамена';


-- -----------------------------------------------------
-- Table `distant_exam_test`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam_test`.`questions` ;

CREATE TABLE IF NOT EXISTS `distant_exam_test`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(255) NULL COMMENT 'Вопрос по предмету',
  `answer1` VARCHAR(255) NULL COMMENT 'Первый ответ',
  `answer2` VARCHAR(255) NULL COMMENT 'Второй ответ',
  `answer3` VARCHAR(255) NULL COMMENT 'Третий ответ',
  `answer` INT NOT NULL COMMENT 'Номер правильного ответа',
  `subjects_id` INT UNSIGNED NOT NULL COMMENT 'FK предмета',
  `languages_id` INT UNSIGNED NOT NULL COMMENT 'FK для language',
  PRIMARY KEY (`id`, `subjects_id`, `languages_id`),
  INDEX `fk_questions_subjects1_idx` (`subjects_id` ASC),
  INDEX `fk_questions_language1_idx` (`languages_id` ASC),
  CONSTRAINT `fk_questions_subjects1`
  FOREIGN KEY (`subjects_id`)
  REFERENCES `distant_exam_test`.`subjects` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_language1`
  FOREIGN KEY (`languages_id`)
  REFERENCES `distant_exam_test`.`languages` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'Хранит вопросы с ответами для всех возможных предметов';


-- -----------------------------------------------------
-- Table `distant_exam_test`.`marks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distant_exam_test`.`marks` ;

CREATE TABLE IF NOT EXISTS `distant_exam_test`.`marks` (
  `mark` INT NULL COMMENT 'Отметка',
  `subjects_id` INT UNSIGNED NOT NULL COMMENT 'FK на предмет по которому поставлена отметка',
  `users_id` INT UNSIGNED NOT NULL COMMENT 'FK к таблице с пользователями',
  `date` DATETIME NOT NULL,
  INDEX `fk_marks_subjects1_idx` (`subjects_id` ASC),
  PRIMARY KEY (`subjects_id`, `users_id`),
  INDEX `fk_marks_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_marks_subjects1`
  FOREIGN KEY (`subjects_id`)
  REFERENCES `distant_exam_test`.`subjects` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_marks_users1`
  FOREIGN KEY (`users_id`)
  REFERENCES `distant_exam_test`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  COMMENT = 'Отметки которые выставляются за сдачу экзамена';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `distant_exam_test`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam_test`;
INSERT INTO `users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (1,'teacher','202cb962ac59075b964b07152d234b70','Petr','Stepanovsky','teacher');
INSERT INTO `users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (2,'teacher2','202cb962ac59075b964b07152d234b70','Alexandr','Luk','teacher');
INSERT INTO `users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (3,'student','202cb962ac59075b964b07152d234b70','Vasya','Zuk','student');
INSERT INTO `users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (4,'student2','202cb962ac59075b964b07152d234b70','Katya','Lis','student');
INSERT INTO `users` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (5,'reniymaria','202cb962ac59075b964b07152d234b70','ssdf','sdf','student');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam_test`.`subjects`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam_test`;
INSERT INTO `subjects` (`id`, `subject`) VALUES (15,'English');
INSERT INTO `subjects` (`id`, `subject`) VALUES (2,'history');
INSERT INTO `subjects` (`id`, `subject`) VALUES (10,'jumping');
INSERT INTO `subjects` (`id`, `subject`) VALUES (1,'Math');
INSERT INTO `subjects` (`id`, `subject`) VALUES (4,'philosophycal');
INSERT INTO `subjects` (`id`, `subject`) VALUES (3,'programming');
INSERT INTO `subjects` (`id`, `subject`) VALUES (16,'Гарри Поттер');
INSERT INTO `subjects` (`id`, `subject`) VALUES (14,'География');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam_test`.`languages`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam_test`;
INSERT INTO `distant_exam_test`.`languages` (`id`, `language`) VALUES (1, 'en');
INSERT INTO `distant_exam_test`.`languages` (`id`, `language`) VALUES (2, 'ru');

COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam_test`.`questions`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam_test`;
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (1,'How much is1+1','4','5','7',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (2,'How much is 6+2','8','89','5',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (3,'How much is 5+2','7','67','4',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (4,'How much is 1+2','3','7','4',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (5,'How much is 3+2','5','55','3',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (6,'How much is 4+2','6','3','2',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (7,'How much is 9+2','11','2','3',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (8,'How much is 7+2','9','4','4',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (9,'How much is 0+2','2','3','5',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (10,'How much is 6+6','12','23','4',1,1,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (11,'The Battle of Jutland was a naval battle that occured during which war?','World War II','Meditarian War','World War I',3,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (12,'In 1513, who became the first European explorer to set eyes on the Pacific Ocean?','Columb','Vasco Noez de Balboa','Napoleon',2,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (13,'According to Greek mythology, who was the god of wine?','Dionysos','Aurora','Hitler',1,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (14,'What three countries were part of the Axis powers in World War II?','German, Italy','German, Italy, and Russia','German, Italy, and Japan',3,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (15,'Who were there first two astronauts that landed on the moon in 1969?','Gagarin and Buzz Aldrin','Neil Armstrong and Buzz Aldrin','Shekspear and Shwart Nigger',2,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (16,'Which U.S. president made the first presidential radio broadcast?','Calvin Coolidge','Bush','Nil Gamilton',1,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (17,'When the Second word war end?','1945','1946','1950',1,2,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (18,'How many jumps you should do every day?','50','100','400',1,10,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (19,'Что произойдет при попытке компиляции следующего кода.\r\nClass A extends B{}\r\nClass B extends C{}\r\nClass C extends A{}','Работа компилятора зациклится','Компиляция будет успешной','Ошибка компиляции',3,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (20,'Каким образом можно запретить наследование класса (речь идет о top-level классах) ?','Добавить модификатор final','Добавить модификатор private','Не определять конструктор',1,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (21,'Можно ли динамически менять размер массива?','Да, можно','Нет, нельзя','Если помолится то можно',2,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (22,'Выберите правильные высказывания относительно концепции взаимосвязи методов hashCode() и equals(Object o)','Если при сравнении двух объектов метод equals возращает значение true, то значения, возвращаемые методами hashCode() этих объектов, должны совпадать.','Если при сравнении двух объектов метод equals возращает значение true, то значения, возвращаемые методами hashCode() этих объектов, могут не совпадать.','Если при сравнении двух объектов метод equals возращает значение false, то значения, возвращаемые методами hashCode() этих объектов, должны быть различными.',1,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (23,'Выберите варианты комментариев (в некотором исходном файле Java), которые не приведут к ошибке.','This is a valid comment in java ','// This is a valid comment in java','/* /* This is a valid comment in java. */ */',2,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (24,'В какой строке(-ах) кода содержится ошибка:\r\nint i = 1;            //1 \r\ni = -+(10 + 2 + i);   //2 \r\n++i--;                //3 \r\nSystem.out.println(i); ','1','2','3',3,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (25,'Можно ли переопределяя метод изменить его модификатор доступа с \"package-private\" на \"protected\"?','Да','Нет','Да, но не всегда',1,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (26,'Какие модификаторы позволяют обращаться к полю/методу публичного класса верхнего уровня из других классов верхнего уровня, находящихся в том же пакете?','private abstract','private','public',3,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (27,'Как можно уничтожить объект в Java?','присвоить null всем ссылкам на объект','вызвать метод finalize() у объекта','этого нельзя сделать вручную',3,3,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (28,'Нюрнбергский процесс проводился ...','в ноябре-октябре','июне','августе',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (29,'Германский план нападения на СССР («Барбаросса») предусматривал:','Расчленение СССР на отдельные государства','Колонизацию Германией всей территории СССР','Уничтожение единого государства, колонизацию европейской части СССР.',3,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (30,'Высшим государственным органом, сосредоточившим всю полноту власти в годы Великой Отечественной войны стал:','Государственный комитет обороны','Совет труда и обороны','Ставка Верховного Главнокомандования.',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (31,'Определите причину неудач Красной Армии в первые месяцы войны:','Превосходство немецкой армии в численности войск и боевой технике','Уменьшение бюджетных ассигнований на оборону','Не хотели воевать',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (32,'В чем заключалось значение битвы за Москву?','Был сорван план молниеносной войны','Был открыт второй фронт в Европе','Стратегическая инициатива перешла в руки советского командования.',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (33,'В каком году СССР превзошел Германию по выпуску военной продукции:','В конце 1942 г.','В середине 1943 г.','В начале 1944 г.',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (34,'Контрнаступление советских войск под Сталинградом началось:','23 августа 1942 г','19 ноября 1942 г.','2 февраля 1943 г.',2,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (35,'Какая из битв Великой Отечественной войны относится к периоду коренного перелома:','Курская битва','Битва под Москвой','Битва за Берлин.',1,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (36,'Выдающимся советским военачальником в годы Великой Отечественной войны был:','В.К. Блюхер','И.С. Конев','М.Н.Тухачевский.',2,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (37,'В годы войны Красная армия освободила:','Стамбул','Прагу','Париж.',2,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (38,'После второй мировой войны и до сих пор не подписан мирный договор:','С Италией','С Японией','С Англией.',2,2,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (39,'Столица Турция:','Стамбул','Анталья','Анкара',3,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (40,'Столица Андорра:','Ордино','Андорра-ла-Велья','Энкам',2,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (41,'Столица Италия:','Милан','Сан-Марино','Рим',3,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (42,'Столица Португалия:','Порту','Лиссабон','Сантарен',2,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (43,'Столица Швейцария:','Берн','Люцерн','Цюрих',1,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (44,'Столица Венгрия:','Будапешт','Мишкольц','Капошвар',1,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (45,'Столица Украина:','Киев','Харьков','Днепропетровск',1,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (46,'Столица Греция:','Афины','Салоники','Пирей',1,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (47,'Столица Бельгия:','Тузла','Сараево','Баня-Лука',2,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (48,'Столица Исландия:','Хоффедль','Хусавик','Рейкьявик',3,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (49,'Столица Латвия:','Юрмала','Рига','Елгава',2,14,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (50,'They ... orange juice.','drinks','doesn\'t drink','drink',3,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (51,'She ... milk','don\'t drink','drinks','drink',2,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (52,'My father ... TV.','watches','watch','watchs',1,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (53,'We ... near the school.','live','lives','doesn\'t live',1,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (54,'Mary and Sue ... to the cinema.','goes','doesn\'t go','don\'t go',3,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (55,'Mark ... cakes.','don\'t love','doesn\'t love','love',2,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (56,'A pianist ... the piano.','play','plays','don\'t play',2,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (57,'Mark ... English.','speaks','don\'t speak','speak',1,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (58,'Sam... hard.','studies','study','studys',1,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (59,'He ... football.','doesn\'t play','don\'t play','play',1,15,1);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (60,'У Антона Ивановича три тысячи четыреста пять рублей. Сколько денег у Антона Ивановича?','345 р.','3045 р.','3405 р.',3,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (61,'Таня хочет обшить кружевом салфетку прямоугольной формы. Размеры сторон салфетки 20 см и 30 см. Сколько сантиметров кружев ей потребуется?','50 см','100 см','600 см',2,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (62,'Масса слона 6700 кг, а льва 200 кг. Рысь вести в 100 раз меньше, чем слон и лев вместе. С помощью какого выражения можно узнать массу рыси?','6700 + 200 - 100','6700 + 200) х 100','(6700 + 200) : 100',3,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (63,'В новогодние подарки раскладывают конфеты. Всего 199 конфет. В каждый подарок надо положить по 5 конфет. Сколько конфет останется?','194','39','4',3,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (64,'Организаторы соревнований по настольному теннису планируют купить 300 мячей. Мячи продаются упаковками по 25 штук в каждой. Сколько нужно купить упаковок?','12','275','325',1,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (65,'Толя участвовал в соревнованиях по прыжкам в длину с разбега. Какой из следующих результатов мог показать Толя?','20 см','3 м','8 м',2,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (66,'Аня задумала число, увеличила его на 6 и получила 120. Какое число задумала Аня?','114','20','720',1,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (67,'Высота подставки для книжных полок 30 см. Высота одной книжной полки 40 см. Какое наибольшее количество книжных полок можно поставить на подставку, если высота комнаты 3 м?','4 полки','6 полок','7 полок',2,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (68,'В парке есть игра, в которой надо набросить кольцо на крючок. При каждом попадании даётся 2 бесплатных броска. Ира сделала всего 16 бросков, а заплатила только за 4. Сколько раз она сумела набросить кольцо на крючок?','12','8','6',3,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (69,'Каким числом является результат действия 12064 : 4?','двузначным','четырёхзначным','пятизначным',2,1,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (70,'В какой части фильмов о Гарри Поттере, Гарри \"возродил\" Лорда Волан - де - Морта?','Тайная комната','Узник Азкабана','Кубок Огня',3,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (71,'Кто является Принцом Полукровкой?','Северус Снейп','Альбус Дамболдор','Рональд Уизли',1,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (72,'Какого животного не было в фильмах о Гарри Поттере7','сова','собака','белка',3,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (73,'Кто был противником Гарри Поттера?','Семья Уизли','Семья Дурсли','Пожиратели Смерти',3,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (74,'Заклинание парализования','остолбеней','имоделлюкс','авада кедавра',1,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (75,'Кто убил Сириуса Блека?','Грейнджер','Лейстрендж','Волан де морт',2,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (76,'Как называется деревня недалеко от Хогвартса?','Хогсмид','Берти бердс','Волчая хижина',1,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (77,'Сколько частей фильмов о Гарри Поттере?','5','7','8',3,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (78,'Кто не был учителем в школе Хогвартс?','Маг Конакал','Памона СТебль','Нарцисса Малфой',3,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (79,'В кого трансформируется МагГонакал?','кошка','дракон','змея',1,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (80,'Что было такого на 3 этаже в школе?','философский камень','запретная секция библиотеки','запретная секция для \"взрослых\"',2,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (81,'Первая любовь Гарри?','Чжоу Чанг','Джинни Уизли','Палумна Лавгуд',1,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (82,'Что такое крестраж?','магический предмет дамболдора','материальный объект, в который можно заключить часть души','иное название волшебная палочка',2,16,2);
INSERT INTO `questions` (`id`, `question`, `answer1`, `answer2`, `answer3`, `answer`, `subjects_id`, `languages_id`) VALUES (83,'Кто должен был убить Дамболдора?','Снегг','Малфой','Лейстрендж',2,16,2);
COMMIT;


-- -----------------------------------------------------
-- Data for table `distant_exam_test`.`marks`
-- -----------------------------------------------------
START TRANSACTION;
USE `distant_exam_test`;
INSERT INTO `marks` (`mark`, `subjects_id`, `users_id`, date) VALUES (4,2,5, '2018-05-08 11:57:00');
INSERT INTO `marks` (`mark`, `subjects_id`, `users_id`, date) VALUES (5,16,5, '2018-05-08 11:57:00');

COMMIT;

