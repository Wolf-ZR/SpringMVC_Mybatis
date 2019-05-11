package com.ssm.po;

import java.util.List;

public class ItemsCustom extends Items{
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
