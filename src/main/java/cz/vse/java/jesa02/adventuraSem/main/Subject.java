package cz.vse.java.jesa02.adventuraSem.main;

public interface Subject {
    /**
     * registrace pozorovatele
     * @param observer
     */
     void register(Observer observer);

    /**
     * odebrání pozorovatele ze seznamu
     * @param observer
     */
    void unregister(Observer observer);

    /**
     * upozorní všechny registrované pozorovatele na změnu
     * nemusí být součástí rozhraní
     */
    void notifyObservers();
}
