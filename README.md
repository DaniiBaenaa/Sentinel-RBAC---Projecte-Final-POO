# Sentinel-RBAC---Projecte-Final-POO

## Nota sobre l'ús d'IA
Hem utilitzat IA de manera puntual per tirar endavant el projecte. La veritat és que anàvem una mica justos de temps i se'ns encallaven algunes parts del codi (sobretot errors d'execució que no vèiem). La IA ens ha servit per fer de "debugger" quan ens bloquejàvem i per ajudar-nos a estructurar millor algunes classes, però la lògica la teníem clara nosaltres.

## 1. Descripció del projecte
### a. Què fa l’aplicació
Bàsicament, l'aplicació és un simulador de control d'accés basat en rols. Permet que diferents usuaris entrin al sistema i intentin accedir a arxius. Depenent del rol que tingui l'usuari (si és administrador, empleat o convidat), el sistema li deixa passar o no. També mira des de quina IP es connecten. Tot el que passa queda guardat en un fitxer de text per tenir un registre.

### b. Objectiu principal
La idea era muntar un sistema de seguretat que demostri que sabem fer servir Objectes, Herència i patrons de disseny. L'objectiu no és fer una app comercial, sinó demostrar que sabem protegir l'accés a dades sensibles i tenir el codi ben organitzat.

## 2. Com executar el projecte
### a. Requisits
Necessites Java, minim la 11.
El Visual Studio Code

### b. Passos bàsics
1.  Obre el projecte i assegura't que les carpetes estan com al repositori.
2. Executa amb el main
3.  Per provar-ho, pots fer servir aquests usuaris que ja estan programats:
    * Admin: usuari `admin` / contrasenya `admin123`
    * Empleat: usuari `employee` / contrasenya `employee123`
    * Convidat: usuari `guest` / contrasenya `guest123`

## 3. Arquitectura
### a. Explicació de l’MVC
Hem separat el codi seguint el model MVC perquè no estigui tot barrejat:

Model: Són les classes que guarden dades, com l'Usuari o el Recurs.
Vista: La classe `ConsoleView` és l'única que "parla" amb l'usuari.
Controlador: Tenim el `RBACSystem` i l'`AccessController`, que són els que decideixen què passa quan l'usuari demana alguna cosa.

### b. Estructura de paquets
Ho tenim organitzat així:
* controller: El cervell del sistema.
* model: Les dades (usuaris, arxius...).
* policy: Les regles de seguretat (com la de revisar la IP).
* service: Eines extra com el Log o el verificador de contrasenyes.
* view: Els menús de la consola.

## 4. Funcionalitats principals

1.  Login: Et demana usuari i contrasenya i comprova el hash.
2.  Rols:
    * L'Admin ho pot veure tot.
    * L'Employee veu coses públiques i internes.
    * El Guest només veu coses públiques.
3.  Control d'IP: Encara que tinguis permís, si la teva IP no és de la llista (com `127.0.0.1`), no entres.
4.  Logs: Tot queda escrit al fitxer `audit.log` automàticament.
5.  Veure el Log: Si ets admin, pots llegir el log directament des de la consola sense obrir el fitxer.

## 5. Ús d’IA (Detall)

Com dèiem al principi, hem fet servir la IA com a suport quan ens quedàvem encallats. Concretament:
* Solucionar errors: Quan sortia una excepció rara amb els fitxers (`AuditLogger`), la IA ens va ajudar a veure on fallava.
* Revisió: Ens ha anat bé per confirmar que estàvem aplicant bé la lògica dels rols.

## 6. Limitacions i millores futures

Sabem que el projecte té algunes coses a millorar:
* No hi ha Base de Dades: Si tanques el programa, es perden els canvis (tot està en memòria). Caldria posar un MySQL.
* La IP és simulada: Com que ho executem en local, et demanem que escriguis la IP a mà per provar si funciona el bloqueig. En la vida real això s'hauria de detectar sol.
* Gestió d'usuaris: No pots crear usuaris nous des del menú, estan fixos al codi. Estaria bé poder fer un "Sign up".
