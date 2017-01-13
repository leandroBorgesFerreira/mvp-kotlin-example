package br.com.simplepass.simplepassnew.map

import br.com.simplepass.simplepassnew.domain.VanInMap

/**
 * Created by leandro on 1/12/17.
 */
interface MapInteractor {
    fun requestPoints(): Iterable<VanInMap>
}