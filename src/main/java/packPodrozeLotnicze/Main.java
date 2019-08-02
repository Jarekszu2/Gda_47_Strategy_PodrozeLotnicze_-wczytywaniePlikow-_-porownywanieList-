package packPodrozeLotnicze;

import packPodrozeLotnicze.strategies.System_1;
import packPodrozeLotnicze.strategies.System_2;
import packPodrozeLotnicze.strategies.System_3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        PodrozeLotnicze podrozeLotnicze = new PodrozeLotnicze();

        System.out.println();
        System.out.println("Lista: ModelLotu, wg: System_1");
        podrozeLotnicze.setStrategy(new System_1());
        List<ModelLotu> listaSystem_1 = podrozeLotnicze.getListModelLotu();
        podrozeLotnicze.printList(listaSystem_1);

        System.out.println();
        System.out.println("Lista: ModelLotu, wg: System_2");
        podrozeLotnicze.setStrategy(new System_2());
        List<ModelLotu> listaSystem_2 = podrozeLotnicze.getListModelLotu();
        podrozeLotnicze.printList(listaSystem_2);

        System.out.println();
        System.out.println("Lista: ModelLotu, wg: System_3");
        podrozeLotnicze.setStrategy(new System_3());
        List<ModelLotu> listaSystem_3 = podrozeLotnicze.getListModelLotu();
        podrozeLotnicze.printList(listaSystem_3);

        System.out.println();
        System.out.println("Set ModeliLotu");
        Set<ModelLotu> modelLotuSet = podrozeLotnicze.createSet();
        podrozeLotnicze.addListToSet(modelLotuSet, listaSystem_1);
        podrozeLotnicze.addListToSet(modelLotuSet, listaSystem_2);
        podrozeLotnicze.addListToSet(modelLotuSet, listaSystem_3);
        podrozeLotnicze.printSet(modelLotuSet);

        System.out.println();
        System.out.println("Mapa: nrLotu, ilość błędnych pozycji.");
        Map<String, Set<ModelLotu>> map = podrozeLotnicze.getMap(modelLotuSet);
        podrozeLotnicze.printMap(map);

//        System.out.println();
//        System.out.println("Wykaz pozycji z błędami:");
//        List<Reservation> reservationList = podrozeLotnicze.getReservationList(map);
//        podrozeLotnicze.printReservationList(reservationList);

        System.out.println();
        podrozeLotnicze.chooseNrLotuToCheck(map);
    }
}
