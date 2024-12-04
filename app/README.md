**VoteSmart - Platforma de Votare Online**

    Scopul acestui proiect este de a gestiona alegerile prin dezvoltarea unei
    platforme de vot online simplificata.

***Structura proiectului:***
 
* **Alegere**: Reprezinta un proces electoral specific, include informatii despre o singura alegere, 
cum ar fi identificatorul, numele si o stare curent (daca alegerea este in curs), dar si liste cu 
circumscritpiile, candidatii si fraudele aferente unei alegeri.
* **Circumscriptie**: Este o subdiviziune teritorial-administrativa destinata organizarii eficiente a 
alegerilor, iar aceasta clasa contine detalii despre numele ei, regiunea specifica dar si liste cu voturile
candidatii si votantii.
*  **Votant si Candidat:** Sunt clase ce extind clasa **Persoana** si reprezinta un individ ce participa la
vot si un individ care candideaza la alegeri.
* **Frauda:** Reprezinta o tentativa de frauda detectata.
* **Analiza:** Clasa ce ofera statistici si informatii detaliate
despre voturi la nivel de regiune.
*  **Vot:** Reprezinta un vot exprimat pentru un candidat.

**Functionalitati Actualizate pentru Sistemul Electoral:**

1. **Adaugare Votant:**  
   Permite inregistrarea unui nou votant intr-o circumscriptie specifica, cu validarea automata a CNP-ului si verificarea varstei minime pentru a putea vota.

2. **Listare Candidati:**  
   Ofera posibilitatea de a vizualiza toti candidatii inscrisi intr-un proces electoral, organizati pe circumscriptii.

3. **Listare Votanti:**  
   Prezinta o lista completa a votantilor inregistrati intr-o circumscriptie specificata, cu optiuni de filtrare si cautare.

4. **Proces de Votare:**  
   Gestioneaza intregul proces de vot, incluzand verificarea eligibilitatii votantilor, prevenirea votului multiplu si validarea voturilor exprimate.

5. **Oprire Alegeri:**  
   Incheie oficial procesul electoral pentru o anumita circumscriptie sau la nivel national, declansand generarea automata a rapoartelor.

6. **Rapoarte de Voturi:**  
   Creeaza rapoarte detaliate despre numarul de voturi, procentaje si distributii atat la nivel de circumscriptie, cat si national.

7. **Analiza Detaliata:**  
   Furnizeaza analize statistice avansate ale rezultatelor electorale, inclusiv tendinte si comparatii intre circumscriptii.

8. **Raport de Fraude:**  
   Listeaza toate tentativele de frauda detectate in timpul procesului electoral, cum ar fi incercarile de vot multiplu sau utilizarea CNP-urilor invalide.

9. **Stergere Alegeri:**  
   Permite stergerea completa a datelor referitoare la un proces electoral specific, inclusiv votanti, candidati si rezultate, cu optiuni de backup.

10. **Listare Alegeri:**  
    Afiseaza o evidenta a tuturor alegerilor gestionate de sistem, incluzand detalii precum datele, circumscriptiile si starea actuala (active, inchise).  