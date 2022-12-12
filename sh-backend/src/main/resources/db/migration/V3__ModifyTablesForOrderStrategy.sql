ALTER TABLE responses ADD response_date DATETIME NOT NULL AFTER listing_id;
ALTER TABLE user ADD signup_date DATETIME AFTER student_number;
ALTER TABLE listing ADD order_type varchar(20) NOT NULL AFTER pets_allowed;
UPDATE listing SET order_type = 'RANDOM' WHERE order_type = '';

INSERT INTO user (name, prefix, surname, phone_number, email, password, user_role, student_number, signup_date, company_name, coc_number, address, zipcode, city)
VALUES('Lex', 'de', 'Kort', '0611223344', 'lexdekort@live.nl', '$2a$10$ygaisjLvp81gcgYnnUaGoO5UJzs2Mc4bYvy2OU2DrkfXu7286pJgi', 'LANDLORD', NULL, NULL, NULL, NULL, 'Bananenlaan 25', '1234 AB', 'Helmond');

INSERT INTO user (name, prefix, surname, phone_number, email, password, user_role, student_number, signup_date, company_name, coc_number, address, zipcode, city)
VALUES('Nick', NULL, 'Blom', '0624687531', 'nickster@live.nl', '$2a$10$jHSnXwIc0gh0Q.xRowtyeuw.24y6XVvD19jMMh9jeLPw3C0.d..0i', 'STUDENT', '222222', '2007-01-30 21:31:07', NULL, NULL, NULL, NULL, NULL);

INSERT INTO user (name, prefix, surname, phone_number, email, password, user_role, student_number, signup_date, company_name, coc_number, address, zipcode, city)
VALUES('Mohammed', NULL, 'Bouali', '0655667788', 'mohmaster@gmail.com', '$2a$10$8A5FNFRO.N1b11aJquSU6eAS9Qm2qzYSKKLMhQn2JvBR16U5tWJkW', 'STUDENT', '111111', '2007-01-30 22:31:07', NULL, NULL, NULL, NULL, NULL);

UPDATE listing SET owner_id = 1 WHERE owner_id IS NULL OR owner_id = '';

ALTER TABLE listing MODIFY owner_id int NOT NULL;