package org.expense.expensetrackerbackend.repoistry;

import org.expense.expensetrackerbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface Userrepo extends CrudRepository<User , Long> {
    public User findByUsername(String username);

}
