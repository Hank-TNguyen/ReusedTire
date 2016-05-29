package autosoftpro.reusedtire;

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

    public Tire(TireBrand tb, int w, int r, int d){
        this.size= new Size(w,r,d);
        this.brand = tb;
    }

    public Tire(){

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
