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
package l1j.server.server;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.misc.BASE64Encoder;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

/**
 * 账号相关资讯
 */
public class Account {
	/** 使用者帐号名称 */
	private String _name;

	/** 来源IP位址 */
	private String _ip;

	/** 加密过后的密码 */
	private String _password;

	/** 上一次登入的日期 */
	private Timestamp _lastActive;

	/** 权限(是否为GM) */
	private int _accessLevel;

	/** 来源 DNS 反解 */
	private String _host;

	/** 是否被禁止登入 (True 代表禁止). */
	private boolean _banned;

	/** 可使用的角色数目 */
	private int _characterSlot;

	/** 帐户是否有效 (True 代表有效). */
	private boolean _isValid = false;

	/** 仓库密码 */
	private int _WarePassword = 0;

	/** 是否在线上 */
	private boolean _online = false;

	/** 纪录用 */
	private static Logger _log = Logger.getLogger(Account.class.getName());

	/**
	 * 建构式
	 */
	private Account() {
	}

	/**
	 * 将明文密码加密
	 * 
	 * @param rawPassword
	 *            明文密码
	 * @return String
	 * @throws NoSuchAlgorithmException
	 *             密码使用不存在的演算法加密
	 * @throws UnsupportedEncodingException
	 *             文字编码不支援
	 */
	private static String encodePassword(final String rawPassword)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] buf = rawPassword.getBytes("UTF-8");
		buf = MessageDigest.getInstance("SHA").digest(buf);

		return new BASE64Encoder().encode(buf);
	}

	/**
	 * 建立新的帐号
	 * 
	 * @param name
	 *            帐号名称
	 * @param rawPassword
	 *            明文密码
	 * @param ip
	 *            连结时的 IP
	 * @param host
	 *            连结时的 dns 反查
	 * @return Account
	 */
	public static Account create(final String name, final String rawPassword,
			final String ip, final String host) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {

			Account account = new Account();
			account._name = name;
			account._password = encodePassword(rawPassword);
			account._ip = ip;
			account._host = host;
			account._banned = false;
			account._lastActive = new Timestamp(System.currentTimeMillis());

			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "INSERT INTO accounts SET login=?,password=?,lastactive=?,access_level=?,ip=?,host=?,online=?,banned=?,character_slot=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, account._name);
			pstm.setString(2, account._password);
			pstm.setTimestamp(3, account._lastActive);
			pstm.setInt(4, 0);
			pstm.setString(5, account._ip);
			pstm.setString(6, account._host);
			pstm.setInt(7, 0);
			pstm.setInt(8, account._banned ? 1 : 0);
			pstm.setInt(9, account._online ? 1 : 0);
			pstm.execute();
			_log.info("创建新账号 " + name);

			return account;
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (UnsupportedEncodingException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return null;
	}

	/**
	 * 从资料库中取得指定帐号的资料
	 * 
	 * @param name
	 *            帐号名称
	 * @return Account
	 */
	public static Account load(final String name) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Account account = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "SELECT * FROM accounts WHERE login=? LIMIT 1";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				return null;
			}
			account = new Account();
			account._name = rs.getString("login");
			account._password = rs.getString("password");
			account._lastActive = rs.getTimestamp("lastactive");
			account._accessLevel = rs.getInt("access_level");
			account._ip = rs.getString("ip");
			account._host = rs.getString("host");
			account._banned = rs.getInt("banned") == 0 ? false : true;
			account._online = rs.getInt("online") == 0 ? false : true;
			account._characterSlot = rs.getInt("character_slot");
			account._WarePassword = rs.getInt("warepassword");

			_log.fine("account exists");
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}

		return account;
	}

	/**
	 * 更新最后一次登入时的日期与时间
	 * 
	 * @param account
	 *            帐号
	 */
	public static void updateLastActive(final Account account, final String ip) {
		Connection con = null;
		PreparedStatement pstm = null;
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "UPDATE accounts SET lastactive=?, ip=? ,online=1 WHERE login = ?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setTimestamp(1, ts);
			pstm.setString(2, ip);
			pstm.setString(3, account.getName());
			pstm.execute();
			account._lastActive = ts;
			_log.fine("update lastactive for " + account.getName());
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/**
	 * 更新资料库中角色数目
	 * 
	 * @param account
	 *            アカウント
	 */
	public static void updateCharacterSlot(final Account account) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "UPDATE accounts SET character_slot=? WHERE login=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setInt(1, account.getCharacterSlot());
			pstm.setString(2, account.getName());
			pstm.execute();
			account._characterSlot = account.getCharacterSlot();
			_log.fine("update characterslot for " + account.getName());
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/**
	 * 取得帐号下的角色数目
	 * 
	 * @return int
	 */
	public int countCharacters() {
		int result = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "SELECT count(*) as cnt FROM characters WHERE account_name=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, _name);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}

	/**
	 * 取得账号下的角色名称
	 * 
	 * @return String
	 */
	public ArrayList<String> getCharacters() {
		ArrayList<String> result = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "SELECT char_name FROM characters WHERE account_name=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, _name);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result.add(rs.getString("char_name"));
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}

	/**
	 * @category 写入是否在线上
	 * @param account 玩家帐号
	 * @param i isOnline?
	 */
	public synchronized static void online(Account account, boolean i) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "UPDATE accounts SET online=? WHERE login=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setInt(1, i ? 1 : 0);
			pstm.setString(2, account.getName());
			pstm.execute();
			account.setOnline(i);
		} catch (SQLException e) {
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}
	
	/**
	 * 归零所有帐号online=0
	 */
	public static void InitialOnlineStatus() {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "UPDATE accounts SET online=0 WHERE online=1";
			pstm = con.prepareStatement(sqlstr);
			pstm.execute();
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/**
	 * 设定该帐号为禁止登入
	 * 
	 * @param login
	 *            アカウント名
	 */
	public static void ban(final String login) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			String sqlstr = "UPDATE accounts SET banned=1, online=0 WHERE login=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, login);
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/**
	 * 确认输入的密码与资料库中的密码是否相同
	 * 
	 * @param rawPassword
	 *            明文密码
	 * @return boolean
	 */
	public boolean validatePassword(final String rawPassword) {
		// 认证成功后如果再度认证就判断为失败
		if (_isValid) {
			return false;
		}
		try {
			_isValid = _password.equals(encodePassword(rawPassword));
			if (_isValid) {
				_password = null; // 认证成功后就将记忆体中的密码清除
			}
			return _isValid;
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return false;
	}

	/**
	 * 变更仓库密码
	 * 
	 * @param newPassword
	 *            新的密码
	 */
	public void changeWarePassword(int newPassword) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();

			pstm = con
					.prepareStatement("UPDATE `accounts` SET `warepassword` = ? WHERE `login` = ?");
			pstm.setInt(1, newPassword);
			pstm.setString(2, getName());
			pstm.execute();

			_WarePassword = newPassword;
		} catch (SQLException e) {
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/**
	 * 取得帐号使否有效 (True 为有效).
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		return _isValid;
	}

	/**
	 * 取得是否为GM (True 代表为GM).
	 * 
	 * @return boolean
	 */
	public boolean isGameMaster() {
		return 0 < _accessLevel;
	}

	/**
	 * 取得帐号名称
	 * 
	 * @return String
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 取得连线时的 IP
	 * 
	 * @return String
	 */
	public String getIp() {
		return _ip;
	}

	/**
	 * 取得上次登入的时间
	 */
	public Timestamp getLastActive() {
		return _lastActive;
	}

	/**
	 * 取得权限
	 * 
	 * @return int
	 */
	public int getAccessLevel() {
		return _accessLevel;
	}

	/**
	 * 取得 DNS 反解的域名
	 * 
	 * @return String
	 */
	public String getHost() {
		return _host;
	}

	/**
	 * 设定是否在线上
	 * @param i
	 */
	public synchronized void setOnline(boolean i) {
		_online = i;
	}

	/**
	 * 取得是否在线上
	 * @return
	 */
	public synchronized boolean isOnlined() {
		return _online;
	}
	
	/**
	 * 取得是否被禁止登入
	 * 
	 * @return boolean
	 */
	public boolean isBanned() {
		return _banned;
	}

	/**
	 * 取得角色数目
	 * 
	 * @return int
	 */
	public int getCharacterSlot() {
		return _characterSlot;
	}

	/**
	 * 设定角色数目
	 * 
	 * @param i
	 *            欲设定的数目
	 */
	public void setCharacterSlot(int i) {
		_characterSlot = i;
	}

	/**
	 * 取得仓库密码
	 * 
	 * @return 仓库密码
	 */
	public int getWarePassword() {
		return _WarePassword;
	}

}
