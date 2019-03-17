CREATE TABLE formwork_project (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  max_left_girders INTEGER UNSIGNED NULL,
  max_left_girders_value INTEGER UNSIGNED NULL,
  is_better_solution_available BOOL NULL,
  is_better_solution_calculated BOOL NULL,
  result_json VARCHAR NULL,
  PRIMARY KEY(id)
);

CREATE TABLE formwork (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_project_id INTEGER UNSIGNED NOT NULL,
  name VARCHAR(45) NULL,
  create_date DATE NULL,
  info VARCHAR(255) NULL,
  PRIMARY KEY(id, formwork_project_id),
  INDEX formwork_FKIndex1(formwork_project_id),
  FOREIGN KEY(formwork_project_id)
    REFERENCES formwork_project(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE formwork_project_details (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_project_id INTEGER UNSIGNED NOT NULL,
  upper_extra_distance FLOAT NULL,
  distance FLOAT NULL,
  overlap_distance FLOAT NULL,
  one_upper_girder_distance FLOAT NULL,
  accuracy INTEGER UNSIGNED NULL,
  PRIMARY KEY(id, formwork_project_id),
  INDEX formwork_project_details_FKIndex1(formwork_project_id),
  FOREIGN KEY(formwork_project_id)
    REFERENCES formwork_project(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE girder_set (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_id INTEGER UNSIGNED NOT NULL,
  formwork_project_id INTEGER UNSIGNED NOT NULL,
  len FLOAT NULL,
  quantity INTEGER UNSIGNED NULL,
  PRIMARY KEY(id, formwork_id, formwork_project_id),
  INDEX girder_set_FKIndex1(formwork_id, formwork_project_id),
  FOREIGN KEY(formwork_id, formwork_project_id)
    REFERENCES formwork(id, formwork_project_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE room (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  formwork_id INTEGER UNSIGNED NOT NULL,
  formwork_project_id INTEGER UNSIGNED NOT NULL,
  len FLOAT NULL,
  wid FLOAT NULL,
  PRIMARY KEY(id, formwork_id, formwork_project_id),
  INDEX room_FKIndex1(formwork_id, formwork_project_id),
  FOREIGN KEY(formwork_id, formwork_project_id)
    REFERENCES formwork(id, formwork_project_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


