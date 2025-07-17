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
            ),
    Triple(
        ProductRequestDTO("AKG K240 Studio", "Kopfhörer", BigDecimal("69.00")),
        "https://www.akg.com/on/demandware.static/-/Sites-akg-master/default/dw61db5b88/images/AKG_K240_Studio_x2.jpg",
        ProductReviewRequestDTO(4, "Klarer Sound und bequemes Design.")
    ),
    Triple(
        ProductRequestDTO("Focusrite Scarlett 2i2", "Audio Interface", BigDecimal("169.00")),
        "https://cdn.mos.cms.futurecdn.net/XVgzf7ARkZ5WvTcvEuvBcE.jpg",
        ProductReviewRequestDTO(5, "Unverzichtbar fürs Home-Studio.")
    ),
    Triple(
        ProductRequestDTO("M-Audio Keystation 49 MK3", "MIDI-Keyboard", BigDecimal("99.00")),
        "https://m.media-amazon.com/images/I/71PiOUt7NlL._AC_SL1500_.jpg",
        ProductReviewRequestDTO(4, "Einfach, funktional, günstig.")
    ),
    Triple(
        ProductRequestDTO("Zildjian A Custom Crash 16\"", "Becken", BigDecimal("239.00")),
        "https://media.guitarcenter.com/is/image/MMGS7/A-Custom-Crash-Cymbal-16-in/449938000000000-00-720x720.jpg",
        ProductReviewRequestDTO(5, "Brillanter Klang!")
    ),
    Triple(
        ProductRequestDTO("Line 6 Helix LT", "Multi-Effektgerät", BigDecimal("1099.00")),
        "https://www.gak.co.uk/blog/wp-content/uploads/2020/06/line-6-helix-lt.jpg",
        ProductReviewRequestDTO(5, "Soundvielfalt ohne Ende.")
    ),
    Triple(
        ProductRequestDTO("Ibanez SR300E", "E-Bass", BigDecimal("429.00")),
        "https://images.musicstore.de/images/1280/ibanez-sr300e-candy-apple-matte_1_GIT0052787-000.jpg",
        ProductReviewRequestDTO(4, "Leicht, schnell und angenehm spielbar.")
    ),
    Triple(
        ProductRequestDTO("Zoom H6", "Mobiler Recorder", BigDecimal("359.00")),
        "https://zoomcorp.com/media/images/H6_img_main_201902_02.image.jpg",
        ProductReviewRequestDTO(5, "Flexibel und zuverlässig.")
    ),
    Triple(
        ProductRequestDTO("Behringer Xenyx Q802USB", "Mixer", BigDecimal("95.00")),
        "https://www.behringer.com/product/_editorImageTypes/MEDIUM_JPG/Behringer%20XENYX%20Q802USB-M.jpg",
        ProductReviewRequestDTO(4, "Viele Features für kleines Geld.")
    )

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
