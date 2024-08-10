import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PhaseCalculator {
   public static String currentPhase(double elapsedDays) {
      double A = 0.0D;
      double B = 29.53D;
      double C = elapsedDays - 5.0D;
      double phase = C % B / B;
      DecimalFormat df = new DecimalFormat("0.00");
      df.setRoundingMode(RoundingMode.DOWN);
      String returnValue = String.valueOf(df.format(phase));
      return returnValue;
   }
}
