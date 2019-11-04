import java.util.Arrays;

public class StringUtil
{
    String s1;
    String s2;
    String a1[];
    String a2[];

    public static String[] string2StringArray(String s)
    {
        String[] p = s.split(" +");
        Arrays.parallelSort(p);
        return  p;
    }

    public static void main(String[] args) {
       /*
        String s = "bald sind ferien";
        String[] p = string2StringArray(s);
        System.out.println(p[0]);
        System.out.println(p[1]);
        System.out.println(p[2]);

        String c = stringArray2String(p);
        System.out.println(c);
        */
        String string1 = "acbdf03032??=§§+2";
        String string2 = "dcfba**345&";
        boolean a = areAnagrams(string1,string2);
        boolean b = areAnagrams2(string1, string2);
        System.out.println(a);
        System.out.println(b);
    }


    public static String convertArrayToStringMethod(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i] + " ");
        }
        return stringBuilder.toString();
    }

    public static String stringArray2String(String[] arr)
    {
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < arr.length; i++)
        {
            if(i == arr.length - 1)
            {
                a.append(arr[i]);
            }
            else
            {
                a.append(arr[i] + " ");
            }
        }
        return a.toString();
    }

    public static boolean areAnagrams2(String string1, String string2)
    {
        if(string1.equals(string2)) return false;
        string1 = removeJunk(string1);
        string2 = removeJunk(string2);
        string1 = string1.toLowerCase();
        string2 = string2.toLowerCase();
        string1 = sort(string1);
        string2 = sort(string2);
        if (string1.equals(string2)) return true;
        return false;
    }

    public static void test()
    {
        String a = "a$";
        a = removeJunk(a);
        System.out.println(a);
    }

    public static String sort(String string)
    {
        char c[] = string.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public static String removeJunk(String string)
    {
        StringBuffer a = new StringBuffer();
        char d[] = string.toCharArray();
        for(char c: d)
        {
            if(Character.isLetter(c) == true)
            {
                a.append(c);
            }

        }
        /*
        for(int i = 0; i < string.length(); i++)
        {
            if(Character.isLetter(string.charAt(i)) == true)
            {
                a.append(string.charAt(i));
            }
        }*/
        return a.toString();
    }

    public static boolean areAnagrams(String string1, String string2)
    {

        int c1[] = new int[string1.length()];
        char b1[] = new char[string1.length()];
        int c2[] = new int[string1.length()];
        char b2[] = new char[string1.length()];

        if(string1.equals(string2) == true) return false;

        int string1counter = 0;
        int string2counter = 0;

        for(int i = 0; i < string1.length(); i++)
        {
            if(Character.isLetter(string1.charAt(i)) == true)
            {
                ++string1counter;
            }
        }

        for(int i = 0; i < string2.length(); i++)
        {
            if(Character.isLetter(string2.charAt(i)) == true)
            {
                ++string2counter;
            }
        }

        if(string1counter != string2counter)
        {
            return false;
        }

        string1counter = 0;
        string2counter = 0;
        string1 = string1.toLowerCase();
        string2 = string2.toLowerCase();

        for(int i = 0; i < string1.length(); i++)
        {
            if(Character.isLetter(string1.charAt(i)) == true)
            {
                for(int p = 0; p < b1.length; p++)
                {
                    if(b1[p] == string1.charAt(i))
                    {
                        c1[p] += 1;
                        break;
                    }
                    else if (p + 1 == b1.length)
                    {
                        b1[string1counter] = string1.charAt(i);
                        c1[string1counter] += 1;
                        ++string1counter;
                    }
                }
            }
        }

        for(int i = 0; i < string2.length(); i++)
        {
            if(Character.isLetter(string2.charAt(i)) == true)
            {
                for(int p = 0; p < b2.length; p++)
                {
                    if(b2[p] == string2.charAt(i))
                    {
                        c2[p] += 1;
                        break;
                    }
                    else if (p + 1 == b2.length)
                    {
                        b2[string2counter] = string2.charAt(i);
                        c2[string2counter] += 1;
                        ++string2counter;
                    }
                }
            }
        }

        for(int i = 0; i < c1.length; i++)
        {
            for(int p = 0; p < c2.length; p++)
            {
                if(b1[i] == b2[p])
                {
                    if(c1[i] != c2[p])
                    {
                        return false;
                    }
                    else
                    {
                        break;
                    }
                }
                else if(p + 1 == c2.length)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
