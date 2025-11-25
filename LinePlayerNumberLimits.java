package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class LinePlayerNumberLimits extends Rpc.Data
{
	public DataVector	limits;

	public LinePlayerNumberLimits()
	{
		limits = new DataVector(new LinePlayerLimit());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(limits);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(limits);
		return os;
	}

	public Object clone()
	{
		try
		{
			LinePlayerNumberLimits o = (LinePlayerNumberLimits)super.clone();
			o.limits = (DataVector)limits.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
