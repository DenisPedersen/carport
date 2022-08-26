package dat.startcode.model.entities.calculator;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;

public class PostCalculator {

    public int calculatePostAmountNeeded(int carportLength) {

        int minDistanceFromStern = 45;
        int distanceBetweenPost = carportLength - minDistanceFromStern * 2;
        int post = 0;

        if (distanceBetweenPost < 310) {
            return post + 2;
        } else if (distanceBetweenPost / 2 < 310) {
            return post + 3;
        } else {
            return post + 4;
        }
    }


    public int calculatePostDistance(int carportLength) {
        int postDistance = 0;
        int distanceFromSternBigCarport = 100;
        int distanceFromSternSmallCarport = 90;
        int restCarportLength = 0;


        if (carportLength >= 240 && carportLength < 310) {
            restCarportLength = carportLength - distanceFromSternSmallCarport;
            postDistance = restCarportLength;
        } else if (carportLength >= 310 && carportLength < 480) {
            restCarportLength = carportLength - distanceFromSternSmallCarport;
            postDistance = restCarportLength / 2;
        } else if (carportLength >= 480 && carportLength < 600) {
            restCarportLength = carportLength - distanceFromSternBigCarport;
            postDistance = restCarportLength / 2;
        } else if (carportLength >= 600) {
            restCarportLength = carportLength - distanceFromSternBigCarport;
            postDistance = restCarportLength / 3;
        }
        return postDistance;

    }

    public int calculatePostDistanceWithFullShedLength(int carportLength, int shedLength) {
        int postDistance = 0;
        int minDistanceFromStern = 50;
        int maxDistanceFromStern = 100;
        int restCarportLength = 0;
        int maxPostDistance = 310;
        int minPostDistance = 280;

        if (carportLength >= 780) {
            restCarportLength = shedLength + maxDistanceFromStern;
            restCarportLength = carportLength - restCarportLength;
            restCarportLength = restCarportLength - maxPostDistance;
            postDistance = restCarportLength - 30;
        } else if (carportLength < 780) {
            restCarportLength = shedLength + minDistanceFromStern;
            restCarportLength = carportLength - restCarportLength;
            restCarportLength = restCarportLength - minPostDistance;
            postDistance = restCarportLength;
        }
        return postDistance;
    }

    public int calculatePostDistanceWithFullShedWidth(int shedWidth) {
        int postDistance = 0;

        postDistance = shedWidth / 2;

        return postDistance;
    }

    public int calculatePostAmountWithShed(int carportLength, int shedLength) {
        int posts = 0;
        int sidePosts = 2;
        int shedPosts = 4;
        int sparePost = 1;
        int minDistanceBetweenPost = 250;
        int maxDistanceBetweenPost = 310;

        if (carportLength - shedLength < maxDistanceBetweenPost) {
            posts = 2;
        } else {
            posts = 4;
        }

        int totalPosts = posts + shedPosts + sidePosts + sparePost;

        return totalPosts;
    }

    public Bomline postAmountWithShed(int carportLength, int shedLength, Material material) {
        int postAmount = calculatePostAmountWithShed(carportLength, shedLength);

        return new Bomline(11, material, postAmount);
    }

    public int calculatePostAmount(int carportLength) {

        int minDistanceBetweenPost = 250;
        int maxDistanceBetweenPost = 310;
        int minDistanceFromStern = 90;
        int maxDistanceFromStern = 100;
        int postDistance = 0;
        int post = 0;


        if (minDistanceFromStern == 90 && maxDistanceFromStern == 100) {
            postDistance = carportLength - minDistanceFromStern;
            postDistance = postDistance / 2;

            if (postDistance < minDistanceBetweenPost) {
                post = 0;
            } else if (postDistance > minDistanceBetweenPost && postDistance < maxDistanceBetweenPost) {
                post = 1;
            } else if (postDistance > maxDistanceBetweenPost) {
                post = 2;
            }

        }

        return post * 2 + 2;

    }

    public Bomline postAmount(Material material, int carportLength) {
        int post = calculatePostAmount(carportLength);

        Bomline bomline = new Bomline(11, material, post);
        return bomline;
    }
}
