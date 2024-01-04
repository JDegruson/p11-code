CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    hospital_name VARCHAR(255) NOT NULL,
    speciality VARCHAR(255) NOT NULL,
    time TIMESTAMP NOT NULL
);