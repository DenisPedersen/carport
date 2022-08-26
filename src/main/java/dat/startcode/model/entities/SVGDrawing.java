package dat.startcode.model.entities;

import dat.startcode.model.entities.calculator.*;
import dat.startcode.model.services.SVG;

public class SVGDrawing {

    CarportRequest carportRequest;
    GeneralCalculator generalCalculator = new GeneralCalculator();
    FrameCalculator frameCalculator = new FrameCalculator();
    BoltAndBracketCalculator boltAndBracketCalculator = new BoltAndBracketCalculator();
    PostCalculator postCalculator = new PostCalculator();

    public SVGDrawing(CarportRequest newCarportRequest) {
        this.carportRequest = newCarportRequest;
    }

    public SVG draw() {

        /*Ydre canvas med pile*/
        SVG outerCanvas = new SVG(0, 0, "0 0 855 690", 600, 600);

        /*Carport*/
        SVG carportSVG = new SVG(75, 10, "0 0 800 600", 500, 500);


        /*Spær*/
        for (int x = 0; x < frameCalculator.calculateRafters(carportRequest.getLength()); x++) {
            carportSVG.addRect((int) (0 + frameCalculator.calculateRaftersDistance(carportRequest.getLength(), frameCalculator.calculateRafters(carportRequest.getLength())) * x), 0, carportRequest.getWidth(), 4.5f);

        }
        /*Top rem*/
        carportSVG.addLine(0, 0, carportRequest.getLength(), 0);

        /*Bund rem*/
        carportSVG.addLine(0, carportRequest.getWidth(), carportRequest.getLength(), carportRequest.getWidth());

        /*Hulbånd*/
        carportSVG.addLineWithDash(55, 0, boltAndBracketCalculator.calculatePerforatedTapeLength(carportRequest.getLength()), carportRequest.getWidth());
        carportSVG.addLineWithDash(boltAndBracketCalculator.calculatePerforatedTapeLength(carportRequest.getLength()), 0, 55, carportRequest.getWidth());

        /*Stolper fast*/
        carportSVG.addRect(45, 0, 15, 15);
        carportSVG.addRect(45, carportRequest.getWidth() - 15, 15, 15);
        carportSVG.addRect(carportRequest.getLength() - 45, 0, 15, 15);
        carportSVG.addRect(carportRequest.getLength() - 45, carportRequest.getWidth() - 15, 15, 15);

        /*Midter stolper*/
        if (postCalculator.calculatePostAmountNeeded(carportRequest.getLength()) == 3) {
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()), 0, 15, 15);
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()), carportRequest.getWidth() - 15, 15, 15);
        } else if (postCalculator.calculatePostAmountNeeded(carportRequest.getLength()) == 4) {
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()), 0, 15, 15);
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()) + postCalculator.calculatePostDistance(carportRequest.getLength()), 0, 15, 15);
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()), carportRequest.getWidth() - 15, 15, 15);
            carportSVG.addRect(45 + generalCalculator.calculateXDistance(carportRequest.getLength()) + postCalculator.calculatePostDistance(carportRequest.getLength()), carportRequest.getWidth() - 15, 15, 15);
        }


        return carportSVG;
    }

    public SVG drawWithShed() {
        CarportCalculator carportCalculator = new CarportCalculator();

        SVG svg = new SVG(75, 10, "0 0 800 600", 500, 500);

        int rafters = frameCalculator.calculateRafters(carportRequest.getLength());

        for (int x = 0; x < rafters; x++) {
            svg.addRect((int) (0 + frameCalculator.calculateRaftersDistance(carportRequest.getLength(), frameCalculator.calculateRafters(carportRequest.getLength())) * x), 0, carportRequest.getWidth(), 4.5f);

        }
        /*Top rem*/
        svg.addLine(0, 0, carportRequest.getLength(), 0);

        /*Bund rem*/
        svg.addLine(0, carportRequest.getWidth(), carportRequest.getLength(), carportRequest.getWidth());

        /*Hulbånd*/
        svg.addLineWithDash(55, 0, boltAndBracketCalculator.calculatePerforatedTapeLength(carportRequest.getLength()) - carportRequest.getShedLength() + 50, carportRequest.getWidth());
        svg.addLineWithDash(boltAndBracketCalculator.calculatePerforatedTapeLength(carportRequest.getLength()) - carportRequest.getShedLength() + 50, 0, 55, carportRequest.getWidth());

        /*Stolper fast*/
        svg.addRect(45, 0, 15, 15);
        svg.addRect(45, carportRequest.getWidth() - 15, 15, 15);
        svg.addRect(carportRequest.getLength() - 45, 0, 15, 15);
        svg.addRect(carportRequest.getLength() - 45, carportRequest.getWidth() - 15, 15, 15);

        /*Skur væg*/
        int shedStart = carportRequest.getLength() - carportRequest.getShedLength();
        svg.addLine(shedStart, 0, shedStart, carportRequest.getWidth());

        /*Stolper på skur / midten*/
        int yPosShed = carportRequest.getWidth() / 2;
        svg.addRect(shedStart, yPosShed, 15, 15);

        int xPosShed = carportRequest.getLength() - 10;
        svg.addRect(xPosShed, carportRequest.getWidth() / 2, 15, 15);

        /*Stolper på skur*/
        int xPos = carportRequest.getLength() - carportRequest.getShedLength();
        svg.addRect(xPos, 0, 15, 15);

        svg.addRect(xPos, carportRequest.getWidth() - 15, 15, 15);

        return svg;
    }


}
