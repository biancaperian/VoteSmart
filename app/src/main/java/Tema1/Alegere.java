package Tema1;

public class Alegere {
    private String id;
    private String nume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    Alegere() {
        this.id = null;
        this.nume = null;
    }

    Alegere(String id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int verificareId(String id) {
        if (id.equals(this.id)) {
            return 1; // id ul nu este unic -> nu adaugam
        }
        return 0; // id ul este unic -> adaugam
    }

}
