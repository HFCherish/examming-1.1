package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class User implements Record {
    protected EntityId id;
    protected String name;

    private User() {}

    public User(String name) {
        this.id = new EntityId(IdGenerator.next());
        this.name = name;
    }

    public EntityId getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return null;
    }
}
