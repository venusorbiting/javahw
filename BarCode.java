/*  Alicia Curtis
    CS E-10b Spring 2018
    PS 5B
    Problem #4
    Program that converts 5-digit zip codes to 32-char bar codes, and vice versa, as long as the input is valid.
*/

public class BarCode
{
    protected String myZipCode;
    protected String myBarCode;
    
    public BarCode (String code)
    {
        // If the length is 5, attempts to convert zip -> bar, throws error if unsuccessful.
        if (code.length() == 5) {
            this.myZipCode = code;
            this.myBarCode = encode(code);
            if (myBarCode == "") throw new IllegalArgumentException("Illegal zip code values: " + code);
        }

        // If the length is 32, attempts to convert bar -> zip, throws error if unsuccessful.
        else if (code.length() == 32)
        {
            this.myBarCode = code;
            this.myZipCode = decode(code);
            if (myZipCode == "") throw new IllegalArgumentException("Illegal bar code values: " + code);
        }
        // Throws error if the length is neither 5 nor 32.
        else throw new IllegalArgumentException("Illegal bar code values: " + code);
    }
    
    public String getZipCode()
    {
        return this.myZipCode;
    }
    
    public String getBarCode()
    {
        return this.myBarCode;
    }
    
    private String digitToCode(char digit)
    {
        switch (digit)
        {
            case '1': return ":::||";
            case '2': return "::|:|";
            case '3': return "::||:";
            case '4': return ":|::|";
            case '5': return ":|:|:";
            case '6': return ":||::";
            case '7': return "|:::|";
            case '8': return "|::|:";
            case '9': return "|:|::";
            case '0': return "||:::";
            default: return "invalid";
        }
    }
    
    private String codeToDigit(String fiveBars)
    {
        switch (fiveBars)
        {
            case ":::||": return "1";
            case "::|:|": return "2";
            case "::||:": return "3";
            case ":|::|": return "4";
            case ":|:|:": return "5";
            case ":||::": return "6";
            case "|:::|": return "7";
            case "|::|:": return "8";
            case "|:|::": return "9";
            case "||:::": return "0";
            default: return "invalid";
        }
    }
    
    private boolean isValidBarCode(String bar)
    {
        /* This method checks for invalid input every step of the way, and returns false (and thereby doesn't waste time checking
        the rest of the input) if anything invalid is found. */
        
        if (bar.charAt(0) != '|') return false;
        
        StringBuilder checkIt = new StringBuilder();
        
        /* Though this method will not return the zip code itself, it still has to build it in order to check whether the check
        sum portion of the bar code is valid, i.e. whether it is the correct check sum value based on the other digits. */
        
        for (int j = 1; j < 25; j += 5)
        {
            // Returns false and breaks the loop if the attempt to convert the bars to a digit is unsuccessful.
            String onePart = bar.substring(j, j + 5);

            if (codeToDigit(onePart) == "invalid") {
                return false;
            }
            else {
                checkIt.append(codeToDigit(onePart));
            }
        }
        
        /* Creates variables for the digit in the check sum position in the bar code, and for the *expected* check sum digit based
        on the rest of the values in the bar code. Returns false if their values are not equal to each other. */
        
        String check2 = codeToDigit(bar.substring(26, 31));
        String check3 = Integer.toString(getCheckDigit(checkIt.toString()));
        
        if (!check2.equals(check3)) return false;
        
        if (bar.charAt(31) != '|') return false;
        
        return true;
    }
    
    private boolean isValidZipCode(String zip)
    {
        boolean check = true;
        
        // Checks each character in the zip code string to make sure it is a digit.
        for (int i = 0; i < 5; i++) {
            if (!(zip.charAt(i) >= '0' && zip.charAt(i) <= '9'))
            {
                check = false;
                break;
            }
        }
        return check;
    }
    
    private int getCheckDigit(String code)
    {
        int sum = 0;
        
        // Creates a sum of all the values in the code, and returns the appropriate check digit based on that sum.
        for (int i = 0; i < 5; i++) {
            sum += code.charAt(i) - '0';
        }
        if (sum % 10 == 0) return 0;    // returns 0 in the case that the sum is already an even multiple of 10
        else return 10 - (sum % 10);
    }
    
    private String encode(String zipCode)
    {
        if (!isValidZipCode(zipCode)) return "";
        
        StringBuilder bar = new StringBuilder("|");
        
        for (int i = 0; i < 5; i++) {
            bar.append(digitToCode(zipCode.charAt(i)));
        }
        
        // Gets the appropriate check digit, converts it to a char, appends it to the code.
        int check = getCheckDigit(zipCode);
        char check2 = (char)(check + '0');
        bar.append(digitToCode(check2));

        bar.append("|");
        
        return bar.toString();
    }
    
    private String decode(String barCode)
    {
        if (!isValidBarCode(barCode)) return "";
        
        StringBuilder zip = new StringBuilder();
        
        // Converts the five sections of the bar code corresponding to zip code digits, ignores check sum & start/end bars.
        for (int i = 1; i < 25; i += 5) {
            zip.append(codeToDigit(barCode.substring(i, i + 5)));
        }
        
        return zip.toString();
    }
}