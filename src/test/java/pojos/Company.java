package pojos;

public class Company {

    private int companyId;
    private String companyName;
    private String startDate;
    private String title;
    private Address address;

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", title='" + title + '\'' +
                ", address=" + address +
                '}';
    }



    public Company( String companyName, String startDate, String title, Address address) {
        this.companyName = companyName;
        this.startDate = startDate;
        this.title = title;
        this.address = address;
    }



    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
