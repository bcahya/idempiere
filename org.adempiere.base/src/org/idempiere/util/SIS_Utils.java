package org.idempiere.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
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
import org.compiere.util.Env;

public class SIS_Utils {
	
	public static String getSQLAccess(
			String m_tableName,
			boolean isGrid
			) {
		Properties m_ctx = Env.getCtx();
		MRole r = MRole.get(m_ctx, Env.getAD_Role_ID(m_ctx));
		MTable t = MTable.get(m_ctx, m_tableName);
		String sqlAdd = "";
		
		//[PSI] - 7613 (Document Type Access)
		if (MSysConfig.getBooleanValue("SIS_ActivateAccessDocBasedOnDocTypeAccess", false, Env.getAD_Client_ID(m_ctx)) 
			&& !r.get_ValueAsBoolean("SIS_IsIgnoreDocTypeAccess") 
			&& ((isGrid 
					&& t.columnExistsInDB(MOrder.COLUMNNAME_DocStatus)
					&& t.columnExistsInDB(MOrder.COLUMNNAME_DocumentNo)) 
					|| (!isGrid && m_tableName.equalsIgnoreCase(MDocType.Table_Name)))
			) {
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
						      + "WHERE AD_Role_ID=" 
						      + r.get_ID()
						      + " AND ISActive = 'Y' " 
						      + ")";
				}
		}
		
		//[PSI] - 7622 (Warehouse Access)
		if (MSysConfig.getBooleanValue("SIS_ActivateAccessDocBasedOnWarehouseAccess", false, Env.getAD_Client_ID(m_ctx))
				&& !r.get_ValueAsBoolean("SIS_IsIgnoreWarehouseAccess")
				&& ((isGrid 
						&& t.columnExistsInDB(MOrder.COLUMNNAME_DocStatus)
						&& t.columnExistsInDB(MOrder.COLUMNNAME_DocumentNo)) 
						|| (!isGrid && m_tableName.equalsIgnoreCase(MWarehouse.Table_Name)))
			) {
			List<String> whs = new ArrayList<String>();
			if (t.columnExistsInDB(MMovement.COLUMNNAME_M_Warehouse_ID)) {
				whs.add(MOrder.COLUMNNAME_M_Warehouse_ID);
			}
			for (String colWH: whs) {
				sqlAdd += 
						" AND "+m_tableName+"."+colWH
						+ " IN (SELECT m_warehouse_id " 
					      + "FROM SIS_RoleWarehouse " 
					      + "WHERE AD_Role_ID=" 
					      + r.get_ID()
					      + " AND ISActive = 'Y' " 
					      + ") ";
			}
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
	
}
