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

@SuppressWarnings("serial")
@Entity
@Table(name="photos")

public class Photo implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private int date;
	

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="photos_liked_by_users",
		joinColumns=@JoinColumn(name="photo_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	Set<User> likedByUsers = new HashSet<User>();

	
	public Photo() {
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


	public int getDate() {
		return date;
	}


	public void setDate(int date) {
		this.date = date;
	}
	
	public Set<User> getLikedByUsers() {
		return likedByUsers;
	}


	public void setLikedByUsers(Set<User> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}


	public void addUserLike(User user) {
		likedByUsers.add(user);
	}
	
	public void removeUserLike(User user) {
		likedByUsers.remove(user);
	}
	
}
