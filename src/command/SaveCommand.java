package command;

public class SaveCommand implements Command {
	SaveLoad saveLoad;
	public SaveCommand(SaveLoad saveLoad) {
		this.saveLoad = saveLoad;
	}
	public void execute() {
		saveLoad.save();
	}
}
