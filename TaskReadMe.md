# Dell Technologies Automation Task
## The solution Implemented with Selenium and Java 
- For the testcases check <od> LoginScenarioTescases.xlsx </od> 
- All dependencies used in the <od> pom.xml </od> file if you face any dependencies issues just run maven clean then maven install in the pom file.
1) Open Constant Class in package (com.saucedemo.DellTechnologiesTask).
2) Change the path variables  to be as paths on which you have cloned the repo.
3) To run the testcases of login scenario, right click in the LoginScenario class in package       (com.saucedemo.DellTechnologiesTask.testpackage) then run as TestNG.
4) To run the whole suite to try the three different platforms (chrome,firefox and microsoft edge), open testng.xml then right       click and run as TestNG.

## Notes: 

- To try different values from the testdata just change the testdataColumnNumber variable in Constant Class.
- You can cover all of testcases by changing the regex variables in the Constant Class Or you can add the same testcase with     different parameters in the testng suite file (testng.xml) and use a paramter annotation for this testcase like i did in the     browser parameter.
- There is also Listener class to take specfic actions on specific cases like invoking take screenshot on failure.
- You will find the screenshots of the executions in the screenshots folder inside the repo.
- You will find allure-report folder. if you face any issue with opening allure report, watch this video
  https://www.youtube.com/watch?v=5b4ZMAjHitw&t=1314s

