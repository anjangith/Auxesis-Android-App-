package auxesisapp.anjanproduction.com.auxesis;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HP on 03-07-2018.
 */

public class recycler_Post_Model {

    private String name;
    private String post;
    private String photoUrl;
    private String postId;
    private String dateAndTime;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }



    public  recycler_Post_Model(){}

    public recycler_Post_Model(String name,String post,String downloadUrl,String postId,String dateAndTime){
        this.name=name;
        this.post=post;
        this.photoUrl=downloadUrl;
        this.postId=postId;
        this.dateAndTime=dateAndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
