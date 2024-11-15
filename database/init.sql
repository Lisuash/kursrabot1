SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

create database if not exists javafxTest;
use javafxTest;

--
-- Table structure for table `new_tour`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`new_tour` (
                                                        `id` int NOT NULL AUTO_INCREMENT,
                                                        `data` date DEFAULT NULL,
                                                        `desk` varchar(250) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `rols`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`rols` (
                                                    `idrols` int NOT NULL AUTO_INCREMENT,
                                                    `name` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`idrols`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `section`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`section` (
                                                       `idsection` int NOT NULL AUTO_INCREMENT,
                                                       `address` varchar(45) DEFAULT NULL,
    `price` int DEFAULT NULL,
    `name` varchar(45) DEFAULT NULL,
    `sport_idsport` int NOT NULL,
    `tournament_idtournament` int NOT NULL,
    PRIMARY KEY (`idsection`),
    KEY `fk_section_sport_idx` (`sport_idsport`),
    KEY `fk_section_tournament1_idx` (`tournament_idtournament`),
    CONSTRAINT `fk_section_sport` FOREIGN KEY (`sport_idsport`) REFERENCES `sport` (`idsport`),
    CONSTRAINT `fk_section_tournament1` FOREIGN KEY (`tournament_idtournament`) REFERENCES `tournament` (`idtournament`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `sectionuser`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`sectionuser` (
                                                           `idsectionuser` int NOT NULL AUTO_INCREMENT,
                                                           `name` varchar(45) DEFAULT NULL,
    `age` int DEFAULT NULL,
    `section_idsection` int NOT NULL,
    PRIMARY KEY (`idsectionuser`),
    KEY `fk_sectionuser_section1_idx` (`section_idsection`),
    CONSTRAINT `fk_sectionuser_section1` FOREIGN KEY (`section_idsection`) REFERENCES `section` (`idsection`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `sectionuser_has_users`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`sectionuser_has_users` (
                                                                     `sectionuser_idsectionuser` int NOT NULL,
                                                                     `users_idusers` int NOT NULL,
                                                                     PRIMARY KEY (`sectionuser_idsectionuser`,`users_idusers`),
    KEY `fk_sectionuser_has_users_users1_idx` (`users_idusers`),
    KEY `fk_sectionuser_has_users_sectionuser1_idx` (`sectionuser_idsectionuser`),
    CONSTRAINT `fk_sectionuser_has_users_sectionuser1` FOREIGN KEY (`sectionuser_idsectionuser`) REFERENCES `sectionuser` (`idsectionuser`),
    CONSTRAINT `fk_sectionuser_has_users_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `sport`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`sport` (
                                                     `idsport` int NOT NULL AUTO_INCREMENT,
                                                     `description` varchar(45) DEFAULT NULL,
    `sportsmen_count` int DEFAULT NULL,
    PRIMARY KEY (`idsport`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `tournament`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`tournament` (
                                                          `idtournament` int NOT NULL AUTO_INCREMENT,
                                                          `points_scored` int DEFAULT NULL,
                                                          `stage_date` date DEFAULT NULL,
                                                          `desk` varchar(250) DEFAULT NULL,
    PRIMARY KEY (`idtournament`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `tournament_has_users`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`tournament_has_users` (
                                                                    `tournament_idtournament` int NOT NULL,
                                                                    `users_idusers` int NOT NULL,
                                                                    `ochki` int DEFAULT NULL,
                                                                    PRIMARY KEY (`tournament_idtournament`,`users_idusers`),
    KEY `fk_tournament_has_users_users1_idx` (`users_idusers`),
    KEY `fk_tournament_has_users_tournament1_idx` (`tournament_idtournament`),
    CONSTRAINT `fk_tournament_has_users_tournament1` FOREIGN KEY (`tournament_idtournament`) REFERENCES `tournament` (`idtournament`),
    CONSTRAINT `fk_tournament_has_users_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `user_newtour`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`user_newtour` (
                                                            `idnewtour` int NOT NULL,
                                                            `iduser` int NOT NULL,
                                                            PRIMARY KEY (`idnewtour`,`iduser`),
    KEY `fk_userid_idx` (`iduser`),
    KEY `fk_tour_idx` (`idnewtour`),
    CONSTRAINT `fk_tour` FOREIGN KEY (`idnewtour`) REFERENCES `new_tour` (`id`),
    CONSTRAINT `fk_userid` FOREIGN KEY (`iduser`) REFERENCES `users` (`idusers`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `users`
--


CREATE TABLE IF NOT EXISTS `user090_db1`.`users` (
                                                     `idusers` int NOT NULL AUTO_INCREMENT,
                                                     `users_code` varchar(45) DEFAULT NULL,
    `users_fullname` varchar(45) DEFAULT NULL,
    `rols_idrols` int NOT NULL,
    PRIMARY KEY (`idusers`,`rols_idrols`),
    KEY `fk_users_rols1_idx` (`rols_idrols`),
    CONSTRAINT `fk_users_rols1` FOREIGN KEY (`rols_idrols`) REFERENCES `rols` (`idrols`)
    ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;


--
-- Заполнение таблиц данными
--
INSERT INTO `new_tour` VALUES (2,'2023-11-21','sdfv'),(3,'2023-11-22','нелето');
INSERT INTO `rols` VALUES (1,'пользователь'),(2,'администратор');
INSERT INTO `section` VALUES (1,'пр.Гагарина 19',200,'Победа',1,1),(2,'ул. Победная 47',350,'Спортсмен',2,2),(3,'пл. Знаменская 80',250,'Звезда',3,3);
INSERT INTO `sectionuser` VALUES (1,'Иванов М.Е.',20,1),(2,'Петров С.И.',12,2),(3,'Кириллов А.Н.',16,3);
INSERT INTO `sectionuser_has_users` VALUES (1,3),(2,5),(3,7);
INSERT INTO `sport` VALUES (1,'бокс',2),(2,'бейсбол',2),(3,'футбол',2);
INSERT INTO `tournament` VALUES (1,20,'2023-08-09','любительский турнир по боксу'),(2,20,'2023-09-08','любительский турнир по бейсболу'),(3,20,'2023-12-12','любительский турнир по футболу');
INSERT INTO `tournament_has_users` VALUES (1,3,15),(1,4,5),(2,5,7),(2,6,13),(3,7,8),(3,8,12);
INSERT INTO `user_newtour` VALUES (3,3),(3,4),(2,5),(2,6),(3,6),(3,8);
INSERT INTO `users` VALUES (1,'1111','test',1),(2,'1112','test',2),(3,'1113','Иванов М.Е.',1),(4,'1114','Сидоров И.П.',1),(5,'1115','Петров С.И.',1),(6,'1116','Морышев В.А.',1),(7,'1117','Кириллов А.Н.',1),(8,'1118','Прокофьев М.К.',1),(9,'1119','Васильчук А.О.',2);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;