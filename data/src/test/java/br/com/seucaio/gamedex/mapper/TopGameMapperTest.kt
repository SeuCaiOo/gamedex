package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import br.com.seucaio.gamedex.mapper.TopGameMapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class TopGameMapperTest {

    @Test
    fun `should map TopGameEntity to TopGameData`() {
        // Given
        val entity = TopGameEntity(id = 1, gameId = 10, name = "Top Game 1", platformId = 100)

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(10, domain.id) // Asserting gameId is used for id
        assertEquals("Top Game 1", domain.name)
    }

    @Test
    fun `should map list of TopGameEntity to list of TopGameData`() {
        // Given
        val entityList = listOf(
            TopGameEntity(id = 1, gameId = 10, name = "Top Game 1", platformId = 100),
            TopGameEntity(id = 2, gameId = 20, name = "Top Game 2", platformId = 100)
        )

        // When
        val domainList = entityList.toDomain()

        // Then
        assertEquals(2, domainList.size)
        assertEquals(10, domainList[0].id)
        assertEquals("Top Game 1", domainList[0].name)
        assertEquals(20, domainList[1].id)
        assertEquals("Top Game 2", domainList[1].name)
    }
}