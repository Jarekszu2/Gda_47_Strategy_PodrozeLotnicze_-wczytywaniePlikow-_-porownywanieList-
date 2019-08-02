package packPodrozeLotnicze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainX {
    public static void main(String[] args) {
        List<Pasazer> l1 = new ArrayList<>(Arrays.asList(
                new Pasazer("a", "1"),
                new Pasazer("b", "2"),
                new Pasazer("c", "3")));
        List<Pasazer> l2 = new ArrayList<>(Arrays.asList(
                new Pasazer("a", "1"),
                new Pasazer("f", "2"),
                new Pasazer("c", "3")));

        l1.removeAll(l2);
        System.out.println(l1);


        List<String> list2 = Utilities.getStrings("LOTY2");
        list2.forEach(System.out::println);

        String rezerwacje = list2.get(6).replace(",", " ");
        System.out.println(rezerwacje);

        List<Reservation> list = Utilities.getReservationList(rezerwacje);
        list.forEach(System.out::println);

        System.out.println();
        System.out.println();
        String cos = "Aleksiej,1,FRFH67 Krichmar,8,IFA102 Maalouf,10,OWR577 Orazi,11,ERQ171 Moore,12,TYT423 Fidler,13,IIO670 Connor,14,USK910 Leduc,15,ZYA126 Ortega,16,ERT853 Bishop,17,MGD636 ";
        System.out.println(cos);
        String cos2 = cos.replaceAll(",", " ");
        System.out.println(cos2);
        List<Reservation> listCos = Utilities.getReservationList(cos2);
        listCos.forEach(System.out::println);
    }
}
