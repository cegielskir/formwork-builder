CREATE DATABASE  IF NOT EXISTS `formwork-builder`;
USE `formwork-builder`;

DROP TABLE IF EXISTS `formwork`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `girder_set`;

CREATE TABLE formwork (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  create_date DATE NULL,
  info VARCHAR(255) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE room (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_id INTEGER UNSIGNED NOT NULL,
  len FLOAT NULL,
  wid FLOAT NULL,
  PRIMARY KEY(id, formwork_id),
  FOREIGN KEY(formwork_id)
    REFERENCES formwork(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE girder_set (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_id INTEGER UNSIGNED NOT NULL,
  len FLOAT NULL,
  quantity INTEGER UNSIGNED NULL,
  PRIMARY KEY(id, formwork_id),
  INDEX GirderSet_FKIndex1(formwork_id),
  FOREIGN KEY(formwork_id)
    REFERENCES formwork(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

