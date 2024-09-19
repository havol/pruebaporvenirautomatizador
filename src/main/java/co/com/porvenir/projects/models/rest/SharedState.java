package co.com.porvenir.projects.models.rest;

import lombok.Getter;
import lombok.Setter;

public class SharedState {

    @Getter
    @Setter
    private static int userId;

}