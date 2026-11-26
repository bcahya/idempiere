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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for SIS_RDOLine
 *  @author iDempiere (generated) 
 *  @version Release 13
 */
@SuppressWarnings("all")
public interface I_SIS_RDOLine 
{

    /** TableName=SIS_RDOLine */
    public static final String Table_Name = "SIS_RDOLine";

    /** AD_Table_ID=1000041 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Tenant.
	  * Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within tenant
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within tenant
	  */
	public int getAD_Org_ID();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

    /** Column name M_LocatorTo_ID */
    public static final String COLUMNNAME_M_LocatorTo_ID = "M_LocatorTo_ID";

	/** Set Locator To.
	  * Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID);

	/** Get Locator To.
	  * Location inventory is moved to
	  */
	public int getM_LocatorTo_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Locator getM_LocatorTo() throws RuntimeException;

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_WarehouseTo_ID */
    public static final String COLUMNNAME_M_WarehouseTo_ID = "M_WarehouseTo_ID";

	/** Set Warehouse To.
	  * To Storage Warehouse and Service Point
	  */
	public void setM_WarehouseTo_ID (int M_WarehouseTo_ID);

	/** Get Warehouse To.
	  * To Storage Warehouse and Service Point
	  */
	public int getM_WarehouseTo_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_M_Warehouse getM_WarehouseTo() throws RuntimeException;

    /** Column name MovementQty */
    public static final String COLUMNNAME_MovementQty = "MovementQty";

	/** Set Movement Quantity.
	  * Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty);

	/** Get Movement Quantity.
	  * Quantity of a product moved.
	  */
	public BigDecimal getMovementQty();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name SIS_QtyReceived */
    public static final String COLUMNNAME_SIS_QtyReceived = "SIS_QtyReceived";

	/** Set Qty Received.
	  * Qty Received
	  */
	public void setSIS_QtyReceived (BigDecimal SIS_QtyReceived);

	/** Get Qty Received.
	  * Qty Received
	  */
	public BigDecimal getSIS_QtyReceived();

    /** Column name SIS_RDOLine_ID */
    public static final String COLUMNNAME_SIS_RDOLine_ID = "SIS_RDOLine_ID";

	/** Set Request Distribution Order Line	  */
	public void setSIS_RDOLine_ID (int SIS_RDOLine_ID);

	/** Get Request Distribution Order Line	  */
	public int getSIS_RDOLine_ID();

    /** Column name SIS_RDOLine_UU */
    public static final String COLUMNNAME_SIS_RDOLine_UU = "SIS_RDOLine_UU";

	/** Set SIS_RDOLine_UU	  */
	public void setSIS_RDOLine_UU (String SIS_RDOLine_UU);

	/** Get SIS_RDOLine_UU	  */
	public String getSIS_RDOLine_UU();

    /** Column name SIS_RDO_ID */
    public static final String COLUMNNAME_SIS_RDO_ID = "SIS_RDO_ID";

	/** Set Request Distribution Order	  */
	public void setSIS_RDO_ID (int SIS_RDO_ID);

	/** Get Request Distribution Order	  */
	public int getSIS_RDO_ID();

	@Deprecated(since="13") // use better methods with cache
	public I_SIS_RDO getSIS_RDO() throws RuntimeException;

    /** Column name SIS_Subbrand_ID */
    public static final String COLUMNNAME_SIS_Subbrand_ID = "SIS_Subbrand_ID";

	/** Set SIS Subbrand	  */
	public void setSIS_Subbrand_ID (int SIS_Subbrand_ID);

	/** Get SIS Subbrand	  */
	public int getSIS_Subbrand_ID();
	
    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User Element List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User Element List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;
}
