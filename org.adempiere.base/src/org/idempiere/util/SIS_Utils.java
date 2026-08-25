package org.idempiere.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MWarehouse;
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
		int accountID = DB.getSQLValueEx(iol.get_TrxName(),
				"select "
				+ "	vc.account_id::int "
				+ "from m_product_acct pa "
				+ "inner join c_validcombination vc "
				+ "	on vc.c_validcombination_id = pa.p_asset_acct "
				+ "where pa.isactive = 'Y' "
				+ "and pa.c_acctschema_id = ? "
				+ "and pa.m_product_id = ? "
				+ "fetch first 1 rows only ",
				c_acctschema_id,
				iol.getM_Product_ID()
		);
		BigDecimal amt = DB.getSQLValueBDEx(iol.get_TrxName(),
				"select "
				+ "	fa.amtacctdr - fa.amtacctcr amt "
				+ "from fact_acct fa "
				+ "where fa.isactive = 'Y' "
				+ "and fa.ad_table_id = ? "
				+ "and fa.record_id = ? "
				+ "and fa.line_id = ? "
				+ "and fa.account_id = ? "
				+ "fetch first 1 rows only",
				MInOut.Table_ID,
				iol.getM_InOut_ID(),
				iol.getM_InOutLine_ID(),
				accountID
		);
		return amt == null ? Env.ZERO : amt.abs();
	}
	
}
