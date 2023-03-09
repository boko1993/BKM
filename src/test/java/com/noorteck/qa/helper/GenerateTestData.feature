Feature: Set up Headers

  @generateEmpData
  #CREATE NEW EMPLOYEE
  Scenario: Headers with no Authentication
    * def javaClassObject = Java.type('com.noorteck.qa.utils.DataGenerator')
    * print fileName
    * def result = new javaClassObject().gernerateEmployeeData2(fileName, region,index)

  @updateEmpId
  Scenario: Headers with no Authentication
    * def javaClassObject = Java.type('com.noorteck.qa.utils.DataGenerator')
    * print fileName
    * def result = new javaClassObject().updateEmpID(fileName, region,index, empId)

  @gernerateJobData
  Scenario: Headers with no Authentication
    * def javaClassObject = Java.type('com.noorteck.qa.utils.DataGenerator')
    * print fileName
    * def result = new javaClassObject().gernerateJobData(fileName, region,index)

  @generateJobData2
  Scenario: Headers with no Authentication
    * def javaClassObject = Java.type('com.noorteck.qa.utils.DataGenerator')
    * print fileName
    * def result = new javaClassObject().gernerateJobData4Patch(fileName, region,index)

  @gernerateEmployeeData
  #for patch employee
  Scenario: Headers with no Authentication
    * def javaClassObject = Java.type('com.noorteck.qa.utils.DataGenerator')
    * print fileName
    * def result = new javaClassObject().generateEmpData(fileName, region,index)
