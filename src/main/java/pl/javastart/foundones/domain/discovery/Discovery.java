package pl.javastart.foundones.domain.discovery;

import java.time.LocalDateTime;

public class Discovery {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime dateAdded;
    private Integer categoryId;
    private Integer userId;

    public Discovery(String title, String url, String description, LocalDateTime dateAdded, Integer categoryId, Integer userId) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Discovery(Integer id, String title, String url, String description, LocalDateTime dateAdded, Integer categoryId, Integer userId) {
        this(title, url, description, dateAdded, categoryId, userId);
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
