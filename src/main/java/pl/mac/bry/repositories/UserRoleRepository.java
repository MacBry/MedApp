package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	UserRole findByRole(String role);
}
