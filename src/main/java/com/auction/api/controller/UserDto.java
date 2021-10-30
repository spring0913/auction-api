package com.auction.api.controller;

import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class UserDto {

    private Long seq;

    private String name;

    private Email email;

    private LocalDateTime createAt;

    public UserDto(User source) {
        copyProperties(source, this);
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email.getAddress();
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("seq", seq)
            .append("name", name)
            .append("email", email)
            .append("createAt", createAt)
            .toString();
    }
}
