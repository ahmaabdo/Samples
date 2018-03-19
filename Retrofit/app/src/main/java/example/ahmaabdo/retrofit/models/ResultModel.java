package example.ahmaabdo.retrofit.models;

import java.util.List;

/**
 * Created by Ahmad on Mar 17, 2018.
 * An object that contains a list of posts
 */

public class ResultModel {

    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
