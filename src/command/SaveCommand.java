package command;

import interfaces.Command;

public class SaveCommand implements Command {
	private SaveLoad saveLoad;
	public SaveCommand(SaveLoad saveLoad) {
		this.saveLoad = saveLoad;
	}
	public void execute() {
		saveLoad.save();
	}
}
