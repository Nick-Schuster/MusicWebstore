# Music Webstore Backend – Spring Boot + Kotlin

Anleitung für Backend für einen Musik-Webstore. Es stellt eine REST-API bereit zur Verwaltung von Produkten und Produktbildern.

---

## Schnellstart (lokal)

### Voraussetzungen

- Java 17+
- Kein manuelles Gradle nötig
- PostgreSQL installieren Version 16.9

### Datenbank aufsetzen

1. bei PostrgreSQL:

Benutzername: postgres

Passwort: merken!

2. Terminal öffnen:

psql -U postgres

CREATE DATABASE webstoredb;

\q

### Projekt starten

```bash
# Repository klonen
git clone https://github.com/Nick-Schuster/MusicWebstore.git
cd MusicWebstore/webstore-backend

# Backend bauen
./gradlew build

# Backend starten
./gradlew bootRun
```

---

## Datenbank-Entities

### Product Entity

| Feld            | Typ                   | Beschreibung                          |
| --------------- | --------------------- | ------------------------------------- |
| `id`            | `Long`                | Primärschlüssel des Produkts          |
| `name`          | `String`              | Produktname (Pflichtfeld)             |
| `description`   | `String?`             | Optionale Produktbeschreibung         |
| `price`         | `BigDecimal`          | Preis des Produkts                    |
| `inStock`       | `Boolean`             | Gibt an, ob das Produkt verfügbar ist |
| `averageRating` | `Double`              | Durchschnitt aller Bewertungen (1–5)  |
| `images`        | `List<ProductImage>`  | Zugehörige Produktbilder              |
| `reviews`       | `List<ProductReview>` | Bewertungen mit Kommentaren           |

### Product-Image Entity

| Feld       | Typ       | Beschreibung               |
| ---------- | --------- | -------------------------- |
| `id`       | `Long`    | Primärschlüssel des Bildes |
| `imageUrl` | `String`  | URL des Bildes             |
| `product`  | `Product` | Zugehöriges Produkt        |

### Product-Review Entity

| Feld      | Typ       | Beschreibung                  |
| --------- | --------- | ----------------------------- |
| `id`      | `Long`    | Primärschlüssel der Bewertung |
| `rating`  | `Int`     | Bewertungswert (1 bis 5)      |
| `comment` | `String?` | Optionaler Freitext-Kommentar |
| `product` | `Product` | Zugehöriges Produkt           |

### User Entity

| Feld       | Typ       | Beschreibung |
| ---------- | --------- | -------- |
| `id`       | `Long`    | Primärschlüssel des Benutzers |
| `username` | `String`  | Eindeutiger Benutzername |
| `password` | `String`  | Passwort |
| `name`     | `String`  | Name des Benutzers |
| `admin`    | `Boolean` | Gibt an, ob der User Admin ist |
| `cart`     | `Cart?`   | Optional: Zugehöriger Warenkorb |

### Cart Entity

| Feld    | Typ              | Beschreibung                    |
| ------- | ---------------- | ------------------------------- |
| `id`    | `Long`           | Primärschlüssel des Warenkorbs  |
| `user`  | `User`           | Der zugeordnete Benutzer        |
| `items` | `List<CartItem>` | Liste der Produkte im Warenkorb |

### CartItem Entity

| Feld       | Typ       | Beschreibung                       |
| ---------- | --------- | ---------------------------------- |
| `id`       | `Long`    | Primärschlüssel des CartItems      |
| `cart`     | `Cart`    | Referenz auf zugehörigen Warenkorb |
| `product`  | `Product` | Referenz auf das Produkt           |
| `quantity` | `Int`     | Menge dieses Produkts im Warenkorb |

---

## Alle API Endpunkte auf einen Blick

### Product API

GET     /api/products         <----         Paginierte Produktliste\
GET     /api/products/all     <----         Alle Produkte ohne Pagination\
GET     /api/products/{id}    <----          Einzelnes Produkt nach ID\
GET     /api/products/search?name=xyz <----  Produktsuche per Name (nicht pag.)\
POST    /api/products             <----      Neues Produkt anlegen\
PUT     /api/products/{id}        <----      Produkt aktualisieren\
DELETE  /api/products/{id}        <----     Produkt löschen\
DELETE  /api/products/all         <----      Alle Produkte löschen (nur für Entwicklung)

---

### Product-Image API

GET     /api/products/{id}/images     <----  Alle Bilder zu einem Produkt abrufen\
POST    /api/products/{id}/images     <----  Bild zu Produkt hinzufügen\
DELETE  /api/products/{id}/images/{id}<----  Bild eines Produkts löschen

---

### Product-Rating API

GET     /api/products/{id}/reviews    <----  Alle Bewertungen zu einem Produkt abrufen\
POST    /api/products/{id}/reviews    <----  Neue Bewertung mit Kommentar hinzufügen

---

## User API

### POST /api/users

Neuen Benutzer anlegen

```http
POST http://localhost:8080/api/users
```

**Body:**

```json
{
  "username": "julia123",
  "password": "Passwort1!",
  "name": "Julia",
  "admin": false
}
```

### GET /api/users

Alle Benutzer abrufen

```http
GET http://localhost:8080/api/users
```

### GET /api/users/{id}

Einzelnen Benutzer nach ID abrufen

```http
GET http://localhost:8080/api/users/1
```

### GET /api/users/search?username=xyz

Benutzer per Benutzername suchen

```http
GET http://localhost:8080/api/users/search?username=julia123
```

### DELETE /api/users/{id}

Benutzer löschen

```http
DELETE http://localhost:8080/api/users/1
```

---

## Cart API

### GET /api/cart/{userId}

Warenkorb des Nutzers abrufen

```http
GET http://localhost:8080/api/cart/1
```

### POST /api/cart/{userId}

Produkt zum Warenkorb hinzufügen

```http
POST http://localhost:8080/api/cart/1
```

**Body:**

```json
{
  "productId": 2,
  "quantity": 1
}
```

### DELETE /api/cart/{userId}/{productId}

Produkt aus Warenkorb entfernen

```http
DELETE http://localhost:8080/api/cart/1/2
```

### POST /api/cart/{userId}/checkout

Warenkorb „bezahlen“ und leeren

```http
POST http://localhost:8080/api/cart/1/checkout
```