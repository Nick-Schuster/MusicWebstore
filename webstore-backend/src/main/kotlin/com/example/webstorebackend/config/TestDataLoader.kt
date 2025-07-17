package com.example.webstorebackend.config

import com.example.webstorebackend.product.dto.*
import com.example.webstorebackend.product.service.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class TestDataLoader(
    private val productService: ProductService,
    private val productImageService: ProductImageService,
    private val productReviewService: ProductReviewService,
    private val userService: UserService,
    private val cartService: CartService // ⬅️ Neu hinzugefügt
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        if (productService.getAllProductsUnpaged().isNotEmpty()) return

        val products = listOf(
            Triple(
                ProductRequestDTO("Fender Stratocaster", "E-Gitarre", BigDecimal("1299.00")),
                "https://th.bing.com/th/id/R.527cee54a53bcaff4b6c77f18b25de4d?rik=XFQbhflsi895FQ&pid=ImgRaw&r=0",
                ProductReviewRequestDTO(5, "Fantastisches Instrument!")
            ),
            Triple(
                ProductRequestDTO("Gibson Les Paul", "E-Gitarre", BigDecimal("2499.00")),
                "https://th.bing.com/th/id/R.8c9c80e57f37d39bfa5179412bbc49ef?rik=3y53GvQcHU2xEQ&pid=ImgRaw&r=0",
                ProductReviewRequestDTO(4, "Super Sound, aber recht teuer.")
            ),
            Triple(
                ProductRequestDTO("Yamaha Pacifica", "E-Gitarre", BigDecimal("379.00")),
                "https://th.bing.com/th/id/R.85dfb89d9b2e113d2e589dc7ce57aec9?rik=%2fYap7115gfc2Vw&pid=ImgRaw&r=0",
                ProductReviewRequestDTO(4, "Preis-Leistung top!")
            ),
            Triple(
                ProductRequestDTO("Shure SM58", "Mikrofon", BigDecimal("115.00")),
                "https://muzikercdn.com/uploads/products/24/2459/main_3884e5e5.jpg",
                ProductReviewRequestDTO(5, "Robust und bewährt – Standard!")
            ),
            Triple(
                ProductRequestDTO("Roland SPD-SX", "Sampling Pad", BigDecimal("799.00")),
                "https://m.media-amazon.com/images/I/61m9U1M5H8L.jpg",
                ProductReviewRequestDTO(5, "Live-Performance-Maschine!")
            ),
            Triple(
                ProductRequestDTO("Marshall DSL20HR", "Röhrenverstärker", BigDecimal("649.00")),
                "https://www.chicagomusicexchange.com/cdn/shop/products/marshall-amps-guitar-heads-marshall-dsl20hr-2-channel-20-watt-guitar-amp-head-u3865086101-29219006414983.jpg?v=1651865367&width=1445",
                ProductReviewRequestDTO(4, "Klassischer Sound, gute Verarbeitung.")
            ),
            Triple(
                ProductRequestDTO("Korg TM-60", "Stimmgerät", BigDecimal("29.90")),
                "https://tse1.mm.bing.net/th/id/OIP.6cP-In_e0AtVW9AVWi3grgHaHa?r=0&rs=1&pid=ImgDetMain&o=7&rm=3",
                ProductReviewRequestDTO(3, "Tut, was es soll.")
            ),
            Triple(
                ProductRequestDTO("Boss RC-505", "Looper", BigDecimal("499.00")),
                "https://www.ikebe-gakki.com/blog/wp-content/uploads/2021/08/rc-505mkII_m.jpg",
                ProductReviewRequestDTO(5, "Ideal für Solo-Artists.")
            )
            Triple(
                ProductRequestDTO("Ibanez RG550", "E-Gitarre", BigDecimal("999.00")),
                "https://www.ibanez.com/common/product_artist_images/2022/RG550-RF_01.png",
                ProductReviewRequestDTO(4, "Schnelles Halsprofil – perfekt für Shredding!")
            ),
            Triple(
                ProductRequestDTO("Electro-Voice RE20", "Mikrofon", BigDecimal("459.00")),
                "https://th.bing.com/th/id/OIP.qfiDLkZTlNDbcvJ4piF6eAHaHa?pid=ImgDetMain",
                ProductReviewRequestDTO(5, "Studioqualität für Podcast und Broadcasting.")
            ),
            Triple(
                ProductRequestDTO("Line 6 Helix LT", "Multi-Effektgerät", BigDecimal("1199.00")),
                "https://line6.com/data/6/0a020a3df36d5f8a63da2820e/image.jpg",
                ProductReviewRequestDTO(5, "Unendliche Klangmöglichkeiten – ein Traum für Gitarristen.")
            ),
            Triple(
                ProductRequestDTO("AKAI MPD218", "MIDI Pad Controller", BigDecimal("119.00")),
                "https://www.akaipro.com/amfile/file/download/file/899/product/294/",
                ProductReviewRequestDTO(4, "Kompakt und direkt – tolle Pads für den Preis.")
            ),
            Triple(
                ProductRequestDTO("Behringer Xenyx Q802USB", "Audio Mixer", BigDecimal("99.00")),
                "https://th.bing.com/th/id/OIP.KNgQm4uPte5Xf6oBdIWoVAHaHa?pid=ImgDetMain",
                ProductReviewRequestDTO(4, "Einsteigerfreundlich mit USB-Interface.")
            ),
            Triple(
                ProductRequestDTO("Zoom H5", "Tragbarer Recorder", BigDecimal("299.00")),
                "https://th.bing.com/th/id/R.6b2257b218cd59765fa99634aa4ed88f?rik=UABW7CGyFi9jqA&pid=ImgRaw&r=0",
                ProductReviewRequestDTO(5, "Klarer Klang und flexible Aufnahmeoptionen.")
            ),
            Triple(
                ProductRequestDTO("Dunlop Cry Baby GCB95", "Wah-Pedal", BigDecimal("109.00")),
                "https://images.ctfassets.net/bs8r7a3m520u/3H4aFKx4QX2xaFyvwYkO2Q/9cb4502f071e336dbe2a7db981edba66/GCB95.png",
                ProductReviewRequestDTO(5, "Der Klassiker unter den Wah-Pedalen.")
            ),
            Triple(
                ProductRequestDTO("Focusrite Scarlett 2i2", "Audio Interface", BigDecimal("179.00")),
                "https://www.focusrite.com/-/media/focusrite/products/scarlett-2i2/scarlett-2i2-3rd-gen/scarlett2i2-3qtr.png",
                ProductReviewRequestDTO(5, "Plug and play für Homestudios – super Qualität.")
        )

        val admin = userService.createUser(
            UserRequestDTO("admin", "Admin123!", "Admin", true)
        )
        val julia = userService.createUser(
            UserRequestDTO("julia123", "Passwort1!", "Julia", false)
        )

        val createdProducts = mutableListOf<ProductResponseDTO>()

        products.forEach { (productDTO, imageUrl, reviewDTO) ->
            val saved = productService.createProduct(productDTO)
            createdProducts.add(saved)
            productImageService.addImage(saved.id, ProductImageRequestDTO(imageUrl))
            productReviewService.addReview(saved.id, reviewDTO)
        }

        // Beispiel: Julia bekommt automatisch zwei Produkte in den Warenkorb
        if (createdProducts.size >= 2) {
            cartService.addItemToCart(
                julia.id,
                CartItemDTO(productId = createdProducts[0].id, quantity = 1)
            )
            cartService.addItemToCart(
                julia.id,
                CartItemDTO(productId = createdProducts[1].id, quantity = 2)
            )
        }

        println("Testdaten erfolgreich geladen.")
    }
}
