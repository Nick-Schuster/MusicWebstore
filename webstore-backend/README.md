# Music Webstore Backend – Spring Boot + Kotlin

Anleitung für Backend für einen Musik-Webstore. Es stellt eine REST-API bereit zur Verwaltung von Produkten und Produktbildern.  


---

## Schnellstart (lokal)

### Voraussetzungen

- Java 17+
- Internetverbindung (für Gradle-Wrapper)
- Kein manuelles Gradle nötig

### Projekt starten

```bash
# Repository klonen
git clone https://github.com/Nick-Schuster/MusicWebstore.git
cd MusicWebstore/webstore-backend

# Backend bauen
./gradlew bootRun

# Backend starten
./gradlew bootRun 
 ```


## Produkt-API Endpunkte

(bis jetzt wird die Produkt-ID automatisch generiert und zählt von 1 ab hoch)

---

GET	/api/products	Paginierte Produktliste  
GET	/api/products/all	Alle Produkte ohne Pagination  
GET	/api/products/{id}	Einzelnes Produkt nach ID  
GET	/api/products/search?name=xyz	Produktsuche per Name (nicht pag.)  
POST	/api/products	Neues Produkt anlegen  
PUT	/api/products/{id}	Produkt aktualisieren  
DELETE	/api/products/{id}	Produkt löschen  




---

### GET /api/products
Paginierte Produktliste (mit page, size, sort)

GET http://localhost:8080/api/products?page=0&size=10&sort=price,asc

---

### GET /api/products/all
Alle Produkte ohne Pagination

GET http://localhost:8080/api/products/all

---

### GET /api/products/search?name=xyz
Produktsuche nach Name (nicht paginiert)

GET http://localhost:8080/api/products/search?name=gitarre

---

### GET /api/products/{id}
Einzelnes Produkt nach ID

GET http://localhost:8080/api/products/1

---

### POST /api/products
Neues Produkt anlegen

POST http://localhost:8080/api/products

**Body:**
```json
{
  "name": "Fender Stratocaster",
  "description": "Klassische E-Gitarre",
  "price": 1299.00,
  "inStock": true
}
```

---

### PUT /api/products/{id}
Produkt aktualisieren

PUT http://localhost:8080/api/products/1
Content-Type: application/json

---

### DELETE /api/products/{id}
Produkt löschen

DELETE http://localhost:8080/api/products/1


---

## Produktbilder-API Endpunkte

### GET /api/products/{productId}/images
Bilder eines Produkts abrufen

GET http://localhost:8080/api/products/1/images


---

### POST /api/products/{productId}/images
Bild zu einem Produkt hinzufügen

POST http://localhost:8080/api/products/1/images
Content-Type: application/json

{
"imageUrl": "https://example.com/gitarre.jpg"
}

---

### DELETE /api/products/{productId}/images/{imageId}
Bild eines Produkts löschen

DELETE http://localhost:8080/api/products/1/images/5

 
