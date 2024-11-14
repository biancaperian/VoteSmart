package Tema1;

import java.util.ArrayList;

public class Alegeri {
    ArrayList<Alegere> listaAlegeri = new ArrayList<Alegere>();
    

    public String adaugareAlegere(ArrayList<Alegere> listaAlegeri, String id, String nume) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                return "EROARE: Deja exista alegeri cu id " + id + "\n";
            }
        }
        Alegere alegere = new Alegere(id, nume, "NEINCEPUT");
        listaAlegeri.add(alegere);
        return "S-au creat alegerile" + nume + "\n";
    }

    public String verificarePornireAlegere(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    a.pornireAlegere();
                    return "Au pornit alegerile" + a.getNume();
                }
                else {
                    return "EROARE: Alegerile deja au inceput";
                }
            }
        }

        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String verificareIdAdaugareCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc, String numeRegiune) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                String rezultat = a.adaugareCircumscriptie(a, numeCirc, numeRegiune);
                return rezultat;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String verificareIdEliminareCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                String rezultat = a.eliminareCircumscriptie(a, numeCirc);
                return rezultat;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String adaugareCandidat (ArrayList<Alegere> listaAlegeri, String id, String CNP, int varsta, String nume) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                    if (a.getCurent().equals("NEINCEPUT") == true) {
                        return "EROARE: Nu este perioada de votare";
                    }
                    if (a.getCurent().equals("NEINCEPUT") == false) {
                        if (CNP.length() != 13) {
                            return "EROARE: CNP invalid";
                        }

                        if (varsta < 35) {
                            return "EROARE: Varsta invalida";
                        }

                        for (Candidat candidat : a.listaCandidati) {
                            if (candidat.getCNP().equals(CNP) == true) {
                                return "EROARE: Candidatul" + nume + " are deja acelasi CNP";
                            }
                        }
                        Candidat candidat = new Candidat(nume, CNP, varsta);
                        a.listaCandidati.add(candidat);
                    }
                    return "S-a adaugat candidatul" + nume;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String eliminareCandidat(ArrayList<Alegere> listaAlegeri, String id, String CNP) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    return "EROARE: Nu este perioada de votare";
                }
                if (a.getCurent().equals("NEINCEPUT") == false) {
                    for (Candidat candidat : a.listaCandidati) {
                        if (candidat.getCNP().equals(CNP) == true) {
                            a.listaCandidati.remove(candidat);
                            return "S-a sters candidatul" + candidat.getNume();
                        }
                    }
                    return "EROARE: Nu exista un candidat cu CNP-ul " + CNP + "\n";
                }
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }


    public String adaugareVotant(ArrayList<Alegere> listaAlegeri, String id, String numeCirc, String CNP, int varsta, String neindemanatic, String nume) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                boolean gasitCirc = false;
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    return "EROARE: Nu este perioada de votare";
                }
                if (CNP.length() != 13) {
                    return "EROARE: CNP invalid";
                }
                if (varsta < 18) {
                    return "EROARE: Varsta invalida";
                }
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    if (circ.getNume().equals(numeCirc) == true) {
                        gasitCirc = true;
                        for (Votant votant : circ.listaVotanti) {
                            if (votant.getCNP().equals(CNP) == true) {
                                return "EROARE: Votantul" + votant.getNume() + " are deja acelasi CNP";
                            }
                        }
                        circ.listaVotanti.add(new Votant(nume, CNP, varsta, neindemanatic));
                        return "S-a adaugat votantul" + nume;
                    }
                }
                if (gasitCirc == false) {
                    return "EROARE: Nu exista o circumscriptie cu numele " + numeCirc;
                }

            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

}
