package protocol;

import com.goldhuman.Common.TimerTask;
import com.goldhuman.IO.Protocol.Manager;
import com.goldhuman.IO.Protocol.ReconnectTask;
import com.goldhuman.IO.Protocol.Session;
import com.goldhuman.IO.Protocol.State;

public final class ClientManager extends Manager
{
	
	public Session s;
	private boolean conn_state;
	private Object locker_state = new Object();
	private static final int BACKOFF_INIT = 2;
	private static final int BACKOFF_DEADLINE = 8;
	private static ClientManager manager=new ClientManager();
	private int backoff;
	

	ClientManager()
	{
		conn_state = false;
		backoff = BACKOFF_INIT;
	}
	
	public static ClientManager GetInstance(){
		return manager;
	}
	void Reconnect()
	{
		TimerTask.AddTimerTask(new ReconnectTask(this, 1), backoff);
		backoff *= 2;
		if (backoff > BACKOFF_DEADLINE)
			backoff = BACKOFF_DEADLINE;
	}
	protected void OnAddSession(Session session)
	{
		synchronized (locker_state)
		{
			if (conn_state)
			{
				Close(session);
				return;
			}
			conn_state = true;
			s = session;
			backoff = BACKOFF_INIT;
		}
		System.out.println("ClientManager::OnAddSession,peer=" + session.getPeerAddress());
		/**old
		s = session;
		System.out.println( "ClientManager::OnAddSession " + session );
		try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
		**/
	}

	protected  void OnDelSession(Session session)
	{
		synchronized (locker_state)
		{
			conn_state = false;
			Reconnect();
		}
		System.out.println("ClientManager::OnDelSession,peer=" + session.getPeerAddress());
		s = null;
		/**old
		System.out.println( "ClientManager::OnDelSession " + session );
		s = null;
		try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
		**/
	}

	protected  void OnAbortSession(Session session)
	{
		synchronized (locker_state)
		{
			conn_state = false;
			Reconnect();
		}
		System.out.println("ClientManager::OnAbortSession,peer=" + session.getPeerAddress());
		s = null;
		/**
		System.out.println( "ClientManager::OnAbortSession " + session );
		s = null;
		try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
		**/
	}

	protected State GetInitState()
	{
		return State.Get("normal");
	}

	protected String Identification()
	{
		return "GameDBClient";
	}

	public String identity()
	{
		return "zengpan";
	}

	public String password()
	{
		return "hello";
	}
}
