package br.com.seucaio.gamedex.model.game

data class GameScreenshot(
    val id: Int,
    val image: String
) {
    companion object {
        val sampleList = listOf(
            GameScreenshot(
                id = -1,
                image = "https://media.rawg.io/media/games/994/99496806493c2f39b9f191923de2a63b.jpg"
            ),
            GameScreenshot(
                id = 2021137,
                image = "https://media.rawg.io/media/screenshots/4f0/4f08c34cdee8f47944a169f80f34ebcd.jpg"
            ),
            GameScreenshot(
                id = 2021139,
                image = "https://media.rawg.io/media/screenshots/a23/a23b55359a952efd51025616e19820df.jpg"
            ),
            GameScreenshot(
                id = 2021140,
                image = "https://media.rawg.io/media/screenshots/623/6230ff9a1dfd3f6235687f13e752ba84.jpg"
            ),
            GameScreenshot(
                id = 2021141,
                image = "https://media.rawg.io/media/screenshots/a94/a94c08a4411580af9b295609a51a5384.jpg"
            ),
            GameScreenshot(
                id = 279011,
                image = "https://media.rawg.io/media/screenshots/efa/efa1af5a9b0fc4c22fc089572e568660.jpg"
            ),
            GameScreenshot(
                id = 279012,
                image = "https://media.rawg.io/media/screenshots/ddb/ddb6e708570bb8e9dad0cb11d56d880b.jpg"
            )
        )
    }
}
