
-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymybuddy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `paymybuddy` ;

-- -----------------------------------------------------
-- Table `paymybuddy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mobile_number` VARCHAR(15) NOT NULL,
  `password` VARCHAR(900) NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `balance` DECIMAL(10,2) NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bank_name` VARCHAR(45) NOT NULL,
  `bank_account_number` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  `user_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`connection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`connection` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `connected_user_id` INT NOT NULL,
  `reciever_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `connectionted_to_userid_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `connectionted_to_userid`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKqib1oklng3fekavl8avy3dwoh`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `amount` DECIMAL(10,2) NULL DEFAULT '0.00',
  `charges` DOUBLE NULL DEFAULT NULL,
  `reciever_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `reciever_email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKsg7jp0aj6qipr50856wf6vbw1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKsg7jp0aj6qipr50856wf6vbw1`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`transfer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `reciever_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `charges` DOUBLE NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` DECIMAL(10,2) NULL DEFAULT '0.00',
  `reciever_email` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idfx_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_idfx`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

