package com.my.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="category_table")
public class Category {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryID", unique = true, nullable = false)
    private long categoryId;
	
	@Column(name="title", unique=true, nullable = false)
    private String title;
    
	@ManyToMany
    @JoinTable (
       name="category_advert_table",
       joinColumns = {@JoinColumn(name="categoryID", nullable = false, updatable = false, referencedColumnName="categoryID")},
       inverseJoinColumns = {@JoinColumn(name="advertID", referencedColumnName="advertID" )}
    )
	private Set<Advert> adverts = new HashSet<Advert>();

    public Category(String title) {
        this.title = title;
    }

    public Category() {
    }

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(Set<Advert> adverts) {
		this.adverts = adverts;
	}

	@Override 
	public String toString(){
		return title;
	}
}