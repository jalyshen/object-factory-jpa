package com.jaly.ddd.object_factory_jpa.biz;

import com.jaly.ddd.object_factory_jpa.db.jpa.JPAUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static com.jaly.ddd.object_factory_jpa.db.jpa.TransactionUtils.Lookup;
import static com.jaly.ddd.object_factory_jpa.db.jpa.JPAUtil.Equals;

@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

    private String email;

    private String password;

    public User() {}

    public User(long id, String email, String password) {
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
    }

    public static final Lookup<User, Long> BY_ID = new Equals<>(User.class, "id");

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
