package test;
public class SplitTest {
    public static void main(String[] args) {
        final String splitMe="123,test,444,\"don't split, this\",more test,1";
        final String[] splitByQuote=splitMe.split("\"");
        final String[][] splitByComma=new String[splitByQuote.length][];
        for(int i=0;i<splitByQuote.length;i++) {
            String part=splitByQuote[i];
            if (i % 2 == 0){
               splitByComma[i]=part.split(",");
            }else{
                splitByComma[i]=new String[1];
                splitByComma[i][0]=part;
            }
        }
        for (String parts[] : splitByComma) {
            for (String part : parts) {
                System.out.println(part);
            }
        }
    }
}