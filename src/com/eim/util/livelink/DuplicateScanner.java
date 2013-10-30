package com.eim.util.livelink;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.eim.util.format.XmlEncoder;

@SuppressWarnings("unchecked")
public class DuplicateScanner {

	public static Connection connection;
	public static final String movedTo = "Enterprise:Fund Documents:TempBackup";

	public static ArrayList<Long> getIdList() throws SQLException {
		ArrayList<Long> results = new ArrayList<Long>();

		Statement statement = connection.createStatement();
		statement
				.execute("select dataid from vg_fund_documents where doctype not in('MONTHLY REPORT (CLIENT)', 'MONTHLY REPORT (RESEARCH)','MONTHLY STAT', 'MANAGER FACT SHEET','AUDITED FINANCIALS','MANAGER REPORT' ) and createdby='1000' and parentid<>'2064' having count(*)>1 group by dataid");
		ResultSet resultset = statement.getResultSet();
		while (resultset.next()) {
			results.add(new Long(resultset.getLong(1)));
		}
		resultset.close();
		statement.close();
		return results;
	}

	public static void saveAsFile(HashMap<Long, ArrayList<String>> map, String filename)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		for (Iterator<Long> iterator = map.keySet().iterator(); iterator.hasNext();) {
			Long key = iterator.next();
			String output = key.toString() + ":";
			for (Iterator values = map.get(key).iterator(); values
					.hasNext();) {
				output = output + values.next() + " ";
			}
			bw.write(output.trim() + "\r\n");
		}
		bw.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Starting analysis...");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@kessel2:1521:DW", "G_OTPROD", "OTPROD");
//					"jdbc:oracle:thin:@sump2:1521:TESTDB", "G_OT_TEST3", "OT_TEST3");
			HashMap<Long, ArrayList<String>> badList = new HashMap<Long, ArrayList<String>>();
			ArrayList<String> deleteList = new ArrayList<String>();
			ArrayList<String> moveList = new ArrayList<String>();
			for (Iterator<Long> iterator = DuplicateScanner.getIdList().iterator(); iterator
					.hasNext();) {
				String previous = null;
				String filenetId = null;
				Statement statement = connection.createStatement();
				Long id = iterator.next();
				statement
						.execute("select filename from vg_fund_documents where dataid='"
								+ id + "'");
				ResultSet resultset = statement.getResultSet();
				while (resultset.next()) {
					String filename = resultset.getString(1);
					if (filename.startsWith("[")) {
						if (filename.indexOf('_')!=-1) {
							filenetId = filename
								.substring(1, filename.indexOf('_'));
							if (previous == null) {
								previous = filenetId;
							} else {
								if (!previous.equals(filenetId)) {
									if (badList.get(id) == null) {
										badList.put(id, new ArrayList<String>());
									}
									badList.get(id).add(previous);
									previous = filenetId;
								}
							}
						}
					}
				}
				if (badList.get(id) != null) {
					badList.get(id).add(previous);
					deleteList.add(buildDeletedString(id));
					moveList.add(buildMovedString(id));
				}
				resultset.close();
				statement.close();
			}
			connection.close();
			System.out.println("Main request done, now processing...");
			saveAsFile(badList, "C:\\temp\\badlistProd.txt");
			System.out
					.println("Main export done, now creating deletion file...");
			saveAsFile(deleteList, "C:\\temp\\deletelistProd.xml");
			saveAsFile(moveList, "C:\\temp\\movelistProd.xml");
			System.out.println("Work done\r\nBad documents:" + badList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void saveAsFile(ArrayList<String> list, String filename)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><LIVELINK><LIBRARY>\n");
		for (Iterator<String> values = list.iterator(); values.hasNext();) {
			bw.write(values.next().toString() + "\r\n");
		}
		bw.write("</LIBRARY><POPULATION/></LIVELINK>\n");
		bw.close();
	}

	private static String buildMovedString(Long id) throws SQLException {
		StringBuffer result = new StringBuffer("<NODE TYPE=\"DOCUMENT\" FUNC=\"MOVE\" SOURCE=\"" + getLocation(id) + ":" + getName(id) + "\"><SYSTEM.ATTRIBUTES><ATTRIBUTE><INT.NAME>LOCATION</INT.NAME><VALUE>");
		result.append(movedTo);
		result.append("</VALUE></ATTRIBUTE><ATTRIBUTE><INT.NAME>NAME</INT.NAME><VALUE>");
		result.append("[" + id.toString() + "]" + getName(id));
		result.append("</VALUE></ATTRIBUTE></SYSTEM.ATTRIBUTES></NODE>\r\n");
		return result.toString();
	}
	
	private static String buildDeletedString(Long id) throws SQLException {
		StringBuffer result = new StringBuffer(
				"<NODE TYPE=\"DOCUMENT\" FUNC=\"DELETE\"><SYSTEM.ATTRIBUTES><ATTRIBUTE><INT.NAME>LOCATION</INT.NAME><VALUE>");
		result.append(movedTo);
		result.append("</VALUE></ATTRIBUTE><ATTRIBUTE><INT.NAME>NAME</INT.NAME><VALUE>");
		result.append("[" + id.toString() + "]" + getName(id));
		result.append("</VALUE></ATTRIBUTE></SYSTEM.ATTRIBUTES></NODE>\r\n");
		return result.toString();
	}

	private static String getName(Long id) throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("select name from vg_fund_documents where dataid='"
				+ id + "'");
		ResultSet resultset = statement.getResultSet();
		String res = "";
		if (resultset.next()) {
			res = resultset.getString(1);
		}
		resultset.close();
		statement.close();
		return  XmlEncoder.encode(res);
	}

	private static String getLocation(Long id) throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("select parentid from vg_fund_documents where dataid='"
						+ id + "'");
		ResultSet resultset = statement.getResultSet();
		String location = "";
		if (resultset.next()) {
			String parent = resultset.getString(1);
			resultset.close();
			statement.close();
			while (!parent.equals("2000")) {
				Statement history = connection.createStatement();
				history
						.execute("select name,parentid from dtree where dataid='"
								+ parent + "'");
				ResultSet histRes = history.getResultSet();
				if (histRes.next()) {
					location = histRes.getString(1) + ":" + location;
					parent = histRes.getString(2);
					if (parent.startsWith("-")) {
						parent = parent.substring(1);
					}
				}
				histRes.close();
				history.close();
			}
			location = "Enterprise:" + location;
		}
		return  XmlEncoder.encode(location.substring(0, location.length() - 1));
	}

}
