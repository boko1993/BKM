@US111
Feature: Job-api

  Background: 
    * def filePath = read(file_path)
    * def testDataFilePath = filePath.JobTestData
    * def hrServerData = read(hrApiServerData)
    * def baseURL = env == 'scrum' ? hrServerData.baseScrumURL : hrServerData.baseSitURL
    * def getHeaderInfo = env == 'scrum' ? filePath.Headers+'@scrum' : filePath.Headers+'@sit'
    * def setHeader = call read(getHeaderInfo)
    * configure headers = setHeader.HEADER

  #1.Update a job
  Scenario Outline: Verify User able to create a new job
    * def apiURI = hrServerData.Job.PostNewJob
    * def endpoint = baseURL+apiURI
    * def generateTestData = call read(filePath.GenerateTestData+'@gernerateJobData'){fileName:'#(testDataFilePath)', region: '<region>', index: '<index>'}
    * def data = read('classpath:'+filePath.JobTestData)
    * def request_body = read('classpath:'+ filePath.JobPostRBody)
    * set request_body.jobId = data.<region>[<index>].jobId
    * set request_body.jobTitle = data.<region>[<index>].jobTitle
    * set request_body.minSalary = data.<region>[<index>].minSalary
    * set request_body.maxSalary = data.<region>[<index>].maxSalary
    * print request_body
    Given url endpoint
    And request request_body
    When method POST
    Then status <status_code>
    And match response.message == <exp_messsage>
    * print response
    * def updateTestData = call read(filePath.GenerateTestData+'@gernerateJobData'){fileName:'#(testDataFilePath)', region: '<region>', index: '<index>', jobId:'#(jobId)'}

    @scrum
    Examples: 
      | region | index | status_code | exp_messsage           |
      | scrum  |     0 |         200 | 'Successfully created' |
