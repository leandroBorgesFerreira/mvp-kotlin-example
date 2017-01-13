package br.com.simplepass.simplepassnew.map

import android.content.Context
import br.com.simplepass.simplepassnew.base.BasePresenter

import com.google.android.gms.maps.model.LatLng

/**
 * Created by hinovamobile on 10/01/17.
 */
interface MapPresenter : BasePresenter{
    fun updateMap(company: String)
}
