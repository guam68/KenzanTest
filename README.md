# KenzanTest

<br/><br/>

| Method | URI | Description | Requires Body | Requires Auth |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| GET | http://3.13.2.32:8080/EmployeeProcessor/api/employees| Retrieves full list employees, excluding those marked as inactive | `false` | `false` | 
| GET | http://3.13.2.32:8080/EmployeeProcessor/api/employees/{id} | Retrieves an employee with specified id or returns 404 if inactive or not found | `false` | `false` |
| POST | http://3.13.2.32:8080/EmployeeProcessor/api/employees | Creates new employee. Must contain a first and last name at minimum. Defaults status to active | `true` | `false` |
| PUT | http://3.13.2.32:8080/EmployeeProcessor/api/employees/{id} | Updates employee with specified id. Must contain a first and last name at minimum. Fields not specified in body of request will be nulled | `true` | `false` |
| DELETE | http://3.13.2.32:8080/EmployeeProcessor/api/employees/{id} | Returns a 204 if the employee is set inactive or 404 if there is no employee  with the specified id | `false` | `true` |

---

<br/><br/>

## Authentication for POST request:
This application uses Basic Authentication. If using Postman, the following may be used to authenticate:

- username: kenzan
- password: kenzan

If using a browser, please include this key/value pair in your request header:
> Authorization: Basic a2VuemFuOmtlbnphbg==

<br/><br/>

## Example POST/PUT Request Body:
```json
{
    "firstName": "Create",
    "middleInitial": "A",
    "lastName": "Test",
    "dateOfBirth": "1999-02-22",
    "dateOfEmployment": "2019-01-11",
    "status": true
}
```

> Note that regardless of request status setting, status will be set to ```true``` at time of creation for POST requests. Date format is YYYY-MM-DD.

<br/><br/>

Employee fields
---
<pre>
ID - Unique identifier for an employee
firstName - Employees first name
middleInitial - Employees middle initial
lastName - Employeed last name
dateOfBirth - Employee birthday and year
dateOfEmployment - Employee start date
status - true or false
</pre>

## Database at time of submission
<pre>
+----+------------+----------------+-----------+---------------+--------------------+--------+
| id | first_name | middle_initial | last_name | date_of_birth | date_of_employment | status |
+----+------------+----------------+-----------+---------------+--------------------+--------+
|  1 | James      | E              | Taylor    | 1984-10-12    | 2015-01-30         |      0 |
|  2 | Sarah      | NULL           | Conner    | 1967-04-24    | 2018-04-13         |      1 |
|  3 | Paul       | R              | West      | 1990-11-02    | 2019-06-12         |      0 |
|  4 | Johnny     | A              | Adams     | 1990-10-09    | 2018-12-31         |      1 |
|  5 | John       | B              | Badams    | 2000-11-10    | 2018-02-01         |      1 |
|  6 | Jonathon   | C              | Cadams    | 2010-12-11    | 2017-03-02         |      1 |
+----+------------+----------------+-----------+---------------+--------------------+--------+
</pre>


<br/><br/>

## Loading of external file on startup

As the application has already been deployed, it won't be possible to observe the database changes so I will briefly cover the process here.

The app contains a service class CsvService. Using an @PostConstruct annotation, an init method is invoked prior to the class being put into service. This method uses the FasterXML/Jackson library to parse out a csv file's headers into the schema to use, map the data to Employee objects, and persist the objects to the database using the EmployeeService class. The method looks for a file named employees.csv located under src/main/resoureces/assets/csv.

<br/><br/>

## Techonlogies Used

- Java 8
- MySQL
- Spring Boot
- Gradle
- Tomcat
- AWS
- FasterXML / Jackson
- Spring Tool Suite
- Postman
- MAMP
- MySQLWorkbench

<br/><br/>

## Lessons / Takeaways

- Probably the biggest takeaway was to not blindly use the latest dependency. I ran into the issue that when deploying to AWS, the app would go into a lockup state that prevented it to be run. The problem ended up being me using the latest dependency for Jackson's csv formatter. Reverting to an older version fixed the problem. I ended up spending more time troubleshooting that issue than the whole rest of the project I think. 

- While writing this README, I'm noticing that my use of a boolean for the status field doesn't really make sense. Having a status of `true` is strange to read and might cause confusion. If I'd paid a bit more attention to the instructions, I'd imagine an ENUM would have been a better fit here. Either that or change the field name to "IsActive" or something similar.

- The instructions state to use one or more design pattern and comment why those design choices were made. The fact that all I know is that REST itself is a design pattern and that I separate logic through the use of controllers, service layers, etc is maybe a bit telling of my experience level. Going forward, this is definetly an area to focus on. Knowing the why behind certain implementations is important as is knowing the pros and cons of alternative methodologies.

- Loading a file at startup and authenticating requests were both new to me. Through implementing them, I was able to learn about the @PostConstruct and @EventListener annotations, dive deeper into using Jackson to handle csv files, and learn how to use Spring Security to vary permission levels throughout the application using Basic Authentication. Spring Security is pretty cool and I'll be looking into OAuth implementation going forward.
