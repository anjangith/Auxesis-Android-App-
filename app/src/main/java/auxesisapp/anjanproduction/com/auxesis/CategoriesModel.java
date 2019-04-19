package auxesisapp.anjanproduction.com.auxesis;

import android.graphics.Bitmap;

/**
 * Created by HP on 24-06-2018.
 */

public class CategoriesModel {
    private String eventName;
    private int imageLogo;
    private int Drawable;


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public int getDrawable() {
        return Drawable;
    }

    public void setEventBit(int id) {
        this.Drawable = id;
    }


    public int getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(int imageLogo) {
        this.imageLogo = imageLogo;
    }
}
