package asn1.decoder;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

public class SnmpGetMessageDecoder {

	DatagramPacket packet;
	byte[] reponse;
	static int verSnmp;
	static int tailleCommunaute;

	static int error;
	static int oidLenght;
	private static String messageDecode;
	private byte[] oidTab;
	ByteArrayOutputStream os;

	public SnmpGetMessageDecoder(DatagramPacket packet) {

		this.packet = packet;
		this.reponse = packet.getData();
		SnmpGetMessageDecoder.verSnmp = (int) reponse[4];
		SnmpGetMessageDecoder.tailleCommunaute = (int) reponse[6];
		SnmpGetMessageDecoder.error = (int) reponse[14 + tailleCommunaute];
		SnmpGetMessageDecoder.oidLenght = (int) reponse[23 + tailleCommunaute];
		os = new ByteArrayOutputStream();
		os.write(reponse, 22 + tailleCommunaute,
				2 + (int) reponse[23 + tailleCommunaute]);
		this.oidTab = os.toByteArray();
	}

	public String parseAll() throws UnsupportedEncodingException, GSSException {
		if (reponse[2] == 0x02) {
			messageDecode = parseSnmpVer();
			messageDecode = messageDecode + parseCommunaute();
			messageDecode = messageDecode + parseSnmpError();
			messageDecode = messageDecode + "OID requested : "+parseOID()+"\n";
			messageDecode = messageDecode + parseMessage();
			return messageDecode;
		} else {

			return "Message too long to be read Please select another OID,\n"
					+ "this problem will be solved for next version";
		}
	}

	public String parseSnmpVer() {

		switch (verSnmp) {
		case 0x00:
			return "Version snmp du msg recu : 1\n";
		case 0x01:
			return "Version snmp du msg recu : 2c\n";
		default:
			return "alo snmp du msg recu : AUTRE\n";
		}

	}

	public String parseCommunaute() throws UnsupportedEncodingException {
		byte[] communaute = new byte[tailleCommunaute];
		for (int i = 0; i < tailleCommunaute; i++) {
			communaute[i] = reponse[i + 7];
		}
		String com = new String(communaute, "UTF-8"); // convertion byte[] to
														// string
		return "Communaute : " + com.toString() + "\n";
	}

	public String parseSnmpError() {
		switch (error) {
		case 0x00:
			return "Error : No Error was found in PDU\n";
		case 0x01:
			return "Error : Response message too large to transport\n";
		case 0x02:
			return "Error : The name of the OID was not found\n";
		case 0x03:
			return "Error : A data type in the request did not match the data type in the SNMP agent \n";
		case 0x04:
			return "Error : The SNMP manager attempted to set a read-only parameter \n";
		case 0x05:
			return "Error : General Error \n";
		default:
			return "Error : General Error\n";
		}
	}

	public String parseOID() {
		Oid oidObject = null;
		try {
			oidObject = new Oid(oidTab);
		} catch (GSSException e) {
			System.out
					.println("Message too long to be read Please select another OID,\n"
							+ "this problem will be solved for next version");
			e.printStackTrace();
		}
		return  oidObject.toString();
	}

	public String parseMessage() throws UnsupportedEncodingException, GSSException {
		int i;
		ByteArrayOutputStream in = new ByteArrayOutputStream();
		in.write(reponse, tailleCommunaute + 24 + oidLenght,
				reponse[tailleCommunaute + 25 + oidLenght]+2);
		i = reponse[tailleCommunaute + 24 + oidLenght];
		MessageAsnParser parser = new MessageAsnParser(i, in.toByteArray());
		return "\n" + parser.decoder();

	}
}
