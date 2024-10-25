package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import weather.StevensonReading;
import weather.WeatherReading;

/**
 * Class for testing the StevensonReading class.
 */
public class StevensonReadingTest {
  private WeatherReading twentythreet12d3v12r;
  private Object dummy;

  @Before
    public void setup() {
    twentythreet12d3v12r = reading(23.0, 12.0, 3.0, 12.0);
  }
  
  /**
   * This method is providing a shorthand way of creating new instances of a new
   * WeatherReading object.
   * 
   * @param temperature is the temperature in degrees Celsius.
   * @param dewpoint is the dewpoint in degrees Celsius.
   * @param windspeed is the windspeed in mph.
   * @param rain is the rain in millimeters.
   * @return a new instance of a WeatherReading object.
   */
  protected WeatherReading reading(double temperature, double dewpoint, double windspeed, 
          double rain) {
    return new StevensonReading(temperature, dewpoint, windspeed, rain);
  }
  
  @Test
  public void testToString() {
    String expected = "Reading: T = 23, D = 12, v = 3, rain = 12";
    assertEquals(expected, twentythreet12d3v12r.toString());
  }
  
  @Test
  public void getTemperature() {
    assertEquals(23, reading(23.0, 12.0, 3.0, 12.0).getTemperature());
  }
  
  @Test
  public void getDewPoint() {
    assertEquals(12, reading(23.0, 12.0, 3.0, 12.0).getDewPoint());
  }
  
  @Test
  public void getWindSpeed() {
    assertEquals(3, reading(23.0, 12.0, 3.0, 12.0).getWindSpeed());
  }
  
  @Test
  public void getTotalRain() {
    assertEquals(12, reading(23.0, 12.0, 3.0, 12.0).getTotalRain());
  }
  
  @Test
  public void testGetRelativeHumidity() {
    assertEquals("Average Humidity", 50, reading(23.0, 12.0, 3.0, 12.0).getRelativeHumidity());
    assertEquals("Low Dewpoint", 23, reading(23.0, 1.0, 3.0, 12.0).getRelativeHumidity());
    assertEquals("Dewpoint same as Temp", 100, reading(23.0, 23.0, 
            3.0, 12.0).getRelativeHumidity());
    assertEquals("Close Humidity", 84, reading(30.0, 27.0, 3.0, 12.0).getRelativeHumidity());
  }
  
  @Test
  public void testGetHeatIndex() {
    assertEquals("Comfortable Heat Index Check", 85, reading(38.384805, 
            36.718655, 3.0, 12.0).getHeatIndex());
    assertEquals("Moderate Heat Index Check", 32, reading(30.0, 20.0, 3.0, 12.0).getHeatIndex());
     
  }
  
  @Test
  public void testWindChill() {
    assertEquals("Wind Chill Check", 21, reading(21.160582, 15.339, 31.242068, 43).getWindChill());
    assertEquals("Wind Chill Check 0 mph", 26, reading(20.0, 12.0, 0.0, 12.0).getWindChill());
    assertEquals("Wind Chill check High MPH", 20, reading(20.0, 0.0, 20.0, 12.0).getWindChill());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfDewPointGreaterthanTemperature() {
    reading(12.0, 23.0, 3.0, 12.0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfWindspeedNegative() {
    reading(23.0, 12.0, -3.0, 12.0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfTotalRainNegative() {
    reading(23.0, 12.0, 3.0, -12.0);
  }
  
  @Test
  public void testEquals() {
    assertTrue(twentythreet12d3v12r.equals(twentythreet12d3v12r));
    assertFalse(twentythreet12d3v12r.equals(dummy));
    assertTrue(twentythreet12d3v12r.equals(new StevensonReading(23.0, 12.0, 3.0, 12.0)));
    assertFalse(reading(23.0, 12.0, 3.0, 12.0).equals(reading(22.0, 12.0, 3.0, 12.0)));
    assertFalse(reading(23.0, 12.0, 3.0, 12.0).equals(reading(23.0, 11.0, 3.0, 12.0)));
    assertFalse(reading(23.0, 12.0, 3.0, 12.0).equals(reading(23.0, 12.0, 2.0, 12.0)));
    assertFalse(reading(23.0, 12.0, 3.0, 12.0).equals(reading(23.0, 12.0, 3.0, 11.0)));
  }
  
  @Test
  public void testHashCode() {
    WeatherReading reading1 = reading(23.0, 12.0, 3.0, 12.0);
    WeatherReading reading2 = reading(23.0, 12.0, 3.0, 12.0);
    assertEquals(reading1.hashCode(), reading2.hashCode());
  }
  
}
