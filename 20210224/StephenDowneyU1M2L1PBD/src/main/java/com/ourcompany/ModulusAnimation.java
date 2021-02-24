package com.ourcompany;

public class ModulusAnimation {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 80; i++ )
        {
            if (i % 10 == 0 ){
                System.out.println("");
                System.out.println("*****");
            }else if (i%10 ==1){
                System.out.println("      *");
                System.out.println(" *****");
            }else if (i%10 ==2){
                System.out.println("      *");
                System.out.println("  **** *");
            }else if (i%10 ==3){
                System.out.println("      *");
                System.out.println("   *** **");
            }else if (i%10 ==4){
                System.out.println("      *");
                System.out.println("    ** ***");
            }else if (i%10 ==5){
                System.out.println("      *");
                System.out.println("     * ****");
            }else if (i%10 ==6){
                System.out.println("      *");
                System.out.println("       *****");
            }

            Thread.sleep(200);
        }
    }
}
