package org.expense.expensetrackerbackend.repoistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenrepo extends CrudRepository<RefreshTokenrepo,Integer> {
    Optional<RefreshTokenrepo> findRefreshTokensBy(String token) ;



}
