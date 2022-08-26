package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class ShedCalculator {

    BoxCalculator boxCalculator = new BoxCalculator();

    public ShedCalculator() {
    }

    public ArrayList<Bomline> shedPlanksAndScrews(int shedWidth, int shedLength, Material planks, Material shortScrews, Material longScrews) {

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();

        int amountOfPlanks = calculateShed2on1(shedLength, shedWidth, planks);
        int shortScrewsNeeded = calculateShortScrewsForShed(amountOfPlanks / 2);
        int longScrewsNeeded = calculateLongScrewsForShed(amountOfPlanks / 2);

        int shortScrewBoxes = boxCalculator.calculateQuantityOfBoxes(shortScrewsNeeded, shortScrews);
        int longScrewBoxes = boxCalculator.calculateQuantityOfBoxes(longScrewsNeeded, longScrews);


        bomlineArrayList.add(new Bomline(12, planks, amountOfPlanks));
        bomlineArrayList.add(new Bomline(22, longScrews, longScrewBoxes));
        bomlineArrayList.add(new Bomline(23, shortScrews, shortScrewBoxes));

        return bomlineArrayList;
    }


    public int calculateShortScrewsForShed(int amountOfPlanks) {
        int screwsNeeded = amountOfPlanks * 4;

        return screwsNeeded;
    }

    public int calculateLongScrewsForShed(int amountOfPlanks) {
        int screwsNeeded = amountOfPlanks * 8;
        return screwsNeeded;
    }

    public int calculateShed2on1(float shedLength, float shedWidth, Material material) {

        int plankOverlap = (2 * material.getHeight()) - (2 * 15);

        int planksNeededForSide = calculateShedPlanksNeededForSide(shedLength, plankOverlap, material);
        int planksNeededForFrontAndBack = calculateShedPlanksNeededForFrontAndBack(shedWidth, plankOverlap, material);

        int totalPlanksNeeded = planksNeededForSide + planksNeededForFrontAndBack;

        return totalPlanksNeeded;
    }

    public int calculateShedPlanksNeededForSide(float shedLength, int plankOverlap, Material material) {
        float overlapsForSide = ((shedLength * 10 - material.getHeight()) / plankOverlap) * 2;
        int planksNeededForSide = (int) Math.ceil(overlapsForSide * 2);

        return planksNeededForSide;
    }

    public int calculateShedPlanksNeededForFrontAndBack(float shedWidth, int plankOverlap, Material material) {
        float overlapsForFrontAndBack = ((shedWidth * 10 - material.getHeight()) / plankOverlap) * 2;
        int planksNeededForFrontAndBack = (int) Math.ceil(overlapsForFrontAndBack * 2);

        return planksNeededForFrontAndBack;
    }


}
