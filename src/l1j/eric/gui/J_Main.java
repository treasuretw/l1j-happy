/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */

package l1j.eric.gui;

import static l1j.server.server.model.skill.L1SkillId.*;
//import java.awt.AWTException;
import java.awt.Color;
//import java.awt.Component;
//import java.awt.HeadlessException;
//import java.awt.Image;
//import java.awt.PopupMenu;
//import java.awt.SystemTray;
//import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
//import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import l1j.eric.gui.CreatGMHTML;// TODO GM选单

import l1j.server.server.GameServer;
//import l1j.server.server.Opcodes;
import l1j.server.server.clientpackets.C_LoginToServer;
import l1j.server.server.datatables.IpTable;
//import l1j.server.server.datatables.ItemTable;
//import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
//import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.skill.L1BuffUtil;
import l1j.server.server.model.skill.L1SkillUse;
//import l1j.server.server.serverpackets.S_ChatPacket;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.Config;// 在线修改经验/掉宝/掉钱倍率by cwt123

/**
 * 图形化管理界面
 * @author eric1
 */
public class J_Main extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static J_Main instance;

	private int select = 0;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	ImageIcon img = new ImageIcon("img/icon.png");

	/** Creates new form J_Main */
	public J_Main() {
		iniPlayerTable();
		initComponents();
		TA_Consol.setForeground(Color.WHITE);
		TA_AllChat.setForeground(Color.WHITE);
		TA_Clan.setForeground(Color.BLACK);
		TA_Normal.setForeground(Color.BLACK);
		TA_Private.setForeground(Color.BLACK);
		TA_Team.setForeground(Color.BLACK);
		TA_World.setForeground(Color.BLACK);
		// background
		TA_Consol.setBackground(Color.BLACK);
		TA_AllChat.setBackground(Color.BLACK);
		TA_Clan.setBackground(Color.WHITE);
		TA_Normal.setBackground(Color.WHITE);
		TA_Private.setBackground(Color.WHITE);
		TA_Team.setBackground(Color.WHITE);
		TA_World.setBackground(Color.WHITE);
		this.setIconImage(img.getImage());
		iniAction();
		T_Item.setSize(300, 400);
		D_Item.pack();
		String s[] = { "物品名称", "物品数量", "物品编号" };
		DTM_Item.setColumnIdentifiers(s);

	}

	private void iniAction() {
		MI_Kill.addActionListener(this);
		MI_BanIP.addActionListener(this);
		MI_ShowPlayer.addActionListener(this);
		MI_Whisper.addActionListener(this);
		MI_Save.addActionListener(this);
		MI_Close.addActionListener(this);
		MI_Angel.addActionListener(this);
		MI_SetClose.addActionListener(this);
		MI_AllBuff.addActionListener(this);
		MI_AllRess.addActionListener(this);

		// 增加管理器倍数 by 24918380
		MI_XP_PET.addActionListener(this);
		MI_CHANCE_WEAPON.addActionListener(this);
		MI_CHANCE_ARMOR.addActionListener(this);
		MI_ATTR_ENCHANT_CHANCE.addActionListener(this);
		MI_LA.addActionListener(this);

		MI_Adena.addActionListener(this);
		MI_Exp.addActionListener(this);
		MI_Drop.addActionListener(this);

		// TODO GM选单
		MI_GMHTML_TW.addActionListener(this);
    	MI_GMHTML_GBK.addActionListener(this);

	}

	/*
	 * private void iniTray() { tray = SystemTray.getSystemTray();
	 * 
	 * trayIcon =new TrayIcon(img.getImage(),"天堂管理介面",PM_Tray);
	 * trayIcon.setImageAutoSize(true); try { tray.add(trayIcon); } catch
	 * (AWTException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	private DefaultTableModel DTM = new DefaultTableModel() {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	};

	private DefaultTableModel DTM_Item = new DefaultTableModel() {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	};

	public static J_Main getInstance() {
		if (instance == null) {
			instance = new J_Main();
		}
		return instance;
	}

	public void addWorldChat(String from, String text) {
		Calendar cal = Calendar.getInstance();
		AllChat(sdf.format(cal.getTime()) + "【" + from + "】:" + text + "\r\n");
		TA_World.append(from + " : " + text + "\r\n");
		TA_World.setCaretPosition(TA_World.getDocument().getLength());
	}

	public void addClanChat(String from, String text) {
		Calendar cal = Calendar.getInstance();
		AllChat(sdf.format(cal.getTime()) + "“" + from + "”:" + text + "\r\n");
		TA_Clan.append(from + " : " + text + "\r\n");
		TA_Clan.setCaretPosition(TA_Clan.getDocument().getLength());
	}

	public void addNormalChat(String from, String text) {
		Calendar cal = Calendar.getInstance();
		AllChat(sdf.format(cal.getTime()) + "{" + from + "}:" + text + "\r\n");
		TA_Normal.append(from + " : " + text + "\r\n");
		TA_Normal.setCaretPosition(TA_Normal.getDocument().getLength());
	}

	public void addTeamChat(String from, String text) {
		Calendar cal = Calendar.getInstance();
		AllChat(sdf.format(cal.getTime()) + "[" + from + "]:" + text + "\r\n");
		TA_Team.append(from + " : " + text + "\r\n");
		TA_Team.setCaretPosition(TA_Team.getDocument().getLength());
	}

	public void addConsol(String text) {
		TA_Consol.append(text + "\r\n");
		TA_Consol.setCaretPosition(TA_Consol.getDocument().getLength());
	}

	public void addConsolPost(String text) {
		TA_Consol.append(text + "\r\n");
		TA_Consol.setCaretPosition(TA_Consol.getDocument().getLength());
	}

	public void addConsolNoLR(String text) {
		TA_Consol.append(text);
		TA_Consol.setCaretPosition(TA_Consol.getDocument().getLength());
	}

	public void AllChat(String text) {
		TA_AllChat.append(text + "\r\n");
		TA_AllChat.setCaretPosition(TA_AllChat.getDocument().getLength());
	}

	public void addPrivateChat(String from, String to, String text) {
		Calendar cal = Calendar.getInstance();
		AllChat(sdf.format(cal.getTime()) + "(" + from + "->" + to + "):"
				+ text + "\r\n");
		TA_Private.append(from + "->" + to + " : " + text + "\r\n");
		TA_Private.setCaretPosition(TA_Private.getDocument().getLength());
	}

	public void addItemTable(String itemname, int cont, long id) {
		Object o[] = { itemname, cont, id };
		DTM_Item.addRow(o);
	}

	public void iniTable() {
		int cont = DTM_Item.getRowCount();
		while (cont > 1) {
			DTM_Item.removeRow(cont - 1);
			cont--;
		}
	}

	public void addPlayerTable(String account, String name, String IP) {
		Object o[] = { account, name, IP };
		DTM.addRow(o);
	}

	private int findPlayer(String name) {
		try {
			for (int j = 0; j < DTM.getRowCount(); j++) {
				if (name.equals(DTM.getValueAt(j, 1).toString())) {
					return j; // 找到了
				}
			}
			// 没找到
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			// row_counter=0;
			// dont do
			return -1;
		}
	}

	public void delPlayerTable(String name) {
		int findNum = 0;
		if ((findNum = findPlayer(name)) != -1) { // 找到
			DTM.removeRow(findNum);
		}
	}

	private void iniPlayerTable() {
		String s[] = { "帐号", "角色名称", "IP地址" };
		DTM.setColumnIdentifiers(s);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		F_Player = new javax.swing.JFrame();
		L_Name = new javax.swing.JLabel();
		L_Title = new javax.swing.JLabel();
		L_Account = new javax.swing.JLabel();
		L_Leavl = new javax.swing.JLabel();
		L_AccessLevel = new javax.swing.JLabel();
		L_Exp = new javax.swing.JLabel();
		L_Hp = new javax.swing.JLabel();
		L_Mp = new javax.swing.JLabel();
		L_Int = new javax.swing.JLabel();
		L_Str = new javax.swing.JLabel();
		L_Con = new javax.swing.JLabel();
		L_Dex = new javax.swing.JLabel();
		L_Wis = new javax.swing.JLabel();
		L_Cha = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		L_Image = new javax.swing.JLabel();
		L_Clan = new javax.swing.JLabel();
		L_AccessLevel7 = new javax.swing.JLabel();
		L_Mp1 = new javax.swing.JLabel();
		L_Map = new javax.swing.JLabel();
		L_X = new javax.swing.JLabel();
		L_Y = new javax.swing.JLabel();
		TF_Account = new javax.swing.JTextField();
		TF_Name = new javax.swing.JTextField();
		TF_Title = new javax.swing.JTextField();
		TF_Level = new javax.swing.JTextField();
		TF_AccessLevel = new javax.swing.JTextField();
		TF_Clan = new javax.swing.JTextField();
		TF_Exp = new javax.swing.JTextField();
		TF_Hp = new javax.swing.JTextField();
		TF_Mp = new javax.swing.JTextField();
		TF_Sex = new javax.swing.JTextField();
		TF_Str = new javax.swing.JTextField();
		TF_Con = new javax.swing.JTextField();
		TF_Dex = new javax.swing.JTextField();
		TF_Wis = new javax.swing.JTextField();
		TF_Int = new javax.swing.JTextField();
		TF_Cha = new javax.swing.JTextField();
		TF_Ac = new javax.swing.JTextField();
		TF_Map = new javax.swing.JTextField();
		TF_X = new javax.swing.JTextField();
		TF_Y = new javax.swing.JTextField();
		B_Item = new javax.swing.JButton();
		CB_Item = new javax.swing.JComboBox();
		PM_Player = new javax.swing.JPopupMenu();
		MI_Kill = new javax.swing.JMenuItem();
		MI_BanIP = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		MI_ShowPlayer = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JSeparator();
		MI_Whisper = new javax.swing.JMenuItem();
		jLabel1 = new javax.swing.JLabel();
		D_Item = new javax.swing.JDialog();
		jScrollPane1 = new javax.swing.JScrollPane();
		T_Item = new JTable(DTM_Item);
		SP_Split = new javax.swing.JSplitPane();
		TP = new javax.swing.JTabbedPane();
		SP_Consol = new javax.swing.JScrollPane();
		TA_Consol = new javax.swing.JTextArea();
		SP_AllChat = new javax.swing.JScrollPane();
		TA_AllChat = new javax.swing.JTextArea();
		SP_World = new javax.swing.JScrollPane();
		TA_World = new javax.swing.JTextArea();
		SP_Normal = new javax.swing.JScrollPane();
		TA_Normal = new javax.swing.JTextArea();
		SP_ = new javax.swing.JScrollPane();
		TA_Private = new javax.swing.JTextArea();
		SP_Clan = new javax.swing.JScrollPane();
		TA_Clan = new javax.swing.JTextArea();
		SP_Team = new javax.swing.JScrollPane();
		TA_Team = new javax.swing.JTextArea();
		SP_player = new javax.swing.JScrollPane();
		T_Player = new JTable(DTM);
		jPanel2 = new javax.swing.JPanel();
		CB_Channel = new javax.swing.JComboBox();
		TF_Target = new javax.swing.JTextField();
		B_Submit = new javax.swing.JButton();
		TF_Msg = new javax.swing.JTextField();
		MB = new javax.swing.JMenuBar();
		M_File = new javax.swing.JMenu();
		MI_Save = new javax.swing.JMenuItem();
		jSeparator3 = new javax.swing.JSeparator();
		MI_SetClose = new javax.swing.JMenuItem();
		MI_Close = new javax.swing.JMenuItem();
		M_Edit = new javax.swing.JMenu();

		// TODO GM选单
		MI_GMHTML_TW = new javax.swing.JMenuItem();
        MI_GMHTML_GBK = new javax.swing.JMenuItem();

		M_Special = new javax.swing.JMenu();

		// 增加管理器倍数 by 24918380
		MI_LA = new javax.swing.JMenuItem();					// 正义倍率(W)
		MI_ATTR_ENCHANT_CHANCE = new javax.swing.JMenuItem();	// 属性强化倍率(X)
		MI_CHANCE_ARMOR = new javax.swing.JMenuItem();			// 防具强化值倍率(S)
		MI_CHANCE_WEAPON = new javax.swing.JMenuItem();			// 武器强化值倍率(Q)
		MI_XP_PET = new javax.swing.JMenuItem();				// 宠物经验值倍率(G)

		// 在线修改经验/掉宝/掉钱倍率 by cwt123
		MI_Exp = new javax.swing.JMenuItem();	// 经验值倍率(F)
		MI_Drop = new javax.swing.JMenuItem();	// 掉宝倍率(D)
		MI_Adena = new javax.swing.JMenuItem(); // 掉钱倍率(M)

		MI_Angel = new javax.swing.JMenuItem();
		MI_AllBuff = new javax.swing.JMenuItem();
		MI_AllRess = new javax.swing.JMenuItem();
		L_Name.setText("名字:");
		L_Title.setText("称号:");
		L_Account.setText("帐号:");
		L_Leavl.setText("等级:");
		L_AccessLevel.setText("权限:");
		L_Exp.setText(" Exp:");
		L_Hp.setText("Hp:");
		L_Mp.setText("Mp:");
		L_Int.setText("智力:");
		L_Str.setText("力量:");
		L_Con.setText("体质:");
		L_Dex.setText("敏捷:");
		L_Wis.setText("精神:");
		L_Cha.setText("魅力:");
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(L_Image,
								javax.swing.GroupLayout.DEFAULT_SIZE, 108,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(L_Image,
								javax.swing.GroupLayout.DEFAULT_SIZE, 180,
								Short.MAX_VALUE).addContainerGap()));
		L_Clan.setText("血盟:");
		L_AccessLevel7.setText("防御:");
		L_Mp1.setText("性别:");
		L_Map.setText("Map:");
		L_X.setText("X:");
		L_Y.setText("Y:");
		TF_Account.setEditable(false);
		TF_Name.setEditable(false);
		TF_Title.setEditable(false);
		TF_Level.setEditable(false);
		TF_AccessLevel.setEditable(false);
		TF_Clan.setEditable(false);
		TF_Exp.setEditable(false);
		TF_Hp.setEditable(false);
		TF_Mp.setEditable(false);
		TF_Sex.setEditable(false);
		TF_Str.setEditable(false);
		TF_Con.setEditable(false);
		TF_Dex.setEditable(false);
		TF_Wis.setEditable(false);
		TF_Int.setEditable(false);
		TF_Cha.setEditable(false);
		TF_Ac.setEditable(false);
		TF_Map.setEditable(false);
		TF_X.setEditable(false);
		TF_Y.setEditable(false);
		B_Item.setText("物品栏显示");
		B_Item.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				B_ItemActionPerformed(evt);
			}
		});
		CB_Item.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"0,身上物品", "1,个人仓库", "2,血盟仓库", "3,妖森仓库" }));
		javax.swing.GroupLayout F_PlayerLayout = new javax.swing.GroupLayout(
				F_Player.getContentPane());
		F_Player.getContentPane().setLayout(F_PlayerLayout);
		F_PlayerLayout
		.setHorizontalGroup(F_PlayerLayout
				.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								F_PlayerLayout
								.createSequentialGroup()
								.addComponent(
										jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												F_PlayerLayout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																F_PlayerLayout
																.createSequentialGroup()
																.addComponent(
																		L_Account)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						TF_Account,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						108,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								F_PlayerLayout
																								.createSequentialGroup()
																								.addGroup(
																										F_PlayerLayout
																										.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								L_Name)
																						.addComponent(
																								L_Title)
																						.addComponent(
																								L_Leavl)
																						.addComponent(
																								L_AccessLevel)
																						.addComponent(
																								L_Clan)
																						.addComponent(
																								L_Exp,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								27,
																								Short.MAX_VALUE)
																						.addComponent(
																								L_Hp,
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								L_Mp,
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								L_Mp1,
																								javax.swing.GroupLayout.Alignment.TRAILING))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								TF_Mp,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Sex,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Hp,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Exp,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Clan,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_AccessLevel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Level,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Title,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								TF_Name,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								108,
																								Short.MAX_VALUE)
																						.addComponent(
																								CB_Item,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												F_PlayerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																F_PlayerLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(
																				F_PlayerLayout
																						.createSequentialGroup()
																						.addGroup(
																								F_PlayerLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												L_Int)
																										.addComponent(
																												L_Wis)
																										.addComponent(
																												L_Dex)
																										.addComponent(
																												L_Cha)
																										.addComponent(
																												L_AccessLevel7)
																										.addComponent(
																												L_Con)
																										.addComponent(
																												L_Str)
																										.addComponent(
																												L_Map)
																										.addComponent(
																												L_X))
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								F_PlayerLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												TF_Str,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Con,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Dex,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Wis,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Int,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Cha,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Ac,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_Map,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												TF_X,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												108,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addGroup(
																				F_PlayerLayout
																						.createSequentialGroup()
																						.addComponent(
																								L_Y)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								TF_Y,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								108,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(B_Item))
										.addContainerGap(52, Short.MAX_VALUE)));
		F_PlayerLayout
				.setVerticalGroup(F_PlayerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								F_PlayerLayout
										.createSequentialGroup()
										.addGroup(
												F_PlayerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																F_PlayerLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Account)
																						.addComponent(
																								TF_Account,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Name)
																						.addComponent(
																								TF_Name,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Title)
																						.addComponent(
																								TF_Title,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Leavl)
																						.addComponent(
																								TF_Level,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_AccessLevel)
																						.addComponent(
																								TF_AccessLevel,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Clan)
																						.addComponent(
																								TF_Clan,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Exp)
																						.addComponent(
																								TF_Exp,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Hp)
																						.addComponent(
																								TF_Hp,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Mp)
																						.addComponent(
																								TF_Mp,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Mp1)
																						.addComponent(
																								TF_Sex,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								L_Y)
																						.addComponent(
																								TF_Y,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																F_PlayerLayout
																		.createSequentialGroup()
																		.addGap(26,
																				26,
																				26)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																F_PlayerLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Str)
																						.addComponent(
																								TF_Str,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Con)
																						.addComponent(
																								TF_Con,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Dex)
																						.addComponent(
																								TF_Dex,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Wis)
																						.addComponent(
																								TF_Wis,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Int)
																						.addComponent(
																								TF_Int,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Cha)
																						.addComponent(
																								TF_Cha,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_AccessLevel7)
																						.addComponent(
																								TF_Ac,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_Map)
																						.addComponent(
																								TF_Map,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				F_PlayerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								L_X)
																						.addComponent(
																								TF_X,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								18,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												F_PlayerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																CB_Item,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(B_Item))
										.addContainerGap(27, Short.MAX_VALUE)));
		MI_Kill.setMnemonic('K');
		MI_Kill.setText("强制踢除(K)");
		PM_Player.add(MI_Kill);
		MI_BanIP.setMnemonic('B');
		MI_BanIP.setText("封锁IP(B)");
		PM_Player.add(MI_BanIP);
		PM_Player.add(jSeparator1);
		MI_ShowPlayer.setMnemonic('P');
		MI_ShowPlayer.setText("玩家资料(P)");
		PM_Player.add(MI_ShowPlayer);
		PM_Player.add(jSeparator2);
		MI_Whisper.setMnemonic('W');
		MI_Whisper.setText("密语(W)");
		PM_Player.add(MI_Whisper);
		jLabel1.setText("jLabel1");
		D_Item.getContentPane().setLayout(new java.awt.GridLayout(1, 0));
		jScrollPane1.setViewportView(T_Item);
		D_Item.getContentPane().add(jScrollPane1);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("天堂1伺服器图形管理介面");
		setLocationByPlatform(true);
		setMinimumSize(new java.awt.Dimension(800, 600));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}
		});
		SP_Split.setDividerLocation(550);
		SP_Consol.setAutoscrolls(true);
		TA_Consol.setBackground(new java.awt.Color(0, 0, 0));
		TA_Consol.setColumns(20);
		TA_Consol.setEditable(false);
		TA_Consol.setForeground(new java.awt.Color(255, 255, 255));
		TA_Consol.setRows(5);
		TA_Consol.setEnabled(false);
		SP_Consol.setViewportView(TA_Consol);
		TP.addTab("Consol", SP_Consol);
		SP_AllChat.setAutoscrolls(true);
		TA_AllChat.setBackground(new java.awt.Color(0, 0, 0));
		TA_AllChat.setColumns(20);
		TA_AllChat.setEditable(false);
		TA_AllChat.setForeground(new java.awt.Color(255, 255, 255));
		TA_AllChat.setRows(5);
		SP_AllChat.setViewportView(TA_AllChat);
		TP.addTab("全部频道 ", SP_AllChat);
		SP_World.setAutoscrolls(true);
		TA_World.setColumns(20);
		TA_World.setEditable(false);
		TA_World.setForeground(new java.awt.Color(0, 0, 204));
		TA_World.setRows(5);
		TA_World.setEnabled(false);
		SP_World.setViewportView(TA_World);
		TP.addTab("世界频道 ", SP_World);
		SP_Normal.setAutoscrolls(true);
		TA_Normal.setColumns(20);
		TA_Normal.setEditable(false);
		TA_Normal.setRows(5);
		TA_Normal.setEnabled(false);
		SP_Normal.setViewportView(TA_Normal);
		TP.addTab("一般频道 ", SP_Normal);
		SP_.setAutoscrolls(true);
		TA_Private.setColumns(20);
		TA_Private.setEditable(false);
		TA_Private.setForeground(new java.awt.Color(204, 0, 51));
		TA_Private.setRows(5);
		TA_Private.setEnabled(false);
		SP_.setViewportView(TA_Private);
		TP.addTab("密语", SP_);
		SP_Clan.setAutoscrolls(true);
		TA_Clan.setColumns(20);
		TA_Clan.setEditable(false);
		TA_Clan.setForeground(new java.awt.Color(153, 51, 0));
		TA_Clan.setRows(5);
		TA_Clan.setEnabled(false);
		SP_Clan.setViewportView(TA_Clan);
		TP.addTab("血盟", SP_Clan);
		SP_Team.setAutoscrolls(true);
		TA_Team.setColumns(20);
		TA_Team.setEditable(false);
		TA_Team.setForeground(new java.awt.Color(102, 0, 102));
		TA_Team.setRows(5);
		TA_Team.setEnabled(false);
		SP_Team.setViewportView(TA_Team);
		TP.addTab("组队", SP_Team);
		SP_Split.setLeftComponent(TP);
		T_Player.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				T_PlayerMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				T_PlayerMouseReleased(evt);
			}
		});
		SP_player.setViewportView(T_Player);
		SP_Split.setRightComponent(SP_player);
		getContentPane().add(SP_Split, java.awt.BorderLayout.CENTER);
		CB_Channel.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"讯息频道", "密语" }));
		B_Submit.setText("发送");
		B_Submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				B_SubmitActionPerformed(evt);
			}
		});
		TF_Msg.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TF_MsgKeyPressed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												CB_Channel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												TF_Target,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												68,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												TF_Msg,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												310,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(B_Submit)
										.addGap(175, 175, 175)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(6, 6, 6)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																CB_Channel,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																TF_Target,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																TF_Msg,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(B_Submit))));
		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
		M_File.setMnemonic('F');
		M_File.setText("档案(F)");
		MI_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		MI_Save.setMnemonic('S');
		MI_Save.setText("储存讯息(S)");
		M_File.add(MI_Save);
		M_File.add(jSeparator3);
		MI_SetClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_E,
				java.awt.event.InputEvent.CTRL_MASK));
		MI_SetClose.setMnemonic('E');
		MI_SetClose.setText("设定关闭伺服器(E)...");
		M_File.add(MI_SetClose);
		MI_Close.setMnemonic('C');
		MI_Close.setText("直接关闭伺服器(C)");
		M_File.add(MI_Close);
		MB.add(M_File);
		M_Edit.setMnemonic('E');
		M_Edit.setText("管理菜单(E)");

		// TODO GM选单
		MI_GMHTML_TW.setMnemonic('B');
		MI_GMHTML_TW.setText("GM选单产生(BIG5)");
		M_Edit.add(MI_GMHTML_TW);

		MI_GMHTML_GBK.setMnemonic('G');
		MI_GMHTML_GBK.setText("GM选单产生(GBK)");
		M_Edit.add(MI_GMHTML_GBK);

		// 增加管理器倍数 by 24918380
		MI_LA.setMnemonic('W');
		MI_LA.setText("正义倍率(W)");
		M_Edit.add(MI_LA);
		MI_ATTR_ENCHANT_CHANCE.setMnemonic('X');
		MI_ATTR_ENCHANT_CHANCE.setText("属性强化倍率(X)");
		M_Edit.add(MI_ATTR_ENCHANT_CHANCE);
		MI_CHANCE_ARMOR.setMnemonic('S');
		MI_CHANCE_ARMOR.setText("防具强化倍率(S)");
		M_Edit.add(MI_CHANCE_ARMOR);
		MI_CHANCE_WEAPON.setMnemonic('Q');
		MI_CHANCE_WEAPON.setText("武器强化倍率(Q)");
		M_Edit.add(MI_CHANCE_WEAPON);
		MI_XP_PET.setMnemonic('G');
		MI_XP_PET.setText("宠物经验倍率(G)");
		M_Edit.add(MI_XP_PET);
		MI_Exp.setMnemonic('F');
		MI_Exp.setText("经验倍率(F)");
		M_Edit.add(MI_Exp);

		// 在线修改经验/掉宝/掉钱倍率 by cwt123
		MI_Exp.setMnemonic('F');
		MI_Exp.setText("经验倍率(F)");
		MI_Drop.setMnemonic('D');
		MI_Drop.setText("掉宝率(D)");
		MI_Adena.setMnemonic('M');
		MI_Adena.setText("掉钱倍率(M)");
		M_Edit.add(MI_Exp);
		M_Edit.add(MI_Drop);
		M_Edit.add(MI_Adena);

		MB.add(M_Edit);
		M_Special.setMnemonic('S');
		M_Special.setText("特殊功能(S)");
		MI_Angel.setMnemonic('A');
		MI_Angel.setText("大天使祝福(A)");
		M_Special.add(MI_Angel);
		MI_AllBuff.setMnemonic('B');
		MI_AllBuff.setText("终极祝福(B)");
		M_Special.add(MI_AllBuff);
		MI_AllRess.setMnemonic('R');
		MI_AllRess.setText("全体复活补血魔(R)");
		M_Special.add(MI_AllRess);
		MB.add(M_Special);
		setJMenuBar(MB);
		pack();
	}// </editor-fold>//GEN-END:initComponents

	// 线上玩家清单点两下
	private void T_PlayerMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_T_PlayerMouseReleased
		// 点左键两下
		if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
			select = T_Player.getSelectedRow();
			setPlayerView((String) DTM.getValueAt(select, 1));
			F_Player.pack();
			F_Player.setVisible(true);
		}
		// 显示快显功能表
		if (evt.isPopupTrigger()) {
			select = T_Player.getSelectedRow();
			PM_Player.show(T_Player, evt.getX(), evt.getY());
		}
	}// GEN-LAST:event_T_PlayerMouseReleased

	// 关闭控制视窗
	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		closeServer();
	}

	// 关闭伺服器
	private void closeServer() {
		// tray.remove(trayIcon);
		saveChatData(false);
		GameServer.getInstance().shutdown();
		System.exit(0);
	}

	private void T_PlayerMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_T_PlayerMousePressed
		processEvent(evt);
	}// GEN-LAST:event_T_PlayerMousePressed

	private void B_SubmitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_B_SubmitActionPerformed
		submitMsg(CB_Channel.getSelectedIndex());
	}// GEN-LAST:event_B_SubmitActionPerformed

	private void TF_MsgKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_TF_MsgKeyPressed
		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			submitMsg(CB_Channel.getSelectedIndex());
		}
	}// GEN-LAST:event_TF_MsgKeyPressed

	private void submitMsg(int select) {
		if (TF_Msg.getText().equals("")) {
			return;
		}
		switch (select) {
		case 0:// 讯息频
			L1World.getInstance().broadcastServerMessage(
					"【管理器讯息】:" + TF_Msg.getText());
			addWorldChat("【管理器】", TF_Msg.getText());
			break;
		case 1:// 密频
			if (L1World.getInstance().getPlayer(TF_Target.getText()) == null) {
				return;
			}
			L1PcInstance target = L1World.getInstance().getPlayer(
					TF_Target.getText());
			target.sendPackets(new S_SystemMessage("【管理器密语】:"
					+ TF_Msg.getText()));
			addPrivateChat("【管理器】", TF_Target.getText(), TF_Msg.getText());
			break;
		}
		TF_Msg.setText("");
	}

	/*
	 * 显示物品 0:身上 1:仓库 2:血盟仓库3:妖森仓库
	 */
	private void showItemTable(int num) {
		iniTable();
		L1PcInstance pc = L1PcInstance.load(TF_Name.getText());
		if (pc.getInventory().getSize() == 0) {
			C_LoginToServer.items(pc);
		}
		L1Inventory inv = null;
		switch (num) {
		case 0: // 0:身上
			if (pc.getInventory() == null) {
				return;
			}
			inv = pc.getInventory();
			D_Item.setTitle("角色身上物品");
			for (L1ItemInstance item : inv.getItems()) {
				addItemTable(item.getName(), item.getCount(), item.getItemId());
			}
			break;
		case 1: // 1:仓库
			if (pc.getDwarfInventory() == null) {
				return;
			}
			D_Item.setTitle("个人仓库物品");
			inv = pc.getDwarfInventory();
			for (L1ItemInstance item : inv.getItems()) {
				addItemTable(item.getName(), item.getCount(), item.getItemId());
			}
			break;
		case 2: // 2:血盟仓库
			if (pc.getClan().getDwarfForClanInventory() == null) {
				return;
			}
			D_Item.setTitle("血盟仓库物品");
			inv = pc.getClan().getDwarfForClanInventory();
			for (L1ItemInstance item : inv.getItems()) {
				addItemTable(item.getName(), item.getCount(), item.getItemId());
			}
			break;
		case 3: // 3:妖森仓库
			if (pc.getDwarfForElfInventory() == null) {
				return;
			}
			D_Item.setTitle("妖森仓库物品");
			inv = pc.getDwarfForElfInventory();
			for (L1ItemInstance item : inv.getItems()) {
				addItemTable(item.getName(), item.getCount(), item.getItemId());
			}
			break;
		}
		D_Item.setVisible(true);
	}

	private void B_ItemActionPerformed(java.awt.event.ActionEvent evt) {
		showItemTable(CB_Item.getSelectedIndex());
	}

	/*
	 * private void TP_ItemStateChanged(javax.swing.event.ChangeEvent evt) {
	 * //showItemTable(TP_Item.getSelectedIndex()); //showItemTable(0);
	 * //System.out.println("1"); //setPlayerView("asdsad"); }
	 */

	private void setPlayerView(String name) {
		L1PcInstance pc = L1PcInstance.load(name);
		int job = 0;
		switch (pc.getClassId()) {
		case L1PcInstance.CLASSID_PRINCE:
			job = 715;
			break;
		case L1PcInstance.CLASSID_PRINCESS:
			job = 647;
			break;
		case L1PcInstance.CLASSID_KNIGHT_MALE:
			job = 384;
			break;
		case L1PcInstance.CLASSID_KNIGHT_FEMALE:
			job = 317;
			break;
		case L1PcInstance.CLASSID_ELF_MALE:
			job = 247;
			break;
		case L1PcInstance.CLASSID_ELF_FEMALE:
			job = 198;
			break;
		case L1PcInstance.CLASSID_WIZARD_MALE:
			job = 532;
			break;
		case L1PcInstance.CLASSID_WIZARD_FEMALE:
			job = 452;
			break;
		case L1PcInstance.CLASSID_DARK_ELF_MALE:
			job = 145;
			break;
		case L1PcInstance.CLASSID_DARK_ELF_FEMALE:
			job = 25;
			break;
		case L1PcInstance.CLASSID_DRAGON_KNIGHT_MALE:
			job = 903;
			break;
		case L1PcInstance.CLASSID_DRAGON_KNIGHT_FEMALE:
			job = 930;
			break;
		case L1PcInstance.CLASSID_ILLUSIONIST_MALE:
			job = 1029;
			break;
		case L1PcInstance.CLASSID_ILLUSIONIST_FEMALE:
			job = 1056;
			break;

		}
		ImageIcon imageIcon = new ImageIcon("img/" + job + ".png");
		// ImageIcon imageIcon = new ImageIcon("img/"+job+".png");
		Icon icon = (Icon) imageIcon;
		L_Image.setIcon(icon);
		TF_Account.setText(pc.getAccountName());
		TF_Name.setText(pc.getName());
		TF_Title.setText(pc.getTitle());
		TF_AccessLevel.setText("" + pc.getAccessLevel());
		TF_Sex.setText((pc.get_sex() == 1) ? "女" : "男");
		TF_Ac.setText(pc.getAc() + "");
		TF_Cha.setText(pc.getCha() + "");
		TF_Int.setText(pc.getInt() + "");
		TF_Str.setText(pc.getStr() + "");
		TF_Con.setText(pc.getCon() + "");
		TF_Wis.setText(pc.getWis() + "");
		TF_Dex.setText(pc.getDex() + "");
		TF_Exp.setText(pc.getExp() + "");
		TF_Map.setText(pc.getMapId() + "");
		TF_X.setText(pc.getX() + "");
		TF_Y.setText(pc.getY() + "");
		TF_Clan.setText(pc.getClanname());
		TF_Level.setText(pc.getLevel() + "");
		TF_Hp.setText(pc.getCurrentHp() + " / " + pc.getMaxHp());
		TF_Mp.setText(pc.getCurrentMp() + " / " + pc.getMaxMp());
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new J_Main().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton B_Item;

	private javax.swing.JButton B_Submit;

	private javax.swing.JComboBox CB_Channel;

	private javax.swing.JComboBox CB_Item;

	private javax.swing.JDialog D_Item;

	private javax.swing.JFrame F_Player;

	private javax.swing.JLabel L_AccessLevel;

	private javax.swing.JLabel L_AccessLevel7;

	private javax.swing.JLabel L_Account;

	private javax.swing.JLabel L_Cha;

	private javax.swing.JLabel L_Clan;

	private javax.swing.JLabel L_Con;

	private javax.swing.JLabel L_Dex;

	private javax.swing.JLabel L_Exp;

	private javax.swing.JLabel L_Hp;

	private javax.swing.JLabel L_Image;

	private javax.swing.JLabel L_Int;

	private javax.swing.JLabel L_Leavl;

	private javax.swing.JLabel L_Map;

	private javax.swing.JLabel L_Mp;

	private javax.swing.JLabel L_Mp1;

	private javax.swing.JLabel L_Name;

	private javax.swing.JLabel L_Str;

	private javax.swing.JLabel L_Title;

	private javax.swing.JLabel L_Wis;

	private javax.swing.JLabel L_X;

	private javax.swing.JLabel L_Y;

	private javax.swing.JMenuBar MB;

	// 增加管理器倍数 by 24918380
	private javax.swing.JMenuItem MI_LA; // 正义倍率
	private javax.swing.JMenuItem MI_ATTR_ENCHANT_CHANCE;
	private javax.swing.JMenuItem MI_CHANCE_ARMOR;
	private javax.swing.JMenuItem MI_CHANCE_WEAPON;
	private javax.swing.JMenuItem MI_XP_PET;

	// 在线修改经验/掉宝/掉钱倍率 by cwt123
	private javax.swing.JMenuItem MI_Exp;
	private javax.swing.JMenuItem MI_Drop;
	private javax.swing.JMenuItem MI_Adena;

	private javax.swing.JMenuItem MI_AllBuff;

	private javax.swing.JMenuItem MI_AllRess;

	private javax.swing.JMenuItem MI_Angel;

	private javax.swing.JMenuItem MI_BanIP;

	private javax.swing.JMenuItem MI_Close;

	// GM 选单
	private javax.swing.JMenuItem MI_GMHTML_GBK;
    private javax.swing.JMenuItem MI_GMHTML_TW;

	private javax.swing.JMenuItem MI_Kill;

	private javax.swing.JMenuItem MI_Save;

	private javax.swing.JMenuItem MI_SetClose;

	private javax.swing.JMenuItem MI_ShowPlayer;

	private javax.swing.JMenuItem MI_Whisper;

	private javax.swing.JMenu M_Edit;

	private javax.swing.JMenu M_File;

	private javax.swing.JMenu M_Special;

	private javax.swing.JPopupMenu PM_Player;

	private javax.swing.JScrollPane SP_;

	private javax.swing.JScrollPane SP_AllChat;

	private javax.swing.JScrollPane SP_Clan;

	private javax.swing.JScrollPane SP_Consol;

	private javax.swing.JScrollPane SP_Normal;

	private javax.swing.JSplitPane SP_Split;

	private javax.swing.JScrollPane SP_Team;

	private javax.swing.JScrollPane SP_World;

	private javax.swing.JScrollPane SP_player;

	private javax.swing.JTextArea TA_AllChat;

	private javax.swing.JTextArea TA_Clan;

	private javax.swing.JTextArea TA_Consol;

	private javax.swing.JTextArea TA_Normal;

	private javax.swing.JTextArea TA_Private;

	private javax.swing.JTextArea TA_Team;

	private javax.swing.JTextArea TA_World;

	private javax.swing.JTextField TF_Ac;

	private javax.swing.JTextField TF_AccessLevel;

	private javax.swing.JTextField TF_Account;

	private javax.swing.JTextField TF_Cha;

	private javax.swing.JTextField TF_Clan;

	private javax.swing.JTextField TF_Con;

	private javax.swing.JTextField TF_Dex;

	private javax.swing.JTextField TF_Exp;

	private javax.swing.JTextField TF_Hp;

	private javax.swing.JTextField TF_Int;

	private javax.swing.JTextField TF_Level;

	private javax.swing.JTextField TF_Map;

	private javax.swing.JTextField TF_Mp;

	private javax.swing.JTextField TF_Msg;

	private javax.swing.JTextField TF_Name;

	private javax.swing.JTextField TF_Sex;

	private javax.swing.JTextField TF_Str;

	private javax.swing.JTextField TF_Target;

	private javax.swing.JTextField TF_Title;

	private javax.swing.JTextField TF_Wis;

	private javax.swing.JTextField TF_X;

	private javax.swing.JTextField TF_Y;

	private javax.swing.JTabbedPane TP;

	private javax.swing.JTable T_Item;

	private javax.swing.JTable T_Player;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JSeparator jSeparator1;

	private javax.swing.JSeparator jSeparator2;

	private javax.swing.JSeparator jSeparator3;

	// End of variables declaration//GEN-END:variables

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ((e.getModifiers() & ActionEvent.MOUSE_EVENT_MASK) == 0) {
			if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0
					|| (e.getModifiers() & MouseEvent.BUTTON2_MASK) != 0) {
				return;
			}
		}
		if (command.equals("强制踢除(K)")) {
			L1PcInstance target = L1World.getInstance().getPlayer(
					(String) DTM.getValueAt(select, 1));
			if (target != null) {
				addConsol((new StringBuilder()).append(
						"您把玩家：" + (String) DTM.getValueAt(select, 1)
								+ "强制剔除游戏。").toString());
				target.sendPackets(new S_Disconnect());
			} else {
				addConsol("此玩家不在线上。");
			}
		} else if (command.equals("封锁IP(B)")) {
			String ip = (String) DTM.getValueAt(select, 2);
			IpTable iptable = IpTable.getInstance();
			if (iptable.isBannedIp(ip)) { // 已经是被封锁IP了
				return;
			}
			for (L1PcInstance tg : L1World.getInstance().getAllPlayers()) {
				if (ip.equals(tg.getNetConnection().getIp())) {
					String msg = new StringBuilder().append("IP:").append(ip)
							.append(" 连线中的人物:").append(tg.getName())
							.append(" 管理员封锁IP!").toString();
					L1World.getInstance().broadcastServerMessage(msg);
				}
			}
			iptable.banIp(ip); // BANリストへIPを加える
			String msg = new StringBuilder().append("IP:").append(ip)
					.append(" 登录BAN IP了。").toString();
			addConsol(msg);
			// perform copy operation
		} else if (command.equals("玩家资料(P)")) {
			setPlayerView((String) DTM.getValueAt(select, 1));
			F_Player.pack();
			F_Player.setVisible(true);
			// perform paste operation
		} else if (command.equals("密语(W)")) {
			TF_Target.setText((String) DTM.getValueAt(select, 1));
			CB_Channel.setSelectedIndex(1);
		} else if (command.equals("储存讯息(S)")) {
			saveChatData(false);
		} else if (command.equals("大天使祝福(A)")) {
			angel();
		} else if (command.equals("关闭伺服器(C)")) {
			closeServer();
		}

		// 增加管理器倍数 by 24918380
		else if (command.equals("正义值倍率(W)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器正义值倍率：" + Config.RATE_LA
						+ " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.RATE_LA = second;
				L1World.getInstance().broadcastServerMessage(
						"正义值倍率变更为：" + Config.RATE_LA + "倍。");
				addConsol(" 正义值倍率变更为：" + Config.RATE_LA + "倍。");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("属性强化倍率(X)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器属性强化倍率："
						+ Config.ATTR_ENCHANT_CHANCE + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.ATTR_ENCHANT_CHANCE = second;
				L1World.getInstance().broadcastServerMessage(
						"属性强化率变更为：" + Config.ATTR_ENCHANT_CHANCE + "%。");
				addConsol(" 属性强化率变更为：" + Config.ATTR_ENCHANT_CHANCE + "%。");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("防具强化倍率(S)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器防具强化倍率："
						+ Config.ENCHANT_CHANCE_ARMOR + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.ENCHANT_CHANCE_ARMOR = second;
				L1World.getInstance().broadcastServerMessage(
						"防具强化率变更为：" + Config.ENCHANT_CHANCE_ARMOR + "%。");
				addConsol(" 防具强化率变更为：" + Config.ENCHANT_CHANCE_ARMOR + "%。");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("武器强化倍率(Q)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器武器强化倍率："
						+ Config.ENCHANT_CHANCE_WEAPON + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.ENCHANT_CHANCE_WEAPON = second;
				L1World.getInstance().broadcastServerMessage(
						"武器强化率变更为：" + Config.ENCHANT_CHANCE_WEAPON + "%。");
				addConsol(" 武器强化率变更为：" + Config.ENCHANT_CHANCE_WEAPON + "%。");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("宠物经验倍率(G)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器宠物经验倍率："
						+ Config.RATE_XP + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.RATE_XP = second;
				L1World.getInstance().broadcastServerMessage(
						"宠物经验倍率变更为：" + Config.RATE_XP + "倍。");
				addConsol(" 宠物经验倍率变更为：" + Config.RATE_XP + "倍。");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		}
		// 增加管理器倍数 by 24918380

		// 在线修改经验/掉宝/掉钱倍率 by cwt123
		else if (command.equals("经验倍率(F)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前伺服器经验倍率："
						+ Config.RATE_XP + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.RATE_XP = second;
				L1World.getInstance().broadcastServerMessage(
						"游戏经验倍率变更为：" + Config.RATE_XP);
				addConsol(" 游戏经验倍率变更为：" + Config.RATE_XP);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("掉钱倍率(M)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前服务器掉钱倍率："
						+ Config.RATE_DROP_ADENA + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.RATE_DROP_ADENA = second;
				L1World.getInstance().broadcastServerMessage(
						"掉钱率变更为：" + Config.RATE_DROP_ADENA);

				addConsol(" 掉钱率变更为：" + Config.RATE_DROP_ADENA);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("掉宝率(D)")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("当前服务器掉宝倍率："
						+ Config.RATE_DROP_ITEMS + " 请输入新倍率：");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);

				Config.RATE_DROP_ITEMS = second;
				L1World.getInstance().broadcastServerMessage(
						"掉宝率变更为：" + Config.RATE_DROP_ITEMS);
				addConsol(" 掉宝率变更为：" + Config.RATE_DROP_ITEMS);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		}
		// 在线修改经验/掉宝/掉钱倍率 by cwt123

		else if (command.equals("设定关闭伺服器(E)...")) {
			String temp = "";
			try {
				temp = JOptionPane.showInputDialog("请输入多长时间以后重开(单位：分钟)!");
				if (temp == null || temp.equals("")) {
					return;
				}
				int second = Integer.valueOf(temp);
				if (second == 0) {
					closeServer();
				}
				GameServer.getInstance().shutdownWithCountdown(second * 60);
				L1World.getInstance().broadcastServerMessage(
						"伺服器将于(" + second + ")分钟后关闭！");
				addWorldChat("管理器", "伺服器将于(" + second + ")分钟后关闭！");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "请输入整数!");
			}
		} else if (command.equals("终极祝福(B)")) {
			int[] allBuffSkill = { LIGHT, DECREASE_WEIGHT,
					PHYSICAL_ENCHANT_DEX, MEDITATION, PHYSICAL_ENCHANT_STR,
					BLESS_WEAPON, BERSERKERS, IMMUNE_TO_HARM, ADVANCE_SPIRIT,
					REDUCTION_ARMOR, BOUNCE_ATTACK, SOLID_CARRIAGE,
					ENCHANT_VENOM, BURNING_SPIRIT, VENOM_RESIST, DOUBLE_BRAKE,
					UNCANNY_DODGE, DRESS_EVASION, GLOWING_AURA, BRAVE_AURA,
					RESIST_MAGIC, CLEAR_MIND, ELEMENTAL_PROTECTION,
					AQUA_PROTECTER, BURNING_WEAPON, IRON_SKIN, EXOTIC_VITALIZE,
					WATER_LIFE, ELEMENTAL_FIRE, SOUL_OF_FLAME, ADDITIONAL_FIRE };
			for (L1PcInstance targetpc : L1World.getInstance().getAllPlayers()) {
				L1BuffUtil.haste(targetpc, 3600 * 1000);
				L1BuffUtil.brave(targetpc, 3600 * 1000);
				switch (targetpc.getType()) {
				case 0:
				case 1: // 王子,骑士
					L1PolyMorph.doPoly(targetpc, 363, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈骑士
					break;
				case 2: // 妖精
					L1PolyMorph.doPoly(targetpc, 369, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈巡守
					break;
				case 3: // 法师
					L1PolyMorph.doPoly(targetpc, 365, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈法师
					break;
				case 4: // 黑妖
					L1PolyMorph.doPoly(targetpc, 367, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈刺客
					break;
				case 5: // 龙骑士
					L1PolyMorph.doPoly(targetpc, 363, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈骑士
					break;
				case 6: // 幻术师
					L1PolyMorph.doPoly(targetpc, 367, 7200,
							L1PolyMorph.MORPH_BY_GM); // 白金光圈刺客
					break;
				}
				for (int element : allBuffSkill) {
					// L1Skills skill =
					// SkillsTable.getInstance().getTemplate(allBuffSkill[i]);
					new L1SkillUse().handleCommands(targetpc, element,
							targetpc.getId(), targetpc.getX(), targetpc.getY(),
							null, 3600 * 1000, L1SkillUse.TYPE_GMBUFF);
				}

				targetpc.sendPackets(new S_ServerMessage(166,
						"奇缘祝福降临人世,全体玩家得到祝福."));
			}
		} else if (command.equals("全体复活补血魔(R)")) {
			for (L1PcInstance tg : L1World.getInstance().getAllPlayers()) {
				if (tg.getCurrentHp() == 0 && tg.isDead()) {
					tg.sendPackets(new S_SystemMessage("GM帮你复活噜。"));
					tg.broadcastPacket(new S_SkillSound(tg.getId(), 3944));
					tg.sendPackets(new S_SkillSound(tg.getId(), 3944));
					// 同祝福复活效果
					tg.setTempID(tg.getId());
					tg.sendPackets(new S_Message_YN(322, "")); // 是否要复活？（Y/N）
				} else {
					tg.sendPackets(new S_SystemMessage("GM帮你治愈噜。"));
					tg.broadcastPacket(new S_SkillSound(tg.getId(), 832));
					tg.sendPackets(new S_SkillSound(tg.getId(), 832));
					tg.setCurrentHp(tg.getMaxHp());
					tg.setCurrentMp(tg.getMaxMp());
				}
			}
		}

		// TODO GM选单
		else if (command.equals("GM选单产生(BIG5)")) {
        	new CreatGMHTML("c");
        }
        else if (command.equals("GM选单产生(GBK)")) {
        	new CreatGMHTML("h");
        }
	}

	// 加天神祝福NPC by eric1300460
	private void angel() {
		for (L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
			if (pc.hasSkillEffect(DECAY_POTION) == true) {	// 药水霜化术状态。
				pc.sendPackets(new S_ServerMessage(698));	// \f1喉咙灼热，无法喝东西。
				return;
			}
			int time = 3600;// 1小时

			// 解除绝对屏障状态
			if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) {
				pc.killSkillEffectTimer(ABSOLUTE_BARRIER);
				pc.startHpRegeneration();
				pc.startMpRegeneration();
				pc.startMpRegenerationByDoll();
			}
			// 勇水
			if (pc.hasSkillEffect(STATUS_ELFBRAVE)) { // 删除重复的状态
				pc.killSkillEffectTimer(STATUS_ELFBRAVE);
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
				pc.setBraveSpeed(0);
			}
			if (pc.hasSkillEffect(HOLY_WALK)) { // 删除重复的状态
				pc.killSkillEffectTimer(HOLY_WALK);
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
				pc.setBraveSpeed(0);
			}
			if (pc.hasSkillEffect(MOVING_ACCELERATION)) { // 删除重复的状态
				pc.killSkillEffectTimer(MOVING_ACCELERATION);
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
				pc.setBraveSpeed(0);
			}
			if (pc.hasSkillEffect(WIND_WALK)) { // 删除重复的状态
				pc.killSkillEffectTimer(WIND_WALK);
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
				pc.setBraveSpeed(0);
			}
			if (pc.hasSkillEffect(STATUS_RIBRAVE)) { // 删除重复的状态
				pc.killSkillEffectTimer(STATUS_RIBRAVE);
				// XXX 生命之树果实状态的消除方法不明
				pc.setBraveSpeed(0);
			}
			pc.sendPackets(new S_SkillBrave(pc.getId(), 1, time));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0));
			pc.sendPackets(new S_SkillSound(pc.getId(), 751));
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 751));
			pc.setSkillEffect(STATUS_BRAVE, time * 1000);
			pc.setBraveSpeed(1);
			// 绿水
			// 醉酒状态解除
			pc.setDrink(false);

			// 加速术、强力加速术ヘ一段加速 重复状态解除
			if (pc.hasSkillEffect(HASTE)) { // 加速术
				pc.killSkillEffectTimer(HASTE);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
				pc.setMoveSpeed(0);
			} else if (pc.hasSkillEffect(GREATER_HASTE)) { // 强力加速术
				pc.killSkillEffectTimer(GREATER_HASTE);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
				pc.setMoveSpeed(0);
			} else if (pc.hasSkillEffect(STATUS_HASTE)) { // 一段加速
				pc.killSkillEffectTimer(STATUS_HASTE);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
				pc.setMoveSpeed(0);
			}

			// 缓速术、集体缓速术、地面障碍  等 状态解除
			if (pc.hasSkillEffect(SLOW)) { // 缓速术
				pc.killSkillEffectTimer(SLOW);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			} else if (pc.hasSkillEffect(MASS_SLOW)) { // 集体缓速术
				pc.killSkillEffectTimer(MASS_SLOW);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			} else if (pc.hasSkillEffect(ENTANGLE)) { // 地面障碍
				pc.killSkillEffectTimer(ENTANGLE);
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			} else {
				pc.sendPackets(new S_SkillHaste(pc.getId(), 1, time));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
				pc.setMoveSpeed(1);
				pc.setSkillEffect(STATUS_HASTE, time * 1000); // 一段加速
			}

			// 体魄
			new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_STR,
					pc.getId(), pc.getX(), pc.getY(), null, time,
					L1SkillUse.TYPE_GMBUFF);
			// 通畅
			new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_DEX,
					pc.getId(), pc.getX(), pc.getY(), null, time,
					L1SkillUse.TYPE_GMBUFF);
			// 灵魂
			new L1SkillUse().handleCommands(pc, 79, pc.getId(), pc.getX(),
					pc.getY(), null, time, L1SkillUse.TYPE_GMBUFF);
			// pc.sendPackets(new S_SystemMessage("您感受到天神在给你祝福! 哈雷路亚~~~!"));
			pc.setCurrentHp(pc.getMaxHp());// 补血
			pc.setCurrentMp(pc.getMaxMp());// 补魔
		}
		L1World.getInstance().broadcastServerMessage("大天使祝福降临!所有玩家获得状态1小时!");
	}

	// 储存信息
	private void saveChatData(boolean bool) {
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d = Calendar.getInstance().getTime();
		String date = " " + sdfmt.format(d);
		try {
			// Consol
			FileOutputStream fos = new FileOutputStream("chatLog/Consol" + date
					+ ".txt");
			fos.write(TA_Consol.getText().getBytes());
			fos.close();
			// AllChat
			fos = new FileOutputStream("chatLog/AllChat" + date + ".txt");
			fos.write(TA_AllChat.getText().getBytes());
			fos.close();
			// World
			fos = new FileOutputStream("chatLog/World" + date + ".txt");
			fos.write(TA_World.getText().getBytes());
			fos.close();
			// Clan
			fos = new FileOutputStream("chatLog/Clan" + date + ".txt");
			fos.write(TA_Clan.getText().getBytes());
			fos.close();
			// Normal
			fos = new FileOutputStream("chatLog/Normal" + date + ".txt");
			fos.write(TA_Normal.getText().getBytes());
			fos.close();
			// Team
			fos = new FileOutputStream("chatLog/Team" + date + ".txt");
			fos.write(TA_Team.getText().getBytes());
			fos.close();
			// Whisper
			fos = new FileOutputStream("chatLog/Whisper" + date + ".txt");
			fos.write(TA_Private.getText().getBytes());
			fos.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void processEvent(MouseEvent e) {
		if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
			int modifiers = e.getModifiers();
			modifiers -= MouseEvent.BUTTON3_MASK;
			modifiers |= MouseEvent.BUTTON1_MASK;
			MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(),
					e.getWhen(), modifiers, e.getX(), e.getY(),
					e.getClickCount(), false);
			T_Player.dispatchEvent(ne);
		}
	}
}
