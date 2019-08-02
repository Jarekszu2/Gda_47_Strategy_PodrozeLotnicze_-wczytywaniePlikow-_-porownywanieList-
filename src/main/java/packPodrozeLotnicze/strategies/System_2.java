package packPodrozeLotnicze.strategies;

import packPodrozeLotnicze.ModelLotu;
import packPodrozeLotnicze.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class System_2 implements IStrategy {
    public List<ModelLotu> getListModelLotu() {
        List<ModelLotu> list = new ArrayList<>();

        List<String> listaZPliku = Utilities.getStrings("LOTY2");

        listaZPliku.remove(119);

        for (int i = 0; i < listaZPliku.size(); i+=7) {
            ModelLotu modelLotu = new ModelLotu();

            modelLotu.setNrLotu(listaZPliku.get(i + 1));

            long timestampLong = Long.valueOf(listaZPliku.get(i + 2));
            Timestamp timestamp = new Timestamp(timestampLong);
            modelLotu.setCzasWylotu(timestamp);

            long timestampLong2 = Long.valueOf(listaZPliku.get(i + 3));
            Timestamp timestamp2 = new Timestamp(timestampLong2);
            modelLotu.setCzasPrzylotu(timestamp2);

            String[] tabSplitFirstClass = listaZPliku.get(i + 4).split(" ");
            List<Integer> listFirstClass = Utilities.getIntegers(tabSplitFirstClass);
            modelLotu.setNumeryMiejscPierwszejKlasy(listFirstClass);

            String[] tabSplitSecondClass = listaZPliku.get(i + 5).split(" ");
            List<Integer> listSecondClass = Utilities.getIntegers(tabSplitSecondClass);
            modelLotu.setNumeryMiejscDrugiejKlasy(listSecondClass);

            if (listaZPliku.get(i + 6).isEmpty()) {
                modelLotu.setListaRezerwacji(null);
            } else {
                String rezerwacje = listaZPliku.get(i + 6).replace(",", " ");
                modelLotu.setListaRezerwacji(Utilities.getReservationList(rezerwacje));
            }
            list.add(modelLotu);
        }
        return list;
    }
}

