# Running a Kotlin Script with RestAssured

This project demonstrates how to run test cases with RestAssured library to make HTTP requests to a REST API endpoint.

## Prerequisites

- JDK 8 or higher installed on your machine.
- Kotlin compiler installed on your machine.
- Internet connectivity to access the API.

## Running the Script

1. Clone the repository to your local machine:

    ```bash
    git clone TestTaskWiki
    ```

2. Navigate to the project directory:

    ```bash
    cd <project-directory>
    ```

3. Build the project: 
    ```bash
    ./gradlew build
    ```

Configuration

Before running the tests, make sure to set up the following configurations:
Open src/main/resources/test.properties
Update the ClientID and ClientSecret properties with your API credentials.(ApiKey should be created in Wikipedia API Dashboard)

Running Tests

To run the tests, execute the following command:

1. Build the project:

    ```bash
    ./gradlew clean test
    ```

Test Results
After running the tests, you can view the test results in the console. Additionally, detailed test reports can be found in the build/reports/tests directory.

## Further Information

For more information on RestAssured and Kotlin, please refer to the official documentation:

- [RestAssured Documentation](https://github.com/rest-assured/rest-assured/wiki/Usage)
