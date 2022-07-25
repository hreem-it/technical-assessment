CREATE TABLE IF NOT EXISTS hospital
(
    id      INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(150) NOT NULL,
    city    VARCHAR(25)  NOT NULL,
    country VARCHAR(25)  NOT NULL
);

CREATE TABLE IF NOT EXISTS patient
(
    id          INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    ssn         VARCHAR(25) UNIQUE NOT NULL,
    name        VARCHAR(25)        NOT NULL,
    lastname    VARCHAR(25)        NOT NULL,
    gender      VARCHAR(10),
    age         INT DEFAULT 0,
    symptoms    VARCHAR(250),
    hospital_id INT                NOT NULL,
    FOREIGN KEY (hospital_id) REFERENCES hospital (id)
);