package autosoftpro.reusedtire;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hung on 5/29/2016.
 *
 * Tire object has the following properties:
 * Size: Width, Ratio, Diameter
 * Brand
 *
 **/



public class Tire {
    private TireBrand brand;
    private Size size;
    private String year;
    private String make;
    private Season season;
    private SpeedRating rating;
    private String idOnServer = null;

    public String getIdOnServer() {
        return idOnServer;
    }

    public void setIdOnServer(String idOnServer) {
        this.idOnServer = idOnServer;
    }

    public Tire(TireBrand tb, int w, int r, int d, String year, String make, Season season, SpeedRating sr ){
        this.size = new Size(w,r,d);
        this.brand = tb;
        this.year = year;
        this.make = make;
        this.season = season;
        this.rating = sr;
    }

    public Tire(){}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public SpeedRating getRating() {
        return rating;
    }

    public void setRating(SpeedRating rating) {
        this.rating = rating;
    }

    public Tire(TireBrand tb, Size s){
        this.brand = tb;
        this.size = s;
    }

    public TireBrand getBrand() {
        return brand;
    }

    public void setBrand(TireBrand brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String toString(){
        return "Tire: " + "Brand: " + getBrand()
                + " , Size: " + size.toString();
    }

}

class Size{
    private Integer Width;
    private Integer Ratio;
    private Integer Diameter;

    public Size(int w, int r, int d){
        this.Width = w;
        this.Ratio = r;
        this.Diameter = d;
    }

    public Integer getWidth() {
        return Width;
    }

    public void setWidth(Integer width) {
        Width = width;
    }

    public Integer getRatio() {
        return Ratio;
    }

    public void setRatio(Integer ratio) {
        Ratio = ratio;
    }

    public Integer getDiameter() {
        return Diameter;
    }

    public void setDiameter(Integer diameter) {
        Diameter = diameter;
    }

    public String toString(){
        return "Width: " + getWidth()
                + " , Ratio: " + getRatio()
                + " , Diameter: " + getDiameter();
    }
}
