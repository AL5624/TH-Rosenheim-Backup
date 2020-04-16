package training.kata.roman;

public class Roman
{
    public static int romanToArabic(String romanNumber)
    {
        char[] romanNumberDigits = romanNumber.toCharArray();
        int arabicNumber = 0;
        for (char digit : romanNumberDigits)
        {
            arabicNumber += getArabicNumber(digit);
        }

        return arabicNumber;
    }

    private static int getArabicNumber(char digit)
    {
        int arabicNumber = 0;
        switch (digit)
        {
            case 'I':
                arabicNumber += 1;
                break;
            case 'V':
                arabicNumber += 5;
                break;
            case 'X':
                arabicNumber += 10;
                break;
            case 'L':
                arabicNumber += 50;
                break;
            case 'C':
                arabicNumber += 100;
                break;
            case 'D':
                arabicNumber += 500;
                break;
            case 'M':
                arabicNumber += 1000;
                break;
            default:
                arabicNumber += 0;
        }
        return arabicNumber;
    }
}
