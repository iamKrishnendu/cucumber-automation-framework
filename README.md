# cucumber-framework
This is a Cucumber based automation framework. Compatible for Web and API based test automation

# Project Structure

```
src
|--- main
|------>Java
|           |-api
|           |-constants
|           |-driver
|           |-exception
|           |-fixtures
|           |-pages     // Page elements
|           |-runner
|           |-scenario
|           |-utility   // Customizable utilities
|------->resources
|           |-log4j.properties
|
|--- test
|-------->java.com
|             |hook
|             |sikuli
|             |stepDifinition
|--------->resources
|              |csv                 // External tet data file .csv
|              |features            // Contains test features
|              |cucumber.properties
|              |extent.properties
|              |extent-config.xml
|              |UserResponseSchema.json
________________________________________________
|------>target
|        |SparkReport   // Contains test reports
```

# Run Configuration
`TestNG` 
```
-ea
-Dcucumber.filter.tags="@demoAPI or @demoWeb"  // Demo tags, can be replaced
-DbrowserName=CHROME   // Browser name
-Denvironment=DEMO    // Environment
```
`maven`
```
-Dcucumber.filter.tags=@demoWeb -DbrowserName=CHROME -Denvironment=DEMO
```
