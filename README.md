#  Webstore für Musikequipment

Ein einfacher Webstore für Musikequipment.  
Das Projekt ist von **Kian, Nick und Jorden**.  
Wir waren leider nicht in der Lage, das Backend für Nutzer-Authentifizierung anzupassen – daher sind **Login und Registration unfertig**.

---

##  Voraussetzungen

- Es ist **JDK 17** notwendig
- Umgebungsvariable muss auf **JDK 17** gesetzt werden
- **Internetverbindung** (für Gradle-Wrapper)
- **Kein manuelles Gradle** nötig
- **PostgreSQL Version 16.9** installieren

---

##  Datenbank: Benutzer anlegen

- Benutzer: `webstoreuser`
- Passwort: `testing`

- In der SQL Shell mit dem PostgreSQL-Passwort einloggen

---

##  Backend starten

Terminal in der Datei `webstore-backend` öffnen und ausführen:
```bash
./gradlew build
./gradlew bootRun