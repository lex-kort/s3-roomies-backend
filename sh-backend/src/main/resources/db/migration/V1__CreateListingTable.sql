CREATE TABLE listing(
    id int NOT NULL AUTO_INCREMENT,
    address varchar(100) NOT NULL,
    city varchar(50) NOT NULL,
    neighborhood varchar(100) NOT NULL,
    description varchar(10000) NOT NULL,
    surface_area int NOT NULL,
    rent double NOT NULL,
    pets_allowed boolean NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id)
)