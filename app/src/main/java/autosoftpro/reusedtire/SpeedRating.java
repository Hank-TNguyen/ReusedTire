package autosoftpro.reusedtire;

/**
 * Created by Hung on 6/2/2016.
 */
public enum SpeedRating {
    ONE(1, "1/5"),
    TWO(2, "2/5"),
    THREE(3, "3/5"),
    FOUR(4, "4/5"),
    FIVE(5, "5/5");

    private String textRate;
    private int numRate;
    private SpeedRating(int numR, String textR) {
        textRate = textR;
        numRate = numR;
    }

    SpeedRating(int i){
        numRate = i;
        switch (i){
            case 1:
                textRate = "1/5";
                break;
            case 3:
                textRate = "3/5";
                break;
        }
    }

    @Override
    public String toString(){
        return textRate;
    }

    public int numRate() {
        return numRate;
    }

}
