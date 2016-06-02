package shashankm.gitlistofcommits;

/**
 * Created by shashankm on 02/06/16.
 */
public class Commit {
    private String authorName, authorAvatar, commitMessage;

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}
