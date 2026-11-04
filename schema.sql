CREATE TABLE IF NOT EXISTS phones (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    ram_gb INT NOT NULL,
    operating_system VARCHAR(50),
    has_torch BOOLEAN,
    is_dual_screen BOOLEAN,
    protection_standard VARCHAR(50)
);