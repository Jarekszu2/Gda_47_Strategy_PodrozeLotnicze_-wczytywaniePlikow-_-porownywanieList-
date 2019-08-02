package packPodrozeLotnicze;

import lombok.Data;
import lombok.NoArgsConstructor;
import packPodrozeLotnicze.strategies.IStrategy;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PodrozeLotnicze {
    private IStrategy iStrategy;

    public void setStrategy(IStrategy iStrategy) {
        this.iStrategy = iStrategy;
    }

    public List<ModelLotu> getListModelLotu() {
        return iStrategy.getListModelLotu();
    }

    public void printList(List<ModelLotu> modelLotuList) {
        modelLotuList.forEach(System.out::println);
        System.out.println("Rozmiar: " + modelLotuList.size());
    }

    public Set<ModelLotu> createSet() {
        Set<ModelLotu> set = new HashSet<>();
        return set;
    }

    public void addListToSet(Set<ModelLotu> set, List<ModelLotu> list) {
        set.addAll(list);
    }

    public void printSet(Set<ModelLotu> modelLotuSet) {
        modelLotuSet.forEach(System.out::println);
        System.out.println("Rozmiar: " + modelLotuSet.size());
    }

    public Map<String, Set<ModelLotu>> getMap(Set<ModelLotu> set) {
        Set<String> strings = set.stream()
                .map(modelLotu -> modelLotu.getNrLotu()).collect(Collectors.toSet());

        Map<String, Set<ModelLotu>> map = strings.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        s -> set.stream()
                                .filter(modelLotu -> modelLotu.getNrLotu().equals(s))
                                .collect(Collectors.toSet())
                ));

        Map<String, Set<ModelLotu>> map1 = map.entrySet().stream()
                .filter(stringSetEntry -> stringSetEntry.getValue().size() > 1)
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));

        return map1;
    }

    public void printMap(Map<String, Set<ModelLotu>> map) {
        map.forEach((k, v) -> System.out.println(k + " " + v.size()));
    }

    public List<Reservation> getReservationList(Map<String, Set<ModelLotu>> map) {
        ScannerWork scannerWork = new ScannerWork();
        String nrLotu = scannerWork.getNrLotu(map);

        Set<ModelLotu> modelLotuSet = map.get(nrLotu);
        List<ModelLotu> modelLotuList = new ArrayList<>(modelLotuSet);
        ModelLotu modelLotu0 = modelLotuList.get(0);
        ModelLotu modelLotu1 = modelLotuList.get(1);
        List<Reservation> reservationList0 = modelLotu0.getListaRezerwacji();
        List<Reservation> reservationList1 = modelLotu1.getListaRezerwacji();
        if (reservationList0 == null) {
            try {
                throw new NullReservationException();
            } catch (NullReservationException e) {
                System.err.println("Lista jest pusta.");
                List<Reservation> list0 = new ArrayList<>();
                return list0;
            }
        } else if (reservationList1 == null) {
            try {
                throw new NullReservationException();
            } catch (NullReservationException e) {
                System.err.println("Lista jest pusta.");
                List<Reservation> list1 = new ArrayList<>();
                return list1;
            }
        } else {
            reservationList0.removeAll(reservationList1);
            return reservationList0;
        }
    }

    public void printReservationList(List<Reservation> list) {
        System.out.println("Lista błędów:");
        list.forEach(System.out::println);
    }

    public void chooseNrLotuToCheck(Map<String, Set<ModelLotu>> map) {
        ScannerWork scannerWork = new ScannerWork();
        boolean flag = false;
        do {
            System.out.println();
            System.out.println("Podaj:\n a) wybór NrLotu do sprawdzenia\n k) koniec sprawdzania");
            System.out.println();
            char znak = scannerWork.getChar();
            if (znak == 'a') {
                List<Reservation> reservationList = getReservationList(map);
                printReservationList(reservationList);
            } else {
                flag = true;
            }
        } while (!flag);
    }
}
