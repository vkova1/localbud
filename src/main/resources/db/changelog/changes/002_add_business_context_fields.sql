CREATE TABLE business_context (
                                  id BIGSERIAL PRIMARY KEY,
                                  business_id BIGINT NOT NULL REFERENCES business(id) ON DELETE CASCADE,
                                  title VARCHAR(255),
                                  category VARCHAR(100),
                                  language VARCHAR(10) DEFAULT 'en',
                                  text TEXT NOT NULL,
                                  vector JSONB,
                                  chunk_index INT,
                                  source VARCHAR(100) DEFAULT 'manual',
                                  is_active BOOLEAN DEFAULT TRUE,
                                  version INT DEFAULT 1,
                                  created_at TIMESTAMP DEFAULT NOW(),
                                  updated_at TIMESTAMP DEFAULT NOW()
);