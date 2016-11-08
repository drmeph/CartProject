--liquibase formatted sql

--changeset kevind:1

CREATE TABLE `cartproject`.`Product` (
  `productId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(145) NOT NULL,
  `rating` DECIMAL(2,1) NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`productId`));

--rollback drop table Product;

--changeset kevind:2

ALTER TABLE `cartproject`.`Product`
ADD COLUMN `nbrRatings` INT NULL AFTER `quantity`;

--rollback ALTER TABLE `cartproject`.`Product` DROP COLUMN `nbrRatings`;

--changeset kevind:3

ALTER TABLE `cartproject`.`Product`
CHANGE COLUMN `nbrRatings` `nbrRatings` INT(11) NULL DEFAULT 0 ;

--rollback ALTER TABLE `cartproject`.`Product` CHANGE COLUMN `nbrRatings` `nbrRatings` INT(11) NULL DEFAULT NULL ;

--changeset kevind:4
ALTER TABLE `cartproject`.`Product`
CHANGE COLUMN `rating` `rating` DECIMAL(2,1) NULL DEFAULT 0 ;

--rollback ALTER TABLE `cartproject`.`Product` CHANGE COLUMN `rating` `rating` DECIMAL(2,1) NULL DEFAULT NULL ;

--changeset kevind:5

ALTER TABLE `cartproject`.`Product`
CHANGE COLUMN `rating` `rating` DECIMAL(3,1) NULL DEFAULT 0 ;

--rollback ALTER TABLE `cartproject`.`Product` CHANGE COLUMN `rating` `rating` DECIMAL(2,1) NULL DEFAULT '0.0' ;

--changeset kevind:6

ALTER TABLE `cartproject`.`Product`
DROP COLUMN `nbrRatings`;

--rollback ALTER TABLE `cartproject`.`Product` CHANGE COLUMN `nbrRatings` `nbrRatings` INT(11) NULL DEFAULT 0 ;