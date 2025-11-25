package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class LinePlayerLimit extends Rpc.Data
{
	public int	line_id;
	public int	cur_num;
	public int	max_num;
	public int	attr;

	public LinePlayerLimit()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(line_id);
		os.marshal(cur_num);
		os.marshal(max_num);
		os.marshal(attr);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		line_id = os.unmarshal_int();
		cur_num = os.unmarshal_int();
		max_num = os.unmarshal_int();
		attr = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			LinePlayerLimit o = (LinePlayerLimit)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
