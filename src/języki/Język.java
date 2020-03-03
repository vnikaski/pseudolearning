package języki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Język implements UczenieLiterowe{
    Map<String, Map<Character, Integer>> słownik = new HashMap<>();
    String nazwa;
    String literyAlfabetu;


    public void przygotujSłownikUL(){ //osobna metoda, a nie część konstruktora, aby zapewnić modułowość
        Map<Character, Integer> wystąpienia = new HashMap<>();
        for(int i = 0; i<literyAlfabetu.length(); i++){
            wystąpienia.put(literyAlfabetu.charAt(i), 0);
        }
        wystąpienia.put(' ', 0); //zakończenia po literze

        for (int i = 0; i < literyAlfabetu.length(); i++){
            słownik.put(literyAlfabetu.charAt(i) + "", new HashMap<Character, Integer>(wystąpienia));
        }
        słownik.put("-", new HashMap<Character, Integer>(wystąpienia));//słowo zaczyna się od litery
    }

    public Map<Character, Integer> częstości(String prefiks){ //Daje słownik częstości poszczególnych liter języka po podanym prefiksie
        return słownik.get(prefiks);
    }

    public void uczSię(String słowo){//zapamiętuje dane o podanym słowie
        if (słownik.size() == 0){
            przygotujSłownikUL();
        }
        for (int i = 0; i < słowo.length(); i++) {
            if (i != słowo.length() - 1) {
                słownik.get(słowo.charAt(i)+"").put(słowo.charAt(i + 1),
                        słownik.get(słowo.charAt(i)+"").get(słowo.charAt(i + 1)) + 1); //dodaje wystąpienie danej litery po literze ze słowa
            }else if(i == 0){
                słownik.get("-").put(słowo.charAt(i),
                        słownik.get("-").get(słowo.charAt(i)) + 1);
            }
            else {
                słownik.get(słowo.charAt(i)+"").put(' ',
                        słownik.get(słowo.charAt(i)+"").get(' ') + 1); //dodaje wystąpienia końca wyrazu po danej literze słowa
            }
        }
    }

    public void uczSięzPliku(String nazwaPliku) throws FileNotFoundException {// zapamiętuje dane o wszystkich słowach ze wskazanego pliku, obśługuje wyjątek braku pliku (wypisuje komunikat i kończy działanie tej metody)
        Scanner sk = new Scanner(new File(nazwaPliku)).useDelimiter("[^" + literyAlfabetu() + literyAlfabetu().toUpperCase() + "]+");
        while (sk.hasNext()) {
            String słowo = sk.next().toLowerCase();
            uczSię(słowo);
        }
        sk.close();
    }

    public String literyAlfabetu(){  // daje napis ze wszystkimi literami danego języka
        return literyAlfabetu;
    }

    public String nazwa(){ //// daje nazwę opisująca rodzaj języka
        return nazwa;
    }
}
