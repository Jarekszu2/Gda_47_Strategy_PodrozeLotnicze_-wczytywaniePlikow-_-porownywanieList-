package packPodrozeLotnicze;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ScannerWork {
    Scanner scanner = new Scanner(System.in);

    public String getNrLotu(Map<String, Set<ModelLotu>> map) {
        boolean flag = false;
        String nrLotu = "";
        do {
            System.out.println("Podaj nr lotu:");
            nrLotu = scanner.next();

            if (map.containsKey(nrLotu)) {
                flag = true;
            } else {
                System.err.println("Błędny nr lotu!");
            }
        } while (!flag);
        return nrLotu;
    }

    public char getChar() {
        boolean flag = false;
        char znak = 'a';
        do {
            System.out.println("Wybierz: a / k ?");
            znak = scanner.next().charAt(0);
            if (znak == 'a' || znak == 'k') {
                flag = true;
            }
        } while (!flag);
        return znak;
    }
}
