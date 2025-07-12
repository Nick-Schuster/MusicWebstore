package com.example.webstorebackend.config

import com.example.webstorebackend.product.dto.ProductImageRequestDTO
import com.example.webstorebackend.product.dto.ProductRequestDTO
import com.example.webstorebackend.product.dto.ProductReviewRequestDTO
import com.example.webstorebackend.product.service.ProductImageService
import com.example.webstorebackend.product.service.ProductReviewService
import com.example.webstorebackend.product.service.ProductService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class TestDataLoader(
    private val productService: ProductService,
    private val productImageService: ProductImageService,
    private val productReviewService: ProductReviewService
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        if (productService.getAllProductsUnpaged().isNotEmpty()) return

        val products = listOf(
            Triple(
                ProductRequestDTO("Fender Stratocaster", "E-Gitarre", BigDecimal("1299.00")),
                "https://example.com/images/stratocaster.jpg",
                ProductReviewRequestDTO(5, "Fantastisches Instrument!")
            ),
            Triple(
                ProductRequestDTO("Gibson Les Paul", "E-Gitarre", BigDecimal("2499.00")),
                "https://example.com/images/lespaul.jpg",
                ProductReviewRequestDTO(4, "Super Sound, aber recht teuer.")
            ),
            Triple(
                ProductRequestDTO("Yamaha Pacifica", "E-Gitarre", BigDecimal("379.00")),
                "https://example.com/images/pacifica.jpg",
                ProductReviewRequestDTO(4, "Preis-Leistung top!")
            ),
            Triple(
                ProductRequestDTO("Shure SM58", "Mikrofon", BigDecimal("115.00")),
                "https://example.com/images/sm58.jpg",
                ProductReviewRequestDTO(5, "Robust und bewährt – Standard!")
            ),
            Triple(
                ProductRequestDTO("Roland SPD-SX", "Sampling Pad", BigDecimal("799.00")),
                "https://example.com/images/spdsx.jpg",
                ProductReviewRequestDTO(5, "Live-Performance-Maschine!")
            ),
            Triple(
                ProductRequestDTO("Marshall DSL20HR", "Röhrenverstärker", BigDecimal("649.00")),
                "https://example.com/images/marshall.jpg",
                ProductReviewRequestDTO(4, "Klassischer Sound, gute Verarbeitung.")
            ),
            Triple(
                ProductRequestDTO("Korg TM-60", "Stimmgerät", BigDecimal("29.90")),
                "https://example.com/images/tm60.jpg",
                ProductReviewRequestDTO(3, "Tut, was es soll.")
            ),
            Triple(
                ProductRequestDTO("Boss RC-505", "Looper", BigDecimal("499.00")),
                "https://example.com/images/rc505.jpg",
                ProductReviewRequestDTO(5, "Ideal für Solo-Artists.")
            )
        )

        products.forEach { (productDTO, imageUrl, reviewDTO) ->
            val saved = productService.createProduct(productDTO)
            productImageService.addImage(saved.id, ProductImageRequestDTO(imageUrl))
            productReviewService.addReview(saved.id, reviewDTO)
        }

        println("Testdaten erfolgreich geladen.")
    }
}
