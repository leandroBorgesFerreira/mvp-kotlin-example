package br.com.simplepass.simplepassnew.domain

import com.google.android.gms.maps.model.Marker

/**
 * Created by leandro on 1/12/17.
 */
data class VanInMap(override val id: String,
                    override val latitude: Double,
                    override val longitude: Double,
                    override val title: String,
                    override val snippet: String,
                    override var marker: Marker? = null) : MapPoint{
}