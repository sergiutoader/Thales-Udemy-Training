package behavioral.state.codingexercise;

class CombinationLock
{
    private int [] combination;
    public String status;

    public CombinationLock(int[] combination)
    {
        this.combination = combination;
        status = "LOCKED";
    }

    public void enterDigit(int digit)
    {
        String stringDigit = String.valueOf(digit);
        if (status.equals("LOCKED")) {
            status = stringDigit;
            return;
        }
        status += stringDigit;

        if (status.length() == combination.length) {
            if (checkValid()) {
               status = "OPEN";
            }
        }
    }

    private boolean checkValid() {

        for(int i = 0; i < status.length(); i++) {
            String digit = status.substring(i, i+1);
            int statusDigit = Integer.parseInt(digit);

            if (statusDigit != combination[i]) {
                status = "ERROR";
                return false;
            }
        }
        return true;
    }
}

public class Demo {
}
