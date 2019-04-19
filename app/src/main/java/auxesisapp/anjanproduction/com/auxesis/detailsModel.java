package auxesisapp.anjanproduction.com.auxesis;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by HP on 17-07-2018.
 */

public class detailsModel {

    private String name;
    private String descp;
    private int drawable;
    private double latng;
    private double longtd;
    private String placeName;
    private LatLng place;


    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }


    public double getLongtd() {
        return longtd;
    }

    public void setLongtd(double longtd) {
        this.longtd = longtd;
    }

    public double getLatng() {
        return latng;
    }

    public void setLatng(double latng) {
        this.latng = latng;
    }

    public LatLng getPlace() {
        return place;
    }

    public void setPlace(LatLng place) {
        this.place = place;
    }
}
