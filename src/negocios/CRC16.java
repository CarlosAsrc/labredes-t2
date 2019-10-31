package negocios;

public class CRC16 {
//	Converte uma String em um int de CRC-16/AUG-CCITT
	public static int converter(String a) {

		String input = a;
		Integer crcRes = crc16(input.getBytes());

		String hex = Integer.toHexString(crcRes);
		int decimal = Integer.parseInt(hex, 16);

		return decimal;
	}

	private static int crc16(final byte[] buffer) {

		int crc = 0x1D0F;
		for (int j = 0; j < buffer.length; j++) {
			crc = ((crc >>> 8) | (crc << 8)) & 0xffff;
			crc ^= (buffer[j] & 0xff);// byte to int, trunc sign
			crc ^= ((crc & 0xff) >> 4);
			crc ^= (crc << 12) & 0xffff;
			crc ^= ((crc & 0xFF) << 5) & 0xffff;
		}
		crc &= 0xffff;
		return crc;
	}
}
