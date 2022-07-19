# Technical Assessment - Hreem IT
Hello and welcome to this technical assessment! If you are here, then it means that you were selected as a potential candidate for Hreem IT. 
This technical assessment is aimed towards testing your problem solving ability from different verticals, i.e software development, development and operations. 
The assessment should not take more than 1-2 hours to finish.

*A good technical assessment should:*
- Be language and framework agnostic
- Should aim to assert how well a candidate is able to adapt to change, cope with challenges and willing to learn new technologies.

**Please clone this repository and commit the completed assessment into your own branch! On completion, PR the changes towards the master branch!**

## Problem description
Your client is working in the medical sector, a new virus breakout has just occurred VOCID-22 *(god forbid lol)* and patients are pouring in to hospitals seeking medical aid, with a wide range of symptoms.
The public and private hospitals have requested your client to quickly create a public API which can be used by all sectors to log and track the pandemic, by allowing nurses and doctors to log patient details into the system.   
You are tasked with creating a simple yet scalable public API, and deploy it out to a public cloud. The API should allow logging data, as well as allow fetching data by hospital name, symptom and/or fetch all records in the database.

The data that needs to be captured is the following:
- Patient Data:
  - social security number
  - name and surname
  - age
  - symptoms
  - name of the hospital that the patient is admitted at
  
  

## Checklist  

- Develop an API according to the requirements above.
- Package and deploy the artifact out to a public cloud (AWS/GCP or Azure)
- Expose the compute resource / artifact with a public endpoint 
- Write a small markdown document called `solution.md`, where you:
  - Briefly motivate your selection of technologies, and how that solves the requirement of being (quick, public, scalable and easily adaptable)
  - Detail the public API endpoint.
