package com.zamoiski.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DNameTitle implements Serializable {
    private String title;
    private String name;

    public DNameTitle(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
