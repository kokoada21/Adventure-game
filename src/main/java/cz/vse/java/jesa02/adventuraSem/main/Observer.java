package cz.vse.java.jesa02.adventuraSem.main;

public interface Observer {
    /**
     * Metoda, kteoru volá předmět pozorování (subject) při změně.
     */

    void update();
    void updateBatoh();
}
