CREATE TABLE user(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    prefix varchar(50),
    surname varchar(50) NOT NULL,
    phone_number varchar(15) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    password varchar(64) NOT NULL,
    user_role varchar(20) NOT NULL,
    student_number varchar(12),
    company_name varchar(50),
    coc_number varchar(16),
    address varchar(50),
    zipcode varchar(50),
    city varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE listing(
    id int NOT NULL AUTO_INCREMENT,
    address varchar(100) NOT NULL,
    city varchar(50) NOT NULL,
    neighborhood varchar(100) NOT NULL,
    owner_id int,
    description varchar(10000) NOT NULL,
    surface_area int NOT NULL,
    rent double NOT NULL,
    pets_allowed boolean NOT NULL,
    end_date DATETIME NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES user(id)
        ON DELETE CASCADE
);

CREATE TABLE responses(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    listing_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
      ON DELETE CASCADE,
    FOREIGN KEY (listing_id) REFERENCES listing(id)
      ON DELETE CASCADE
);