package transmission;

import java.util.Objects;

/**
 * AutomaticTransmission represents a transmission system for a car where the thresholds
 * for gear shifts are predefined in the form of a monotonically increasing function of speed
 * where each of these thresholds represent a gear shift and are calculated in miles per hour.
 * 
 */
public final class AutomaticTransmission implements Transmission {
	
  private static AutomaticTransmission instance;
  private final int threshold1;
  private final int threshold2;
  private final int threshold3;
  private final int threshold4;
  private final int threshold5;
  private int speed;
  private int gear;


  /**
* Constructs a single automatic car's transmission system.
* 
* @param threshold1 represents the threshold that causes the transmission 
*     to change from gear 1 to gear 2.
* @param threshold2 represents the threshold that causes the transmission 
*     to change from gear 2 to gear 3
* @param threshold3 represents the threshold that causes the transmission 
*     to change from gear 3 to gear 4
* @param threshold4 represents the threshold that causes the transmission 
*     to change from gear 4 to gear 5
* @param threshold5 represents the threshold that causes the transmission 
*     to change from gear 5 to gear 6

   */
  private AutomaticTransmission(int threshold1, int threshold2, 
      int threshold3, int threshold4, int threshold5) {
    speedValidation(threshold1, threshold2, threshold3, threshold4, threshold5);
    this.threshold1 = threshold1;
    this.threshold2 = threshold2;
    this.threshold3 = threshold3;
    this.threshold4 = threshold4;
    this.threshold5 = threshold5;
    this.speed = 0;
    this.gear = 0;
  }
  
  /**
   * Public static method to access the instance of an AutomaticTransmission Object.
   * @param threshold1 represents the threshold that causes the transmission 
   *     to change from gear 1 to gear 2.
   * @param threshold2 represents the threshold that causes the transmission 
   *     to change from gear 2 to gear 3
   * @param threshold3 represents the threshold that causes the transmission 
   *     to change from gear 3 to gear 4
   * @param threshold4 represents the threshold that causes the transmission 
   *     to change from gear 4 to gear 5
   * @param threshold5 represents the threshold that causes the transmission 
   *     to change from gear 5 to gear 6
   *      
   *@return returns an instance of the AutomaticTransmission Object.
   */
  public static AutomaticTransmission getInstance(int threshold1, int threshold2, 
                                                   int threshold3, int threshold4, int threshold5) {
    instance = new AutomaticTransmission(threshold1, threshold2, 
       threshold3, threshold4, threshold5);
    return instance;
  }

  /*
 * Abstracting speed validation
 */
  private void speedValidation(int threshold1, int threshold2, int threshold3, 
      int threshold4, int threshold5) {
    if (threshold1 <= 0 || threshold2 < 0 || threshold3 < 0 || threshold4 < 0 
        || threshold5 < 0) {
      throw new IllegalArgumentException("Speed thresholds cannot be negative");
    }
    if (threshold5 <= threshold4 || threshold4 <= threshold3 
        ||  threshold3 <= threshold2 || threshold2 <= threshold1) {
      throw new IllegalArgumentException("Speed thresholds must be in monotonically "
    + "increasing order with speed1 being the lowest and speed5 "
    + "being the highest. Two thresholds cannot be the same.");
    }
  }

  @Override
    public void increaseSpeed() {
    this.speed += 1;
    adjustGear();
  }

  @Override
    public void decreaseSpeed() throws IllegalStateException {
    if (this.speed <= 0) {
      throw new IllegalStateException("The speed cannot go below 0.");
    }
    this.speed -= 1;
    adjustGear();
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getGear() {
    return this.gear;
  }
  
  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.getSpeed(), this.getGear());
  }
  
  @Override
  public boolean equals(Object o) {
    // Fast pointer for equality
    if (this == o) {
      return true;
    }
    // If o isn't the right class than it cannot be equal
    if (!(o instanceof AutomaticTransmission)) {
      return false;
    }
    AutomaticTransmission that = (AutomaticTransmission) o;
    if (this.threshold1 == that.threshold1 
            && this.threshold2 == that.threshold2
            && this.threshold3 == that.threshold3
            && this.threshold4 == that.threshold4
            && this.threshold5 == that.threshold5) {
      return true;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(threshold1, threshold2, threshold3, threshold4, threshold5);
  }
  
  /**
   * Function used to update gear based on current speed.
   */
  private void adjustGear() {
    if (this.speed == 0) {
      this.gear = 0;
    } else if (this.speed < threshold1) {
      this.gear = 1;
    } else if (this.speed < threshold2) {
      this.gear = 2;
    } else if (this.speed < threshold3) {
      this.gear = 3;
    } else if (this.speed < threshold4) {
      this.gear = 4;
    } else if (this.speed < threshold5) {
      this.gear = 5;
    } else {
      gear = 6;
    }
  }
}
