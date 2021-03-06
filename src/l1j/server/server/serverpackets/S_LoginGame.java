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
package l1j.server.server.serverpackets;

import static l1j.server.server.Opcodes.S_OPCODE_LOGINTOGAME;

/**
 * 由3.0的S_Unkown1改名
 * 正式命名為LoginGame
 */
public class S_LoginGame extends ServerBasePacket {
	public S_LoginGame() {
		writeC(S_OPCODE_LOGINTOGAME);
		writeC(0x03);
		writeC(0x00);
		writeC(0xF7);
		writeC(0xAD);
		writeC(0x74);
		writeC(0x00);
		writeC(0xE5);
	}
	
	public byte[] getContent() {
		return getBytes();
	}
}
