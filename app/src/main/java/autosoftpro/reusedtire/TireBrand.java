package autosoftpro.reusedtire;

public enum TireBrand{
    Bridgestone("Bridgestone"),
    Continental("Continental"),
    Falken("Falken"),
    Firestone("Firestone"),
    Michelin("Michellin"),
    Muteki("Muteki"),
    Pirelli("Pirelli"),
    Yokohama("Yokohama");

    private String brand;
    TireBrand(String b){
        brand = b;
    }

    @Override
    public String toString(){
        return brand;
    }
}

