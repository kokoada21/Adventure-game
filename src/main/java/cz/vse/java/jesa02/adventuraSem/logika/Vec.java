package cz.vse.java.jesa02.adventuraSem.logika;


import java.awt.*;

/**
 * Tato třída definuje objekty hry, které lze přenášet z místnosti do místnosti a předávat je jiným postavám.
 *
 * @author Adam Ješina
 * @version 24.5.2020
 */
public class Vec
{
    private String nazev;
    private boolean prenositelna;
    private boolean nositelne;
    private Image image;
    
    /**
     *  Vytváří instance třídy Vec, jejich název, přenositelnost a možnost jejich obléknutí.
     */
    
    public Vec(String nazev, boolean prenositelna, boolean nositelne)
    {
       this.nazev = nazev;
       this.prenositelna = prenositelna;
       this.nositelne = nositelne;
       this.image = image;
    }
        
    /**
     * Vrátí jmeno věci.
     */
    public String getNazev()
    {
        return nazev;
        
    }
    
    /**
     *  Vrátí boolean funkci zda je věc možné přenášet či nikoli.
     */
    public boolean isPrenositelna(){
        return prenositelna;
    }
    
    /**
     *  Vrátí boolean funkci zda je věc možné nosit či ne.
     */
    public boolean isObleceni(){
        return nositelne;
    }

    public Image getImage(){
        return image;
    }

    @Override
    public String toString() {
        return getNazev();
    }
}
