package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public Optional<User> getUserByCar(String carModel, int carSeries) {
      return sessionFactory.getCurrentSession()
              .createQuery("from User u where u.car.model = :model and u.car.series = :series", User.class)
              .setParameter("model", carModel)
              .setParameter("series", carSeries)
              .getResultStream()
              .findFirst();
   }

   @Override
   public void remove(User user) {
      sessionFactory.getCurrentSession().remove(user);
   }
}
