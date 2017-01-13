package br.com.simplepass.simplepassnew.map

import android.content.Context

import com.google.android.gms.maps.model.LatLng

/**
 * Created by hinovamobile on 10/01/17.
 */
class MapPresenterImpl(private val mMapInteractor: MapInteractor, private val mMapView: MapView) : MapPresenter {

    override fun updateMap(company: String) {
        mMapView.showProgress(true)
    }
}
