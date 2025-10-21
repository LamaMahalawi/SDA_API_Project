package Tests.Mentoring_Practices.JsonNode.Task02;

import java.util.List;

public class PetPojo {
    private List<String> photoUrls;
    private String name;
    private Integer id;
    private CategoryPojo category;
    private List<TagPojo> tags;
    private String status;

    public void setPhotoUrls(List<String> photoUrls){
        this.photoUrls = photoUrls;
    }

    public List<String> getPhotoUrls(){
        return photoUrls;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setCategory(CategoryPojo category){
        this.category = category;
    }

    public CategoryPojo getCategory(){
        return category;
    }

    public void setTags(List<TagPojo> tags){
        this.tags = tags;
    }

    public List<TagPojo> getTags(){
        return tags;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "Task03{" +
                        "photoUrls = '" + photoUrls + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",category = '" + category + '\'' +
                        ",tags = '" + tags + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}