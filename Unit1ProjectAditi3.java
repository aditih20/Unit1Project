
import java.util.Scanner;
public class Unit1ProjectAditi3 {
    public static void main(String[] args) {
     /* asking the user for mass of water, starting temperature, and ending temperature */   
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the mass of the water in grams.");
        double mass = myObj.nextDouble();
        System.out.println("Enter the starting temperature of the water in degrees Celsius.");
        double initialTemp = myObj.nextDouble();
    /*enforcing minimum temperature of -273.14C*/
        if (initialTemp < -273.14) {
            initialTemp = -273.14;
        }
        System.out.println("Enter the ending temperature of the water in degrees Celsius.");
        double finalTemp = myObj.nextDouble();
    /*enforcing minimum temperature of -273.14C*/
        if (finalTemp < -273.14) {
            finalTemp = -273.14;
        }
    /* printing the values of mass, StartTemp, EndTemp, Starting Phase */
        System.out.println("Mass (in g): " + mass);
        System.out.println("Starting temperature in degrees Celsius: " + initialTemp);
        System.out.println("Ending temperature in degrees Celsius: " + finalTemp);
    /*identifying starting and ending phases of the water and printing them*/
        String initialPhase = "liquid";   
        if (initialTemp > 100) {
            initialPhase = ("vapor");
            System.out.println("Starting phase: vapor");
        } else { if (initialTemp < 0) {
            initialPhase = ("ice");
            System.out.println("Starting phase: ice");
        } else {
            System.out.println("Starting phase: liquid");
        }
        }
        String finalPhase = ("liquid");
        if (finalTemp > 100) {
            finalPhase = ("vapor");
            System.out.println("Ending phase: vapor");
        } else { if (finalTemp < 0) {
            finalPhase = ("ice");
            System.out.println("Ending phase: ice");
        } else {
            System.out.println("Ending phase: liquid");
        }
        }
        /*checking if endothermic*/
        boolean endothermic = false;
        if(finalTemp > initialTemp)
            endothermic = true;
        double heatEnergy = 0;
        
        /*initialPhase = solid*/
        if(initialPhase.equals("solid")) {
            heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, finalPhase, endothermic);
            if(!finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeLiquid(mass, 0, finalTemp, finalPhase, endothermic);
            }
            if(finalPhase.equals("vapor")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalPhase, endothermic);
            }
        }
        /*initialPhase = liquid*/
        if(initialPhase.equals("liquid")) {
            heatEnergy += tempChangeLiquid(mass, initialTemp, finalTemp, finalPhase, endothermic);
            if(finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
            }
            if(finalPhase.equals("vapor")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalPhase, endothermic);
            }
        }
        /*initialPhase = vapor*/
        if(initialPhase.equals("vapor")) {
            heatEnergy += tempChangeVapor(mass, initialTemp, finalTemp, finalPhase, endothermic);
            if(!finalPhase.equals("vapor")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeLiquid(mass, 100, finalTemp, finalPhase, endothermic);
            }
            if(finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
            }
        }
        System.out.println("Total Heat Energy: " + round(heatEnergy) + "kJ");
    }
/*temperature change solid*/
    public static double tempChangeSolid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(!endPhase.equals("solid"))
            endTemp = 0;
            double energyChange = round(mass*0.002108*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (solid): " + energyChange + "kJ");
        else
            System.out.println("Cooling (solid): " + energyChange + "kJ");
        return energyChange;
    }
       /*temperature change vapor*/ 
    public static double tempChangeVapor(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(!endPhase.equals("vapor"))
            endTemp = 100;
            double energyChange = round(mass*0.001996*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (vapor): " + energyChange + "kJ");
        else
            System.out.println("Cooling (vapor): " + energyChange + "kJ");
        return energyChange;
    }
    /*temperature change liquid*/
    public static double tempChangeLiquid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(endPhase.equals("solid"))
            endTemp = 0;
        if(endPhase.equals("vapor"))
            endTemp = 100;
            double energyChange = round(mass*0.004184*(endTemp - startTemp));
        if(endothermic)
            System.out.println("Heating (liquid): " + energyChange + "kJ");
        else
            System.out.println("Cooling (liquid): " + energyChange + "kJ");
        return energyChange;
    }
    /*fusion*/
    public static double fusion(double mass, boolean endothermic) {
        double energyChange;
        if(endothermic) {
            energyChange = round(0.333*mass);
            System.out.println("Melting: " + energyChange + "kJ");
        }
        else {
            energyChange = round(-0.333*mass);
            System.out.println("Freezing: " + energyChange + "kJ");
        }
            return energyChange;
    }
    /*vaporization*/
    public static double vaporization(double mass, boolean endothermic) {
        double energyChange;
        if(endothermic) {
            energyChange = round(2.257*mass);
            System.out.println("Evaporation: " + energyChange + "kJ");
        }
        else {
            energyChange = round(-2.257*mass);
            System.out.println("Condensation: " + energyChange + "kJ");
        }
        return energyChange;
    }
    /*rounding*/
    public static double round(double x) {
        x *= 1000;
        if(x > 0)
            return (int)(x + 0.5)/1000.0;
        else
            return (int)(x - 0.5)/1000.0;  
    }
}
