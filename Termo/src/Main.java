public class Main {
    
    public static void main(String[] args) {
        //Model
        ModelTermo model = new ModelTermo();

        //Controller
        Controller ctrl = new Controller(model);

        //View
        JFrameTermo view = new JFrameTermo(ctrl);
    }
}
