/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boodcamp;


public class Location {
    private String ciudad, pais, region;
    private Atmosphere atm;
    private Astronomy astro;
    private Item it;

    public Location(String ciudad, String pais, String region, Atmosphere atm, Astronomy astro, Item it) {
        this.ciudad = ciudad;
        this.pais = pais;
        this.region = region;
        this.atm = atm;
        this.astro = astro;
        this.it = it;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Atmosphere getAtm() {
        return atm;
    }

    public void setAtm(Atmosphere atm) {
        this.atm = atm;
    }

    public Astronomy getAstro() {
        return astro;
    }

    public void setAstro(Astronomy astro) {
        this.astro = astro;
    }

    public Item getIt() {
        return it;
    }

    public void setIt(Item it) {
        this.it = it;
    }

    
        
    
}
