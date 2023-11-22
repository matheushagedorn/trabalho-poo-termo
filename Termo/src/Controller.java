public class Controller {

    ModelTermo model;
    
    public Controller(ModelTermo model) {
        this.model = model;
    }

    public void verificaPalavra(int linha) {
        model.verificaPalavra(linha);
    }

    public boolean verificaFinalizado(int linha) {
        return model.verificaFinalizado(linha);
    }

    public boolean isGanhou() {
        return model.isGanhou();
    }
}
