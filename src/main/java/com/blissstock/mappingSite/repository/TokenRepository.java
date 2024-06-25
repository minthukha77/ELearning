package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.Token;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
        @Query(nativeQuery = true, value = "SELECT * FROM Token WHERE token=:token and token_type=:tokenType and is_used=false Limit 1")
        Token getToken(
                        @Param("token") String token,
                        @Param("tokenType") String tokenType);

        @Query(nativeQuery = true, value = "SELECT user_account FROM Token WHERE token=:token and token_type=:tokenType and is_used=false Limit 1")
        Long findByToken(@Param("token") String token,
                        @Param("tokenType") String tokenType);

        @Modifying
        @Query(nativeQuery = true, value = "update token set is_used=true WHERE token=:token")
        void setAsUsedtoken(
                        @Param("token") String token);

}

/*
 * , @Param("type") String type);
 */
/*
 * @Query("SELECT t FROM Token t WHERE t.token=:token and t.type=type")
 * Token findByTokenAndType(String token, String type);
 */

// Token findByToken(String token);