package packPodrozeLotnicze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static List<Integer> getIntegers(String[] tabSplitFirstClass) {
        List<Integer> listFirstClass = new ArrayList<>();
        for (int j = 0; j < tabSplitFirstClass.length; j++) {
            try {
                listFirstClass.add(Integer.valueOf(tabSplitFirstClass[j]));
            } catch (NumberFormatException e) {
                listFirstClass.add(0);
            }
        }
        return listFirstClass;
    }
    public static StringBuilder getStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("src/main/java/packPodrozeLotnicze/dane/");
        return stringBuilder;
    }

    public static List<Reservation> getReservationList(String rezerwacje) {
        String[] tabRezerwacje = rezerwacje.split(" ");
        List<Reservation> reservationList = new ArrayList<>();
        for (int j = 0; j < tabRezerwacje.length; j+=3) {
            Reservation reservation = new Reservation();
            reservation.setName(tabRezerwacje[j]);
            reservation.setNumerMiejsca(Integer.valueOf(tabRezerwacje[j + 1]));
            reservation.setNumerReserwacji(tabRezerwacje[j + 2]);
            reservationList.add(reservation);
        }
        return reservationList;
    }

    public static List<String> getStrings(String file) {
        String linia;
        List<String> listaZPliku = new ArrayList<>();
        StringBuilder stringBuilder = Utilities.getStringBuilder();
        stringBuilder.append(file);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(stringBuilder.toString()))) {
            while ((linia = bufferedReader.readLine()) != null) {
                listaZPliku.add(linia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaZPliku;
    }
}
