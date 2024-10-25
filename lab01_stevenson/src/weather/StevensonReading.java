package weather;

import java.util.Objects;

/**
 * Stevenson weather reading which is a weather reading taken from a Stevenson station
 *  represented as temperature in Celsius, dewpoint in Celsius,
 * windspeed in mph and amount of total rain over the last 24 hours in millimeters.
 */
public final class StevensonReading implements WeatherReading {
  private final double temperature;
  private final double dewpoint;
  private final double windspeed;
  private final double rain;
  
  /**
   * Constructs a single reading of a weather station.
   * 
   * @param temperature the air temperature in celsius
   * @param dewpoint the dew point temperature in celsius
   * @param windspeed the wind speed in miles per hour
   * @param rain the total rain received in the last 24 hours in millimeters
   */
  public StevensonReading(double temperature, double dewpoint, double windspeed, double rain) {
    if (dewpoint > temperature) {
      throw new IllegalArgumentException("Dew point temperature cannot be greater than the air "
              + "temperature");
    }
    if (windspeed < 0) {
      throw new IllegalArgumentException("Windspeed cannot be negative");
    }
    if (rain < 0) {
      throw new IllegalArgumentException("Total rain received in the last 24 "
                + "hours cannot be negative");
    }
    this.temperature = temperature;
    this.dewpoint = dewpoint;
    this.windspeed = windspeed;
    this.rain = rain;
  }
    
  @Override
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(dewpoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(windspeed);
  }

  @Override
  public int getTotalRain() {
    return (int) Math.round(rain);
  }

  @Override
  public int getRelativeHumidity() {
    double avp = 6.11 * Math.pow(10, ((7.5 * dewpoint) / (237.3 + dewpoint)));
    double svp = 6.11 * Math.pow(10, ((7.5 * temperature) / (237.3 + temperature)));
    double rh1 = (avp / svp) * 100;
    int rh = (int) Math.round(rh1);
    return rh;
  }

  @Override
  public int getHeatIndex() {
    double avp = 6.11 * Math.pow(10, ((7.5 * dewpoint) / (237.3 + dewpoint)));
    double svp = 6.11 * Math.pow(10, ((7.5 * temperature) / (237.3 + temperature)));
    double temprh = (avp / svp) * 100.0;
    double c1 = -8.78469475556;
    double c2 = 1.61139411; 
    double c3 = 2.33854883889;
    double c4 = -0.14611605;
    double c5 = -0.012308094;
    double c6 = -0.0164248277778;
    double c7 = 0.002211732;
    double c8 = 0.00072546;
    double c9 = -0.000003582;
    
    double calc1 = c1;
    double calc2 = c2 * temperature;
    double calc3 = c3 * temprh;
    double calc4 = c4 * temperature * temprh;
    double calc5 = c5 * Math.pow(temperature, 2);
    double calc6 = c6 * Math.pow(temprh, 2);
    double calc7 = c7 * Math.pow(temperature, 2) * temprh;
    double calc8 = c8 * temperature * Math.pow(temprh, 2);
    double calc9 = c9 * Math.pow(temperature, 2) * Math.pow(temprh, 2);
    
    
    
    
    double heatindex = calc1 + calc2 + calc3 + calc4 + calc5 
            +  calc6 + calc7 + calc8 + calc9;

    
    int hi  = (int) Math.round(heatindex);
    return hi;
  }

  @Override
  public int getWindChill() {
    double fahrenheitCalculator = (9.0 / 5.0 * temperature) + 32;
    double fwc = 35.74 + (0.6215 * fahrenheitCalculator) - 35.75 
            * Math.pow(windspeed, 0.16) + (0.4275 * fahrenheitCalculator)
            * Math.pow(windspeed, 0.16);
    double revert = (fwc - 32) / 1.8;
    int wc = (int) Math.round(revert);
    return wc;
  }
  
  @Override
  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", 
              getTemperature(), getDewPoint(), getWindSpeed(), getTotalRain());
  }
  
  @Override
  
  public boolean equals(Object o) {
    // Fast path for pointer equality.
    if (this == o) {
      return true;
    }
    // If o isn't the right class than it cannot be equal
    if (!(o instanceof StevensonReading)) {
      return false;
    }
    StevensonReading that = (StevensonReading) o;
    if (this.temperature == that.temperature 
            && this.dewpoint == that.dewpoint
            && this.windspeed == that.windspeed
            && this.rain == that.rain) {
      return true;
    }
    return false;
  }

  
  @Override
  public int hashCode() {
    return Objects.hash(temperature, dewpoint, windspeed, rain);
  }
  
}
