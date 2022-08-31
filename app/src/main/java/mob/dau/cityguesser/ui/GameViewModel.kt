package mob.dau.cityguesser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mob.dau.cityguesser.data.MAX_OF_STEPS
import mob.dau.cityguesser.data.SCORE_INCREASE
import mob.dau.cityguesser.data.dataset

class GameViewModel : ViewModel() {

    private val data = dataset

    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private var _stepCount = MutableLiveData(0)
    val stepCount: LiveData<Int>
        get() = _stepCount

    private lateinit var _currentQuestToScreen: Pair<Int, Map<String, Boolean>>
    val currentQuestToScreen
        get() = _currentQuestToScreen

    private var _currentImage = MutableLiveData(1)
    val currentImage: LiveData<Int>
        get() = _currentImage

    private var _options1 = MutableLiveData("option1")
    val options1: LiveData<String>
        get() = _options1

    private var _options2 = MutableLiveData("option2")
    val options2: LiveData<String>
        get() = _options2

    private var _options3 = MutableLiveData("option3")
    val options3: LiveData<String>
        get() = _options3

    private var _options4 = MutableLiveData("option4")
    val options4: LiveData<String>
        get() = _options4

    private var questList = mutableListOf<Pair<Int, Map<String, Boolean>>>()
    private lateinit var currentQuest: Pair<Int, Map<String, Boolean>>

    init {
        getNextQuest()
    }

    private fun getNextQuest() {
        currentQuest = data.random()

        if (questList.contains(currentQuest)) {
            getNextQuest()
        } else {
            _currentQuestToScreen = currentQuest
            _currentImage.value = currentQuestToScreen.first
            _options1.value = currentQuestToScreen.second.keys.elementAt(0)
            _options2.value = currentQuestToScreen.second.keys.elementAt(1)
            _options3.value = currentQuestToScreen.second.keys.elementAt(2)
            _options4.value = currentQuestToScreen.second.keys.elementAt(3)
            _stepCount.value = (_stepCount.value)?.inc()
            questList.add(currentQuest)
        }
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserAnswerIsCorrect(userAnswer: String): Boolean {
        if (currentQuestToScreen.second[userAnswer] == true) {
            increaseScore()
            return true
        }
        return false
    }

    fun nextQuest(): Boolean {
        return if (_stepCount.value!! < MAX_OF_STEPS) {
            getNextQuest()
            true
        } else false
    }

    fun reinitializeData() {
        _stepCount.value = 0
        _score.value = 0
        questList.clear()
        getNextQuest()
    }
}