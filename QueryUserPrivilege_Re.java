package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.IO.Protocol.Rpc.Data.DataVector;
import java.lang.*;

public final class QueryUserPrivilege_Re extends Protocol
{
	public int	userid;
	public DataVector	auth;

	public QueryUserPrivilege_Re()
	{
		auth = new DataVector(new ByteData());
	}

	public final class ByteData extends Rpc.Data
	{
		public	byte	b;

		public ByteData()
		{
		}

		public OctetsStream marshal(OctetsStream os)
		{
			os.marshal(b);
			return os;
		}

		public OctetsStream unmarshal(OctetsStream os) throws MarshalException
		{
			b = os.unmarshal_byte();
			return os;
		}

		public Object clone()
		{
			try
			{
				ByteData o = (ByteData)super.clone();
				return o;
			}
			catch (Exception e) { }
			return null;
		}

	};

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(userid);
		os.marshal(auth);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		userid = os.unmarshal_int();
		os.unmarshal(auth);
		return os;
	}

	public Object clone()
	{
		try
		{
			QueryUserPrivilege_Re o = (QueryUserPrivilege_Re)super.clone();
			o.auth = (DataVector)auth.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
