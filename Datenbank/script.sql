SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `roomanizer` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `roomanizer` ;

-- -----------------------------------------------------
-- Table `roomanizer`.`roomCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomCategories` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomCategories` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `bedCount` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`rooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`rooms` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `roomNumber` VARCHAR(255) NOT NULL ,
  `idRoomCategories` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `roomnr_UNIQUE` (`roomNumber` ASC) ,
  INDEX `fk_rooms_categories1` (`idRoomCategories` ASC) ,
  CONSTRAINT `fk_rooms_categories1`
    FOREIGN KEY (`idRoomCategories` )
    REFERENCES `roomanizer`.`roomCategories` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomOptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomOptions` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomOptions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomsRoomOptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomsRoomOptions` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomsRoomOptions` (
  `idRooms` INT NOT NULL ,
  `idOptions` INT NOT NULL ,
  PRIMARY KEY (`idRooms`, `idOptions`) ,
  INDEX `fk_room_has_roomoptions_roomoptions1` (`idOptions` ASC) ,
  INDEX `fk_room_has_roomoptions_room` (`idRooms` ASC) ,
  CONSTRAINT `fk_room_has_roomoptions_room`
    FOREIGN KEY (`idRooms` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_room_has_roomoptions_roomoptions1`
    FOREIGN KEY (`idOptions` )
    REFERENCES `roomanizer`.`roomOptions` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomStatus` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomStatus` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomsRoomStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomsRoomStatus` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomsRoomStatus` (
  `idRooms` INT NOT NULL ,
  `idRoomStatus` INT NOT NULL ,
  `startDate` DATE NOT NULL ,
  `endDate` DATE NOT NULL ,
  PRIMARY KEY (`idRooms`, `idRoomStatus`) ,
  INDEX `fk_rooms_has_roomStatus_roomStatus1` (`idRoomStatus` ASC) ,
  INDEX `fk_rooms_has_roomStatus_rooms1` (`idRooms` ASC) ,
  INDEX `i_start` (`startDate` ASC) ,
  INDEX `i_end` (`endDate` ASC) ,
  CONSTRAINT `fk_rooms_has_roomStatus_rooms1`
    FOREIGN KEY (`idRooms` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rooms_has_roomStatus_roomStatus1`
    FOREIGN KEY (`idRoomStatus` )
    REFERENCES `roomanizer`.`roomStatus` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`seasons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`seasons` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`seasons` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `startDate` DATE NOT NULL ,
  `endDate` DATE NOT NULL COMMENT 'problem with the year' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `i_start` (`startDate` ASC) ,
  INDEX `i_end` (`endDate` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomCategoryPrices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roomCategoryPrices` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomCategoryPrices` (
  `idRoomCategories` INT NOT NULL ,
  `idSeasons` INT NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `priceMin` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`idRoomCategories`, `idSeasons`) ,
  INDEX `fk_roomCategories_has_season_season1` (`idSeasons` ASC) ,
  INDEX `fk_roomCategories_has_season_roomCategories1` (`idRoomCategories` ASC) ,
  CONSTRAINT `fk_roomCategories_has_season_roomCategories1`
    FOREIGN KEY (`idRoomCategories` )
    REFERENCES `roomanizer`.`roomCategories` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_roomCategories_has_season_season1`
    FOREIGN KEY (`idSeasons` )
    REFERENCES `roomanizer`.`seasons` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`countries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`countries` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`countries` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `nameShort` CHAR(2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  UNIQUE INDEX `nameShort_UNIQUE` (`nameShort` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`addresses` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`addresses` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `street` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `zip` VARCHAR(255) NULL ,
  `email` VARCHAR(255) NULL ,
  `phone` VARCHAR(255) NULL ,
  `fax` VARCHAR(255) NULL ,
  `idCountries` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_addresses_countries1` (`idCountries` ASC) ,
  CONSTRAINT `fk_addresses_countries1`
    FOREIGN KEY (`idCountries` )
    REFERENCES `roomanizer`.`countries` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`parties`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`parties` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`parties` (
  `idParties` INT NOT NULL AUTO_INCREMENT ,
  `idAddresses` INT NOT NULL ,
  PRIMARY KEY (`idParties`) ,
  INDEX `fk_persons_addresses1` (`idAddresses` ASC) ,
  CONSTRAINT `fk_persons_addresses1`
    FOREIGN KEY (`idAddresses` )
    REFERENCES `roomanizer`.`addresses` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`users` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(255) NOT NULL ,
  `password` CHAR(32) NOT NULL ,
  `active` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `employeeCode_UNIQUE` (`username` ASC) ,
  INDEX `i_password` (`password` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`roles` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`userRoles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`userRoles` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`userRoles` (
  `idRoles` INT NOT NULL ,
  `idUsers` INT NOT NULL ,
  PRIMARY KEY (`idRoles`, `idUsers`) ,
  INDEX `fk_role_has_employee_employee1` (`idUsers` ASC) ,
  INDEX `fk_role_has_employee_role1` (`idRoles` ASC) ,
  CONSTRAINT `fk_role_has_employee_role1`
    FOREIGN KEY (`idRoles` )
    REFERENCES `roomanizer`.`roles` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role_has_employee_employee1`
    FOREIGN KEY (`idUsers` )
    REFERENCES `roomanizer`.`users` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`permissions` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`permissions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`rolePermissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`rolePermissions` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`rolePermissions` (
  `idPermissions` INT NOT NULL ,
  `idRoles` INT NOT NULL ,
  PRIMARY KEY (`idPermissions`, `idRoles`) ,
  INDEX `fk_permissions_has_roles_roles1` (`idRoles` ASC) ,
  INDEX `fk_permissions_has_roles_permissions1` (`idPermissions` ASC) ,
  CONSTRAINT `fk_permissions_has_roles_permissions1`
    FOREIGN KEY (`idPermissions` )
    REFERENCES `roomanizer`.`permissions` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_permissions_has_roles_roles1`
    FOREIGN KEY (`idRoles` )
    REFERENCES `roomanizer`.`roles` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`customers` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`customers` (
  `idParties` INT NOT NULL ,
  `idAddresses` INT NOT NULL ,
  INDEX `fk_customers_addresses1` (`idAddresses` ASC) ,
  INDEX `fk_customers_persons1` (`idParties` ASC) ,
  PRIMARY KEY (`idParties`) ,
  CONSTRAINT `fk_customers_addresses1`
    FOREIGN KEY (`idAddresses` )
    REFERENCES `roomanizer`.`addresses` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customers_persons1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`parties` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`guests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`guests` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`guests` (
  `idParties` INT NOT NULL ,
  `fname` VARCHAR(255) NOT NULL ,
  `lname` VARCHAR(255) NOT NULL ,
  `birthday` DATE NULL ,
  INDEX `fk_guest_persons1` (`idParties` ASC) ,
  UNIQUE INDEX `idPersons_UNIQUE` (`idParties` ASC) ,
  PRIMARY KEY (`idParties`) ,
  CONSTRAINT `fk_guest_persons1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`parties` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`serviceTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`serviceTypes` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`serviceTypes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `taxRate` DECIMAL(5,2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`services` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`services` (
  `idServices` INT NOT NULL AUTO_INCREMENT ,
  `idServiceTypes` INT NOT NULL ,
  PRIMARY KEY (`idServices`) ,
  INDEX `fk_services_serviceTypes1` (`idServiceTypes` ASC) ,
  CONSTRAINT `fk_services_serviceTypes1`
    FOREIGN KEY (`idServiceTypes` )
    REFERENCES `roomanizer`.`serviceTypes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`habitations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`habitations` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`habitations` (
  `idServices` INT NOT NULL ,
  `startDate` DATE NOT NULL ,
  `endDate` DATE NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `deposit` VARCHAR(255) NOT NULL ,
  `idRooms` INT NOT NULL ,
  `idUsers` INT NOT NULL ,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`idServices`) ,
  INDEX `fk_habitation_rooms1` (`idRooms` ASC) ,
  INDEX `fk_habitations_services1` (`idServices` ASC) ,
  INDEX `i_star` (`startDate` ASC) ,
  INDEX `i_end` (`endDate` ASC) ,
  INDEX `fk_habitations_users1` (`idUsers` ASC) ,
  CONSTRAINT `fk_habitation_rooms1`
    FOREIGN KEY (`idRooms` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitations_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`idServices` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitations_users1`
    FOREIGN KEY (`idUsers` )
    REFERENCES `roomanizer`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`reservations` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservations` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `reservationNumber` VARCHAR(255) NOT NULL ,
  `startDate` DATE NOT NULL ,
  `endDate` DATE NOT NULL ,
  `comment` VARCHAR(255) NULL ,
  `idParties` INT NOT NULL ,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `idUsers` INT NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_reservations_persons1` (`idParties` ASC) ,
  UNIQUE INDEX `reserationNumber_UNIQUE` (`reservationNumber` ASC) ,
  INDEX `i_start` (`startDate` ASC) ,
  INDEX `i_end` (`endDate` ASC) ,
  INDEX `fk_reservations_users1` (`idUsers` ASC) ,
  CONSTRAINT `fk_reservations_persons1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`parties` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservations_users1`
    FOREIGN KEY (`idUsers` )
    REFERENCES `roomanizer`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservationItems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`reservationItems` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservationItems` (
  `idReservations` INT NOT NULL ,
  `idRoomCategories` INT NOT NULL ,
  `amount` INT NOT NULL ,
  PRIMARY KEY (`idReservations`, `idRoomCategories`) ,
  INDEX `fk_reservations_has_roomCategories_roomCategories1` (`idRoomCategories` ASC) ,
  INDEX `fk_reservations_has_roomCategories_reservations1` (`idReservations` ASC) ,
  CONSTRAINT `fk_reservations_has_roomCategories_reservations1`
    FOREIGN KEY (`idReservations` )
    REFERENCES `roomanizer`.`reservations` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservations_has_roomCategories_roomCategories1`
    FOREIGN KEY (`idRoomCategories` )
    REFERENCES `roomanizer`.`roomCategories` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservationOptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`reservationOptions` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservationOptions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `expiration` DATE NOT NULL ,
  `prepayment` DECIMAL(10,2) NOT NULL ,
  `fulfilled` TINYINT(1) NOT NULL ,
  `comment` VARCHAR(255) NULL ,
  `idReservations` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_reservationOption_reservations1` (`idReservations` ASC) ,
  INDEX `i_expiration` (`expiration` ASC) ,
  CONSTRAINT `fk_reservationOption_reservations1`
    FOREIGN KEY (`idReservations` )
    REFERENCES `roomanizer`.`reservations` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`extraServices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`extraServices` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`extraServices` (
  `idServices` INT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`idServices`) ,
  INDEX `fk_extraServices_services1` (`idServices` ASC) ,
  INDEX `i_name` (`name` ASC) ,
  CONSTRAINT `fk_extraServices_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`idServices` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`allocations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`allocations` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`allocations` (
  `idGuests` INT NOT NULL ,
  `idService` INT NOT NULL ,
  PRIMARY KEY (`idGuests`, `idService`) ,
  INDEX `fk_allocations_guests1` (`idGuests` ASC) ,
  INDEX `fk_allocations_habitations1` (`idService` ASC) ,
  CONSTRAINT `fk_allocations_guests1`
    FOREIGN KEY (`idGuests` )
    REFERENCES `roomanizer`.`guests` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_allocations_habitations1`
    FOREIGN KEY (`idService` )
    REFERENCES `roomanizer`.`habitations` (`idServices` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`paymentMethods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`paymentMethods` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`paymentMethods` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`invoices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`invoices` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`invoices` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `invoiceNumber` VARCHAR(255) NOT NULL ,
  `discount` DECIMAL(4,2) NULL ,
  `expiration` DATE NOT NULL ,
  `fulfilled` TINYINT(1) NOT NULL ,
  `idUsers` INT NOT NULL ,
  `idpaymentMethods` INT NOT NULL ,
  `idCustomers` INT NOT NULL ,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `invoiceNumber_UNIQUE` (`invoiceNumber` ASC) ,
  INDEX `fk_invoices_employees1` (`idUsers` ASC) ,
  INDEX `fk_invoices_paymentMethods1` (`idpaymentMethods` ASC) ,
  INDEX `i_expiration` (`expiration` ASC) ,
  INDEX `i_fulfilled` (`fulfilled` ASC) ,
  INDEX `fk_invoices_customers1` (`idCustomers` ASC) ,
  CONSTRAINT `fk_invoices_employees1`
    FOREIGN KEY (`idUsers` )
    REFERENCES `roomanizer`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_paymentMethods1`
    FOREIGN KEY (`idpaymentMethods` )
    REFERENCES `roomanizer`.`paymentMethods` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_customers1`
    FOREIGN KEY (`idCustomers` )
    REFERENCES `roomanizer`.`customers` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`invoiceItems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`invoiceItems` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`invoiceItems` (
  `idServices` INT NOT NULL ,
  `idInvoice` INT NOT NULL ,
  `amount` INT NOT NULL ,
  `idUsers` INT NOT NULL ,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `idHabitations` INT NOT NULL ,
  PRIMARY KEY (`idServices`, `idInvoice`) ,
  INDEX `fk_services_has_invoice_invoice1` (`idInvoice` ASC) ,
  INDEX `fk_services_has_invoice_services1` (`idServices` ASC) ,
  INDEX `fk_invoiceItems_users1` (`idUsers` ASC) ,
  INDEX `fk_invoiceItems_habitations1` (`idHabitations` ASC) ,
  CONSTRAINT `fk_services_has_invoice_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`idServices` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_services_has_invoice_invoice1`
    FOREIGN KEY (`idInvoice` )
    REFERENCES `roomanizer`.`invoices` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_invoiceItems_users1`
    FOREIGN KEY (`idUsers` )
    REFERENCES `roomanizer`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoiceItems_habitations1`
    FOREIGN KEY (`idHabitations` )
    REFERENCES `roomanizer`.`habitations` (`idServices` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`companyTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`companyTypes` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`companyTypes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`companies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`companies` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`companies` (
  `idParties` INT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `idCompanyTypes` INT NOT NULL ,
  INDEX `fk_companies_companyTypes1` (`idCompanyTypes` ASC) ,
  INDEX `i_name` (`name` ASC) ,
  INDEX `fk_companies_customers1` (`idParties` ASC) ,
  PRIMARY KEY (`idParties`) ,
  CONSTRAINT `fk_companies_companyTypes1`
    FOREIGN KEY (`idCompanyTypes` )
    REFERENCES `roomanizer`.`companyTypes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_companies_customers1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`customers` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`companiesPersons`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`companiesPersons` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`companiesPersons` (
  `idParties` INT NOT NULL ,
  `idCompanies` INT NOT NULL ,
  PRIMARY KEY (`idParties`, `idCompanies`) ,
  INDEX `fk_companies_has_persons_persons1` (`idParties` ASC) ,
  INDEX `fk_companiesPersons_companies1` (`idCompanies` ASC) ,
  CONSTRAINT `fk_companies_has_persons_persons1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`parties` (`idParties` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_companiesPersons_companies1`
    FOREIGN KEY (`idCompanies` )
    REFERENCES `roomanizer`.`companies` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservationsGuests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`reservationsGuests` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservationsGuests` (
  `reservationsID` INT NOT NULL ,
  `personsID` INT NOT NULL ,
  PRIMARY KEY (`reservationsID`, `personsID`) ,
  INDEX `fk_guests_has_reservations_reservations1` (`reservationsID` ASC) ,
  INDEX `fk_guests_has_reservations_guests1` (`personsID` ASC) ,
  CONSTRAINT `fk_guests_has_reservations_guests1`
    FOREIGN KEY (`personsID` )
    REFERENCES `roomanizer`.`guests` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guests_has_reservations_reservations1`
    FOREIGN KEY (`reservationsID` )
    REFERENCES `roomanizer`.`reservations` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`privatePerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roomanizer`.`privatePerson` ;

CREATE  TABLE IF NOT EXISTS `roomanizer`.`privatePerson` (
  `idParties` INT NOT NULL ,
  `fname` VARCHAR(255) NOT NULL ,
  `lname` VARCHAR(255) NOT NULL ,
  INDEX `fk_privatePerson_customers1` (`idParties` ASC) ,
  PRIMARY KEY (`idParties`) ,
  CONSTRAINT `fk_privatePerson_customers1`
    FOREIGN KEY (`idParties` )
    REFERENCES `roomanizer`.`customers` (`idParties` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `roomanizer`.`roomOptions`
-- -----------------------------------------------------
START TRANSACTION;
USE `roomanizer`;
INSERT INTO `roomanizer`.`roomOptions` (`id`, `name`) VALUES (NULL, 'Smoker');
INSERT INTO `roomanizer`.`roomOptions` (`id`, `name`) VALUES (NULL, 'Animals');

COMMIT;

-- -----------------------------------------------------
-- Data for table `roomanizer`.`roomStatus`
-- -----------------------------------------------------
START TRANSACTION;
USE `roomanizer`;
INSERT INTO `roomanizer`.`roomStatus` (`id`, `name`) VALUES (NULL, 'Occupied - Clean');
INSERT INTO `roomanizer`.`roomStatus` (`id`, `name`) VALUES (NULL, 'Occupied - Dirty');
INSERT INTO `roomanizer`.`roomStatus` (`id`, `name`) VALUES (NULL, 'Free - Clean');
INSERT INTO `roomanizer`.`roomStatus` (`id`, `name`) VALUES (NULL, 'Free - Dirty');
INSERT INTO `roomanizer`.`roomStatus` (`id`, `name`) VALUES (NULL, 'Out of order');

COMMIT;

-- -----------------------------------------------------
-- Data for table `roomanizer`.`serviceTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `roomanizer`;
INSERT INTO `roomanizer`.`serviceTypes` (`id`, `name`, `taxRate`) VALUES (NULL, 'Drinks', 20);
INSERT INTO `roomanizer`.`serviceTypes` (`id`, `name`, `taxRate`) VALUES (NULL, 'Food', 10);
INSERT INTO `roomanizer`.`serviceTypes` (`id`, `name`, `taxRate`) VALUES (NULL, 'Habitation', 20);
INSERT INTO `roomanizer`.`serviceTypes` (`id`, `name`, `taxRate`) VALUES (NULL, 'Wellness', 20);
INSERT INTO `roomanizer`.`serviceTypes` (`id`, `name`, `taxRate`) VALUES (NULL, 'Others', 20);

COMMIT;

-- -----------------------------------------------------
-- Data for table `roomanizer`.`paymentMethods`
-- -----------------------------------------------------
START TRANSACTION;
USE `roomanizer`;
INSERT INTO `roomanizer`.`paymentMethods` (`id`, `name`) VALUES (NULL, 'Credit card');
INSERT INTO `roomanizer`.`paymentMethods` (`id`, `name`) VALUES (NULL, 'Cash');
INSERT INTO `roomanizer`.`paymentMethods` (`id`, `name`) VALUES (NULL, 'Credit');

COMMIT;

-- -----------------------------------------------------
-- Data for table `roomanizer`.`companyTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `roomanizer`;
INSERT INTO `roomanizer`.`companyTypes` (`id`, `name`) VALUES (NULL, 'Company');
INSERT INTO `roomanizer`.`companyTypes` (`id`, `name`) VALUES (NULL, 'Travel Agency');

COMMIT;
