--  Add sample dummy entities to test APIs
INSERT INTO hospital(name, city, country)
VALUES ('Afyonkarahisar State Hospital', 'Afyon', 'Turkey'),
       ('Tuzla State Hospital', 'Istanbul', 'Turkey'),
       ('Istituto Clinico Humanitas', 'Milan', 'Italy'),
       ('Acibadem Private Hospital', 'Ankara', 'Turkey'),
       ('Policlinico Universitario Gemelli', 'Rome', 'Italy'),
       ('Karolinska Universitetssjukhuset', 'Solna', 'Sweden');


INSERT INTO patient(ssn, name, lastname, gender, age, symptoms, hospital_id)
VALUES ('11223344551', 'John', 'Doe', 'Male', 32, 'Cough-Fever or chills-Sore throat-Congestion or runny nose', 1),
       ('11223344552', 'Emily', 'Zoe', 'Female', 25, 'Headache-Fatigue', 2),
       ('11223344553', 'Johnny', 'Moe', 'Male', 90, 'Cough-Fever or chills-Sore throat-Congestion or runny nose', 3),
       ('11223344554', 'Remmy', 'Handson', 'Male', 75, 'Headache-Fever or chills-Sore throat', 1),
       ('11223344555', 'Herry', 'Doe', 'Male', 45, 'New loss of taste or smell-Fever or chills-Sore throat', 1),
       ('11223344556', 'Gabby', 'Doe', 'Female', 38, 'Diarrhea-Fever or chills-Congestion or runny nose', 4),
       ('11223344557', 'Clie', 'Doe', 'Female', 17, 'Diarrhea-Fever or chills-Sore throat', 5),
       ('11223344558', 'Forte', 'Doe', 'Female', 15, 'Cough-Fever or chills-Sore throat-Congestion or runny nose', 5),
       ('11223344559', 'Micheal', 'Doe', 'Female', 47, 'Nausea or vomiting-Sore throat:Cough', 5),
       ('11223344550', 'Fardo', 'Doe', 'Male', 34, 'Cough-Fever or chills-Sore throat-Congestion or runny nose', 2);