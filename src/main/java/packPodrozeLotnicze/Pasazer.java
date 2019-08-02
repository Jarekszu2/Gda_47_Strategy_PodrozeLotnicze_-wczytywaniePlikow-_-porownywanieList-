package packPodrozeLotnicze;

import lombok.Data;

@Data
public class Pasazer {
    private String imie;
    private String rezerwacja;

    public Pasazer(String imie, String rezerwacja) {
        this.imie = imie;
        this.rezerwacja = rezerwacja;
    }
}
