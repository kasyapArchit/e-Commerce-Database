import beans.Product;
import cresponse.Cresponse;
import view.View;
import view.customer.*;

public class Main {
    public static void main (String[] args) {

        View v = new ProductView();
        v.Display();
    }
}