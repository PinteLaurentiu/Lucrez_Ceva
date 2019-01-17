create table oauth_access_token
(
  token_id varchar(256) null,
  token blob null,
  authentication_id varchar(100) not null
    primary key,
  user_name varchar(256) null,
  client_id varchar(256) null,
  authentication blob null,
  refresh_token varchar(256) null
)
;

create table oauth_client_details
(
  client_id varchar(100) not null
    primary key,
  resource_ids varchar(256) null,
  client_secret varchar(256) null,
  scope varchar(256) null,
  authorized_grant_types varchar(256) null,
  web_server_redirect_uri varchar(256) null,
  authorities varchar(256) null,
  access_token_validity int null,
  refresh_token_validity int null,
  additional_information varchar(4096) null,
  autoapprove varchar(256) null
)
;

create table oauth_refresh_token
(
  token_id varchar(256) null,
  token blob null,
  authentication blob null
)
;



INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('IOS-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 604800, 604800);

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('Android-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 604800, 604800);

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('Web-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 604800, 604800);

INSERT INTO user (id,name,avatarPath) VALUES (1,'Alex Pintilie','');
INSERT INTO user_login (id, email, bcrypPassword, user_id) VALUES (1, 'Alex123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',1);
INSERT INTO user_role(role,user_id) VALUES (0,1);
INSERT INTO user_role(role,user_id) VALUES (1,1);
INSERT INTO user_activation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg', 1);

INSERT INTO user (id,name,avatarPath) VALUES (2,'Pop Andrei','');
INSERT INTO user_login (id, email, bcrypPassword, user_id) VALUES (2, 'Andrei123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',2);
INSERT INTO user_role(role,user_id) VALUES (0,2);
INSERT INTO user_activation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg2', 2);

INSERT INTO job (id, date, description, expireDate, jobType, location, title, user_id, salary, acceptanceType) VALUES (NULL, '2019-01-01', 'mata', '2019-01-31', 0, 'ana', 'are', 1, 1000, 0);
INSERT INTO job (id, date, description, expireDate, jobType, location, title, user_id, salary, acceptanceType) VALUES (NULL, '2019-01-02', 'mata', '2019-01-31', 1, 'anuta', 'nare', 1, 1000, 0);