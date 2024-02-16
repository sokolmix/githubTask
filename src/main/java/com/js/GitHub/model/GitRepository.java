package com.js.GitHub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepository {
    private String name;
    private Owner owner;
    private boolean fork;



}
