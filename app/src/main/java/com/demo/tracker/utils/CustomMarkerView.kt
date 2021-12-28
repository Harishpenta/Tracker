package com.demo.tracker.utils

import android.content.Context
import com.demo.tracker.db.Run
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    c: Context,
    layoutId: Int
) : MarkerView(c, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f ,-height.toFloat())
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }

        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timeStamp
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvDateMarker.text = dateFormat.format(calendar.time)
        val avgSpeed = "${run.avgSpeedInKMH}km/h"
        tvAvgSpeedMarker.text = avgSpeed
        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        tvDistanceMarker.text = distanceInKm
        tvDurationMarker.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillisPersonRun)
        val caloriesBurned = "${run.caloriesBurned}kcal"
        tvCaloriesBurnedMarker.text = caloriesBurned
    }
}