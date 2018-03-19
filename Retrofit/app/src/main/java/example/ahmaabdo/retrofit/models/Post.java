package example.ahmaabdo.retrofit.models;

/**
 * Created by Ahmad on Mar 17, 2018.
 * An object for posts
 */

public class Post {
    //Same name at JSON
    private String id, post_content, post_writer, created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_writer() {
        return post_writer;
    }

    public void setPost_writer(String post_writer) {
        this.post_writer = post_writer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
