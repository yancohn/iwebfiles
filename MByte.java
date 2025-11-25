package protocol;

import java.lang.*;
import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public class MByte extends Rpc.Data
{
	private byte value;
	public byte bvalue() { return value; }
	public MByte() { value=0; }
	public MByte(byte v) { value=v; }
	public OctetsStream marshal(OctetsStream os)
	{
		return os.marshal(value);
	}
	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		value=os.unmarshal_byte();
		return os;
	}
}

