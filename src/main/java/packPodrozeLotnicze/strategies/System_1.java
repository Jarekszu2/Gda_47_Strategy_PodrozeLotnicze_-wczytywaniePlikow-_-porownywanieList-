package packPodrozeLotnicze.strategies;

import packPodrozeLotnicze.ModelLotu;
import packPodrozeLotnicze.Utilities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class System_1 implements IStrategy {
    public List<ModelLotu> getListModelLotu() {
        List<ModelLotu> modelLotuList = new ArrayList<>();

        List<String> listaZPliku = Utilities.getStrings("LOTY1");
        for (int i = 0; i < listaZPliku.size(); i++) {
            if(listaZPliku.get(i).isEmpty()){
                continue;
            }
            String[] tabSplit = listaZPliku.get(i).split(";");

            ModelLotu modelLotu = new ModelLotu();

            modelLotu.setNrLotu(tabSplit[0]);

            long timestampLong = Long.valueOf(tabSplit[1]);
            Timestamp timestamp = new Timestamp(timestampLong);
            modelLotu.setCzasWylotu(timestamp);

            long timestampLong2 = Long.valueOf(tabSplit[2]);
            Timestamp timestamp2 = new Timestamp(timestampLong2);
            modelLotu.setCzasPrzylotu(timestamp2);

            String[] tabSplitFirstClass = tabSplit[3].split("%");
            List<Integer> listFirstClass = Utilities.getIntegers(tabSplitFirstClass);
            modelLotu.setNumeryMiejscPierwszejKlasy(listFirstClass);

            String[] tabSplitSecondClass = tabSplit[4].split("%");
            List<Integer> listSecondClass = Utilities.getIntegers(tabSplitSecondClass);
            modelLotu.setNumeryMiejscDrugiejKlasy(listSecondClass);

            if (tabSplit.length > 5) {
                String rezerwacje = tabSplit[5].replace("#"," ").replace("%", " ");
                modelLotu.setListaRezerwacji(Utilities.getReservationList(rezerwacje));
            } else {
                modelLotu.setListaRezerwacji(null);
            }
            modelLotuList.add(modelLotu);
        }
        return modelLotuList;
    }




}
