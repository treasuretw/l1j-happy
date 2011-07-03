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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.utils.LeakCheckedConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DBへのアクセスするための各种インターフェースを提供する.
 */
public class L1DatabaseFactory {
	/** 资料库的实例 */
	private static L1DatabaseFactory _instance;

	/** 资料库连结的来源 */
	private ComboPooledDataSource _source;

	/** 纪录用 */
	private static Logger _log = Logger.getLogger(L1DatabaseFactory.class
			.getName());

	/* 连结资料库相关的资讯 */
	/** 资料库连结的驱动程式 */
	private static String _driver;

	/** 资料库连结的位址 */
	private static String _url;

	/** 登入资料库的使用者名称 */
	private static String _user;

	/** 登入资料库的密码 */
	private static String _password;

	/**
	 * 设定资料库登入的相关资讯
	 * 
	 * @param driver 
	 *            资料库连结的驱动程式
	 * @param url
	 *            资料库连结的位址
	 * @param user
	 *            登入资料库的使用者名称 
	 * @param password
	 *            登入资料库的密码
	 */
	public static void setDatabaseSettings(final String driver,
			final String url, final String user, final String password) {
		_driver = driver;
		_url = url;
		_user = user;
		_password = password;
	}

	/**
	 * 资料库连结的设定与配置
	 * 
	 * @throws SQLException
	 */
	public L1DatabaseFactory() throws SQLException {
		try {
			// DatabaseFactoryをL2Jから一部を除いて拜借
			_source = new ComboPooledDataSource();
			_source.setDriverClass(_driver);
			_source.setJdbcUrl(_url);
			_source.setUser(_user);
			_source.setPassword(_password);

			/* Test the connection */
			_source.getConnection().close();
		} catch (SQLException x) {
			_log.fine("Database Connection FAILED");
			// rethrow the exception
			throw x;
		} catch (Exception e) {
			_log.fine("Database Connection FAILED");
			throw new SQLException("could not init DB connection:" + e);
		}
	}

	/**
	 * 伺服器关闭的时候要关闭与资料库的连结
	 */
	public void shutdown() {
		try {
			_source.close();
		} catch (Exception e) {
			_log.log(Level.INFO, "", e);
		}
		try {
			_source = null;
		} catch (Exception e) {
			_log.log(Level.INFO, "", e);
		}
	}

	/**
	 * 取得资料库的实例（第一次实例为 null 的时候才新建立一个).
	 * 
	 * @return L1DatabaseFactory
	 * @throws SQLException
	 */
	public static L1DatabaseFactory getInstance() throws SQLException {
		if (_instance == null) {
			_instance = new L1DatabaseFactory();
		}
		return _instance;
	}

	/**
	 * 取得资料库连结时的连线
	 * 
	 * @return Connection 连结对象
	 * @throws SQLException
	 */
	public Connection getConnection() {
		Connection con = null;

		while (con == null) {
			try {
				con = _source.getConnection();
			} catch (SQLException e) {
				_log
						.warning("L1DatabaseFactory: getConnection() failed, trying again "
								+ e);
			}
		}
		return Config.DETECT_DB_RESOURCE_LEAKS ? LeakCheckedConnection
				.create(con) : con;
	}
}
