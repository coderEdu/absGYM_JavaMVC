package newgym.controller;

public class StaticResources {

	static int lastSelectedID;
	static boolean refreshCombo;
	
	public static boolean isRefreshCombo() {
		return refreshCombo;
	}

	public static void setRefreshCombo(boolean refreshCombo) {
		StaticResources.refreshCombo = refreshCombo;
	}

	public static int getLastSelectedID() {
		return lastSelectedID;
	}

	public static void setLastSelectedID(int lastSelectedID) {
		StaticResources.lastSelectedID = lastSelectedID;
	}
	
}
