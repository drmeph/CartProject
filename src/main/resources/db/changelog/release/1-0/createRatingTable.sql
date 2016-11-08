--liquibase formatted sql

--changeset kevind:1

CREATE TABLE `cartproject`.`Rating` (
  `ratingId` INT NOT NULL,
  `rate` INT NOT NULL,
  `productId` INT NOT NULL,
  PRIMARY KEY (`ratingId`),
  INDEX `productId_for_rating_idx` (`productId` ASC),
  CONSTRAINT `productId_for_rating`
    FOREIGN KEY (`productId`)
    REFERENCES `cartproject`.`Product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--rollback drop table Rating;

--changeset kevind:2

ALTER TABLE `cartproject`.`Rating`
CHANGE COLUMN `ratingId` `ratingId` INT(11) NOT NULL AUTO_INCREMENT ;

--rollback ALTER TABLE `cartproject`.`Rating` CHANGE COLUMN `ratingId` `ratingId` INT(11) NOT NULL ;

