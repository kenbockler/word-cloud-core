CREATE TABLE IF NOT EXISTS textfiles (
                                     id UUID PRIMARY KEY,
                                     filename TEXT NOT NULL,
                                     file_size BIGINT NOT NULL,
                                     upload_time TIMESTAMP NOT NULL,
                                     status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS words (
                                     id SERIAL PRIMARY KEY,
                                     textfile_id UUID NOT NULL REFERENCES textfiles(id),
                                     word TEXT NOT NULL,
                                     count INTEGER NOT NULL
);

-- Indeksi loomine words tabelis olevatele sõnadele jõudluse parandamiseks
CREATE INDEX IF NOT EXISTS idx_words_word ON words (word);

