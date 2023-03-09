@SmokeTest
Feature: udpate Employee Functionality

  Background: 
    * def filePath = read(file_path)
    * def testDataFilePath = filePath.EmpTestData
    * def hrServerData = read(hrApiServerData)
    * def baseURL = env == 'scrum' ? hrServerData.baseScrumURL : hrServerData.baseSitURL
    * def getHeaderInfo = env == 'scrum' ? filePath.Headers+'@scrum' : filePath.Headers+'@sit'
    * def setHeader = call read(getHeaderInfo)
    * configure headers = setHeader.HEADER

  #1.Creating new resource POST REQUEST   # data is your test data => fill => request-body is your empty template for the request body
  Scenario Outline: Verify User able to update new Employee
    * def apiURI = hrServerData.Employee.PatchExistingEmp
    * def endpoint = baseURL+apiURI
    * def generateTestData = call read(filePath.GenerateTestData+'@gernerateEmployeeData'){fileName:'#(testDataFilePath)', region: '<region>', index: '<index>'}
    * def data = read('classpath:'+filePath.EmpTestData)
    * def request_body = read('classpath:'+ filePath.EmpPutRBody)
    * set request_body.employeeId = data.<region>[<index>].employeeId
    * set request_body.firstName = data.<region>[<index>].firstName
    * set request_body.lastName = data.<region>[<index>].lastName
    * set request_body.phoneNumber = data.<region>[<index>].phoneNumber
    * print request_body
    Given url endpoint
    And request request_body
    When method PATCH
    * print response
    Then status <status_code>
    And match response.message == <exp_messsage>
    * def updateTestData = call read( filePath.GenerateTestData+'@updateEmpId'){fileName:'#(testDataFilePath)', region: '<region>', index: '<index>', empId:'#(request_body.employeeId)' }

    @scrum
    Examples: 
      | region | index | status_code | exp_messsage           |
      | scrum  |     0 |         200 | 'Successfully updated' |

    @sit
    Examples: 
      | region | index | status_code | exp_messsage            |
      | sit    |     0 |         200 | 'Successfully updated'  |
