package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

@Suppress("Indentation")
data class GameDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val imageBackgroundAdditional: String = String.EMPTY,
    val description: String = String.EMPTY,
    val metacritic: Int = Int.ZERO,
    val released: String = String.EMPTY,
    val website: String = String.EMPTY,
    val rating: Double = Double.ZERO,
    val alternativeNames: List<String> = emptyList(),
    val platformsInfo: List<GamePlatformDetailInfo> = emptyList(),
    val storesInfo: List<GameStoreDetailInfo> = emptyList(),
    val developersInfo: List<GameDeveloperDetail> = emptyList(),
    val publishersInfo: List<GamePublisherDetail> = emptyList(),
    val genresInfo: List<GameGenreDetail> = emptyList(),
    val esrbRatingInfo: GameEsrbRatingDetail? = null,
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GameDetail(
                id = 416,
                name = "Grand Theft Auto: San Andreas",
                imageBackground =
                "https://media.rawg.io/media/games/960/" +
                    "960b601d9541cec776c5fa42a00bf6c4.jpg",
                imageBackgroundAdditional =
                "https://media.rawg.io/media/screenshots/236/" +
                    "236efd76a15fca0ce4d9129a788e517e.jpg",
                description =
                "Grand Theft Auto - San Andreas is the seventh entry in the series in the GTA " +
                    "franchise, but only second big title after GTA - Vice City. Setting up in " +
                    "fictional state San Andreas, you follow the story of CJ, a member of one of " +
                    "the multiple gangs in the city. CJ's family is being attacked in drive " +
                    "shooting which resulted in the death of CJ's mother, so he returns to home " +
                    "from Liberty City. Meeting the rest of the family at his mom's funeral, he " +
                    "decides to rebuild the gang and gain control of the state.\n\n" +
                    "The game makes a brilliant connection with missions and open world actions " +
                    "that you are able to do around the cities. You can steal cars, buy guns, " +
                    "hunt for collectables and do some side quests, while different characters " +
                    "give you specific missions in order to push the plot forward. Streets are " +
                    "filled with people as well as plenty of vehicles to steal. Fictional brands " +
                    "of cars, tanks, bikes are available to be stolen from any place around the " +
                    "city. Armoury contains pistols, rifles, hand-machine guns or a rocket " +
                    "launcher as well as melee weapons giving player freedom in anything he's " +
                    "doing in GTA.",
                metacritic = 93,
                released = "2004-10-26",
                website =
                "http://www.rockstargames.com/" +
                    "sanandreas/pc",
                rating = 4.5,
                alternativeNames = listOf("GTA SA", "GTA San Andreas", "serise"),
                platformsInfo = GamePlatformDetailInfo.sampleList,
                storesInfo = GameStoreDetailInfo.sampleList,
                developersInfo = GameDeveloperDetail.sampleList,
                publishersInfo = GamePublisherDetail.sampleList,
                genresInfo = GameGenreDetail.sampleList,
                esrbRatingInfo = GameEsrbRatingDetail(id = 4, name = "Mature")
            ),
            GameDetail(
                id = 417,
                name = "Grand Theft Auto: Vice City",
            )
        )
    }
}
