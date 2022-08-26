package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class BoltAndBracketCalculator {

    BoxCalculator boxCalculator = new BoxCalculator();


    public int calculatePerforatedTapeLength(int carportLength) {

        int perforatedTapeLength = 0;
        int maxRafterDistance = 59;

        perforatedTapeLength = carportLength - maxRafterDistance;
        return perforatedTapeLength;

    }

    public int calculatePerforatedtapeLengthWithShed(int carportLength, int shedLength) {

        int perforatedTapeLength = 0;
        int maxRafterDistance = 59;

        perforatedTapeLength = carportLength - shedLength;
        perforatedTapeLength = perforatedTapeLength - maxRafterDistance;
        return perforatedTapeLength;

    }

    public int calculateCarriageBolt(int postAmount) {

        int carriageBolt = 2;
        int carriageboltToHead = 2;

        carriageBolt = carriageBolt * postAmount;

        return carriageBolt + carriageboltToHead;
    }


    public int calculateCarriageBoltWithShed(int postAmountWithShed) {
        int carriageBolt = 2;
        int carriageboltToHead = 2;

        postAmountWithShed -= 3;

        carriageBolt = carriageBolt * postAmountWithShed;

        return carriageBolt + carriageboltToHead;

    }

    public int calculateSquareSpacer(int carriageBolt) {

        int squareSpacer;

        squareSpacer = carriageBolt;

        return squareSpacer;
    }

    public int calculateSteelBracketRight(int rafters) {

        int steelBracket = 0;

        steelBracket = rafters;

        return steelBracket;
    }

    public int calculateSteelBracketLeft(int rafters) {

        int steelBracket = 0;

        steelBracket = rafters;

        return steelBracket;
    }

    public int calculateScrewForBracket(int bracket) {

        int screws = 0;

        screws = (bracket * 2) * 9;

        return screws;
    }

    public int calculateScrewForPerforatedTape(int rafters) {

        int screws = 0;
        int perforatedTape = 2;

        screws = (rafters - 2) * perforatedTape;
        screws = screws * 2;

        return screws;

    }

    public Bomline carriageBolt(Material material, int postAmount) {
        int bolt = calculateCarriageBolt(postAmount);

        Bomline bomline = new Bomline(21, material, bolt);
        return bomline;
    }

    public Bomline carriageBoltWithShed(Material material, int postAmount) {
        int bolt = calculateCarriageBoltWithShed(postAmount);

        Bomline bomline = new Bomline(21, material, bolt);
        return bomline;
    }

    public Bomline squareSpacer(Material material, int carriageBolt) {
        int spacer = calculateSquareSpacer(carriageBolt);

        Bomline bomline = new Bomline(21, material, spacer);
        return bomline;
    }

    public Bomline steelBracketRight(Material material, int rafters) {
        int steelBrackets = calculateSteelBracketRight(rafters);

        Bomline bomline = new Bomline(18, material, steelBrackets);
        return bomline;
    }

    public Bomline steelBracketLeft(Material material, int rafters) {
        int steelBrackets = calculateSteelBracketLeft(rafters);

        Bomline bomline = new Bomline(18, material, steelBrackets);
        return bomline;
    }

    public Bomline screwsForTapeAndBracket(Material material, int bracket, int rafters) {
        int screwsForBrackets = calculateScrewForBracket(bracket);
        int screwsForTape = calculateScrewForPerforatedTape(rafters);
        int screwBoxes = boxCalculator.calculateQuantityOfBoxes(screwsForTape + screwsForBrackets, material);

        Bomline bomline = new Bomline(20, material, screwBoxes);
        return bomline;
    }

    public ArrayList<Bomline> addWoodForZPlusHandleAndHingeForDoor(Material zForDoor, Material doorHandle, Material tHinge) {

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();

        Bomline bomline1 = new Bomline(5, zForDoor, 1);
        Bomline bomline2 = new Bomline(24, doorHandle, 1);
        Bomline bomline3 = new Bomline(25, tHinge, 2);

        bomlineArrayList.add(bomline1);
        bomlineArrayList.add(bomline2);
        bomlineArrayList.add(bomline3);

        return bomlineArrayList;

    }

    public Bomline screwsForSternAndWeatherBoard(Material material) {
        return new Bomline(18, material, 1);
    }

    public Bomline perforatedTape(Material material) {
        return new Bomline(17, material, 2);
    }

    public Bomline calculateAngleBracket(int noggingAmount, Material material) {

        Bomline bomline = new Bomline(26, material, noggingAmount * 2);
        return bomline;
    }


}
