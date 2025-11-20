package br.com.seucaio.gamedex.model.data

data class TopGameData(
    val id: Int,
    val name: String
) {
    companion object {
        val sampleList = listOf(
            TopGameData(1, "Grand Theft Auto III"),
            TopGameData(2, "Need For Speed: Carbon"),
            TopGameData(3, "Gun"),
            TopGameData(3, "Max Payne 3"),
            TopGameData(5, "Grand Theft Auto V"),
            TopGameData(6, "Fifa Street 2"),
            TopGameData(4, "Grand Theft Auto: San Andreas"),
            TopGameData(7, "Dirt Rally"),
        )
    }
}
