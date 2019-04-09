package com.example.nemesis;

public class Articles {
    private String articleName;
    private String articleLink;
    private String articlePic;

    public Articles(String articleName, String articleLink, String articlePic) {
        this.articleName = articleName;
        this.articleLink = articleLink;
        this.articlePic = articlePic;
    }


    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }
}
