package packPodrozeLotnicze.strategies;

import packPodrozeLotnicze.ModelLotu;
import packPodrozeLotnicze.Utilities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class System_3 implements IStrategy {
    public List<String> getListeNazwPlikowZDanymi() {
        List<String> listaPlikow = new ArrayList<>();

        // wczytuję z pliku lisę plików z danymi
        List<String> listaDanychZPliku = Utilities.getStrings("/flights/flights.txt");

        // sprawdzam i usuwam jak są puste elementy (na końcu mogą sie dopisać)
        for (int i = 0; i < listaDanychZPliku.size(); i++) {
            if (listaDanychZPliku.get(i).isEmpty()) {
                listaDanychZPliku.remove(i);
                i--;
            }
        }

        // wyciągam z listy same nazwy plików
        for (int i = 0; i < listaDanychZPliku.size(); i++) {
            String[] tabSplit = listaDanychZPliku.get(i).split("/");
            listaPlikow.add(tabSplit[1]);
        }
        return listaPlikow;
    }

    public List<String> getListStringSystem_3() {
        List<String> listaDanychZWszystkichPlikow = new ArrayList<>();

        // uzyskuję listę nazw plików z danymi
        List<String> listaPlikow = getListeNazwPlikowZDanymi();

        // dla każdego pliku wczytuję dane z jednego pliku do listy danych tego pliku, a następnie z tej listy do listy
        // zbiorczej danych z wszystkich plików
        for (int i = 0; i < listaPlikow.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/flights/");
            stringBuilder.append(listaPlikow.get(i));
            List<String> listaDanychZJednegoPliku = Utilities.getStrings(stringBuilder.toString());
            for (int j = 0; j < listaDanychZJednegoPliku.size(); j++) {
                listaDanychZWszystkichPlikow.add(listaDanychZJednegoPliku.get(j));
            }
        }

        // usuwam elementy puste z listy
        for (int i = 0; i < listaDanychZWszystkichPlikow.size(); i++) {
            if (listaDanychZWszystkichPlikow.get(i).isEmpty()) {
                listaDanychZWszystkichPlikow.remove(i);
                i--;
            }
        }
        return listaDanychZWszystkichPlikow;
    }

    public List<ModelLotu> getListModelLotu() {
        List<ModelLotu> listSystem_3 = new ArrayList<>();
        List<String> listaDanychZWszystkichPlikow = getListStringSystem_3();

        for (int i = 0; i < listaDanychZWszystkichPlikow.size(); i += 6) {
            ModelLotu modelLotu = new ModelLotu();

            String[] tab0 = listaDanychZWszystkichPlikow.get(i).split("=");
            modelLotu.setNrLotu(tab0[1]);

            String[] tab1 = listaDanychZWszystkichPlikow.get(i + 1).split("=");
            long timestampLong = Long.valueOf(tab1[1]);
            Timestamp timestamp = new Timestamp(timestampLong);
            modelLotu.setCzasWylotu(timestamp);


            String[] tab2 = listaDanychZWszystkichPlikow.get(i + 2).split("=");
            long timestampLong2 = Long.valueOf(tab2[1]);
            Timestamp timestamp2 = new Timestamp(timestampLong2);
            modelLotu.setCzasPrzylotu(timestamp2);

            String[] tab3 = listaDanychZWszystkichPlikow.get(i + 3).split("=");
            String[] tabSplitFirstClass = tab3[1].split(" ");
            List<Integer> listFirstClass = Utilities.getIntegers(tabSplitFirstClass);
            modelLotu.setNumeryMiejscPierwszejKlasy(listFirstClass);

            String[] tab4 = listaDanychZWszystkichPlikow.get(i + 4).split("=");
            String[] tabSplitSecondClass = tab4[1].split(" ");
            List<Integer> listSecondClass = Utilities.getIntegers(tabSplitSecondClass);
            modelLotu.setNumeryMiejscDrugiejKlasy(listSecondClass);

            String[] tab5 = listaDanychZWszystkichPlikow.get(i + 5).split("=");
            if (tab5.length > 1) {
                String rezerwacje = tab5[1].replace("/", " ").replace(",", " ");
                modelLotu.setListaRezerwacji(Utilities.getReservationList(rezerwacje));
            } else {
                modelLotu.setListaRezerwacji(null);
            }

            listSystem_3.add(modelLotu);
        }

        return listSystem_3;
    }
}
