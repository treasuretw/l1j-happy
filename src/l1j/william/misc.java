package l1j.william;

import l1j.server.Server;
import l1j.server.server.ClientThread;
import l1j.server.server.IdFactory;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NpcChatPacket;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Item;
import l1j.server.server.templates.L1Npc;
import l1j.server.L1DatabaseFactory;

import java.util.ArrayList;
import java.sql.*;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.ResourceBundle;

/**
 *
 */
public class misc {

	private static ArrayList<ArrayList<Object>> aData = new ArrayList<ArrayList<Object>>();
	private static boolean NO_MORE_GET_DATA = false;
	@SuppressWarnings({ "rawtypes", "unused" })
	private static ArrayList aData2 = new ArrayList();
	@SuppressWarnings("unused")
	private static boolean NO_MORE_GET_DATA2 = false;
	private static ArrayList<ArrayList<Object>> aData3 = new ArrayList<ArrayList<Object>>();
	private static boolean NO_MORE_GET_DATA3 = false;
	private static ArrayList<int[]> aData4 = new ArrayList<int[]>();
	private static boolean NO_MORE_GET_DATA4 = false;
	private static ArrayList<int[]> aData5 = new ArrayList<int[]>();
	private static boolean NO_MORE_GET_DATA5 = false;
	@SuppressWarnings("rawtypes")
	private static ArrayList<ArrayList<Comparable>> aData6 = new ArrayList<ArrayList<Comparable>>();
	private static boolean NO_MORE_GET_DATA6 = false;
	private static ArrayList<ArrayList<Object>> aData8 = new ArrayList<ArrayList<Object>>();
	private static boolean NO_MORE_GET_DATA8 = false;
	@SuppressWarnings({ "rawtypes", "unused" })
	private static ArrayList aData9 = new ArrayList();
	@SuppressWarnings("unused")
	private static boolean NO_MORE_GET_DATA9 = false;
	@SuppressWarnings({ "unused", "rawtypes" })
	private static ArrayList aData10 = new ArrayList();
	@SuppressWarnings("unused")
	private static boolean NO_MORE_GET_DATA10 = false;
	// 兑换任务,成套变身, 资料栏位用什么分割,预设是","
	public static final String TOKEN = ",";
    // i18n
	private static ResourceBundle resource = ResourceBundle.getBundle("l1j.william.misc");

	public static void main(String a[]) {
		while(true) {
			try {
				Server.main(null);
			} catch(Exception ex) {
			}
		}
	}
	// 不让使用
	private misc(L1PcInstance owner) {
		_owner = owner;
	}

	// 怪物讲话
	public  static void forL1MonsterTalking(L1NpcInstance monster,int iType ) {
		@SuppressWarnings("rawtypes")
		ArrayList<Comparable> aTempData = null;
		@SuppressWarnings("unused")
		int[] iTemp = null;
		if(!NO_MORE_GET_DATA6) {
			NO_MORE_GET_DATA6 = true;
			getData6();
		}
		for(int i=0;i<aData6.size();i++) {
			aTempData = aData6.get(i);
			java.util.Random random = new java.util.Random();
			//System.out.println("data nid="+aTempData.get(0)+",monster name id="+monster.getNpcTemplate().get_npcId() );
			if( ((Integer) aTempData.get(3)).intValue()==iType && ((Integer) aTempData.get(0)).intValue() == monster.getNpcTemplate().get_npcId()  )
  			  if( ((Integer)aTempData.get(1) ).intValue() >= random.nextInt(100) ){			
	    	     monster.broadcastPacket(new S_NpcChatPacket(monster, (String)aTempData.get(2)  , 2)); 
			}
		}
    }

	@SuppressWarnings("rawtypes")
	private static void getData6() {
        java.sql.Connection con = null;
        try {
          con = L1DatabaseFactory.getInstance().getConnection();
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william_npc_talk");
		ArrayList<Comparable> aReturn = null;
	      @SuppressWarnings("unused")
		String sTemp = null;
          if( rset!=null)
            while (rset.next()) {
	    	  aReturn = new ArrayList<Comparable>();
	    	  aReturn.add(0, new Integer(rset.getInt("npc_id")));
	    	  aReturn.add(1, new Integer(rset.getInt("random")));
	    	  aReturn.add(2, rset.getString("talktext"));
	    	  aReturn.add(3, new Integer(rset.getInt("type")));
	    	  aData6.add(aReturn);
            }
          if(con!=null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex) {

        }
	}

	// 特定地点,使用物品触发怪物
	public static void forRequestItemUSe(ClientThread client,L1ItemInstance itemInstance){

		L1PcInstance user = client.getActiveChar();

		int aTempData[] = null;
		int iX = user.getX();
		int iY = user.getY();
		int iMap = user.getMapId();
		int iX1 = 0;
		int iY1 = 0;
		int iX2 = 0;
		int iY2 = 0;
		short iMap2 = 0;
		int itemid = itemInstance.getItemId();
		if(!NO_MORE_GET_DATA5) {
			NO_MORE_GET_DATA5 = true;
		  getData5();
		}
		for(int i=0; i<aData8.size(); i++) {

	          if( itemid == ((Integer) aData8.get(i).get(0)).intValue() ) {
	        	  user.sendPackets(new S_SystemMessage( (new java.lang.StringBuilder().append(user.getName()).append(resource.getString("weapon.upgrade.message1") )).toString()));
				  user.sendPackets(new S_SystemMessage( (new java.lang.StringBuilder().append(resource.getString("weapon.upgrade.message2")  + misc.getWeaponExp( itemInstance.getId())+"/"+ ((Integer) aData8.get(i).get(1)).intValue()  )).toString()));
				  break;
	          }
		}
		//System.out.println("data5 size=" + aData5.size());
		for(int i=0; i<aData5.size(); i++) {
			aTempData = aData5.get(i);
			iX1 = aTempData[4];
			iY1 = aTempData[5];
			iX2 = aTempData[6];
			iY2 = aTempData[7];
			iMap2 = (short)aTempData[8];
			//System.out.println("minx="+iX1+",miny="+iY1+",maxx="+iX2+",maxy="+iY2+",map2="+iMap2+",x="+iX+",y="+iY+",map="+iMap);
			//System.out.println("minx="+iX1+",miny="+iY1+",maxx="+iX2+",maxy="+iY2+",map2="+iMap2+",x="+iX+",y="+iY+",map="+iMap);
			//System.out.println("itemid ="+aTempData[0]+",current item id ="+itemid);
			if( iMap==iMap2 )
              if( iX>=iX1 && iY>=iY1 && iX<=iX2 && iY<=iY2  ) {
            	if( aTempData[0]==itemid )
  			      if( user.getInventory().checkItem(aTempData[0],aTempData[1]) ) {
  			        try {
				      if( aTempData[2]==1 ) {
				        for(int j=0;j<aTempData[1];j++) {
		  			      L1ItemInstance item = user.getInventory().findItemId(aTempData[0]);
					      user.getInventory().removeItem(item.getId(), 1);
					    }
 			          }
			          //GMCommands aGM = GMCommands.getInstance();
			          mobspawn(client, aTempData[3]);
				    } catch (Exception e) {
				    }
  			      }
              }
		}
	}

	private static void getData5() {
        java.sql.Connection con = null;
        try {
          con = L1DatabaseFactory.getInstance().getConnection();
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william5");
	      int[] aReturn = null;
	      @SuppressWarnings("unused")
		String sTemp = null;
          if( rset!=null)
            while (rset.next()) {
	    	  aReturn = new int[9];
	    	  aReturn[0]=rset.getInt("materials");
	    	  aReturn[1]=rset.getInt("counts");
	    	  aReturn[2]=rset.getInt("destroy");
	    	  aReturn[3]=rset.getInt("monster_id");
	    	  aReturn[4]=rset.getInt("location_minx");
	    	  aReturn[5]=rset.getInt("location_miny");
	    	  aReturn[6]=rset.getInt("location_maxx");
	    	  aReturn[7]=rset.getInt("location_maxy");
	    	  aReturn[8]=rset.getInt("map_id");
	    	  aData5.add(aReturn);
            }
          if(con!=null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex) {
        }
	}

	// 移动触发怪物
	public static void forCRequestMoveChar(ClientThread client) {
		L1PcInstance user = client.getActiveChar();
		int aTempData[] = null;
		@SuppressWarnings("unused")
		int id = 0;
		@SuppressWarnings("unused")
		int iDamage=0;
		if (!NO_MORE_GET_DATA4) {
			NO_MORE_GET_DATA4 = true;
		  getData4();
		}
		for (int i=0; i<aData4.size(); i++) {
			aTempData = aData4.get(i);
			if (user.getInventory().checkItem(aTempData[0],aTempData[1])) {

			  java.util.Random random = new java.util.Random();
			  if (aTempData[3] >= random.nextInt(100)) {
			    try {
				  if (aTempData[2] == 1) {
				    for (int j=0; j<aTempData[1]; j++) {
		  			    L1ItemInstance item = user.getInventory().findItemId(aTempData[0]);
					    user.getInventory().removeItem(item.getId(), 1);
					}
 			      }
			    //  GMCommands aGM = GMCommands.getInstance();
			      mobspawn(client, aTempData[4]);
				} catch (Exception e) {
				}
			  }
			}
		}
	}

	private static void getData4() {
        java.sql.Connection con = null;
        try {
          con = L1DatabaseFactory.getInstance().getConnection();
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william4");
	      int[] aReturn = null;
	      @SuppressWarnings("unused")
		String sTemp = null;
          if (rset != null)
            while (rset.next()) {
	    	  aReturn = new int[5];
	    	  aReturn[0]=rset.getInt("materials");
	    	  aReturn[1]=rset.getInt("counts");
	    	  aReturn[2]=rset.getInt("destroy");
	    	  aReturn[3]=rset.getInt("random");
	    	  aReturn[4]=rset.getInt("monster_id");
	    	  aData4.add(aReturn);
            }
          if (con != null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex) {
        }
	}

	// 变身
	public static ArrayList<ArrayList<Object>> getC_ArmorSetData() {
		if (!NO_MORE_GET_DATA3) {
			NO_MORE_GET_DATA3 = true;
			  getData3();
		}
		return aData3;
	}

	private static void getData3() {
        java.sql.Connection con = null;
        try {
          con = L1DatabaseFactory.getInstance().getConnection();
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william3");
	      ArrayList<Object> aReturn = null;
	      @SuppressWarnings("unused")
		String sTemp = null;
          if (rset != null)
            while (rset.next()) {
	    	  aReturn = new ArrayList<Object>();
	    	  aReturn.add(0, getArray( rset.getString("sets"),TOKEN,1));
	    	  aReturn.add(1, new Integer(rset.getInt("polyid")));
	    	  aReturn.add(2, new Integer(rset.getInt("SkillIconGFX1")));
	    	  aReturn.add(3, new Integer(rset.getInt("SkillIconGFX2")));
	    	  aReturn.add(4, new Integer(rset.getByte("str")));
	    	  aReturn.add(5, new Integer(rset.getByte("dex")));
	    	  aReturn.add(6, new Integer(rset.getByte("con")));
	    	  aReturn.add(7, new Integer(rset.getByte("wis")));
	    	  aReturn.add(8, new Integer(rset.getByte("cha")));
	    	  aReturn.add(9, new Integer(rset.getByte("inte")));
	    	  aReturn.add(10, new Integer(rset.getInt("ac")));
	    	  aReturn.add(11, new Integer(rset.getInt("hp")));
	    	  aReturn.add(12, new Integer(rset.getInt("mp")));
	    	  aReturn.add(13, new Integer(rset.getInt("hpr")));
	    	  aReturn.add(14, new Integer(rset.getInt("mpr")));
	    	  aReturn.add(15, new Integer(rset.getInt("defense_fire")));
	    	  aReturn.add(16, new Integer(rset.getInt("defense_wind")));
	    	  aReturn.add(17, new Integer(rset.getInt("defense_water")));
	    	  aReturn.add(18, new Integer(rset.getInt("defense_earth")));
	    	  aReturn.add(19, new Integer(rset.getInt("defense_magic")));
	    	  aReturn.add(20, new Integer(rset.getInt("resist_freeze")));
	    	  aReturn.add(21, new Integer(rset.getInt("resist_sleep")));
	    	  aReturn.add(22, new Integer(rset.getInt("resist_stan")));
	    	  aReturn.add(23, new Integer(rset.getInt("resist_stone")));
	    	  aData3.add(aReturn);
            }
          if (con != null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
	}

	// 20070828 Carrey0824 add 武器发动魔法或技能使用"范围攻击"计算 use chrisliu code
	@SuppressWarnings("unused")
	private static void areaskill (L1PcInstance npc, int d, int vis) {
		//(以自身)计算攻击范围-使用方式areaskill(player,(int)d,几格的范围)
		for (L1Object visibleObjects : L1World.getInstance().getVisibleObjects(npc, vis)) {
			if (visibleObjects == null)
				continue;

			if (visibleObjects instanceof L1MonsterInstance) {
				L1NpcInstance targetNpc = (L1NpcInstance) visibleObjects;
				targetNpc.receiveDamage(npc, d); // 怪被范围魔法打死的怪经验会给玩家
			}
		}
	}

	// 兑换活动
	public static ArrayList<Object> forRequestNPCAction (String s,L1PcInstance l1pcinstance) {
		ArrayList<Object> aTempData = null;
		ArrayList<Object> aTempData8 = null;
		int iExp = 0;
		if (!NO_MORE_GET_DATA) {
			NO_MORE_GET_DATA = true;
		  getData();
		}
		if (!NO_MORE_GET_DATA8) {
			NO_MORE_GET_DATA8 = true;
			  getData8();
		}
		if (s.equalsIgnoreCase("upgrade_weapon")) {
			L1ItemInstance equipeitem = null;
			for (Object itemObject :  l1pcinstance.getInventory().getItems()) {
				L1ItemInstance item = (L1ItemInstance) itemObject;
				if (item.getItem().getType2() == 1 && item.isEquipped()) {
					equipeitem = item;
					for (int i=0; i<aData8.size(); i++) {
					  aTempData8=aData8.get(i);
					  if (equipeitem.getItemId() == ((Integer) aTempData8.get(0)).intValue()) {
						  iExp = getWeaponExp( equipeitem.getId());
						  if (((Integer) aTempData8.get(1)).intValue() <= iExp) {
						      if (aTempData8.get(3)!= null && aTempData8.get(4)!= null && !consumeItem (l1pcinstance , (int[]) aTempData8.get(3),(int[] )aTempData8.get(4))) {
							  		aTempData = new ArrayList<Object>();
									aTempData.add(0,new Integer(0));
							  	    return aTempData;
							      }

						      upgradeWeapon(equipeitem.getId(), ItemTable.getInstance().getTemplate(((Integer) aTempData8.get(2)).intValue()).getItemId(), ItemTable.getInstance().getTemplate(((Integer) aTempData8.get(2)).intValue()).getName() );
						      // sosodemon 修正为判断身上武器&移除后新增 start
							  if (item.isEquipped() == true) {
								  l1pcinstance.getInventory().removeItem(item, 1);
							  }
							  l1pcinstance.getInventory().storeItem(ItemTable.getInstance().getTemplate(((Integer) aTempData8.get(2)).intValue()).getItemId(), 1);
							  // sosodemon 修正为判断身上武器&移除后新增 end
							  L1World.getInstance().broadcastPacketToAll(new S_SystemMessage((new java.lang.StringBuilder().append(l1pcinstance.getName()).append(resource.getString("weapon.upgrade.message3")).append(equipeitem.getItem().getName()).append(( resource.getString("weapon.upgrade.message4")).toString())).toString()));
					    	  ArrayList<Object> aReturn = new ArrayList<Object>();
					    	  aReturn.add(0,new Integer(1));
					    	  aReturn.add(1,new String("a1_1"));
					    	  aReturn.add(2,new String[]{resource.getString("html.a1_1.name"),resource.getString("html.a1_1.data1"),resource.getString("html.a1_1.data2") });
					    	  aReturn.add(3,null);
					    	  aReturn.add(4,null);
					    	  aReturn.add(5,null);
					    	  aReturn.add(6,null);
					    	  return aReturn;
						  }
						  else {
					    	  ArrayList<Object> aReturn = new ArrayList<Object>();
					    	  aReturn.add(0,new Integer(1));
					    	  aReturn.add(1,new String("wperr1"));
					    	  aReturn.add(2,new String[]{resource.getString("html.wperr1.name"), resource.getString("html.wperr1.data1"),resource.getString("html.wperr1.data2"),resource.getString("html.wperr1.data3"),resource.getString("html.wperr1.data4"),""+iExp,((Integer) aTempData8.get(1)).toString() });
					    	  aReturn.add(3,null);
					    	  aReturn.add(4,null);
					    	  aReturn.add(5,null);
					    	  aReturn.add(6,null);
					    	  return aReturn;
						  }
					  }
					}
				}
			}
			ArrayList<Object> aReturn = new ArrayList<Object>();
			aReturn.add(0,new Integer(1));
			aReturn.add(1,new String("wperr2"));
			aReturn.add(2,new String[]{ resource.getString("html.wperr2.name"),resource.getString("html.wperr2.data") });
			aReturn.add(3,null);
			aReturn.add(4,null);
			aReturn.add(5,null);
			aReturn.add(6,null);
			return aReturn;
		}
		for (int i=0; i<aData.size(); i++) {
			aTempData = aData.get(i);
  	        if (((String)aTempData.get(7)).equals(s)) {
  	  	      if (l1pcinstance.getLevel()>=Integer.parseInt((String)aTempData.get(8))) {
				java.util.Calendar aC = java.util.Calendar.getInstance();
				int iMonth = aC.get(Calendar.MONTH) + 1;
				int iDay = aC.get(Calendar.DAY_OF_MONTH);
				String sDate = null;
				int iTime = 0;
				int iType = Integer.parseInt((String)aTempData.get(11));
				int iComparedNum =0;
				if (iMonth < 10)
					  sDate = aC.get(Calendar.YEAR)+"0"+iMonth;
					else
				      sDate = aC.get(Calendar.YEAR)+""+iMonth;
				if (iDay < 10)
					  sDate = sDate + "0" + iDay;
					else
					  sDate = sDate + "" + iDay;
			    iTime = aC.get(Calendar.HOUR_OF_DAY);
			    if (aTempData.get(12).equals("1") && getJoin(s,l1pcinstance.getId())) {
			    	  ArrayList<Object> aReturn = new ArrayList<Object>();
			    	  aReturn.add(0,new Integer(1));
			    	  aReturn.add(1,new String("joinerr"));
			    	  aReturn.add(2,new String[]{resource.getString("html.joinerr.name"),resource.getString("html.joinerr.data") });
			    	  aReturn.add(3,null);
			    	  aReturn.add(4,null);
			    	  aReturn.add(5,null);
			    	  aReturn.add(6,null);
			    	  return aReturn;
			    }
			    if (iType == 1) // 日期
			    	iComparedNum = Integer.parseInt(sDate);
			    else if (iType == 2)
			    	iComparedNum = iTime;

              if (aTempData.get(9) == null || Integer.parseInt((String)aTempData.get(9)) <= iComparedNum)
			    	if (aTempData.get(10) == null || iComparedNum <= Integer.parseInt((String)aTempData.get(10))) {

				      if (aTempData.get(13).equals("1")) {  //just check
				    	 if (l1pcinstance.getInventory().checkItem((int[])aTempData.get(3),(int[])aTempData.get(4))) {
							  if (aTempData.get(12).equals("1") && !getJoin(s,l1pcinstance.getId()))
							    addJoin(s,l1pcinstance.getId());
					    	  ArrayList<Object> aReturn = new ArrayList<Object>();
					    	  aReturn.add(0,aTempData.get(0));
					    	  aReturn.add(1,aTempData.get(1));
					    	  aReturn.add(2,aTempData.get(2));
					    	  aReturn.add(3,null);
					    	  aReturn.add(4,null);
					    	  aReturn.add(5,null);
					    	  aReturn.add(6,null);
					    	  return aReturn;
				        }
				      }
				      else {
				    	 if (aTempData.get(3)!= null && aTempData.get(4)!= null)
					        if (l1pcinstance.getInventory().checkItem((int[])aTempData.get(3),(int[])aTempData.get(4)))
								  if (aTempData.get(12).equals("1") && !getJoin(s,l1pcinstance.getId()))
								    addJoin(s,l1pcinstance.getId());
				    	 return aTempData;
				      }
			    	}
			    	else {
			    	  ArrayList<Object> aReturn = new ArrayList<Object>();
			    	  aReturn.add(0,new Integer(1));
			    	  aReturn.add(1,new String("enderr"));
			    	  aReturn.add(2,new String[]{resource.getString("html.enderr.name"),resource.getString("html.enderr.data"),(String)aTempData.get(10)});
			    	  aReturn.add(3,null);
			    	  aReturn.add(4,null);
			    	  aReturn.add(5,null);
			    	  aReturn.add(6,null);
			    	  return aReturn;
			    	}
			    else {
			    	  ArrayList<Object> aReturn = new ArrayList<Object>();
			    	  aReturn.add(0,new Integer(1));
			    	  aReturn.add(1,new String("starterr"));
			    	  aReturn.add(2,new String[]{resource.getString("html.starterr.name"),resource.getString("html.starterr.data"),(String)aTempData.get(9)});
			    	  aReturn.add(3,null);
			    	  aReturn.add(4,null);
			    	  aReturn.add(5,null);
			    	  aReturn.add(6,null);
			    	  return aReturn;
			    }
			  }
			  else {
		    	  ArrayList<Object> aReturn = new ArrayList<Object>();
		    	  aReturn.add(0,new Integer(1));
		    	  aReturn.add(1,new String("levelerr"));
		    	  aReturn.add(2, new String[]{resource.getString("html.levelerr.name"),resource.getString("html.levelerr.data"),(String)aTempData.get(8)});
		    	  aReturn.add(3,null);
		    	  aReturn.add(4,null);
		    	  aReturn.add(5,null);
		    	  aReturn.add(6,null);
		    	  return aReturn;
			  }
  	        }
		}
		aTempData = new ArrayList<Object>();
		aTempData.add(0,new Integer(0));
  	    return aTempData;
	}

    private static void upgradeWeapon(int weapon_oid,int new_weapon_id,String new_weapon_name) {
	      java.sql.Connection con = null;
	        try {
	          con = L1DatabaseFactory.getInstance().getConnection();
	          Statement stat = con.createStatement();
	          try {
	        	  stat.executeUpdate("update william_weapon_exp set exp='0' where weapon_oid ='"+weapon_oid+"'");

	          } catch(Exception eee) {
	          }
	          if (con!=null && !con.isClosed())
	           	  con.close();
	        } catch(Exception ex) {
	        }
    }

	private static int getWeaponExp(int weapon_oid) {
	      java.sql.Connection con = null;
	      int iReturn = -1;
	        try {
	          con = L1DatabaseFactory.getInstance().getConnection();
	          Statement stat = con.createStatement();
	          ResultSet rset = stat.executeQuery("SELECT * FROM william_weapon_exp where weapon_oid ='" + weapon_oid + "'");
	          if (rset != null)
	            if (rset.next())
	            	iReturn = rset.getInt("exp");
	          if (con!=null && !con.isClosed())
	           	  con.close();
	        } catch(Exception ex) {
	        }
	      return iReturn;
	}

	private static int[] getEquipWeaponInfo(L1PcInstance l1pcinstance) {
		int[] iReturn = null;
		@SuppressWarnings("unused")
		L1ItemInstance equipeitem = null;
		for (Object itemObject :  l1pcinstance.getInventory().getItems()) {
			L1ItemInstance item = (L1ItemInstance) itemObject;
			if(item.getItem().getType2() == 1 && item.isEquipped()) {
				iReturn = new int[2];
				iReturn[0] = item.getId();
				iReturn[1] = item.getItemId();
			}
		}
		return iReturn;
	}

	public static void addWeaponExp(L1PcInstance l1pcinstance ,int exp) {
		// chen3209 修正 willam 武升系统会造成伺服器当机
		java.sql.Connection con = null;
        if (exp <= 0) return;
        int iReturn = -1;
        int[] iWeaponInfo = null;
        ArrayList<Object> iTemp = null;
          if (!NO_MORE_GET_DATA8) {
              NO_MORE_GET_DATA=true;
                getData8();
          }
          iWeaponInfo = getEquipWeaponInfo(l1pcinstance);
          if( iWeaponInfo == null) return;
          try {
            con = L1DatabaseFactory.getInstance().getConnection();
            Statement stat = con.createStatement();
            for (int j=0; j<aData8.size(); j++) {
                iTemp = aData8.get(j);
                if (((Integer)iTemp.get(0)).intValue() == iWeaponInfo[1]) {
                    try {
                      stat.executeUpdate("insert into william_weapon_exp (weapon_oid ,exp) values ('" + iWeaponInfo[0] + "','" + exp + "')");
                    }
					catch(Exception ex2) {

                        ResultSet rset = stat.executeQuery("SELECT exp FROM william_weapon_exp where weapon_oid ='" + iWeaponInfo[0] + "'");
                          if (rset != null)
                              if (rset.next())
                                  iReturn = rset.getInt("exp");
                            if (iReturn>= 99999999) { // 把xxxx改成你武器经验的最大值
                                con.close();
                                break;
                            } else {
                                stat.executeUpdate("update william_weapon_exp set exp=exp+" + (exp/10) + " where weapon_oid='" + iWeaponInfo[0] + "'"); // 这边是设定武器得到经验的倍数
                            }
					}
                }
            }
            if (con != null && !con.isClosed())
                  con.close();
          } catch(Exception ex) {
          } 
	}
	// END

	private static void addJoin(String sCmd,int iUserID) {
	      java.sql.Connection con = null;
	        try {
	          con = L1DatabaseFactory.getInstance().getConnection();
	          Statement stat = con.createStatement();
	          stat.executeUpdate("insert into william_quests_save(command,userid) values ('" + sCmd + "','" + iUserID + "')");
	          if(con!=null && !con.isClosed())
	           	  con.close();

	        } catch(Exception ex) {
	        }
	}

	private static boolean getJoin(String sCmd,int iUserID) {
      java.sql.Connection con = null;
      boolean bReturn=false;
        try {
          con = L1DatabaseFactory.getInstance().getConnection();
          Statement stat = con.createStatement();
          ResultSet rset = stat.executeQuery("SELECT * FROM william_quests_save where command='" + sCmd + "' and userid='" + iUserID + "'");
          if (rset!= null)
            if (rset.next())
        	  bReturn = true;
          if(con!=null && !con.isClosed())
           	  con.close();

        } catch(Exception ex) {
        }

      return bReturn;
	}

	private static void getData8() {
		
        java.sql.Connection con = null;
        ArrayList<Object> aReturn = null;
        try { 
          con = L1DatabaseFactory.getInstance().getConnection(); 
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william_weapon_upgrade");
	      @SuppressWarnings("unused")
		String sTemp = null;
          if( rset!=null)
            while (rset.next()){
        	    aReturn = new ArrayList<Object>();
            	aReturn.add(0,new Integer( rset.getString("weapon_id")) );
            	aReturn.add(1,new Integer( rset.getString("upgrade_exp")) );
            	aReturn.add(2,new Integer( rset.getString("upgrade_weapon_id")) );
            	if( rset.getString("materials")!=null && !rset.getString("materials").equals("") )
                    aReturn.add(3,getArray(rset.getString("materials"),TOKEN,1) );
                else 
                    aReturn.add(3,null );
                
                if( rset.getString("counts")!=null && !rset.getString("counts").equals("") )
                    aReturn.add(4,getArray(rset.getString("counts"),TOKEN,1) );
                else 
                    aReturn.add(4,null );
	    	  aData8.add(aReturn);
            }
          if(con!=null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex){
        	
        }
	}	

	private static void  getData() { 
       java.sql.Connection con = null;

       try {
         con = L1DatabaseFactory.getInstance().getConnection();
         Statement stat = con.createStatement();
         ResultSet rset = stat.executeQuery("SELECT * FROM william_quests where enable=1");
   	     ArrayList<Object> aReturn = null;
   	     String sTemp = null;
         if (rset != null)
           while (rset.next()) {
        	   aReturn = new ArrayList<Object>();
               aReturn.add(0,new Integer("1") );
             if (rset.getString("htmlid")!= null && !rset.getString("htmlid").equals(""))
                 aReturn.add(1,getArray(rset.getString("htmlid"),TOKEN,3));
             else
            	 aReturn.add(1,null);
             if (rset.getString("htmldata")!= null && !rset.getString("htmldata").equals(""))
                 aReturn.add(2,getArray(rset.getString("htmldata"),TOKEN,2));
             else
            	 aReturn.add(2,null);
             if (rset.getString("materials")!= null && !rset.getString("materials").equals(""))
                 aReturn.add(3,getArray(rset.getString("materials"),TOKEN,1));
             else
            	 aReturn.add(3,null);

             if (rset.getString("counts")!= null && !rset.getString("counts").equals(""))
                 aReturn.add(4,getArray(rset.getString("counts"),TOKEN,1));
             else
            	 aReturn.add(4,null);

             if (rset.getString("createitem")!= null && !rset.getString("createitem").equals(""))
                 aReturn.add(5,getArray(rset.getString("createitem"),TOKEN,1));
             else
            	 aReturn.add(5,null);

             if (rset.getString("createcount")!= null && !rset.getString("createcount").equals(""))
                 aReturn.add(6,getArray(rset.getString("createcount"),TOKEN,1));
             else
            	 aReturn.add(6,null);

         	 sTemp = rset.getString("command");
             aReturn.add(7,sTemp);

             if (rset.getString("activated_level")!= null && !rset.getString("activated_level").equals(""))
                 aReturn.add(8,rset.getString("activated_level"));
             else
            	 aReturn.add(8,null);

             if (rset.getString("activated_timestart")!= null && !rset.getString("activated_timestart").equals(""))
                 aReturn.add(9,rset.getString("activated_timestart"));
             else
            	 aReturn.add(9,null);

             if (rset.getString("activated_timeend")!= null && !rset.getString("activated_timeend").equals(""))
                 aReturn.add(10,rset.getString("activated_timeend"));
             else
            	 aReturn.add(10,null);

             if (rset.getString("activated_type")!= null && !rset.getString("activated_type").equals(""))
                 aReturn.add(11,rset.getString("activated_type"));
             else
            	 aReturn.add(11,null);
             if (rset.getString("islimit")!= null && !rset.getString("islimit").equals(""))
                 aReturn.add(12,rset.getString("islimit"));
             else
            	 aReturn.add(12,null);
             if (rset.getString("justcheck")!= null && !rset.getString("justcheck").equals(""))
                 aReturn.add(13,rset.getString("justcheck"));
             else
            	 aReturn.add(13,null);
             aData.add(aReturn);
           }
         if (con!= null && !con.isClosed())
       	  con.close();
       } catch(SQLException e) {
         //_log.warning(e.getMessage()); 
       }
   }

   private static Object getArray(String s,String sToken,int iType) {
     StringTokenizer st = new StringTokenizer(s,sToken);
     int iSize = st.countTokens();
     String  sTemp = null;
     if (iType == 1) { // int
     	int[] iReturn = new int[iSize];
     	for (int i=0; i<iSize; i++) {
       	  sTemp = st.nextToken();
     	  iReturn[i] = Integer.parseInt(sTemp);
     	}
     	return iReturn;
      }
     if (iType == 2) { // String
      	String[] sReturn = new String[iSize];
      	for (int i=0; i<iSize; i++) {
       	  sTemp = st.nextToken();
      	  sReturn[i] = sTemp;
      	}
      	return sReturn;
       }
     if (iType == 3) { // String
      	String sReturn = null;
      	for (int i=0; i<iSize; i++) {
       	  sTemp = st.nextToken();
      	  sReturn = sTemp;
      	}
      	return sReturn;
       }
     return null;
   }

   private static void mobspawn(ClientThread clientthread, int i) {
	   L1PcInstance l1pcinstance = clientthread.getActiveChar();
	   try {
		   L1Npc l1npc = NpcTable.getInstance().getTemplate(i);
		   if (l1npc != null) {
				@SuppressWarnings("unused")
				Object obj = null;
				try {
					@SuppressWarnings("unused")
					String s = l1npc.getImpl();
					@SuppressWarnings("unused")
					Object aobj[] = { l1npc };
					L1NpcInstance l1npcinstance = new L1MonsterInstance(l1npc);
					l1npcinstance.setId(IdFactory.getInstance().nextId());
					l1npcinstance.setX(l1pcinstance.getX());
					l1npcinstance.setY(l1pcinstance.getY());
					l1npcinstance.setHomeX(l1pcinstance.getX());
					l1npcinstance.setHomeY(l1pcinstance.getY());
					l1npcinstance.setHeading(l1pcinstance.getHeading());
					l1npcinstance.setMap(l1pcinstance.getMap());
					L1World.getInstance().storeObject(l1npcinstance);
					L1World.getInstance().addVisibleObject(l1npcinstance);
					L1Object object = L1World.getInstance().findObject(l1npcinstance.getId());
					L1MonsterInstance newnpc = (L1MonsterInstance)object;
					newnpc.onNpcAI();
					L1World.getInstance().storeObject(l1npcinstance);
					L1World.getInstance().addVisibleObject(l1npcinstance);
				} catch (Exception exception1) {
					exception1.printStackTrace();
				}
			}
		} catch (Exception exception) {
		}
   }

   private static boolean consumeItem (L1PcInstance l1pcinstance,int[] materials,int[] counts) {
			boolean isCreate = true;
			for (int j = 0; j < materials.length; j++) {
				if (!l1pcinstance.getInventory().checkItem(materials[j],counts[j])) {
					L1Item temp = ItemTable.getInstance().getTemplate(materials[j]);
					l1pcinstance.sendPackets(new S_ServerMessage(337, temp
							.getName()+":"+counts[j])); // \f1%0不足。
					isCreate = false;
				}
			}

			if (isCreate) {
				//System.out.println("test2");
				for (int j = 0; j < materials.length; j++) {
					// 材料消费
					l1pcinstance.getInventory().consumeItem(materials[j],counts[j]);
				}
		    }
    	    return isCreate;
   }
    @SuppressWarnings("unused")
    private final L1PcInstance _owner;
}
