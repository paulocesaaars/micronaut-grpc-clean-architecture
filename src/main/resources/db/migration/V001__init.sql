CREATE TABLE tb_user (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,

    CONSTRAINT email UNIQUE(email)
);
