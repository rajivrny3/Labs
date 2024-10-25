package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;


/**
 * Class for testing the StevensonReading class.
 */
public class AutomaticTransmissionTest {
  private static int threshold1 = 10;
  private static int threshold2 = 20;
  private static int threshold3 = 35;
  private static int threshold4 = 50;
  private static int threshold5 = 70;
  private Transmission sampleTransmission;
  private Object dummy;

  @Before
  public void setup() {
    sampleTransmission = AutomaticTransmission.getInstance(threshold1, threshold2, threshold3, 
   threshold4, threshold5);
  }
  
  /**
   * This method is providing a shorthand way of creating new instances of a new
   * Transmission object.
   * 
   * @param threshold1 is the speed that causes the transmission to change from gear 1 to gear 2.
   * @param threshold2 is the speed that causes the transmission to change from gear 2 to gear 3.
   * @param threshold3 is the speed that causes the transmission to change from gear 3 to gear 4.
   * @param threshold4 is the speed that causes the transmission to change from gear 4 to gear 5.
   * @param threshold5 is the speed that causes the transmission to change from gear 5 to gear 6.
   * @return a new instance of a Transmission object.
   */
  protected Transmission vehicleTransmission(int threshold1, int threshold2, int
      threshold3, int threshold4, int threshold5) {
    return  AutomaticTransmission.getInstance(threshold1, threshold2, 
      threshold3, threshold4, threshold5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThreshold1Negative() {
    vehicleTransmission(-10, 20, 35, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThreshold2Negative() {
    vehicleTransmission(10, -20, 35, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThreshold3Negative() {
    vehicleTransmission(10, 20, -35, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThreshold4Negative() {
    vehicleTransmission(10, 20, 35, -50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThreshold5Negative() {
    vehicleTransmission(10, 20, 35, 50, -70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThresholdCorrectOrder1to2() {
    vehicleTransmission(30, 20, 35, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThresholdCorrectOrder2to3() {
    vehicleTransmission(10, 40, 35, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThresholdCorrectOrder3to4() {
    vehicleTransmission(10, 20, 60, 50, 70);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfThresholdCorrectOrder4to5() {
    vehicleTransmission(10, 20, 35, 80, 70);
  }
  
  
  @Test
  public void testGetSpeed() {
    assertEquals(0, sampleTransmission.getSpeed()); 
  }
  
  @Test
  public void testGetGear() {
    assertEquals(0, sampleTransmission.getGear());
  }
  
  @Test
  public void testToString() {
    String expected = "Transmission (speed = 0, gear = 0)";
    assertEquals(expected, sampleTransmission.toString());
  }
  
  @Test
  public void testEquals() {
    assertTrue(sampleTransmission.equals(sampleTransmission));
    assertFalse(sampleTransmission.equals(dummy));
    assertTrue(sampleTransmission.equals(AutomaticTransmission.getInstance(10, 20, 35, 50, 70)));
    assertFalse(vehicleTransmission(10, 20, 35, 50, 70).equals(vehicleTransmission(11, 
        20, 35, 50, 70)));
    assertFalse(vehicleTransmission(10, 20, 35, 50, 70).equals(vehicleTransmission(10, 
        21, 35, 50, 70)));
    assertFalse(vehicleTransmission(10, 20, 35, 50, 70).equals(vehicleTransmission(10, 
        20, 33, 50, 70)));
    assertFalse(vehicleTransmission(10, 20, 35, 50, 70).equals(vehicleTransmission(10, 
        20, 35, 54, 70)));
    assertFalse(vehicleTransmission(10, 20, 35, 50, 70).equals(vehicleTransmission(10, 
        20, 35, 50, 75)));
  }

  @Test
  public void testHashCode() {
    Transmission transmission1 = vehicleTransmission(10, 20, 35, 50, 70);
    Transmission transmission2 = vehicleTransmission(10, 20, 35, 50, 70);
    assertEquals(transmission1.hashCode(), transmission2.hashCode());
  }
  
  @Test
  public void testIncreaseSpeed() {
    sampleTransmission.increaseSpeed();
    assertEquals(1, sampleTransmission.getSpeed()); 
    assertEquals(1, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold1; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(10, sampleTransmission.getSpeed()); 
    assertEquals(2, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold2; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(20, sampleTransmission.getSpeed());
    assertEquals(3, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold3; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(35, sampleTransmission.getSpeed());
    assertEquals(4, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold4; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(50, sampleTransmission.getSpeed());
    assertEquals(5, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold5; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(70, sampleTransmission.getSpeed());
    assertEquals(6, sampleTransmission.getGear());
  }
  
  @Test
  public void testDecreaseSpeed() {
    sampleTransmission.increaseSpeed();
    sampleTransmission.decreaseSpeed();
    assertEquals(0, sampleTransmission.getSpeed());
    
    for (int i = sampleTransmission.getSpeed(); i < threshold5; i++) {
      sampleTransmission.increaseSpeed();
    }
    assertEquals(70, sampleTransmission.getSpeed());
    assertEquals(6, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i > threshold4; i--) {
      sampleTransmission.decreaseSpeed();
    }
    assertEquals(50, sampleTransmission.getSpeed());
    assertEquals(5, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i > threshold3; i--) {
      sampleTransmission.decreaseSpeed();
    }
    assertEquals(35, sampleTransmission.getSpeed());
    assertEquals(4, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i > threshold2; i--) {
      sampleTransmission.decreaseSpeed();
    }
    assertEquals(20, sampleTransmission.getSpeed());
    assertEquals(3, sampleTransmission.getGear());
    
    for (int i = sampleTransmission.getSpeed(); i > threshold1; i--) {
      sampleTransmission.decreaseSpeed();
    }
    assertEquals(10, sampleTransmission.getSpeed());
    assertEquals(2, sampleTransmission.getGear());
    
    sampleTransmission.decreaseSpeed();
    assertEquals(9, sampleTransmission.getSpeed());
    assertEquals(1, sampleTransmission.getGear());
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfSpeedLessThanZero() {
    sampleTransmission.decreaseSpeed();
  }
  
}
