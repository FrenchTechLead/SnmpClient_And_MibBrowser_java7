package window;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import asn1.coder.SnmpGetMessageCoder;
import asn1.coder.SnmpGetNextMessageCoder;
import asn1.decoder.SnmpGetMessageDecoder;

public class Request {
	
	static String reponse =null;
	static String oid ="";
	public Request(){}
	
	
	public static  String getRequest(String ip, String com, String oid)throws Exception {

		SnmpGetMessageCoder get = null;
		get = new SnmpGetMessageCoder(com, oid);
		InetAddress addressServeur = InetAddress.getByName(ip);
		DatagramSocket soc = new DatagramSocket();
		

		DatagramPacket packSortant = new DatagramPacket(get.codedMessageOut(),
				get.codedMessageOut().length, addressServeur, 161);

		soc.connect(addressServeur, 161); // not necessary because DatagramSockets work on notConnected mode
		soc.send(packSortant);

		byte[] buff = new byte[1000];

		soc.setSoTimeout(3000); // if message not received in 3 seconds it throws timeout exception
		DatagramPacket packEntrant = new DatagramPacket(buff, buff.length);
		soc.receive(packEntrant);

		SnmpGetMessageDecoder parser = new SnmpGetMessageDecoder(packEntrant);

		reponse = "Message Received\n" + "Host : " + packEntrant.getAddress()
				+ " Port : " + packEntrant.getPort() + ".\n\n" + parser.parseAll();

		return reponse;

	}
	
	
	
	public static  String getNextRequest(String ip, String com, String oid)throws Exception {

		SnmpGetNextMessageCoder getNext = null;
		getNext = new SnmpGetNextMessageCoder(com, oid);
		InetAddress addressServeur = InetAddress.getByName(ip);
		DatagramSocket soc = new DatagramSocket();
		

		DatagramPacket packSortant = new DatagramPacket(getNext.codedMessageOut(),
				getNext.codedMessageOut().length, addressServeur, 161);

		soc.connect(addressServeur, 161); // not necessary because DatagramSockets work on notConnected mode
		soc.send(packSortant);

		byte[] buff = new byte[1000];

		soc.setSoTimeout(3000); // if message not received in 3 seconds it throws timeout exception
		DatagramPacket packEntrant = new DatagramPacket(buff, buff.length);
		soc.receive(packEntrant);

		SnmpGetMessageDecoder parser = new SnmpGetMessageDecoder(packEntrant);

		reponse = "Message Received\n" + "Host : " + packEntrant.getAddress()
				+ " Port : " + packEntrant.getPort() + ".\n\n" + parser.parseAll();
		Request.oid=parser.parseOID();

		return reponse;

	}
}
