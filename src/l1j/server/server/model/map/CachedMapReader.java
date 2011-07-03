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
package l1j.server.server.model.map;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import l1j.server.server.datatables.MapsTable;
import l1j.server.server.utils.collections.Maps;

/**
 * 将地图做快取的动作以减少读取的时间。
 */
public class CachedMapReader extends MapReader {

	/** 地图档的路径 */
	@SuppressWarnings("unused")
	private static final String MAP_DIR = "./maps/";

	/** cache 后地图档的路径 */
	private static final String CACHE_DIR = "./data/mapcache/";

	/**
	 * 将指定编号的地图转成快取的地图格式
	 * 
	 * @param mapId
	 *            地图编号
	 * @return L1V1Map
	 * @throws IOException
	 */
	private L1V1Map cacheMap(final int mapId) throws IOException {
		File file = new File(CACHE_DIR);
		if (!file.exists()) {
			file.mkdir();
		}

		L1V1Map map = (L1V1Map) new TextMapReader().read(mapId);

		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(CACHE_DIR + mapId + ".map")));

		out.writeInt(map.getId());
		out.writeInt(map.getX());
		out.writeInt(map.getY());
		out.writeInt(map.getWidth());
		out.writeInt(map.getHeight());

		for (byte[] line : map.getRawTiles()) {
			for (byte tile : line) {
				out.writeByte(tile);
			}
		}
		out.flush();
		out.close();

		return map;
	}

	/**
	 * 从快取地图中读取特定编号的地图
	 * 
	 * @param mapId
	 *            地图编号
	 * @return L1Map
	 * @throws IOException
	 */
	@Override
	public L1Map read(final int mapId) throws IOException {
		File file = new File(CACHE_DIR + mapId + ".map");
		if (!file.exists()) {
			return cacheMap(mapId);
		}

		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(CACHE_DIR + mapId + ".map")));

		int id = in.readInt();
		if (mapId != id) {
			throw new FileNotFoundException();
		}

		int xLoc = in.readInt();
		int yLoc = in.readInt();
		int width = in.readInt();
		int height = in.readInt();

		byte[][] tiles = new byte[width][height];
		for (byte[] line : tiles) {
			in.read(line);
		}

		in.close();
		L1V1Map map = new L1V1Map(id, tiles, xLoc, yLoc, 
				MapsTable.getInstance().isUnderwater(mapId), 
				MapsTable.getInstance().isMarkable(mapId), 
				MapsTable.getInstance().isTeleportable(mapId), 
				MapsTable.getInstance().isEscapable(mapId), 
				MapsTable.getInstance().isUseResurrection(mapId), 
				MapsTable.getInstance().isUsePainwand(mapId), 
				MapsTable.getInstance().isEnabledDeathPenalty(mapId), 
				MapsTable.getInstance().isTakePets(mapId),
				MapsTable.getInstance().isRecallPets(mapId), 
				MapsTable.getInstance().isUsableItem(mapId), 
				MapsTable.getInstance().isUsableSkill(mapId));
		return map;
	}

	/**
	 * 取得所有地图与编号的 Mapping
	 * 
	 * @return Map
	 * @throws IOException
	 */
	@Override
	public Map<Integer, L1Map> read() throws IOException {
		Map<Integer, L1Map> maps = Maps.newMap();
		for (int id : TextMapReader.listMapIds()) {
			maps.put(id, read(id));
		}
		return maps;
	}
}
