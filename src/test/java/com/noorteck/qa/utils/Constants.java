package com.noorteck.qa.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Constants {

	public final static String CLASSNAME = "com.mysql.cj.jdbc.Driver";

	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet rs = null;
	public static List<LinkedHashMap<String, String>> resultsetInListOfMaps = new ArrayList<>();
	public static Map<String, Object> envDataMap = new LinkedHashMap<String, Object>();
	public static String query = "SELECT job_id FROM hr_scrum.employees WHERE job_id IS NOT NULL ORDER BY RAND() limit 1 ;";
	public static String query2 = "SELECT job_id FROM hr_scrum.jobs WHERE job_id Is not null order by rand() limit 1;";
	public static String query3 = "SELECT manager_id,department_id,job_id FROM hr_scrum.employees WHERE manager_id IS NOT NULL ORDER BY RAND() limit 1;";
	public static String query4 = "SELECT * FROM hr_scrum.employees WHERE manager_id IS NOT NULL ORDER BY RAND() limit 1;";

}
