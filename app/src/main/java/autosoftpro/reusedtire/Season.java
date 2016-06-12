package autosoftpro.reusedtire;

/**
 * Created by Hung on 6/2/2016.
 */
public enum Season {
    ALLSEASON("All-season"),
    ALLWEATHER("All-weather"),
    WINTER("Winter");

    private String type;
    private Season(String t){
        type = t;
    }

    @Override
    public String toString(){
        return type;
    }
}
