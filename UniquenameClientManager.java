
package protocol;

 
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.Common.Security.*;


public final class UniquenameClientManager extends Manager
{
	public Session s;

	private boolean conn_state;
	private Object locker_state = new Object();
	private static final int BACKOFF_INIT =2;
	private static final int BACKOFF_DEADLINE = 8;
	private int backoff;
	void Reconnect()
	{
		TimerTask.AddTimerTask(new ReconnectTask(this, 1), backoff);
		backoff *= 2;
		if (backoff > BACKOFF_DEADLINE) backoff = BACKOFF_DEADLINE;
	}

	UniquenameClientManager() { conn_state = false; backoff = BACKOFF_INIT; }
	public boolean GetConnectState() { return conn_state; }

	synchronized protected void OnAddSession(Session session)
	{
		synchronized (locker_state){
			if (conn_state)
			{
				Close(session);
				return;
			}
			conn_state = true;
			s = session;
			backoff = BACKOFF_INIT;
		}
		System.out.println( "UniquenameClientManager::OnAddSession " + session );
		notify();
	}

	protected void OnDelSession(Session session)
	{
		synchronized (locker_state){
			conn_state = false;
			Reconnect();
		}
		System.out.println( "UniquenameClientManager::OnDelSession " + session );
		s = null;
	}

	protected void OnAbortSession(Session session)
	{

		synchronized (locker_state){
			conn_state = false;
			Reconnect();
		}
		System.out.println( "UniquenameClientManager::OnAbortSession " + session );
		s = null;
	}

	protected State GetInitState()
	{
		return State.Get("normal");
	}

	protected String Identification()
	{
		return "UniqueNameClient";
	}
}
