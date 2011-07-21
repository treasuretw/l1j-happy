package l1j.william;

import l1j.server.server.ClientThread;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;

/**
 * 处理外挂非法移动
 */
public class External_water {

	private static byte _UseItemCount = 0;

	private static long _oldUseItemTimeInMillis = 0L;

	public static void water(byte decrypt[], ClientThread client) throws InterruptedException {

		long nowUseItemTimeInMillis = System.currentTimeMillis();

		L1PcInstance pc = client.getActiveChar();

		if (_UseItemCount == 0) {
			_UseItemCount++;
			_oldUseItemTimeInMillis = nowUseItemTimeInMillis;
			return;
		}

		long UseItemInterval = nowUseItemTimeInMillis - _oldUseItemTimeInMillis;

		if (UseItemInterval > 2000) {
			_UseItemCount = 0;
			_oldUseItemTimeInMillis = 0;
		} else {
			if (_UseItemCount >= 3) { // 连续走三步以上
				L1World.getInstance().broadcastServerMessage("怀疑您正在使用外挂,您被自动防挂系统踢下线!! ");
				Thread.sleep(1000);				// 暂停一秒
				pc.getNetConnection().kick();	// 踢下线

				_UseItemCount = 0;
				_oldUseItemTimeInMillis = 0;
				return;
			}
			_UseItemCount++;
		}
	}

}
