import java.time.LocalDate;
import java.util.Objects;

public class Order {
  private int oderId;
  private int amount;
  private LocalDate createDate;
  private String approvedBy;
  private Customer customer;

  public Order(int amount, Customer customer) {
    this.amount = amount;
    this.customer = customer;
    createDate = LocalDate.now();
    oderId = hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return oderId == order.oderId &&
        amount == order.amount &&
        Objects.equals(createDate, order.createDate) &&
        Objects.equals(approvedBy, order.approvedBy) &&
        Objects.equals(customer, order.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, createDate, approvedBy, customer);
  }

  @Override
  public String toString() {
    return "Order{" +
        "oderId=" + oderId +
        ", amount=" + amount +
        ", createDate=" + createDate +
        ", approvedBy='" + approvedBy + '\'' +
        ", customer=" + customer +
        '}';
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public int getOderId() {
    return oderId;
  }

  public int getAmount() {
    return amount;
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public Customer getCustomer() {
    return customer;
  }
}
