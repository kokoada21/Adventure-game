package cz.vse.java.jesa02.adventuraSem.logika;


import cz.vse.java.jesa02.adventuraSem.main.Observer;
import cz.vse.java.jesa02.adventuraSem.main.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 *  Class HernPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, vkládá do nich věci a postavy
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  pamatuje si hráčův batoh
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Ješina
 *@version    LS 2020
 */
public class HerniPlan implements Subject {

    private Prostor aktualniProstor;
    private Batoh mujBatoh;
    private Set<Observer> seznamPozorovatelu;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví pokoj.
     * Vytváří instanci třídy Batoh
     * Vytváři instanci pozorovatelu
     */
    public HerniPlan() {
        zalozProstoryHry();
        seznamPozorovatelu = new HashSet<>();
        mujBatoh = new Batoh();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Vkládá do prostorů osoby a předměty.
     * Jako výchozí aktuální prostor nastaví pokoj.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor pokoj = new Prostor("pokoj", "tvuj pokoj");
        Prostor chodba = new Prostor("chodba", "chodba, ktera vede do vstupni haly a do koupelny");
        Prostor koupelna = new Prostor("koupelna", "koupelna");
        Prostor hala = new Prostor("hala", "vstupni hala domu");
        Prostor kuchyn = new Prostor("kuchyn", "prostorna kuchyn");
        Prostor obyvaci_pokoj = new Prostor("obyvaci_pokoj", "obyvaci pokoj");
        Prostor krizovatka = new Prostor("krizovatka", "krizovatka, odsud se dostanes do celeho mesta");
        Prostor park = new Prostor("park", "park s lavickami, keři, stromy a fontanou");
        Prostor patrikuv_dum = new Prostor("patrikuv_dum", "dum, ve kterem bydli tvuj kamarad Patrik");
        Prostor obchod = new Prostor("obchod", "obchod s potravinami");
        Prostor ericin_dum = new Prostor("ericin_dum", "dum, ve kterem bydli tvoje kamaradka Erika");
        Prostor klub = new Prostor("klub", "klub, ve kterem jsi vcera slavil");
        // přiřazují se průchody mezi prostory (sousedící prostory)
        pokoj.setVychod(chodba);
        chodba.setVychod(pokoj);
        chodba.setVychod(koupelna);
        chodba.setVychod(hala);
        koupelna.setVychod(chodba);
        hala.setVychod(chodba);
        hala.setVychod(kuchyn);
        hala.setVychod(obyvaci_pokoj);
        hala.setVychod(krizovatka);
        kuchyn.setVychod(hala);
        obyvaci_pokoj.setVychod(hala);
        krizovatka.setVychod(hala);
        krizovatka.setVychod(park);
        krizovatka.setVychod(obchod);
        krizovatka.setVychod(klub);
        park.setVychod(krizovatka);
        park.setVychod(patrikuv_dum);
        patrikuv_dum.setVychod(park);
        patrikuv_dum.setVychod(klub);
        klub.setVychod(patrikuv_dum);
        klub.setVychod(krizovatka);
        obchod.setVychod(krizovatka);
        obchod.setVychod(ericin_dum);
        ericin_dum.setVychod(obchod);

        Vec triko = new Vec("kalhoty", true, true);
        Vec kalhoty = new Vec("triko", true, true);
        Vec stul = new Vec("stul", false, false);
        pokoj.vlozVec(stul);
        pokoj.vlozVec(triko);
        pokoj.vlozVec(kalhoty);
        pokoj.vlozVec(stul);

        Vec sprcha = new Vec("sprcha", false, false);
        koupelna.vlozVec(sprcha);

        Vec boty = new Vec("boty", true, true);
        hala.vlozVec(boty);

        Vec klice = new Vec("klice", true, false);
        Vec lednice = new Vec("lednice", false, false);
        Postava otec = new Postava("otec", false, "Dobre rano, doufam ze sis to vcera uzil, klíče máš tady.", "No to je dost. Ty ses teda zridil.");
        kuchyn.vlozVec(klice);
        kuchyn.vlozVec(lednice);
        kuchyn.vlozPostavu(otec);

        Vec pohovka = new Vec("pohovka", false, false);
        Postava mamka = new Postava("mamka", false, "Dobre rano, doufam ze sis to vcera uzil", "No to je dost. Ty ses teda zridil.");
        obyvaci_pokoj.vlozVec(pohovka);
        obyvaci_pokoj.vlozPostavu(mamka);

        Vec odpadky = new Vec("odpadky", true, false);
        Postava policista = new Postava("policista", false, "Dobry den", "Jste zatcen za verejne obnazovani");
        krizovatka.vlozVec(odpadky);
        krizovatka.vlozPostavu(policista);

        Vec chleba = new Vec("chleba", true, false);
        obchod.vlozVec(chleba);

        Vec penezenka = new Vec("penezenka", true, false);
        Postava tulak = new Postava("tulak", true, "Zdravim, mozna jsem nejakou penezenku nasel, ale nebude to jen tak", "Jdi pryc!");
        tulak.setVymena(penezenka, chleba);
        park.vlozPostavu(tulak);

        Postava patrik = new Postava("Patrik", false, "Ahoj, tvuj mobil bych hledal v klubu", "Proboha vem si něco na sebe.");
        patrikuv_dum.vlozPostavu(patrik);

        Vec mobil = new Vec("mobil", true, false);
        Vec triko_fanouska = new Vec("triko_fanouska", true, true);
        Postava vyhazovac = new Postava("vyhazovac", false, "Zdravim, dneska se tu našel mobil.", "Jdi pryč!");
        klub.vlozVec(triko_fanouska);
        klub.vlozVec(mobil);
        klub.vlozPostavu(vyhazovac);

        Postava erika = new Postava("Erika", false, "Ahoj, nic si napamatuju, snad neco vi Patrik", "Proboha vem si něco na sebe.");
        ericin_dum.vlozPostavu(erika);

        aktualniProstor = pokoj;  // hra začíná v pokoji       
    }

    /**
     * Metoda vrací odkaz na hráčův batoh.
     *
     * @return hráčův batoh
     */

    public Batoh getBatoh() {
        return mujBatoh;
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {

        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     * reistrace pozorovatele, metoda rozhrani Subject
     * @param observer
     */
    @Override
    public void register(Observer observer) {
        seznamPozorovatelu.add(observer);
    }

    /**
     * odebrani pozorovatele, metoda rozhrani Subject
     * @param observer
     */
    @Override
    public void unregister(Observer observer) {
        seznamPozorovatelu.remove(observer);
    }

    /**
     * Metoda, která upozornuje observers na zmenu sv subjectu.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer: seznamPozorovatelu){
            observer.update();
        }
    }
}
