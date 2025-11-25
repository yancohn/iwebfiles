
package protocol;

 
import com.goldhuman.Common.TimerTask;
import com.goldhuman.IO.PollIO;
import com.goldhuman.IO.Protocol.Manager;
import com.goldhuman.IO.Protocol.Protocol;
import com.goldhuman.IO.Protocol.ReconnectTask;
import com.goldhuman.IO.Protocol.Session;
import com.goldhuman.IO.Protocol.State;


public final class DeliveryClientManager extends Manager
{
	private static DeliveryClientManager manager=new DeliveryClientManager();
	public static DeliveryClientManager GetInstance(){
		return manager;
	}
    public Session s;

	private boolean conn_state;
    private Object locker_state = new Object();
    private static final int BACKOFF_INIT =2;
    private static final int BACKOFF_DEADLINE = 8;
    private int backoff;
    void Reconnect()
    {
		System.out.println("DeliveryClientManager reconnect");
        TimerTask.AddTimerTask(new ReconnectTask(this, 1), backoff);
        backoff *= 2;
        if (backoff > BACKOFF_DEADLINE) backoff = BACKOFF_DEADLINE;
    }

    DeliveryClientManager() { conn_state = false; backoff = BACKOFF_INIT; }
    public boolean GetConnectState() { return conn_state; }

    synchronized protected void OnAddSession(Session session)
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
    	System.out.println( "DeliveryClientManager::OnAddSession " + session );
    	AnnounceLinkType ku = (AnnounceLinkType)Protocol.Create("AnnounceLinkType");
        Send(s,ku);
        PollIO.WakeUp();
    	/**old
			s = session;
            System.out.println( "DeliveryClientManager::OnAddSession " + session );
		AnnounceLinkType ku = (AnnounceLinkType)Protocol.Create("AnnounceLinkType");
       Send(s,ku);
		PollIO.WakeUp();
            try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
        **/
    }

	protected synchronized void OnDelSession(Session session)
    {
        synchronized (locker_state){
            conn_state = false;
            Reconnect();
        }
        System.out.println( "DeliveryClientManager::OnDelSession " + session );
//        s = null;
//        try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
    }

    protected synchronized void OnAbortSession(Session session)
    {

        synchronized (locker_state){
            conn_state = false;
            Reconnect();
        }
        System.out.println( "DeliveryClientManager::OnAbortSession " + session );
//        s = null;
//        try { notifyAll(); } catch(Exception ex) { ex.printStackTrace(); }
    }

    protected State GetInitState()
    {
        return State.Get("normal");
    }

    protected String Identification()
    {
        return "DeliveryClient";
    }
}
