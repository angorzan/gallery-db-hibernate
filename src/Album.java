import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="albums")
public class Album implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Set<Photo> photos = new HashSet<Photo>();



	public void addPhoto(Photo newPhoto) {
		photos.add(newPhoto);
	}

	public void removePhoto(Photo newPhoto) {
		photos.remove(newPhoto);
	}
	
	public Set<Photo> getPhotos() {
		return photos;
	}


	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}


	public Album() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
}
