package com.noorteck.qa.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.github.javafaker.Faker;
import com.mysql.cj.Query;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class DataGenerator extends JsonFileUtils {

	public void updateEmpID(String apiName, String region, String indexStr, String id)
			throws JSONException, IOException {
		int index = Integer.parseInt(indexStr);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("employeeId", Integer.parseInt(id));

		modifyTestData(map, apiName, region, index);

	}

	public void gernerateEmployeeData2(String apiName, String region, String indexStr)
			throws JSONException, IOException, SQLException {
		// pick employeeId, managerId, departmentId and jobId from database and assign
		// one of them randomly to the request body.
		int index = Integer.parseInt(indexStr);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Faker fakeData = new Faker();

		DBUtils.connectToDB();
		System.out.println("test");
		Constants.rs = DBUtils.executeQuery(Constants.query4);
		ResultSet rs = Constants.rs;

		String firstName = fakeData.name().firstName();
		String lastName = fakeData.name().lastName();

		String email = firstName + "@test.com";
		String phoneNumber = fakeData.phoneNumber().cellPhone();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		String hireDate = sdf.format(fakeData.date().birthday());
		String jobTitle = fakeData.job().title();
		double minSalary = 10000;
		double maxSalary = 200000;

		double salary = minSalary + (maxSalary - minSalary) * fakeData.random().nextDouble();

		salary = Double.parseDouble(String.format("%.2f", salary));
		double minCommission = 0.0;
		double maxCommission = 0.8;
		double commission = minCommission + (maxCommission - minCommission) * fakeData.random().nextDouble();

		commission = Double.parseDouble(String.format("%.2f", commission));

		while (rs.next()) {
			int depId = rs.getInt("department_id");
			int managerId = rs.getInt("manager_id");
			String jobId = rs.getString("job_id");

			map.put("department_id", depId);
			map.put("managerId", managerId);
			map.put("jobId", jobId);

			map.put("firstName", firstName);
			map.put("lastName", lastName);
			map.put("email", email);
			map.put("salary", salary);
			map.put("hireDate", hireDate);
			map.put("phoneNumber", phoneNumber);
			map.put("comissionPct", commission);
		}

		modifyTestData(map, apiName, region, index);

	}

	public static void gernerateEmployeeData(String apiName, String region, String indexStr)
			throws JSONException, IOException, SQLException {
		int index = Integer.parseInt(indexStr);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Faker fakeData = new Faker();
		// pick employeeId, managerId, departmentId and jobId from database and assign
		// one of them randomly to the request body.
		DBUtils.connectToDB();
		Constants.rs = DBUtils.executeQuery(Constants.query);
		ResultSet rs = Constants.rs;
		while (rs.next()) {
			int empId = rs.getInt("employee_id");
			map.put("employeeId", empId);
		}
		System.err.println(map.get("employeeId"));
		// Map<String, String> emp =
		// DBUtils.getResultSetInListOfMap(Constants.rs).get(0);
		// System.err.println("test");
		// get required fields from result set.
		// String jobId = emp.get("job_id");
		// int managerId = Integer.parseInt(emp.get("manager_id"));
		// int depId = Integer.parseInt(emp.get("department_id"));
		// int empId = Integer.parseInt(emp.get("employee_id"));

		String firstName = fakeData.name().firstName();
		String lastName = fakeData.name().lastName();

		String email = firstName + "@test.com";
		String phoneNumber = fakeData.phoneNumber().cellPhone();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String hireDate = sdf.format(fakeData.date().birthday());
		String jobTitle = fakeData.job().title();
		double minSalary = 10000;
		double maxSalary = 200000;

		double salary = minSalary + (maxSalary - minSalary) * fakeData.random().nextDouble();

		salary = Double.parseDouble(String.format("%.2f", salary));
		double minCommission = 0.0;
		double maxCommission = 0.8;
		double commission = minCommission + (maxCommission - minCommission) * fakeData.random().nextDouble();

		commission = Double.parseDouble(String.format("%.2f", commission));

		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("phoneNumber", phoneNumber);
		map.put("salary", salary);
		map.put("comissionPct", commission);
		map.put("hireDate", hireDate);
		map.put("jobTitle", jobTitle);

		System.out.println("FirstName: " + firstName);
		System.out.println("LastName: " + lastName);
		System.out.println("Email: " + email);
		System.out.println("Phone: " + phoneNumber);
		System.out.println("Date: " + hireDate);
		System.out.println("Salary: " + salary);
		System.out.println("Commission: " + commission);

		modifyTestData(map, apiName, region, index);

	}

	public void gernerateJobData(String apiName, String region, String indexStr)
			throws JSONException, IOException, SQLException {
		int index = Integer.parseInt(indexStr);

		Faker fakeData = new Faker();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		double minSalary = fakeData.number().numberBetween(1000, 2000);
		double maxSalary = fakeData.number().numberBetween(2000, 4000);
//		DBUtils.connectToDB();
//		ResultSet rs = Constants.rs;
//		rs = DBUtils.executeQuery(Constants.query);
//		while (rs.next()) {
//
//		
//		}

		String jobTitle = fakeData.company().industry();
		String jobId = fakeData.company().buzzword();
		System.out.println(jobId);
		map.put("minSalary", minSalary);
		map.put("maxSalary", maxSalary);
		map.put("jobTitle", jobTitle);
		map.put("jobId", jobId);

		modifyTestData(map, apiName, region, index);

	}

	public void gernerateJobData4Patch(String apiName, String region, String indexStr)
			throws JSONException, IOException, SQLException {
		int index = Integer.parseInt(indexStr);
		DBUtils.connectToDB();

		ResultSet rs = Constants.rs;
		rs = DBUtils.executeQuery(Constants.query2);
		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		Faker fakeData = new Faker();

		while (rs.next()) {
			String jobid = rs.getString("job_id");

			map2.put("jobId", jobid);
		}

		String jobTitle = fakeData.company().industry();
		double minSalary = fakeData.number().numberBetween(1000, 2000);
		double maxSalary = fakeData.number().numberBetween(2000, 4000);

		map2.put("minSalary", minSalary);
		map2.put("maxSalary", maxSalary);
		map2.put("jobTitle", jobTitle);
		modifyTestData(map2, apiName, region, index);

	}

	// patch employee
	public void generateEmpData(String apiName, String region, String indexStr)
			throws JSONException, IOException, SQLException {
		int index = Integer.parseInt(indexStr);

		Faker fakeData = new Faker();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		DBUtils.connectToDB();
		ResultSet rs = Constants.rs;
		rs = DBUtils.executeQuery(Constants.query);
		while (rs.next()) {
			int empId = rs.getInt("employee_id");
			map.put("employeeId", empId);

		}
		String phoneNumber = fakeData.phoneNumber().cellPhone();
		String firstName = fakeData.name().firstName();
		String lastName = fakeData.name().lastName();

		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("phoneNumber", phoneNumber);

		modifyTestData(map, apiName, region, index);

	}
}
