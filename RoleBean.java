
// RoleBean

package protocol;

public class RoleBean
{
	public User				user;
	public GRoleBase		base;
	public GRoleStatus		status;
	public GRolePocket		pocket;
	public GRoleStorehouse	storehouse;
	public GRoleTask		task;
	//public GFriendList		friendlist;
	//public MessageVector	messages;

	public RoleBean() {
		user = new User();
		base = new GRoleBase();
		status = new GRoleStatus();
		pocket = new GRolePocket();
		storehouse = new GRoleStorehouse();
		task = new GRoleTask();
	}

	public String getLogString()
	{
		String n = "";
		try { n = base.name.getString(); } catch (Exception e) { e.printStackTrace(); }
		return new String("roleid="+base.id+",name="+n+",level="+status.level
					+",exp="+status.exp+",pp="+status.pp+",money="+pocket.money);
	}

	public static String ClsName( int cls )
	{
		switch( cls )
		{
			case 0:		return "ÎäÏÀ";
			case 1:		return "·¨Ê¦";
			case 2:		return "É®ÂÂ";
			case 3:		return "Ñı¾«";
			case 4:		return "ÑıÊŞ";
			case 5:		return "÷ÈÁé";
			case 6:		return "ÓğÃ¢";
			case 7:		return "ÓğÁé";
			default:	return "Î´Öª";
		}
	}

	public static String GenderName( byte gender )
	{
		return (gender > 0 ? "Å®" : "ÄĞ");
	}

	public static String StatusName( int status )
	{
		if( 1 == status )
			return "Õı³£";
		else if( 2 == status )
			return "±ØĞëÉ¾³ı";
		else if( 3 == status )
			return "×¼±¸É¾³ı";
		else
			return "Î´Öª";
	}

}

