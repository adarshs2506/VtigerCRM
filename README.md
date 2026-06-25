# Vtiger CRM Automation Framework

## Overview
This project is an end-to-end automation framework developed for Vtiger CRM using Selenium WebDriver, Java, TestNG, Maven, and Jenkins. 
The framework follows the Page Object Model (POM) design pattern and supports Data-Driven Testing.

## Tech Stack

Java
Selenium WebDriver
TestNG
Maven
Jenkins
Apache POI
JSON
Extent Reports
Page Object Model (POM)
Git & GitHub

## Framework Features

✔ Page Object Model (POM)

✔ Data-Driven Testing using Excel

✔ Cross Browser Support

✔ Generic Utility Classes

✔ Business Utility Classes

✔ TestNG Framework

✔ Extent Report Integration

✔ Screenshot Capture on Failure

✔ Listener Implementation

✔ JSON-based Configuration

✔ Maven Project Structure

✔ Jenkins Continuous Integration (CI)

✔ GitHub Integration

## Project Structure

Project Structure
-----------------
<img width="443" height="805" alt="Screenshot 2026-06-25 211808" src="https://github.com/user-attachments/assets/f9391e1a-dad6-42e7-9d50-4393ca429fc5" />

## Modules Automated

- Login
- Organizations
- Contacts
- Leads
- Opportunities
- Products
- Documents
- Cases

## Design Patterns Used

- Page Object Model (POM)
- Utility Design Pattern

## Prerequisites

Before running the project, ensure the following are installed:

Java JDK 25 or above
Maven
Eclipse IDE
Jenkins
Google Chrome Browser

## Configuration

Update the configuration details in:

`commonData.json`

Example:

```json
{
  "url":"http://localhost:8888",
  "un":"admin",
  "pwd":"****",
  "bro":"chrome"
}
```
## Running the Tests
Run Individual Test

Run As → TestNG Test

Run Complete Suite
by-testng.xml

## Running Through Jenkins

Configure Jenkins with GitHub repository.
Configure Maven in Jenkins.
Add the Maven goal:
-f vtiger-automation-framework/pom.xml clean test
Click Build Now to execute the complete automation suite.

## Reports

Extent Reports are generated automatically under:
advance_reports/

Screenshots for failed test cases are stored under:
screenshots/

## Author
Adarsh Singh
