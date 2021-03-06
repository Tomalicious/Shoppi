package com.example.shopr.repositories;


import com.example.shopr.domainenums.Auth;
import com.example.shopr.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public Auth findAuth(String user) {
        TypedQuery query = entityManager.createQuery("select u from User u where u.username = :user" , User.class);
        query.setParameter("user" , user);
        User users = (User) query.getSingleResult();
        return users.getAuth();
    }

    public String findPass(String user) {
        TypedQuery query = entityManager.createQuery("select u from User u where u.username = :user" , User.class);
        query.setParameter("user" , user);
        User users = (User) query.getSingleResult();
        return users.getPassword();
    }

    public Long findId(String user) {
        TypedQuery query = entityManager.createQuery("select u from User u where u.username = :user" , User.class);
        query.setParameter("user" , user);
        User users = (User) query.getSingleResult();
        return users.getId();
    }

    public User findByUserId(Long userId) {
        TypedQuery query = entityManager.createQuery("select u from User u where u.id = :id" , User.class);
        query.setParameter("id" , userId);
        User user  =  (User) query.getSingleResult();
        return user;
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateUser(User thisUser) {
        entityManager.merge(thisUser);
    }

}
