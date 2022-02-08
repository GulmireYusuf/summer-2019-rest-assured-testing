package pojos;

import com.google.gson.annotations.SerializedName;

public class Contact {
    private int contactId;
    private String emailAddress;
    private String phone;

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", emailAddress='" + emailAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", premanentAddress='" + premanentAddress + '\'' +
                '}';
    }

    public Contact(int contactId, String emailAddress, String phone, String premanentAddress) {
        this.contactId = contactId;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.premanentAddress = premanentAddress;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPremanentAddress() {
        return premanentAddress;
    }

    public void setPremanentAddress(String premanentAddress) {
        this.premanentAddress = premanentAddress;
    }

    @SerializedName("premanentAddress")
    private String premanentAddress;
}
