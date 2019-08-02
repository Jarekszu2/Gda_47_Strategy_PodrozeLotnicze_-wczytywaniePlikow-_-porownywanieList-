package packPodrozeLotnicze;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/*
- nr lotu
- czas wylotu (timestamp)
- czas przylotu (timestamp)
- numery miejsc pierwszej klasy
- numery miejsc drugiej klasy
- nazwiska osób które zarezerwowały loty, numery zarezerwowanych miejsc, numery rezerwacji

 */

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ModelLotu {
    private String nrLotu;
    private Timestamp czasWylotu;
    private Timestamp czasPrzylotu;
    private List<Integer> numeryMiejscPierwszejKlasy;
    private List<Integer> numeryMiejscDrugiejKlasy;
    private List<Reservation> listaRezerwacji;
}
