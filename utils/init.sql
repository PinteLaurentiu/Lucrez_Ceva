

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('IOS-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 36000, 36000);

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('Android-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 36000, 36000);

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('Web-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 36000, 36000);

INSERT INTO user (id,name,avatarPath) VALUES (1,'Alex Pintilie','');
INSERT INTO user_login (id, email, bcrypPassword, user_id) VALUES (1, 'Alex123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',1);
INSERT INTO user_role(role,user_id) VALUES (0,1);
INSERT INTO user_role(role,user_id) VALUES (1,1);
INSERT INTO user_activation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg', 1);

INSERT INTO user (id,name,avatarPath) VALUES (2,'Pop Andrei','');
INSERT INTO user_login (id, email, bcrypPassword, user_id) VALUES (2, 'Andrei123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',2);
INSERT INTO user_role(role,user_id) VALUES (0,2);
INSERT INTO user_activation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg2', 2);

INSERT INTO job (id, date, description, expireDate, jobType, location, title, user_id, salary, acceptanceType) VALUES (NULL, '2019-01-01', 'mata', '2019-01-31', 0, 'ana', 'are', 3, 1000, 0);
INSERT INTO job (id, date, description, expireDate, jobType, location, title, user_id, salary, acceptanceType) VALUES (NULL, '2019-01-02', 'mata', '2019-01-31', 1, 'anuta', 'nare', 3, 1000, 0);