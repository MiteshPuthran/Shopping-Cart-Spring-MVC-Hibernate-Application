import java.util.HashSet;
import java.util.Set;

import com.my.spring.dao.AdvertDAO;
import com.my.spring.dao.CartDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.AdvertException;
import com.my.spring.exception.CategoryException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

public class TestMain {

	public static void main(String[] args) throws CategoryException, AdvertException, UserException {
		
		UserDAO ud = new UserDAO();
		User u = ud.get(1);
		
		Category cat1 = new Category();		
		cat1.setTitle("New Category 2");
		
		Cart cart =new Cart();
		cart.setTotalprice("totalprice");
		
		Set<Advert> advs = new HashSet<Advert>();
		Advert adv = new Advert("adv12", "adv12", u , cat1, cart);
		
		AdvertDAO advertDao = new AdvertDAO();
		advertDao.create(adv);
		
		advs.add(adv);
		cat1.setAdverts(advs);
		
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.update(cat1);
		
		CartDAO cartDao = new CartDAO();
		cartDao.update(cart);
		
		System.out.println("Finished Testing...");

	}

}
