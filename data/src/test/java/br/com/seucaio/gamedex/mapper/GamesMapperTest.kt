package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.mapper.GamesMapper.toDomain
import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.game.GameScreenshotResponse
import br.com.seucaio.gamedex.remote.dto.game.PlatformInfoDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.StoreInfoDetailResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GamesMapperTest {

    // region GameItem (list)

    @Test
    fun `should map GameItemResponse to GameItem`() {
        // Given
        val item = GameItemResponse(
            id = 1,
            name = "Game 1",
            imageBackground = "bg.png",
            metacritic = 88,
            released = "2020-01-01",
            rating = 4.5,
            platforms = listOf(
                GameItemResponse.PlatformInfoResponse(
                    info = GameDataListInfoGamesResponse(id = 10, name = "PC")
                )
            ),
            stores = listOf(
                GameItemResponse.StoreInfoResponse(
                    info = GameDataListInfoGamesResponse(id = 20, name = "Steam")
                )
            ),
            genres = listOf(
                GameDataListInfoGamesResponse(id = 30, name = "Action")
            ),
            esrbRating = GameDataListInfoGamesResponse(id = 40, name = "Teen"),
            shortScreenshots = listOf(
                GameScreenshotResponse(id = 100, image = "shot1.jpg"),
                GameScreenshotResponse(id = 101, image = "shot2.jpg")
            )
        )

        // When
        val domain: GameItem = item.toDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Game 1", domain.name)
        assertEquals("bg.png", domain.imageBackground)
        assertEquals(88, domain.metacritic)
        assertEquals("2020-01-01", domain.released)
        assertEquals(4.5, domain.rating, 0.0)
        assertEquals(1, domain.platforms.size)
        assertEquals(10, domain.platforms[0].id)
        assertEquals("PC", domain.platforms[0].name)
        assertEquals(1, domain.stores.size)
        assertEquals(20, domain.stores[0].id)
        assertEquals("Steam", domain.stores[0].name)
        assertEquals(1, domain.genres.size)
        assertEquals(30, domain.genres[0].id)
        assertEquals("Action", domain.genres[0].name)
        assertEquals(40, domain.esrbRating?.id)
        assertEquals("Teen", domain.esrbRating?.name)
        assertEquals(2, domain.shortScreenshots.size)
        assertEquals(100, domain.shortScreenshots[0].id)
        assertEquals("shot1.jpg", domain.shortScreenshots[0].image)
    }

    @Test
    fun `should map list of GameItemResponse to list of GameItem`() {
        // Given
        val list = listOf(
            GameItemResponse(id = 1, name = "G1"),
            GameItemResponse(id = 2, name = "G2")
        )

        // When
        val domainList = list.toDomain()

        // Then
        assertEquals(2, domainList.size)
        assertEquals(1, domainList[0].id)
        assertEquals("G1", domainList[0].name)
        assertEquals(2, domainList[1].id)
        assertEquals("G2", domainList[1].name)
    }

    @Test
    fun `should handle nulls in GameItemResponse mapping with defaults`() {
        // Given: most nullable fields are null in DTO
        val item = GameItemResponse(
            id = 3,
            name = "Nulls Game"
        )

        // When
        val domain = item.toDomain()

        // Then: defaults applied
        assertEquals(3, domain.id)
        assertEquals("Nulls Game", domain.name)
        assertEquals("", domain.imageBackground)
        assertEquals(0, domain.metacritic)
        assertEquals("", domain.released)
        assertEquals(0.0, domain.rating, 0.0)
        assertEquals(0, domain.platforms.size)
        assertEquals(0, domain.stores.size)
        assertEquals(0, domain.genres.size)
        assertNull(domain.esrbRating)
        assertEquals(0, domain.shortScreenshots.size)
    }

    // endregion

    // region GameDetail

    @Test
    fun `should map GameDetailResponse to GameDetail with nested info`() {
        // Given
        val detailResponse = GameDetailResponse(
            id = 99,
            name = "Epic Game",
            imageBackground = "bg.jpg",
            imageBackgroundAdditional = "bg2.jpg",
            description = "Desc",
            metacritic = 91,
            released = "2021-05-05",
            website = "https://game.example",
            rating = 4.8,
            alternativeNames = listOf("E1", "E2"),
            platforms = listOf(
                PlatformInfoDetailResponse(
                    info = GameDataListInfoResponse(
                        id = 1,
                        name = "PC",
                        imageBackground = null,
                        gamesCount = 5000
                    ),
                    releasedAt = "2021-05-05",
                    requirements = PlatformInfoDetailResponse.PlatformRequirementsResponse(
                        minimum = "min req",
                        recommended = "rec req"
                    )
                )
            ),
            stores = listOf(
                StoreInfoDetailResponse(
                    id = 7,
                    info = StoreInfoDetailResponse.StoreItemResponse(
                        id = 70,
                        name = "Steam",
                        domain = "store.steampowered.com",
                        gamesCount = 10000,
                        imageBackground = "store.jpg"
                    )
                )
            ),
            developers = listOf(
                GameDataListInfoResponse(id = 5, name = "Dev A", imageBackground = "dev.jpg", gamesCount = 10)
            ),
            publishers = listOf(
                GameDataListInfoResponse(id = 6, name = "Pub A", gamesCount = 0, imageBackground = null)
            ),
            genres = listOf(
                GameDataListInfoResponse(id = 8, name = "Adventure", imageBackground = null, gamesCount = 123)
            ),
            esrbRating = GameDataListInfoGamesResponse(id = 4, name = "Mature")
        )

        // When
        val domain: GameDetail = detailResponse.toDomain()

        // Then
        assertEquals(99, domain.id)
        assertEquals("Epic Game", domain.name)
        assertEquals("bg.jpg", domain.imageBackground)
        assertEquals("bg2.jpg", domain.imageBackgroundAdditional)
        assertEquals("Desc", domain.description)
        assertEquals(91, domain.metacritic)
        assertEquals("2021-05-05", domain.released)
        assertEquals("https://game.example", domain.website)
        assertEquals(4.8, domain.rating, 0.0)
        assertEquals(listOf("E1", "E2"), domain.alternativeNames)

        // platformsInfo
        assertEquals(1, domain.platformsInfo.size)
        val p = domain.platformsInfo[0]
        assertEquals(1, p.platformItem.id)
        assertEquals("PC", p.platformItem.name)
        assertEquals(5000, p.platformItem.gamesCount)
        assertEquals("2021-05-05", p.releasedAt)
        assertEquals("min req", p.requirements?.minimum)
        assertEquals("rec req", p.requirements?.recommended)

        // storesInfo
        assertEquals(1, domain.storesInfo.size)
        val s = domain.storesInfo[0]
        assertEquals(7, s.id)
        assertEquals(70, s.storeItem.id)
        assertEquals("Steam", s.storeItem.name)
        assertEquals("store.steampowered.com", s.storeItem.domain)
        assertEquals(10000, s.storeItem.gamesCount)
        assertEquals("store.jpg", s.storeItem.imageBackground)

        // developers/publishers/genres
        assertEquals(1, domain.developersInfo.size)
        assertEquals(5, domain.developersInfo[0].id)
        assertEquals("Dev A", domain.developersInfo[0].name)
        assertEquals("dev.jpg", domain.developersInfo[0].imageBackground)
        assertEquals(10, domain.developersInfo[0].gamesCount)

        assertEquals(1, domain.publishersInfo.size)
        assertEquals(6, domain.publishersInfo[0].id)
        assertEquals("Pub A", domain.publishersInfo[0].name)
        // nulls should default
        assertEquals("", domain.publishersInfo[0].imageBackground)
        assertEquals(0, domain.publishersInfo[0].gamesCount)

        assertEquals(1, domain.genresInfo.size)
        assertEquals(8, domain.genresInfo[0].id)
        assertEquals("Adventure", domain.genresInfo[0].name)
        assertEquals("", domain.genresInfo[0].imageBackground)
        assertEquals("", domain.genresInfo[0].description)
        assertEquals(123, domain.genresInfo[0].gamesCount)

        // esrb
        assertEquals(4, domain.esrbRatingInfo?.id)
        assertEquals("Mature", domain.esrbRatingInfo?.name)
    }

    @Test
    fun `should handle nulls in GameDetailResponse mapping with defaults`() {
        // Given: only required fields
        val detailResponse = GameDetailResponse(
            id = 1,
            name = "Simple",
            description = "D"
        )

        // When
        val domain = detailResponse.toDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Simple", domain.name)
        assertEquals("", domain.imageBackground)
        assertEquals("", domain.imageBackgroundAdditional)
        assertEquals("D", domain.description)
        assertEquals(0, domain.metacritic)
        assertEquals("", domain.released)
        assertEquals("", domain.website)
        assertEquals(0.0, domain.rating, 0.0)
        assertEquals(emptyList<String>(), domain.alternativeNames)
        assertEquals(0, domain.platformsInfo.size)
        assertEquals(0, domain.storesInfo.size)
        assertEquals(0, domain.developersInfo.size)
        assertEquals(0, domain.publishersInfo.size)
        assertEquals(0, domain.genresInfo.size)
        assertNull(domain.esrbRatingInfo)
    }

    // endregion
}
