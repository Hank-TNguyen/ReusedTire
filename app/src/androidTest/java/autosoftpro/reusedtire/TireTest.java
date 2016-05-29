package autosoftpro.reusedtire;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Hung on 5/29/2016.
 */

@RunWith(Suite.class)
public class TireTest extends TestCase{

    public void testTireObject1(){
        Size size = new Size(155 , 25 , 14);
        TireBrand tb = TireBrand.Bridgestone;
        Tire tire = new Tire(tb, size);

        assertNotNull(tire.toString());
        assertNotNull(tire.getSize());
        assertEquals("Width not matched", tire.getSize().getWidth(), Integer.valueOf(155));
        assertEquals("Ratio not matched", tire.getSize().getRatio(), Integer.valueOf(25));
        assertEquals("Diameter not matched", tire.getSize().getDiameter(), Integer.valueOf(14));
        assertEquals("Brand not matched", tire.getBrand().toString(), "Bridgestone");
    }

    public void testTireObject2(){
        Size size = new Size(355 , 65 , 28);
        TireBrand tb = TireBrand.Muteki;
        Tire tire = new Tire(tb, size);

        assertNotNull(tire.toString());
        assertNotNull(tire.getSize());
        assertEquals("Width not matched", tire.getSize().getWidth(), Integer.valueOf(355));
        assertEquals("Ratio not matched", tire.getSize().getRatio(), Integer.valueOf(65));
        assertEquals("Diameter not matched", tire.getSize().getDiameter(), Integer.valueOf(28));
        assertEquals("Brand not matched", tire.getBrand().toString(), "Muteki");
    }

}
