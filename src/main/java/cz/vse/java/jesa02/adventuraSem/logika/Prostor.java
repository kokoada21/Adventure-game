package cz.vse.java.jesa02.adventuraSem.logika;

import cz.vse.java.jesa02.adventuraSem.main.Observer;
import cz.vse.java.jesa02.adventuraSem.main.Subject;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry a věci a postavy v nich obsažené
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Ješina
 *@version    LS 2020
 */
public class Prostor {

    private String nazev;
    private boolean konecHry;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Vec> seznamVeci; //obsahuje věci
    private Set<Postava> seznamPostav; //obsahuje postavy
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník"
     * vytváří kolekce postav a věcí a východů
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamVeci = new HashSet<Vec>();
        seznamPostav = new HashSet<Postava>();
    }
    
    /** 
     * Metoda vypisující informace o aktuální místnosti, jako jsou východy, věci a postavy
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru " + popis + ".\n"
                + popisVychodu() + ".\n"
                + popisVeci()+ ".\n"
                + popisPostav();
    }
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci v místnosti, například:
     * "veci: triko ".
     *
     * @return Popis věcí - názvy všech věcí v místnosti
     */
    public String popisVeci(){
        String popisyVeci = "Věci:";
        for (Vec jmeno : seznamVeci) {
            popisyVeci += " " + jmeno.getNazev();
        }
        return popisyVeci;
    }

    public Collection<Vec> getSeznamVeci(){
        Collection<Vec> seznam = null;
        for (Vec vec : seznamVeci) {
            seznam.add(vec);
        }
        return seznam;
    }


    /**
     * Vrací textový řetězec, který popisuje postavy v mítnosti, například:
     * "Postavy: otec ".
     *
     * @return Popis postav - názvy postav v místnosti
     */
    private String popisPostav(){
        String popisyPostav = "Postavy:";
        for (Postava clovek : seznamPostav) {
            popisyPostav += " " + clovek.getJmenoPost();
        }
        return popisyPostav;
    }
    
    /**
     * Metoda pro vkládání postav do místností. Užívá třída HerniPlan.
     */
    public void vlozPostavu(Postava clovek)
    {
        seznamPostav.add(clovek);
    }
    
    /**
     * Metoda pro vkládání věcí do místností. Užívá třída HerniPlan.
     */
    public void vlozVec(Vec neco)
    {
        seznamVeci.add(neco);
    }
    
    /**
     * Metoda vrací hodnotu true, když je osoba v místnosti, když není, vrací false
     */
    public boolean obsahujePostavu(String jmenoPost)
    {
        for (Postava clovek :seznamPostav) {
            if ( clovek.getJmenoPost().equals(jmenoPost) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Metoda vrací hodnotu true, když je věc v místnosti, když není, vrací false
    */
    public boolean obsahujeVec(String nazevVeci)
    {
        for (Vec neco :seznamVeci) {
            if ( neco.getNazev().equals(nazevVeci) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Metoda vrací odkaz na postavu z místnosti.
    */
    public Postava vratPostavu(String jmenoPostavy){
        Postava vybranaPostava = null;
        for (Postava nekdo :seznamPostav ) {
            if (nekdo.getJmenoPost().equals(jmenoPostavy) ) {
                vybranaPostava = nekdo; 
            }
        }
        return vybranaPostava; 
    }
    
    
    /**
    * Metoda vrací vec z místnosti, zároveň ověřuje její přenositelnost a odstraňuje ji.
    */
    public Vec vzitVec(String nazevVeci){
        Vec vybranaVec = null;
        for ( Vec neco :seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci) ) {
                vybranaVec = neco;
            }
        }
         
        if ( vybranaVec != null ) {
            if ( vybranaVec.isPrenositelna() ) {                
                    seznamVeci.remove(vybranaVec);                
            } 
        }
        return vybranaVec;        
    }
    
     /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
    
        /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */ 
     @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

     /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    
     /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }


    /**Prepsana metoda toString pro panel s mistnostmi**/
    @Override
    public String toString() {

        return getNazev();
    }


}
