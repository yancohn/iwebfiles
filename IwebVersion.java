package protocol;
public class IwebVersion {
	private String version = ""; 
	private String update = "";
	private String comment = "";
	private String game = "";
	private String buildDate="";
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	public IwebVersion(String version, String update, String comment, String game, String buildDate) {
		this.version = version;
		this.update = update;
		this.comment = comment;
		this.game = game;
		this.buildDate = buildDate;
	}
}
