package game;

public class GameLauncher {

    public GameLauncher() {
        new Window();
    }

    public static void main(String[] args) {
        System.out.println("Sterowanie:\nchodzisz na WSAD.\nRozkladasz wieze na SPACJI");
        System.out.println("Zmieniasz zywiol na ALT\nZaznaczasz cel LEWYM PRZYCISKIEM MYSZY");
        System.out.println("gdy rozlozysz wieze, zaczynasz walke na ENTER");
        System.out.println("Libcza wiezyczek wzrasta wraz z poziomem.");
        System.out.println("Pause = BACKSPACE");
        new GameLauncher();
    }

}
