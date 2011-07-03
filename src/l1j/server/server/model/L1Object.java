/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package l1j.server.server.model;

import java.io.Serializable;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.map.L1Map;
import l1j.server.server.model.map.L1WorldMap;

// Referenced classes of package l1j.server.server.model:
// L1PcInstance, L1Character

/**
 * 所有对象的基底
 */
public class L1Object implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 取得对象所存在的地图ID
	 * 
	 * @return 地图ID
	 */
	public short getMapId() {
		return (short) _loc.getMap().getId();
	}

	/**
	 * 设定对象所存在的地图ID
	 * 
	 * @param mapId
	 *            地图ID
	 */
	public void setMap(short mapId) {
		_loc.setMap(L1WorldMap.getInstance().getMap(mapId));
	}

	/**
	 * 取得对象所存在的地图
	 * 
	 */
	public L1Map getMap() {
		return _loc.getMap();
	}

	/**
	 * 设定对象所存在的地图
	 * 
	 * @param map
	 *            设定地图
	 */
	public void setMap(L1Map map) {
		if (map == null) {
			throw new NullPointerException();
		}
		_loc.setMap(map);
	}

	/**
	 * 取得对象在世界中唯一的ID
	 * 
	 * @return 唯一的ID
	 */
	public int getId() {
		return _id;
	}

	/**
	 * 设定对象在世界中唯一的ID
	 * 
	 * @param id
	 *            唯一的ID
	 */
	public void setId(int id) {
		_id = id;
	}

	/**
	 * 取得对象在地图上的X轴值
	 * 
	 * @return 座标X轴值
	 */
	public int getX() {
		return _loc.getX();
	}

	/**
	 * 设定对象在地图上的X轴值
	 * 
	 * @param x
	 *            座标X轴值
	 */
	public void setX(int x) {
		_loc.setX(x);
	}

	/**
	 * 取得对象在地图上的Y轴值
	 * 
	 * @return 座标Y轴值
	 */
	public int getY() {
		return _loc.getY();
	}

	/**
	 * 设定对象在地图上的Y轴值
	 * 
	 * @param y
	 *            座标Y轴值
	 */
	public void setY(int y) {
		_loc.setY(y);
	}

	private L1Location _loc = new L1Location();

	/**
	 * 对象存在在地图上的L1Location
	 * 
	 * @return L1Location的座标对应
	 */
	public L1Location getLocation() {
		return _loc;
	}

	public void setLocation(L1Location loc) {
		_loc.setX(loc.getX());
		_loc.setY(loc.getY());
		_loc.setMap(loc.getMapId());
	}

	public void setLocation(int x, int y, int mapid) {
		_loc.setX(x);
		_loc.setY(y);
		_loc.setMap(mapid);
	}

	/**
	 * 取得与另一个对象间的直线距离。
	 */
	public double getLineDistance(L1Object obj) {
		return this.getLocation().getLineDistance(obj.getLocation());
	}

	/**
	 * 取得与另一个对象间的距离X轴或Y轴较大的那一个。
	 */
	public int getTileLineDistance(L1Object obj) {
		return this.getLocation().getTileLineDistance(obj.getLocation());
	}

	/**
	 * 取得与另一个对象间的X轴+Y轴的距离。
	 */
	public int getTileDistance(L1Object obj) {
		return this.getLocation().getTileDistance(obj.getLocation());
	}

	/**
	 * 对象的荧幕范围进入玩家
	 * 
	 * @param perceivedFrom
	 *            进入荧幕范围的玩家
	 */
	public void onPerceive(L1PcInstance perceivedFrom) {
	}

	/**
	 * 对象对玩家采取的行动
	 * 
	 * @param actionFrom
	 *            要采取行动的玩家目标
	 */
	public void onAction(L1PcInstance actionFrom) {
	}

	/**
	 * 与对象交谈的玩家
	 * 
	 * @param talkFrom
	 *            交谈的玩家
	 */
	public void onTalkAction(L1PcInstance talkFrom) {
	}

	private int _id = 0;

	public void onAction(L1PcInstance attacker, int skillId) {

	}
}
