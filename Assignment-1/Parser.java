import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    static ArrayList<Token> list;
    static ArrayList<String> tokens;
    static String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static ArrayList<String> keywords;
    static ArrayList<String> operators,punctuator;
    public static void tokenize(String s,int row)
    {
        if (s.charAt(0)=='#')
        {
            return;
        }
        ArrayList<Token> line=new ArrayList<>();
        for (int i=0;i<s.length();)
        {
            char c=s.charAt(i);
            if (c=='\"'||c=='\'')
            {
                list.add(new Token(c+"", "Punctuation", row + 1, i + 1));
                line.add(new Token(c+"", "Punctuation", row + 1, i + 1));
                i++;
                int pos=s.indexOf(c,i);
                if (pos<0)
                {
                    continue;
                }
                else
                {
                    String a=s.substring(i,pos);
                    list.add(new Token(a, "Literal", row + 1, i + 1));
                    line.add(new Token(a, "Literal", row + 1, i + 1));
                    list.add(new Token(c+"", "Punctuation", row + 1, pos + 1));
                    line.add(new Token(c+"", "Punctuation", row + 1, pos + 1));
                    i=pos+1;
                    continue;
                }
            }

            boolean flag=false;
            for (String t:tokens)
            {
                if (c==t.charAt(0))
                {
                    try{
                        String a=s.substring(i,i+t.length());
                        if (a.equals(t))
                        {
                            if(keywords.contains(t)&&((i>0&& alphabet.contains(""+s.charAt(i-1)))||(i+t.length()<s.length()&& alphabet.contains(""+s.charAt(i+t.length())))))
                                continue;
                            if (keywords.contains(t)) {
                                list.add(new Token(a, "Keyword", row + 1, i + 1));
                                line.add(new Token(a, "Keyword", row + 1, i + 1));
                            }
                            if (punctuator.contains(t)) {
                                list.add(new Token(a, "Punctuation", row + 1, i + 1));
                                line.add(new Token(a, "Punctuation", row + 1, i + 1));
                            }
                            if (operators.contains(t)) {
                                list.add(new Token(a, "Operator", row + 1, i + 1));
                                line.add(new Token(a, "Operator", row + 1, i + 1));
                            }
                            i=i+a.length();
                            flag=true;
                            break;
                        }
                    }
                    catch (Exception e)
                    {
                        //System.out.println(e.getMessage());
                    }
                }
            }
            if (!flag)
            {
                i=i+1;
            }
        }
        int start=0,end=0;
        for (Token t:line)
        {
            end=t.column-1;
            String ss=s.substring(start,end);
            if (ss.length()>0)
            {
                String string[]=ss.split(" ");
                int ii=start;
                for(String s1:string)
                {
                    if (s1.length()>0&&!s1.contains("\t\n "))
                    {
                        if(isNumber(s1))
                            list.add(new Token(s1,"Constant",row+1,ii+1));
                        else
                            list.add(new Token(s1,"Identifier",row+1,ii+1));
                    }
                    ii+=s1.length();
                }
            }
            start=end+t.token.length();
        }
        String ss=s.substring(start);
        if (ss.length()>0)
        {
            String[] string=ss.split(" ");
            int ii=start;
            for(String s1:string)
            {
                if (s1.length()>0&&!s1.contains("\t\n "))
                {
                    if(isNumber(s1))
                        list.add(new Token(s1,"Constant",row+1,ii+1));
                    else
                        list.add(new Token(s1,"Identifier",row+1,ii+1));
                }
                ii+=s1.length();
            }
        }

    }
    public static boolean isNumber(String s)
    {
        try
        {
            int num=Integer.parseInt(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static void main(String[] args)
    {
        tokens=new ArrayList<>();
        list=new ArrayList<>();
        keywords=new ArrayList<>();
        punctuator=new ArrayList<>();
        operators=new ArrayList<>();
        try {
        Scanner sc = new Scanner(new File("/home/obs/Documents/compiler-lab-duvam/src/code"));
        Scanner scanner=new Scanner(new File("/home/obs/Documents/compiler-lab-duvam/src/keywords"));
        while(scanner.hasNextLine())
        {
            String s=scanner.next();
            tokens.add(s);
            keywords.add(s);
        }
        scanner=new Scanner(new File("/home/obs/Documents/compiler-lab-duvam/src/punctuation"));
        while(scanner.hasNextLine())
        {
                String s=scanner.next();
                tokens.add(s);
                punctuator.add(s);
        }
            scanner=new Scanner(new File("/home/obs/Documents/compiler-lab-duvam/src/operators"));
            while(scanner.hasNextLine())
            {
                String s=scanner.next();
                tokens.add(s);
                operators.add(s);
            }
        int i=0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            tokenize(s,i);
            i++;
        }
            String format = "%15s%20s%10s%10s%n";
            System.out.printf(format,"Token","Type","Row","Column");
            for (Token t:list)
            {
                t.display();
            }


    }
    catch (Exception e)
    {}
    }
}

