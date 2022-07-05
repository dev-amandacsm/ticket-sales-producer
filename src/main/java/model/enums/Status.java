package model.enums;

public enum Status {
    PENDING_PAYMENT(1),
    APPROVED(2),
    REJECTED(3);

    private int id;

    Status(int id) {
        this.id = id;
    }
}
