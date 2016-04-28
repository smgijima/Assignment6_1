package Domain;


/**
 * Created by mgijma on 2016/04/07.
 */

public class CalculateDistance  implements Distance{


    private String endPoint;
    private String destination;

    public CalculateDistance(String endPoint, String destination) {
        this.endPoint = endPoint;
        this.destination = destination;
    }


    public Distance calculate() {
        return new CalculateDistance(endPoint,destination);
    }

    @Override
    public String toString() {
        return "End point: " + endPoint +"\n" +"Destination=" + destination + "\n";
    }


}
