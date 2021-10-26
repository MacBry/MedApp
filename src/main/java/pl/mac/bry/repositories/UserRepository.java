package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.User;

public interface UserRepository extends CrudRepository<User,Long>{

}
