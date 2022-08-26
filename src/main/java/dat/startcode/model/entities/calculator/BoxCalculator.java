package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Material;

public class BoxCalculator {

    public BoxCalculator() {
    }

    public int calculateQuantityOfBoxes(int amountNeeded, Material material) {

        int amount = 1;

        while (amountNeeded > material.getQuantity() * amount) {

            amount++;
        }
        return amount;
    }
}
