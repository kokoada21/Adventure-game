package cz.vse.java.jesa02.adventuraSem.logika;

 /**
     *  Tato třída definuje postavy ve hře, které se následně vyskytují v jednotlivých místnostech.
     *  Postavy může hráč oslovit případně s nimi vyměňovat věci
     *@author     Adam Ješina
     *@version    LS 2020
 **/
public class Postava
{
    private String jmeno;
    private boolean isZla;
    private String proslov;
    private String zlyProslov;
    private Vec coMa;
    private Vec coChce;

    /**
     * Konstruktor třídy batoh, přiřazuje hodnotu jmena, určuje zda je postava zlá a definuje dva typy proslovů.
     */
    public Postava(String jmeno, boolean isZla, String proslov, String zlyProslov)
    {
       this.jmeno = jmeno;
       this.isZla = isZla;
       this.proslov = proslov;
       this.zlyProslov = zlyProslov;
    }
    /**
    * Metoda vrací jméno postavy.
    */
    public String getJmenoPost()
    {
        return jmeno;
    }
    
    /**
    * Metoda boolean hodnotu, zda je postava zlá.
    */
    public boolean getZla(){
        return isZla;
    }
    
    /**
    * Metoda vrací proslov postavy v závislosti na stavu hodna/zla.
    */
    public String getProslov(){
        if(isZla){
        return zlyProslov;
        }        
        return proslov;        
    }
    
    /**
    * Metoda nastavuje hodnotu isZla na true.
    */
    public void setZla(){
        isZla = true;
    }
    
    /**
    * Metoda nastavuje hodnotu isZla na false.
    */
    public void setHodna(){
        isZla = false;
    }
    
    /**
    * Metoda nastavuje vybrané postavě předměty pro výměnu.
    */
    public void setVymena(Vec coMa, Vec coChce){
        this.coMa = coMa;
        this.coChce = coChce;
    }
    
    /**
    * Metoda vrací Vec, kterou postava chce výměnou za věc, kterou vlastní.
    */
    public Vec getCoChce(){
        return coChce; 
    }
    
    /**
    * Metoda vrací Vec, kterou postava vlastni.
    */
    public Vec getCoMa(){
        return coMa; 
    }
    
    /**
    * Metoda ověřuje, zda chce osoba něco směnit či ne v závislosti na proměnných coMa, coChce.
    */
    public boolean chceMenit(){
        if(coMa == null || coChce == null){
        return false;
        }
        return true; 
    }
    
    /**
    * Metoda nastavuje hodnoty coMa a coChce na null, zabraňuje tak opakovanému vyměňování.
    */
    public void setUzVymenil(){
        this.coMa = null;
        this.coChce = null; 
    }
    
    /**
    * Metoda vrací String hodnotu požadovaných předmětů k výměně.
    */
    public String getPredmetyKVymene(){
        return " dam ti: " + coMa.getNazev() + " vymenou za: " + coChce.getNazev();
    }
    
}
