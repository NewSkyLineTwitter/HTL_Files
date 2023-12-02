import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CityMap {
    final ArrayList<City> cities = new ArrayList<>();

    public void addCity(City city) {
        cities.add(city);
    }

    public void printRoute(ArrayList<Integer> route) {
        double totalDistance = 0;

        System.out.println("Optimal Route:");
        System.out.println("Start ->");

        for (int i = 0; i < route.size(); i++) {
            City currentCity = cities.get(route.get(i));
            City nextCity;

            if (i + 1 < route.size()) {
                nextCity = cities.get(route.get(i + 1));
            } else {
                nextCity = cities.get(route.get(0));
            }

            double distance = currentCity.dist(nextCity);
            totalDistance += distance;

            System.out.print(currentCity.getName() + " -> ");
        }
        System.out.println("Total Distance: " + totalDistance);
    }

    public void printAllRoutes() {
        ArrayList<Integer> currentRoute = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            currentRoute.add(i);
        }

        Iterator<ArrayList<Integer>> permutations = new PermutationIterator<>(currentRoute);

        while (permutations.hasNext()) {
            ArrayList<Integer> route = permutations.next();
            double totalDistance = calculateTotalDistance(route);

            System.out.println("Route:");
            System.out.println("Start ->");

            for (int i = 0; i < route.size(); i++) {
                City currentCity = cities.get(route.get(i));
                City nextCity;

                if (i + 1 < route.size()) {
                    nextCity = cities.get(route.get(i + 1));
                } else {
                    nextCity = cities.get(route.get(0));
                }

                double distance = currentCity.dist(nextCity);
                totalDistance += distance;

                System.out.print(currentCity.getName() + " (" + currentCity.x + "," + currentCity.y + ") -> ");
            }

            System.out.println("End");
            System.out.println("Total Distance: " + totalDistance);
        }
    }

    public ArrayList<Integer> findShortestRoute() {
        ArrayList<Integer> shortestRoute = null;
        double shortestDistance = Double.MAX_VALUE;

        ArrayList<Integer> currentRoute = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            currentRoute.add(i);
        }

        Iterator<ArrayList<Integer>> permutations = new PermutationIterator<>(currentRoute);

        while (permutations.hasNext()) {
            ArrayList<Integer> route = permutations.next();
            double totalDistance = calculateTotalDistance(route);

            if (totalDistance < shortestDistance) {
                shortestDistance = totalDistance;
                shortestRoute = new ArrayList<>(route);
            }
        }

        return shortestRoute;
    }

    private double calculateTotalDistance(ArrayList<Integer> route) {
        double totalDistance = 0;

        for (int i = 0; i < route.size(); i++) {
            City currentCity = cities.get(route.get(i));
            City nextCity;

            if (i + 1 < route.size()) {
                nextCity = cities.get(route.get(i + 1));
            } else {
                nextCity = cities.get(route.get(0));
            }

            totalDistance += currentCity.dist(nextCity);
        }

        return totalDistance;
    }
}