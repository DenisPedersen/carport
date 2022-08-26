package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class FrameCalculator {
    GeneralCalculator generalCalculator = new GeneralCalculator();

    public ArrayList<Bomline> calculateFrontUnderStern(ArrayList<Material> underSternArrayList, int rafterLength) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material frontUnderStern = generalCalculator.calculateMaterialLength(rafterLength, underSternArrayList);
        if (frontUnderStern == null) {
            ArrayList<Material> frontUnderSternArrayList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(rafterLength + 5, underSternArrayList);


            if (frontUnderSternArrayList.size() == 1) {
                Bomline bomline = new Bomline(1, frontUnderSternArrayList.get(0), 4);
                bomlineArrayList.add(bomline);
                return bomlineArrayList;
            }
            for (Material material : frontUnderSternArrayList) {


                Bomline bomline = new Bomline(1, material, 2);
                bomlineArrayList.add(bomline);
            }
        } else {
            Bomline bomline = new Bomline(1, frontUnderStern, 2);

            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }


    public ArrayList<Bomline> calculateFrontOverStern(ArrayList<Material> overSternArrayList, int rafterLength) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material frontOverStern = generalCalculator.calculateMaterialLength(rafterLength, overSternArrayList);
        if (frontOverStern == null) {
            ArrayList<Material> frontOverSternArrayList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(rafterLength + 5, overSternArrayList);

            if (overSternArrayList.size() == 1) {
                Bomline bomline = new Bomline(1, frontOverSternArrayList.get(0), 2);
                bomlineArrayList.add(bomline);
                return bomlineArrayList;
            }
            for (Material material : frontOverSternArrayList) {

                Bomline bomline = new Bomline(1, material, 2);
                bomlineArrayList.add(bomline);
            }
        } else {
            Bomline bomline = new Bomline(1, frontOverStern, 2);
            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }

    public ArrayList<Bomline> calculateSideOverStern(ArrayList<Material> sternArrayList, int carportLength) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material sideStern = generalCalculator.calculateMaterialLength(carportLength, sternArrayList);
        if (sideStern == null) {
            ArrayList<Material> sideSternArrayList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(carportLength, sternArrayList);
            for (Material material : sideSternArrayList) {
                Bomline bomline = new Bomline(4, material, 2);
                bomlineArrayList.add(bomline);
            }
        } else {
            Bomline bomline = new Bomline(4, sideStern, 2);
            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }


    public ArrayList<Bomline> calculateSideUnderStern(ArrayList<Material> sternArrayList, int carportLength) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material sideStern = generalCalculator.calculateMaterialLength(carportLength, sternArrayList);
        if (sideStern == null) {
            ArrayList<Material> sideSternArrayList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(carportLength, sternArrayList);
            for (Material material : sideSternArrayList) {
                Bomline bomline = new Bomline(2, material, 2);
                bomlineArrayList.add(bomline);
            }
        } else {
            Bomline bomline = new Bomline(2, sideStern, 2);
            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }

    public ArrayList<Bomline> calculateWeatherBoardForSide(ArrayList<Material> weatherBoardArrayList, int carportLength) {

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material sideWeatherBoard = generalCalculator.calculateMaterialLength(carportLength, weatherBoardArrayList);
        if (sideWeatherBoard == null) {

            ArrayList<Material> sideWeatherBoardList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(carportLength, weatherBoardArrayList);

            for (Material material : sideWeatherBoardList) {

                Bomline bomline = new Bomline(13, material, 2);
                bomlineArrayList.add(bomline);

            }
        } else {
            Bomline bomline = new Bomline(13, sideWeatherBoard, 2);
            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }

    public ArrayList<Bomline> calculateWeatherBoardForFrontAndBack(ArrayList<Material> weatherBoardArrayList, int rafterLength) {

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material frontAndSideWeatherBoard = generalCalculator.calculateMaterialLength(rafterLength + 5, weatherBoardArrayList);
        if (frontAndSideWeatherBoard == null) {

            ArrayList<Material> frontAndBackWeatherBoardList = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(rafterLength, weatherBoardArrayList);

            for (Material material : frontAndBackWeatherBoardList) {

                Bomline bomline = new Bomline(14, material, 2);
                bomlineArrayList.add(bomline);

            }
        } else {
            Bomline bomline = new Bomline(14, frontAndSideWeatherBoard, 2);
            bomlineArrayList.add(bomline);
        }
        return bomlineArrayList;
    }

    public float calculateRaftersDistance(float carportLength, int rafters) {

        float newDistance = 0;
        float rafterWidth = 4.5f;

        newDistance = carportLength / rafters;

        return newDistance + rafterWidth;
    }

    public int calculateRafters(int carportLength) {

        int distance = 59;
        int rafters = 0;

        rafters = carportLength / distance + 2;

        return rafters;
    }

    public Bomline rafters(Material material, int carportLength) {
        int rafter = calculateRafters(carportLength);

        Bomline bomline = new Bomline(10, material, rafter);
        return bomline;
    }

    public ArrayList<Bomline> calculateHead(int carportLength, ArrayList<Material> headArrayList, int shedLength) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        Material head = generalCalculator.calculateMaterialLength(carportLength - shedLength, headArrayList);

        Material headForShed = null;

        for (Material m : headArrayList) {
            if (m.getLength() > shedLength) {
                headForShed = m;
                break;
            }
        }
        if (headForShed == null) {
            throw new ArithmeticException("Kunne ikke finde en rem som passer til dit skur");
        }

        bomlineArrayList.add(new Bomline(8, head, 2));
        bomlineArrayList.add(new Bomline(9, headForShed, 1));


        return bomlineArrayList;
    }

    public ArrayList<Bomline> calculateNoggingAndBracket(int shedLength, ArrayList<Material> noggingArrayList) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();

        Material noggingTheShortOne = generalCalculator.calculateMaterialLength(shedLength, noggingArrayList);

        if (noggingTheShortOne == null) {
            ArrayList<Material> shortNoggingArraylist = generalCalculator.calculateMaterialIfMoreThanOneIsNeeded(shedLength, noggingArrayList);
            for (Material material : shortNoggingArraylist) {
                bomlineArrayList.add(new Bomline(6, material, 4));
            }
        } else {
            bomlineArrayList.add(new Bomline(6, noggingTheShortOne, 4));
        }

        Material noggingTheLongOne = generalCalculator.calculateMaterialLength(250, noggingArrayList);

        bomlineArrayList.add(new Bomline(7, noggingTheLongOne, 12));

        return bomlineArrayList;
    }

}
