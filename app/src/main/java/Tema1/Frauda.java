package Tema1;

/**
 * Clasa Frauda reprezinta o tentativa de frauda. Aceasta retine informatii
 * despre votantul implicat si circumscriptia in care s-a produs frauda.
 */

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
