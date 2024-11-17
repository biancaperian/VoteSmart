package Tema1;

public class Frauda {
    Votant  votant;
    Circumscriptie circumscriptie;

    Frauda() {
        votant = new Votant();
        circumscriptie = new Circumscriptie();
    }

    Frauda(Votant votant, Circumscriptie circumscriptie) {
        this.votant = votant;
        this.circumscriptie = circumscriptie;
    }


}
