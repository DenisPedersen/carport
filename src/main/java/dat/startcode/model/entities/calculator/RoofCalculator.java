package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class RoofCalculator {

    GeneralCalculator generalCalculator = new GeneralCalculator();
    BoxCalculator boxCalculator = new BoxCalculator();

    public ArrayList<Bomline> calculateRoofPlates(int carportLength, int carportWidth, ArrayList<Material> listOfRoofPlates) {

        ArrayList<Bomline> bomLineArrayList = new ArrayList<Bomline>();
        Material primaryRoofPlate = generalCalculator.calculateMaterialLength(carportLength, listOfRoofPlates);

        if (primaryRoofPlate != null) {
            int amount = calculateAmountOfRoofPlatesForWidth(primaryRoofPlate, carportWidth);
            bomLineArrayList.add(new Bomline(15, primaryRoofPlate, amount));
        } else {
            primaryRoofPlate = listOfRoofPlates.get(0);
            Material secondaryRoofPlate = null;
            for (Material m : listOfRoofPlates) {
                if (primaryRoofPlate.getLength() + m.getLength() > carportLength) {
                    secondaryRoofPlate = m;
                    break;
                }
            }
            if (secondaryRoofPlate == null) {
                throw new ArithmeticException("Kunne ikke lave en lang nok tagplade");
            }
            int amount = calculateAmountOfRoofPlatesForWidth(primaryRoofPlate, carportWidth);
            bomLineArrayList.add(new Bomline(15, primaryRoofPlate, amount));
            bomLineArrayList.add(new Bomline(15, secondaryRoofPlate, amount));

        }

        if (carportLength > primaryRoofPlate.getLength()) {
            for (Material m : listOfRoofPlates) {
                if (primaryRoofPlate.getLength() + m.getLength() > carportLength) {

                }
            }
        }
        return bomLineArrayList;
    }


    public int calculateAmountOfRoofPlatesForWidth(Material material, int carportWidth) {

        if (material.getWidth() > carportWidth) {
            return 1;
        } else {
            int amount = 1;
            while (material.getWidth() * amount < carportWidth) {
                amount++;
            }
            return amount;
        }
    }

    public int calculateBottomScrewForRoof(int carportWidth, int carportLength) {

        int quantity = ((carportWidth / 100) * (carportLength / 100)) * 13;


        return quantity;
    }

    public Bomline calculateAmountOfBoxesOfBottomScrews(Material material, int carportWidth, int carportLength) {

        int screwsNeeded = calculateBottomScrewForRoof(carportWidth, carportLength);
        int boxesNeeded = boxCalculator.calculateQuantityOfBoxes(screwsNeeded, material);

        Bomline bomline = new Bomline(16, material, boxesNeeded);
        return bomline;
    }

}
