package protocol;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Random;
import org.apache.commons.lang.StringEscapeUtils;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import com.goldhuman.Common.*;

public class XmlRoleBase
{

	static public class XmlRoleException extends Exception
	{
		public XmlRoleException( String s )
		{
			super( s );
		}
	}

	static protected String toHexString(byte[] x)
	{
		StringBuffer sb = new StringBuffer(x.length * 2);
		for ( int i = 0; i < x.length; i++ )
		{
			byte n = x[i];
			int nibble = (int)(n >> 4)&0xf;
			sb.append ( (char)(nibble >= 10 ? 'a' + nibble - 10 : '0' + nibble) );
			nibble = (int)(n & 0xf);
			sb.append ( (char)(nibble >= 10 ? 'a' + nibble - 10 : '0' + nibble) );
		}
		return sb.toString();
	}

	static protected byte[] hextoByteArray(String x)
	{
		if( x.length() < 2 )
			return new byte[0];
		if( (x.length() % 2) != 0 )
			System.err.println( "hextoByteArray error! hex size=" + Integer.toString(x.length()) );

		byte [] rb = new byte[x.length()/2];
		for ( int i = 0; i < rb.length; i++ )
		{
			rb[i] = 0;

			int n = (int)x.charAt(i+i);
			if( n >= '0' && n <= '9' )
				n = n - '0';
			else if( n >= 'a' && n <= 'f' )
				n = n - 'a' + 10;
			rb[i] = (byte)(rb[i] | ((n<<4)&0xf0));

			n = (int)x.charAt(i+i+1);
			if( n >= '0' && n <= '9' )
				n = n - '0';
			else if( n >= 'a' && n <= 'f' )
				n = n - 'a' + 10;
			rb[i] = (byte)(rb[i] | (n&0xf));
		}
		return rb;
	}

	static protected String escapeUnprintable(String x)
	{
		//&#x1;&#x2;&#x3;&#x4;&#x5;&#x6;&#x7;&#x8;&#xb; &#xe;&#xf;&#x10;&#x11;&#x12;&#x13;&#x14;&#x15;&#x16;&#x17;&#x18;&#x19;&#x1a;&#x1b;&#x1c;&#x1d;&#x1e;&#x1f;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<x.length(); i++)
		{
			if ((byte)x.charAt(i) == 7)
			{
				sb.append("#########7;");
			}
			else
				sb.append(x.charAt(i));
		}
		return sb.toString();
	}

	static protected String unescapeUnprintable(String x)
	{
		String match="#########7;";
		if (x.indexOf(match)>=0)
		{
			byte[] rb = new byte[1];
			rb[0] = (byte)7;
			x = x.replace(match, new String(rb));
		}
		return x;
	}


	static protected Node createVariable( Document doc, String name, String type, String value )
	{
		Element var = doc.createElement("variable");
		var.setAttribute("name",name);
		var.setAttribute("type",type);
		var.appendChild( doc.createTextNode(value) );
		return var;
	}

	static protected Node createVariable( Document doc, String name, String type, Octets value )
	{
		Element var = doc.createElement("variable");
		var.setAttribute("name",name);
		var.setAttribute("type",type);
		var.appendChild( doc.createTextNode(toHexString(value.getBytes())) );
		return var;
	}

	static protected String getElementValue( NodeList list, String name ) throws XmlRoleException
	{
		for( int i=0; i<list.getLength(); i++ )
		{
			Element node = (Element)list.item(i);
			String n = node.getAttribute( "name" );
			if( name.equals( n ) )
			{
				Text child = (Text)node.getFirstChild();
				if( null == child )
					return "";
				return child.getNodeValue();
			}
		}
		throw new XmlRoleException( "getElementValue(NodeList,String): list.size="
				+ list.getLength() + ", name=" + name );
	}
	static protected String getElementValue( NodeList list, String name, String parentName ) throws XmlRoleException
	{
		for( int i=0; i<list.getLength(); i++ )
		{
			Element node = (Element)list.item(i);
			if (!node.getParentNode().getNodeName().equals(parentName)) continue;
			String n = node.getAttribute( "name" );
			if( name.equals( n ) )
			{
				Text child = (Text)node.getFirstChild();
				if( null == child )
					return "";
				return child.getNodeValue();
			}
		}
		throw new XmlRoleException( "getElementValue(NodeList,String, parentName): list.size="
				+ list.getLength() + ", name=" + name  + ",parentName=" + parentName);
	}
}
