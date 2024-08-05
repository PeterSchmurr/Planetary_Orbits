
    public class ZenithLabel extends Planet {
   public String labelText;

   public ZenithLabel(String labelText, double theta, double distance, int period, double xPos, double yPos) {
      this.labelText = labelText;
      this.theta = theta;
      this.distance = distance;
      this.period = period;
      this.xPos = xPos;
      this.yPos = yPos;
      this.delta = 6.28D / this.period;
   }
}
