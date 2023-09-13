-- DROP TABLE IF EXISTS doctors CASCADE;
-- DROP TABLE IF EXISTS patients CASCADE;
-- DROP TABLE IF EXISTS coupons CASCADE;

CREATE TABLE IF NOT EXISTS doctors
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name VARCHAR(50)                             NOT NULL,
    last_name  VARCHAR(50)                             NOT NULL,
    CONSTRAINT pk_doctors PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS patients
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name    VARCHAR(50)                             NOT NULL,
    last_name     VARCHAR(50)                             NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_patients PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS coupons
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    doctor_id            BIGINT                                  NOT NULL,
    patient_id           BIGINT                                  NOT NULL,
    reception_start_time TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    reception_end_time   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_coupons PRIMARY KEY (id),
    CONSTRAINT fk_coupons_to_doctors FOREIGN KEY (doctor_id) REFERENCES doctors (id),
    CONSTRAINT fk_coupons_to_patients FOREIGN KEY (patient_id) REFERENCES patients (id)
);
