package Builder;


import Model.Astronomy;
import Model.Atmosphere;
import Model.Item;
import Model.Location;

public class LocationBuilder {
    private String city = "", country="", region="";
    private Atmosphere atm = null;
    private Astronomy astro=null;
    private Item it = null;

    public LocationBuilder() {
    }

    public LocationBuilder city(String city){
        this.city = city;
        return this;
    }

    public LocationBuilder country(String country){
        this.country = country;
        return this;
    }

    public LocationBuilder region(String region){
        this.region = region;
        return this;
    }

    public LocationBuilder atm(Atmosphere atm){
        this.atm = atm;
        return this;
    }

    public LocationBuilder astro(Astronomy astro){
        this.astro = astro;
        return this;
    }

    public LocationBuilder it(Item it){
        this.it = it;
        return this;
    }

    public Location build(){
        return  new Location(this);
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public Atmosphere getAtm() {
        return atm;
    }

    public Astronomy getAstro() {
        return astro;
    }

    public Item getIt() {
        return it;
    }
}
