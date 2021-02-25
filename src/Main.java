import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
//		main.addNewData();
//		main.printUsers();
		main.cascadeTest();
//		main.removeUser();
//		main.removeAlbum();
//		main.removePhoto();
		main.removeLike();
		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
	
	private void addNewData() {
		User newUser = new User();
		newUser.setUsername("luke");
		newUser.setJoinDate(2013);

		User newUser2 = new User();
		newUser2.setUsername("mary");
		newUser2.setJoinDate(2017);
		
		Album newAlbum = new Album();
		newAlbum.setName("Luke's album");
		newAlbum.setDescription("My favourite photos");

		Album newAlbum2 = new Album();
		newAlbum2.setName("Mary's album");
		newAlbum2.setDescription("My photos");

		
		Photo newPhoto = new Photo();
		newPhoto.setDate(2016);
		newPhoto.setName("Croatia 2016");

		Photo newPhoto2 = new Photo();
		newPhoto2.setDate(2017);
		newPhoto2.setName("Hungary 2017");
		
		
		newAlbum.addPhoto(newPhoto);
		newUser.addAlbum(newAlbum);
		
		newAlbum2.addPhoto(newPhoto2);
		newUser2.addAlbum(newAlbum2);
		
		newUser2.likePhoto(newPhoto);
		newPhoto.addUserLike(newUser2);
		
		Transaction transaction = session.beginTransaction();
		session.save(newUser);
		session.save(newUser2);
		transaction.commit();
	}
	
	private void printUsers() {
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();

		System.out.println("### Users");
		for (User user : users) {
			System.out.println(user.getUsername());
			for (Album album : user.getAlbums()) {
				System.out.println("    " + album.getName());
				for (Photo photo : album.getPhotos()) {
					System.out.print("        " + photo.getName());
				}
			}
		}
	}
	
	private void removeUser() {
		
		String hql = "FROM User WHERE id=2";
		Query query = session.createQuery(hql);
		User user = (User) query.uniqueResult();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}
	
	private void removeAlbum() {
		
		String hql = "FROM User WHERE id=4";
		Query query = session.createQuery(hql);
		User user = (User) query.uniqueResult();
				
		Transaction transaction = session.beginTransaction();
		for(Album album : user.getAlbums()) {
			user.removeAlbum(album);
			session.save(user);
		}
		transaction.commit();
	}
	private void removePhoto() {
		
		String hql = "FROM Album WHERE id=9";
		Query query = session.createQuery(hql);
		Album album = (Album) query.uniqueResult();
				
		Transaction transaction = session.beginTransaction();
		for(Photo photo : album.getPhotos()) {
			album.removePhoto(photo);
			session.save(album);
		}
		transaction.commit();
	}
	private void removeLike() {
		
		String hql = "FROM Photo WHERE id=4";
		Query query = session.createQuery(hql);
		Photo photo = (Photo) query.uniqueResult();
				
		Transaction transaction = session.beginTransaction();
		for(User user : photo.getLikedByUsers()) {
			photo.removeUserLike(user);
			session.save(photo);
		}
		transaction.commit();
	}
	private void cascadeTest() {
		
		String hql = "FROM User u WHERE u.id=2";
		Query query = session.createQuery(hql);
		User user = (User) query.uniqueResult();
				
		Transaction transaction = session.beginTransaction();
		for(Photo photo : user.getLikedPhotos()) {
			photo.removeUserLike(user);
			session.save(photo);
		}
		session.delete(user);
		transaction.commit();
	}

}
