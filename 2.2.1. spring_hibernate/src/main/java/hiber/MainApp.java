package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "LastName1", "user1@email.ru",
              new Car("ModelOne", 1000)));

      userService.add(new User("User2", "LastName2", "user2@email.ru",
              new Car("ModelTwo", 2000)));

      userService.add(new User("User3", "LastName3", "user3@email.ru",
              new Car("Model3", 3000)));

      userService.add(new User("User4", "LastName4", "user4@email.ru",
              new Car("ModelFour", 4000)));

      userService.add(new User("User5", "LastName5", "user5@email.ru",
              new Car("ModelFour", 4000)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      userService.getUserByCar("Model3", 3000)
              .ifPresentOrElse(System.out::println, () -> System.out.println("no user found"));

      context.close();

   }
}
