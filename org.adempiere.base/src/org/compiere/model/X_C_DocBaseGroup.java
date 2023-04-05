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

/** Generated Model for C_DocBaseGroup
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="C_DocBaseGroup")
public class X_C_DocBaseGroup extends PO implements I_C_DocBaseGroup, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230403L;

    /** Standard Constructor */
    public X_C_DocBaseGroup (Properties ctx, int C_DocBaseGroup_ID, String trxName)
    {
      super (ctx, C_DocBaseGroup_ID, trxName);
      /** if (C_DocBaseGroup_ID == 0)
        {
			setC_DocBaseGroup_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_C_DocBaseGroup (Properties ctx, int C_DocBaseGroup_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_DocBaseGroup_ID, trxName, virtualColumns);
      /** if (C_DocBaseGroup_ID == 0)
        {
			setC_DocBaseGroup_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_C_DocBaseGroup (Properties ctx, String C_DocBaseGroup_UU, String trxName)
    {
      super (ctx, C_DocBaseGroup_UU, trxName);
      /** if (C_DocBaseGroup_UU == null)
        {
			setC_DocBaseGroup_ID (0);
			setName (null);
        } */
    }

    /** Standard Constructor */
    public X_C_DocBaseGroup (Properties ctx, String C_DocBaseGroup_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_DocBaseGroup_UU, trxName, virtualColumns);
      /** if (C_DocBaseGroup_UU == null)
        {
			setC_DocBaseGroup_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_DocBaseGroup (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client
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
      StringBuilder sb = new StringBuilder ("X_C_DocBaseGroup[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Document Base Type Group.
		@param C_DocBaseGroup_ID Group of Document Base Type for Period Control
	*/
	public void setC_DocBaseGroup_ID (int C_DocBaseGroup_ID)
	{
		if (C_DocBaseGroup_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_DocBaseGroup_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_DocBaseGroup_ID, Integer.valueOf(C_DocBaseGroup_ID));
	}

	/** Get Document Base Type Group.
		@return Group of Document Base Type for Period Control
	  */
	public int getC_DocBaseGroup_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocBaseGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_DocBaseGroup_UU.
		@param C_DocBaseGroup_UU C_DocBaseGroup_UU
	*/
	public void setC_DocBaseGroup_UU (String C_DocBaseGroup_UU)
	{
		set_Value (COLUMNNAME_C_DocBaseGroup_UU, C_DocBaseGroup_UU);
	}

	/** Get C_DocBaseGroup_UU.
		@return C_DocBaseGroup_UU	  */
	public String getC_DocBaseGroup_UU()
	{
		return (String)get_Value(COLUMNNAME_C_DocBaseGroup_UU);
	}

	/** Set Comment/Help.
		@param Help Comment or Hint
	*/
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp()
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}