package command;

public class LoadCommand implements Command {
	SaveLoad saveLoad;
	public LoadCommand(SaveLoad saveLoad) {
		this.saveLoad = saveLoad;
	}
	public void execute() {
		saveLoad.load();
	}
}
