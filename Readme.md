# Basketball Manager - Microservices App

Kompletna aplikacja webowa do zarządzania ligami koszykarskimi i ich drużynami. Projekt oparty na architekturze mikroserwisów z frontendem w Angularze, zintegrowany za pomocą Docker Compose.

---

##  Architektura Systemu

System składa się z czterech głównych modułów komunikujących się wewnątrz izolowanej sieci Dockerowej:

1.  **Frontend Service (Angular)**: Interfejs użytkownika serwowany przez serwer Nginx, który pełni również rolę **Reverse Proxy** (przekierowuje zapytania `/api` do Gatewaya).
2.  **Gateway Service (Spring Cloud Gateway)**: Centralny punkt wejścia dla zapytań API. Odpowiada za routing do odpowiednich mikrousług.
3.  **League Service**: Mikrousługa do zarządzania danymi lig koszykarskich.
4.  **Team Service**: Mikrousługa zarządzająca drużynami przypisanymi do konkretnych lig.

---

## Technologie

*   **Frontend**: Angular 18, RxJS, Change Detection (Manual/Async).
*   **Backend**: Java 21, Spring Boot 3.x, Spring Cloud Gateway.
*   **Baza danych**: H2 (In-memory).
*   **Konteneryzacja**: Docker, Docker Compose.
*   **Serwer WWW**: Nginx (z konfiguracją Reverse Proxy).

---

## Jak uruchomić projekt?

### 1. Wymagania
*   **Docker Desktop** zainstalowany i uruchomiony.
*   **Java 21** oraz **Maven**.
*   **Node.js** (bez dockera).

### 2. Budowanie aplikacji (Kompilacja)
Przed uruchomieniem kontenerów należy zbudować pliki binarne dla backendu oraz paczkę frontendu:

**Backend (JAR):**
W folderze głównym wykonaj poniższe komendy (lub użyj skryptu):
```bash
cd league-service && ./mvnw clean package -DskipTests
cd ../team-service && ./mvnw clean package -DskipTests
cd ../gateway-service && ./mvnw clean package -DskipTests
cd ..