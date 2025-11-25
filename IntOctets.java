package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class IntOctets extends Rpc.Data
{
	public int	m_int;
	public Octets	m_octets;

	public IntOctets()
	{
		m_octets = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(m_int);
		os.marshal(m_octets);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		m_int = os.unmarshal_int();
		os.unmarshal(m_octets);
		return os;
	}

	public Object clone()
	{
		try
		{
			IntOctets o = (IntOctets)super.clone();
			o.m_octets = (Octets)m_octets.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
