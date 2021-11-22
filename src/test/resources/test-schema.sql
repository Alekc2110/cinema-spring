
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema default_schema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinema_bs
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinema_spring
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema_spring
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `test1` ;

-- -----------------------------------------------------
-- Table `cinema_spring`.`line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`line` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `line_number` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`movies` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `director` VARCHAR(255) NULL DEFAULT NULL,
  `photo_url` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `year` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`movie_sessions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`movie_sessions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `show_date` DATE NULL DEFAULT NULL,
  `show_time` TIME NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  `movie_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKatpmn0h31nwhwdgd0ogr8q6kj` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `FKatpmn0h31nwhwdgd0ogr8q6kj`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinema_spring`.`movies` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_3g1j96g94xpk3lpxl2qbl985x` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `order_time` DATETIME(6) NULL DEFAULT NULL,
  `total_price` INT NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK32ql8ubntj5uh44ph9659tiih` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema_spring`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`roles` (
  `id` BIGINT NOT NULL,
  `role_name` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKfwd8notqt2avggaputa06qln5` (`id` ASC) VISIBLE,
  CONSTRAINT `FKfwd8notqt2avggaputa06qln5`
    FOREIGN KEY (`id`)
    REFERENCES `cinema_spring`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`seats` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` INT NULL DEFAULT NULL,
  `line_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqussuq585ib8qqrte5vm4bu8r` (`line_id` ASC) VISIBLE,
  CONSTRAINT `FKqussuq585ib8qqrte5vm4bu8r`
    FOREIGN KEY (`line_id`)
    REFERENCES `cinema_spring`.`line` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema_spring`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_spring`.`tickets` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `movie_session_id` BIGINT NULL DEFAULT NULL,
  `order_id` BIGINT NULL DEFAULT NULL,
  `seat_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKidxabarcn97kf9acrcqokf1r9` (`movie_session_id` ASC) VISIBLE,
  INDEX `FKqgi3sbv1u45s41wawh75ut8ph` (`order_id` ASC) VISIBLE,
  INDEX `FK1f6n3pv4b80wl6gj4ra32ctxk` (`seat_id` ASC) VISIBLE,
  CONSTRAINT `FK1f6n3pv4b80wl6gj4ra32ctxk`
    FOREIGN KEY (`seat_id`)
    REFERENCES `cinema_spring`.`seats` (`id`),
  CONSTRAINT `FKidxabarcn97kf9acrcqokf1r9`
    FOREIGN KEY (`movie_session_id`)
    REFERENCES `cinema_spring`.`movie_sessions` (`id`),
  CONSTRAINT `FKqgi3sbv1u45s41wawh75ut8ph`
    FOREIGN KEY (`order_id`)
    REFERENCES `cinema_spring`.`orders` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
