package partner.common.model;

public class Partner {
    private final String name;
    private final String email;
    private final String homePage;
    private final String password;

    private Partner(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.homePage = builder.homePage;
        this.password = builder.password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getPassword() {
        return password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String email;
        private String homePage;
        private String password;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withHomePage(String homePage) {
            this.homePage = homePage;
            return this;
        }

        public Partner build() {
            return new Partner(this);
        }
    }
}
