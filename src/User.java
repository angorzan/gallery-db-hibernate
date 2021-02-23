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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="users")

public class User implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String username;
	
	@Column
	private int joinDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private Set<Album> albums = new HashSet<Album>();

	@ManyToMany(mappedBy="likedByUsers", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Photo> likedPhotos = new HashSet<Photo>();

	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void addAlbum(Album newAlbum) {
		albums.add(newAlbum);
	}

	public void removeAlbum(Album newAlbum) {
		albums.remove(newAlbum);
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(int joinDate) {
		this.joinDate = joinDate;
	}


	public Set<Photo> getLikedPhotos() {
		return likedPhotos;
	}


	public void setLikedPhotos(Set<Photo> likedPhotos) {
		this.likedPhotos = likedPhotos;
	}

	public void likePhoto(Photo photo) {
		likedPhotos.add(photo);
	}

	public void unlikePhoto(Photo photo) {
		likedPhotos.add(photo);
	}
	
}
