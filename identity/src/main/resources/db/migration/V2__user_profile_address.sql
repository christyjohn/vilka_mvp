-- =========================
-- USER PROFILES
-- =========================
CREATE TABLE user_profiles (
    user_id BIGINT UNSIGNED PRIMARY KEY,

    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    gender VARCHAR(20),
    date_of_birth DATE,

    profile_image_url VARCHAR(500),
    bio TEXT,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_profiles_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_user_profiles_phone ON user_profiles(phone);


-- =========================
-- COUNTRIES
-- =========================
CREATE TABLE countries (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    country_code CHAR(2) NOT NULL ,
    country_name VARCHAR(100) NOT NULL,
	
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT uk_countries_code UNIQUE (country_code)
);


-- =========================
-- CURRENCIES
-- =========================
CREATE TABLE currencies (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    code VARCHAR(3) NOT NULL,
    name VARCHAR(100) NOT NULL,
    symbol VARCHAR(10),
    minor_unit SMALLINT DEFAULT 2,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_currencies_code UNIQUE (code)
);


-- =========================
-- COUNTRY ↔ CURRENCY
-- =========================
CREATE TABLE country_currencies (
    country_id BIGINT UNSIGNED NOT NULL,
    currency_id BIGINT UNSIGNED NOT NULL,

    is_primary BOOLEAN DEFAULT TRUE,

    PRIMARY KEY (country_id, currency_id),

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
    CONSTRAINT fk_cc_country
        FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE,

    CONSTRAINT fk_cc_currency
        FOREIGN KEY (currency_id) REFERENCES currencies(id) ON DELETE CASCADE
);

CREATE INDEX idx_cc_currency_id ON country_currencies(currency_id);


-- =========================
-- USER ADDRESSES
-- =========================
CREATE TABLE user_addresses (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT UNSIGNED NOT NULL,

    label ENUM('HOME', 'WORK', 'OTHER'),

    street_address VARCHAR(255) NOT NULL,
    landmark VARCHAR(255),
    locality VARCHAR(150),

    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    postal_code VARCHAR(20),

    country_id BIGINT UNSIGNED NOT NULL,
	
	latitude DECIMAL(10,8),
	longitude DECIMAL(11,8),

    is_default BOOLEAN DEFAULT FALSE,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_addresses_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_addresses_country
        FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE INDEX idx_user_addresses_user_id ON user_addresses(user_id);
CREATE INDEX idx_user_addresses_country_id ON user_addresses(country_id);
CREATE INDEX idx_user_addresses_lat_lng ON user_addresses(latitude, longitude);

-- =========================
-- USER PREFERENCES
-- =========================
CREATE TABLE user_preferences (
    user_id BIGINT UNSIGNED PRIMARY KEY,

    language VARCHAR(20) DEFAULT 'en',
    currency_id BIGINT UNSIGNED,

    notifications_enabled BOOLEAN DEFAULT TRUE,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_preferences_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_preferences_currency
        FOREIGN KEY (currency_id) REFERENCES currencies(id)
);

CREATE INDEX idx_user_preferences_currency_id 
ON user_preferences(currency_id);