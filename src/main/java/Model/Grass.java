package Model;

public class Grass {

    private double quantity;
    private final int weight = 1;
    private final int maxQuantityByCell = 200;

    public Grass(double quantity) {
        this.quantity = quantity < 0 ? 0 : Math.min(quantity, 200);
    }

    public double getQuantity() { return quantity; }

    /**Метод уменьшения количества травы
     * @param quantity Количество съедаемой травы,
     * @return количество реально съеденной травы
     * */
    public double minusGrass(double quantity){

        if (quantity <= 0) return 0;
        if (this.quantity - quantity < 0){
            double current = this.quantity;
            this.quantity = 0;
            return current;
        }
        this.quantity -= quantity;
        return quantity;

    }

    public void grow(){
        this.quantity = 100;
    }
    public int getWeight() {
        return weight;
    }

    public int getMaxQuantityByCell() {
        return maxQuantityByCell;
    }

    @Override
    public String toString() {
        return "\uD83C\uDF3Fx" +  String.format("%.2f",quantity);
    }
}
