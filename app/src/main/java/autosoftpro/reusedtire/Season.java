package autosoftpro.reusedtire;

/**
 * Created by Hung on 6/2/2016.
 */
public enum Season {
    ALLSEASON("All Season"),
    ALLWEATHER("All Weather"),
    WINTER("Winter");

    private String type;
    private Season(String t){
        type = t;
    }

    public static Season fromString(String text){
        if(text != null){
            for (Season s: Season.values()){
                if (text.equalsIgnoreCase(s.type)){
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return type;
    }
}
