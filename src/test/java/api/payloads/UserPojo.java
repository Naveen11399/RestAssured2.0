package api.payloads;

public class UserPojo {
    private String name;
    private String email;
    private String phoneNumber;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Add validation logic if needed
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Add validation logic to check email format if needed
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String string) {
        // Add validation logic to check phone number format if needed
        this.phoneNumber = string;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        // Add validation logic if needed
        this.message = message;
    }
}
