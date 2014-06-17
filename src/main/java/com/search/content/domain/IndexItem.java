package com.search.content.domain;

public class IndexItem {

    private String id;
    private String title;
    private String content;
    private String path;
    private String description;
    

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String PATH = "path";
    public static final String DESCRIPTION = "description";
    

    public IndexItem() {
		// TODO Auto-generated constructor stub
	}
    public IndexItem(String id, String title , String description) {
		this.id =id;
		this.title=title;
		this.description=description;
	}

   
   
    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}

	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
    public String toString() {
        return "IndexItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
