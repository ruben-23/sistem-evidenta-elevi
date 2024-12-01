CREATE TABLE IF NOT EXISTS `liceuu`.`roluri` (
                                                   `id_rol` INT NOT NULL AUTO_INCREMENT,
                                                   `nume` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_rol`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`users` (
                                                  `id_user` INT NOT NULL AUTO_INCREMENT,
                                                  `username` VARCHAR(50) NOT NULL,
    `parola` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `id_rol` INT NOT NULL,
    PRIMARY KEY (`id_user`),
    INDEX `fk_users_roluri1_idx` (`id_rol` ASC) VISIBLE,
    CONSTRAINT `fk_users_roluri1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `liceuu`.`roluri` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`profesori`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`profesori` (
                                                      `id_profesor` INT NOT NULL AUTO_INCREMENT,
                                                      `id_user` INT NOT NULL,
                                                      `nume` VARCHAR(50) NOT NULL,
    `prenume` VARCHAR(50) NOT NULL,
    `numar_telefon` VARCHAR(45) NOT NULL,
    `adresa` VARCHAR(255) NOT NULL,
    `CNP` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_profesor`),
    INDEX `fk_profesori_users1_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `fk_profesori_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `liceuu`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`specializari`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`specializari` (
                                                         `id_specializare` INT NOT NULL AUTO_INCREMENT,
                                                         `nume` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_specializare`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`clase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`clase` (
                                                  `id_clasa` INT NOT NULL AUTO_INCREMENT,
                                                  `nume` VARCHAR(50) NOT NULL,
    `id_profesor` INT NOT NULL,
    `id_specializare` INT NOT NULL,
    PRIMARY KEY (`id_clasa`),
    INDEX `fk_clasa_profesori1_idx` (`id_profesor` ASC) VISIBLE,
    INDEX `fk_clase_specializari1_idx` (`id_specializare` ASC) VISIBLE,
    CONSTRAINT `fk_clasa_profesori1`
    FOREIGN KEY (`id_profesor`)
    REFERENCES `liceuu`.`profesori` (`id_profesor`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_clase_specializari1`
    FOREIGN KEY (`id_specializare`)
    REFERENCES `liceuu`.`specializari` (`id_specializare`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`elevi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`elevi` (
                                                  `id_elev` INT NOT NULL AUTO_INCREMENT,
                                                  `nume` VARCHAR(50) NOT NULL,
    `prenume` VARCHAR(50) NOT NULL,
    `CNP` VARCHAR(50) NOT NULL,
    `sex` VARCHAR(50) NOT NULL,
    `numar_telefon` VARCHAR(50) NULL,
    `adresa` VARCHAR(255) NOT NULL,
    `data_nasterii` DATE NOT NULL,
    `id_clasa` INT NOT NULL,
    PRIMARY KEY (`id_elev`),
    INDEX `fk_elevi_clasa_idx` (`id_clasa` ASC) VISIBLE,
    CONSTRAINT `fk_elevi_clasa`
    FOREIGN KEY (`id_clasa`)
    REFERENCES `liceuu`.`clase` (`id_clasa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`materii`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`materii` (
                                                    `id_materie` INT NOT NULL AUTO_INCREMENT,
                                                    `nume` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id_materie`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`clase_materii_profesori`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`clase_materii_profesori` (
                                                                    `id_clasa` INT NOT NULL,
                                                                    `id_profesor` INT NOT NULL,
                                                                    `id_materie` INT NOT NULL,
                                                                    INDEX `fk_clase_materii_profesori_clasa1_idx` (`id_clasa` ASC) VISIBLE,
    INDEX `fk_clase_materii_profesori_profesori1_idx` (`id_profesor` ASC) VISIBLE,
    PRIMARY KEY (`id_materie`, `id_profesor`, `id_clasa`),
    CONSTRAINT `fk_clase_materii_profesori_clasa1`
    FOREIGN KEY (`id_clasa`)
    REFERENCES `liceuu`.`clase` (`id_clasa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_clase_materii_profesori_profesori1`
    FOREIGN KEY (`id_profesor`)
    REFERENCES `liceuu`.`profesori` (`id_profesor`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_clase_materii_profesori_materii1`
    FOREIGN KEY (`id_materie`)
    REFERENCES `liceuu`.`materii` (`id_materie`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`note`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`note` (
                                                 `id_nota` INT NOT NULL AUTO_INCREMENT,
                                                 `nota` TINYINT NOT NULL,
                                                 `data` DATE NOT NULL,
                                                 `id_materie` INT NOT NULL,
                                                 `id_profesor` INT NOT NULL,
                                                 `id_clasa` INT NOT NULL,
                                                 `id_elev` INT NOT NULL,
                                                 INDEX `fk_note_clase_materii_profesori1_idx` (`id_materie` ASC, `id_profesor` ASC, `id_clasa` ASC) VISIBLE,
    INDEX `fk_note_elevi1_idx` (`id_elev` ASC) VISIBLE,
    PRIMARY KEY (`id_nota`),
    CONSTRAINT `fk_note_clase_materii_profesori1`
    FOREIGN KEY (`id_materie` , `id_profesor` , `id_clasa`)
    REFERENCES `liceuu`.`clase_materii_profesori` (`id_materie` , `id_profesor` , `id_clasa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_note_elevi1`
    FOREIGN KEY (`id_elev`)
    REFERENCES `liceuu`.`elevi` (`id_elev`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`absente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`absente` (
                                                    `id_absenta` INT NOT NULL AUTO_INCREMENT,
                                                    `data` DATE NOT NULL,
                                                    `id_elev` INT NOT NULL,
                                                    `id_materie` INT NOT NULL,
                                                    `id_profesor` INT NOT NULL,
                                                    `id_clasa` INT NOT NULL,
                                                    PRIMARY KEY (`id_absenta`),
    INDEX `fk_absente_clase_materii_profesori1_idx` (`id_materie` ASC, `id_profesor` ASC, `id_clasa` ASC) VISIBLE,
    INDEX `fk_absente_elevi1_idx` (`id_elev` ASC) VISIBLE,
    CONSTRAINT `fk_absente_clase_materii_profesori1`
    FOREIGN KEY (`id_materie` , `id_profesor` , `id_clasa`)
    REFERENCES `liceuu`.`clase_materii_profesori` (`id_materie` , `id_profesor` , `id_clasa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_absente_elevi1`
    FOREIGN KEY (`id_elev`)
    REFERENCES `liceuu`.`elevi` (`id_elev`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`secretara`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`secretara` (
                                                      `id_secretara` INT NOT NULL AUTO_INCREMENT,
                                                      `id_user` INT NOT NULL,
                                                      `nume` VARCHAR(50) NOT NULL,
    `prenume` VARCHAR(50) NOT NULL,
    `adresa` VARCHAR(255) NOT NULL,
    `numar_telefon` VARCHAR(50) NOT NULL,
    `CNP` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_secretara`),
    INDEX `fk_secretara_users1_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `fk_secretara_users1`
    FOREIGN KEY (`id_user`)
    REFERENCES `liceuu`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`permisiuni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`permisiuni` (
                                                       `id_permisiune` INT NOT NULL AUTO_INCREMENT,
                                                       `nume` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_permisiune`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`roluri_permisiuni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`roluri_permisiuni` (
                                                              `id_rol` INT NOT NULL,
                                                              `iid_permisiune` INT NOT NULL,
                                                              PRIMARY KEY (`id_rol`, `iid_permisiune`),
    INDEX `fk_roluri_permisiuni_permisiuni1_idx` (`iid_permisiune` ASC) VISIBLE,
    CONSTRAINT `fk_roluri_permisiuni_roluri1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `liceuu`.`roluri` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_roluri_permisiuni_permisiuni1`
    FOREIGN KEY (`iid_permisiune`)
    REFERENCES `liceuu`.`permisiuni` (`id_permisiune`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`burse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`burse` (
                                                  `id_bursa` INT NOT NULL AUTO_INCREMENT,
                                                  `tip` VARCHAR(50) NOT NULL,
    `suma` INT NOT NULL,
    PRIMARY KEY (`id_bursa`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liceuu`.`elevi_burse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liceuu`.`elevi_burse` (
                                                        `id_bursa` INT NOT NULL,
                                                        `id_elev` INT NOT NULL,
                                                        INDEX `fk_elevi_burse_burse1_idx` (`id_bursa` ASC) VISIBLE,
    INDEX `fk_elevi_burse_elevi1_idx` (`id_elev` ASC) VISIBLE,
    PRIMARY KEY (`id_bursa`, `id_elev`),
    CONSTRAINT `fk_elevi_burse_burse1`
    FOREIGN KEY (`id_bursa`)
    REFERENCES `liceuu`.`burse` (`id_bursa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    CONSTRAINT `fk_elevi_burse_elevi1`
    FOREIGN KEY (`id_elev`)
    REFERENCES `liceuu`.`elevi` (`id_elev`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;