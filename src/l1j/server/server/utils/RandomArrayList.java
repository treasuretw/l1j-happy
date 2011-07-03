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
package l1j.server.server.utils;

public class RandomArrayList {
	/** 泛用型随机矩阵，所使用的指标 */
	private static int listint = 0;
	/** 新型泛用型，适用Int的正数范围 */
	private static double[] ArrayDouble = new double[32767];

	public static void setArrayList() {
		for (listint = 0; listint < 32767; listint++) {
			ArrayDouble[listint] = Math.random();
		}
		l1j.william.PcFrameMonitor.sos();
	}

	public static int getlistint() {
		if (listint < 32766)
			return ++listint;
		else
			return listint = 0;
	}

	/**
	 * getInt(int 数值) 随机值的伪静态，速度是nextInt(int 数值) 的数倍
	 * 根据呼叫的数值传回 静态表内加工后的数值,并采共同指标来决定传回的依据.
	 * EX:getInt(92988) => 0~92987
	 * 
	 * @param rang - Int类型
	 * @return 0 ~ (数值-1)
	 */
	public static int getInt(int rang) {
		return (int) (ArrayDouble[getlistint()] * rang);
	}
	public static int getInt(double rang) {
		return (int) (ArrayDouble[getlistint()] * rang);
	}

	/**
	 * getInc(int 数值, int 输出偏移值) 随机值的伪静态，速度是nextInt(int 数值) 的数倍
	 * 根据呼叫的数值传回 静态表内加工后的数值,并采共同指标来决定传回的依据.
	 * EX:getInc(92988, 10) => (0~92987) + 10 => 10~92997
	 * 
	 * @param rang - Int类型
	 * @param increase - 修正输出结果的范围
	 * @return 0 ~ (数值-1) + 输出偏移值
	 */
	public static int getInc(int rang, int increase) {
		return getInt(rang) + increase;
	}
	public static int getInc(double rang, int increase) {
		return getInt(rang) + increase;
	}
}
