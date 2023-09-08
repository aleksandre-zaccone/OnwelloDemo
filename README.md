# Voting System README

This Java project aims to create a simple voting system with a RESTful API. The project is built using Java 17, Spring Boot, H2 database, Lombok, Spring Data JPA with Hibernate, and Swagger 3 with springdoc.

The instructions lack specific details, which is why I had to make some assumptions.

Assumptions:
- Backend with Rest API: The project is focused on the backend, providing a RESTful API for vote management.
- One-Time Voting ONLY: Each voter is allowed to vote only once.
- Unique Voters and Candidates: A voter and a candidate are considered unique based on their first name and last name combination, for simplicity.
- Custom Exceptions: Custom exception handling has been implemented to manage specific scenarios. Example:
    - VoterAlreadyExistsException: Thrown when attempting to create a voter with an existing name combination.
    - CandidateAlreadyExistsException: Thrown when attempting to create a candidate with an existing name combination.
    - VoterAlreadyVotedException: Thrown when a voter tries to vote more than once.
- Tech Stack: The project utilizes the following technologies:
    - Java 17
    - Spring Boot
    - H2 database
    - Lombok
    - Spring Data JPA with Hibernate
    - Swagger
    - There are no complex security measures or additional validations in place.

## API Endpoints

### Voters Endpoints

**Create Voter**
- Method: POST
- Endpoint: /voteSystem/api/V1/createVoter
- Request Body:
```json
{
  "firstName": "Aleksandre-1", 
  "lastName": "Ablotia", 
  "voted": false
}
```

**Get All Voters**

- Method: GET
- Endpoint: /voteSystem/api/V1/getAllVoters

**Get Voter by ID**

- Method: GET
- Endpoint: /voteSystem/api/V1/getVoterById/1

**Update Voter**

- Method: PUT
- Endpoint: `/voteSystem/api/V1/updateVoter`
- Request Body Example:
```json
{
    "id": 1,
    "firstName": "Aleksandre-UPDATED",
    "lastName": "Ablotia",
    "voted": false
}

```

### Candidate Endpoints

**Create Candidate**

- Method: POST
- Endpoint: `/voteSystem/api/V1/createCandidate`
- Request Body Example:
```json
{
    "firstName": "Isaac",
    "lastname": "Asimov"
}
```

**Get All Candidates**

- Method: GET
- Endpoint: `/voteSystem/api/V1/getCandidates`

**Get Candidate by ID**

- Method: GET
- Endpoint: `/voteSystem/api/V1/getCandidateById/1`

**Update Candidate**

- Method: PUT
- Endpoint: `/voteSystem/api/V1/updateCandidate`
- Request Body Example:
```json
{
    "id": 1,
    "firstName": "Isaak-UPDATED",
    "lastname": "Isimov"
}
```

**Get Vote Count by Candidate ID**

- Method: GET
- Endpoint: `/voteSystem/api/V1/getVoteCountByCandidateId/1`


### Votes Endpoints ###

**Add Vote**
- Method: POST
- Endpoint: `/voteSystem/api/V1/vote/addVote`
- Request Body Example:
 ```json
{
    "candidate": {
        "id": 1,
        "firstName": "Isaac",
        "lastname": "Asimov"
    },
    "voter": {
        "id": 1,
        "firstName": "Aleksandre-1",
        "lastName": "Ablotia"
    }
}
```

