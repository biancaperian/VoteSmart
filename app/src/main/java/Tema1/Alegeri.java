package Tema1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clasa Alegeri gestioneaza functionalitatile principarle ale procesului
 * electoral.
 */

public class Alegeri {
    ArrayList<Alegere> listaAlegeri = new ArrayList<Alegere>();
    ArrayList<Analiza> listaAnaliza = new ArrayList<Analiza>();

    /**
     * Creeaza o noua alegere si daca aceasta nu exista deja o adauga
     * la lista de alegeri.
     * @param listaAlegeri - Lista de alegeri existente.
     * @param id - Identificatorul alegerii.
     * @param nume - numele alegerii.
     * @return Mesaj de succes sau eroare daca s-a adaugat sau nu alegerea.
     */
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

    /**
     * Verifica daca o anumita alegere este in stadiul "IN_CURS",
     * in caz contrar le porneste.
     * @param listaAlegeri - lista de alegeri existente.
     * @param id - Identificatorul alegerii.
     * @return Mesaj de succes sau de eroare.
     */
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

    /**
     * Adauga o circumscriptie, daca aceasta nu exista deja, la o alegere specificata.
     * @param listaAlegeri - Lista de alegeri existente.
     * @param id - Identificatorul alegerii in care vrem sa aduagam circumscriptia.
     * @param numeCirc - Numele circumscriptiei.
     * @param numeRegiune - Numele regiunii.
     * @return Mesaj de succes sau de eroare.
     */
    public String verificareIdAdaugareCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc, String numeRegiune) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                String rezultat = a.adaugareCircumscriptie(a, numeCirc, numeRegiune);
                return rezultat;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    /**
     * Elimina o circumscriptie, daca aceasta exista, dintr-o alegere specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii din care dorim sa stergem circumscriptia.
     * @param numeCirc - Numele circumscriptiei.
     * @return Mesaj de eroare sau de succes.
     */
    public String verificareIdEliminareCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                String rezultat = a.eliminareCircumscriptie(a, numeCirc);
                return rezultat;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    /**
     * Adauga un candidat la alegerile specificate.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii.
     * @param CNP - Codul numeric personalal candidatului.
     * @param varsta - Varsta candidatului.
     * @param nume - Numele candidatului.
     * @return Mesaj de succes sau de eroare.
     */
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

    /**
     * Elimina un candidat dintr-o alegere specifica.
     * @param listaAlegeri -Lista alegerilor existente.
     * @param id - Identificatorul alegerii.
     * @param CNP - Codul Numeric Personal al candidatului.
     * @return Un mesaj de eroare sau de succes.
     */
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

    /**
     * Adauga un nou votant la o alegere specificata.
     * @param listaAlegeri - Lista de alegeri existente.
     * @param id - Identificatorul alegerii la care dorim sa adaugam votantul.
     * @param numeCirc - Numele circumscriptiei unde vrem sa adaugam votantul.
     * @param CNP - Codul Numeric Personal al votantului.
     * @param varsta -  Varsta votantului.
     * @param neindemanatic - Indicator daca votantul este neindemanatic sau nu.
     * @param nume - Numele votantului.
     * @return Mesaj de erooare sau de succes.
     */
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

    /**
     * Afiseaza lista candidatilor dintr-o alegere specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii din care afisam lista candidatilor.
     */
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

    /**
     * Printeaza votantii dintr-o circumscriptie specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id -  Identificatorul alegerii cae contine circumscriptia dorita.
     * @param numeCirc - Numele circumscriptiei.
     */
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

    /**
     * Permite unui votant sa voteze pentru un candidat intr-o anumita circumscriptie.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id -  Identificatorul alegeroo in care se desfasoara votarea.
     * @param numeCirc - Numele circumscriptiei unde are loc votarea.
     * @param CNP_votant - Codul Numeric Personal al votantuluo care doreste sa voteze.
     * @param CNP_candidat - Codul Numeric Personal al canditatului pentru care voteaza
     * @return Mesaj de succes sau de eroare.
     */
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
                                   a.listaFraude.add(new Frauda(votant, circ));
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
                        for (Circumscriptie circ2 : a.listaCircumscriptii) {
                            for (Votant votant : circ2.listaVotanti) {
                                if (votant.getCNP().trim().equals(CNP_votant.trim()) == true) {
                                    if (gasitVotant == false) {
                                        a.listaFraude.add(new Frauda(votant, circ));
                                        return "FRAUDA: Votantul cu CNP-ul " + CNP_votant + " a incercat sa comita o frauda. Votul a fost anulat.";
                                    }
                                }
                            }
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

    /**
     * Opreste procesul de votare pentru o alegere specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii pe care ne dorim sa o oprim.
     * @return Mesaj de eroare sau de succes.
     */
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

    /**
     * Genereaza un raport al voturilor pentru o circumscriptie specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii care contine circumscriptia dorita.
     * @param numeCirc - Numele circumscriptiei
     */
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

    /**
     * Genereaza un raport al voturilor la nivel national pentru o alegere specificata.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii pentru care generam raportul national.
     */
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

    /**
     * Ofera o analiza detaliata a rezultatelor votarii intr-o circumscriptie specificata.
     * @param listaAlegeri - Lista alegerilor disponibile.
     * @param id - Identificatorul alegerii care contine circumsriptis dorita.
     * @param numeCirc - Numele circumscriptiei.
     * @return Mesaj cu detalii despre analiza efectuata sau eroare.
     */
    public String analizaDetaliataCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("TERMINAT") == false) {
                    return "EROARE: Inca nu s-a terminat votarea";
                }

                boolean gasitCirc = false;
                for (Circumscriptie circ : a.listaCircumscriptii) {
                    if (circ.getNume().trim().equals(numeCirc.trim())) {
                        gasitCirc = true;
                        if (circ.listaCandidatiVotati.size() == 0) {
                            return "GOL: Lumea nu isi exercita dreptul de vot in" + numeCirc;
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

                        int numarVoturiNationale = a.totalVoturiNationale();
                        int procentajVoturiCirc = (circ.listaCandidatiVotati.size() * 100) / numarVoturiNationale;
                        Collections.sort(circ.listaVoturi);
                        int procentajVoturiCandidat = (circ.listaVoturi.get(0).getNrVoturiCirc() * 100) / circ.listaCandidatiVotati.size();

                        return "In" + numeCirc + " au fost " + circ.listaCandidatiVotati.size() + " voturi din " + numarVoturiNationale + ". Adica " + procentajVoturiCirc + "%. Cele mai multe voturi au fost stranse de " + circ.listaVoturi.get(1).candidat.getCNP() + circ.listaVoturi.get(1).candidat.getNume() + ". Acestea constituie " + procentajVoturiCandidat + "% din voturile circumscriptiei.";

                    }
                }

                if (gasitCirc == false) {
                    return "EROARE: Nu exista o circumscriptie cu numele" + numeCirc;
                }
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    /**
     * Ofera o analiza detaliata a rezultatelor votarii la nivel national.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii pentru care se face analiza nationala.
     */
    public void analizaDetaliataPlanNational(ArrayList<Alegere> listaAlegeri, String id) {
       boolean gasitId = false;
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                gasitId = true;
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

                for (Circumscriptie circ : a.listaCircumscriptii) {
                    boolean gasitRegiune = false;
                    for (Analiza analiza : listaAnaliza) {
                        if (circ.getRegiune().equals(analiza.getNumeRegiune()) ==  true) {
                            gasitRegiune = true;
                            analiza.listaCandidatiRegiune.addAll(circ.listaCandidatiVotati);

                        }
                    }

                    if (gasitRegiune == false) {
                        Analiza analizaNoua = new Analiza(circ.getRegiune());
                        listaAnaliza.add(analizaNoua);
                        analizaNoua.listaCandidatiRegiune.addAll(circ.listaCandidatiVotati);
                    }
                }

                for (Analiza analiza : listaAnaliza) {
                    for (Candidat candidat : analiza.listaCandidatiRegiune) {
                        boolean gasitCandidat = false;
                        for (Vot vot : analiza.listaVoturi) {
                            if (candidat.getNume().trim().equals(vot.candidat.getNume().trim()) == true) {
                                gasitCandidat = true;
                                vot.adaugaVotCirc();
                                break;
                            }
                        }
                        if (gasitCandidat == false) {
                            Vot votNou = new Vot(candidat);
                            analiza.listaVoturi.add(votNou);
                            votNou.adaugaVotCirc();
                        }

                        Collections.sort(analiza.listaVoturi);
                    }
                }

                int nrVoturiRomania = 0;

                for (Analiza analiza : listaAnaliza) {
                    nrVoturiRomania = nrVoturiRomania + analiza.listaCandidatiRegiune.size();
                }

                System.out.println("In Romania au fost " + nrVoturiRomania + " voturi.");

                for (Analiza analiza : listaAnaliza) {
                    int procentajRegiune = 0;
                    int procentajCandidat = 0;
                    procentajRegiune = (analiza.listaCandidatiRegiune.size() * 100) / nrVoturiRomania;
                    procentajCandidat = (analiza.listaVoturi.get(1).getNrVoturiCirc() * 100) / analiza.listaCandidatiRegiune.size();
                    System.out.println("In " + analiza.getNumeRegiune() + " au fost " + analiza.listaCandidatiRegiune.size() + " voturi din " + nrVoturiRomania + ". Adica " + procentajRegiune + "%. Cele mai multe voturi au fost stranse de " + analiza.listaVoturi.get(1).candidat.getCNP()  +  analiza.listaVoturi.get(1).candidat.getNume() + ". Acestea constituie " + procentajCandidat + "% din voturile regiunii.");
                }


            }
        }
        if (gasitId == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
    }

    /**
     * Genereaza un raport al fraudelor comise in cadrul unei alegeri specificate.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii pentru care se genereaza raportul de fraude.
     */
    public void raportFraude(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.getId().equals(id)) {
                if (a.getCurent().equals("TERMINAT") == false) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return ;
                }

                if (a.listaFraude.size() == 0) {
                    System.out.println("GOL: Romanii sunt cinstiti");
                    return ;
                }

                System.out.println("Fraude comise:");
                for (int i = a.listaFraude.size() - 1; i > 0; i--) {
                    System.out.println("In " + a.listaFraude.get(i).circumscriptie.getNume() + ": " + a.listaFraude.get(i).votant.getCNP() +  a.listaFraude.get(i).votant.getNume());
                }

            }

        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return;
    }

    /**
     * Sterge o alegere specificata din lista de alegeri, daca aceasta exista.
     * @param listaAlegeri - Lista alegerilor existente.
     * @param id - Identificatorul alegerii cpe care ne dorim sa o stergem.
     */
    public void stergereAlegeri(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.getId().equals(id)) {
                System.out.println("S-au sters alegerile" + a.getNume());
                listaAlegeri.remove(a);
                return ;
            }
        }

        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return ;
    }

    /**
     * Afiseaza lista tuturor alegerilor existente.
     * @param listaAlegeri - Lista de alegeri disponibila.
     */
    public void listaAlegeri(ArrayList<Alegere> listaAlegeri) {
        if (listaAlegeri.size() == 0) {
            System.out.println("GOL: Nu sunt alegeri");
            return ;
        }

        System.out.println("Alegeri:");
        for (Alegere a : listaAlegeri) {
            System.out.println(a.getId() + a.getNume());
        }

    }
}
