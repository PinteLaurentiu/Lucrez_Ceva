drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(100) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(100) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

INSERT INTO oauth_client_details(client_id, resource_ids, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('IOS-client', 'resource-server-rest-api',
        /*password*/'$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
        'read,write', 'password,refresh_token', null, 36000, 36000);

INSERT INTO user (id,name,avatarPath,abilities) VALUES (1,'Alex Pintilie','','');
INSERT INTO userLogin (id, email, bcrypPassword, user_id) VALUES (1, 'Alex123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',1);
INSERT INTO userRole(role,user_id) VALUES (0,1);
INSERT INTO userRole(role,user_id) VALUES (1,1);
INSERT INTO userActivation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg', 1);

INSERT INTO user (id,name,avatarPath,abilities) VALUES (2,'Pop Andrei','','');
INSERT INTO userLogin (id, email, bcrypPassword, user_id) VALUES (2, 'Andrei123', /*password*/ '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',2);
INSERT INTO userRole(role,user_id) VALUES (0,2);
INSERT INTO userActivation(activated, expiration, uuid, user_id) VALUES (1, NULL, 'gg2', 2);
