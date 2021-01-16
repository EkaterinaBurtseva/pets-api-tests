# General info
Test automation solution to verify https://petstore.swagger.io/#/

# Environment
* Java 8+
* Gradle

# Additional plugins 
* Lombok
* Rest-assured
* AssertJ
* Cucumber
* Serenity
* Gitlab CI

# Execution
* locally via IDE
* using Gradle
```
gradle clean test aggregate
```
* via gitlab CI

# Reporting and demo
Latest serenity report:
Latest job results from the CI: 
Demo available here 


#TODO
* extend current test coverage
* reorganize project with division of stepdefinitions and feature file
* use Allure + Cucumber + Junit(TestNG) (if BDD needed) instead of Serenity
* add more detailed logging by @Slf4j

# Troubleshooting

- make sure that JDK at least java8
- clean gradle daemon
```
gradle --stop
```
- invalidate cache and restart
- try 
```
gradle build
```

