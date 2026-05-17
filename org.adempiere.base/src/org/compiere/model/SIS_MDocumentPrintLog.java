package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class SIS_MDocumentPrintLog extends X_SIS_DocumentPrintLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355049508358610013L;

	public SIS_MDocumentPrintLog(Properties ctx, int SIS_DocumentLog_ID, String trxName) {
		super(ctx, SIS_DocumentLog_ID, trxName);
	}

	public SIS_MDocumentPrintLog(Properties ctx, int SIS_DocumentLog_ID, String trxName, String[] virtualColumns) {
		super(ctx, SIS_DocumentLog_ID, trxName, virtualColumns);
	}

	public SIS_MDocumentPrintLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
