package generatory;
import java.util.*;

import języki.Język;

public class Generator2 extends Generator implements GenerowaniePoLiterze {
    public Generator2(Język język) {
        super(język);
        super.nazwa = "generator literowy";
    }

    public String wygenerujPoLiterze(String poprzedzająca){
        Random rng = new Random();
        double rand = rng.nextDouble();

        int k = 0;
        Map<Character, Double> słownikCzęstości = new HashMap<>();
        for(Map.Entry<Character, Integer> entry: język.częstości(poprzedzająca).entrySet()){
            k += entry.getValue();
            słownikCzęstości.put(entry.getKey(), entry.getValue().doubleValue());
        }
        double toOne = 0;
        for (Character key: słownikCzęstości.keySet()){
            słownikCzęstości.put(key, słownikCzęstości.get(key)/k);
        }

        double a = 0;
        for (Map.Entry<Character, Double> entry: słownikCzęstości.entrySet()){
            if (rand <= entry.getValue()+a){
                return entry.getKey() +"";
            } else{
                a += entry.getValue();
            }
        }
        return null;
    }

    @Override
    public String dajSłowo() { //słowo ma nieograniczoną długość żeby dawało użycie dla list (które trzeba było gdzieś dać") ograniczenie byłoby dość prostą pętlą for(int i = 0; i < 8; i++) jednak jedynie słowa o nieograniczonej długości dają jakiekolwiek prawo bytu listom (też naciągane, bo możnaby po prostu wydłużać String)
        List<String> słowo = new ArrayList<>();
        for (int i = 0; !słowo.contains(" "); i++){
            if(i == 0){
                słowo.add(wygenerujPoLiterze("-"));
            } else{
                słowo.add(wygenerujPoLiterze(słowo.get(i-1)));
            }
        }
        return String.join("", słowo);
    }

}
