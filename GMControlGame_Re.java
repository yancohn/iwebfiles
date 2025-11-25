package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.IO.Protocol.Rpc.Data.DataVector;

public final class GMControlGame_Re extends Protocol
{
	public int	xid;
	public int	retcode;
	public DataVector	resvector;

	public GMControlGame_Re()
	{
		resvector = new DataVector(new GMControlGameRes());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(xid);
		os.marshal(retcode);
		os.marshal(resvector);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		xid = os.unmarshal_int();
		retcode = os.unmarshal_int();
		os.unmarshal(resvector);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMControlGame_Re o = (GMControlGame_Re)super.clone();
			o.resvector = (DataVector)resvector.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		DeliveryDB.GMControlGame_Re( this );
	}

}
