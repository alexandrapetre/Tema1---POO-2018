
CLASA SUBSCRIPTIE
 -are variabila nume, variabila timestamp(pentru LRU), variabilele add_time si
 frequency(pentru LFU)
 -constructorul cu parametru nume, seteaza numele elementului adaugat
 - metoda abstracta scadere, va decrementa in functie de comenzi numarul
 de cereri ale elementului

CLASA FREE
  - seteaza apeland super(nume) numele noului element
  -metoda toString afiseaza atunci cand se da comanda get numele subscriptiei

CLASA BASIC
 -construcorul cu parametrii nume si numar_cereiri, seteaza numele, urcand in
lantul de mostenire pana la clasa Subscriptie si numarul de cereri basic
 -metoda scadere(), scade din numarul de cereri basic 1, pana la epuizarea
 cererilor, daca nu mai sunt se apeleaza super.scadere(), si elementul devine
 free
 -metoda toString este apelata dupa comanda get si afiseaza numele subscriptiei

CLASA PREMIUM
  - construcorul cu parametrii nume si numar_cereiri_premium, seteaza numele,
  urcand in lantul de mostenire  in clasa basic, free si apoi subscriptie,
  si seteaza numarul de cereri premium ale elementului
  -metoda scadere(), scade din numarul de cereri premium daca numarul este
  mai mare ca zero, si urca in lantul de mostenire daca este mai mic, iar elementul
  devine basic sau free in functie de numarul de cereri basic
  -metoda toString afiseaza numele elementului in functie de numarul de cereri
  premium.

CLASA FIFOCache
  -se creaza un ArrayList care reprezinta memoria Cache
  -variabila nr_max reprezina numarul maxim de elemente din memoria Cache
  -ArrayList-ul functioneaza ca o coada, se adauga la rand elementele in Cache
  in functie de numarul maxim, si se elemina primul element din ArrayList

CLASA LRUCache
  -se creeaza un ArrayList care reprezinta memoria Lru Cache care contine elemente
  de tip Subscriptie
  -atunci cand se adauga un element in memoria Cache Lru, se apeleaza metoda
  add(Subscriptie s), care adauga in ArrayList in functie de numarul maxim
  de elemente
  -la fiecare adaugare se seteaza timestamp-ul cu 0, daca se da comanda GET,
  iar elementul este deja in Cache, se reapeleaza metoda getTime(Subscriptie s)
  unde se seteaza cel mai recent element accesat cu valoarea 0
  -metoda remove, elimina elementul din Cache cu timestamp-ul cel mai mare

CLASA LFUCache
  -se construieste un ArrayList cu elemente de tip Subscriptie
  - la adaugarea unui element in Cache se apeleaza metoda set_time_add  si se
  seteaza frecventa elementului respectiv cu 0
  -atunci cand se da comanda GET, si elementul se afla in memoria cache se apeleaza
  metoda get_frequency care incrementeaza variabila frequency specifica fiecarui
  element de tip subscriptie
  -eliminarea din Cache se face cu ajutorul metodei remove(), si se elemina in functie
  de frecventa (cea ma mica este elminata), iar daca sunt 2 elemente cu acceasi
  frecventa se elemina elementul mai vechi, care a fost setat
  cu ajutorul metodei set_time_add.

CLASA MAIN
  - se citesc din fisier comenzile
  -pentru comanda ADD se adauga in memoria principala elementul respectiv in
  functie de subscriptia sa (daca e premium, basic sau free)
  -daca elementul exista deja in memoria principala, se suprascrie si se verifica
  daca elemenul nu este si in cache, daca se gaseste in cache se elimina
  -pentru comanda GET se cauta dupa numele elementul. Prima oara se cauta in
  memoria principala, apoi in memoria cache
  -daca elementul se afla in memoria principala dar nu si in cache atunci
  se adauga in cache
  - se returneaza 0, 1 sau 2 in functie de memoria in care se gaseste
