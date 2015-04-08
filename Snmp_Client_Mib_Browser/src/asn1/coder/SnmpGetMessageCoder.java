package asn1.coder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;


public class SnmpGetMessageCoder {

	private final String COMMUNAUTE;
	private int comLength;
	private int oidLength;
	private final Oid oidObject;

	public SnmpGetMessageCoder(String Communaute, String OID)
			throws GSSException {
		this.COMMUNAUTE = Communaute.trim();
		OID = OID.trim();
		comLength = this.COMMUNAUTE.length();
		this.oidObject = new Oid(OID);
		this.oidLength = oidObject.getDER().length;
	}

	public byte[] codedMessageOut() throws IOException, GSSException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(0x30); //type sequence 
		out.write(comLength + oidLength + 22);//
		out.write(0x02);
		out.write(0x01);
		out.write(0x01);
		out.write(0x04);
		out.write(comLength);
		out.write(COMMUNAUTE.getBytes(), 0, comLength);
		out.write(0xA0);
		out.write(oidLength + 15);
		out.write(0x02);
		out.write(0x01);
		out.write(0x01);
		out.write(0x02);
		out.write(0x01);
		out.write(0x00);
		out.write(0x02);
		out.write(0x01);
		out.write(0x00);
		out.write(0x30);
		out.write(oidLength + 4);
		out.write(0x30);
		out.write(oidLength + 2);
		out.write(oidObject.getDER());
		out.write(0x05);
		out.write(0x00);

		return out.toByteArray();
	}
}
