package org.compiere.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MChangeLog;
import org.compiere.model.MIssue;
import org.compiere.model.MSysConfig;

public class SIS_DBChangeLogIssue {
	
	static String SIS_DB_CHANGELOG_URL = "SIS_DB_CHANGELOG_URL";
	static String SIS_DB_CHANGELOG_USERNAME = "SIS_DB_CHANGELOG_USERNAME";
	static String SIS_DB_CHANGELOG_PASSWORD = "SIS_DB_CHANGELOG_PASSWORD";
	public static String SIS_MOVE_CHANGELOG = "SIS_MOVE_CHANGELOG";
	
	private static CLogger			log = CLogger.getCLogger (SIS_DBChangeLogIssue.class);
	
	public static List<String> listTableChangelogIssue(){
		List<String> listTableName = new ArrayList<String>();
		listTableName.add(MChangeLog.Table_Name);
		listTableName.add(MIssue.Table_Name);
		
		return listTableName;
	}
	
	public static boolean isCLI(String tableName) {
		return (MSysConfig.getBooleanValue(SIS_DBChangeLogIssue.SIS_MOVE_CHANGELOG, false)
				&& (SIS_DBChangeLogIssue.listTableChangelogIssue().contains(tableName)));
	}
	
	public static Connection getConnectionCLI() {
		// open connection to replica
		Connection conn = null;
		String conURL = MSysConfig.getValue(SIS_DB_CHANGELOG_URL, "");
		String conUsername = MSysConfig.getValue(SIS_DB_CHANGELOG_USERNAME, "");
		String conPassword = MSysConfig.getValue(SIS_DB_CHANGELOG_PASSWORD, "");
		try {
			conn = DB.getDatabase(conURL).getDriverConnection(conURL, conUsername, conPassword);
		} catch (SQLException e) {
			log.warning("Could not get a connection to " + conURL + ", cause = " + e.getLocalizedMessage());
			conn = null;
		}
		return conn;
	}
	
	public static void executeUpdateCLI(String sql, List<Object> params){
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = getConnectionCLI();
			ps = con.prepareStatement(sql);
			ps.setFetchSize(100);
			setMultiParam(ps, params);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AdempiereException(e.getMessage());
		} finally{
			ps = null;
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setMultiParam(PreparedStatement ps, List<Object> params)
            throws SQLException {
        int index = 0;
        for (Object param : params) {
            index++;
            if (param == null)
                ps.setObject(index, null);
            else if (param instanceof String)
                ps.setString(index, (String) param);
            else if (param instanceof Integer)
                ps.setInt(index, ((Integer) param).intValue());
            else if (param instanceof BigDecimal)
                ps.setBigDecimal(index, (BigDecimal) param);
            else if (param instanceof Timestamp)
                ps.setTimestamp(index, (Timestamp) param);
            else if (param instanceof Boolean)
                ps.setString(index, ((Boolean) param).booleanValue() ? "Y" : "N");
            else if (param instanceof byte[])
                ps.setBytes(index, (byte[]) param);
            else
                throw new DBException("Unknown parameter type " + index + " - " + param);
        }
    }

}
