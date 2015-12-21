package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User> {

    List<User> getAll();
}
