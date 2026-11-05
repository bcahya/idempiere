package org.idempiere.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.DBException;
import org.compiere.util.Env;

public class SIS_Utils {
	
	public static String convertListIntegerToWhereIn(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int a = 0; a < list.size(); a++) {
			if (a > 0) {
				sb.append(", ");
			}
			sb.append(String.valueOf((Integer) list.get(a)));
		}
		return sb.toString();
	}

	public static final PreparedStatement setMultiParam(PreparedStatement ps, List<Object> params) throws SQLException {
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
		return ps;
	}
	
	public static String convertListStringToWhereIn(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (int a = 0; a < list.size(); a++) {
			if (a > 0) {
				sb.append(", ");
			}
			sb.append("'");
			sb.append((String) list.get(a));
			sb.append("'");
		}
		return sb.toString();
	}
	
	public static List<Object> getListSingleObjectFromSQL(StringBuilder sql, List<Object> params) {
		List<Object> listObject = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DB.prepareStatement(sql.toString(), null);
			ps = setMultiParam(ps, params);
			ps.setFetchSize(100);
			rs = ps.executeQuery();
			while (rs.next()) {
				listObject.add(rs.getObject(1));
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		} finally {
			DB.close(rs, ps);
			ps = null;
			rs = null;
		}
		return listObject;
	}

	public static List<Object> getListObjectFromSQL(String sql, List<Object> params) {
		List<Object> listObject = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DB.prepareStatement(sql, null);
			DB.setParameters(ps, params);
			ps.setFetchSize(100);
			rs = ps.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int countColumn = metadata.getColumnCount();
			while (rs.next()) {
				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				for (int a = 1; a <= countColumn; a++) {
					map.put(metadata.getColumnName(a), rs.getObject(metadata.getColumnName(a)));
				}
				listObject.add(map);
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		} finally {
			DB.close(rs, ps);
			ps = null;
			rs = null;
		}
		return listObject;
	}

	public static List<Object> getListObjectFromSQL(String sql, List<Object> params, String trxName) {
		List<Object> listObject = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DB.prepareStatement(sql, trxName);
			DB.setParameters(ps, params);
			ps.setFetchSize(100);
			rs = ps.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int countColumn = metadata.getColumnCount();
			while (rs.next()) {
				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				for (int a = 1; a <= countColumn; a++) {
					map.put(metadata.getColumnName(a), rs.getObject(metadata.getColumnName(a)));
				}
				listObject.add(map);
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		} finally {
			DB.close(rs, ps);
			ps = null;
			rs = null;
		}
		return listObject;
	}

	public static List<Integer> getListIntegerFromSQL(String sql, List<Object> params) {
		List<Integer> listData = new ArrayList<>();
		try {
			for (LinkedHashMap<String, Object> map : getListMapFromSQL(sql, params)) {
				int countKey = 0;
				for (String key : map.keySet()) {
					if (countKey > 1) {
						throw new Exception("Key of map must be 1!");
					}
					countKey += 1;
					if (!(map.get(key) instanceof Integer)) {
						throw new Exception("Value is not Integer!");
					}
					listData.add((int) map.get(key));
				}
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		}
		return listData;
	}
	
	public static List<Integer> getListIntegerFromSQL(String sql, List<Object> params, String trxName) {
		List<Integer> listData = new ArrayList<>();
		try {
			for (LinkedHashMap<String, Object> map : getListMapFromSQL(sql, params, trxName)) {
				int countKey = 0;
				for (String key : map.keySet()) {
					if (countKey > 1) {
						throw new Exception("Key of map must be 1!");
					}
					countKey += 1;
					if (!(map.get(key) instanceof Integer)) {
						throw new Exception("Value is not Integer!");
					}
					listData.add((int) map.get(key));
				}
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		}
		return listData;
	}

	public static String getWhereInFromListInteger(String sql, List<Object> params) {
		return convertListIntegerToWhereIn(getListIntegerFromSQL(sql, params));
	}

	public static List<String> getListStringFromSQL(String sql, List<Object> params) {
		List<String> listData = new ArrayList<>();
		try {
			for (LinkedHashMap<String, Object> map : getListMapFromSQL(sql, params)) {
				int countKey = 0;
				for (String key : map.keySet()) {
					if (countKey > 1) {
						throw new Exception("Key of map must be 1!");
					}
					countKey += 1;
					if (!(map.get(key) instanceof String)) {
						throw new Exception("Value is not String!");
					}
					listData.add((String) map.get(key));
				}
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getMessage());
		}
		return listData;
	}

	public static String getWhereInFromListString(String sql, List<Object> params) {
		return convertListStringToWhereIn(getListStringFromSQL(sql, params));
	}

	public static LinkedHashMap<String, Object> getMapFromSQL(String sql, List<Object> params) {
		List<Object> listObject = getListObjectFromSQL(sql, params);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if (listObject.size() > 0) {
			map = (LinkedHashMap<String, Object>) listObject.get(0);
		}
		return map;
	}

	public static LinkedHashMap<String, Object> getMapFromSQL(String sql, List<Object> params, String trxName) {
		List<Object> listObject = getListObjectFromSQL(sql, params, trxName);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if (listObject.size() > 0) {
			map = (LinkedHashMap<String, Object>) listObject.get(0);
		}
		return map;
	}

	public static List<LinkedHashMap<String, Object>> getListMapFromSQL(String sql, List<Object> params) {
		List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
		List<Object> listObject = new ArrayList<Object>();

		if (!sql.equals("")) {
			listObject = getListObjectFromSQL(sql, params);
		}

		for (Object o : listObject) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = (LinkedHashMap<String, Object>) o;
			resultList.add(map);
		}
		return resultList;
	}

	public static List<LinkedHashMap<String, Object>> getListMapFromSQL(String sql, List<Object> params, String trxName) {
		List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
		List<Object> listObject = new ArrayList<Object>();

		if (!sql.equals("")) {
			listObject = getListObjectFromSQL(sql, params, trxName);
		}

		for (Object o : listObject) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = (LinkedHashMap<String, Object>) o;
			resultList.add(map);
		}
		return resultList;
	}
	
	public static List<Integer> getIncludedRoles(Integer ad_role_id){
		List<Integer> listRoleID = new ArrayList<>();
		listRoleID.add(ad_role_id);
		drillIncludedRole(listRoleID, ad_role_id);
		return listRoleID;
	}
	
	static void drillIncludedRole(List<Integer> listRoleID, int ad_role_id) {
		List<Object> params = new ArrayList<>();
		params.clear();
		params.add(ad_role_id);
		List<Integer> lRoleID = getListIntegerFromSQL(
				"select "
				+ "    ri.included_role_id::int "
				+ "from ad_role_included ri "
				+ "inner join ad_role r "
				+ "  on r.ad_role_id = ri.ad_role_id "
				+ "where ri.ad_role_id = ? "
				+ "and ri.isactive = 'Y' "
				+ "and r.isactive = 'Y' ", params);
		for (int rID: lRoleID) {
			if (!listRoleID.contains(rID)) {
				listRoleID.add(rID);
			}
			drillIncludedRole(listRoleID, rID);
		}
	}
	
	public String getWhereIncludedRole(Integer ad_role_id) {
		return convertListIntegerToWhereIn(getIncludedRoles(ad_role_id));
	}
	
	public static String getSQLAccess(
			String m_tableName,
			boolean isGrid
			) {
		Properties m_ctx = Env.getCtx();
		List<Integer> roleIDs = getIncludedRoles(Env.getAD_Role_ID(m_ctx));
		MTable t = MTable.get(m_ctx, m_tableName);
		String sqlAdd = "";
		
		//[PSI] - 7613 (Document Type Access)
		if (MSysConfig.getBooleanValue("SIS_ActivateAccessDocBasedOnDocTypeAccess", false, Env.getAD_Client_ID(m_ctx)) 
			&& ((isGrid 
					&& t.columnExistsInDB(MOrder.COLUMNNAME_DocStatus)
					&& t.columnExistsInDB(MOrder.COLUMNNAME_DocumentNo)) 
					|| (!isGrid && m_tableName.equalsIgnoreCase(MDocType.Table_Name)))
			) {
			for (int roleID: roleIDs) {
				MRole r = MRole.get(m_ctx, roleID);
				if (r.get_ValueAsBoolean("SIS_IsIgnoreDocTypeAccess")) {
					sqlAdd = "";
					break;
				}
				String colDT = "";
				if (t.columnExistsInDB(MOrder.COLUMNNAME_C_DocTypeTarget_ID)) {
					colDT = MOrder.COLUMNNAME_C_DocTypeTarget_ID;
				} else if (t.columnExistsInDB(MOrder.COLUMNNAME_C_DocType_ID)) {
					colDT = MOrder.COLUMNNAME_C_DocType_ID;
				}
				if (!colDT.equalsIgnoreCase("")) {
					sqlAdd += 
							" AND "+m_tableName+"."+colDT
							+ " IN (SELECT C_DocType_ID " 
						      + "FROM SIS_RoleDocType " 
						      + "WHERE AD_Role_ID = " 
						      + roleID
						      + " AND ISActive = 'Y' " 
						      + ")";
				}
			}
		}
		
		//[PSI] - 7622 (Warehouse Access)
		String sqlAddWH = "";
		if (MSysConfig.getBooleanValue("SIS_ActivateAccessDocBasedOnWarehouseAccess", false, Env.getAD_Client_ID(m_ctx))
				&& ((isGrid 
						&& t.columnExistsInDB(MOrder.COLUMNNAME_DocStatus)
						&& t.columnExistsInDB(MOrder.COLUMNNAME_DocumentNo)) 
						|| (!isGrid && m_tableName.equalsIgnoreCase(MWarehouse.Table_Name)))
			) {
			for (int roleID: roleIDs) {
				MRole r = MRole.get(m_ctx, roleID);
				if (r.get_ValueAsBoolean("SIS_IsIgnoreWarehouseAccess")) {
					sqlAddWH = "";
					break;
				}
				List<String> whs = new ArrayList<String>();
				if (t.columnExistsInDB(MMovement.COLUMNNAME_M_Warehouse_ID)) {
					whs.add(MOrder.COLUMNNAME_M_Warehouse_ID);
				}
				for (String colWH: whs) {
					sqlAddWH += 
							" AND "+m_tableName+"."+colWH
							+ " IN (SELECT m_warehouse_id " 
						      + "FROM SIS_RoleWarehouse " 
						      + "WHERE AD_Role_ID =" 
						      + roleID
						      + " AND ISActive = 'Y' " 
						      + ") ";
				}
			}
			sqlAdd += sqlAddWH;
		}
		return sqlAdd;
	}
	
	public static BigDecimal getFactAmtMR(
			int c_acctschema_id,
			MInOutLine iol
			) {
		int accountID = getProductAccountID(iol, "p_asset_acct", c_acctschema_id);
		BigDecimal amt = getAmtAcct(iol, accountID, c_acctschema_id);
		return amt == null ? Env.ZERO : amt.abs();
	}
	
	public static BigDecimal getFactAmtInv(
			int c_acctschema_id,
			MInvoiceLine il
			) {
		int accountID = 0;
		if (il.getM_Product_ID() > 0) {
			accountID = getProductAccountID(il, "p_expense_acct", c_acctschema_id);
		} else if (il.getC_Charge_ID() > 0){
			MAcctSchema as = MAcctSchema.get(c_acctschema_id);
			accountID = MCharge.getAccount(il.getC_Charge_ID(), as).getAccount_ID();
		}
		BigDecimal amt = getAmtAcct(il, accountID, c_acctschema_id);
		return amt == null ? Env.ZERO : amt.abs();
	}
	
	public static BigDecimal getAmtAcct(
			PO po,
			int accountID,
			int c_acctschema_id) {
		String tableHeader = po.get_TableName().replace("Line", "");
		String colHeader = tableHeader +"_ID";
		MTable tHeader = MTable.get(po.getCtx(), tableHeader);
		int parentID = po.get_ValueAsInt(colHeader);
		return DB.getSQLValueBDEx(po.get_TrxName(),
				"select "
				+ "	fa.amtacctdr - fa.amtacctcr amt "
				+ "from fact_acct fa "
				+ "where fa.isactive = 'Y' "
				+ "and fa.ad_table_id = ? "
				+ "and fa.record_id = ? "
				+ "and fa.line_id = ? "
				+ "and fa.account_id = ? "
				+ "fetch first 1 rows only",
				tHeader.get_ID(),
				parentID,
				po.get_ID(),
				accountID
		);
	}
	
	public static int getProductAccountID(
			PO po,
			String colAcct,
			int c_acctschema_id) {
		return  DB.getSQLValueEx(po.get_TrxName(),
				"select "
				+ "	vc.account_id::int "
				+ "from m_product_acct pa "
				+ "inner join c_validcombination vc "
				+ "	on vc.c_validcombination_id = pa."+colAcct+" "
				+ "where pa.isactive = 'Y' "
				+ "and pa.c_acctschema_id = ? "
				+ "and pa.m_product_id = ? "
				+ "fetch first 1 rows only ",
				c_acctschema_id,
				po.get_ValueAsInt(MProduct.COLUMNNAME_M_Product_ID)
		);
	}
	
	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = Env.ZERO;
		if (value != null) {
			if (value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if (value instanceof String) {
				ret = new BigDecimal((String) value);
			} else if (value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) value);
			} else if (value instanceof Double) {
				ret = new BigDecimal((Double) value);
			} else if (value instanceof Long) {
				ret = new BigDecimal((Long) value);
			} else if (value instanceof Number) {
				ret = new BigDecimal(((Number) value).doubleValue());
			} else if (value instanceof Integer) {
				ret = new BigDecimal(((Integer) value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass()
						+ " into a BigDecimal.");
			}
		}
		return ret;
	}

	public static BigDecimal getBigDecimal(Object value, int scale) {
		return getBigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static Timestamp getTimestampFromString(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Timestamp result = null;
		try {
			result = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			throw new AdempiereException(e.getMessage());
		}
		return result;
	}
	
	public static Timestamp getTimestampFromStringDash(String date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp result = null;
		try {
			result = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			throw new AdempiereException(e.getMessage());
		}
		return result;
	}
	
	public static Timestamp getTimestampTimeFromString(String date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Timestamp result = null;
		try {
			result = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			throw new AdempiereException(e.getMessage());
		}
		return result;
	}
	
	public static Timestamp getTimestampTimeFromStringDash(String date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp result = null;
		try {
			result = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			throw new AdempiereException(e.getMessage());
		}
		return result;
	}
	
	public static String getStringFromTimeStampTime(Timestamp date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String result = sdf.format(date);
		return result;
	}
	
	public static String getStringTitleTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String result = sdf.format(new Date());
		return result;
	}
	
	public static String getStringFromTimeStampTimeDash(Timestamp date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = sdf.format(date);
		return result;
	}
	
	public static Timestamp getTimestampFromObject(Object o) {
		if (o == null) {
			return null;
		}
		return (Timestamp) o;
	}
	
	public static String getStringDate(Timestamp date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getStringTime(Timestamp date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}

	public static String getStringDateDmy(Timestamp date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

	public static String getStringPeriod(Timestamp date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(date);
	}
	
}
