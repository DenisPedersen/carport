package dat.startcode.model.entities;

import dat.startcode.model.entities.calculator.BoxCalculator;
import dat.startcode.model.entities.calculator.CarportCalculator;
import dat.startcode.model.entities.calculator.GeneralCalculator;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest

{

    Material material = new Material(1,"Tagplade", 5,"styk",600,109,1,1,2);
    ArrayList<Material> materialArrayList = new ArrayList<>();
    CarportCalculator carportCalculator = new CarportCalculator();

    @BeforeAll
    public static void setUpClass() {
    }


    @BeforeEach
    void setUp()
    {
        ArrayList<Material> roofPlateArrayList = new ArrayList<>();
        //materialArrayList.clear();
        roofPlateArrayList.add(material);
        roofPlateArrayList.add(new Material(1,"Tagplade", 5,"styk",360,109,1,1,2));
        roofPlateArrayList.add(new Material(1,"Tagplade", 5,"styk",240,109,1,1,2));

    }

    @Test
    void calculateShed2on1Test()
    {

        Material material = new Material(11,"tryimp. Bræt", 45,"styk",210,19,100,1,1);


        int planks = carportCalculator.getShedCalculator().calculateShed2on1(210, 600, material);

        assertEquals(187, planks);

    }


    @Test
    void CalculateRoofPlatesTest()
    {
    ArrayList<Material> listOfRoofPlates = new ArrayList<>();
    listOfRoofPlates.add(material);
    listOfRoofPlates.add(new Material(2,"tagplade", 10,"styk",360,109,1,1,2));
    ArrayList <Bomline> newBomLineArrayList = carportCalculator.getRoofCalculator().calculateRoofPlates(900, 600, listOfRoofPlates);
    assertEquals(2,newBomLineArrayList.size());
    }

    @Test
    void calculateAmountOfRoofPlatesForWidthTest() {
        int amount;

        amount = carportCalculator.getRoofCalculator().calculateAmountOfRoofPlatesForWidth( material, 600);
        assertEquals(6, amount);
        amount = carportCalculator.getRoofCalculator().calculateAmountOfRoofPlatesForWidth(material, 1090);
        assertEquals(10, amount);
        amount = carportCalculator.getRoofCalculator().calculateAmountOfRoofPlatesForWidth(material, 2);
        assertEquals(1, amount);
        amount = carportCalculator.getRoofCalculator().calculateAmountOfRoofPlatesForWidth(material, 109);
        assertEquals(1, amount);

    }

    @Test
    void calculatePostAmountTest() {
        int post = carportCalculator.getPostCalculator().calculatePostAmount(780);
        assertEquals(6, post);
    }


    @Test
    void calculateCarriageBolt() {
        int carriageBolt = carportCalculator.getBoltAndBracketCalculator().calculateCarriageBolt(6);
        assertEquals(14, carriageBolt);
    }

    @Test
    void calculateSquareSpace() {
        int squareSpace = carportCalculator.getBoltAndBracketCalculator().calculateSquareSpacer(12);
        assertEquals(12, squareSpace);
    }

    @Test
    void calculateRafters() {
        int rafters = carportCalculator.getFrameCalculator().calculateRafters(780);
        assertEquals(15, rafters);
    }

    @Test
    void calculateRaftersDistance() {
        float distance = carportCalculator.getFrameCalculator().calculateRaftersDistance(780, 15);
        assertEquals(56.5, distance);
    }


    @Test
    void calculateBottomScrewForRoofTest() {

        int quantity;

        quantity=carportCalculator.getRoofCalculator().calculateBottomScrewForRoof(600, 780);
        assertEquals(546,quantity);
        assertEquals(13, carportCalculator.getRoofCalculator().calculateBottomScrewForRoof(100,100));
        assertEquals(1300, carportCalculator.getRoofCalculator().calculateBottomScrewForRoof(2000,500));
    }

    @Test
    void calculateMaterialLengthTest()
    {

        GeneralCalculator generalCalculator  = new GeneralCalculator();
        materialArrayList.clear();
        materialArrayList.add(new Material(1,"Tagplade", 5,"styk",240,109,1,1,2));
        materialArrayList.add(new Material(1,"Tagplade", 5,"styk",360,109,1,1,2));
        materialArrayList.add(material);


        Material returnedMaterial = generalCalculator.calculateMaterialLength(370, materialArrayList);
        assertEquals(material, returnedMaterial);

        returnedMaterial = generalCalculator.calculateMaterialLength(360, materialArrayList);
        assertEquals(material, returnedMaterial);

        returnedMaterial = generalCalculator.calculateMaterialLength(120, materialArrayList);
        assertEquals(materialArrayList.get(0), returnedMaterial);
    }

    @Test
    void calculateQuantityOfBoxesTest()
    {
        BoxCalculator boxCalculator = new BoxCalculator();

        Material material = new Material(1, "Bundskrue", 5, "Pakke", 2, 200);

        int result;

        result = boxCalculator.calculateQuantityOfBoxes(350, material);
        assertEquals(2, result);

        result = boxCalculator.calculateQuantityOfBoxes(200, material);
        assertEquals(1, result);

        result = boxCalculator.calculateQuantityOfBoxes(1, material);
        assertEquals(1, result);
    }

    @Test
    void calculateAmountOfBoxesOfBottomScrewsTest()
    {
        Material material = new Material(1, "Bundskrue", 5, "Pakke", 2, 200);

        Bomline bomline =   carportCalculator.getRoofCalculator().calculateAmountOfBoxesOfBottomScrews(material, 7, 7);
        assertEquals(1, bomline.getQuantity());

        bomline = carportCalculator.getRoofCalculator().calculateAmountOfBoxesOfBottomScrews(material, 1, 2);
        assertEquals(1, bomline.getQuantity());

    }

    @Test
    void calculateFrontAndBackSternTest()
    {
        ArrayList<Material> sternArrayList = new ArrayList<>();
        sternArrayList.add(new Material(1,"25x125mm. trykimp. Brædt ", 50, "stk", 360, 25, 125, 1,1));
        sternArrayList.add(new Material(2,"25x125mm. trykimp. Brædt ", 50, "stk", 560, 25, 125, 1,1));

        ArrayList<Bomline> bomlineArrayList = carportCalculator.getFrameCalculator().calculateFrontOverStern(sternArrayList, 900);
        assertEquals(2, bomlineArrayList.size());
        assertEquals(920, bomlineArrayList.get(0).getMaterial().getLength()+bomlineArrayList.get(1).getMaterial().getLength());

        bomlineArrayList = carportCalculator.getFrameCalculator().calculateFrontUnderStern(sternArrayList, 360);
        assertEquals(1, bomlineArrayList.size());
        assertEquals(sternArrayList.get(1).getLength(), bomlineArrayList.get(0).getMaterial().getLength());

    }

    @Test
    void  calculateSideSternTest()
    {
        ArrayList<Material> sternArrayList = new ArrayList<>();
        sternArrayList.add(new Material(1,"25x125mm. trykimp. Brædt ", 50, "stk", 360, 25, 125, 1,1));
        sternArrayList.add(new Material(2,"25x125mm. trykimp. Brædt ", 50, "stk", 560, 25, 125, 1,1));

        ArrayList<Bomline> bomlineArrayList = carportCalculator.getFrameCalculator().calculateSideUnderStern(sternArrayList, 900);
        assertEquals(2, bomlineArrayList.size());
        assertEquals(920, bomlineArrayList.get(0).getMaterial().getLength()+bomlineArrayList.get(1).getMaterial().getLength());

        bomlineArrayList = carportCalculator.getFrameCalculator().calculateSideUnderStern(sternArrayList, 360);
        assertEquals(1, bomlineArrayList.size());
        assertEquals(sternArrayList.get(1).getLength(), bomlineArrayList.get(0).getMaterial().getLength());
    }

    @Test
    void calculateWeatherBoardForFrontAndBackTest()
    {
        ArrayList<Material> weatherboardArrayList = new ArrayList<>();
        weatherboardArrayList.add(new Material(1,"25x125mm. trykimp. Brædt ", 50, "stk", 360, 25, 125, 1,1));
        weatherboardArrayList.add(new Material(2,"25x125mm. trykimp. Brædt ", 50, "stk", 560, 25, 125, 1,1));

        ArrayList<Bomline> bomlineArrayList = carportCalculator.getFrameCalculator().calculateWeatherBoardForFrontAndBack(weatherboardArrayList, 900);
        assertEquals(2, bomlineArrayList.size());
        assertEquals(920, bomlineArrayList.get(0).getMaterial().getLength()+bomlineArrayList.get(1).getMaterial().getLength());

        bomlineArrayList = carportCalculator.getFrameCalculator().calculateWeatherBoardForFrontAndBack(weatherboardArrayList, 360);
        assertEquals(1, bomlineArrayList.size());
        assertEquals(weatherboardArrayList.get(1).getLength(), bomlineArrayList.get(0).getMaterial().getLength());


    }

    @Test
    void  calculateWeatherBoardForSideTest()
    {
        ArrayList<Material> sternArrayList = new ArrayList<>();
        sternArrayList.add(new Material(1,"25x125mm. trykimp. Brædt ", 50, "stk", 360, 25, 125, 1,1));
        sternArrayList.add(new Material(2,"25x125mm. trykimp. Brædt ", 50, "stk", 560, 25, 125, 1,1));

        ArrayList<Bomline> bomlineArrayList = carportCalculator.getFrameCalculator().calculateWeatherBoardForSide(sternArrayList, 900);
        assertEquals(2, bomlineArrayList.size());
        assertEquals(920, bomlineArrayList.get(0).getMaterial().getLength()+bomlineArrayList.get(1).getMaterial().getLength());

        bomlineArrayList = carportCalculator.getFrameCalculator().calculateWeatherBoardForSide(sternArrayList, 360);
        assertEquals(1, bomlineArrayList.size());
        assertEquals(sternArrayList.get(1).getLength(), bomlineArrayList.get(0).getMaterial().getLength());
    }


    @Test
    void calculateSteelBracketRight() {
        int steelBracket = carportCalculator.getBoltAndBracketCalculator().calculateSteelBracketRight(12);
        assertEquals(12, steelBracket);
    }

    @Test
    void calculateSteelBracketLeft() {
        int steelBracket = carportCalculator.getBoltAndBracketCalculator().calculateSteelBracketLeft(12);
        assertEquals(12, steelBracket);
    }

    @Test
    void calculateScrewForBracket() {
        int screws = carportCalculator.getBoltAndBracketCalculator().calculateScrewForBracket(15);
        assertEquals(270, screws);
    }

    @Test
    void calculateScrewForPerforatedTape() {
        int screws = carportCalculator.getBoltAndBracketCalculator().calculateScrewForPerforatedTape(15);
        assertEquals(52, screws);
    }

    @Test
    void calculatePostAmountWithShedTest()
    {
        int posts = carportCalculator.getPostCalculator().calculatePostAmountWithShed(780, 210);
        assertEquals(11, posts);
    }

    @Test
    void postAmount() {
        Material material = new Material(1, "Stolper", 5, "stk", 1);

        Bomline bomline = carportCalculator.getPostCalculator().postAmount(material, 780);
        assertEquals(6, bomline.getQuantity());
    }

    @Test
    void carriageBolt() {
        Material material = new Material(1, "Breddebolte", 5, "stk", 2);

        Bomline bomline = carportCalculator.getBoltAndBracketCalculator().carriageBolt(material, 6);
        assertEquals(14, bomline.getQuantity());
    }

    @Test
    void squareSpacer() {
        Material material = new Material(1, "Firkantskiver", 5, "stk", 2);

        Bomline bomline = carportCalculator.getBoltAndBracketCalculator().squareSpacer(material, 16);
        assertEquals(16, bomline.getQuantity());
    }

    @Test
    void rafters() {
        Material material = new Material(1, "Spær", 5, "stk", 1);

        Bomline bomline = carportCalculator.getFrameCalculator().rafters(material, 780);
        assertEquals(15, bomline.getQuantity());
    }

    @Test
    void steelBracketRight(){
        Material material = new Material(1, "Universalbeslag højre", 5, "stk", 1);

        Bomline bomline = carportCalculator.getBoltAndBracketCalculator().steelBracketRight(material, 15);
        assertEquals(15, bomline.getQuantity());
    }

    @Test
    void steelBracketLeft(){
        Material material = new Material(1, "Universalbeslag venstre", 5, "stk", 1);

        Bomline bomline = carportCalculator.getBoltAndBracketCalculator().steelBracketLeft(material, 15);
        assertEquals(15, bomline.getQuantity());
    }

    @Test
    void screwsForTapeAndBracket(){
        Material material = new Material(1, "Skruer", 5, "pakke", 2, 250);

        Bomline bomline = carportCalculator.getBoltAndBracketCalculator().screwsForTapeAndBracket(material, 15, 15);
        assertEquals(2, bomline.getQuantity());

    }

    @Test
    void calculateHeadTest()
    {
        ArrayList<Material> headArrayList = new ArrayList<>();
        headArrayList.add(new Material(8,"Spærtræ ubh.", 100,"Stk",600,45,195,2,1,"Træ"));
        headArrayList.add(new Material(9,"Spærtræ ubh.",80,"Stk",480,45,195,1,1,"Træ"));

        ArrayList<Bomline> bomlineArrayList = carportCalculator.getFrameCalculator().calculateHead(780, headArrayList, 210);
        assertEquals(2, bomlineArrayList.size());


    }

    @Test
    void calculateShedPlanksNeededForSide() {
        Material material = new Material(11,"tryimp. Bræt", 45,"styk",210,19,100,1,1);

        int plankOverlap = (2*material.getHeight()) - (2*15);

        int planksNeededForSide = carportCalculator.getShedCalculator().calculateShedPlanksNeededForSide(210, plankOverlap,material);

        assertEquals(48, planksNeededForSide);
    }

    @Test
    void calculateShedPlanksNeededForFrontAndBack() {
        Material material = new Material(11,"tryimp. Bræt", 45,"styk",210,19,100,1,1);

        int plankOverlap = (2*material.getHeight()) - (2*15);


        int planksNeededForFrontAndBack = carportCalculator.getShedCalculator().calculateShedPlanksNeededForFrontAndBack(600,plankOverlap,material);

        assertEquals(139,planksNeededForFrontAndBack);
    }

    @Test
    void calculateShed2on1() {
        Material material = new Material(11,"tryimp. Bræt", 45,"styk",210,19,100,1,1);
        int totalPlanksNeeded = carportCalculator.getShedCalculator().calculateShed2on1(210, 600, material);

        assertEquals(187,totalPlanksNeeded);
    }

    @Test
    void calculateShortScrewsForShed() {
        int screwsNeeded = carportCalculator.getShedCalculator().calculateShortScrewsForShed(94);

        assertEquals(376,screwsNeeded);
    }

    @Test
    void calculateLongScrewsForShed() {
        int screwNeeded = carportCalculator.getShedCalculator().calculateLongScrewsForShed( 94);

        assertEquals(752,screwNeeded);
    }

    @Test
    void shedPlanksAndScrewsTest () {
    }


    void calculatePostDistanceTest(){
        float postDistance = carportCalculator.getPostCalculator().calculatePostDistance(660);
        assertEquals(280, postDistance);
    }

    @Test
    void calculatePostDistanceWithFullShedLengthTest(){
        int postDistance = carportCalculator.getPostCalculator().calculatePostDistanceWithFullShedLength(620, 150);
        assertEquals(140, postDistance);
    }

    @Test
    void calculatePostDistanceWithFullShedWidthTest(){
        int postDistanceWidth = carportCalculator.getPostCalculator().calculatePostDistanceWithFullShedWidth(530);
        assertEquals(265, postDistanceWidth);
    }

    @Test
    void calculatePerforatedTapeLengthTest(){

        int perforatedTapeLength = carportCalculator.getBoltAndBracketCalculator().calculatePerforatedTapeLength(780);
        assertEquals(721,perforatedTapeLength);
    }
    @Test
    void calculatePerforatedtapeLengthWithShedTest(){
        int perforatedTapeLength = carportCalculator.getBoltAndBracketCalculator().calculatePerforatedtapeLengthWithShed(780,210);
        assertEquals(511,perforatedTapeLength);
    }
}
