package Tema1;

import java.util.ArrayList;
import java.util.Collections;

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
                        circ.listaVotanti.add(new Votant(nume, CNP, varsta, neindemanatic, false));
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

    public void printareCandidatiDinAlegeri(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    System.out.println("EROARE: Nu au inceput inca alegerile");
                    return ;
                }
                if (a.listaCandidati.size() == 0) {
                    System.out.println("GOL: Nu sunt candidati");
                    return ;
                } else {
                    System.out.println("Candidatii:\n");
                    for (Candidat candidat : a.listaCandidati) {
                        System.out.println(candidat.getNume() + " " + candidat.getCNP() + " " + candidat.getVarsta() + " \n");
                    }
                    return ;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return ;
    }

    public void printareVotantiDinCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                boolean gasitCirc = false;
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    System.out.println("EROARE: Nu au inceput inca alegerile");
                    return ;
                }
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    if (circ.getNume().trim().equals(numeCirc.trim()) == true) {
                        gasitCirc = true;
                        if (circ.listaVotanti.size() == 0) {
                            System.out.println("GOL: Nu sunt votanti in" + numeCirc);
                            return ;
                        } else {
                            System.out.println("Votantii din" + numeCirc + ":\n");
                            for (Votant votant : circ.listaVotanti) {
                                System.out.println(votant.getNume() + " " + votant.getCNP() + " " + votant.getVarsta() + " \n");
                            }
                            return ;
                        }
                    }
                }
                if (gasitCirc == false) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele" + numeCirc);
                    return ;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return ;
    }

    public String votare(ArrayList<Alegere> listaAlegeri, String id, String numeCirc, String CNP_votant, String CNP_candidat) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                boolean gasitCirc = false;
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    return "EROARE: Nu este perioada de votare";
                }
                boolean existentaVotant = false;
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    for (Votant votant : circ.listaVotanti) {
                        if (votant.getCNP().trim().equals(CNP_votant.trim()) == true) {
                            existentaVotant = true;
                            break;
                        }
                    }
                }

                if (existentaVotant == false) {
                    return "EROARE: Nu exista un votant cu CNP-ul " + CNP_votant;
                }

                for (Circumscriptie circ : a.listaCircumscriptii) {
                    if (circ.getNume().trim().equals(numeCirc.trim()) == true) {
                        gasitCirc = true;
                        boolean gasitVotant = false;
                        for (Votant votant : circ.listaVotanti) {
                            if (votant.getCNP().trim().equals(CNP_votant.trim()) == true) {
                                gasitVotant = true;
                                if (votant.getVotat() == true) {
                                    return "FRAUDA: Votantul cu CNP-ul " + CNP_votant + " a incercat sa comita o frauda. Votul a fost anulat.";
                                }
                                boolean gasitCandidat = false;
                                for (Candidat candidat : a.listaCandidati) {
                                    if (candidat.getCNP().trim().equals(CNP_candidat.trim()) == true) {
                                        gasitCandidat = true;
                                        votant.setVotat(true);
                                        if (votant.getNeindemanatic().equals("da") == true ) {
                                            candidat.crestereNrVoturi();
                                        }
                                       circ.listaCandidatiVotati.add(candidat);
                                        return votant.getNume() + " a votat pentru" + candidat.getNume();
                                    }
                                }

                                if (gasitCandidat == false) {
                                    return "EROARE: Nu exista un candidat cu CNP-ul" + CNP_candidat;
                                }
                            }
                        }

                        if (gasitVotant == false) {
                            return "FRAUDA: Votantul cu CNP-ul " + CNP_votant + " a incercat sa comita o frauda. Votul a fost anulat.";
                        }
                    }
                }

                if (gasitCirc == false) {
                    return "EROARE: Nu exista o circumscriptie cu numele " + numeCirc;
                }

            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String oprireAlegeri(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("IN_CURS") == false) {
                    return "EROARE: Nu este perioada de votare";
                }

                a.setCurent("TERMINAT");
                return "S-au terminat alegerile" + a.getNume();
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public void raportVoturi(ArrayList<Alegere> listaAlegeri, String id, String numeCirc) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {

                if (a.getCurent().equals("TERMINAT") == false) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return ;
                }

                boolean gasitCirc = false;
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    if (circ.getNume().trim().equals(numeCirc.trim()) == true) {
                        gasitCirc = true;
                        if (circ.listaCandidatiVotati.size() == 0) {
                            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in" + numeCirc);
                            return ;
                        }

                        for (Candidat candidat : circ.listaCandidatiVotati) {
                            boolean gasitCandidat = false;
                            for (Vot vot : circ.listaVoturi) {
                                if (candidat.getNume().trim().equals(vot.candidat.getNume().trim()) == true) {
                                    gasitCandidat = true;
                                    vot.adaugaVotCirc();
                                    break;
                                }
                            }
                            if (gasitCandidat == false) {
                                Vot votNou = new Vot(candidat);
                                circ.listaVoturi.add(votNou);
                                votNou.adaugaVotCirc();
                            }

                            Collections.sort(circ.listaVoturi);

                        }

                        System.out.println("Raport voturi" + numeCirc + ":");
                        for (Vot vot : circ.listaVoturi) {
                            System.out.println(vot.candidat.getNume().trim() + " " + vot.candidat.getCNP() + " - " + vot.getNrVoturiCirc());
                        }
                        return ;

                    }
                }

                if (gasitCirc == false) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele" + numeCirc);
                    return ;
                }

            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return ;
    }

    public void raportVoturiNationale(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("TERMINAT") == false) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return ;
                }
                int numarVoturi = 0;
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    for (Candidat candidat : circ.listaCandidatiVotati) {
                        numarVoturi++;
                    }
                }

                if (numarVoturi == 0) {
                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                    return ;
                }

                Collections.sort(a.listaCandidati);

                System.out.println("Raport voturi Romania:");
                for (Candidat candidat : a.listaCandidati) {
                    System.out.println(candidat.getNume().trim() + " " + candidat.getCNP() + " - " +candidat.getNrVoturi());
                }
                return ;


            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return ;
    }

}
