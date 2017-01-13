package br.com.simplepass.simplepassnew;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.simplepassnew.domain.VanInMap;

/**
 * Created by leandro on 1/12/17.
 */

public class TestUtils {

    public static Iterable<VanInMap> provideDefaultVans(){
        List<VanInMap> vans = new ArrayList<>();

        vans.add(new VanInMap("1", -20.0, -43.0, "van 1", "direcao: qualquer1", null));
        vans.add(new VanInMap("2", -21.0, -44.0, "van 2", "direcao: qualquer2", null));
        vans.add(new VanInMap("3", -22.0, -45.0, "van 3", "direcao: qualquer3", null));
        vans.add(new VanInMap("4", -23.0, -46.0, "van 4", "direcao: qualquer4", null));

        return vans;
    }
}
