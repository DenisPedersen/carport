package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class GeneralCalculator {

    public int calculateXDistance(int carportLength) {

        int minDistanceFromStern = 45;
        int distanceBetweenPost = carportLength - minDistanceFromStern * 2;
        int post1 = minDistanceFromStern;
        if (distanceBetweenPost < 310) {
            return distanceBetweenPost;
        } else if (distanceBetweenPost / 2 < 310) {
            return distanceBetweenPost / 2;

        } else if (distanceBetweenPost / 3 < 310) {
            return distanceBetweenPost / 3;
        }
        return distanceBetweenPost;
    }

    public int calculateXDistanceWithShed(int carportLength, int shedLength) {

        int minDistanceFromStern = 45;
        int distanceBetweenShedPostAndPost = (carportLength - minDistanceFromStern) - shedLength;

        if (distanceBetweenShedPostAndPost > 310) {
            return distanceBetweenShedPostAndPost / 2;
        }
        return -1;
    }

    public Material calculateMaterialLength(int dimension, ArrayList<Material> listOfMaterials) {

        for (Material material : listOfMaterials) {
            if (material.getLength() > dimension) {
                return material;
            }
        }
        return null;
    }

    public ArrayList<Material> calculateMaterialIfMoreThanOneIsNeeded(int dimension, ArrayList<Material> listOfMaterials) {

        ArrayList<Material> materialArrayList = new ArrayList<>();
        Material material1 = null;
        Material material2 = null;

        for (int i = 0; i < listOfMaterials.size(); i++) {
            material1 = listOfMaterials.get(i);
            for (int j = 0; j < listOfMaterials.size(); j++) {
                material2 = listOfMaterials.get(j);
                if (material1.getLength() + material2.getLength() > dimension) {
                    break;
                }
            }
            if (material1.getLength() + material2.getLength() > dimension) {
                break;
            }
        }
        if (material1 == material2) {
            materialArrayList.add(material1);
            return materialArrayList;
        }
        materialArrayList.add(material1);
        materialArrayList.add(material2);
        return materialArrayList;
    }


}
