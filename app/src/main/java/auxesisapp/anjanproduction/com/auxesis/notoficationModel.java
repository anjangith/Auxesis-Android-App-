package auxesisapp.anjanproduction.com.auxesis;

/**
 * Created by HP on 03-09-2018.
 */

public class notoficationModel {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }
    public notoficationModel(String title,String body){
        this.title=title;
        this.description=body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
