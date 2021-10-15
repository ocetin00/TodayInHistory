package com.oguzhancetin.todayinhistory

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.oguzhancetin.todayinhistory.data.WikiRepository
import com.oguzhancetin.todayinhistory.databinding.ActivityMainBinding
import com.oguzhancetin.todayinhistory.fragmentMain.FragmentMainViewModel
import com.oguzhancetin.todayinhistory.fragmentMain.FragmentMainViewModelFactory
import com.oguzhancetin.todayinhistory.model.Event
import com.oguzhancetin.todayinhistory.model.WikiResult
import com.oguzhancetin.todayinhistory.util.CardStackAdapter
import com.yuyakaido.android.cardstackview.*


//CardStackListener
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FragmentMainViewModel

    private val manager by lazy { CardStackLayoutManager(this, MyCardStackListener) }
    private val mAdapter by lazy { CardStackAdapter() }

    private var currentEvent = emptyList<Event>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@MainActivity,com.oguzhancetin.todayinhistory.R.layout.activity_main)

        binding.txtDateDesc.isSelected = true
        binding.lifecycleOwner = this

        setupCardStackView()
        initializeViewModel()

        binding.rewindButton.setOnClickListener {
            binding.cardStackView.rewind()
        }


    }


    private fun setupCardStackView() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        binding.cardStackView.apply {
            layoutManager = manager
            adapter = mAdapter
            itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
        }
    }



    private fun initializeViewModel() {
        val repository = WikiRepository()
        val viewModelFactory = FragmentMainViewModelFactory(repository)
        viewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        ).get(FragmentMainViewModel::class.java)

        viewModel.events.observe(this) {
            when (it) {
                is WikiResult.Succes -> {
                    cancelShimmerEffect()
                    val dateDesc = " Today in history " + " '${it.data?.date}'"
                    binding.txtDateDesc.text = dateDesc

                    it.data?.events?.let { events ->


                        currentEvent =events
                                //events.filter { event -> event.description.isNotBlank() && event.description !== "null" }
                        mAdapter.setEvents(events)

                    }
                }
                is WikiResult.Loading -> {

                }
                is WikiResult.Error -> {

                }
            }

        }
    }

    private fun cancelShimmerEffect() {
        binding.shimmerViewContainer.visibility = View.GONE
        binding.cardStackView.visibility = View.VISIBLE
        binding.txtDateDescPlaceholder.visibility = View.GONE
        binding.txtDateDesc.visibility = View.VISIBLE
    }


    object MyCardStackListener : CardStackListener {
        override fun onCardRewound() {

        }

        override fun onCardCanceled() {

        }

        override fun onCardAppeared(view: View?, position: Int) {
        }

        override fun onCardDisappeared(view: View?, position: Int) {

        }

        override fun onCardDragging(direction: Direction?, ratio: Float) {

        }

        override fun onCardSwiped(direction: Direction) {

        }
    }
}