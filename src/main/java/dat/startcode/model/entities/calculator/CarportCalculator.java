package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

import java.util.ArrayList;

public class CarportCalculator {

    BoltAndBracketCalculator boltAndBracketCalculator = new BoltAndBracketCalculator();
    FrameCalculator frameCalculator = new FrameCalculator();
    PostCalculator postCalculator = new PostCalculator();
    RoofCalculator roofCalculator = new RoofCalculator();
    ShedCalculator shedCalculator = new ShedCalculator();

    private ArrayList<Material> materialArrayList = new ArrayList<>();
    private ArrayList<Material> noggingArrayList = new ArrayList<>();
    private ArrayList<Material> headArrayList = new ArrayList<>();
    private ArrayList<Material> roofPlatesArraylist = new ArrayList<>();
    private ArrayList<Material> overSternArrayList = new ArrayList<>();
    private ArrayList<Material> underSternArrayList = new ArrayList<>();
    private ArrayList<Material> weatherboardArrayList = new ArrayList<>();

    //private HashMap<String, ArrayList<Material>> materialHashMap = new HashMap<>();

    public CarportCalculator(ArrayList<Material> materialArrayList) {
        this.materialArrayList = materialArrayList;
        //this.materialHashMap = materialHashMap;

        //tilføj løsholter til egen arraylist
        noggingArrayList.add(materialArrayList.get(6));
        noggingArrayList.add(materialArrayList.get(5));

        //tilføj rem til egen arraylist
        headArrayList.add(materialArrayList.get(8));
        headArrayList.add(materialArrayList.get(7));

        //tilføj tagplader til egen arraylist
        roofPlatesArraylist.add(materialArrayList.get(13));
        roofPlatesArraylist.add(materialArrayList.get(14));

        //tilføj overstern til egen arraylist
        overSternArrayList.add(materialArrayList.get(2));
        overSternArrayList.add(materialArrayList.get(3));

        //tilføj understern til egen arraylist
        underSternArrayList.add(materialArrayList.get(0));
        underSternArrayList.add(materialArrayList.get(1));


        //tilføj vandbræt til egen arraylist
        weatherboardArrayList.add(materialArrayList.get(11));
        weatherboardArrayList.add(materialArrayList.get(12));

    }

    public CarportCalculator() {
    }

    public ArrayList<Bomline> createCarportWithFullShed(int carportLength, int carportWidth, int shedLength, int shedWidth) {
        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        int rafters = frameCalculator.calculateRafters(carportLength);
        int brackets = boltAndBracketCalculator.calculateSteelBracketLeft(rafters) * 2;


        //tilføj understern til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateFrontUnderStern(underSternArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateSideUnderStern(underSternArrayList, carportLength));

        //tilføj overstern til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateFrontOverStern(overSternArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateSideUnderStern(overSternArrayList, carportLength));

        //tilføj løsholt og vinkelbeslag til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateNoggingAndBracket(shedLength, noggingArrayList));

        //tilføj rem til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateHead(carportLength, headArrayList, shedLength));

        // tilføj spær til arraylist
        bomlineArrayList.add(frameCalculator.rafters(materialArrayList.get(7), carportLength));

        // tilføj stolper til arraylist
        bomlineArrayList.add(postCalculator.postAmountWithShed(carportLength, shedLength, materialArrayList.get(9)));

        //tilføj skurbeklædning og skruer til arraylist
        bomlineArrayList.addAll(shedCalculator.shedPlanksAndScrews(shedWidth, shedLength, materialArrayList.get(10), materialArrayList.get(25), materialArrayList.get(24)));

        // tilføj vandbræt til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateWeatherBoardForFrontAndBack(weatherboardArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateWeatherBoardForSide(weatherboardArrayList, carportLength));

        // tilføj tagplader og skruer til arraylist
        bomlineArrayList.addAll(roofCalculator.calculateRoofPlates(carportLength, carportWidth, roofPlatesArraylist));
        bomlineArrayList.add(roofCalculator.calculateAmountOfBoxesOfBottomScrews(materialArrayList.get(15), carportWidth, carportLength));

        //tilføj hulbånd til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.perforatedTape(materialArrayList.get(16)));

        // tilføj venstre beslag til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.steelBracketLeft(materialArrayList.get(18), rafters));

        // tilføj højre beslag til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.steelBracketRight(materialArrayList.get(17), rafters));

        //tilføj skruer til stern og vandbræt til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.screwsForSternAndWeatherBoard(materialArrayList.get(20)));

        // tilføj skruer til beslag og hulbånd til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.screwsForTapeAndBracket(materialArrayList.get(21), brackets, rafters));

        // tilføj bræddebolte til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.carriageBoltWithShed(materialArrayList.get(22), postCalculator.calculatePostAmountWithShed(carportLength, shedLength)));

        // tilføj firkantskiver til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.squareSpacer(materialArrayList.get(23), boltAndBracketCalculator.calculateCarriageBoltWithShed(postCalculator.calculatePostAmountWithShed(carportLength, shedLength))));

        //tilføj træ til z og håndtag og hængsel til dør til skuret
        bomlineArrayList.addAll(boltAndBracketCalculator.addWoodForZPlusHandleAndHingeForDoor(materialArrayList.get(4), materialArrayList.get(27), materialArrayList.get(28)));

        //tilføj vinkelbeslag til skuret
        bomlineArrayList.add(boltAndBracketCalculator.calculateAngleBracket(16, materialArrayList.get(29)));

        return bomlineArrayList;
    }

    public ArrayList<Bomline> createCarportNoShed(int carportLength, int carportWidth) {

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();
        int rafters = frameCalculator.calculateRafters(carportLength);
        int brackets = boltAndBracketCalculator.calculateSteelBracketLeft(rafters) * 2;


        //tilføj understern til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateFrontUnderStern(underSternArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateSideUnderStern(underSternArrayList, carportLength));

        //tilføj overstern til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateFrontOverStern(overSternArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateSideOverStern(overSternArrayList, carportLength));

        //tilføj rem til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateHead(carportLength, headArrayList, 210));

        // tilføj spær til arraylist
        bomlineArrayList.add(frameCalculator.rafters(materialArrayList.get(7), carportLength));

        // tilføj stolper til arraylist
        bomlineArrayList.add(postCalculator.postAmount(materialArrayList.get(9), carportLength));

        // tilføj vandbræt til arraylist
        bomlineArrayList.addAll(frameCalculator.calculateWeatherBoardForFrontAndBack(weatherboardArrayList, carportWidth));
        bomlineArrayList.addAll(frameCalculator.calculateWeatherBoardForSide(weatherboardArrayList, carportLength));

        // tilføj tagplader og skruer til arraylist
        bomlineArrayList.addAll(roofCalculator.calculateRoofPlates(carportLength, carportWidth, roofPlatesArraylist));
        bomlineArrayList.add(roofCalculator.calculateAmountOfBoxesOfBottomScrews(materialArrayList.get(15), carportWidth, carportLength));

        //tilføj hulbånd til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.perforatedTape(materialArrayList.get(16)));

        // tilføj venstre beslag til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.steelBracketLeft(materialArrayList.get(18), rafters));

        // tilføj højre beslag til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.steelBracketRight(materialArrayList.get(17), rafters));

        //tilføj skruer til stern og vandbræt til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.screwsForSternAndWeatherBoard(materialArrayList.get(20)));

        // tilføj skruer til beslag og hulbånd til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.screwsForTapeAndBracket(materialArrayList.get(21), brackets, rafters));

        // tilføj bræddebolte til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.carriageBolt(materialArrayList.get(22), postCalculator.calculatePostAmount(carportLength)));

        // tilføj firkantskiver til arraylist
        bomlineArrayList.add(boltAndBracketCalculator.squareSpacer(materialArrayList.get(23), boltAndBracketCalculator.calculateCarriageBolt(postCalculator.calculatePostAmount(carportLength))));

        return bomlineArrayList;
    }

    public BoltAndBracketCalculator getBoltAndBracketCalculator() {
        return boltAndBracketCalculator;
    }

    public FrameCalculator getFrameCalculator() {
        return frameCalculator;
    }

    public PostCalculator getPostCalculator() {
        return postCalculator;
    }

    public RoofCalculator getRoofCalculator() {
        return roofCalculator;
    }

    public ShedCalculator getShedCalculator() {
        return shedCalculator;
    }
}
