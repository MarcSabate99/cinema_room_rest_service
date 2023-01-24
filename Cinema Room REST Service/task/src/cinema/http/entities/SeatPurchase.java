package cinema.http.entities;

public class SeatPurchase {

    public int row;
    public int column;

    public int price;

    SeatPurchase() {}

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPrice() {
        return row <= 4 ? 10: 8;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
