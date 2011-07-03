package l1j.eric.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
//import l1j.server.server.datatables.CastleTable;
//import l1j.server.server.templates.L1Castle;
import l1j.server.server.utils.SQLUtil;

/**
 *	产生 GM 管理菜单
 */

public class CreatGMHTML {

	private static Logger _log = Logger.getLogger(CreatGMHTML.class.getName());

	public CreatGMHTML(String language) {
		String lan = "BIG5";
		if(language.equals("h")) {
			lan = "GBK";
		}
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			String html = "-"+language+".html";
			con = L1DatabaseFactory.getInstance().getConnection();


			// 读取道具资料表数据
			pstm = con.prepareStatement("SELECT * FROM etcitem ORDER BY item_type ASC, item_id ASC");
			rs = pstm.executeQuery();
			String path = "gmHTML/";
			File file;
			String tempItemName="";
			FileOutputStream fos = null;
			FileOutputStream fetcitem = new FileOutputStream("gmHTML/gmetcitem"+html);
			String t="<body>物品清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("item_type"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("item_type");
					file=new File(path+"gm"+tooLong(tempItemName)+html);
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gm"+tooLong(tempItemName)+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand item "+rs.getInt("item_id")+
					" 1 0 1\"> "+rs.getString("name")+"("+rs.getInt("item_id")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();


			// 读取装备资料表数据
			String str = "aromr";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM armor ORDER BY type ASC, item_id ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>防具清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("type"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("type");
					file=new File(path+"gm"+tooLong(tempItemName)+html);
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gm"+tooLong(tempItemName)+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand item "+rs.getInt("item_id")+
					" 1 0 1\"> "+rs.getString("name")+"("+rs.getInt("item_id")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();


			// 读取武器资料表数据
			str = "weapon";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM weapon ORDER BY type ASC, item_id ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>武器清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("type"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("type");
					file=new File(path+"gm"+tooLong(tempItemName)+html);
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gm"+tooLong(tempItemName)+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand item "+rs.getInt("item_id")+
					" 1 0 1\"> "+rs.getString("name")+"("+rs.getInt("item_id")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();	


			// 读取怪物资料表数据
			str = "npc";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM npc where impl!='L1Monster' ORDER BY impl ASC, npcid ASC");
			rs = pstm.executeQuery();
			tempItemName="1";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>增加NPC清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("impl"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("1")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("impl");
					file=new File(path+"gm"+tooLong(tempItemName)+html);
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gm"+tooLong(tempItemName)+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand insert npc "+rs.getInt("npcid")+
					"\"> "+rs.getString("name")+"("+rs.getString("impl")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();		


			//***************insert mob
			str = "mob";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM npc where impl='L1Monster' ORDER BY impl ASC, npcid ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>增加怪物清单(汇入资料库)<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("impl"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("impl");
					file=new File(path+"gm"+tooLong(tempItemName)+html);
					
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gm"+tooLong(tempItemName)+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand insert mob "+rs.getInt("npcid")+
					"\"> "+rs.getString("name")+"("+rs.getInt("npcid")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();	


			//***************spawn mob
			str = "spawn";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM npc where impl='L1Monster' ORDER BY impl ASC, npcid ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>召唤怪物清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("impl"))) {
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("impl");
					file=new File(path+"gmspawnMob"+html);
					
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gmspawnMob"+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand spawn "+rs.getInt("npcid")+
					"\"> "+rs.getString("name")+"("+rs.getInt("npcid")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();


			//***************summon mob
			str = "summon";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM npc where impl='L1Monster' ORDER BY impl ASC, npcid ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>召唤宠物清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			while(rs.next()) {
				if(!tempItemName.equals(rs.getString("impl"))){
					String s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
					if(!tempItemName.equals("")) {
						fos.write(s.getBytes(lan));
					}
					tempItemName=rs.getString("impl");
					file=new File(path+"gmsummonMob"+html);
					fos=new FileOutputStream(file);
					s= "<body><br><font fg=FFFFCC>"+tempItemName+"</font><br><br>\r\n";
					fos.write(s.getBytes(lan));
					t="<a link=\""+"gmsummonMob"+"\"><font fg=FFFFCC> "+tempItemName+" </font></a><br>\r\n";
					fetcitem.write(t.getBytes(lan));
				}
				String q="<a action=\"gmcommand summon "+rs.getInt("npcid")+
					"\"> "+rs.getString("name")+"("+rs.getInt("npcid")+") </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();


			//***************teleport
			str = "teleport";
			path = "gmHTML/";
			pstm = con.prepareStatement("SELECT * FROM etcitem where item_type='scroll' and locx!=0 ORDER BY item_id ASC");
			rs = pstm.executeQuery();
			tempItemName="";
			fos = null;
			fetcitem = new FileOutputStream("gmHTML/gm"+str+html);
			t="<body>传送清单<br><font fg=996600>==============================</font><br><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			file=new File(path+"gmteleport2"+html);
			fos=new FileOutputStream(file);
			t="<a link=\""+"gmteleport2\"><font fg=FFFFCC> 传送清单 </font></a><br>\r\n";
			fetcitem.write(t.getBytes(lan));
			String s;
			s= "<body><br><font fg=FFFFCC>teleport</font><br><br>\r\n";
			fos.write(s.getBytes(lan));
			while(rs.next()) {

				String q="<a action=\"gmcommand move "+rs.getInt("locx")+" "+rs.getInt("locy")+" "+rs.getInt("mapid")+
					"\"> "+rs.getString("name")+" </a><br>\r\n";
				fos.write(q.getBytes(lan));
			}
			s = "<br><br><img src=\"#331\" link=\"gmadmin\"><br></body>\r\n";
			fos.write(s.getBytes(lan));
			t="<br><br><img src=\"#331\" link=\"gmadmin\"></body>\r\n";
			fetcitem.write(t.getBytes(lan));
			fetcitem.flush();
			fetcitem.close();
		} catch (SQLException e) {
			_log.log(Level.INFO, e.getLocalizedMessage(), e);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private String tooLong(String s) {
		if(s.length()>10) {
			return s.substring(0, 10);
		}
		return s;
	}
}
