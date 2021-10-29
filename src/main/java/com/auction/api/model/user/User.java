package com.auction.api.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class User {

    private final Long seq;

    private final String name;

    private final Email email;

    private String password;

    private final LocalDateTime createAt;

    public User(String name, Email email, String password) {
        this(null, name, email, password, null);
    }

    public User(Long seq, String name, Email email, String password, LocalDateTime createAt) {
        checkArgument(isNotEmpty(name), "name must be provided.");
        checkArgument(name.length() >= 1 && name.length() <= 10, "name length must be between 1 and 10 characters.");
        checkArgument(email != null, "email must be provided.");
        checkArgument(password != null, "password must be provided.");

        this.seq = seq;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(seq, user.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("seq", seq)
            .append("name", name)
            .append("email", email)
            .append("password", "[PROTECTED]")
            .append("createAt", createAt)
            .toString();
    }

    static public class Builder {
        private Long seq;
        private String name;
        private Email email;
        private String password;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder(User user) {
            this.seq = user.seq;
            this.name = user.name;
            this.email = user.email;
            this.password = user.password;
            this.createAt = user.createAt;
        }

        public Builder seq(Long seq){
            this.seq = seq;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(Email email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder createAt(LocalDateTime createAt){
            this.createAt = createAt;
            return this;
        }

        public User build(){
            return new User(seq, name, email, password, createAt);
        }
    }
}
