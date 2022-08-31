package mob.dau.cityguesser.data

import mob.dau.cityguesser.R

const val MAX_OF_STEPS = 10
const val SCORE_INCREASE = 10

val dataset: List<Pair<Int, Map<String, Boolean>>> = listOf(
    Pair(R.drawable.city1_img, mapOf("Moscow" to false, "Florence" to false, "San Francisco" to true, "Orlando" to false,)),
    Pair(R.drawable.city2_img, mapOf("Rio de Janeiro" to true, "Copenhagen" to false, "Sydney" to false, "Mumbai" to false,)),
    Pair(R.drawable.city3_img, mapOf("Venice" to false, "Moscow" to true, "Riyadh" to false, "Johannesburg" to false,)),
    Pair(R.drawable.city4_img, mapOf("Tokyo" to false, "Moscow" to false, "Madrid" to false, "London" to true,)),
    Pair(R.drawable.city5_img, mapOf("Antwerp" to false, "Copenhagen" to true, "Berlin" to false, "Stockholm" to false,)),
    Pair(R.drawable.city6_img, mapOf("Copenhagen" to false, "Stockholm" to true, "Milan" to false, "Amsterdam" to false,)),
    Pair(R.drawable.city7_img, mapOf("Berlin" to true, "Miami" to false, "Taipei" to false, "Rome" to false,)),
    Pair(R.drawable.city8_img, mapOf("Moscow" to false, "Florence" to false, "Istanbul" to true, "Orlando" to false,)),
    Pair(R.drawable.city9_img, mapOf("Toronto" to true, "Delhi" to false, "Phuket" to false, "Shenzhen" to false,)),
    Pair(R.drawable.city10_img, mapOf("Moscow" to false, "Florence" to false, "San Francisco" to false, "Seoul" to true,)),
)