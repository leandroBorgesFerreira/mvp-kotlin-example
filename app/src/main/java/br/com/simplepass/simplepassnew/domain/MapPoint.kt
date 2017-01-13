package br.com.simplepass.simplepassnew.domain


import com.google.android.gms.maps.model.Marker

/**
 * Created by hinovamobile on 12/09/16.
 */
interface MapPoint {
    val id: String
    val latitude: Double
    val longitude: Double
    val title: String
    val snippet: String
    var marker: Marker?
}
