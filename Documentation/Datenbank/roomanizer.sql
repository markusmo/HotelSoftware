SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `roomanizer` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `roomanizer` ;

-- -----------------------------------------------------
-- Table `roomanizer`.`roomCategories`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomCategories` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`rooms`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `roomNumber` INT NOT NULL ,
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
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomOptions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomsRoomOptions`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomsRoomOptions` (
  `idRoom` INT NOT NULL ,
  `idOptions` INT NOT NULL ,
  PRIMARY KEY (`idRoom`, `idOptions`) ,
  INDEX `fk_room_has_roomoptions_roomoptions1` (`idOptions` ASC) ,
  INDEX `fk_room_has_roomoptions_room` (`idRoom` ASC) ,
  CONSTRAINT `fk_room_has_roomoptions_room`
    FOREIGN KEY (`idRoom` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_has_roomoptions_roomoptions1`
    FOREIGN KEY (`idOptions` )
    REFERENCES `roomanizer`.`roomOptions` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomStatus`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomStatus` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomsRoomStatus`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomsRoomStatus` (
  `idRooms` INT NOT NULL ,
  `idRoomStatus` INT NOT NULL ,
  `start` DATE NOT NULL ,
  `end` DATE NOT NULL ,
  PRIMARY KEY (`idRooms`, `idRoomStatus`) ,
  INDEX `fk_rooms_has_roomStatus_roomStatus1` (`idRoomStatus` ASC) ,
  INDEX `fk_rooms_has_roomStatus_rooms1` (`idRooms` ASC) ,
  INDEX `i_start` (`start` ASC) ,
  INDEX `i_end` (`end` ASC) ,
  CONSTRAINT `fk_rooms_has_roomStatus_rooms1`
    FOREIGN KEY (`idRooms` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rooms_has_roomStatus_roomStatus1`
    FOREIGN KEY (`idRoomStatus` )
    REFERENCES `roomanizer`.`roomStatus` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`season`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`season` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `start` DATE NOT NULL COMMENT 'problem with the year' ,
  `end` DATE NOT NULL COMMENT 'problem with the year' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `i_start` (`start` ASC) ,
  INDEX `i_end` (`end` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roomCategoryPrice`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roomCategoryPrice` (
  `idRoomCategories` INT NOT NULL ,
  `idSeason` INT NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `priceMin` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`idRoomCategories`, `idSeason`) ,
  INDEX `fk_roomCategories_has_season_season1` (`idSeason` ASC) ,
  INDEX `fk_roomCategories_has_season_roomCategories1` (`idRoomCategories` ASC) ,
  CONSTRAINT `fk_roomCategories_has_season_roomCategories1`
    FOREIGN KEY (`idRoomCategories` )
    REFERENCES `roomanizer`.`roomCategories` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomCategories_has_season_season1`
    FOREIGN KEY (`idSeason` )
    REFERENCES `roomanizer`.`season` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`countries`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`countries` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `nameShort` CHAR(2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  UNIQUE INDEX `nameShort_UNIQUE` (`nameShort` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`persons`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`persons` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fname` VARCHAR(255) NOT NULL ,
  `lname` VARCHAR(255) NOT NULL ,
  `street` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `zip` VARCHAR(255) NULL ,
  `email` VARCHAR(255) NULL ,
  `phone` VARCHAR(255) NULL ,
  `fax` VARCHAR(255) NULL ,
  `birthday` DATE NULL ,
  `idCountries` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_person_country1` (`idCountries` ASC) ,
  INDEX `i_fname` (`fname` ASC) ,
  INDEX `i_lname` (`lname` ASC) ,
  CONSTRAINT `fk_person_country1`
    FOREIGN KEY (`idCountries` )
    REFERENCES `roomanizer`.`countries` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`employees`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`employees` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `employeeCode` VARCHAR(255) NOT NULL ,
  `password` CHAR(32) NOT NULL ,
  `idPersons` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `employeeCode_UNIQUE` (`employeeCode` ASC) ,
  INDEX `fk_employees_persons1` (`idPersons` ASC) ,
  UNIQUE INDEX `idPersons_UNIQUE` (`idPersons` ASC) ,
  INDEX `i_password` (`password` ASC) ,
  CONSTRAINT `fk_employees_persons1`
    FOREIGN KEY (`idPersons` )
    REFERENCES `roomanizer`.`persons` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`employeeRoles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`employeeRoles` (
  `idRole` INT NOT NULL ,
  `idEmployees` INT NOT NULL ,
  PRIMARY KEY (`idRole`, `idEmployees`) ,
  INDEX `fk_role_has_employee_employee1` (`idEmployees` ASC) ,
  INDEX `fk_role_has_employee_role1` (`idRole` ASC) ,
  CONSTRAINT `fk_role_has_employee_role1`
    FOREIGN KEY (`idRole` )
    REFERENCES `roomanizer`.`roles` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_employee_employee1`
    FOREIGN KEY (`idEmployees` )
    REFERENCES `roomanizer`.`employees` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`permissions`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`permissions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`rolePermissions`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`rolePermissions` (
  `idPermissions` INT NOT NULL ,
  `idRoles` INT NOT NULL ,
  PRIMARY KEY (`idPermissions`, `idRoles`) ,
  INDEX `fk_permissions_has_roles_roles1` (`idRoles` ASC) ,
  INDEX `fk_permissions_has_roles_permissions1` (`idPermissions` ASC) ,
  CONSTRAINT `fk_permissions_has_roles_permissions1`
    FOREIGN KEY (`idPermissions` )
    REFERENCES `roomanizer`.`permissions` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permissions_has_roles_roles1`
    FOREIGN KEY (`idRoles` )
    REFERENCES `roomanizer`.`roles` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`customers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idPersons` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_customer_persons1` (`idPersons` ASC) ,
  UNIQUE INDEX `idPersons_UNIQUE` (`idPersons` ASC) ,
  CONSTRAINT `fk_customer_persons1`
    FOREIGN KEY (`idPersons` )
    REFERENCES `roomanizer`.`persons` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`guests`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`guests` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idPersons` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_guest_persons1` (`idPersons` ASC) ,
  UNIQUE INDEX `idPersons_UNIQUE` (`idPersons` ASC) ,
  CONSTRAINT `fk_guest_persons1`
    FOREIGN KEY (`idPersons` )
    REFERENCES `roomanizer`.`persons` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`serviceTypes`
-- -----------------------------------------------------
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
CREATE  TABLE IF NOT EXISTS `roomanizer`.`services` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `idServiceTypes` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_services_serviceTypes1` (`idServiceTypes` ASC) ,
  INDEX `i_name` (`name` ASC) ,
  CONSTRAINT `fk_services_serviceTypes1`
    FOREIGN KEY (`idServiceTypes` )
    REFERENCES `roomanizer`.`serviceTypes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`habitations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`habitations` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `start` DATE NOT NULL ,
  `end` DATE NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `idRooms` INT NOT NULL ,
  `idServices` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_habitation_rooms1` (`idRooms` ASC) ,
  INDEX `fk_habitations_services1` (`idServices` ASC) ,
  INDEX `i_star` (`start` ASC) ,
  INDEX `i_end` (`end` ASC) ,
  CONSTRAINT `fk_habitation_rooms1`
    FOREIGN KEY (`idRooms` )
    REFERENCES `roomanizer`.`rooms` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitations_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservations` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `reserationNumber` VARCHAR(255) NOT NULL ,
  `start` DATE NOT NULL ,
  `end` DATE NOT NULL ,
  `comment` VARCHAR(255) NULL ,
  `idPersons` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_reservations_persons1` (`idPersons` ASC) ,
  UNIQUE INDEX `reserationNumber_UNIQUE` (`reserationNumber` ASC) ,
  INDEX `i_start` (`start` ASC) ,
  INDEX `i_end` (`end` ASC) ,
  CONSTRAINT `fk_reservations_persons1`
    FOREIGN KEY (`idPersons` )
    REFERENCES `roomanizer`.`persons` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservationItem`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservationItem` (
  `idReservations` INT NOT NULL ,
  `idRoomCategories` INT NOT NULL ,
  `amount` INT NOT NULL ,
  PRIMARY KEY (`idReservations`, `idRoomCategories`) ,
  INDEX `fk_reservations_has_roomCategories_roomCategories1` (`idRoomCategories` ASC) ,
  INDEX `fk_reservations_has_roomCategories_reservations1` (`idReservations` ASC) ,
  CONSTRAINT `fk_reservations_has_roomCategories_reservations1`
    FOREIGN KEY (`idReservations` )
    REFERENCES `roomanizer`.`reservations` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservations_has_roomCategories_roomCategories1`
    FOREIGN KEY (`idRoomCategories` )
    REFERENCES `roomanizer`.`roomCategories` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`reservationOption`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`reservationOption` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `expiration` DATE NOT NULL ,
  `prepayment` DECIMAL(10,2) NOT NULL ,
  `fulfilled` TINYINT(1) NOT NULL ,
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
CREATE  TABLE IF NOT EXISTS `roomanizer`.`extraServices` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `price` DECIMAL(10,2) NOT NULL ,
  `idServices` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_extraServices_services1` (`idServices` ASC) ,
  INDEX `i_name` (`name` ASC) ,
  CONSTRAINT `fk_extraServices_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`allocations`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`allocations` (
  `idHabitations` INT NOT NULL ,
  `idGuests` INT NOT NULL ,
  PRIMARY KEY (`idHabitations`, `idGuests`) ,
  INDEX `fk_habitations_has_guests_guests1` (`idGuests` ASC) ,
  INDEX `fk_habitations_has_guests_habitations1` (`idHabitations` ASC) ,
  CONSTRAINT `fk_habitations_has_guests_habitations1`
    FOREIGN KEY (`idHabitations` )
    REFERENCES `roomanizer`.`habitations` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_habitations_has_guests_guests1`
    FOREIGN KEY (`idGuests` )
    REFERENCES `roomanizer`.`guests` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`paymentMethods`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`paymentMethods` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`invoices`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`invoices` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `invoiceNumber` INT NOT NULL ,
  `issuing` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `discount` DECIMAL(4,2) NULL ,
  `fulfilled` TINYINT(1) NOT NULL ,
  `idEmployees` INT NOT NULL ,
  `idCustomers` INT NOT NULL ,
  `idpaymentMethods` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `invoiceNumber_UNIQUE` (`invoiceNumber` ASC) ,
  INDEX `fk_invoices_employees1` (`idEmployees` ASC) ,
  INDEX `fk_invoices_customers1` (`idCustomers` ASC) ,
  INDEX `fk_invoices_paymentMethods1` (`idpaymentMethods` ASC) ,
  CONSTRAINT `fk_invoices_employees1`
    FOREIGN KEY (`idEmployees` )
    REFERENCES `roomanizer`.`employees` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_customers1`
    FOREIGN KEY (`idCustomers` )
    REFERENCES `roomanizer`.`customers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoices_paymentMethods1`
    FOREIGN KEY (`idpaymentMethods` )
    REFERENCES `roomanizer`.`paymentMethods` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`invoiceItems`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`invoiceItems` (
  `idServices` INT NOT NULL ,
  `idInvoice` INT NOT NULL ,
  `amount` INT NOT NULL ,
  `idHabitations` INT NOT NULL ,
  PRIMARY KEY (`idServices`, `idInvoice`) ,
  INDEX `fk_services_has_invoice_invoice1` (`idInvoice` ASC) ,
  INDEX `fk_services_has_invoice_services1` (`idServices` ASC) ,
  INDEX `fk_invoiceItems_habitations1` (`idHabitations` ASC) ,
  CONSTRAINT `fk_services_has_invoice_services1`
    FOREIGN KEY (`idServices` )
    REFERENCES `roomanizer`.`services` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_services_has_invoice_invoice1`
    FOREIGN KEY (`idInvoice` )
    REFERENCES `roomanizer`.`invoices` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoiceItems_habitations1`
    FOREIGN KEY (`idHabitations` )
    REFERENCES `roomanizer`.`habitations` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`companyTypes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`companyTypes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roomanizer`.`companies`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `roomanizer`.`companies` (
  `id` INT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `idCompanyTypes` INT NOT NULL ,
  `idCustomers` INT NOT NULL ,
  `companiescol` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_companies_companyTypes1` (`idCompanyTypes` ASC) ,
  INDEX `fk_companies_customers1` (`idCustomers` ASC) ,
  INDEX `i_name` (`name` ASC) ,
  CONSTRAINT `fk_companies_companyTypes1`
    FOREIGN KEY (`idCompanyTypes` )
    REFERENCES `roomanizer`.`companyTypes` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_companies_customers1`
    FOREIGN KEY (`idCustomers` )
    REFERENCES `roomanizer`.`customers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
