package android.example.com.myapplication;

public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;
    private String  likes;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl,String likes) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.likes = likes;
    }

    public String getText() {
        return text;
    }
    public String getLikes() {
        return likes;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}