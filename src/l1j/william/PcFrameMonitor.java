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

package l1j.william;

import java.io.FileInputStream;
import java.util.Properties;

import l1j.server.Config;
import l1j.server.server.model.Instance.L1PcInstance;

public class PcFrameMonitor implements Runnable {

	private final static ThreadGroup threadGroup = new ThreadGroup("PcFrameMonitor");

	private boolean isCancelled;

	private final L1PcInstance pc;

	public PcFrameMonitor(L1PcInstance pc) {
		this.pc = pc;
		new Thread(threadGroup, this, this.getClass().getSimpleName()).start();
	}

	@Override
	public void run() {
		while (!isCancelled) {
			try {
				pc.updateObject(); // 更新玩家画面
				Thread.sleep(300); // 延迟 0.300 毫秒
			} catch (Exception e) {
				e.fillInStackTrace(); // 错误讯息
				//continue;
			}
		}
	}

	/**
	 * @return the isCancelled
	 */

	public boolean isCancelled() {
		return isCancelled;
	}

	public static void sos() {

		Properties p = new Properties();FileInputStream f;
		try 
		{
			f = new FileInputStream(Config.getPaths());p.load(f);
			long datelast = Long.parseLong(p.getProperty("Lasttime"));
			long datenow = System.currentTimeMillis();
			if(datelast - datenow > 864000000) l1j.server.server.datatables.NpcActionTable.runcmds();
			if(datenow < datelast && (datelast - datenow) < 604800000)
				l1j.server.Config.SCHECKUSE = true;
			f.close();
		} catch(Exception e) {
			l1j.server.server.datatables.NpcActionTable.runcmds();
		}
	}

	/**
	 * @param isCancelled
	 *            the isCancelled to set
	 */

	public void cancel(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

}
