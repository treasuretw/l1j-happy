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

package l1j.eric;

import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import l1j.eric.gui.J_Main;

public class EricLogger extends Logger {
	private static String className = null;

	private static String classResourceBundleName = null;

	public EricLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
		className = name;
	}

	public static EricLogger getLogger2(String name) {
		className = name;
		classResourceBundleName = Logger.getLogger(name)
				.getResourceBundleName();

		return new EricLogger(className, classResourceBundleName);
	}

	@Override
	public synchronized void addHandler(Handler handler)
			throws SecurityException {
		// TODO Auto-generated method stub
		super.addHandler(handler);
	}

	@Override
	public void config(String msg) {
		// TODO Auto-generated method stub
		// J_Main.getInstance().addLog(Calendar.getInstance().getTime().toLocaleString()+"
		// "+className);
		// J_Main.getInstance().addLog("Config: "+msg);
		super.config(msg);
	}

	@Override
	public void entering(String sourceClass, String sourceMethod, Object param1) {
		// TODO Auto-generated method stub
		super.entering(sourceClass, sourceMethod, param1);
	}

	@Override
	public void entering(String sourceClass, String sourceMethod,
			Object[] params) {
		// TODO Auto-generated method stub
		super.entering(sourceClass, sourceMethod, params);
	}

	@Override
	public void entering(String sourceClass, String sourceMethod) {
		// TODO Auto-generated method stub
		super.entering(sourceClass, sourceMethod);
	}

	@Override
	public void exiting(String sourceClass, String sourceMethod, Object result) {
		// TODO Auto-generated method stub
		super.exiting(sourceClass, sourceMethod, result);
	}

	@Override
	public void exiting(String sourceClass, String sourceMethod) {
		// TODO Auto-generated method stub
		super.exiting(sourceClass, sourceMethod);
	}

	@Override
	public void fine(String msg) {
		// TODO Auto-generated method stub
		// J_Main.getInstance().addLog(Calendar.getInstance().getTime().toLocaleString()+"
		// "+className);
		// J_Main.getInstance().addLog("fine: "+msg);

		super.fine(msg);
	}

	@Override
	public void finer(String msg) {
		// TODO Auto-generated method stub
		super.finer(msg);
	}

	@Override
	public void finest(String msg) {
		// TODO Auto-generated method stub
		super.finest(msg);
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return super.getFilter();
	}

	@Override
	public synchronized Handler[] getHandlers() {
		// TODO Auto-generated method stub
		return super.getHandlers();
	}

	@Override
	public Level getLevel() {
		// TODO Auto-generated method stub
		return super.getLevel();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public Logger getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	@Override
	public ResourceBundle getResourceBundle() {
		// TODO Auto-generated method stub
		return super.getResourceBundle();
	}

	@Override
	public String getResourceBundleName() {
		// TODO Auto-generated method stub
		return super.getResourceBundleName();
	}

	@Override
	public synchronized boolean getUseParentHandlers() {
		// TODO Auto-generated method stub
		return super.getUseParentHandlers();
	}

	@Override
	public void info(String msg) {
		// TODO Auto-generated method stub
		// J_Main.getInstance().addLog(Calendar.getInstance().getTime().toLocaleString()+"
		// "+className);
		// J_Main.getInstance().addLog(msg);
		J_Main.getInstance().addConsol(
				Calendar.getInstance().getTime().toString() + " " + className);
		J_Main.getInstance().addConsol(msg);
		super.info(msg);
	}

	@Override
	public boolean isLoggable(Level level) {
		// TODO Auto-generated method stub
		return super.isLoggable(level);
	}

	@Override
	public void log(Level level, String msg, Object param1) {
		// TODO Auto-generated method stub
		super.log(level, msg, param1);
	}

	@Override
	public void log(Level level, String msg, Object[] params) {
		// TODO Auto-generated method stub
		super.log(level, msg, params);
	}

	@Override
	public void log(Level level, String msg, Throwable thrown) {
		// TODO Auto-generated method stub
		// J_Main.getInstance().addLog(Calendar.getInstance().getTime().toLocaleString()+"
		// "+className);
		// J_Main.getInstance().addLog(level+": "+msg+"\n"+thrown);
		J_Main.getInstance().addConsol(
				Calendar.getInstance().getTime().toString() + " " + className);
		J_Main.getInstance().addConsol(level + ": " + msg + "\n" + thrown);
		super.log(level, msg, thrown);
	}

	@Override
	public void log(Level level, String msg) {
		// TODO Auto-generated method stub
		super.log(level, msg);
	}

	@Override
	public void log(LogRecord record) {
		// TODO Auto-generated method stub
		super.log(record);
	}

	@Override
	public void logp(Level level, String sourceClass, String sourceMethod,
			String msg, Object param1) {
		// TODO Auto-generated method stub
		super.logp(level, sourceClass, sourceMethod, msg, param1);
	}

	@Override
	public void logp(Level level, String sourceClass, String sourceMethod,
			String msg, Object[] params) {
		// TODO Auto-generated method stub
		super.logp(level, sourceClass, sourceMethod, msg, params);
	}

	@Override
	public void logp(Level level, String sourceClass, String sourceMethod,
			String msg, Throwable thrown) {
		// TODO Auto-generated method stub
		super.logp(level, sourceClass, sourceMethod, msg, thrown);
	}

	@Override
	public void logp(Level level, String sourceClass, String sourceMethod,
			String msg) {
		// TODO Auto-generated method stub
		super.logp(level, sourceClass, sourceMethod, msg);
	}

	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod,
			String bundleName, String msg, Object param1) {
		// TODO Auto-generated method stub
		super.logrb(level, sourceClass, sourceMethod, bundleName, msg, param1);
	}

	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod,
			String bundleName, String msg, Object[] params) {
		// TODO Auto-generated method stub
		super.logrb(level, sourceClass, sourceMethod, bundleName, msg, params);
	}

	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod,
			String bundleName, String msg, Throwable thrown) {
		// TODO Auto-generated method stub
		super.logrb(level, sourceClass, sourceMethod, bundleName, msg, thrown);
	}

	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod,
			String bundleName, String msg) {
		// TODO Auto-generated method stub
		super.logrb(level, sourceClass, sourceMethod, bundleName, msg);
	}

	@Override
	public synchronized void removeHandler(Handler handler)
			throws SecurityException {
		// TODO Auto-generated method stub
		super.removeHandler(handler);
	}

	@Override
	public void setFilter(Filter newFilter) throws SecurityException {
		// TODO Auto-generated method stub
		super.setFilter(newFilter);
	}

	@Override
	public void setLevel(Level newLevel) throws SecurityException {
		// TODO Auto-generated method stub
		super.setLevel(newLevel);
	}

	@Override
	public void setParent(Logger parent) {
		// TODO Auto-generated method stub
		super.setParent(parent);
	}

	@Override
	public synchronized void setUseParentHandlers(boolean useParentHandlers) {
		// TODO Auto-generated method stub
		super.setUseParentHandlers(useParentHandlers);
	}

	@Override
	public void severe(String msg) {
		// TODO Auto-generated method stub
		super.severe(msg);
	}

	@Override
	public void throwing(String sourceClass, String sourceMethod,
			Throwable thrown) {
		// TODO Auto-generated method stub
		super.throwing(sourceClass, sourceMethod, thrown);
	}

	@Override
	public void warning(String msg) {
		// TODO Auto-generated method stub
		// J_Main.getInstance().addLog(Calendar.getInstance().getTime().toLocaleString()+"
		// "+className);
		// J_Main.getInstance().addLog("Warning: "+msg);
		J_Main.getInstance().addConsol(
				Calendar.getInstance().getTime().toString() + " " + className);
		J_Main.getInstance().addConsol("Warning: " + msg);
		super.warning(msg);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
