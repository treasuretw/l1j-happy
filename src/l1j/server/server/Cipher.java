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

public class Cipher {
	/* 静态私用变数 */
	/**
	 * 将乱数数值混淆用的混淆密码 (32位元,静态唯读) 该数值只有在Cipher初始化时才会被调用
	 */
	private final static int _1 = 0x9c30d539;
	/**
	 * 初始的解码数值
	 */
	private final static int _2 = 0x930fd7e2;
	/**
	 * 将乱数数值混淆用的混淆密码 (32位元,静态唯读) 该数值只有在Cipher初始化时才会被调用
	 */
	private final static int _3 = 0x7c72e993;
	/**
	 * 将封包数值混淆用的混淆密码 (32位元,静态唯读) 该数值只有在编码或解码时才会被调用
	 */
	private final static int _4 = 0x287effc3;

	/* 动态私用变数 */
	/**
	 * 参考用的编码钥匙 (位元组阵列长度为8,相当于一个64位元的长整数)
	 */
	private final byte[] eb = new byte[8];
	/**
	 * 参考用的解码钥匙 (位元组阵列长度为8,相当于一个64位元的长整数)
	 */
	private final byte[] db = new byte[8];
	/**
	 * 参考用的封包钥匙 (位元组阵列长度为4,相当于一个32位元的整数)
	 */
	private final byte[] tb = new byte[4];

	/**
	 * 初始化流程: 1.建立新的钥匙暂存器(keys),将编码钥匙与混淆钥匙(_1)进行混淆并带入keys[0],将初始的解码数值带入key[1]
	 * 2.将key[0]向右反转19个位元(0x13)并带入key[0] 3.将key[0]与key[1]与混淆钥匙(_3)进行混淆并带入key[1]
	 * 4.将keys转换为64位元的位元组阵列
	 * 
	 * @param key
	 *            , 由乱数产生的编码钥匙
	 */
	public Cipher(int key) {
		int[] keys = { key ^ _1, _2 };
		keys[0] = Integer.rotateLeft(keys[0], 0x13);
		keys[1] ^= keys[0] ^ _3;

		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < tb.length; j++) {
				eb[(i * 4) + j] = db[(i * 4) + j] = (byte) (keys[i] >> (j * 8) & 0xff);
			}
		}
	}

	/**
	 * 将未受保护的资料进行混淆,避免资料外流.
	 * 
	 * @param data
	 *            , 未受保护的资料
	 * @return data, 受保护的资料
	 */
	public byte[] encrypt(byte[] data) {
		for (int i = 0; i < tb.length; i++) {
			tb[i] = data[i];
		}

		data[0] ^= eb[0];

		for (int i = 1; i < data.length; i++) {
			data[i] ^= data[i - 1] ^ eb[i & 7];
		}

		data[3] ^= eb[2];
		data[2] ^= eb[3] ^ data[3];
		data[1] ^= eb[4] ^ data[2];
		data[0] ^= eb[5] ^ data[1];
		update(eb, tb);
		return data;
	}

	/**
	 * 将受保护的资料进行还原,让伺服器可以参考正确的资料.
	 * 
	 * @param data
	 *            , 受保护的资料
	 * @return data, 原始的资料
	 */
	public byte[] decrypt(byte[] data) {
		data[0] ^= db[5] ^ data[1];
		data[1] ^= db[4] ^ data[2];
		data[2] ^= db[3] ^ data[3];
		data[3] ^= db[2];

		for (int i = data.length - 1; i >= 1; i--) {
			data[i] ^= data[i - 1] ^ db[i & 7];
		}

		data[0] ^= db[0];
		update(db, data);
		return data;
	}

	/**
	 * 将指定的钥匙进行混淆并与混淆钥匙相加(_4)
	 * 
	 * @param data
	 *            , 受保护的资料
	 * @return data, 原始的资料
	 */
	private void update(byte[] data, byte[] ref) {
		for (int i = 0; i < tb.length; i++) {
			data[i] ^= ref[i];
		}

		int int32 = (((data[7] & 0xFF) << 24) | ((data[6] & 0xFF) << 16)
				| ((data[5] & 0xFF) << 8) | (data[4] & 0xFF))
				+ _4;

		for (int i = 0; i < tb.length; i++) {
			data[i + 4] = (byte) (int32 >> (i * 8) & 0xff);
		}
	}
}
