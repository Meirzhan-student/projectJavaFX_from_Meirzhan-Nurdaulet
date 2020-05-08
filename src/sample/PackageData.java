package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private Guests guests;
    private ArrayList<Guests> guest;

    public PackageData(String operationType, Guests guests) {
        this.operationType = operationType;
        this.guests = guests;
    }

    public PackageData(String operationType) {
        this.operationType = operationType;
    }

    public PackageData(ArrayList<Guests> guest) {
        this.guest = guest;
    }
}
