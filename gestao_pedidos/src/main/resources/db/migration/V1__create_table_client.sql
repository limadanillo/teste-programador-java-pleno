-- Version: 1
-- Description: Create client table

CREATE TABLE IF NOT EXISTS client (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  telephone VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE
);