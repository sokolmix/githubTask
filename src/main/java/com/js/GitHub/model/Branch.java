package com.js.GitHub.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {
    private String name;
    private Commit commit;
}
