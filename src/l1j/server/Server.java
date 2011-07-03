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
package l1j.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import l1j.eric.gui.J_Main;
import l1j.server.Config;
import l1j.server.server.GameServer;
import l1j.server.telnet.TelnetServer;

/**
 * 启动 L1J-TW 伺服器
 */
public class Server {

	/** 用于讯息记录 */
	private static Logger _log = Logger.getLogger(Server.class.getName());

	/** 记录设定档的档资料夹 */
	private static final String LOG_PROP = "./config/log.properties";

	/**
	 * 主要伺服器
	 * 
	 * @param args
	 *            命令列参数
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		File logFolder = new File("log");
		logFolder.mkdir();

		try {
			InputStream is = new BufferedInputStream(new FileInputStream(
					LOG_PROP));
			LogManager.getLogManager().readConfiguration(is);
			is.close();
		} catch (IOException e) {
			_log.log(Level.SEVERE, "Failed to Load " + LOG_PROP + " File.", e);
			System.exit(0);
		}
		try {
			Config.load();
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			System.exit(0);
		}

		// L1DatabaseFactory初期设定
		L1DatabaseFactory.setDatabaseSettings(Config.DB_DRIVER, Config.DB_URL,
				Config.DB_LOGIN, Config.DB_PASSWORD);
		L1DatabaseFactory.getInstance();

		// TODO 管理者介面 by eric1300460
		if (Config.GUI) {
			J_Main.getInstance().setVisible(true);
		}

		GameServer.getInstance().initialize();
		if (Config.TELNET_SERVER) {
			TelnetServer.getInstance().start();
		}
	}
}
