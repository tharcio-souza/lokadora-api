CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    email VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255)

);
CREATE TABLE user_roles
(
    user_id    SERIAL NOT NULL ,
    role  VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)

);