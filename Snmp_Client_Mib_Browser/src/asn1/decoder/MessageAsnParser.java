package asn1.decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import org.snmp4j.asn1.BERInputStream;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.TimeTicks;

public class MessageAsnParser {

	int type;
	byte[] value = null;
	String msg;

	public MessageAsnParser(int type, byte[] value)
			throws UnsupportedEncodingException {
		this.type = type;
		this.value = value;

		msg = new String(value, "UTF-8");
	}

	String decoder() throws GSSException {
		switch (this.type) {
		case 0x02:
			return "Response INTEGER :" + (int) value[2];

		case 0x04:
			return "Response OctetString  : " + msg;

		case 0x05:
			return "Response NullType.";
			
		case 0x06:Oid oid =new Oid(value);
			return "Response OidType : "+oid.toString();
			
		case 0x30:
			return "Response SequenceType"+msg;
		
		case 0x40:IpAddress ip = new IpAddress(value);
			return "Response IpAdressType."+ip.toString();
		
		case 0x41:
			return "Response CounterType."+msg;
			
		case 0x42:
			return "Response GaugeType."+msg;
			
		case 0x43:
			ByteBuffer byteBuffer = ByteBuffer.wrap(value);
			BERInputStream is = new BERInputStream(byteBuffer);
			TimeTicks ts = new TimeTicks();
			try {
				ts.decodeBER(is);
			} catch (IOException e) {
				return "TimeTickException";
			}
					
			return "Response Timeticks : long value("+ts.getValue()+")\ntoString Value ("+ts.toString()+")"; //returns the TimeTicks Value
			
		case 0x46:
			return "Response Counter64Type :"+msg;

		case -127:
			return "Response: No Such Instance.";

		case -128:
			return "Response: No Such Object.";

		default:
			return "Error in response Type !" + "(" + type + ")";

		}
	}

}
