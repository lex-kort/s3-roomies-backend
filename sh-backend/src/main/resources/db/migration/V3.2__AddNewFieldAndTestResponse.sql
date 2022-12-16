ALTER TABLE responses ADD has_accepted boolean AFTER position;

INSERT INTO responses (listing_id, user_id, response_date) VALUES (1, 2, '2007-01-30 22:31:07');
INSERT INTO responses (listing_id, user_id, response_date) VALUES (1, 3, '2007-01-30 22:32:07');