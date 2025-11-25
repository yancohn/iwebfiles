package protocol;
import com.goldhuman.Common.Conf;
public class IwebVersionManager {
	private IwebVersion confVersion ; //etc/iweb.conf配置的版本信息
	private IwebVersion localVersion ; //classes/version.conf配置的版本信息
	private IwebVersionManager() {
		try {
			Conf conf = Conf.GetInstance("/etc/iweb.conf", null);
			String section = "iweb";
			String version = conf.find(section, "version");
			String update = conf.find(section, "update");
			String game = conf.find(section, "game");
			String comment = conf.find(section, "comment");
			String buildDate = conf.find(section, "build");
			confVersion = new IwebVersion(version, update, comment, game, buildDate);
			System.out.println("iweb.conf version=" + version);
		       	System.out.println("iweb.conf update=" + update + ",game=" + game);
		       	System.out.println("iweb.conf comment=" + comment + ",buildDate=" + buildDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			JioConfig conf = JioConfig.GetInstance(JioConfig.class.getResource("/version.conf"), null);
			String section = "version";
			String version = conf.find(section, "version");
			String update = conf.find(section, "update");
			String game = conf.find(section, "game");
			String comment = conf.find(section, "comment");
			String buildDate = conf.find(section, "build");
			localVersion = new IwebVersion(version, update, comment, game, buildDate);
			System.out.println("version.conf version=" + version);
		       	System.out.println("version.conf update=" + update + ",game=" + game);
		       	System.out.println("version.conf comment=" + comment + ",buildDate=" + buildDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static IwebVersionManager instance;
	static {
		instance = new IwebVersionManager();
	}
	public static IwebVersionManager getInstance() { return instance; }
	public static IwebVersion getIwebConfVersion() { // /etc/iweb.conf
		return instance.confVersion;
	}
	public static IwebVersion getVersionConfVersion() { // classes/version.conf
		return instance.localVersion;
	}

}
