/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for SIS_DocumentPrintLog
 *  @author iDempiere (generated)
 *  @version Release 13 - $Id$ */
@org.adempiere.base.Model(table="SIS_DocumentPrintLog")
public class X_SIS_DocumentPrintLog extends PO implements I_SIS_DocumentPrintLog, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260516L;

    /** Standard Constructor */
    public X_SIS_DocumentPrintLog (Properties ctx, int SIS_DocumentPrintLog_ID, String trxName)
    {
      super (ctx, SIS_DocumentPrintLog_ID, trxName);
      /** if (SIS_DocumentPrintLog_ID == 0)
        {
        } */
    }

    /** Standard Constructor */
    public X_SIS_DocumentPrintLog (Properties ctx, int SIS_DocumentPrintLog_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SIS_DocumentPrintLog_ID, trxName, virtualColumns);
      /** if (SIS_DocumentPrintLog_ID == 0)
        {
        } */
    }

    /** Standard Constructor */
    public X_SIS_DocumentPrintLog (Properties ctx, String SIS_DocumentPrintLog_UU, String trxName)
    {
      super (ctx, SIS_DocumentPrintLog_UU, trxName);
      /** if (SIS_DocumentPrintLog_UU == null)
        {
        } */
    }

    /** Standard Constructor */
    public X_SIS_DocumentPrintLog (Properties ctx, String SIS_DocumentPrintLog_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SIS_DocumentPrintLog_UU, trxName, virtualColumns);
      /** if (SIS_DocumentPrintLog_UU == null)
        {
        } */
    }

    /** Load Constructor */
    public X_SIS_DocumentPrintLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_SIS_DocumentPrintLog[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_ID)
			.getPO(getAD_Table_ID(), get_TrxName());
	}

	/** Set Table.
		@param AD_Table_ID Database Table information
	*/
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getAD_User_ID(), get_TrxName());
	}

	/** Set User/Contact.
		@param AD_User_ID User within the system - Internal or Business Partner Contact
	*/
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Record ID.
		@param Record_ID Direct internal record ID
	*/
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			set_ValueNoCheck (COLUMNNAME_Record_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Print Log.
		@param SIS_DocumentPrintLog_ID Document Print Log
	*/
	public void setSIS_DocumentPrintLog_ID (int SIS_DocumentPrintLog_ID)
	{
		if (SIS_DocumentPrintLog_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SIS_DocumentPrintLog_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SIS_DocumentPrintLog_ID, Integer.valueOf(SIS_DocumentPrintLog_ID));
	}

	/** Get Document Print Log.
		@return Document Print Log	  */
	public int getSIS_DocumentPrintLog_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SIS_DocumentPrintLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Print Count.
		@param SIS_PrintCount Print Count
	*/
	public void setSIS_PrintCount (int SIS_PrintCount)
	{
		set_Value (COLUMNNAME_SIS_PrintCount, Integer.valueOf(SIS_PrintCount));
	}

	/** Get Print Count.
		@return Print Count
	  */
	public int getSIS_PrintCount()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SIS_PrintCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}