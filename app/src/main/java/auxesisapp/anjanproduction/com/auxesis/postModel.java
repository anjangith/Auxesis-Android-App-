package auxesisapp.anjanproduction.com.auxesis;

/**
 * Created by HP on 02-07-2018.
 */

public class postModel {

    private String name;
    private String post;
    private String UserId;
    private String postId;
    private String downloadUrl;
    private String timeandDate;


    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }



    public postModel(){}



    public postModel(String name,String post,String userId,String postId,String downloadUrl,String time){
        this.downloadUrl=downloadUrl;
        this.name=name;
        this.post=post;
        this.UserId=userId;
        this.postId=postId;
        this.timeandDate=time;

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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


    public String getTimeandDate() {
        return timeandDate;
    }

    public void setTimeandDate(String timeandDate) {
        this.timeandDate = timeandDate;
    }
}
