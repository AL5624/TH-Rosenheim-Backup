import java.util.Objects;

public class Customer {
  private int customerId;
  private String salutation;
  private String firstLastName;
  private String email;

  public Customer(String salutation, String firstLastName, String email) {
    this.salutation = salutation;
    this.firstLastName = firstLastName;
    this.email = email;
    customerId = hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return customerId == customer.customerId &&
        Objects.equals(salutation, customer.salutation) &&
        Objects.equals(firstLastName, customer.firstLastName) &&
        Objects.equals(email, customer.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, salutation, firstLastName, email);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerId=" + customerId +
        ", salutation='" + salutation + '\'' +
        ", firstLastName='" + firstLastName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public int getCustomerId() {
    return customerId;
  }

  public String getSalutation() {
    return salutation;
  }

  public String getFirstLastName() {
    return firstLastName;
  }

  public String getEmail() {
    return email;
  }
}
