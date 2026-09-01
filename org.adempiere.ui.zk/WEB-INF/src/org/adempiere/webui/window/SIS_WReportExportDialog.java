/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - trekglobal														   *
 * - hengsin                         								   *
 **********************************************************************/
package org.adempiere.webui.window;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.LogAuthFailure;
import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.IReportViewerExportSource.ExportFormat;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.SIS_MDocumentPrintLog;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 * Dialog to export and download report
 * @author hengsin
 */
public class SIS_WReportExportDialog extends Window implements EventListener<Event> {

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 8580712975224551032L;
	private Listbox cboType = new Listbox();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private IReportViewerExportSource viewer;
	/* SysConfig USE_ESC_FOR_TAB_CLOSING */
	private boolean isUseEscForTabClosing = MSysConfig.getBooleanValue(MSysConfig.USE_ESC_FOR_TAB_CLOSING, false, Env.getAD_Client_ID(Env.getCtx()));
	private Textbox username;
	private Textbox password;	
	/**	Logger			*/
	private static final CLogger log = CLogger.getCLogger(SIS_WReportExportDialog.class);	
	private int printCount = 0;
	private PrintInfo m_printInfo;
	 
	private String trxName;
	/**
	 * @param viewer
	 */
	public SIS_WReportExportDialog(IReportViewerExportSource viewer,PrintInfo m_printInfo) {
	    this.viewer = viewer;	
	    this.m_printInfo = m_printInfo;
	    trxName = Trx.createTrxName("PrintLog");
	    ZKUpdateUtil.setWindowWidthX(this, 450);
	    
	    setClosable(true);
	    setBorder("normal");
	    setSclass("popup-dialog");
	    setStyle("position:absolute");
	    cboType.setMold("select");
	    
	    cboType.getItems().clear();
	    ExportFormat[] exportFormats = viewer.getExportFormats();
	    Arrays.sort(exportFormats, new Comparator<ExportFormat>() {
	        @Override
	        public int compare(ExportFormat ef0, ExportFormat ef1) {
	            return ef0.label.compareTo(ef1.label);
	        }
	    });
	    for (ExportFormat exportFormat : exportFormats) {
	        ListItem item = cboType.appendItem(exportFormat.label, exportFormat);
	        if (viewer.getContentType().equals(exportFormat.contentType) && viewer.getFileExtension().equals(exportFormat.extension)) {
	            item.setSelected(true);
	        }
	    }		    
    	
	    printCount = new Query(Env.getCtx(), SIS_MDocumentPrintLog.Table_Name,"AD_Table_ID=? AND Record_ID=?" ,trxName)
				.setParameters(m_printInfo.getAD_Table_ID(),m_printInfo.getRecord_ID())
				.count();

	    Vbox vb = new Vbox();
	    ZKUpdateUtil.setWidth(vb, "100%");
	    appendChild(vb);

	    Hbox hb = new Hbox();
	    hb.setSclass("dialog-content");
	    hb.setAlign("center");
	    hb.setPack("start");

	    Div divFields = new Div();
	    divFields.setStyle("width: 100%;");

	    if(printCount >0) {	    	
		    // Username row
		    Div hbUsername = new Div();
		    hbUsername.setSclass("dialog-content");
		    Div divUsernameLabel = new Div();
		    divUsernameLabel.setStyle("display:flex; text-align: right;");
		    divUsernameLabel.appendChild(new Label(Msg.getMsg(Env.getCtx(), "User")));
		    username = new Textbox();
		    ZKUpdateUtil.setWidth(username, "75%");
		    hbUsername.appendChild(divUsernameLabel);
		    hbUsername.appendChild(username);
		    divFields.appendChild(hbUsername);	    
		    
		    // Password row
		    Div hbPassword = new Div();
		    hbPassword.setSclass("dialog-content");
		    Div divPasswordLabel = new Div();
		    divUsernameLabel.setStyle("display:flex; text-align: right;");
		    divPasswordLabel.appendChild(new Label(Msg.getMsg(Env.getCtx(), "Password")));
		    password = new Textbox();
		    password.setType("password");
		    ZKUpdateUtil.setWidth(password, "75%");
		    hbPassword.appendChild(divPasswordLabel);
		    hbPassword.appendChild(password);
		    divFields.appendChild(hbPassword);
	    }

	    // File type row
	    Hbox hbFileType = new Hbox();
	    hbFileType.setSclass("dialog-content");
	    hbFileType.setAlign("center");
	    hbFileType.setPack("start");
	    Div divFileTypeLabel = new Div();
	    divFileTypeLabel.setStyle("text-align: right;");
	    divFileTypeLabel.appendChild(new Label(Msg.getMsg(Env.getCtx(), "FilesOfType")));
	    ZKUpdateUtil.setWidth(cboType, "100%");
	    hbFileType.appendChild(divFileTypeLabel);
	    hbFileType.appendChild(cboType);
	    	   
	    divFields.appendChild(hbFileType);

	    vb.appendChild(divFields);
	    vb.appendChild(confirmPanel);
	    LayoutUtils.addSclass("dialog-footer", confirmPanel);
	    confirmPanel.addActionListener(this);
	    addEventListener(Events.ON_CANCEL, e -> onCancel());
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if(event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
			onCancel();
		else if(event.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			int userId = Env.getAD_User_ID(Env.getCtx());			
	    	Trx trx = Trx.get(trxName, true);
			try {
			
			if(printCount > 0) {
				userId = validateUser();								
			} 
			SIS_MDocumentPrintLog log = new SIS_MDocumentPrintLog(Env.getCtx(), 0, trxName);
			log.setRecord_ID(m_printInfo.getRecord_ID());
			log.setAD_Table_ID(m_printInfo.getAD_Table_ID());
			log.setAD_User_ID(userId);
			log.saveEx();
			trx.commit();
			exportFile();
			} catch (Exception e) {
				trx.rollback();				
				throw new AdempiereException(e);
			} finally {
				trx.close();
			}			
		}
	}
	
	private int validateUser() {		
		Login login = new Login(Env.getCtx());
		String userId = username.getValue();
		String userPassword = password.getValue();
		KeyNamePair clientsKNPairs[] = login.getClients(userId, userPassword);
		if (clientsKNPairs != null && clientsKNPairs.length > 0)
		{
			MUser user = MUser.get(Env.getCtx(), Login.getAppUser(userId));
			MRole[] role = user.getRoles(Env.getAD_Org_ID(Env.getCtx()));        	
			for (MRole r : role) {
				if (r.get_ValueAsBoolean("SIS_AllowPrintCopy"))
					return user.get_ID();
			}			
			throw new AdempiereException("User has no role to print copy document!");
		}	               
		 throw new AdempiereException("Invalid user");
	}

	/**
	 * Handle onCancel event
	 */
	private void onCancel() {
		// do not allow to close tab for Events.ON_CTRL_KEY event
		if(isUseEscForTabClosing)
			SessionManager.getAppDesktop().setCloseTabWithShortcut(false);

		onClose();
	}

	/**
	 * Export report as file for download by user
	 */
	private void exportFile()
	{
		try
		{
			AMedia media = null;
			
			ListItem li = cboType.getSelectedItem();
			if(li == null || li.getValue() == null)
			{
				Dialog.error(-1, "FileInvalidExtension");
				return;
			}
			
			ExportFormat exportFormat = li.getValue();
			media = viewer.getMedia(exportFormat.contentType, exportFormat.extension);

			onClose();		
			Filedownload.save(media, viewer.getReportName() + "." + exportFormat.extension);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Failed to export content.", e);
		}
	}
	
}
