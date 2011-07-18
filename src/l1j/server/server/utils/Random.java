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
package l1j.server.server.utils;

public class Random {
	private static final int _max = Short.MAX_VALUE;

	private static int _idx = 0;

	private static double[] _value = new double[_max + 1];

	static {
		for (_idx = 0; _idx < _max + 1; _idx++) {
			_value[_idx] = (Math.random() + Math.random() + Math.random() + Math.random() + Math.random()) % 1.0;
		}
	}

	// 取得整数
	public static int nextInt(int n) {
		_idx = _idx & _max;
		return (int) (_value[_idx++] * n);
	}

	// 取得整数+偏移
	public static int nextInt(int n, int offset) {
		_idx = _idx & _max;
		return offset + (int) (_value[_idx++] * n);
	}

	// 随机布林值
	public static boolean nextBoolean() {
		return (nextInt(2) == 1);
	}

	// 随机位元
	public static byte nextByte() {
		return (byte)nextInt(256);
	}

	// 随机长整数
	public static long nextLong() {
		long l = nextInt(Integer.MAX_VALUE) << 32 + nextInt(Integer.MAX_VALUE);
		return l;
	}

	//11j-tw
	/** 泛用型随机矩阵，所使用的指标 */
    private static int _nIndex = 0;

    /** 新型泛用型，适用Int的正数范围 */
    private static double[] _nArray = new double[0x7FFF + 1];

    static {
            do {
                    _nArray[_nIndex] = Math.random();
            } while(getIndex() != 0x00);
    }

    private static int getIndex() {
            return _nIndex = 0x7FFF & ( ++_nIndex );
    }

	/**
     * getByte(byte[] 容器) ：模仿Random.nextBytes(byte[]) 制作
     */
    public static void getByte(byte[] arr) {
            int _nLen_t = arr.length;
            for(int i = 0; i < _nLen_t; i++)
                    arr[i] = (byte) (getValue() * 0x80);
    }

    private static boolean haveNextGaussian = false;
    private static double nextGaussian;
    /**
     * getGaussian() ：return 高斯分配
     */
    public static double getGaussian() {
            if (haveNextGaussian) {
                    haveNextGaussian = false;
                    return nextGaussian;
            } else {
                    double v1, v2, s;
                    do {
                            v1 = 2 * _nArray[getIndex()] - 1;   // between -1.00 ~ 1.00
                            v2 = 2 * _nArray[getIndex()] - 1;   // between -1.00 ~ 1.00
                            s = v1 * v1 + v2 * v2;
                    } while (s >= 1 || s == 0);
                    double multiplier = Math.sqrt(-2 * Math.log(s) / s);
                    nextGaussian = v2 * multiplier;
                    haveNextGaussian = true;
                    return v1 * multiplier;
            }
    }

	/**
     * getValue() ：return between 0.00 ~ 1.00
     */
    private static double getValue() {
            return _nArray[getIndex()];
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
            return (int) (getValue() * rang);
    }

    public static int getInt(double rang) {
            return (int) (getValue() * rang);
    }

    public static double getDouble() {
            return getValue();
    }

    public static double getDouble(double rang) {
            return getValue() * rang;
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

    public static double getDc(int rang, int increase) {
            return getDouble(rang) + increase;
    }

    public static double getDc(double rang, int increase) {
            return getDouble(rang) + increase;
    }
}
