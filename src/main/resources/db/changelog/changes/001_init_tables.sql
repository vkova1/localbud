
CREATE TABLE IF NOT EXISTS app_user (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(255),
    subscription VARCHAR(100),
    password VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS business (
                                        id BIGSERIAL PRIMARY KEY,
                                        owner_id BIGINT,
                                        name VARCHAR(255) NOT NULL,
    description TEXT,
    slug VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS business_context (
                                                id BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS message (
                                       id BIGSERIAL PRIMARY KEY,
                                       promt TEXT,
                                       user_id BIGINT,
                                       business_id BIGINT,
                                       response TEXT,
                                       CONSTRAINT fk_message_business FOREIGN KEY (business_id)
    REFERENCES business (id) ON DELETE CASCADE,
    CONSTRAINT fk_message_user FOREIGN KEY (user_id)
    REFERENCES app_user (id) ON DELETE CASCADE
    );

--rollback DROP TABLE IF EXISTS message, business_context, business, app_user;