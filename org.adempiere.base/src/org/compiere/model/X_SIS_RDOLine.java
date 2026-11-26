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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for SIS_RDOLine
 *  @author iDempiere (generated)
 *  @version Release 13 - $Id$ */
@org.adempiere.base.Model(table="SIS_RDOLine")
public class X_SIS_RDOLine extends PO implements I_SIS_RDOLine, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20261022L;

    /** Standard Constructor */
    public X_SIS_RDOLine (Properties ctx, int SIS_RDOLine_ID, String trxName)
    {
      super (ctx, SIS_RDOLine_ID, trxName);
      /** if (SIS_RDOLine_ID == 0)
        {
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SIS_RDOLine (Properties ctx, int SIS_RDOLine_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SIS_RDOLine_ID, trxName, virtualColumns);
      /** if (SIS_RDOLine_ID == 0)
        {
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SIS_RDOLine (Properties ctx, String SIS_RDOLine_UU, String trxName)
    {
      super (ctx, SIS_RDOLine_UU, trxName);
      /** if (SIS_RDOLine_UU == null)
        {
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SIS_RDOLine (Properties ctx, String SIS_RDOLine_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SIS_RDOLine_UU, trxName, virtualColumns);
      /** if (SIS_RDOLine_UU == null)
        {
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_SIS_RDOLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SIS_RDOLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
	{
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_ID)
			.getPO(getC_Project_ID(), get_TrxName());
	}

	/** Set Project.
		@param C_Project_ID Financial Project
	*/
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
	{
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_ID)
			.getPO(getC_UOM_ID(), get_TrxName());
	}

	/** Set UOM.
		@param C_UOM_ID Unit of Measure
	*/
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
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

	/** Set Line.
		@param LineNo Line No
	*/
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Locator getM_LocatorTo() throws RuntimeException
	{
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_ID)
			.getPO(getM_LocatorTo_ID(), get_TrxName());
	}

	/** Set Locator To.
		@param M_LocatorTo_ID Location inventory is moved to
	*/
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1)
			set_Value (COLUMNNAME_M_LocatorTo_ID, null);
		else
			set_Value (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
	{
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_ID)
			.getPO(getM_Locator_ID(), get_TrxName());
	}

	/** Set Locator.
		@param M_Locator_ID Warehouse Locator
	*/
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_ID)
			.getPO(getM_Product_ID(), get_TrxName());
	}

	/** Set Product.
		@param M_Product_ID Product, Service, Item
	*/
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			set_Value (COLUMNNAME_M_Product_ID, null);
		else
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Warehouse getM_WarehouseTo() throws RuntimeException
	{
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_ID)
			.getPO(getM_WarehouseTo_ID(), get_TrxName());
	}

	/** Set Warehouse To.
		@param M_WarehouseTo_ID To Storage Warehouse and Service Point
	*/
	public void setM_WarehouseTo_ID (int M_WarehouseTo_ID)
	{
		if (M_WarehouseTo_ID < 1)
			set_Value (COLUMNNAME_M_WarehouseTo_ID, null);
		else
			set_Value (COLUMNNAME_M_WarehouseTo_ID, Integer.valueOf(M_WarehouseTo_ID));
	}

	/** Get Warehouse To.
		@return To Storage Warehouse and Service Point
	  */
	public int getM_WarehouseTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Quantity.
		@param MovementQty Quantity of a product moved.
	*/
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Delivered Quantity.
		@param QtyDelivered Delivered Quantity
	*/
	public void setQtyDelivered (BigDecimal QtyDelivered)
	{
		set_Value (COLUMNNAME_QtyDelivered, QtyDelivered);
	}

	/** Get Delivered Quantity.
		@return Delivered Quantity
	  */
	public BigDecimal getQtyDelivered()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDelivered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered The Quantity Entered is based on the selected UoM
	*/
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Qty Received.
		@param SIS_QtyReceived Qty Received
	*/
	public void setSIS_QtyReceived (BigDecimal SIS_QtyReceived)
	{
		set_Value (COLUMNNAME_SIS_QtyReceived, SIS_QtyReceived);
	}

	/** Get Qty Received.
		@return Qty Received
	  */
	public BigDecimal getSIS_QtyReceived()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SIS_QtyReceived);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Request Distribution Order Line.
		@param SIS_RDOLine_ID Request Distribution Order Line
	*/
	public void setSIS_RDOLine_ID (int SIS_RDOLine_ID)
	{
		if (SIS_RDOLine_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SIS_RDOLine_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SIS_RDOLine_ID, Integer.valueOf(SIS_RDOLine_ID));
	}

	/** Get Request Distribution Order Line.
		@return Request Distribution Order Line	  */
	public int getSIS_RDOLine_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SIS_RDOLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SIS_RDOLine_UU.
		@param SIS_RDOLine_UU SIS_RDOLine_UU
	*/
	public void setSIS_RDOLine_UU (String SIS_RDOLine_UU)
	{
		set_Value (COLUMNNAME_SIS_RDOLine_UU, SIS_RDOLine_UU);
	}

	/** Get SIS_RDOLine_UU.
		@return SIS_RDOLine_UU	  */
	public String getSIS_RDOLine_UU()
	{
		return (String)get_Value(COLUMNNAME_SIS_RDOLine_UU);
	}

	@Deprecated(since="13") // use better methods with cache
	public I_SIS_RDO getSIS_RDO() throws RuntimeException
	{
		return (I_SIS_RDO)MTable.get(getCtx(), I_SIS_RDO.Table_ID)
			.getPO(getSIS_RDO_ID(), get_TrxName());
	}

	/** Set Request Distribution Order.
		@param SIS_RDO_ID Request Distribution Order
	*/
	public void setSIS_RDO_ID (int SIS_RDO_ID)
	{
		if (SIS_RDO_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SIS_RDO_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SIS_RDO_ID, Integer.valueOf(SIS_RDO_ID));
	}

	/** Get Request Distribution Order.
		@return Request Distribution Order	  */
	public int getSIS_RDO_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SIS_RDO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	/** Set SIS Subbrand.
		@param SIS_Subbrand_ID SIS Subbrand
	*/
	public void setSIS_Subbrand_ID (int SIS_Subbrand_ID)
	{
		if (SIS_Subbrand_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SIS_Subbrand_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SIS_Subbrand_ID, Integer.valueOf(SIS_Subbrand_ID));
	}

	/** Get SIS Subbrand.
		@return SIS Subbrand	  */
	public int getSIS_Subbrand_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SIS_Subbrand_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
	{
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_ID)
			.getPO(getUser1_ID(), get_TrxName());
	}

	/** Set User Element List 1.
		@param User1_ID User defined list element #1
	*/
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1)
			set_Value (COLUMNNAME_User1_ID, null);
		else
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User Element List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}