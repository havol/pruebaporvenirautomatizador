package co.com.porvenir.projects.utils;

public enum RestService {

    LIST_USERS("/api/users?page=2"),
    CREATE_USER("/api/users"),
    UPDATE_USER("/api/users/{id}"),
    DELETE_USER("/api/users/{id}");

    private String uri;

    RestService(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }

}